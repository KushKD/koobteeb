package com.hp.dit.election_ems.Controller.transfer;

import com.hp.dit.election_ems.entities.ModuleMaster;
import com.hp.dit.election_ems.entities.TransferRequestEntities;
import com.hp.dit.election_ems.entities.TrdocumentsEntity;
import com.hp.dit.election_ems.entities.UserEntity;
import com.hp.dit.election_ems.form.module.ModuleForm;
import com.hp.dit.election_ems.form.transfer.TransferForm;
import com.hp.dit.election_ems.form.viewdata.ViewData;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.repositories.documents.TRDRepository;
import com.hp.dit.election_ems.repositories.transfer.TransferRepository;
import com.hp.dit.election_ems.services.FileStorageService;
import com.hp.dit.election_ems.validators.ModuleValidator;
import com.hp.dit.election_ems.validators.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class TransferController {

    @Autowired
    TransferValidator transferValidator;

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TRDRepository trdRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/createtransfer", method = RequestMethod.GET)
    public String createTransfer(Model model, HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("transferForm", new TransferForm());
                return "createTransfer";
            }

        }
    }


    @RequestMapping(value = "/viewTransfer", method = RequestMethod.GET)
    public String viewRequests(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                return "viewTransfer";
            }



        }
    }


    @RequestMapping(value = "/saveTransfer", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveDetails(@ModelAttribute("transferForm") TransferForm transferForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


        transferValidator.validate(transferForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createTransfer";
        }
        try {
            LoggedInUserSession userSession = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(userSession);

            System.out.println(transferForm.toString());
            TransferRequestEntities transferRequestEntities = new TransferRequestEntities();
            TransferRequestEntities savedTransferRequest = null;
            transferRequestEntities.setVehicleNumber(transferForm.getVehicleNo().toString());
            transferRequestEntities.setFromDate(transferForm.getFromDate().toString());
            transferRequestEntities.setThrueDate(transferForm.getThruDate().toString());
            transferRequestEntities.setSourceAddress(transferForm.getSourceAddress().toString());
            transferRequestEntities.setDestAddress(transferForm.getDestAddress().toString());
            transferRequestEntities.setActive(true);
            transferRequestEntities.setAmount(Long.parseLong(transferForm.getAmount()));

            UserEntity user = new UserEntity();
            user.setUserId(userSession.getUserId());
            transferRequestEntities.setEnteredBy(user);




            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            transferRequestEntities.setCreatedDate(date);

            savedTransferRequest = transferRepository.save(transferRequestEntities);


            /**
             * Saving Files
             */
            List<TrdocumentsEntity> documentsList = new ArrayList<>();
            for (int i = 0; i < transferForm.getAttachFiles().length; i++) {

                TrdocumentsEntity trd = new TrdocumentsEntity();


                System.out.println("file Name:-" + i + "\t" + transferForm.getAttachFiles()[i].getName());
                System.out.println("file Size:-" + i + "\t" + transferForm.getAttachFiles()[i].getSize());
                System.out.println("file Content Type:-" + i + "\t" + transferForm.getAttachFiles()[i].getContentType());
                System.out.println("\t \n");
                String fileName = fileStorageService.storeFile(transferForm.getAttachFiles()[i]);

                trd.setActive(true);
                trd.setDocumentName(fileName);
                trd.setEnteredBy(userSession.getUserId().intValue());

                Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
                Date date2 = new Date(timestamp2.getTime());
                trd.setCreatedDate(date2);

                trd.setTransferRequestID(savedTransferRequest.getTransferRequestID());
                documentsList.add(trd);

            }

            trdRepository.saveAll(documentsList);



            transferForm.setVehicleNo("");
            transferForm.setSourceAddress("");
            transferForm.setDestAddress("");
            transferForm.setFromDate("");
            transferForm.setThruDate("");
            request.getSession().setAttribute("successMessage", "Request Raised Successfully. Generated Transfer Id is:- " + savedTransferRequest.getTransferRequestID());
            return "createTransfer";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "createTransfer";
        }

    }

}

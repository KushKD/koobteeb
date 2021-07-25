package com.hp.dit.beetbook.Controller;

import com.hp.dit.beetbook.entities.PoliceStationMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.form.policestation.PSForm;
import com.hp.dit.beetbook.repositories.policestationRepository.PSRepository;
import com.hp.dit.beetbook.validators.PoliceStationUpdateValidator;
import com.hp.dit.beetbook.validators.PoliceStationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class PoliceStationController {

    @Autowired
    PSRepository psRepository;

    @Autowired
    PoliceStationValidator policeStationValidator;

    @Autowired
    PoliceStationUpdateValidator policeStationUpdateValidator;

    @RequestMapping(value = "/createps", method = RequestMethod.GET)
    public String createPS(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("pSForm", new PSForm());
            return "createPs";
        }
    }

    //savePs
    @Transactional
    @RequestMapping(value = "/savePs", method = RequestMethod.POST)
    public String saveState(@ModelAttribute("pSForm") PSForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        policeStationValidator.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            PoliceStationMaster polceStation = null;
            if (bindingResult.hasErrors()) {
                return "createPs";
            }

            try {
                PoliceStationMaster ps = new PoliceStationMaster();
                ps.setPsName(form.getPsName().toString());
                ps.setActive(true);
                ps.setStateID(Integer.parseInt(form.getStateId()));
                ps.setDistrictId(Integer.parseInt(form.getDistrictId()));
                ps.setSosdpoId(Integer.parseInt(form.getSosdpoId()));
                ps.setDeleted(false);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                ps.setCreatedDate(date);
                polceStation = psRepository.save(ps);
                form.setPsId("");
                request.getSession().setAttribute("successMessage", "Police Station Saved Successfully. Generated Police Station Id is:- " + polceStation.getPsId());

                return "createPs";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createPs";
            }
        }
    }



    @RequestMapping(value = "/viewps", method = RequestMethod.GET)
    public String viewStates(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            List<PoliceStationMaster> states = psRepository.getAllPoliceStation();
            model.addAttribute("police_stations", states);
            return "viewPs";
        }
    }

    @RequestMapping(value = "/updatePs/{psId}", method = RequestMethod.GET)
    public String updateState(@PathVariable("psId")Integer id, Model model) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            PoliceStationMaster state = psRepository.getPoliceStationViaId(id);
            System.out.println(state.toString());
            model.addAttribute("ps_to_update", state);
            model.addAttribute("pSForm", new PSForm());
            return "updatePs";

        }

    }

    //updatePs
    @Transactional
    @RequestMapping(value = "/updatePs", method = RequestMethod.POST)
    public String updatePs(@ModelAttribute("pSForm") PSForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        policeStationUpdateValidator.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            PoliceStationMaster savedPs = null;
            if (bindingResult.hasErrors()) {
                return "updatePs";
            }

            try {

                //Get State Data via ID

                PoliceStationMaster ps = new PoliceStationMaster();

                ps = psRepository.getPoliceStationViaId(Integer.parseInt(form.getPsId()));

                ps.setPsName(form.getPsName().toString());
                ps.setStateID(Integer.parseInt(form.getStateId()));
                ps.setDistrictId(Integer.parseInt(form.getDistrictId()));
                ps.setSosdpoId(Integer.parseInt(form.getSosdpoId()));


                if (form.getIsActive().equalsIgnoreCase("T")) {
                    ps.setActive(true);
                } else {
                    ps.setActive(false);
                }

                if (form.getIsDeleted().equalsIgnoreCase("T")) {
                    ps.setDeleted(true);
                } else {
                    ps.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                ps.setUpdatedOn(date);
                savedPs = psRepository.save(ps);
                form.setPsName("");
                request.getSession().setAttribute("successMessage", "Police Station Updated.");

                return "redirect:/viewps";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "updatePs";
            }

        }
    }
}

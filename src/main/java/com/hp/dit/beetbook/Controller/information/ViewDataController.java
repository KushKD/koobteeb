package com.hp.dit.beetbook.Controller.information;

import com.hp.dit.beetbook.Controller.HomeController;
import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.InformationEntity;
import com.hp.dit.beetbook.entities.PoliceStationMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import com.hp.dit.beetbook.form.ViewInformationWebForm;
import com.hp.dit.beetbook.form.viewdata.ViewData;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.modals.information.InformationMarkerWeb;
import com.hp.dit.beetbook.repositories.information.InformationRepository;
import com.hp.dit.beetbook.utilities.Utilities;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ViewDataController  {

    @Autowired
    InformationRepository informationRepository;



    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/viewData", method = RequestMethod.GET)
    public String viewData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("viewData", new ViewData());
            return "viewData";
        }
    }

    //viewCompleteData
    @RequestMapping(value = "/viewCompleteData", method = RequestMethod.GET)
    public String viewCompleteData(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {


            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                return "viewCompleteData";
            }




        }
    }

    @RequestMapping(value = "/getInformation", method = RequestMethod.POST)
    public String getActiveBeat(@ModelAttribute("viewData") ViewData viewDataForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            if (bindingResult.hasErrors()) {
                return "viewData";
            }
            try {

                List<Object[]> data = informationRepository.getmarkersWeb(
                        Integer.parseInt(viewDataForm.getStateId()),
                        Integer.parseInt(viewDataForm.getDistrictId()),
                        Integer.parseInt(viewDataForm.getSubmoduleId()),
                        Integer.parseInt(viewDataForm.getBeatId()),
                        Integer.parseInt(viewDataForm.getPsId()),
                        viewDataForm.getFromDate(),
                        viewDataForm.getToDate()
                );
                System.out.println(data.toString());

                if (!data.isEmpty()) {
                    List<InformationMarkerWeb> informationMarkerWebs = new ArrayList<>();


                    for (Object[] result : data) {
                        InformationMarkerWeb pojo = new InformationMarkerWeb();
                        pojo.setId((Integer) result[0]);
                        pojo.setSubmoduleId((Integer) result[1]);
                        pojo.setSubmoduleName((String) result[2]);
                        pojo.setName((String) result[3]);
                        pojo.setDate((String) result[4]);
                        informationMarkerWebs.add(pojo);
                    }
                    model.addAttribute("informationMarkerWebs", informationMarkerWebs);
                    model.addAttribute("state_id", viewDataForm.getStateId());
                    model.addAttribute("district_id", viewDataForm.getDistrictId());
                    model.addAttribute("sodpo_id", viewDataForm.getSosdpoId());
                    model.addAttribute("ps_id", viewDataForm.getPsId());
                    model.addAttribute("beat_id", viewDataForm.getBeatId());
                    model.addAttribute("submodule_id", viewDataForm.getSubmoduleId());


                    viewDataForm.setToDate(viewDataForm.getToDate());
                    viewDataForm.setFromDate(viewDataForm.getFromDate());
                    return "viewData";
                } else {
                    model.addAttribute("serverError", "No Data Found");
                    model.addAttribute("state_id", viewDataForm.getStateId());
                    model.addAttribute("district_id", viewDataForm.getDistrictId());
                    model.addAttribute("sodpo_id", viewDataForm.getSosdpoId());
                    model.addAttribute("ps_id", viewDataForm.getPsId());
                    model.addAttribute("beat_id", viewDataForm.getBeatId());
                    model.addAttribute("submodule_id", viewDataForm.getSubmoduleId());
                    return "viewData";
                }


            } catch (Exception ex) {
                model.addAttribute("serverError", ex.toString());
                model.addAttribute("state_id", viewDataForm.getStateId());
                model.addAttribute("district_id", viewDataForm.getDistrictId());
                model.addAttribute("sodpo_id", viewDataForm.getSosdpoId());
                model.addAttribute("ps_id", viewDataForm.getPsId());
                model.addAttribute("beat_id", viewDataForm.getBeatId());
                model.addAttribute("submodule_id", viewDataForm.getSubmoduleId());
                return "viewData";
            }
        }

    }

    @RequestMapping(value = "/updateInformation/{district_id}", method = RequestMethod.GET)
    public String updateInformation(@PathVariable("district_id")Integer district_id, Model model,HttpServletRequest request) throws Exception {

        System.out.println(district_id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            InformationEntity information =informationRepository.getCompleteInformationViaId(district_id);
            System.out.println(information.toString());


            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user.toString());

            model.addAttribute("state_id", information.getStateId());
            model.addAttribute("district_id", information.getDistrictId());
            model.addAttribute("sodpo_id", information.getSosdpoId());
            model.addAttribute("ps_id", information.getPsId().getPsId());
            model.addAttribute("beat_id", information.getBeatId().getBeatId());
            model.addAttribute("submodule_id", information.getSubmoduleId().getSubmoduleId());
            model.addAttribute("information", information);
            model.addAttribute("user", user);

            model.addAttribute("viewInformationWebForm", new ViewInformationWebForm());
            return "viewInformation";

        }
    }


    @Transactional
    @RequestMapping(value = "/updateInformationEntity", method = RequestMethod.POST)
    public String updateInformationEntity(@ModelAttribute("viewInformationWebForm") ViewInformationWebForm form, Model model,HttpServletRequest request) throws Exception {

        System.out.println(form.toString());
        System.out.println(form.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            InformationEntity information =informationRepository.getCompleteInformationViaId(form.getId());
            System.out.println(information.toString());


            if(Utilities.ifEmptyField(Integer.toString(form.getStateId()))){
                System.out.println(form.getStateId());
                information.setStateId(form.getStateId());
            }
            if(Utilities.ifEmptyField(Integer.toString(form.getDistrictId()))){
                System.out.println(form.getDistrictId());
                information.setDistrictId(form.getDistrictId());
            }
            if(Utilities.ifEmptyField(Integer.toString(form.getSosdpoId()))){
                System.out.println(form.getSosdpoId());
                information.setSosdpoId(form.getSosdpoId());
            }
            if(Utilities.ifEmptyField(Integer.toString(form.getPsId()))){
                System.out.println(form.getPsId());
                PoliceStationMaster policeStationMaster = new PoliceStationMaster();
                policeStationMaster.setPsId(form.getPsId());
                information.setPsId(policeStationMaster);
            }
            if(Utilities.ifEmptyField(Integer.toString(form.getBeatId()))){
                System.out.println(form.getBeatId());
                BeatMaster beat = new BeatMaster();
                beat.setBeatId(form.getBeatId());
                information.setBeatId(beat);
            }
            if(Utilities.ifEmptyField(Integer.toString(form.getSubmoduleId()))){
                System.out.println(form.getSubmoduleId());
                SubModuleMaster subModuleMaster = new SubModuleMaster();
                subModuleMaster.setSubmoduleId(form.getSubmoduleId());
                information.setSubmoduleId(subModuleMaster);
            }
            if(Utilities.ifEmptyField(form.getName())){
                System.out.println(form.getName());
                information.setName(form.getName());
            }
            if(Utilities.ifEmptyField(form.getOwner_name())){
                System.out.println(form.getOwner_name());
                information.setOwnerName(form.getOwner_name());
            }
            if(Utilities.ifEmptyField(form.getOwner_nametwo())){
                System.out.println(form.getOwner_nametwo());
                information.setOwnerNameTwo(form.getOwner_nametwo());
            }
            if(Utilities.ifEmptyField(form.getContact_numberone())){
                System.out.println(form.getContact_numberone());
                information.setContactNoOne(form.getContact_numberone());
            }
            if(Utilities.ifEmptyField(form.getContact_numbertwo())){
                System.out.println(form.getContact_numbertwo());
                information.setContactNoTwo(form.getContact_numbertwo());
            }
            if(Utilities.ifEmptyField(form.getHelpline_number())){
                System.out.println(form.getHelpline_number());
                information.setHelplineNumber(form.getHelpline_number());
            }
            if(Utilities.ifEmptyField(form.getLandline_number())){
                System.out.println(form.getLandline_number());
                information.setLandlineNumber(form.getLandline_number());
            }
            if(Utilities.ifEmptyField(form.getCctv())){
                System.out.println(form.getCctv());
                information.setCctv(form.getCctv());
            }
            if(Utilities.ifEmptyField(form.getNumber_idols())){
                System.out.println(form.getNumber_idols());
                information.setNumberIdols(form.getNumber_idols());
            }
            if(Utilities.ifEmptyField(form.getNumber_securitypersons())){
                System.out.println(form.getNumber_securitypersons());
                information.setNumberSecurityPersons(form.getNumber_securitypersons());
            }
            if(Utilities.ifEmptyField(form.getEmail_id())){
                System.out.println(form.getEmail_id());
                information.setEmailId(form.getEmail_id());
            }
            if(Utilities.ifEmptyField(form.getFacbook_id())){
                System.out.println(form.getFacbook_id());
                information.setFacbookId(form.getFacbook_id());
            }
            if(Utilities.ifEmptyField(form.getPermanent_address())){
                System.out.println(form.getPermanent_address());
                information.setPermanentAddress(form.getPermanent_address());
            }
            if(Utilities.ifEmptyField(form.getPresent_address())){
                System.out.println(form.getPresent_address());
                information.setPresentAddress(form.getPresent_address());
            }
            if(Utilities.ifEmptyField(form.getFir_no())){
                System.out.println(form.getFir_no());
                information.setFirNo(form.getFir_no());
            }
            if(Utilities.ifEmptyField(form.getFir_details())){
                System.out.println(form.getFir_details());
                information.setFirDetails(form.getFir_details());
            }
            if(Utilities.ifEmptyField(form.getLicencee_no())){
                System.out.println(form.getLicencee_no());
                information.setLicenceeNo(form.getLicencee_no());
            }
            if(Utilities.ifEmptyField(form.getLicencee_name())){
                System.out.println(form.getLicencee_name());
                information.setLicenceeName(form.getLicencee_name());
            }
            if(Utilities.ifEmptyField(form.getDetails())){
                System.out.println(form.getDetails());
                information.setDetails(form.getDetails());
            }
            if(Utilities.ifEmptyField(form.getOther())){
                System.out.println(form.getOther());
                information.setOther(form.getOther());
            }
            if(Utilities.ifEmptyField(form.getChecking_date_sho())){
                System.out.println(form.getChecking_date_sho());
                information.setCheckingDateSho(form.getChecking_date_sho());
            }
            if(Utilities.ifEmptyField(form.getTotal_population())){
                System.out.println(form.getTotal_population());
                information.setTotalPopulation(form.getTotal_population());
            }
            if(Utilities.ifEmptyField(form.getPeriod_fair())){
                System.out.println(form.getPeriod_fair());
                information.setPeriodFair(form.getPeriod_fair());
            }
            if(Utilities.ifEmptyField(form.getAuthority())){
                System.out.println(form.getAuthority());
                information.setAuthority(form.getAuthority());
            }
            if(Utilities.ifEmptyField(form.getDuration_parole())){
                System.out.println(form.getDuration_parole());
                information.setDurationParole(form.getDuration_parole());
            }
            if(Utilities.ifEmptyField(form.getId_proof())){
                System.out.println(form.getId_proof());
                information.setIdProof(form.getId_proof());
            }
            if(Utilities.ifEmptyField(form.getSection())){
                System.out.println(form.getSection());
                information.setSection(form.getSection());
            }
            if(Utilities.ifEmptyField(form.getSpecial_reported_cases())){
                System.out.println(form.getSpecial_reported_cases());
                information.setSpecialReportedCases(form.getSpecial_reported_cases());
            }
            if(Utilities.ifEmptyField(form.getExtra_one())){
                System.out.println(form.getExtra_one());
                information.setExtraOne(form.getExtra_one());
            }
            if(Utilities.ifEmptyField(form.getExtra_two())){
                System.out.println(form.getExtra_two());
                information.setExtraTwo(form.getExtra_two());
            }

            if(Utilities.ifEmptyField(form.getLatitude())){
                System.out.println(form.getLatitude());
                information.setLatitude(Double.parseDouble(form.getLatitude()));
            }
            if(Utilities.ifEmptyField(form.getLongitude())){
                System.out.println(form.getLongitude());
                information.setLongitude(Double.parseDouble(form.getLongitude()));
            }

            /**
             * Location Points
             */
            GeometryFactory geometryFactory = new GeometryFactory();

            Coordinate coordinate = new Coordinate();
            coordinate.x = information.getLongitude();
            coordinate.y = information.getLatitude();

            Point myPoint = geometryFactory.createPoint(coordinate);
            myPoint.setSRID(4326);
            information.setLocationPoints(myPoint);


            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            information.setUpdatedOn(date);

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user.toString());

            InformationEntity newEntity = new InformationEntity();
            newEntity = informationRepository.save(information);

            model.addAttribute("state_id", newEntity.getStateId());
            model.addAttribute("district_id", newEntity.getDistrictId());
            model.addAttribute("sodpo_id", newEntity.getSosdpoId());
            model.addAttribute("ps_id", newEntity.getPsId());
            model.addAttribute("beat_id", newEntity.getBeatId());
            model.addAttribute("submodule_id", newEntity.getSubmoduleId().getSubmoduleId());
            model.addAttribute("information", newEntity);
            model.addAttribute("user", user);
            model.addAttribute("viewInformationWebForm", new ViewInformationWebForm());
            model.addAttribute("serverError", "Data Updated Successfully.");
            return "viewInformation";

        }
    }
}

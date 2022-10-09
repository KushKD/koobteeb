package com.hp.dit.election_ems.Controller.beat;

import com.hp.dit.election_ems.entities.BeatMaster;
import com.hp.dit.election_ems.entities.DistrictMaster;
import com.hp.dit.election_ems.entities.PoliceStationMaster;
import com.hp.dit.election_ems.entities.StatesMaster;
import com.hp.dit.election_ems.form.beat.BeatForm;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.repositories.beats.BeatRepository;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class BeatController {


    @Autowired
    BeatRepository beatRepository;

    @RequestMapping(value = "/createbeat", method = RequestMethod.GET)
    public String createDistrict(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                model.addAttribute("beatForm", new BeatForm());
                return "createbeat";
            }



        }
    }

    //saveState
    @Transactional
    @RequestMapping(value = "/saveBeat", method = RequestMethod.POST)
    public String saveDistrict(@ModelAttribute("beatForm") BeatForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
      //  districtValidator.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            BeatMaster saveBeat = null;
            if (bindingResult.hasErrors()) {
                return "createbeat";
            }

            try {
                BeatMaster beat = new BeatMaster();
                beat.setBeatName(form.getBeatName().toString());

                if (form.getIsActive().equalsIgnoreCase("T")) {
                    beat.setActive(true);
                } else {
                    beat.setActive(false);
                }

                if (form.getIsDeleted().equalsIgnoreCase("T")) {
                    beat.setDeleted(true);
                } else {
                    beat.setDeleted(false);
                }

                StatesMaster stateId = new StatesMaster();
                stateId.setStateID(Integer.parseInt(form.getStateId()));
                beat.setStateID(stateId);

                DistrictMaster districtMaster = new DistrictMaster();
                districtMaster.setDistrictId(Integer.parseInt(form.getDistrictId()));
                beat.setDistrictId(districtMaster);

                beat.setSosdpoId(Integer.parseInt(form.getSosdpoId()));

                PoliceStationMaster ps = new PoliceStationMaster();
                ps.setPsId(Integer.parseInt(form.getPsId()));
                beat.setPsId(ps);

               // beat.setLatitude(Double.parseDouble(form.getLatitude()));
               // beat.setLongitude(Double.parseDouble(form.getLongitude()));


                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                beat.setCreatedDate(date);
                saveBeat = beatRepository.save(beat);
                form.setBeatName("");
                request.getSession().setAttribute("successMessage", "Beat Saved Successfully. Generated Beat Id is:- " + saveBeat.getBeatId());

                return "createbeat";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createbeat";
            }
        }

    }

    //viewbeat
    @RequestMapping(value = "/viewbeat", method = RequestMethod.GET)
    public String viewbeat(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {


            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                return "viewbeat";
            }




        }
    }

    //updateBeat beatId
    @RequestMapping(value = "/updateBeat/{beatId}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("beatId")Integer beat_Id, Model model, HttpServletRequest request) throws Exception {

        System.out.println(beat_Id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                BeatMaster beatDetails =beatRepository.getBeatViaId(beat_Id);
                System.out.println(beatDetails.toString());
                model.addAttribute("beat_to_update", beatDetails);
                model.addAttribute("beatForm", new BeatForm());
                return "updatebeat";
            }


        }
    }


    @Transactional
    @RequestMapping(value = "/updateBeatEntity", method = RequestMethod.POST)
    public String updateDistrictEntity(@ModelAttribute("beatForm") BeatForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
       // districtValidatorUpdate.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            BeatMaster updatedBeat = null;
            if (bindingResult.hasErrors()) {
                return "viewbeat";
            }

            try {

                //Get State Data via ID

                BeatMaster beat = new BeatMaster();
                beat = beatRepository.getBeatViaId(Integer.parseInt(form.getBeatId()));
                beat.setBeatName(form.getBeatName().toString());

                if (form.getIsActive().equalsIgnoreCase("T")) {
                    beat.setActive(true);
                } else {
                    beat.setActive(false);
                }

                if (form.getIsDeleted().equalsIgnoreCase("T")) {
                    beat.setDeleted(true);
                } else {
                    beat.setDeleted(false);
                }

                StatesMaster stateId = new StatesMaster();
                stateId.setStateID(Integer.parseInt(form.getStateId()));
                beat.setStateID(stateId);

                DistrictMaster districtMaster = new DistrictMaster();
                districtMaster.setDistrictId(Integer.parseInt(form.getDistrictId()));
                beat.setDistrictId(districtMaster);




                beat.setSosdpoId(Integer.parseInt(form.getSosdpoId()));

                PoliceStationMaster policeStationMaster = new PoliceStationMaster();
                policeStationMaster.setPsId(Integer.parseInt(form.getPsId()));
                beat.setPsId(policeStationMaster);

               // beat.setLatitude(Double.parseDouble(form.getLatitude()));
               // beat.setLongitude(Double.parseDouble(form.getLongitude()));

                GeometryFactory geometryFactory = new GeometryFactory();

//                Coordinate coordinate = new Coordinate();
//                coordinate.x = beat.getLongitude();
//                coordinate.y = beat.getLatitude();
//
//                Point myPoint = geometryFactory.createPoint(coordinate);
//                myPoint.setSRID(4326);
//                beat.setBeatGeometry(myPoint);


                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                beat.setCreatedDate(date);
                updatedBeat = beatRepository.save(beat);
                form.setBeatName("");
                request.getSession().setAttribute("successMessage", "Beat Updated.");

                return "redirect:/viewbeat";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "updatebeat";
            }

        }
    }


}
package com.hp.dit.beetbook.Controller;

import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.form.beat.BeatForm;
import com.hp.dit.beetbook.form.district.DistrictForm;
import com.hp.dit.beetbook.repositories.beats.BeatRepository;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepository;
import com.hp.dit.beetbook.validators.DistrictValidator;
import com.hp.dit.beetbook.validators.DistrictValidatorUpdate;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
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
public class BeatController {


    @Autowired
    BeatRepository beatRepository;

    @RequestMapping(value = "/createbeat", method = RequestMethod.GET)
    public String createDistrict(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("beatForm", new BeatForm());
            return "createbeat";
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

                beat.setStateID(Integer.parseInt(form.getStateId()));
                beat.setDistrictId(Integer.parseInt(form.getDistrictId()));
                beat.setSosdpoId(Integer.parseInt(form.getSosdpoId()));
                beat.setPsId(Integer.parseInt(form.getPsId()));

                beat.setLatitude(Double.parseDouble(form.getLatitude()));
                beat.setLongitude(Double.parseDouble(form.getLongitude()));


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
    public String viewbeat(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            List<BeatMaster> beats = beatRepository.getAllBeats();
            model.addAttribute("beats", beats);
            return "viewbeat";
        }
    }

    //updateBeat beatId
    @RequestMapping(value = "/updateBeat/{beatId}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("beatId")Integer beat_Id, Model model) throws Exception {

        System.out.println(beat_Id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            BeatMaster beatDetails =beatRepository.getBeatViaId(beat_Id);
            System.out.println(beatDetails.toString());
            model.addAttribute("beat_to_update", beatDetails);
            model.addAttribute("beatForm", new BeatForm());
            return "updatebeat";

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

                beat.setStateID(Integer.parseInt(form.getStateId()));
                beat.setDistrictId(Integer.parseInt(form.getDistrictId()));
                beat.setSosdpoId(Integer.parseInt(form.getSosdpoId()));
                beat.setPsId(Integer.parseInt(form.getPsId()));

                beat.setLatitude(Double.parseDouble(form.getLatitude()));
                beat.setLongitude(Double.parseDouble(form.getLongitude()));

                GeometryFactory geometryFactory = new GeometryFactory();

                Coordinate coordinate = new Coordinate();
                coordinate.x = beat.getLongitude();
                coordinate.y = beat.getLatitude();

                Point myPoint = geometryFactory.createPoint(coordinate);
                myPoint.setSRID(4326);
                beat.setBeatGeometry(myPoint);


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

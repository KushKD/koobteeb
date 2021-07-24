package com.hp.dit.beetbook.Controller;


import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.form.district.DistrictForm;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepository;
import com.hp.dit.beetbook.validators.DistrictValidator;
import com.hp.dit.beetbook.validators.DistrictValidatorUpdate;
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
public class DistrictsController {

    @Autowired
    DistrictValidator districtValidator;

    @Autowired
    DistrictValidatorUpdate districtValidatorUpdate;



    @Autowired
    DistrictRepository districtRepository;

    @RequestMapping(value = "/createDistrict", method = RequestMethod.GET)
          public String createDistrict(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("districtForm", new DistrictForm());
            return "createDistrict";
        }
          }


          //saveState
          @Transactional
          @RequestMapping(value = "/saveDistrict", method = RequestMethod.POST)
          public String saveDistrict(@ModelAttribute("districtForm") DistrictForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
              districtValidator.validate(form, bindingResult);
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                  return "login";
              } else {
                  DistrictMaster savedistrict = null;
                  if (bindingResult.hasErrors()) {
                      return "createDistrict";
                  }

                  try {
                      DistrictMaster district = new DistrictMaster();
                      district.setDistrictName(form.getDistrictName().toString());

                      if (form.getDistrictIsActive().equalsIgnoreCase("T")) {
                          district.setActive(true);
                      } else {
                          district.setActive(false);
                      }

                      if (form.getDistrictIsDeleted().equalsIgnoreCase("T")) {
                          district.setDeleted(true);
                      } else {
                          district.setDeleted(false);
                      }

                      district.setStateID(Integer.parseInt(form.getStateId()));


                      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                      Date date = new Date(timestamp.getTime());
                      district.setCreatedDate(date);
                      savedistrict = districtRepository.save(district);
                      form.setDistrictName("");
                      request.getSession().setAttribute("successMessage", "District Saved Successfully. Generated State Id is:- " + savedistrict.getStateID());

                      return "createDistrict";
                  } catch (Exception ex) {
                      request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                      return "createDistrict";
                  }
              }



          }


    @RequestMapping(value = "/viewDistrict", method = RequestMethod.GET)
    public String viewDistrict(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            List<DistrictMaster> districts = districtRepository.getAllDistricts();
            model.addAttribute("districts", districts);
            return "viewDistrict";
        }
    }


    @RequestMapping(value = "/updateDistrict/{district_id}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("district_id")Integer district_id, Model model) throws Exception {

        System.out.println(district_id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            DistrictMaster district =districtRepository.getDistrictViaId(district_id);
            System.out.println(district.toString());
            model.addAttribute("district_to_update", district);
            model.addAttribute("districtForm", new DistrictForm());
            return "updateDistrict";

        }
    }


    //update District
    @Transactional
    @RequestMapping(value = "/updateDistrictEntity", method = RequestMethod.POST)
    public String updateDistrictEntity(@ModelAttribute("districtForm") DistrictForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        districtValidatorUpdate.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            DistrictMaster savedDistrict = null;
            if (bindingResult.hasErrors()) {
                return "viewDistrict";
            }

            try {

                //Get State Data via ID

                DistrictMaster master = new DistrictMaster();

                master = districtRepository.getDistrictViaId(Integer.parseInt(form.getDistrictId()));

                master.setDistrictName(form.getDistrictName());
                master.setDistrictId(Integer.parseInt(form.getDistrictId()));

                master.setStateID(Integer.parseInt(form.getStateId()));

                if (form.getDistrictIsActive().equalsIgnoreCase("T")) {
                    master.setActive(true);
                } else {
                    master.setActive(false);
                }

                if (form.getDistrictIsDeleted().equalsIgnoreCase("T")) {
                    master.setDeleted(true);
                } else {
                    master.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                master.setUpdatedOn(date);
                savedDistrict = districtRepository.save(master);
                form.setDistrictName("");
                request.getSession().setAttribute("successMessage", "District Updated.");

                return "redirect:/viewDistrict";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "updateDistrict";
            }

        }
    }
}

package com.hp.dit.beetbook.Controller;

import com.hp.dit.beetbook.form.activebeat.ActiveBeat;
import com.hp.dit.beetbook.form.viewdata.ViewData;
import com.hp.dit.beetbook.modals.activeBeatModal.ActiveBeatModal;
import com.hp.dit.beetbook.modals.information.InformationMarkerWeb;
import com.hp.dit.beetbook.repositories.information.InformationRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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
}

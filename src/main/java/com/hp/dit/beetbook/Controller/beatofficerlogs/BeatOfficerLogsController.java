package com.hp.dit.beetbook.Controller.beatofficerlogs;


import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.form.district.DistrictForm;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
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

@Controller
public class BeatOfficerLogsController {

    @RequestMapping(value = "/beatOfficerLogs", method = RequestMethod.GET)
    public String viewDistrict(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            return "beatOfficerLogs";
        }
    }


}

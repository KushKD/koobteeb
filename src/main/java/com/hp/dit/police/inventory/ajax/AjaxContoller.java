package com.hp.dit.police.inventory.ajax;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.dit.police.inventory.entities.DistrictMaster;
import com.hp.dit.police.inventory.entities.PoliceLines;
import com.hp.dit.police.inventory.entities.PoliceStationMaster;
import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.repositories.RolesRepository;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import com.hp.dit.police.inventory.repositories.policelines.PoliceLinesRepository;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.repositories.user.UserRepository;
import com.hp.dit.police.inventory.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AjaxContoller {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    PoliceLinesRepository policeLinesRepository;

    @Autowired
    PSRepository psRepository;







    private static final Logger logger = LoggerFactory.getLogger(AjaxContoller.class);


    @RequestMapping(value = "/ajax/getStates", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getStates() throws Exception {
        Map<String, Object> map = null;
        List<StatesMaster> states = stateRepository.getAllStates();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, states);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);

        return jsonStr;

    }




    @RequestMapping(value = "/ajax/getsospdo", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getSOSDPO() throws Exception {
        Map<String, Object> map = null;
        List<PoliceLines> states = policeLinesRepository.getAllActiveSOSdo();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, states);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);

        return jsonStr;

    }





    @RequestMapping(value = "/ajax/getDistrictViaState", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getDistrictViaState(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;
        List<DistrictMaster> districts = districtRepository.findDistrictByStateId(Integer.parseInt(id));

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, districts);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);
        return jsonStr;


    }

    @RequestMapping(value = "/ajax/soSdpo", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getSoSDpo(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;
        List<DistrictMaster> districts = districtRepository.findDistrictByStateId(Integer.parseInt(id));

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, districts);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);
        return jsonStr;


    }



    @RequestMapping(value = "/ajax/getPoliceStations", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getPoliceStations(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;
        List<PoliceStationMaster> districts = psRepository.getAllActivePoliceStationViaSoSdpo(Integer.parseInt(id));

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, districts);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);
        return jsonStr;


    }


}



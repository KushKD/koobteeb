package com.hp.dit.police.inventory.ajax;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.dit.police.inventory.entities.*;
import com.hp.dit.police.inventory.modals.RUsersPojo;
import com.hp.dit.police.inventory.modals.RolesModal;
import com.hp.dit.police.inventory.modals.beats.BeatsNameId;
import com.hp.dit.police.inventory.repositories.RolesRepository;
import com.hp.dit.police.inventory.repositories.beats.BeatRepository;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import com.hp.dit.police.inventory.repositories.policelines.PoliceLinesRepository;
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
import java.util.*;

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

    @Autowired
    BeatRepository beatRepository;






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


    @RequestMapping(value = "/ajax/getRoles", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getRoles() throws JsonProcessingException {
        Map<String, Object> map = null;
        List<Object[] > roles = rolesRepository.getRoles();
        List<RolesModal> modelRole = new ArrayList<>();


        for (Object[] result : roles) {
            RolesModal pojo = new RolesModal();
            pojo.setRole_id((Integer) result[0]);
            pojo.setRole_name((String) result[1]);
            modelRole.add(pojo);
        }

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, modelRole);
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



    @RequestMapping(value = "/ajax/getBeatsViaPolceStation", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getBeatsViaPolceStation(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;
        List<BeatsNameId> beats = beatRepository.findBeatNameIdByPSId(Integer.parseInt(id));

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, beats);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);
        return jsonStr;


    }


    /**
     ###################### ID Card Generation Type Reports ##########################
     */






    //getAllRevenueUsers
    @RequestMapping(value = "/ajax/getAllRevenueUsers", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getAllRevenueUsers(

            @RequestParam(value = "stateId", required = true) String stateId,
            @RequestParam(value = "districtId", required = true) String districtId,
            @RequestParam(value = "barrierId", required = true) String barrierId
    ) throws Exception {


        Map<String, Object> map = null;
        logger.info("stateId" + stateId);
        logger.info("districtID" + districtId);
        logger.info("barrierId" + barrierId);

        System.out.println("stateId" + stateId + "districtID" + districtId + "barrierId" + barrierId);

        try {

            List<Object[]> items = userRepository.getRevenueUsersViaBarrier(

                    Integer.parseInt(stateId),
                    Integer.parseInt(districtId),
                    Integer.parseInt(barrierId), "REVENUE");

            List<RUsersPojo> modelRole = new ArrayList<>();


            for (Object[] result : items) {
                RUsersPojo pojo = new RUsersPojo();
                pojo.setUserName((String) result[0]);
                pojo.setUserId((Integer) result[1]);
                modelRole.add(pojo);
            }

            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, modelRole);
            map.put(Constants.keyMessage, Constants.valueMessage);
            map.put(Constants.keyStatus, HttpStatus.OK);

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = null;
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            return jsonStr;

        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, ex.getLocalizedMessage());
            map.put(Constants.keyMessage, Constants.valueMessage);
            map.put(Constants.keyStatus, HttpStatus.OK);
            //return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = null;
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);

            return  jsonStr;

        }



    }

    //getOptionsViaSubModuleId


}



package com.hp.dit.election_ems.ajax;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.dit.election_ems.entities.*;
import com.hp.dit.election_ems.modals.*;
import com.hp.dit.election_ems.modals.beats.BeatsNameId;
import com.hp.dit.election_ems.modals.usersviabeat.ShowCashRequestData;
import com.hp.dit.election_ems.modals.usersviabeat.UsersViaBeat;
import com.hp.dit.election_ems.repositories.RolesRepository;
import com.hp.dit.election_ems.repositories.beats.BeatRepository;
import com.hp.dit.election_ems.repositories.districtRepository.DistrictRepository;
import com.hp.dit.election_ems.repositories.documents.TRDRepository;
import com.hp.dit.election_ems.repositories.modules.ModuleRepository;
import com.hp.dit.election_ems.repositories.policestationRepository.PSRepository;
import com.hp.dit.election_ems.repositories.bank.BankRepository;
import com.hp.dit.election_ems.repositories.stateRepository.StateRepository;
import com.hp.dit.election_ems.repositories.submodules.SubModuleRepository;
import com.hp.dit.election_ems.repositories.transfer.TransferRepository;
import com.hp.dit.election_ems.repositories.user.UserRepository;
import com.hp.dit.election_ems.repositories.userdatatable.UserDatatableRepository;
import com.hp.dit.election_ems.utilities.Constants;
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
    BankRepository bankRepository;

    @Autowired
    PSRepository psRepository;

    @Autowired
    BeatRepository beatRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    SubModuleRepository subModuleRepository;

    @Autowired
    UserDatatableRepository userDatatableRepository;

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TRDRepository trdRepository;




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


    @RequestMapping(value = "/ajax/getModules", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getModules() throws Exception {
        Map<String, Object> map = null;
        List<ModuleMaster> modules = moduleRepository.getAllActiveModules();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, modules);
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
        List<BankMaster> states = bankRepository.getAllActiveSOSdo();

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


    @RequestMapping(value = "/ajax/getSubModules", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getSubModules() throws Exception {
        Map<String, Object> map = null;
        List<SubModuleMaster> subModules = subModuleRepository.getAllActiveSubModules();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, subModules);
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


    @RequestMapping(value = "/ajax/getUsers", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getUsers(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;

        List<UsersViaBeat> users = userDatatableRepository.getActiveUsersViaBeat(Integer.parseInt(id));
        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, users);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);
        System.out.println(jsonStr);
        return jsonStr;


    }


    //getTransferRequestData
    @RequestMapping(value = "/ajax/getTransferRequestData", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    String getTransferRequestData(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;

        TransferRequestEntities users = transferRepository.getTransactionViaId(Integer.parseInt(id));
        List<TrdocumentsEntity> documents = trdRepository.getDocumentsViaId(Integer.parseInt(id));
        ShowCashRequestData data = new ShowCashRequestData();
        data.setDocuments(documents);
        data.setVehicleNo(users.getVehicleNumber());
        data.setSourceAddress(users.getSourceAddress());
        data.setDestAddress(users.getDestAddress());
        data.setFromDate(users.getFromDate());
        data.setThruDate(users.getThrueDate());
        data.setEnteredBy(users.getEnteredBy().getUserName());
        data.setEnteredByMobile(Long.toString(users.getEnteredBy().getMobileNumber()));
        data.setRole(users.getEnteredBy().getRoles().get(0).getRoleName());
        data.setRoleDesc(users.getEnteredBy().getRoles().get(0).getRoleDescription());


        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, data);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        jsonStr = Obj.writeValueAsString(map);
        logger.info(jsonStr);
        System.out.println(jsonStr);
        return jsonStr;


    }

}



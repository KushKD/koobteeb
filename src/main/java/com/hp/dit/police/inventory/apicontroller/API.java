package com.hp.dit.police.inventory.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.dit.police.inventory.entities.DistrictMaster;
import com.hp.dit.police.inventory.entities.PoliceStationMaster;
import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.repositories.RolesRepository;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import com.hp.dit.police.inventory.repositories.policelines.PoliceLinesRepository;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.repositories.user.UserRepository;
import com.hp.dit.police.inventory.repositories.userlocationlogs.UserLocationLogsRepository;
import com.hp.dit.police.inventory.security.EncryptDecrypt;
import com.hp.dit.police.inventory.services.FileStorageService;
import com.hp.dit.police.inventory.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class API {

    private static final Logger logger = LoggerFactory.getLogger(API.class);


    @Autowired
    StateRepository stateRepository;

    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    UserRepository userRepository;


    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private PoliceLinesRepository policeLinesRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PSRepository psRepository;



    @Autowired
    UserLocationLogsRepository userLocationLogsRepository;






    @PersistenceContext
    private EntityManager entityManager;

    ObjectMapper objectMapper = new ObjectMapper();


    /**
     * @param fileName
     * @param request
     * @return Function to get the Download the File Via URL
     */

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    /**
     * @return Get States Return encrypted  Data
     */
    @RequestMapping(value = "/api/states", method = RequestMethod.GET, produces = Constants.ProducesPlainText)
    public String getStates() throws JsonProcessingException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Map<String, Object> map = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String jsonStr = null;

        try {
            List<StatesMaster> states = stateRepository.getAllStates();
            if (!states.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, states);
                map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                map.put(Constants.keyStatus, HttpStatus.OK.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                logger.info(ED.encrypt(jsonStr));
                return ED.encrypt(jsonStr);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, states);
                map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                return ED.encrypt(jsonStr);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, ex.getLocalizedMessage().toString());
            map.put(Constants.keyMessage, "Server was unable to process the Request. Please try again Later.");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            return ED.encrypt(jsonStr);

        }
    }



    /**
     * @return Get States Return encrypted  Data
     */
    @RequestMapping(value = "/api/allstates", method = RequestMethod.GET, produces = Constants.ProducesPlainText)
    public String getAllStates() throws JsonProcessingException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Map<String, Object> map = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String jsonStr = null;

        try {
            List<StatesMaster> states = stateRepository.getCompleteListStates();
            if (!states.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, states);
                map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                map.put(Constants.keyStatus, HttpStatus.OK.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                logger.info(ED.encrypt(jsonStr));
                return ED.encrypt(jsonStr);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, states);
                map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                return ED.encrypt(jsonStr);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, ex.getLocalizedMessage().toString());
            map.put(Constants.keyMessage, "Server was unable to process the Request. Please try again Later.");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            return ED.encrypt(jsonStr);

        }
    }







    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/api/getDistricts", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getDistricts(@RequestBody String stateId) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String state_id = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;

        if (stateId != null && !stateId.isEmpty()) {
            logger.info("State ID:-\t" + stateId);
            state_id = ED.decrypt(stateId);

            try {
                List<DistrictMaster> districts = districtRepository.findDistrictByStateId(Integer.parseInt(state_id));
                if (!districts.isEmpty()) {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, districts);
                    map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, districts);
                    map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                    map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    return ED.encrypt(jsonStr);
                }
            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, ex.getLocalizedMessage().toString());
                map.put(Constants.keyMessage, "Server was unable to process the Request. Please try again Later.");
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                return ED.encrypt(jsonStr);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "State ID Passed not in valid format");
            map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            logger.info(ED.encrypt(jsonStr));
            return ED.encrypt(jsonStr);
        }
    }


    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/api/getPoliceStations", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getPoliceStations(@RequestBody String stateId) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String state_id = null, jsonStr = null;
        String district_id = null, sosdpo_id = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;

        if (stateId != null && !stateId.isEmpty()) {
            logger.info("State ID:-\t" + stateId);
            state_id = ED.decrypt(stateId);

            try {

                JsonObject jsonObject = new JsonParser().parse(state_id).getAsJsonObject();
                System.out.println(jsonObject.toString());
                logger.info("API:: User Data is (Json Object ):- " + jsonObject);
                stateId = jsonObject.getAsJsonObject().get("stateId").getAsString();
                district_id = jsonObject.getAsJsonObject().get("districtId").getAsString();
                sosdpo_id = jsonObject.getAsJsonObject().get("sdpoId").getAsString();

                logger.info("stateId:- " + stateId);
                logger.info("districtId:- " + district_id);
                logger.info("SoSDPOId:- " + sosdpo_id);

                List<PoliceStationMaster> policeStations = psRepository.getAllActivePoliceStationViaDistrictSoSdpo(Integer.parseInt(stateId),Integer.parseInt(district_id),Integer.parseInt(sosdpo_id));
                if (!policeStations.isEmpty()) {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, policeStations);
                    map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, policeStations);
                    map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                    map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    return ED.encrypt(jsonStr);
                }
            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, ex.getLocalizedMessage().toString());
                map.put(Constants.keyMessage, "Server was unable to process the Request. Please try again Later.");
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                return ED.encrypt(jsonStr);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Data Passed not in valid format");
            map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            logger.info(ED.encrypt(jsonStr));
            return ED.encrypt(jsonStr);
        }
    }







}

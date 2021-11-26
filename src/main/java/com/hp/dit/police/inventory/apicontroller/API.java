package com.hp.dit.police.inventory.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.dit.police.inventory.entities.*;
import com.hp.dit.police.inventory.modals.RolesModal;
import com.hp.dit.police.inventory.modals.RolesUser;
import com.hp.dit.police.inventory.modals.UsePoJo;
import com.hp.dit.police.inventory.modals.UserPojoWithRole;
import com.hp.dit.police.inventory.modals.activeBeatModal.ActiveBeatModal;
import com.hp.dit.police.inventory.modals.beats.BeatsNameId;
import com.hp.dit.police.inventory.modals.information.InformationMarkers;
import com.hp.dit.police.inventory.modals.information.InformationViaId;
import com.hp.dit.police.inventory.modals.submoduleModal.SubModuleRoleList;
import com.hp.dit.police.inventory.repositories.RolesRepository;
import com.hp.dit.police.inventory.repositories.beats.BeatRepository;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import com.hp.dit.police.inventory.repositories.information.InformationRepository;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import com.hp.dit.police.inventory.repositories.sosdpo.SoSdpoRepository;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.repositories.user.UserRepository;
import com.hp.dit.police.inventory.repositories.userlocationlogs.UserLocationLogsRepository;
import com.hp.dit.police.inventory.security.EncryptDecrypt;
import com.hp.dit.police.inventory.services.FileStorageService;
import com.hp.dit.police.inventory.utilities.Constants;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

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
    BeatRepository beatRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SoSdpoRepository soSdpoRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PSRepository psRepository;



    @Autowired
    UserLocationLogsRepository userLocationLogsRepository;



    @Autowired
    InformationRepository informationRepository;




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


    @RequestMapping(value = "/api/roles", method = RequestMethod.GET, produces = Constants.ProducesPlainText)
    public String getRoles() throws JsonProcessingException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Map<String, Object> map = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String jsonStr = null;

        try {
            List<Object[] > roles = rolesRepository.getRoles();
            List<RolesModal> modelRole = new ArrayList<>();


            for (Object[] result : roles) {
                RolesModal pojo = new RolesModal();
                pojo.setRole_id((Integer) result[0]);
                pojo.setRole_name((String) result[1]);
                modelRole.add(pojo);
            }
            if (!modelRole.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, modelRole);
                map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                map.put(Constants.keyStatus, HttpStatus.OK.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                logger.info(ED.encrypt(jsonStr));
                return ED.encrypt(jsonStr);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, modelRole);
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

    @RequestMapping(value = "/api/sosdpo", method = RequestMethod.GET, produces = Constants.ProducesPlainText)
    public String getsosdpo() throws JsonProcessingException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Map<String, Object> map = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String jsonStr = null;

        try {
            List<S0SdpoMaster> states = soSdpoRepository.getAllActiveSOSdo();
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


    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/api/getBeats", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getBeats(@RequestBody String stateId) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String state_id = null, jsonStr = null;
        String district_id = null, sosdpo_id = null, ps_id = null;
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
                ps_id = jsonObject.getAsJsonObject().get("psId").getAsString();

                logger.info("stateId:- " + stateId);
                logger.info("districtId:- " + district_id);
                logger.info("SoSDPOId:- " + sosdpo_id);
                logger.info("PSID:- " + ps_id);

                List<BeatsNameId> beats = beatRepository.findBeatNameIdByPSId(Integer.parseInt(ps_id));
                if (!beats.isEmpty()) {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, beats);
                    map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, beats);
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




    /**
     * Login Function
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String loginBeat(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String userData_ = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String encrypted = null, psid=null, username = null, beatid=null, password = null, stateId = null, districtId = null, sosdpoid = null, mobileNumber = null, roleId;

        if (userData != null && !userData.isEmpty()) {
            logger.info("User Data Encrypted:-\t" + userData);
            userData_ = ED.decrypt(userData);
            logger.info("User Data DeEncrypted:-\t" + userData_);

            try {
                JsonObject jsonObject = new JsonParser().parse(userData_).getAsJsonObject();
                System.out.println(jsonObject.toString());
                logger.info("API:: User Data is (Json Object ):- " + jsonObject);


                username = jsonObject.getAsJsonObject().get("username").getAsString();
                password = jsonObject.getAsJsonObject().get("password").getAsString();
                stateId = jsonObject.getAsJsonObject().get("stateId").getAsString();
                districtId = jsonObject.getAsJsonObject().get("districtId").getAsString();
                sosdpoid = jsonObject.getAsJsonObject().get("sdpoId").getAsString();
                psid = jsonObject.getAsJsonObject().get("psId").getAsString();
                beatid = jsonObject.getAsJsonObject().get("beatId").getAsString();

                logger.info("Username:- " + username);
                logger.info("password:- " + password);
                logger.info("stateId:- " + stateId);
                logger.info("districtId:- " + districtId);
                logger.info("barrierId:- " + sosdpoid);
                logger.info("psid:- " + psid);
                logger.info("beatid:- " + beatid);


                UsePoJo user = userRepository.apiLogin(
                        Integer.parseInt(stateId),
                        Integer.parseInt(districtId),
                        Integer.parseInt(sosdpoid),
                        Integer.parseInt(psid),
                        Integer.parseInt(beatid),
                        username,password);
                logger.info("User \t" + user.toString());

                if (user != null) {

                    UserPojoWithRole userwithRole = new UserPojoWithRole();
                    userwithRole.setUserPojo(user);

                    logger.info("User with Role");
                    logger.info(userwithRole.toString());

                    //Get Role of the User
                    //TODO kush

                    logger.info("Getting Data from Roles Table");
                   List<Object[]> rolesOfUser = rolesRepository.getRoleViaUser(user.getUser_id());
                    List<RolesUser> rolesUser = new ArrayList<>();


                    for (Object[] result : rolesOfUser) {
                        RolesUser pojo = new RolesUser();
                        pojo.setRole_id((Integer) result[0]);
                        pojo.setRole_name((String) result[1]);
                        rolesUser.add(pojo);
                    }

                    logger.info("Roles List Data" , rolesUser.toString());
                    logger.info("Roles ID " , rolesUser.get(0).getRole_id());
                    logger.info("Roles Name " , rolesUser.get(0).getRole_name());

                    userwithRole.setRoleId(rolesUser.get(0).getRole_id());
                    userwithRole.setRoleName(rolesUser.get(0).getRole_name());

                    System.out.println(user.toString());
                    PasswordEncoder encoder = new BCryptPasswordEncoder();
                    System.out.println(encoder.encode(password));

                    boolean isPasswordMatch = encoder.matches(password, user.getPassword());
                    System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

                    //Get RoleId via UserPojo UserID

                    if (isPasswordMatch) {

                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, userwithRole);
                        map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                        map.put(Constants.keyStatus, HttpStatus.OK.value());
                        Obj = new ObjectMapper();
                        jsonStr = Obj.writeValueAsString(map);
                        logger.info(jsonStr);
                        logger.info(ED.encrypt(jsonStr));
                        return ED.encrypt(jsonStr);

                    } else {
                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, "Password Doesn't Match!");
                        map.put(Constants.keyMessage, "Request Successful.");
                        map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                        Obj = new ObjectMapper();
                        jsonStr = Obj.writeValueAsString(map);
                        logger.info(jsonStr);
                        return ED.encrypt(jsonStr);
                    }

                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "No User found");
                    map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                    map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    return ED.encrypt(jsonStr);
                }

            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "No User found");
                map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                return ED.encrypt(jsonStr);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Data not in valid format");
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
     * Login SO and SHO
     */
    @RequestMapping(value = "/api/loginSoSP", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String loginSoSP(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String userData_ = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String encrypted = null, psid=null, username = null, beatid=null, password = null, stateId = null, districtId = null, sosdpoid = null, mobileNumber = null, roleId;

        if (userData != null && !userData.isEmpty()) {
            logger.info("User Data Encrypted:-\t" + userData);
            userData_ = ED.decrypt(userData);
            logger.info("User Data DeEncrypted:-\t" + userData_);

            try {
                JsonObject jsonObject = new JsonParser().parse(userData_).getAsJsonObject();
                System.out.println(jsonObject.toString());
                logger.info("API:: User Data is (Json Object ):- " + jsonObject);


                username = jsonObject.getAsJsonObject().get("username").getAsString();
                password = jsonObject.getAsJsonObject().get("password").getAsString();
                stateId = jsonObject.getAsJsonObject().get("stateId").getAsString();
                districtId = jsonObject.getAsJsonObject().get("districtId").getAsString();
                sosdpoid = jsonObject.getAsJsonObject().get("sdpoId").getAsString();
                psid = jsonObject.getAsJsonObject().get("psId").getAsString();

                logger.info("Username:- " + username);
                logger.info("password:- " + password);
                logger.info("stateId:- " + stateId);
                logger.info("districtId:- " + districtId);
                logger.info("barrierId:- " + sosdpoid);
                logger.info("psid:- " + psid);


                UsePoJo user = userRepository.apiLoginSho(
                        Integer.parseInt(stateId),
                        Integer.parseInt(districtId),
                        Integer.parseInt(sosdpoid),
                        Integer.parseInt(psid),
                        username,password);
                logger.info("User \t" + user.toString());

                if (user != null) {

                    UserPojoWithRole userwithRole = new UserPojoWithRole();
                    userwithRole.setUserPojo(user);

                    logger.info("User with Role");
                    logger.info(userwithRole.toString());

                    //Get Role of the User
                    //TODO kush

                    logger.info("Getting Data from Roles Table");
                    List<Object[]> rolesOfUser = rolesRepository.getRoleViaUser(user.getUser_id());
                    List<RolesUser> rolesUser = new ArrayList<>();


                    for (Object[] result : rolesOfUser) {
                        RolesUser pojo = new RolesUser();
                        pojo.setRole_id((Integer) result[0]);
                        pojo.setRole_name((String) result[1]);
                        rolesUser.add(pojo);
                    }

                    logger.info("Roles List Data" , rolesUser.toString());
                    logger.info("Roles ID " , rolesUser.get(0).getRole_id());
                    logger.info("Roles Name " , rolesUser.get(0).getRole_name());

                    userwithRole.setRoleId(rolesUser.get(0).getRole_id());
                    userwithRole.setRoleName(rolesUser.get(0).getRole_name());

                    System.out.println(user.toString());
                    PasswordEncoder encoder = new BCryptPasswordEncoder();
                    System.out.println(encoder.encode(password));

                    boolean isPasswordMatch = encoder.matches(password, user.getPassword());
                    System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

                    //Get RoleId via UserPojo UserID

                    if (isPasswordMatch) {

                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, userwithRole);
                        map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                        map.put(Constants.keyStatus, HttpStatus.OK.value());
                        Obj = new ObjectMapper();
                        jsonStr = Obj.writeValueAsString(map);
                        logger.info(jsonStr);
                        logger.info(ED.encrypt(jsonStr));
                        return ED.encrypt(jsonStr);

                    } else {
                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, "Password Doesn't Match!");
                        map.put(Constants.keyMessage, "Request Successful.");
                        map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                        Obj = new ObjectMapper();
                        jsonStr = Obj.writeValueAsString(map);
                        logger.info(jsonStr);
                        return ED.encrypt(jsonStr);
                    }

                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "No User found");
                    map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                    map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    return ED.encrypt(jsonStr);
                }

            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "No User found");
                map.put(Constants.keyMessage, "Request Successful. No Data Found.");
                map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                return ED.encrypt(jsonStr);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Data not in valid format");
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
     * Login SO and SHO
     */
    @RequestMapping(value = "/api/saveLocationLogs", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String saveLocationLogs(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String LogsData_ = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String encrypted = null,  username = null, beatid=null,  mobileNumber = null, roleId = null , userId = null;
        String latitude_ =null, longitude_=null;
        UserLocationLogsEntity userLocationLogsEntity = null, userSavedLocationLogsEntity = null;

        if (userData != null && !userData.isEmpty()) {
            logger.info("User Data Encrypted:-\t" + userData);
            LogsData_ = ED.decrypt(userData);
            logger.info("User Data DeEncrypted:-\t" + LogsData_);

            try {
                JsonObject jsonObject = new JsonParser().parse(LogsData_).getAsJsonObject();
                System.out.println(jsonObject.toString());
                logger.info("API:: Logs Data is (Json Object ):- " + jsonObject);


                username = jsonObject.getAsJsonObject().get("username").getAsString();
                userId = jsonObject.getAsJsonObject().get("user_id").getAsString();
                roleId = jsonObject.getAsJsonObject().get("role_id").getAsString();
                mobileNumber = jsonObject.getAsJsonObject().get("mobile").getAsString();
                beatid = jsonObject.getAsJsonObject().get("beat_id").getAsString();
                latitude_ = jsonObject.getAsJsonObject().get("latitude").getAsString();
                longitude_ = jsonObject.getAsJsonObject().get("longitude").getAsString();

                logger.info("Username:- " + username);
                logger.info("userId:- " + userId);
                logger.info("roleId:- " + roleId);
                logger.info("mobileNumber:- " + mobileNumber);
                logger.info("beatid:- " + beatid);
                logger.info("latitude_:- " + latitude_);
                logger.info("longitude_:- " + longitude_);


                userLocationLogsEntity = new UserLocationLogsEntity();
                userLocationLogsEntity.setUsername(username);
                userLocationLogsEntity.setUserId(Integer.parseInt(userId));
                userLocationLogsEntity.setRoleId(Integer.parseInt(roleId));
                userLocationLogsEntity.setMobileNumber(Long.valueOf(mobileNumber));
                userLocationLogsEntity.setBeat_id(Integer.parseInt(beatid));
                userLocationLogsEntity.setLatitude(Double.parseDouble(latitude_));
                userLocationLogsEntity.setLongitude(Double.parseDouble(longitude_));
                userLocationLogsEntity.setActive(true);

                GeometryFactory geometryFactory = new GeometryFactory();

                Coordinate coordinate = new Coordinate();
                coordinate.x = Double.parseDouble(longitude_);
                coordinate.y = Double.parseDouble(latitude_);

                Point myPoint = geometryFactory.createPoint(coordinate);
                myPoint.setSRID(4326);
                userLocationLogsEntity.setLocationPoints(myPoint);


                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                userLocationLogsEntity.setCreatedDate(date);

                logger.info("User \t" + userLocationLogsEntity.toString());

                userSavedLocationLogsEntity = userLocationLogsRepository.save(userLocationLogsEntity);
                if (userSavedLocationLogsEntity!=null) {



                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, userSavedLocationLogsEntity.getLogsId());
                        map.put(Constants.keyMessage, "Saved successfully.");
                        map.put(Constants.keyStatus, HttpStatus.OK.value());
                        Obj = new ObjectMapper();
                        jsonStr = Obj.writeValueAsString(map);
                        logger.info(jsonStr);
                        logger.info(ED.encrypt(jsonStr));
                        return ED.encrypt(jsonStr);



                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "Unable to save Data");
                    map.put(Constants.keyMessage, "Request Successful. Unable to save Data");
                    map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    return ED.encrypt(jsonStr);
                }

            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "Data not in valid format");
                map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                logger.info(ED.encrypt(jsonStr));
                return ED.encrypt(jsonStr);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Data not in valid format");
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
     * saveInformation
     */
    @Transactional
    @RequestMapping(value = "/api/saveInformation", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String saveInformation(@RequestBody String indormation) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String info = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<SubModuleRoleList> submodules = null;

        InformationEntity informationToSave = new InformationEntity();
        InformationEntity informationSaved = null;

        if (indormation != null && !indormation.isEmpty()) {
            logger.info("Information ID:-\t" + indormation);
            info = ED.decrypt(indormation);
            logger.info("Information Decrypt ID:-\t" + info);

            try {
                informationToSave = objectMapper.readValue(info, InformationEntity.class);

                informationToSave.setActive(true);
                GeometryFactory geometryFactory = new GeometryFactory();

                Coordinate coordinate = new Coordinate();
                coordinate.x = informationToSave.getLongitude();
                coordinate.y = informationToSave.getLatitude();

                Point myPoint = geometryFactory.createPoint(coordinate);
                myPoint.setSRID(4326);
                informationToSave.setLocationPoints(myPoint);


                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                informationToSave.setCreatedDate(date);



                logger.info("informationToSave :-" + informationToSave.toString());


                informationSaved = informationRepository.save(informationToSave);

                if (informationSaved!=null) {


                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, informationSaved.getId());
                    map.put(Constants.keyMessage, "Request Successful. Data Saved Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "Error in saving the data");
                    map.put(Constants.keyMessage, "Request Successful. Error in saving the data");
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
            map.put(Constants.keyResponse, "Data in Invalid format");
            map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            logger.info(ED.encrypt(jsonStr));
            return ED.encrypt(jsonStr);
        }
    }

    @RequestMapping(value = "/api/getInformationViaId", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getInformationViaId(@RequestBody String id) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String role_id = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<SubModuleRoleList> submodules = null;

        if (id != null && !id.isEmpty()) {
            logger.info("Role ID:-\t" + id);
            role_id = ED.decrypt(id);

            try {
                InformationViaId information = informationRepository.getInformationViaId(Integer.parseInt(role_id));

                if (information!=null) {


                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, information);
                    map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "");
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
            map.put(Constants.keyResponse, "Sub Module ID Passed not in valid format");
            map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            logger.info(ED.encrypt(jsonStr));
            return ED.encrypt(jsonStr);
        }
    }


    @RequestMapping(value = "/api/getMarkers", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getMarkers(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String LogsData_ = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String encrypted = null,  beatid=null, moduleId = null, submoduleId=null, psId = null;
        UserLocationLogsEntity userLocationLogsEntity = null, userSavedLocationLogsEntity = null;

        if (userData != null && !userData.isEmpty()) {
            logger.info("User Data Encrypted:-\t" + userData);
            LogsData_ = ED.decrypt(userData);
            logger.info("User Data DeEncrypted:-\t" + LogsData_);

            try {
                JsonObject jsonObject = new JsonParser().parse(LogsData_).getAsJsonObject();
                System.out.println(jsonObject.toString());
                logger.info("API:: Logs Data is (Json Object ):- " + jsonObject);

                beatid = jsonObject.getAsJsonObject().get("beat_id").getAsString();
                moduleId = jsonObject.getAsJsonObject().get("module_id").getAsString();
                submoduleId = jsonObject.getAsJsonObject().get("submodule_id").getAsString();
                psId = jsonObject.getAsJsonObject().get("ps_id").getAsString();


                logger.info("beatid:- " + beatid);
                logger.info("moduleId:- " + moduleId);
                logger.info("submoduleId:- " + submoduleId);
                logger.info("psId:- " + psId);

                List<InformationMarkers> markers = informationRepository.getmarkersViaLocation(Integer.parseInt(moduleId),Integer.parseInt(submoduleId),
                        Integer.parseInt(beatid),Integer.parseInt(psId));

                if (!markers.isEmpty()) {



                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, markers);
                    map.put(Constants.keyMessage, "Data Found Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);



                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "No Record Found");
                    map.put(Constants.keyMessage, "Request Successful. No Record Found");
                    map.put(Constants.keyStatus, HttpStatus.NO_CONTENT.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    return ED.encrypt(jsonStr);
                }

            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "Data not in valid format");
                map.put(Constants.keyMessage, "Internal Error");
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
                Obj = new ObjectMapper();
                jsonStr = Obj.writeValueAsString(map);
                logger.info(jsonStr);
                logger.info(ED.encrypt(jsonStr));
                return ED.encrypt(jsonStr);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Data not in valid format");
            map.put(Constants.keyMessage, "Internal Error");
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR.value());
            Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(map);
            logger.info(jsonStr);
            logger.info(ED.encrypt(jsonStr));
            return ED.encrypt(jsonStr);
        }
    }



    @RequestMapping(value = "/api/getActiveBeats", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getActiveBeats(@RequestBody String date) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String date_to_search = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<SubModuleRoleList> submodules = null;

        if (date != null && !date.isEmpty()) {
            logger.info("Date ID:-\t" + date);
            date_to_search = ED.decrypt(date);

            try {
                List<Object[]> data = userLocationLogsRepository.getActiveBeats(date_to_search);

                if (data!=null) {

                    List<ActiveBeatModal> activeBeatModal = new ArrayList<>();


                    for (Object[] result : data) {
                        ActiveBeatModal pojo = new ActiveBeatModal();
                        pojo.setBeatId((Integer) result[0]);
                        pojo.setUsername((String) result[1]);
                        pojo.setUserId((Integer) result[2]);
                        pojo.setRoleId((Integer) result[3]);
                        pojo.setMobile((BigInteger) result[4]);
                        pojo.setBeatName((String) result[5]);
                        pojo.setPoliceStationName((String) result[6]);
                        pojo.setDate((String) result[7]);
                        activeBeatModal.add(pojo);
                    }


                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, activeBeatModal);
                    map.put(Constants.keyMessage, "Request Successful. Data Found Successfully.");
                    map.put(Constants.keyStatus, HttpStatus.OK.value());
                    Obj = new ObjectMapper();
                    jsonStr = Obj.writeValueAsString(map);
                    logger.info(jsonStr);
                    logger.info(ED.encrypt(jsonStr));
                    return ED.encrypt(jsonStr);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "");
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
            map.put(Constants.keyResponse, "Sub Module ID Passed not in valid format");
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

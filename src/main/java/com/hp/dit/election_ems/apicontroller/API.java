package com.hp.dit.election_ems.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.dit.election_ems.entities.*;
import com.hp.dit.election_ems.modals.RolesModal;
import com.hp.dit.election_ems.modals.RolesUser;
import com.hp.dit.election_ems.modals.UsePoJo;
import com.hp.dit.election_ems.modals.UserPojoWithRole;
import com.hp.dit.election_ems.modals.activeBeatModal.ActiveBeatModal;
import com.hp.dit.election_ems.modals.beats.BeatsNameId;
import com.hp.dit.election_ems.modals.information.InformationMarkers;
import com.hp.dit.election_ems.modals.information.InformationViaId;
import com.hp.dit.election_ems.modals.moduleModel.ModulesModal;
import com.hp.dit.election_ems.modals.submoduleModal.SubModuleRoleList;
import com.hp.dit.election_ems.modals.usersviabeat.UsersViaBeat;
import com.hp.dit.election_ems.repositories.RolesRepository;
import com.hp.dit.election_ems.repositories.beats.BeatRepository;
import com.hp.dit.election_ems.repositories.comments.CommentsRepository;
import com.hp.dit.election_ems.repositories.districtRepository.DistrictRepository;
import com.hp.dit.election_ems.repositories.information.InformationRepository;
import com.hp.dit.election_ems.repositories.modules.ModuleRepository;
import com.hp.dit.election_ems.repositories.pin.PinRepository;
import com.hp.dit.election_ems.repositories.policestationRepository.PSRepository;
import com.hp.dit.election_ems.repositories.sosdpo.SoSdpoRepository;
import com.hp.dit.election_ems.repositories.stateRepository.StateRepository;
import com.hp.dit.election_ems.repositories.submoduleoptions.SubModuleOptionRepository;
import com.hp.dit.election_ems.repositories.submodules.SubModuleRepository;
import com.hp.dit.election_ems.repositories.user.UserRepository;
import com.hp.dit.election_ems.repositories.userdatatable.UserDatatableRepository;
import com.hp.dit.election_ems.repositories.userlocationlogs.UserLocationLogsRepository;
import com.hp.dit.election_ems.security.EncryptDecrypt;
import com.hp.dit.election_ems.services.FileStorageService;
import com.hp.dit.election_ems.utilities.Constants;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    PinRepository pinRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    UserLocationLogsRepository userLocationLogsRepository;

    @Autowired
    SubModuleRepository subModuleRepository;

    @Autowired
    SubModuleOptionRepository subModuleOptionRepository;

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    UserDatatableRepository userDatatableRepository;

    @Autowired
    CommentsRepository commentsRepository;




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



    @RequestMapping(value = "/api/checkPin", method = RequestMethod.POST, produces = Constants.ProducesPlainText, consumes = Constants.ProducesPlainText)
    public String checkPin(@RequestBody String data) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String district_id = null, pin = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;

        if (data != null && !data.isEmpty()) {
            logger.info("data ID:-\t" + data);
            jsonStr = ED.decrypt(data);

        try {
            JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
            System.out.println(jsonObject.toString());
            logger.info("API:: User Data is (Json Object ):- " + jsonObject);
            district_id = jsonObject.getAsJsonObject().get("districtId").getAsString();
            pin = jsonObject.getAsJsonObject().get("pin").getAsString();

            logger.info("districtId:- " + district_id);
            logger.info("pin:- " + pin);


            PinMaster pinDetails = pinRepository.findActivePins(district_id,pin);
            if (pinDetails != null) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, pinDetails);
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
    }else {
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

    @RequestMapping(value = "/api/getModules", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getModules(@RequestBody String roleId) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String role_id = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<ModulesModal> modulesViaRole = null;

        if (roleId != null && !roleId.isEmpty()) {
            logger.info("Role ID:-\t" + roleId);
            role_id = ED.decrypt(roleId);

            try {
                List<Object[]> modules = moduleRepository.getModulesViaRoleId(Integer.parseInt(role_id));

                if (!modules.isEmpty()) {

                    modulesViaRole = new ArrayList<>();
                    for (Object[] result : modules) {
                        ModulesModal pojo = new ModulesModal();
                        pojo.setModuleId((Integer) result[0]);
                        pojo.setModuleName((String) result[1]);
                        pojo.setModuleIcon((String) result[2]);
                        pojo.setActive((Boolean) result[3]);
                        modulesViaRole.add(pojo);
                    }

                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, modulesViaRole);
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
     * GEt Sub Modules
     */
    @RequestMapping(value = "/api/getSubModules", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getSubModules(@RequestBody String roleId) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String role_id = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<SubModuleRoleList> submodules = null;

        if (roleId != null && !roleId.isEmpty()) {
            logger.info("Role ID:-\t" + roleId);
            role_id = ED.decrypt(roleId);

            try {
                List<SubModuleRoleList> modules = subModuleRepository.findSubModulesByModueId(Integer.parseInt(role_id));

                if (!modules.isEmpty()) {


                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, modules);
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
     * GEt Sub Modules
     */
    @RequestMapping(value = "/api/getSubModulesOptions", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getSubModulesOptions(@RequestBody String roleId) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String role_id = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<SubModuleRoleList> submodules = null;

        if (roleId != null && !roleId.isEmpty()) {
            logger.info("Role ID:-\t" + roleId);
            role_id = ED.decrypt(roleId);

            try {
                List<OptionsMaster> options = subModuleOptionRepository.getOptionsViaSubModuleId(Integer.parseInt(role_id));

                if (!options.isEmpty()) {


                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, options);
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

    /**
     * saveInformation
     */
    @Transactional
    @RequestMapping(value = "/api/saveInformation", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/plain")
    public String saveInformation(@RequestParam(required = true, value = "jsondata") String information,
                                  @RequestParam(required = true, value = "files") MultipartFile[] files) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String info = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        List<SubModuleRoleList> submodules = null;

        InformationEntity informationToSave = new InformationEntity();
        InformationEntity informationSaved = null;

        if (information != null && !information.isEmpty() && files != null ) {
            logger.info("Information ID:-\t" + information);
            info = ED.decrypt(information);
            logger.info("Information Decrypt ID:-\t" + info);

            try {
                informationToSave = objectMapper.readValue(info, InformationEntity.class);
                logger.info("Data To be Saved" + informationToSave);

                //Save Files
                List<MultipartFile> fileNames = Arrays.asList(files);
                logger.info("file1 Size:-" + fileNames.size());

                for (int i = 0; i < fileNames.size(); i++) {

                    logger.info("file Name:-" + i + "\t" + fileNames.get(i).getName());
                    logger.info("file Size:-" + i + "\t" + fileNames.get(i).getSize());
                    logger.info("file Content Type:-" + i + "\t" + fileNames.get(i).getContentType());
                    logger.info("\t \n");
                    String fileName = fileStorageService.storeFile(fileNames.get(i));
                }
                String fileDownloadUri_fileOne = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(informationToSave.getPhoto())
                        .toUriString();

                logger.info("File One URL :- \t" + fileDownloadUri_fileOne);



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


    /**
     * Get Users
     */
    @RequestMapping(value = "/api/getUsers", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String getUsers(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String LogsData_ = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String encrypted = null,  beatid=null;
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
                logger.info("beatid:- " + beatid);

                List<UsersViaBeat> users = userDatatableRepository.getActiveUsersViaBeat(Integer.parseInt(beatid));

                if (!users.isEmpty()) {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, users);
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

/**
 * Get Markers Via User Beat and Date
 */
@RequestMapping(value = "/api/getCheckingRemarksInformation", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
@ResponseBody
public String getCheckingRemarksInformation(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
    Map<String, Object> map = null;
    String LogsData_ = null, jsonStr = null;
    EncryptDecrypt ED = new EncryptDecrypt();
    ObjectMapper Obj = null;
    String encrypted = null,  beatid=null, userid= null, fromdate = null, todate= null ;
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
            logger.info("beatid:- " + beatid);
            userid = jsonObject.getAsJsonObject().get("user_id").getAsString();
            logger.info("userID:- " + userid);
            fromdate = jsonObject.getAsJsonObject().get("from_date").getAsString();
            logger.info("fromDate:- " + fromdate);
            List<InformationMarkers> markers = informationRepository.getUploadedInformationByOfficialDateWise(Integer.parseInt(beatid),Integer.parseInt(userid),fromdate);

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

/**
 * Save Comments
 */
@RequestMapping(value = "/api/saveComments", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
@ResponseBody
public String saveComments(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
    Map<String, Object> map = null;
    String LogsData_ = null, jsonStr = null;
    EncryptDecrypt ED = new EncryptDecrypt();
    ObjectMapper Obj = null;
    String encrypted = null,  userId = null, informationId, comments;
    CommentsMaster commentsEntity = null, savedCommentsEntity = null;

    if (userData != null && !userData.isEmpty()) {
        logger.info("User Data Encrypted:-\t" + userData);
        LogsData_ = ED.decrypt(userData);
        logger.info("User Data DeEncrypted:-\t" + LogsData_);

        try {
            JsonObject jsonObject = new JsonParser().parse(LogsData_).getAsJsonObject();
            System.out.println(jsonObject.toString());
            logger.info("API:: Logs Data is (Json Object ):- " + jsonObject);



            userId = jsonObject.getAsJsonObject().get("user_id").getAsString();
            informationId = jsonObject.getAsJsonObject().get("information_id").getAsString();
            comments = jsonObject.getAsJsonObject().get("comment").getAsString();

            logger.info("InformationId:- " + informationId);
            logger.info("userId:- " + userId);
            logger.info("Comments:- " + comments);


            commentsEntity = new CommentsMaster();
            commentsEntity.setComments(comments);

            UserEntity user = new UserEntity();
            user.setUserId(Long.valueOf(userId));
            commentsEntity.setUserId(user);

          //  InformationEntity information = new InformationEntity();
           // information.setId(Integer.parseInt(informationId));
            commentsEntity.setInformationId(Integer.parseInt(informationId));

            commentsEntity.setActive(true);
            commentsEntity.setDeleted(false);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            commentsEntity.setCreatedDate(date);

            logger.info("User \t" + commentsEntity.toString());

            savedCommentsEntity = commentsRepository.save(commentsEntity);
            if (savedCommentsEntity!=null) {



                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, savedCommentsEntity.getCommnetsId());
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


}

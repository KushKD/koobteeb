package com.hp.dit.beetbook.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.modals.RolesUser;
import com.hp.dit.beetbook.modals.UsePoJo;
import com.hp.dit.beetbook.modals.UserPojoWithRole;
import com.hp.dit.beetbook.repositories.RolesRepository;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepository;
import com.hp.dit.beetbook.repositories.stateRepository.StateRepository;
import com.hp.dit.beetbook.repositories.user.UserRepository;
import com.hp.dit.beetbook.security.EncryptDecrypt;
import com.hp.dit.beetbook.services.FileStorageService;
import com.hp.dit.beetbook.utilities.Constants;
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
    private RolesRepository rolesRepository;



    @PersistenceContext
    private EntityManager entityManager;

    ObjectMapper objectMapperx = new ObjectMapper();
    ObjectMapper objectMappery = new ObjectMapper();
    ObjectMapper objectMapperz = new ObjectMapper();


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
     * Login Function
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain")
    @ResponseBody
    public String login(@RequestBody String userData) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> map = null;
        String userData_ = null, jsonStr = null;
        EncryptDecrypt ED = new EncryptDecrypt();
        ObjectMapper Obj = null;
        String encrypted = null, username = null, password = null, stateId = null, districtId = null, barrierId = null, mobileNumber = null, roleId;

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
                barrierId = jsonObject.getAsJsonObject().get("barrierId").getAsString();
                mobileNumber = jsonObject.getAsJsonObject().get("mobile").getAsString();

                logger.info("Username:- " + username);
                logger.info("password:- " + password);
                logger.info("stateId:- " + stateId);
                logger.info("districtId:- " + districtId);
                logger.info("barrierId:- " + barrierId);
                logger.info("mobileNumber:- " + mobileNumber);


                UsePoJo user = userRepository.apiLogin(
                        Long.parseLong(mobileNumber),
                        Integer.parseInt(stateId),
                        Integer.parseInt(districtId),
                        Integer.parseInt(barrierId),
                        username);
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

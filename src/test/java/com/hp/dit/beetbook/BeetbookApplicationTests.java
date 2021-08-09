package com.hp.dit.beetbook;

import com.google.gson.JsonObject;
import com.hp.dit.beetbook.ajax.AjaxContoller;
import com.hp.dit.beetbook.apicontroller.API;
import com.hp.dit.beetbook.entities.RolesEntity;
import com.hp.dit.beetbook.entities.UserEntity;
import com.hp.dit.beetbook.repositories.RolesRepository;
import com.hp.dit.beetbook.repositories.stateRepository.StateRepository;
import com.hp.dit.beetbook.repositories.user.UserRepository;
import com.hp.dit.beetbook.security.EncryptDecrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BeetbookApplicationTests {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    API apiController;

//
//	@Autowired
//	RolesRepository rolesRepository;
//    @Autowired
//	UserRepository userRepository;


//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void createUser(){
//
//        UserEntity user1 = new UserEntity();
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        user1.setUserName("kush");
//        user1.setRank("Administrator");
//        user1.setFirstName("Kush");
//        user1.setLastName("Dhawan");
//        user1.setPassword(encoder.encode("Demo@123"));
//        user1.setMobileNumber(9459619235L);
//        user1.setPsId(1);
//        user1.setSosdpoId(1);
//        user1.setActive(true);
//
//       // user1.setBarrierId(3);
//        user1.setDistrictId(204);
//        user1.setStateId(9);
//        user1.setBeatId(1);
//        Optional<RolesEntity> role = rolesRepository.findById(1);
//		List<RolesEntity> list = new ArrayList<>();
//		list.add(role.get());
//        user1.setRoles(list);
//
//        userRepository.save(user1);
//    }
//
////
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void createRoles() {
//
//        RolesEntity roles = new RolesEntity();
//        roles.setRoleName("SUPER ADMIN");
//        roles.setRoleDescription("Super Admin of the Website");
//        roles.setActive(true);
//        rolesRepository.save(roles);
//
//    }
////
//    @Test
//    void bCryptPassword(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        encoder.encode("fagu@123");
//        System.out.printf("\n");
//        System.out.printf(encoder.encode("fagu@123"));
//        System.out.printf("\n");
//    }
//

//  @Test
//  void checkRandom(){
//
//      System.out.println("!@!@!@!@!"+random24.randomDecimalString(6));
//  }


//    @Test
//    void testDate() throws ParseException {
//        DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = originalFormat.parse("14-07-2020");
//        String formattedDate = targetFormat.format(date);
//        System.out.println(formattedDate);
//    }

//    @Test
//    private void date(){
//        System.out.println("-----Current time of a different time zone using LocalTime-----");
//        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
//        LocalTime localTime=LocalTime.now(zoneId);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String formattedTime=localTime.format(formatter);
//        System.out.println("Current time of the day in Los Angeles: " + formattedTime);
//    }

//    @Test
//    public  void convertStringSqlDate() {
//
//
//
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date parsed = null;
//
//        try {
//            parsed = format.parse("14-07-2020");
//        } catch (ParseException e) {
//
//        }
//
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//        System.out.println(sql);
//       // return sql;
//    }


	//  @Test
//    public  void checkCrypto() throws ParserConfigurationException, IOException, SAXException {
//
//            CryptographyAES AES = new CryptographyAES();
//
//           String decrypt = AES.decryptFile(Constants.stringSample,Constants.securityKeyAES);
//            System.out.println( " Decryption " +decrypt);
//
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse( new InputSource( new StringReader( decrypt ) ) );
//
//            doc.getDocumentElement().normalize();
//
//            NodeList nodeList = doc.getElementsByTagName("VehicleDetails");
//            List<VehicleDetailsObject> vehicleDetails = new ArrayList<>();
//
//            //loop all available student nodes
//            for (int i = 0; i < nodeList.getLength(); i++) {
//
//                Node node = nodeList.item(i);
//
//                if(node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element elem = (Element) node;
//                    VehicleDetailsObject data = new VehicleDetailsObject();
//                    data.setRcChassisNo(elem.getElementsByTagName("rc_chasi_no").item(0).getTextContent());
//                    data.setRcEngineNumber(elem.getElementsByTagName("rc_eng_no").item(0).getTextContent());
//                    data.setRcFitUpto(elem.getElementsByTagName("rc_fit_upto").item(0).getTextContent());
//                    data.setRcRegisteredAt(elem.getElementsByTagName("rc_registered_at").item(0).getTextContent());
//                    data.setRcStatus(elem.getElementsByTagName("rc_status").item(0).getTextContent());
//                    data.setRcRegistrationNo(elem.getElementsByTagName("rc_regn_no").item(0).getTextContent());
//                    data.setRcStatusAsOn(elem.getElementsByTagName("rc_status_as_on").item(0).getTextContent());
//                    System.out.println(data.toString());
//
//                    vehicleDetails.add(data);
//                }
//            }
//
//
//
//
//    }

        @Test
    @Transactional
    @Rollback(value = false)
    void getStates() throws Exception {
            EncryptDecrypt ED  = new EncryptDecrypt();
        String states = apiController.getStates();
            System.out.println("===States===");
            System.out.println(states);
            System.out.println(ED.decrypt(states));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void getDistricts() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        System.out.println(ED.encrypt("9"));
        String districts = apiController.getDistricts(ED.encrypt("9"));
        System.out.println("===Districts===");
        System.out.println(districts);
        System.out.println(ED.decrypt(districts));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getSoSdPo() throws Exception  {

        EncryptDecrypt ED = new EncryptDecrypt();
       // System.out.println(ED.encrypt("9"));
        String soSdpo = apiController.getsosdpo();
        System.out.println("===soSdpo===");
        System.out.println(soSdpo);
        System.out.println(ED.decrypt(soSdpo));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getPoliceStations() throws Exception  {

        EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("stateId", "9");
        jsonObjecttwo.addProperty("districtId", "204");
        jsonObjecttwo.addProperty("sdpoId", "1");
         System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.getPoliceStations(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Police Station===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getBeats() throws Exception  {

        EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("stateId", "9");
        jsonObjecttwo.addProperty("districtId", "204");
        jsonObjecttwo.addProperty("sdpoId", "1");
        jsonObjecttwo.addProperty("psId", "1");
        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.getBeats(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Beats===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void loginBeat() throws Exception  {

        EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("stateId", "9");
        jsonObjecttwo.addProperty("districtId", "204");
        jsonObjecttwo.addProperty("sdpoId", "1");
        jsonObjecttwo.addProperty("psId", "1");
        jsonObjecttwo.addProperty("beatId", "1");
        jsonObjecttwo.addProperty("username", "beat");
        jsonObjecttwo.addProperty("password", "Demo@123");
        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.loginBeat(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Loggged In User===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void loginSoSP() throws Exception  {

        EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("stateId", "9");
        jsonObjecttwo.addProperty("districtId", "204");
        jsonObjecttwo.addProperty("sdpoId", "1");
        jsonObjecttwo.addProperty("psId", "1");
        jsonObjecttwo.addProperty("username", "shouser");
        jsonObjecttwo.addProperty("password", "Demo@123");
        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.loginSoSP(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Loggged In SHO User===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void checkPin() throws Exception  {
            EncryptDecrypt ED = new EncryptDecrypt();
        String districts = apiController.checkPin();
        System.out.println("===Districts===");
        System.out.println(districts);
        System.out.println(ED.decrypt(districts));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getModules() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getModules(ED.encrypt("4"));
        System.out.println("===Modules===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getRoles() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getRoles();
        System.out.println("===Roles===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void locationLogs() throws Exception  {


        EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("user_id", "3");
        jsonObjecttwo.addProperty("role_id", "4");
        jsonObjecttwo.addProperty("latitude", "0.0");
        jsonObjecttwo.addProperty("longitude", "0.0");
        jsonObjecttwo.addProperty("username", "beat");
        jsonObjecttwo.addProperty("mobile", "9459619235");
        jsonObjecttwo.addProperty("beat_id", "1");
        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.saveLocationLogs(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Loggged In SHO User===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }

    //getSubModules
    @Test
    @Transactional
    @Rollback(value = false)
    void getSubModules() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getSubModules(ED.encrypt("2"));
        System.out.println("===Sub Modules===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void getOptions() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getSubModulesOptions(ED.encrypt("2"));
        System.out.println("===Sub Modules Options===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }

    String json = "{\n" +
            "  \"stateId\": 9,\n" +
            "  \"districtId\": 204,\n" +
            "  \"sosdpoId\": 1,\n" +
            "  \"psId\": 1,\n" +
            "  \"beatId\": 1,\n" +
            "  \"moduleId\": 2,\n" +
            "  \"submoduleId\": {\n" +
            "    \"submoduleId\": 2\n" +
            "  },\n" +
            "  \"userId\": 3,\n" +
            "  \"roleId\": 4,\n" +
            "  \"name\": \"test\",\n" +
            "  \"ownerName\": \"hhxhxhf\",\n" +
            "  \"ownerNameTwo\": \"\",\n" +
            "  \"photo\": \"\",\n" +
            "  \"photoId\": \"\",\n" +
            "  \"contactNoOne\": \"9459619235\",\n" +
            "  \"contactNoTwo\": \"\",\n" +
            "  \"helplineNumber\": \"\",\n" +
            "  \"landlineNumber\": \"\",\n" +
            "  \"optionId\": 1,\n" +
            "  \"numberIdols\": \"\",\n" +
            "  \"numberSecurityPersons\": \"\",\n" +
            "  \"cctv\": \"\",\n" +
            "  \"emailId\": \"\",\n" +
            "  \"facbookId\": \"\",\n" +
            "  \"presentAddress\": \"\",\n" +
            "  \"permanentAddress\": \"\",\n" +
            "  \"firNo\": \"\",\n" +
            "  \"firDetails\": \"\",\n" +
            "  \"licenceeNo\": \"\",\n" +
            "  \"licenceeName\": \"\",\n" +
            "  \"details\": \"\",\n" +
            "  \"other\": \"\",\n" +
            "  \"checkingDateSho\": \"\",\n" +
            "  \"totalPopulation\": \"\",\n" +
            "  \"periodFair\": \"\",\n" +
            "  \"authority\": \"\",\n" +
            "  \"durationParole\": \"\",\n" +
            "  \"idProof\": \"\",\n" +
            "  \"section\": \"\",\n" +
            "  \"specialReportedCases\": \"\",\n" +
            "  \"extraOne\": \"\",\n" +
            "  \"extraTwo\": \"\",\n" +
            "  \"latitude\": 31.0704335,\n" +
            "  \"longitude\": 77.1868896\n" +
            "}";

    @Test
    @Transactional
    @Rollback(value = false)
    void saveInformation() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.saveInformation(ED.encrypt(json));
        System.out.println("===Information===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void saveInformationViaId() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getInformationViaId(ED.encrypt("1"));
        System.out.println("===Information===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void getmarkersViaLocation() throws Exception  {


        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("beat_id", "1");
        jsonObjecttwo.addProperty("module_id", "2");
        jsonObjecttwo.addProperty("submodule_id", "2");
        jsonObjecttwo.addProperty("ps_id", "1");

        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getMarkers(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Information===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }



    //getActiveBeats
    @Test
    @Transactional
    @Rollback(value = false)
    void getActiveBeats() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        System.out.println(ED.encrypt("09-08-2021"));
        String data = apiController.getActiveBeats(ED.encrypt("08-08-2021"));
        System.out.println("===Data Active Beats===");
        System.out.println(data);
        System.out.println(ED.decrypt(data));

    }



}

package com.hp.dit.election_ems;

import com.google.gson.JsonObject;
import com.hp.dit.election_ems.apicontroller.API;
import com.hp.dit.election_ems.entities.CommentsMaster;
import com.hp.dit.election_ems.repositories.RolesRepository;
import com.hp.dit.election_ems.repositories.comments.CommentsRepository;
import com.hp.dit.election_ems.repositories.stateRepository.StateRepository;
import com.hp.dit.election_ems.repositories.user.UserRepository;
import com.hp.dit.election_ems.security.EncryptDecrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class BeetbookApplicationTests {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    API apiController;


	@Autowired
	RolesRepository rolesRepository;
    @Autowired
	UserRepository userRepository;

    @Autowired
    CommentsRepository commentsRepository;

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
//
//  @Test
//  void checkRandom(){
//
//      System.out.println("!@!@!@!@!"+random24.randomDecimalString(6));
//  }
//

//    @Test
//    void testDate() throws ParseException {
//        DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = originalFormat.parse("14-07-2020");
//        String formattedDate = targetFormat.format(date);
//        System.out.println(formattedDate);
//    }
//
//    @Test
//    private void date(){
//        System.out.println("-----Current time of a different time zone using LocalTime-----");
//        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
//        LocalTime localTime= LocalTime.now(zoneId);
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


//	  @Test
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
        jsonObjecttwo.addProperty("sdpoId", "4");
        jsonObjecttwo.addProperty("psId", "7");
        jsonObjecttwo.addProperty("beatId", "69");
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
        jsonObjecttwo.addProperty("sdpoId", "4");
        jsonObjecttwo.addProperty("psId", "7");
        jsonObjecttwo.addProperty("username", "shouser");
        jsonObjecttwo.addProperty("password", "Demo@123");
        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.loginSoSP(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Loggged In SHO User===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }

    /**
     * TODO Change Pin Function
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(value = false)
    void checkPin() throws Exception  {
            EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("districtId", "206");
        jsonObjecttwo.addProperty("pin", "123457");
        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String districts = apiController.checkPin(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Districts===");
        System.out.println(districts);
        System.out.println(ED.decrypt(districts));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getModules() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        String modules = apiController.getModules(ED.encrypt("1"));
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

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void locationLogs() throws Exception  {
//
//        //31.1142457,77.1775003
//
//        EncryptDecrypt ED = new EncryptDecrypt();
//        JsonObject jsonObjecttwo = new JsonObject();
//        jsonObjecttwo.addProperty("user_id", "1");
//        jsonObjecttwo.addProperty("role_id", "1");
//        jsonObjecttwo.addProperty("latitude", "31.1142457");
//        jsonObjecttwo.addProperty("longitude", "77.1775003");
//        jsonObjecttwo.addProperty("username", "beat");
//        jsonObjecttwo.addProperty("mobile", "9459619235");
//        jsonObjecttwo.addProperty("beat_id", "1");
//        System.out.println(ED.encrypt(jsonObjecttwo.toString()));
//        String ps = apiController.saveLocationLogs(ED.encrypt(jsonObjecttwo.toString()));
//        System.out.println("===Loggged In SHO User===");
//        System.out.println(ps);
//        System.out.println(ED.decrypt(ps));

  //  }

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





String json = "{\"stateId\": 9,\n    \"districtId\": 204,\n    \"sosdpoId\": 1,\n    \"psId\": {\n      \"psId\": 1\n    },\n    \"beatId\": {\n      \"beatId\": 1\n    },\n    \"moduleId\":  2,\n    \"submoduleId\": {\n      \"submoduleId\": 2\n    },\n    \"userId\": 3,\n    \"roleId\": 4,\n    \"name\": \"gchch\",\n    \"ownerName\": \"gcgcgxvh\",\n    \"ownerNameTwo\": \"\",\n    \"photo\": \"\",\n    \"photoId\": \"\",\n    \"contactNoOne\": \"8686858585\",\n    \"contactNoTwo\": \"\",\n    \"helplineNumber\": \"\",\n    \"landlineNumber\": \"\",\n    \"optionId\": 1,\n    \"numberIdols\": \"\",\n    \"numberSecurityPersons\": \"\",\n    \"cctv\": \"\",\n    \"emailId\": \"\",\n    \"facbookId\": \"\",\n    \"presentAddress\": \"\",\n    \"permanentAddress\": \"\",\n    \"firNo\": \"\",\n    \"firDetails\": \"\",\n    \"licenceeNo\": \"\",\n    \"licenceeName\": \"\",\n    \"details\": \"\",\n    \"other\": \"\",\n    \"checkingDateSho\": \"\",\n    \"totalPopulation\": \"\",\n    \"periodFair\": \"\",\n    \"authority\": \"\",\n    \"durationParole\": \"\",\n    \"idProof\": \"\",\n    \"section\": \"\",\n    \"specialReportedCases\": \"\",\n    \"extraOne\": \"\",\n    \"extraTwo\": \"\",\n    \"latitude\": 31.1104692,\n    \"longitude\": 77.1777489\n  }";
    @Test
    @Transactional
    @Rollback(value = false)
    void saveInformation() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        System.out.println(ED.encrypt(json));
        System.out.println(ED.encrypt(json));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void getInformationViaId() throws Exception  {
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
        jsonObjecttwo.addProperty("beat_id", "69");
        jsonObjecttwo.addProperty("module_id", "2");
        jsonObjecttwo.addProperty("submodule_id", "2");
        jsonObjecttwo.addProperty("ps_id", "7");

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

    @Test
    @Transactional
    @Rollback(value = false)
    void getUsers() throws Exception  {
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("beat_id", "206");

        EncryptDecrypt ED = new EncryptDecrypt();

        String modules = apiController.getUsers(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Users From Beat===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }



    @Test
    @Transactional
    @Rollback(value = false)
    //  /api/getCheckingRemarksInformation
    // {"MSG":"Data Found Successfully.","STATUS":200,"RESPONSE":[{"id":315,"latitude":31.0057992,"longitude":76.78041,"name":"Royal Public Sr Sec School Lodhimajra","photo":"IMG_2022_04_13_11_57_31.jpeg","subModuleName":"Educational Institutes","moduleId":2,"subMoculeIcon":"1628054571582__education.png","createdDate":1649830986655,"subModuleId":34},{"id":313,"latitude":31.0058069,"longitude":76.7804144,"name":"Govt Primary school Lodhimajra","photo":"IMG_2022_04_13_11_42_49.jpeg","subModuleName":"Educational Institutes","moduleId":2,"subMoculeIcon":"1628054571582__education.png","createdDate":1649830189313,"subModuleId":34},{"id":312,"latitude":31.0047195,"longitude":76.776752,"name":"Govt senior secondary  school  Lodhimajra","photo":"IMG_2022_04_13_11_23_39.jpeg","subModuleName":"Educational Institutes","moduleId":2,"subMoculeIcon":"1628054571582__education.png","createdDate":1649829254413,"subModuleId":34},{"id":245,"latitude":31.0010343,"longitude":76.7351249,"name":"sbi atm","photo":"IMG_2022_02_18_10_43_15.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1645161128434,"subModuleId":24},{"id":244,"latitude":31.001058,"longitude":76.7349737,"name":"icici","photo":"IMG_2022_02_18_10_39_58.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1645160933012,"subModuleId":24},{"id":241,"latitude":31.0179099,"longitude":76.7165668,"name":"sbi atm ","photo":"IMG_2022_02_17_11_33_37.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1645077755270,"subModuleId":24},{"id":240,"latitude":31.0179099,"longitude":76.7165668,"name":"shurti sharma","photo":"IMG_2022_02_17_11_28_03.jpeg","subModuleName":"Liquor Shops","moduleId":2,"subMoculeIcon":"1627469065444__liquor_shop.png","createdDate":1645077419141,"subModuleId":7},{"id":237,"latitude":31.0165915,"longitude":76.7183201,"name":"vivon healthcare ","photo":"IMG_2022_02_17_10_56_29.jpeg","subModuleName":"Medical/Pesticide Shops","moduleId":2,"subMoculeIcon":"1627469091341__medical_shops.png","createdDate":1645075532626,"subModuleId":8},{"id":229,"latitude":31.0163524,"longitude":76.7194024,"name":"kherha pull","photo":"IMG_2022_02_16_11_44_38.jpeg","subModuleName":"Accidents/Desasters","moduleId":2,"subMoculeIcon":"1628157392374__accident.png","createdDate":1644992016299,"subModuleId":37},{"id":228,"latitude":31.0163524,"longitude":76.7194024,"name":"jai baba sewa society ","photo":"IMG_2022_02_16_11_42_24.jpeg","subModuleName":"Trucks & Taxi Operators Unions","moduleId":2,"subMoculeIcon":"1627986160125__taxi-stop.png","createdDate":1644991882891,"subModuleId":19},{"id":227,"latitude":30.9886261,"longitude":76.7504665,"name":"axis bank atm","photo":"IMG_2022_02_16_02_14_58.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1644957847154,"subModuleId":24},{"id":226,"latitude":30.9883974,"longitude":76.7524515,"name":"icici bank atm","photo":"IMG_2022_02_16_02_13_34.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1644957787967,"subModuleId":24},{"id":225,"latitude":30.9883974,"longitude":76.7524515,"name":"Hitachi atm","photo":"IMG_2022_02_16_02_11_45.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1644957659709,"subModuleId":24},{"id":219,"latitude":30.9963125,"longitude":76.7287968,"name":"kasambowal bagwaniya","photo":"IMG_2022_02_15_12_43_31.jpeg","subModuleName":"Wildlife Sanctuary","moduleId":2,"subMoculeIcon":"1628157430028__park.png","createdDate":1644909227583,"subModuleId":38},{"id":218,"latitude":30.9967303,"longitude":76.7269784,"name":"handakundi bagwaniya","photo":"IMG_2022_02_15_12_11_59.jpeg","subModuleName":"Mining Area","moduleId":2,"subMoculeIcon":"1628157568612__mining.png","createdDate":1644907263376,"subModuleId":45},{"id":217,"latitude":30.997515,"longitude":76.7383008,"name":"highway filing station","photo":"IMG_2022_02_15_12_03_28.jpeg","subModuleName":"Petrol Pump & Gas Agencies","moduleId":2,"subMoculeIcon":"1628053615535__petrol-pump.png","createdDate":1644906750040,"subModuleId":21},{"id":216,"latitude":30.9932901,"longitude":76.7446539,"name":"chandel pastiside","photo":"IMG_2022_02_15_11_56_39.jpeg","subModuleName":"Medical/Pesticide Shops","moduleId":2,"subMoculeIcon":"1627469091341__medical_shops.png","createdDate":1644906353762,"subModuleId":8},{"id":206,"latitude":30.9931256,"longitude":76.7464574,"name":"shurti","photo":"IMG_2022_02_12_11_48_59.jpeg","subModuleName":"Liquor Shops","moduleId":2,"subMoculeIcon":"1627469065444__liquor_shop.png","createdDate":1644646689498,"subModuleId":7},{"id":205,"latitude":30.9899513,"longitude":76.7565564,"name":"shere himachal dost society manpura","photo":"IMG_2022_02_12_10_28_48.jpeg","subModuleName":"Trucks & Taxi Operators Unions","moduleId":2,"subMoculeIcon":"1627986160125__taxi-stop.png","createdDate":1644641706068,"subModuleId":19},{"id":176,"latitude":30.994196,"longitude":76.7432111,"name":"Hitachi atm","photo":"IMG_2022_02_11_12_22_11.jpeg","subModuleName":"Banks, ATMs & Post Office","moduleId":2,"subMoculeIcon":"1628053873472__atm-machine.png","createdDate":1644562281063,"subModuleId":24},{"id":174,"latitude":30.9942335,"longitude":76.7435718,"name":"bhuani medicose","photo":"IMG_2022_02_11_11_37_13.jpeg","subModuleName":"Medical/Pesticide Shops","moduleId":2,"subMoculeIcon":"1627469091341__medical_shops.png","createdDate":1644559595248,"subModuleId":8},{"id":173,"latitude":30.9942335,"longitude":76.7435718,"name":"satnam bar kharuni","photo":"IMG_2022_02_11_11_18_47.jpeg","subModuleName":"Bar, Clubs & Resturants","moduleId":2,"subMoculeIcon":"1628157547827__club.png","createdDate":1644558483838,"subModuleId":44},{"id":171,"latitude":30.9866793,"longitude":76.7557524,"name":"P S MANPURA ","photo":"IMG_2022_02_11_10_32_39.jpeg","subModuleName":"CCTV Camera Locations","moduleId":2,"subMoculeIcon":"1628054467708__cctv.png","createdDate":1644555737706,"subModuleId":33},{"id":168,"latitude":30.9866777,"longitude":76.7557507,"name":"lee marit","photo":"IMG_2022_02_09_16_00_44.jpeg","subModuleName":"Hotels, Sarais, Guest House","moduleId":2,"subMoculeIcon":"1644226791521__hotel.png","createdDate":1644402601130,"subModuleId":2},{"id":154,"latitude":30.9867034,"longitude":76.7557297,"name":"demo ps manpura","photo":"IMG_2022_02_09_15_43_22.jpeg","subModuleName":"CCTV Camera Locations","moduleId":2,"subMoculeIcon":"1628054467708__cctv.png","createdDate":1644401573011,"subModuleId":33},{"id":149,"latitude":30.9306096,"longitude":76.8223186,"name":"abc","photo":"IMG_2022_02_09_13_49_36.jpeg","subModuleName":"Industries & Factories","moduleId":2,"subMoculeIcon":"1628157527904__factory.png","createdDate":1644394738677,"subModuleId":43}]}
    void getDataUsersDateWise() throws Exception  {
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("beat_id", "180");
        jsonObjecttwo.addProperty("user_id", "16");
        jsonObjecttwo.addProperty("from_date", "01-02-2022");

        EncryptDecrypt ED = new EncryptDecrypt();

        String modules = apiController.getCheckingRemarksInformation(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Users From Beat===");
        System.out.println(modules);
        System.out.println(ED.decrypt(modules));

    }


    @Test
    @Transactional
    @Rollback(value = false)
        //  /api/saveComments
           void addComments() throws Exception  {
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("comment", "Verified and Tested");
        jsonObjecttwo.addProperty("user_id", "1");
        jsonObjecttwo.addProperty("information_id", "619");

        EncryptDecrypt ED = new EncryptDecrypt();

        String savedData = apiController.saveComments(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Saved Data===");
        System.out.println(savedData);
        System.out.println(ED.decrypt(savedData));

    }

    @Test
    @Transactional
    @Rollback(value = false)
        //  /api/saveComments
    void getComments() throws Exception  {


        List<CommentsMaster> savedData = commentsRepository.getAllActiveComments(619);
        System.out.println("===Saved Data===");
        System.out.println(savedData.toString());

    }

}

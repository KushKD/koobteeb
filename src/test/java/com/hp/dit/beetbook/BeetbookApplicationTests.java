package com.hp.dit.beetbook;

import com.hp.dit.beetbook.entities.RolesEntity;
import com.hp.dit.beetbook.entities.UserEntity;
import com.hp.dit.beetbook.repositories.RolesRepository;
import com.hp.dit.beetbook.repositories.user.UserRepository;
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

	//postData



}

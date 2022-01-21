package com.hp.dit.beetbook.utilities;

public class Constants {

    public static final String keyResponse = "RESPONSE";
    public static final String keyMessage = "MSG";
    public static final String keyStatus = "STATUS";
    public static final String valueMessage = "Request Successful.";
    public static final String valueMessageEmpty = "Please enter valid Vehicle Registration Number";

    /**
     * Captcha Salt Characters
     */
    public static final String captchaSaltCharacters = "1234567890";

    /**
     * Cache String
     */
    public static final String cacheLogString = "=========================================================" + "Using cache manager: " + " =========================================================";



    /**
     * Controllers Mapping
     */
    public static final String loginController = "/login";
    public static final String indexController = "/index";
    public static final String headerNameXXSRF = "X-XSRF-TOKEN";
    public static final String setHeaderCokkie = "Set-Cookie";
    public static final String headerNameXSRF = "XSRF-TOKEN";
    public static final String JSESSIONID = "JSESSIONID";
    public static final String SameSite_Strict = "SameSite=Strict";
    public static final String formatting = "%s; %s";
    public static final String nocache_nostore_mustrevalidate = "no-cache, no-store, must-revalidate";
    public static final String no_cache = "no-cache";
    public static final String text_html = "text/html; charset=UTF-8";
    public static final String pragma = "pragma";
    public static final String Cache_control = "Cache-control";
    public static final String seperator = "/";
    public static final String nocrf = "/nocsrf";
    public static final String messageSource = "messageSource";
    public static final String utf8 = "UTF-8";
    public static final String lang_messages = "lang/messages";
    public static final String lang = "lang";

    public static final String secMessage = "Logout Successful.";
    public static final String secError = "Please Enter valid User Credentials and Captcha";
    public static final String errorsec = "error";
    public static final String messagesec = "message";

    /**
     * Permissions Access
     */
    public static final String permitAll = "/**";
    public static final String permitDownloadFile = "/downloadFile/**";
    public static final String permitApi = "/api/**";
    public static final String permitApiDatatable = "/apidataTable/**";
    public static final String denyPermitResourcesFonts = "/resources/css/fonts";

    /**
     * Roles Names
     */
    public static final String vendor = "Vendor";
    public static final String admin = "ADMIN";
    public static final String superAdmin = "Super Admin";



    /**
     * Credentials For the CDAC OTP
     */
    public static final String smsUsername = "hpgovt-hpparking";
    public static final String smsPassword = "Parking@123";
    public static final String smsSenderId = "hpgovt";
    public static final String smsSecureKey = "a2d6e4f0-f846-4594-9f68-7949b3da85d6";

    public static final String otp_Message = "Your OTP to login into the ID Card Generator Application is:  ";

    public static final String vahan = "https://vahan.nic.in/vahanws/ws/vchdtls/";
    public static final String getCarDetailsVahan = "getDetails";
    public static final String getChessisDetailsVahan = "getChasisDetails";
    public static final String getEngineDetailsVahan = "getEngineDetails";
    public static final String securityKeyAES = "hPeDisTRict@310720";
    public static final String clientId = "HPEDISTRICT";

    public static final String consumesTextPlain = "text/plain";
    public static final String ProducesPlainText = "text/plain";

    //public static final String encryptedData ="xdFWhxCR1yqcnS+pRB+A3ytYrPWv6HKOJNm530CY2JuCRTeCMfmgpVYT58k8EBDjJ+/tWk/98TofUgs9a6QSbw+vWhFN0IwH3ED37+pHCO77pTPhDwfPkgzRG7XcLZ2ogOaTGybaaXbEjT68EhQAg2Yn3uP6pqGIiMy8y7lEnw1apmHK/NBdPq5H7Zk0cUm+LyX/U7myJy57glC0ze3F/DO4PUVbrRpZDbTMBop118BGHd4H5CB6VKT/rT4f9OfVj5EWSszyW0+vPhAc/MoC5VxMql6LHOlnUg8h2lSJMxO4QrjoaQX0et6M0w8yMbW8Kg3RLxIaEovs6Qc6/PTCTdHmxs9EK/VWK1382XS4GpkXsnI19pGHsDwX9NhAjbibyH+cOHXt7n+DiH4J0AavjSxV2CSdzYKG/gEV4d4L3doVr1VCK8fEEqbP63z31mrZLzkfPKFzba3HL7b7W+osgrYLAt48AEWcTYa6EUY5L2SQLLKs+E1ZhrH8Z9Z9K+b0+nt+hLnsZxJpiAX7dqrs3h2c1Px3xGRFVD8apZzliC1eX6Zc3hpXB/15OQQUlwMlcnl5eMebZp213XVu/vD+XegM7iL6OA8VChPoAmWAjjcdFBCyHgMaMZbM9UBIHg4dXpsU+oAHIkdGSu4hTgImukh1bf+QUte4iM6w96QnFACp7/Uf1RCBgrrQUVSdXD16IjbDFGSc9eow6lySWz8vGDebkEkAOCjk/I1dGB9dxhaowI96UaYorcx0IugkA0HR+Syiz4Gs2iQQC8Btw+0Sb6sB81FNOoQ2uXHPC0DYzbgtA7nlGbfXHtoHpKMFFZP/rvRAP2GwLDjVLm7NFMre/cVaORBuHR8FKbD/RLWaPReHB/NTsWorEcaU++2wToEM5E9L/zlBqH38SaJAp7wzmA==";

    public static final String stringSample =
            "Xf+46E4cnMCbvzP1iaR7LopU9GoFxzo/IyFKlVafxtDQKmek5A02eJVvqFS+GYAGCoki5vj4Opz9 CQNFLP+CBNKco4S7ejKsrAnGdArffOJiJEmrHgbJK20x7/r+LFTYoHx6z0qGibizPPsoJCNDY6G0 92ioTEcAXPKpW4uk1qS/F35xhk77wBWwek5aYInrmjNroki6ZwW49+1bGYmCg9UQfJ7endepaTKM TQFsj1i543W/F2ZGVNGDnghZhDhSvJfLmCIwFhH973rvl3A+RdVqIC9ChWT89xMOweejP2WhlkVl SBIDigexO7DQMOQ6DuLKKBFjhPUwU2zkcHfz9mn1+f6MrfhmGWzB7L3xHrYd4qjdw/PjvgvwcJAo KIHJ04axV37Zi79QD7z8R7+bvfbaOvCsl5a8ErEPzrAJ3j0iq3k/3DcY8d5tI26DCS8IRIA6M2Zf WFmEOvckzpkzaOetgk1jklxiTMc7JTZHdskMpAvPygZwCu97H5T+pEXCRQ/7sMcTtSiZKVPydgwW sRKw7IhlCbCSXPs47yWdwJJFx6p90HRbmHVbPkESY0REJ/VS8hEUR1rFRcS42Natydz9XfRVpTpW wBUhG0/3Znt7Uj9rvxIpH/gYFjunDw3Q";

    //Saarthi
   public static final String usesr = "HP_OnlineSewa";
    public static final String password = "02a1faeb78fcca5f13243d13094350e4";

    public static final String SaarthiURLL = "https://sarathi.parivahan.gov.in/SarathiReport/rsServices/sarathi/service/getLicenseData/";


    /**
     * State Id
     */
    public static final Integer stateID = 9;



}

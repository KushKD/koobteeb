package com.hp.dit.election_ems.modals.information;

import java.io.Serializable;

public class InformationViaId implements Serializable {

    private String name;
    private String ownerName;
    private String ownerNameTwo;
    private String photo;
    private String photoId;
    private String contactNoOne;
    private String contactNoTwo;
    private String helplineNumber;
    private String landlineNumber;
    private Integer optionId;
    private String cctv;
    private String numberIdols;
    private String numberSecurityPersons;
    private String emailId;
    private String facbookId;
    private String presentAddress;
    private String permanentAddress;
    private String firNo;
    private String firDetails;
    private String licenceeNo;
    private String licenceeName;
    private String details;
    private String other;
    private String checkingDateSho;
    private String totalPopulation;
    private String periodFair;
    private String authority;
    private String durationParole;
    private String idProof;
    private String section;
    private String SpecialReportedCases;
    private String extraOne;
    private String extraTwo;
    private Double latitude;
    private Double longitude;

    public InformationViaId(String name, String ownerName, String ownerNameTwo, String photo, String photoId, String contactNoOne, String contactNoTwo, String helplineNumber, String landlineNumber, Integer optionId, String cctv, String numberIdols, String numberSecurityPersons, String emailId, String facbookId, String presentAddress, String permanentAddress, String firNo, String firDetails, String licenceeNo, String licenceeName, String details, String other, String checkingDateSho, String totalPopulation, String periodFair, String authority, String durationParole, String idProof, String section, String specialReportedCases, String extraOne, String extraTwo, Double latitude, Double longitude) {
        this.name = name;
        this.ownerName = ownerName;
        this.ownerNameTwo = ownerNameTwo;
        this.photo = photo;
        this.photoId = photoId;
        this.contactNoOne = contactNoOne;
        this.contactNoTwo = contactNoTwo;
        this.helplineNumber = helplineNumber;
        this.landlineNumber = landlineNumber;
        this.optionId = optionId;
        this.cctv = cctv;
        this.numberIdols = numberIdols;
        this.numberSecurityPersons = numberSecurityPersons;
        this.emailId = emailId;
        this.facbookId = facbookId;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.firNo = firNo;
        this.firDetails = firDetails;
        this.licenceeNo = licenceeNo;
        this.licenceeName = licenceeName;
        this.details = details;
        this.other = other;
        this.checkingDateSho = checkingDateSho;
        this.totalPopulation = totalPopulation;
        this.periodFair = periodFair;
        this.authority = authority;
        this.durationParole = durationParole;
        this.idProof = idProof;
        this.section = section;
        SpecialReportedCases = specialReportedCases;
        this.extraOne = extraOne;
        this.extraTwo = extraTwo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerNameTwo() {
        return ownerNameTwo;
    }

    public void setOwnerNameTwo(String ownerNameTwo) {
        this.ownerNameTwo = ownerNameTwo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getContactNoOne() {
        return contactNoOne;
    }

    public void setContactNoOne(String contactNoOne) {
        this.contactNoOne = contactNoOne;
    }

    public String getContactNoTwo() {
        return contactNoTwo;
    }

    public void setContactNoTwo(String contactNoTwo) {
        this.contactNoTwo = contactNoTwo;
    }

    public String getHelplineNumber() {
        return helplineNumber;
    }

    public void setHelplineNumber(String helplineNumber) {
        this.helplineNumber = helplineNumber;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getCctv() {
        return cctv;
    }

    public void setCctv(String cctv) {
        this.cctv = cctv;
    }

    public String getNumberIdols() {
        return numberIdols;
    }

    public void setNumberIdols(String numberIdols) {
        this.numberIdols = numberIdols;
    }

    public String getNumberSecurityPersons() {
        return numberSecurityPersons;
    }

    public void setNumberSecurityPersons(String numberSecurityPersons) {
        this.numberSecurityPersons = numberSecurityPersons;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFacbookId() {
        return facbookId;
    }

    public void setFacbookId(String facbookId) {
        this.facbookId = facbookId;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getFirNo() {
        return firNo;
    }

    public void setFirNo(String firNo) {
        this.firNo = firNo;
    }

    public String getFirDetails() {
        return firDetails;
    }

    public void setFirDetails(String firDetails) {
        this.firDetails = firDetails;
    }

    public String getLicenceeNo() {
        return licenceeNo;
    }

    public void setLicenceeNo(String licenceeNo) {
        this.licenceeNo = licenceeNo;
    }

    public String getLicenceeName() {
        return licenceeName;
    }

    public void setLicenceeName(String licenceeName) {
        this.licenceeName = licenceeName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getCheckingDateSho() {
        return checkingDateSho;
    }

    public void setCheckingDateSho(String checkingDateSho) {
        this.checkingDateSho = checkingDateSho;
    }

    public String getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(String totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public String getPeriodFair() {
        return periodFair;
    }

    public void setPeriodFair(String periodFair) {
        this.periodFair = periodFair;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDurationParole() {
        return durationParole;
    }

    public void setDurationParole(String durationParole) {
        this.durationParole = durationParole;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSpecialReportedCases() {
        return SpecialReportedCases;
    }

    public void setSpecialReportedCases(String specialReportedCases) {
        SpecialReportedCases = specialReportedCases;
    }

    public String getExtraOne() {
        return extraOne;
    }

    public void setExtraOne(String extraOne) {
        this.extraOne = extraOne;
    }

    public String getExtraTwo() {
        return extraTwo;
    }

    public void setExtraTwo(String extraTwo) {
        this.extraTwo = extraTwo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "InformationViaId{" +
                "name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerNameTwo='" + ownerNameTwo + '\'' +
                ", photo='" + photo + '\'' +
                ", photoId='" + photoId + '\'' +
                ", contactNoOne='" + contactNoOne + '\'' +
                ", contactNoTwo='" + contactNoTwo + '\'' +
                ", helplineNumber='" + helplineNumber + '\'' +
                ", landlineNumber='" + landlineNumber + '\'' +
                ", optionId=" + optionId +
                ", cctv='" + cctv + '\'' +
                ", numberIdols='" + numberIdols + '\'' +
                ", numberSecurityPersons='" + numberSecurityPersons + '\'' +
                ", emailId='" + emailId + '\'' +
                ", facbookId='" + facbookId + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", firNo='" + firNo + '\'' +
                ", firDetails='" + firDetails + '\'' +
                ", licenceeNo='" + licenceeNo + '\'' +
                ", licenceeName='" + licenceeName + '\'' +
                ", details='" + details + '\'' +
                ", other='" + other + '\'' +
                ", checkingDateSho='" + checkingDateSho + '\'' +
                ", totalPopulation='" + totalPopulation + '\'' +
                ", periodFair='" + periodFair + '\'' +
                ", authority='" + authority + '\'' +
                ", durationParole='" + durationParole + '\'' +
                ", idProof='" + idProof + '\'' +
                ", section='" + section + '\'' +
                ", SpecialReportedCases='" + SpecialReportedCases + '\'' +
                ", extraOne='" + extraOne + '\'' +
                ", extraTwo='" + extraTwo + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

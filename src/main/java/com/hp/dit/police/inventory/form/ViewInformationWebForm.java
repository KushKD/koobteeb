package com.hp.dit.police.inventory.form;

import java.io.Serializable;

public class ViewInformationWebForm implements Serializable {

    private Integer id ;
    private Integer stateId;
    private Integer districtId;
    private Integer sosdpoId;
    private Integer psId;
    private Integer beatId;
    private Integer submoduleId;


    private Integer  user_id;
    private Integer  role_id;


    private String name;
    private String owner_name;
    private String owner_nametwo;
    private String photo;
    private String photo_id;
    private String contact_numberone;
    private String contact_numbertwo;
    private String helpline_number;
    private String landline_number;


    private Integer option_id ;


    private String cctv;
    private String number_idols;
    private String number_securitypersons;
    private String email_id ;
    private String facbook_id ;
    private String present_address;
    private String permanent_address;


    private String fir_no;
    private String fir_details;
    private String licencee_no ;
    private String licencee_name ;
    private String details;
    private String other;
    private String checking_date_sho;
    private String total_population;
    private String period_fair ;
    private String authority ;


    private String duration_parole;
    private String id_proof ;
    private String section ;
    private String special_reported_cases ;
    private String extra_one;
    private String extra_two;


    private String latitude ;
    private String longitude ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getSosdpoId() {
        return sosdpoId;
    }

    public void setSosdpoId(Integer sosdpoId) {
        this.sosdpoId = sosdpoId;
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Integer getBeatId() {
        return beatId;
    }

    public void setBeatId(Integer beatId) {
        this.beatId = beatId;
    }


    public Integer getSubmoduleId() {
        return submoduleId;
    }

    public void setSubmoduleId(Integer submoduleId) {
        this.submoduleId = submoduleId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_nametwo() {
        return owner_nametwo;
    }

    public void setOwner_nametwo(String owner_nametwo) {
        this.owner_nametwo = owner_nametwo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getContact_numberone() {
        return contact_numberone;
    }

    public void setContact_numberone(String contact_numberone) {
        this.contact_numberone = contact_numberone;
    }

    public String getContact_numbertwo() {
        return contact_numbertwo;
    }

    public void setContact_numbertwo(String contact_numbertwo) {
        this.contact_numbertwo = contact_numbertwo;
    }

    public String getHelpline_number() {
        return helpline_number;
    }

    public void setHelpline_number(String helpline_number) {
        this.helpline_number = helpline_number;
    }

    public String getLandline_number() {
        return landline_number;
    }

    public void setLandline_number(String landline_number) {
        this.landline_number = landline_number;
    }

    public Integer getOption_id() {
        return option_id;
    }

    public void setOption_id(Integer option_id) {
        this.option_id = option_id;
    }

    public String getCctv() {
        return cctv;
    }

    public void setCctv(String cctv) {
        this.cctv = cctv;
    }

    public String getNumber_idols() {
        return number_idols;
    }

    public void setNumber_idols(String number_idols) {
        this.number_idols = number_idols;
    }

    public String getNumber_securitypersons() {
        return number_securitypersons;
    }

    public void setNumber_securitypersons(String number_securitypersons) {
        this.number_securitypersons = number_securitypersons;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getFacbook_id() {
        return facbook_id;
    }

    public void setFacbook_id(String facbook_id) {
        this.facbook_id = facbook_id;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getFir_no() {
        return fir_no;
    }

    public void setFir_no(String fir_no) {
        this.fir_no = fir_no;
    }

    public String getFir_details() {
        return fir_details;
    }

    public void setFir_details(String fir_details) {
        this.fir_details = fir_details;
    }

    public String getLicencee_no() {
        return licencee_no;
    }

    public void setLicencee_no(String licencee_no) {
        this.licencee_no = licencee_no;
    }

    public String getLicencee_name() {
        return licencee_name;
    }

    public void setLicencee_name(String licencee_name) {
        this.licencee_name = licencee_name;
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

    public String getChecking_date_sho() {
        return checking_date_sho;
    }

    public void setChecking_date_sho(String checking_date_sho) {
        this.checking_date_sho = checking_date_sho;
    }

    public String getTotal_population() {
        return total_population;
    }

    public void setTotal_population(String total_population) {
        this.total_population = total_population;
    }

    public String getPeriod_fair() {
        return period_fair;
    }

    public void setPeriod_fair(String period_fair) {
        this.period_fair = period_fair;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDuration_parole() {
        return duration_parole;
    }

    public void setDuration_parole(String duration_parole) {
        this.duration_parole = duration_parole;
    }

    public String getId_proof() {
        return id_proof;
    }

    public void setId_proof(String id_proof) {
        this.id_proof = id_proof;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSpecial_reported_cases() {
        return special_reported_cases;
    }

    public void setSpecial_reported_cases(String special_reported_cases) {
        this.special_reported_cases = special_reported_cases;
    }

    public String getExtra_one() {
        return extra_one;
    }

    public void setExtra_one(String extra_one) {
        this.extra_one = extra_one;
    }

    public String getExtra_two() {
        return extra_two;
    }

    public void setExtra_two(String extra_two) {
        this.extra_two = extra_two;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "ViewInformationWebForm{" +
                "id=" + id +
                ", stateId=" + stateId +
                ", districtId=" + districtId +
                ", sosdpoId=" + sosdpoId +
                ", psId=" + psId +
                ", beatId=" + beatId +
                ", submodule_id=" + submoduleId +
                ", user_id=" + user_id +
                ", role_id=" + role_id +
                ", name='" + name + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", owner_nametwo='" + owner_nametwo + '\'' +
                ", photo='" + photo + '\'' +
                ", photo_id='" + photo_id + '\'' +
                ", contact_numberone='" + contact_numberone + '\'' +
                ", contact_numbertwo='" + contact_numbertwo + '\'' +
                ", helpline_number='" + helpline_number + '\'' +
                ", landline_number='" + landline_number + '\'' +
                ", option_id=" + option_id +
                ", cctv='" + cctv + '\'' +
                ", number_idols='" + number_idols + '\'' +
                ", number_securitypersons='" + number_securitypersons + '\'' +
                ", email_id='" + email_id + '\'' +
                ", facbook_id='" + facbook_id + '\'' +
                ", present_address='" + present_address + '\'' +
                ", permanent_address='" + permanent_address + '\'' +
                ", fir_no='" + fir_no + '\'' +
                ", fir_details='" + fir_details + '\'' +
                ", licencee_no='" + licencee_no + '\'' +
                ", licencee_name='" + licencee_name + '\'' +
                ", details='" + details + '\'' +
                ", other='" + other + '\'' +
                ", checking_date_sho='" + checking_date_sho + '\'' +
                ", total_population='" + total_population + '\'' +
                ", period_fair='" + period_fair + '\'' +
                ", authority='" + authority + '\'' +
                ", duration_parole='" + duration_parole + '\'' +
                ", id_proof='" + id_proof + '\'' +
                ", section='" + section + '\'' +
                ", special_reported_cases='" + special_reported_cases + '\'' +
                ", extra_one='" + extra_one + '\'' +
                ", extra_two='" + extra_two + '\'' +
                '}';
    }
}

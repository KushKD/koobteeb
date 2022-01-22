package com.hp.dit.beetbook.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="information")
public class AllInformationEntity {

    @Id
    @GeneratedValue(generator = "information_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "information_id_seq", sequenceName = "public.information_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id")
    private int id;


    @Column(name="state_id")
    private Integer stateId;


    @Column(name="district_id")
    private Integer districtId;


    @Column(name="sosdpo_id")
    private Integer sosdpoId;

	@OneToOne
	@JoinColumn(name="ps_id")
	private PoliceStationMaster psId;

	@OneToOne
	@JoinColumn(name="beat_id")
	private BeatMaster beatId;


	@Column(name="module_id")
	private Integer moduleId;

	@OneToOne()
	@JoinColumn(name="submodule_id")
	private SubModuleMaster submoduleId;

	@OneToOne
	@JoinColumn(name="user_id")
	private UserDatatableEntity userId;

	@Column(name="role_id")
	private Integer roleId;

	@Column(name="name")
	private String name;

	@Column(name="owner_name")
	private String ownerName;

	@Column(name="owner_nametwo")
	private String ownerNameTwo;

	@Column(name="photo")
	private String photo;

	@Column(name="photo_id")
	private String photoId;

	@Column(name="contact_numberone")
	private String contactNoOne;

	@Column(name="contact_numbertwo")
	private String contactNoTwo;

	@Column(name="helpline_number")
	private String helplineNumber;

	@Column(name="landline_number")
	private String landlineNumber;

	@Column(name="option_id")
	private Integer optionId;

	@Column(name="cctv")
	private String cctv;

	@Column(name="number_idols")
	private String numberIdols;

	@Column(name="number_securitypersons")
	private String numberSecurityPersons;

	@Column(name="email_id")
	private String emailId;

	@Column(name="facbook_id")
	private String facbookId;

	@Column(name="present_address")
	private String presentAddress;

	@Column(name="permanent_address")
	private String permanentAddress;

	@Column(name="fir_no")
	private String firNo;

	@Column(name="fir_details")
	private String firDetails;

	@Column(name="licencee_no")
	private String licenceeNo;

	@Column(name="licencee_name")
	private String licenceeName;

	@Column(name="details")
	private String details;

	@Column(name="other")
	private String other;

	@Column(name="checking_date_sho")
	private String checkingDateSho;

	@Column(name="total_population")
	private String totalPopulation;

	@Column(name="period_fair")
	private String periodFair;

	@Column(name="authority")
	private String authority;

	@Column(name="duration_parole")
	private String durationParole;

	@Column(name="id_proof")
	private String idProof;

	@Column(name="section")
	private String section;

	@Column(name="special_reported_cases")
	private String SpecialReportedCases;

	@Column(name="extra_one")
	private String extraOne;

	@Column(name="extra_two")
	private String extraTwo;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;


	@Column(name = "active")
	private Boolean active;

	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "lastmodifieddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;


	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public PoliceStationMaster getPsId() {
		return psId;
	}

	public void setPsId(PoliceStationMaster psId) {
		this.psId = psId;
	}

	public BeatMaster getBeatId() {
		return beatId;
	}

	public void setBeatId(BeatMaster beatId) {
		this.beatId = beatId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public SubModuleMaster getSubmoduleId() {
		return submoduleId;
	}

	public void setSubmoduleId(SubModuleMaster submoduleId) {
		this.submoduleId = submoduleId;
	}

	public UserDatatableEntity getUserId() {
		return userId;
	}

	public void setUserId(UserDatatableEntity userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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



	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "InformationEntity{" +
				"id=" + id +
				", stateId=" + stateId +
				", districtId=" + districtId +
				", sosdpoId=" + sosdpoId +
				", psId=" + psId +
				", beatId=" + beatId +
				", moduleId=" + moduleId +
				", submoduleId=" + submoduleId +
				", userId=" + userId +
				", roleId=" + roleId +
				", name='" + name + '\'' +
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
				", active=" + active +
				", createdDate=" + createdDate +
				", updatedOn=" + updatedOn +
				'}';
	}
}
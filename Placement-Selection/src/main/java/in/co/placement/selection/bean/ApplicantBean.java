package in.co.placement.selection.bean;

import java.sql.Blob;
import java.util.Date;

public class ApplicantBean extends BaseBean {

	private String applicantName;
	private String applicantEmail;
	private String applicantMobile;
	private String applicantCity;
	private String applicantState;
	private String applicantCountry;
	private String applicantAddress;
	private Date applicantDob;
	private Blob applicantImage;
	private String applicantQualification;
	
	
	
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getApplicantMobile() {
		return applicantMobile;
	}

	public void setApplicantMobile(String applicantMobile) {
		this.applicantMobile = applicantMobile;
	}

	public String getApplicantCity() {
		return applicantCity;
	}

	public void setApplicantCity(String applicantCity) {
		this.applicantCity = applicantCity;
	}

	public String getApplicantState() {
		return applicantState;
	}

	public void setApplicantState(String applicantState) {
		this.applicantState = applicantState;
	}

	public String getApplicantCountry() {
		return applicantCountry;
	}

	public void setApplicantCountry(String applicantCountry) {
		this.applicantCountry = applicantCountry;
	}

	public String getApplicantAddress() {
		return applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	public Date getApplicantDob() {
		return applicantDob;
	}

	public void setApplicantDob(Date applicantDob) {
		this.applicantDob = applicantDob;
	}

	public Blob getApplicantImage() {
		return applicantImage;
	}

	public void setApplicantImage(Blob applicantImage) {
		this.applicantImage = applicantImage;
	}

	public String getApplicantQualification() {
		return applicantQualification;
	}

	public void setApplicantQualification(String applicantQualification) {
		this.applicantQualification = applicantQualification;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}

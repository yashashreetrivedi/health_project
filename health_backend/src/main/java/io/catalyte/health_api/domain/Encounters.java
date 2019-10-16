package io.catalyte.health_api.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Document(collection ="encounters")
public class Encounters {

	
	
	@Id
	private String _id;
	private ObjectId patientid;
	private String notes;
	private String visitcode;
	private String provider;
	private String billingcode;
	private String icd10;
	private Double totalcost;
	private Double copay;
	private String chiefcomplaint;
	private Integer pluse;
	private Integer systolic;
	private Integer diastolic;
	private Long date;
	
	
	public void setTotalcost(Double totalcost) {
		this.totalcost = totalcost;
	}
	public void setCopay(Double copay) {
		this.copay = copay;
	}
	public  String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
		
	}
	public String getPatientid() {
		return patientid.toHexString();
	}
	public void setPatientid(String patientid) {
		this.patientid = new ObjectId(patientid);
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getVisitcode() {
		return visitcode;
	}
	public void setVisitcode(String visitcode) {
		this.visitcode = visitcode;
	}
	
	public String getProvider() {

		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getBillingcode() {
		return billingcode;
	}
	public void setBillingcode(String billingcode) {
		this.billingcode = billingcode;
	}
	public String getIcd10() {
		return icd10;
	}
	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}
	public double getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}
	public double getCopay() {
		return copay;
	}
	public void setCopay(double copay) {
		this.copay = copay;
	}
	public String getChiefcomplaint() {
		return chiefcomplaint;
	}
	public void setChiefcomplaint(String chiefcomplaint) {
		this.chiefcomplaint = chiefcomplaint;
	}
	public Integer getPluse() {
		return pluse;
	}
	public void setPluse(Integer pluse) {
		this.pluse = pluse;
	}
	public Integer getSystolic() {
		return systolic;
	}
	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}
	public Integer getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		
		this.date = date;
	}
	@Override
	public String toString() {
		return "Encounters [patientid=" + patientid + ", _id=" + _id + ", notes=" + notes + ", visitcode=" + visitcode
				+ ", provider=" + provider + ", billingcode=" + billingcode + ", icd10=" + icd10 + ", totalcost="
				+ totalcost + ", copay=" + copay + ", chiefcomplaint=" + chiefcomplaint + ", pluse=" + pluse
				+ ", systolic=" + systolic + ", diastolic=" + diastolic + ", date=" + date + "]";
	}
	
	
	

	
}

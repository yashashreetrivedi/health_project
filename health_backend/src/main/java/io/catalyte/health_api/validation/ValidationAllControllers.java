package io.catalyte.health_api.validation;

public class ValidationAllControllers {
	
	//Validation postal code
	public boolean validatePostal(String validPostal) {
		if (validPostal.matches("^[0-9]{5}")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//validation for sate
	public boolean validateState(String validstate) {
		if(validstate.matches("^[A-Z]{2}")) {
			return true;
		}
		else {
			return false;
		}
	}
	//validation for gender
	public boolean ValidateGender(String gender ) {
		if(gender.equals("Male") || gender.equals("male") || gender.equals("Female") || gender.equals("female")) {
			return true;
		}
		
		return false;
	}
	
	// ssn code Validation(DDD-DD-DDDD)
	
	public boolean validateSSN(String ssn) {
		if(ssn.matches("^\\d{3}-?\\d{2}-?\\d{4}$")) {
			return true;
		}
		return false;
	}

//	icd code validation String LDD
	
	public boolean validateIcd(String code) {
		if(code.matches("^[a-z,A-Z]{1}[0-9]{2}$")) {
			return true;
		}
		return false;
	}
	
	// visit code validation String - LDL DLD
	
	public boolean validateVisiteCode(String visitecode) {
		if(visitecode.matches("^[a-z,A-Z]{1}[0-9]{1}[a-z,A-Z]{1}[' '][0-9]{1}[a-z,A-Z]{1}[0-9]{1}$")) {
			return true;
		}
		return false;
	}
  
//	billing code validation String - DDD.DDD.DDD-DD
	
	public boolean validatebillingcode(String billingcode) {
		if(billingcode.matches("^[0-9]{3}['.'][0-9]{3}['.'][0-9]{3}[\\-][0-9]{2}$")) {
			return true;
		}
		return false;
	}
	
	
	//validate email  (A*@A*.A*)
	public boolean validateEmail(String email) {
		if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
		{
			return true;
		}
		return false;
	}
	
	public boolean validateNotNullPatient(String id, String firstname, String lastname,String gender, String street, String city ,int  height, int weight, int age) {
		
		if (  firstname != null && firstname != "" && lastname != null && lastname != "" &&  gender !=null && gender != "" && street != null && street != "" && city != null
				&& city != ""  && height != 0 && height > 0  && weight != 0  && weight> 0 &&  age !=0 && age>0) {
			return true;
		}
		return false;
	}
	
	
public boolean validateNotNullUser( String name , String password,  String title) {
	if ( name !=null && name != ""  && password != null && password !=""  &&  title != null &&  title != "" )
	{
		return true;
	}
	return false;
}
	
public boolean validateNotNullEncounters(String id, String visitcode, String provider, String billingcode, String icd10, String chiefcomplaint, Double totalcost, Double copay, Integer  pulse, Integer systolic, Integer  diastolic, Long date ) {
	if( visitcode != null && visitcode != "" &&  provider != null && provider != " " &&  billingcode != null && billingcode != "" 
		&& icd10 != null && icd10 != "" && totalcost != null && totalcost != 0 && copay != null && copay != 0 && chiefcomplaint != null && chiefcomplaint !="" && 
		pulse != null && pulse != 0 && systolic != null && systolic != 0 &&  diastolic != null && diastolic != 0 && date != null && date != 0) {
		return true;
	}
		return false;
}
} 


import React from 'react';
import TextInput from '../../common/TextInput.js';
import PropTypes from 'prop-types';
import { Redirect } from 'react-router-dom';

const btncolor= {
	color:'white',
	backgroundColor:'#009688'
};
const PatientForm = ({ patient, onSave, onChange, onCancel,  errors,  apiError, saveSuccess }) => {
		
	return (

		<form>
					
			<h1>Manage patient</h1>
			{apiError.apierror && <div className="alert alert-danger">{apiError.apierror}</div>}

			
			<TextInput name="firstname"
				label="First Name"
				value={patient.firstname}
				onChange={onChange}
				error={errors.firstname} />

			<TextInput name="lastname"
				label="Last Name"
				value={patient.lastname}
				onChange={onChange}
				error={errors.lastname}/>
				
			<TextInput name="ssn"
				label="SSN"
				value={patient.ssn}
				onChange={onChange}
				error={errors.ssn}/>


			<TextInput name="street"
				label="Street"
				value={patient.address.street}
				onChange={onChange}
				error={errors.street}/>

			<TextInput name="city"
				label="City"
				value={patient.address.city}
				onChange={onChange}
				error={errors.city}/>

			<TextInput name="state"
				label="State"
				value={patient.address.state}
				onChange={onChange}
				error={errors.state}/>

			<TextInput name="postal"
				label="Postal"
				value={patient.address.postal}
				onChange={onChange}
				error={errors.postal}
				required minlength="5" 
			/>

			<TextInput name="age"
				label="Age"
				value={patient.age}
				onChange={onChange}
				error={errors.age}/> 

			<TextInput name="gender"
				label="Gender"
				value={patient.gender}
				onChange={onChange}
				error={errors.gender}/>

			<TextInput name="height"
				label="Height"
				value={patient.height}
				onChange={onChange}
				error={errors.height}
			
			/>

			<TextInput name="weight"
				label="Weight"
				value={patient.weight}
				onChange={onChange}
				error={errors.weight}/>

			<TextInput name="insurance"
				label="Insurance"
				value={patient.insurance}
				onChange={onChange}
				error={errors.insurance}/>

			<button type="submit"
				className="btn btn-raised btn-primary"
				onClick={onSave} > 
				{ 'Save'}
			</button>
			{saveSuccess &&  
						< Redirect to = {'/patients'} /> } 
			
			<button type="button" style={btncolor}
				className="btn btn-raised btn-default"
				onClick={onCancel}>Cancel		
			</button>
					
		</form>
	);
	
};

PatientForm.propTypes = {
	patient: PropTypes.object.isRequired,
	onSave: PropTypes.func.isRequired,
	onCancel: PropTypes.func.isRequired,
	onChange: PropTypes.func.isRequired,
	errors: PropTypes.object,
	saveSuccess: PropTypes.object.isRequired,
	apiError: PropTypes.object
};

export default PatientForm;

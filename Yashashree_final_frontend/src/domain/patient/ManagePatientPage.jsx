import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as patientActions from './patientActions';
import PatientForm from './PatientForm';
import PropTypes from 'prop-types';

export class ManagePatientPage extends React.Component {
	constructor(props, context) {
		super(props, context);

		this.state = {
			patient: Object.assign({}, this.props.patient),
			errors: {},
			apiErrors: Object.assign({}, this.props.apiErrors),
			saving: false,
			saveSuccess: false,
			errorMessage: '',
		};

		this.updatePatientState = this.updatePatientState.bind(this);
		this.savePatient = this.savePatient.bind(this);
		this.cancel = this.cancel.bind(this);
	}

	componentWillReceiveProps(nextProps) {
		if (this.props.patient._id !== nextProps.patient._id) {
			this.setState({ patient: Object.assign({}, nextProps.patient) });
		}
	}

	updatePatientState(event) {
		const field = event.target.name;
		let patient = this.state.patient;
		if(field === 'street' || field === 'city' || field === 'state' || field === 'postal' )
		{
			patient.address[field] = event.target.value;
		}
		else
		{
			patient[field] = event.target.value;
		}
		return this.setState({ patient: patient });
	}

	patientFormIsValid() {
		let formIsValid = true;
		let errors = {};
		let patient = this.state.patient;
		const str = /^[a-zA-Z_ ]+$/;  
		const ssn=/^[0-9]{3}-?[0-9]{2}-?[0-9]{4}$/;
		const digit=/^[0-9]+$/;
		const statecode=/[A-Z]{2}/;
		const zipCode = /^\d{5}$/;
		const gender =/^(?:male|Male|female|Female)$/;
		
		if(!str.test(this.state.patient.firstname)) {
			errors.firstname = 'Please enter valid name';
			formIsValid = false;
		}

		if(!str.test(this.state.patient.lastname)) {
			errors.lastname = 'Please enter valid name';
			formIsValid = false;
		}
		
		if(!zipCode.test(this.state.patient.address.postal)) {
			errors.postal = 'Postal should be 5 digit number';
			formIsValid = false;
		}
		
		
		if(!gender.test(this.state.patient.gender)) {
			errors.gender = 'Please enter valid Gender';
		}
		
		if(!ssn.test(this.state.patient.ssn)) {
			errors.ssn = 'SSN should  be valid formate ex:(435-55-3456)';
		}
		
		if( !digit.test(this.state.patient.height)) {
			errors.height = 'Enter digit only';
		}
  
		if( !digit.test(this.state.patient.weight)) {
			errors.weight = 'Enter digit only';
		}
 
		if(patient.address.street === '') {
			errors.street='This field is required';
		}

		if(patient.address.city=== '') {
			errors.city='This field is required';
		}
		
		if(!digit.test(this.state.patient.age)) {
			errors.age = 'Enter number only';
		}

		if(!statecode.test(this.state.patient.address.state)) {
			errors.state='please enter valid state ex:IL';
		}
		
		if(patient.insurance === '') {
			errors.insurance='This field is required';
		}
		
		this.setState({ errors: errors });
		return formIsValid;
	}

	
	savePatient(event) {
		event.preventDefault();

		if (!this.patientFormIsValid()) {
			return;
		}
		
		if(this.state.patient._id === undefined)
		{
			this.props.actions.createPatientSaga(this.state.patient);
		}
		else
		{
			this.setState({ saveSuccess: false });
			this.props.actions.updatePatientSaga(this.state.patient);
		}

	}
	
	cancel() {
		this.props.actions.handlePatientError('');
		this.context.router.history.push('/patients');
	}
			
	render() {
		
		return (
			
			<PatientForm
				onChange={this.updatePatientState}
				onSave={this.savePatient}
				onCancel={this.cancel}
				patient={this.state.patient}
				errors={this.state.errors}
				apiError={this.props.apiErrors}
				saveSuccess={this.props.saveSuccess}
			
			/>
		
		);
		
	}
}


ManagePatientPage.propTypes = {
	patient: PropTypes.object.isRequired,
	actions: PropTypes.object.isRequired,
	saveSuccess: PropTypes.object.isRequired,
	apiErrors: PropTypes.object.isRequired
};

ManagePatientPage.contextTypes = {
	router: PropTypes.object
};

function mapStateToProps(state, ownProps) {
	const patientId = ownProps.match.params.id;
	let patient;
	
	let apiErrors = {
		apierror: state.patients.errorMessage
	};

	let saveSuccess = state.patients.saveSuccess;
	if(patientId !== 'new')
	{
		patient = { _id: '', firstname: '', lastname: '', ssn: '' ,
			address :{
				street:'',
				city:'',
				state:'',
				postal:''
			},
			age:'',
			gender:'',
			height:'',
			weight:'',
			insurance:''

		};
	}
	else
	{
		patient = { firstname: '', lastname: '', ssn: '' ,
			address :{
				street:'',
				city:'',
				state:'',
				postal:''
			},
			age:'',
			gender:'',
			height:'',
			weight:'',
			insurance:''

		};
	}
	if (patientId && state.patients.patients.length > 0 && patientId !=='new' ) {
		patient = state.patients.patient;
	}
	return {
		
		patient: patient,
		apiErrors: apiErrors,
		saveSuccess: saveSuccess
	};
}

function mapDispatchToProps(dispatch) {
	return {
		actions: bindActionCreators(patientActions, dispatch)
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ManagePatientPage);


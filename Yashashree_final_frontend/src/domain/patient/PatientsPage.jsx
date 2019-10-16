import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import  { bindActionCreators } from 'redux';
import PatientList from './PatientList.jsx';
import * as patientActions from './patientActions.jsx';

const errorStyle = {
	
	color: 'red'
};

class PatientsPage extends React.Component {
	constructor(props, context) {
		super(props, context);
		
		this.state = {
			patient: { firstname: '', _id: '' },
			apiErrors: '',
			saveSuccess: false,
			errorMessage: ''

		};
		this.redirectToAddPatientPage = this.redirectToAddPatientPage.bind(this);
		this.deletePatient = this.deletePatient.bind(this);
		this.loadRecords = this.loadRecords.bind(this);
		this.redirectToUpdatePatientPage = this.redirectToUpdatePatientPage.bind(this);
		this.redirectToEncounterPage = this.redirectToEncounterPage.bind(this);
	}
  
	redirectToAddPatientPage() {
		this.props.actions.saveSuccess(false);
		this.props.actions.handlePatientError('');
		this.context.router.history.push('/patient/new');
	}

	redirectToUpdatePatientPage(id) {
		this.props.actions.saveSuccess(false);
		this.props.actions.handlePatientError('');
		this.props.actions.getPatientInfoSaga(id);
		setTimeout(() => {
			if(this.props.errorMessage === '')
			{
				this.context.router.history.push('/patient/'+ id);                               
			}
		}, 300);
		
	}

	redirectToEncounterPage(id) {
		this.props.actions.handlePatientError('');
		this.context.router.history.push('/encounter/' + id);
	}

	loadRecords()
	{
		this.props.actions.handlePatientError('');
		this.props.actions.loadPatientsSaga();
	}

	deletePatient(id) {
		this.props.actions.handlePatientError('');
		this.props.actions.deletePatientSaga(id);
	}
  
	componentDidMount() {
		
		this.loadRecords();
	}

	
	render() {
		
		let errorHtml;
		if(this.props.errorMessage === '') {
			errorHtml = '';
		} else {
			errorHtml = <div style={errorStyle}>
				<h3>{this.props.errorMessage}</h3>
			</div>;
		}
		
		const { patients } = this.props;
		return (
			<div>
				<br/>
				{errorHtml}
				<br/>
				<input type="submit"
					value="Add Patient "
					className="btn btn-raised btn-primary"
					onClick={this.redirectToAddPatientPage}/>
  
				<PatientList
					onDeletePatient={this.deletePatient} 
					patients={patients}
					onUpdateRedirect={this.redirectToUpdatePatientPage} 
					onEncounterRedirect={this.redirectToEncounterPage} 
				/>
			</div>
		);
	}
}
  
PatientsPage.propTypes = {
	patients: PropTypes.array.isRequired,
	actions: PropTypes.object.isRequired,
	errorMessage: PropTypes.string.isRequired
};
  
PatientsPage.contextTypes = {
	router: PropTypes.object
};

function mapStateToProps(state) {
	return {
		patients: state.patients.patients,
		errorMessage: state.patients.errorMessage,
		patient: state.patients.patient,
	};
}
  
function mapDispatchToProps(dispatch) {
	return {
		actions: bindActionCreators(patientActions, dispatch)
	};
}
  
export default connect (mapStateToProps, mapDispatchToProps)(PatientsPage);

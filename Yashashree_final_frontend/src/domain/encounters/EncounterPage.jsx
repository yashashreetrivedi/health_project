import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import  { bindActionCreators } from 'redux';
import EncounterList from './EncounterList.jsx';
import * as EncounterActions from './EncounterActions.jsx';


const errorStyle = {
	
	color: 'red'
};

class EncounterPage extends React.Component {
	constructor(props, context) {
		super(props, context);
		
		this.state = {
			id: '',
			encounter: { notes: '', _id: '' },
			errorMessage: ''

		};
		this.redirectToPatientListPage = this.redirectToPatientListPage.bind(this);
		this.loadRecords = this.loadRecords.bind(this);
	}
  
	
	redirectToPatientListPage() {
		this.context.router.history.push('/patients');
	}

	loadRecords()
	{
		let encounter = [];
		this.props.actions.handleEncounterError('');
		this.props.actions.getPatientEncountersSuccess(encounter);
		this.props.actions.getPatientEncountersSaga(this.props.id);
		
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
		
		const { encounters } = this.props;
		return (
			<div>
				<br/>
				{errorHtml}
				<br/>
				<input type="submit"
					value="Patinet list "
					className="btn btn-raised btn-primary"
					onClick={this.redirectToPatientListPage} />
				<EncounterList  encounters={encounters}  />
			</div>
		);
	}
}

EncounterPage.propTypes = {
	encounters: PropTypes.array.isRequired,
	actions: PropTypes.object.isRequired,
	errorMessage: PropTypes.string.isRequired,
	id: PropTypes.string.isRequired
};

  
EncounterPage.contextTypes = {
	router: PropTypes.object
};
function mapStateToProps(state, ownProps) {

	const patientId = ownProps.match.params.id;
	return {
		id: patientId,
		encounters: state.encounters.encounters,
		errorMessage: state.encounters.errorMessage
		
	};
}
  
function mapDispatchToProps(dispatch) {
	return {
		actions: bindActionCreators(EncounterActions, dispatch)
	};
}
  
export default connect(mapStateToProps, mapDispatchToProps)(EncounterPage);


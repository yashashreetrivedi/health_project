import React from 'react';
import { Route, Switch } from 'react-router-dom';
import PatientsPage from './domain/patient/PatientsPage.jsx';
import ManagePatientPage from './domain/patient/ManagePatientPage.jsx';
import Login from './domain/login/Login.jsx';
import EncounterPage from './domain/encounters/EncounterPage.jsx';


class Content extends React.Component {
	render() {
		return (
			<div>
				<Switch>
					
					<Route exact path='/' component={Login} />
					<Route path="/patients" component={PatientsPage} />
					<Route path="patient/new" component={ManagePatientPage}/>
					<Route path="/patient/:id" component={ManagePatientPage}/>
					<Route path="/encounter/:id" component={EncounterPage}/>
					
				</Switch>
			</div>

		);
	}
}

export default Content;
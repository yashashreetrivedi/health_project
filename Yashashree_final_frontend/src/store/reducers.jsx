import { combineReducers } from 'redux';
import patients from '../domain/patient/patientReducers.jsx';
import LoginReducer from '../domain/login/LoginReducers.jsx';
import EncounterReducer from '../domain/encounters/EncounterReducers.jsx';

const defaultReducers = combineReducers({

	login: LoginReducer,
	patients: patients ,
	encounters: EncounterReducer
	
});


export default defaultReducers;
import { put, takeEvery } from 'redux-saga/effects';
import constants from '../domain/patient/constants.jsx';
import EncounterConstants from '../domain/encounters/EncounterConstants.jsx';
import * as LoginAction from '../domain/login/LoginAction.jsx';
import * as patientActions from '../domain/patient/patientActions.jsx';
import LoginConstants from '../domain/login/LoginConstants';
import * as encounterActions from '../domain/encounters/EncounterActions.jsx';


export function* HealthCenter() {
	yield takeEvery(constants.LOAD_PATIENTS_SAGA, loadPatients);
	yield takeEvery(constants.UPDATE_PATIENT_SAGA, updatePatient);
	yield takeEvery(constants.CREATE_PATIENT_SAGA, addPatient);
	yield takeEvery(constants.DELETE_PATIENT_SAGA, deletePatient);
	yield takeEvery(LoginConstants.HANDLESUBMIT_SAGA, setLogin);
	yield takeEvery(EncounterConstants.LOAD_ENCOUNTERS_SAGA, loadPatientsEncounters);
	yield takeEvery(constants.GET_PATIENT_INFO_SAGA, getPatientInfo);
			
}


const httpHeader = method => {
	let header = {
		'method': method,
		'headers': new Headers({
			'Content-Type': 'application/json; charset=utf-8'
		})
	};
	return header;
};

const httpHeader1 = method => {
	let header = {
		'method': method,
		'headers': new Headers({
			'Authorization': localStorage.getItem('type') + ' ' + localStorage.getItem('token') ,
			'Content-Type': 'application/json; charset=utf-8'
		})
	};
	return header;
};

function* setLogin(action) {
	let response;
	let header = httpHeader('POST');
	header.body =  JSON.stringify(
		{
			email: action.email,
			password:btoa(action.password)
		});
	
	response = yield fetch('http://localhost:8080/auth/signin', header);
	
	if(response.ok) {
		const data = yield response.json();
		yield setLocalStorage(data.tokenType, data.accessToken);
		yield put(LoginAction.handleToken(data));
	}
	else  {
		yield removeLocalStorage();
		yield put(LoginAction.handleError('Incorrect Email or Password'));
	}
	
}
	

function *loadPatients() {
	let header = httpHeader1('GET');
	let response;
	try {
	
	
		response = yield fetch('http://localhost:8080/patient/info', header);
		
		if(response.ok) {
			const data = yield response.json();
			yield put(patientActions.loadPatientsSuccess(data));
		}
		else if(response.status === 403) {
			yield put(patientActions.handlePatientError('You dont have permission to view patient information '));
		}
		else if(response.status === 404) {
			yield put(patientActions.handlePatientError('Patient not found'));
		}
		else if(response.status === 401) {
			yield put(patientActions.handlePatientError('Please login and retry'));
		}
		else if(response.status === 500) {
			yield put(patientActions.handlePatientError('Database Unavalible'));
		}
	
	}
	catch(err)
	{
		yield put(patientActions.handlePatientError('something went wrong'));
	}

}		
		
	

function *updatePatient(action) {
	let header = httpHeader1('PUT');
	header.body = JSON.stringify(action.patient);
	let response;
	try {
		response = yield fetch(`http://localhost:8080/patient/id?id=${action.patient._id}`, header);
		if(response.ok) {
			const data = yield response.json();
			yield put(patientActions.saveSuccess(true));
			yield put(patientActions.updatePatientSuccess(data));
			yield put(patientActions.handlePatientError(''));
		}
		else if(response.status === 403) {
			yield put(patientActions.handlePatientError('You dont have permission for update'));
		}
		else if(response.status === 400) {
			yield put(patientActions.handlePatientError('Please enter valid data'));
		}
		else if(response.status === 401) {
			yield put(patientActions.handlePatientError('Please login to view patient'));
		}
		else if(response.status === 404) {
			yield put(patientActions.handlePatientError(' Patient Id not found'));
		}
		else if(response.status === 500) {
			yield put(patientActions.handlePatientError(' Database unavalible'));
		}	
	}
	catch(err)	{
		yield put(patientActions.handlePatientError('Something went wrong'));
	}

}
	

function *addPatient(action) {
	let response;
	let header = httpHeader1('POST');
	header.body = JSON.stringify(action.patient);
	try {
		response = yield fetch('http://localhost:8080/patient', header);
		if(response.ok) {
		
			const data = yield response.json();
			yield put(patientActions.saveSuccess(true));
			yield put(patientActions.createPatientSuccess(data));
			yield put(patientActions.handlePatientError(''));
		}
	
		else if(response.status === 403) {
			yield put(patientActions.handlePatientError('You dont have permission for add new patient'));
		}
		else if(response.status === 400) {
			yield put(patientActions.handlePatientError('Please enter valid data'));
		}

		else if(response.status === undefined) {
			yield put(patientActions.handlePatientError('Server is not avaliable, Please retry after some time'));
		}
		else if(response.status === 401) {
			yield put(patientActions.handlePatientError('Please login and retry after some time'));
		}
		else if(response.status === 409) {
			yield put(patientActions.handlePatientError('Invalid creation of object patient already present'));
		}
		else if(response.status === 500) {
			yield put(patientActions.handlePatientError(' Database unavalible'));
		}
	}
	catch(err) {
		yield put(patientActions.handlePatientError('Something went wrong'));
	}
}
	
	
function *deletePatient(action) {
	let header = httpHeader1('DELETE');
	let response;	
	try {
		response = yield fetch(`http://localhost:8080/patient/${action.id}`, header);
		if(response.ok) {
		
			yield put(patientActions.deletePatientSuccess(action.id));
			yield put(patientActions.handlePatientError('Patient deleted succesfully'));
		}
	
		else if(response.status === 409) {
			yield put(patientActions.handlePatientError('Patient has associated encounter, cannot delete'));
		}
		else if(response.status === 404) {
			yield put(patientActions.handlePatientError('Patient not found'));
		}
		else if(response.status === 403) {
			yield put(patientActions.handlePatientError('You dont have permission to delete patient'));
		}
		else if(response.status === 500) {
			yield put(patientActions.handlePatientError(' Database unavalible'));
		}
		else if(response.status === 401) {
			yield put(patientActions.handlePatientError('Please retry after some time'));
		}
	}
	catch(err) {
		yield put(patientActions.handlePatientError('Something went wrong'));
	}
}


function *loadPatientsEncounters(action) {
	let header = httpHeader1('GET');
	let response;
	try {
		response = yield fetch(`http://localhost:8080/encounters/patient/${action.id}`, header);
		if(response.ok) {
			const data = yield response.json();
		
			yield put(encounterActions.getPatientEncountersSuccess(data));
		}

		else if(response.status === 403) {
			yield put(encounterActions.handleEncounterError('You dont have permission to see encounter'));
		}
		else if(response.status === 404) {
			yield put(encounterActions.handleEncounterError('Ther is no encounter for this patient'));
		}
		else if(response.status === 401) {
			yield put(patientActions.handlePatientError(' Please retry after some time'));
		}
		else if(response.status === 500) {
			yield put(patientActions.handlePatientError(' Database unavalible'));
		}
	}
	catch(err)
	{
		yield put(patientActions.handlePatientError('something went wrong'));
	}
}
	

function *getPatientInfo(action) {
	let header = httpHeader1('GET');
	let  response;
	try {
		response = yield fetch(`http://localhost:8080/patient/id?_id=${action.id}`, header);
		if(response.ok) {
			const data = yield response.json();
			yield put(patientActions.getPatientInfoSuccess(data));
		}
		else if(response.status === 403) {
			yield put(patientActions.handlePatientError('You dont have permission to update or view patient details'));
		}
		else if(response.status === 404) {
			yield put(patientActions.handlePatientError('There is no patient as of now'));
		}
		else if(response.status === 401) {
			yield put(patientActions.handlePatientError('Please login to view Patient'));
		}

		else if(response.status === 401) {
			yield put(patientActions.handlePatientError(' Please retry after some time'));
		}
		else if(response.status === 500) {
			yield put(patientActions.handlePatientError('Server is not avaliable, Please retry after some time'));
		}
	}
	catch(err) {
		yield put(patientActions.handlePatientError(' Please retry after some time'));
	}
	
}



function setLocalStorage(type, token) {
	localStorage.setItem('isAuthenticated', 'true');
	localStorage.setItem('type', type);
	localStorage.setItem('token', token);
}

function removeLocalStorage() {
	localStorage.removeItem('isAuthenticated');
	localStorage.removeItem('type');
	localStorage.removeItem('token');
}
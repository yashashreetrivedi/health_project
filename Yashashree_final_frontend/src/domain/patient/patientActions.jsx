import  constants from './constants';

export function loadPatientsSuccess(patients) {
	return {
		type: constants.LOAD_PATIENTS_SUCCESS,
		patients
	};
}


export const loadPatientsSaga = (payload) => {
	return {
		type: constants.LOAD_PATIENTS_SAGA,
		payload : payload
	};
};


export const updatePatientSuccess = (patient ) => {
	return {
		type:constants.UPDATE_PATIENT_SUCCESS,
		patient:patient
	};
};

export const updatePatientSaga = (patient) => {
	return {
		type: constants.UPDATE_PATIENT_SAGA,
		patient:patient
	};
};

export const createPatientSuccess =(patient) => {
	return {
		type:constants.CREATE_PATIENT_SUCCESS,
		patient:patient
	};
	
};

export const createPatientSaga =(patient) => {
	return {
		type:constants.CREATE_PATIENT_SAGA,
		patient:patient
	};

};


export const deletePatientSuccess = (id) => {
	return {

		type: constants.DELETE_PATIENT_SUCCESS,
		id
	};
};


export const deletePatientSaga = (id) => {
	return {
		type:constants.DELETE_PATIENT_SAGA,
		id

	};
	
};

export const handlePatientError = (errorMessage) => {
	return {
		type: constants.HANDLE_PATIENT_ERROR,
		errorMessage: errorMessage
	};
};

export const saveSuccess = (saveSuccess) => {
	return {
		type: constants.SAVE_SUCCESS,
		saveSuccess: saveSuccess
	};
};

export const getPatientInfoSuccess = (patient) => {
	return {

		type: constants.GET_PATIENT_INFO_SUCCESS,
		patient: patient
	};
};

export const getPatientInfoSaga = (id) => {
	return {
		type: constants.GET_PATIENT_INFO_SAGA,
		id
	};
	
};
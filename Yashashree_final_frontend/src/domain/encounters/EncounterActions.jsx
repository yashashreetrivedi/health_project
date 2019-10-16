import  EncounterConstants from './EncounterConstants';

export function getPatientEncountersSuccess(encounters) {
	return {
		type: EncounterConstants.LOAD_ENCOUNTERS_SUCCESS,
		encounters
	};
}


export const getPatientEncountersSaga= (id) => {
	return {
		type: EncounterConstants.LOAD_ENCOUNTERS_SAGA,
		id
	};
};


export const handleEncounterError = (errorMessage) => {
	return {
		type: EncounterConstants.HANDLE_ENCOUNTER_ERROR,
		errorMessage: errorMessage
	};
};
import  constants from './constants';
import initialState from './initialState';

export default function patientReducer(state = initialState, action) {
	switch (action.type) {
		case constants.LOAD_PATIENTS_SUCCESS:
			
			return { ...state, patients: action.patients };

		case constants.UPDATE_PATIENT_SUCCESS:
			
			return { ...state, patient: action.patient };
			

		case constants.CREATE_PATIENT_SUCCESS:
		{
			let patientArr =[...state.patients  , action.patient];
			return { ...state, patients: patientArr };
		}
			
		case constants.DELETE_PATIENT_SUCCESS:
		{
			const pervPatientList = state.patients;

			for (var i=pervPatientList.length-1; i>=0; i--) {
				if (pervPatientList[i]._id === action.id) {
					pervPatientList.splice(i, 1);
					break;      
				}
			}

			
			return { ...state, patients: pervPatientList };
		}
			
		case constants.HANDLE_PATIENT_ERROR: {
			return { ...state, errorMessage: action.errorMessage };
				
		}

		case constants.SAVE_SUCCESS: {
			return { ...state, saveSuccess: action.saveSuccess };
				
		}

		case constants.FETCH_PATIENT_SUCCESS: {
			return { ...state, fetchPatientSuccess: action.fetchPatientSuccess };
				
		}

		case constants.GET_PATIENT_INFO_SUCCESS:
			
			return { ...state, patient: action.patient };
				
		default: return state;
	}

}




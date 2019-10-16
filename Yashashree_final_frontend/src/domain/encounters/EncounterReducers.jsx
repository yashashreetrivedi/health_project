import EncounterConstants from './EncounterConstants';
import initialState from './initialState';

export default function encounterReducer(state = initialState, action) {
	switch (action.type) {
		case EncounterConstants.LOAD_ENCOUNTERS_SUCCESS:
			return { ...state, encounters: action.encounters };

		case EncounterConstants.HANDLE_ENCOUNTER_ERROR: {
			return { ...state, errorMessage: action.errorMessage };
                    
		}
		default: return state;
	}
}

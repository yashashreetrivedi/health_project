import LoginConstants from './LoginConstants.jsx';

const initState = {

	login: { 
		email: '',
		password: ''
	},
	isLoggedIn: false,
	errorMessage: '',
	
};


const LoginReducer = (state=initState, action) => {
	switch(action.type) {

		case LoginConstants.HANDLE_LOGIN: {

			
			return { ...state, 
				login:{
					...state.login,
					[action.name]: action.value }
			};
               
		}
		
        
		case LoginConstants.HANDLE_TOKEN: {
			
			const isLoggedIn = localStorage.getItem('isAuthenticated');
			return { ...state, isLoggedIn: isLoggedIn, errorMessage: '' };
		}

		case LoginConstants.HANDLE_ERROR: {
			return { ...state, errorMessage: action.errorMessage };
		}

		case LoginConstants.HANDLE_LOGOUT: {
			
			localStorage.removeItem('isAuthenticated');
			localStorage.removeItem('type');
			localStorage.removeItem('token');
		
			return { ...state, 
				isLoggedIn: false,
				login:{
					...state.login , 
					email:'',
					password:''
				}
			};
		}
		default: return state;
	}

};

export default LoginReducer;
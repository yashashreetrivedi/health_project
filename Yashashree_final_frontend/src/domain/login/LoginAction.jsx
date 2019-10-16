import LoginConstants from './LoginConstants.jsx';
export const handleLogin = (name, value) => {

	return {
		type: LoginConstants.HANDLE_LOGIN,
		name: name,
		value: value
	};
};

export const handleToken = () => {
	return {
		type: LoginConstants.HANDLE_TOKEN,
		
	};
};

export const handleSubmitSaga = (email, password) => {
	return {
		type:LoginConstants.HANDLESUBMIT_SAGA,
		email: email,
		password: password
	};
};

export const handleError = (errorMessage) => {
	return {
		type: LoginConstants.HANDLE_ERROR,
		errorMessage: errorMessage
	};
};

export const handleLogout = (bool) => {
	return {
		type: LoginConstants.HANDLE_LOGOUT,
		bool: bool
	};
};

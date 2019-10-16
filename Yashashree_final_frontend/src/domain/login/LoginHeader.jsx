import React from 'react';
import * as LoginAction from './LoginAction.jsx';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';

const headerStyle = {
	display: 'flex',
	flexDirection: 'column',

};
const btnstyleheader = {
	display: 'flex',
	fontSize: '26px',
	border: 'none',
	outline: 'none',
	color: 'white',
	textalign: 'center',
	backgroundColor: '#009688',
	margin: '0',
};


class LoginHeader extends React.Component {
	constructor(props) {
		super(props);

		this.handleLogout = this.handleLogout.bind(this);
	}

	handleLogout() {
		this.props.dispatch(LoginAction.handleLogout());
	}

	componentDidMount() {
		
		this.props.dispatch(LoginAction.handleToken());
	}
	render() {
		
		let authHTML;
		if(this.props.login.isLoggedIn !== 'true') {
			authHTML =
				<div
					style={btnstyleheader}>Super Health Clinic
				</div>;
		}
		else {
			authHTML =
				<div style={headerStyle}>
					<Link to='/' style={btnstyleheader} onClick={this.handleLogout}>Logout</Link>
				</div>;
		}
		

		return(
			<div className='login'>

				{authHTML}
				
								
			</div>
		);
	}
}
LoginHeader.propTypes = {
	dispatch:PropTypes.func.isRequired,
	login:PropTypes.object.isRequired
};


export default connect((state)=>{
	return {
		login: state.login,
		
	};
})(LoginHeader);
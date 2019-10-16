import React from 'react';
import* as LoginAction from './LoginAction.jsx';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';


const textarea= {
	width: '300px',
	height:'30px'
};

const titleStyle= {
	width: '600px',
	padding: '10px',
	marginTop: '30px',
	marginLeft: 'auto',
	marginRight: 'auto',
	backgroundColor:'#009688',
	color:'white',
	fontWeight: 'bold',
	fontSize:'30px'
  
};

const formstyle ={
	width: '600px',
	padding: '150px',
	margin: 'auto',
	backgroundColor:'#A9A9A9',
	
};

const btncolor ={
	color:'white',
	backgroundColor:'#009688'
};
	
	
const errorStyle = {
	
	color: 'red'
};



class Login extends React.Component {	
	constructor(props) {
		super(props);
		this.state ={
		};

		this.handleLogin = this.handleLogin.bind(this);
	}

	handleLogin(e) {
		this.props.dispatch(LoginAction.handleLogin(e.target.name, e.target.value ));
	}

	
	handleSubmitSaga() {
	
		
		this.props.dispatch(LoginAction.handleSubmitSaga( this.props.login.login.email, this.props.login.login.password));
	}


	render() {
		
		let errorHtml;
		if(this.props.login.errorMessage === '') {
			errorHtml = '';
		} else {
			errorHtml = <div style={errorStyle}>
				<h3>{this.props.login.errorMessage}</h3>
			</div>;
		}
	
		return (
			<div>
				<div style={titleStyle}> Login </div>
				<div style={formstyle}>
					<form >
						<input onBlur={this.handleLogin} type="text" name="email" placeholder="Enter Email...."  id="email"  className="field" style={textarea}/> <br/><br/>
						
						<input  onBlur= {this.handleLogin} type="password" name="password"  placeholder="Password..."  id="myInput"  className="field" style={textarea}/>
						
						<br/>
						{errorHtml}
						<br/><br/>
				
						<input type="button"  className="btn btn-raised btn-default" value="Login"  onClick={() => this.handleSubmitSaga()} style={btncolor}/>
						
						{this.props.login.isLoggedIn &&  
							< Redirect to = {'/patients'}/>}
					</form>
				</div>
			</div>

		
		);
	}
		
}


Login.propTypes = {
	dispatch:PropTypes.func.isRequired,
	login:PropTypes.object.isRequired
};

export default connect((state)=>{
	
	return {
		login: state.login,
		
	};
})(Login);

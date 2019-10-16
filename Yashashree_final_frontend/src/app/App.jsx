import React, { Component } from 'react';
import Routes from '../routes.js';
import Footer from '../domain/footer/Footer.jsx';
import LoginHeader from '../domain/login/LoginHeader';

class App extends Component {
	render() {
		return (
			<div >
				<LoginHeader/>
				<Routes />
				<Footer/> 
										
			</div>
		);
	}
}

export default App;



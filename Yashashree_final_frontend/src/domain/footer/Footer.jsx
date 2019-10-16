import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

const Style = {
	display: 'flex',
	justifyContent: 'space-between',
	flexDirection: 'row',
	alignItems: 'center',
	overflow: 'hidden',
	backgroundColor:'#333',
	border: 'none',
	position: 'fixed',
	bottom: 0,
	height: '20px',
	width: '100%'
};


const TextStyle = {
	color: 'white',
	margin: 650,
	bottom: '20px',
	marginCenter: '20px',
   
};


/** 
 * @class Footer This class holds all the footer
 * */
class Footer extends React.Component {
	
	
	constructor(props) {
		super(props);
		this.state = {
		};
		

	}

	render() {
		return (
			<div>
				<div style={Style}>
					<p style={TextStyle}> Super health clinic </p>
					
				</div>
			</div>);
	}

}


Footer.propTypes = {
	footer: PropTypes.object,
	dispatch: PropTypes.func

};

export default connect((state)=>{
	return {
		footer: state.footer,
	
	};
})(Footer);

import React from 'react';
import PropTypes from 'prop-types';


const btncolor= {
	color:'white',
	backgroundColor:'#009688'
};

const pretendCell = {
	display: 'table-cell'
};

const PatientListRow = ({ patient, onDeletePatient, onUpdateRedirect, onEncounterRedirect }) => 
{
	const align = {
		verticalAlign: 'middle'
	};
	
	return (
      
		<tr >
			<td style={align}>
				<span className ="glyphicon glyphicon-trash clickable" onClick={onDeletePatient}></span>
			</td>
			<td style={align}>{patient.firstname}</td>
			<td style={align}>{patient.lastname}</td> 
			<td style={align}>{patient.age}</td> 
			<td style={align}>{patient.gender}</td> 
			<td style={align}>
				<span style={pretendCell}><button type="button" className="btn btn-raised btn-default" onClick={onUpdateRedirect} style={btncolor}>Details</button></span>				
				<span style={pretendCell}><button type="button" className="btn btn-raised btn-default" onClick={onEncounterRedirect }style={btncolor}>Encounter</button></span>
			</td>
		</tr>

         
	);


   
};

PatientListRow.propTypes = {
	patient: PropTypes.object.isRequired,
	onDeletePatient: PropTypes.func.isRequired,
	onUpdateRedirect: PropTypes.func.isRequired,
	onEncounterRedirect: PropTypes.func.isRequired
};

export default PatientListRow;
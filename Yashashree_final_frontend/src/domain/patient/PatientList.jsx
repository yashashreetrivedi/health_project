import React from 'react';
import PropTypes from 'prop-types';
import PatientListRow from './PatientListRow.jsx';


const PatientList = ({ patients, onDeletePatient, onUpdateRedirect, onEncounterRedirect }) => {	
	return (
		
			
		<table className="table">
		
			<thead>
				<tr>
					<th>&nbsp;</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Gender </th>
							
				</tr>
			</thead>
			
			<tbody>
				{patients.map((patient) => {
					const deletePatient = onDeletePatient.bind(this, patient._id);
					const updateRedirect = onUpdateRedirect.bind(this, patient._id);
					const encounterRedirect = onEncounterRedirect.bind(this, patient._id);
					return (<PatientListRow 
						key={patient._id} 
						onDeletePatient={deletePatient} 
						patient={patient} 
						onUpdateRedirect={updateRedirect} 
						onEncounterRedirect={encounterRedirect} 
					/>);
				})
				}
			</tbody>
		</table>
			

	
	);
};

 
PatientList.propTypes = {
	patients: PropTypes.array.isRequired,
	onDeletePatient: PropTypes.func.isRequired,
	onUpdateRedirect: PropTypes.func.isRequired,
	onEncounterRedirect: PropTypes.func.isRequired
};
  
export default PatientList;
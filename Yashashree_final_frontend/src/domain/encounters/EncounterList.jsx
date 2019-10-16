import React from 'react';
import PropTypes from 'prop-types';
import EncounterListRow from './EncounterListRow.jsx';

const EncounterList = ({ encounters }) => {
	return (

		<table className="table">
			<thead>
				<tr>
					
					<th>Notes</th>
					<th>Visitcode</th>
					<th>Provider</th>
					<th>Billingcode</th>
					<th>Cd10ity</th>
					<th>Totalcost</th>
					<th>Copay</th>
					<th>Chiefcomplaint</th>
					<th>Pulse </th>
					<th>Systolic</th>
					<th>Diastolic</th>
					<th>Date</th>
				
				</tr>
			</thead>
			<tbody>
				{encounters.map((encounter) => {
					return (<EncounterListRow key={encounter._id} encounter={encounter}  />);
					
				})
				}
			</tbody>
		</table>
	);
};
  
EncounterList.propTypes = {
	encounters: PropTypes.array.isRequired,
	
};
  
export default EncounterList;
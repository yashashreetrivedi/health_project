import React from 'react';
import PropTypes from 'prop-types';


const EncounterListRow = ({ encounter }) => 
{
	return (
       
		<tr>
			<td>{encounter.notes}</td>
			<td>{encounter.visitcode}</td>
			<td>{encounter.provider}</td>
			<td>{encounter.billingcode}</td>
			<td>{encounter.icd10}</td>
			<td>{encounter.totalcost}</td>
			<td>{encounter.copay}</td>
			<td>{encounter.chiefcomplaint}</td>
			<td>{encounter.pulse}</td>
			<td>{encounter.systolic}</td>
			<td>{encounter.diastolic}</td>
			<td>{encounter.date}</td>
		</tr>
         
	);
   
};

EncounterListRow.propTypes = {
	encounter: PropTypes.object.isRequired,
};

export default EncounterListRow;
import React from 'react';
import Enzyme from 'enzyme';
import encounters from '../../domain/encounters/EncounterPage.jsx';
import PatientPage from '../../domain/patient/PatientsPage';
import Adapter from 'enzyme-adapter-react-16';
import { shallow } from 'enzyme';
import { Provider } from 'react-redux';
import store from '../../store/Store.jsx';
import { expect } from 'jest';

Enzyme.configure({ adapter: new Adapter() });

test('The emcounters component displays', () => {
	expect(shallow(<Provider store={store}><encounters /></Provider>).exists()).toBe(true);
});

test('The patient component displays', () => {
	expect(shallow(<Provider store={store}><PatientPage /></Provider>).exists()).toBe(true);
});

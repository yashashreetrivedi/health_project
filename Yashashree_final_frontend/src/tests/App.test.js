import React from 'react';
import Enzyme from 'enzyme';
import App from '../app/App.jsx';
import Adapter from 'enzyme-adapter-react-16';
import { shallow } from 'enzyme';
import wrapper from 'jest';
import { expect } from 'jest';

Enzyme.configure({ adapter: new Adapter() });

test('The app component displays', () => {

	expect(shallow(<App />).exists()).toBe(true), 

	it('should render 1 <div />', () => {
		expect(wrapper.find('div').length).toEqual(1);
	});
});

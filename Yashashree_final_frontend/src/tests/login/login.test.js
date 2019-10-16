import React from 'react';
import Enzyme from 'enzyme';
import Login from '../../domain/login/Login.jsx';
import Adapter from 'enzyme-adapter-react-16';
import { mount, shallow } from 'enzyme';
import { Provider } from 'react-redux';
import store from '../../store/Store.jsx';
import jest from 'jest';
import { expect } from 'jest';



Enzyme.configure({ adapter: new Adapter() });

test('Login Component calls handleLogin when email is input', () => {
	const handleLogin = jest.fn();
	const wrapper = mount(
		<Provider store={store}><Login handleLogin={handleLogin} /></Provider>
	);
	const p = wrapper.find('#email');
	p.simulate('change');
	expect(handleLogin).toHaveBeenCalled;
});

test('The login component displays', () => {
	const wrapper = shallow(<Provider store={store}><Login /></Provider>).
		wrapper.exists.toBe(true);
	it('login should exist', () => {
		expect(wrapper.exists.toBe(true));
	});
	it('should render 1 <div />', () => {
		expect(wrapper.find('div').length).toEqual(1);
	});
});
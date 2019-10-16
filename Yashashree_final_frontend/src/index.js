import React from 'react';
import ReactDOM from 'react-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap-material-design/dist/css/bootstrap-material-design.min.css';
import { BrowserRouter } from 'react-router-dom';
import App from './app/App.jsx';
import { Provider } from 'react-redux';
import store from './store/Store.jsx';
import './index.css';


document.addEventListener('DOMContentLoaded', function () {

	ReactDOM.render(<Provider store={store}><BrowserRouter><App /></BrowserRouter></Provider>,document.getElementById('root'));
});

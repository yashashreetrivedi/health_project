import { createStore, compose, applyMiddleware  } from 'redux';
import reducers from './reducers.jsx';
import createSagaMiddleware from 'redux-saga';
import { HealthCenter } from '../sagas/sagas.js';

const sagaMiddleware = createSagaMiddleware();

const store = createStore(reducers,

	compose(applyMiddleware(sagaMiddleware), window.devToolsExtension ? window.devToolsExtension() : f => f)
);

sagaMiddleware.run(HealthCenter);


export default store;

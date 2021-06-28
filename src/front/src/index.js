import React from 'react';
import ReactDOM from 'react-dom';
import AppContainer from './AppContainer';
import store from './redux/redux';
import {Provider} from 'react-redux';
import "./index.css";

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <AppContainer />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

window.store = store;
import { useEffect } from 'react';
import App from './App';
import { connect } from 'react-redux';
import { getSettedCategory, getCategories } from './redux/categoryReduser';

const AppContainer = (props) => {
	useEffect(() => {
		props.getSettedCategory();
		props.getCategories();
	}, []);

	return <App />
}

export default connect(null, { getSettedCategory, getCategories })(AppContainer);
import { useEffect } from 'react';
import App from './App';
import { connect } from 'react-redux';
import { getSettedCategory, getCategories } from './redux/categoryReduser';
import { getTotalAssets, getTotalPrice } from './redux/assetsReduser';
import initializeApp from './utils/initialize'

const AppContainer = (props) => {
	useEffect(() => {
		if (props.isAuth)
			initializeApp(props);
	}, [props.isAuth]);

	return <App />
}

const mapStateToProps = (state) => ({
	isAuth: state.auth.isAuth
})

export default connect(mapStateToProps, { getSettedCategory, getCategories, getTotalAssets, getTotalPrice })(AppContainer);
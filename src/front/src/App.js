import AuthPage from './components/AuthPage/AuthPage'
import NotFound from './components/NotFound/NotFound'
import { Route, BrowserRouter as Router, Redirect, Switch } from 'react-router-dom'
import { connect } from 'react-redux';

function App(props) {
	return (
		<Router>
			<Switch>
				{props.isAuth && <Redirect to='/home' />}
				<Route
					path="/login"
					component={AuthPage}
				/>
				<Route
					path="/signup"
					component={AuthPage}
				/>
				<Route
					component={NotFound}
				/>
			</Switch>
		</Router>
	);
}

const mapStateToProps = (state) => ({
	isAuth: state.auth.isAuth
})

export default connect(mapStateToProps, {})(App);

import LoginPage from './components/LoginPage/LoginPage'
import HomePage from './components/HomePage/HomePage'
import AssetsPage from './components/AssetsPage/AssetsPage'
import NotFound from './components/NotFound/NotFound'
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom'
import AuthForm from './components/Forms/AuthForm/AuthForm'
import SignUpForm from './components/Forms/SignUpForm/SignUpForm'
import Alert from './components/Alert/Alert';
import Confirm from './components/Confirm/Confirm';

function App() {
	return (
		<Router>
			<Switch>
				<Route
					path="/login"
					render={() => <LoginPage form={<AuthForm />}></LoginPage>}
				/>
				<Route
					path="/signup"
					render={() => <LoginPage form={<SignUpForm />}></LoginPage>}
				/>
				<Route
					exact
					path="/"
					component={ HomePage }
				/>
				<Route
					exact
					path="/assets"
					component={ AssetsPage }
				/>
				<Route
					component={NotFound}
				/>
			</Switch>
			<Alert />
			<Confirm />
		</Router>
	);
}

export default App;

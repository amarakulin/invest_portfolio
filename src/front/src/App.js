import LoginPage from './components/LoginPage/LoginPage'
import HomePage from './components/HomePage/HomePage'
import NotFound from './components/NotFound/NotFound'
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom'
import AuthForm from './components/Forms/AuthForm/AuthForm'
import SignUpForm from './components/Forms/SignUpForm/SignUpForm'

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
					component={ HomePage }
				/>
				<Route
					component={NotFound}
				/>
			</Switch>
		</Router>
	);
}

export default App;

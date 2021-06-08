import AuthPage from './components/AuthPage/AuthPage'
import SignUpPage from './components/SignUpPage/SignUpPage'
import HomePage from './components/HomePage/HomePage'
import NotFound from './components/NotFound/NotFound'
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom'

function App() {
	return (
		<Router>
			<Switch>
				<Route
					path="/login"
					component={AuthPage}
				/>
				<Route
					path="/signup"
					component={ SignUpPage }
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

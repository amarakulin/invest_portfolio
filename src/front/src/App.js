import AuthPage from './components/AuthPage/AuthPage'
import {Route, BrowserRouter as Router} from 'react-router-dom'

function App() {
	return (
		<Router>
			<Route 
				path="/login"
				render={() => <AuthPage/>}
			/>
		</Router>
	);
}

export default App;

import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

const mapStateToProps = (state) => ({
	isAuth: state.auth.isAuth
})

export const withAuthRedirectToSignUp = (Component) => {

	const RedirectComponent = (props) => {
		if (!props.isAuth)
			return <Redirect to='/signup'/>
		return <Component {...props} />
	}

	const ConnectedRedirectComponent = connect(mapStateToProps, {})(RedirectComponent);

	return ConnectedRedirectComponent;
}

export const withAuthRedirectToHome = (Component) => {

	const RedirectComponent = (props) => {
		if (props.isAuth)
			return <Redirect to='/'/>
		return <Component {...props} />
	}

	const ConnectedRedirectComponent = connect(mapStateToProps, {})(RedirectComponent);

	return ConnectedRedirectComponent;
}

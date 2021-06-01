import style from './AuthPage.module.css'
import AuthForm from './AuthForm/AuthForm'
import AuthBG from '../../assets/AuthBG.png'

const AuthPage = (props) => {
	return (
		<div className={style.container}>
			<img src={AuthBG} alt="img" className={style.img}/>
			<AuthForm></AuthForm>
		</div>
	)
}

export default AuthPage;
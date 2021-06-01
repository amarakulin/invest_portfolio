import style from './AuthForm.module.css';
import Title from '../../basicComponents/Title/Title'
import Subtitle from '../../basicComponents/Subtitle/Subtitle'
import FormInput from '../../basicComponents/FormInput/FormInput'
import Checkbox from '../../basicComponents/Checkbox/Checkbox'
import Button from '../../basicComponents/Button/Button'
import {NavLink} from 'react-router-dom'

const AuthForm = (props) => {
	return (
		<div className={style.container}>
			<Title>Вход</Title>
			<Subtitle>Пожалуйста, заполните все поля </Subtitle>
			<hr></hr>
			<FormInput id="email" placeholder="Введите E-mail" labelText="E-mail*"/>
			<FormInput id="password" placeholder="Введите пароль" labelText="Введите пароль*"/>
			<div className={style.wrapepr}>
				<Checkbox id="remember" text="Запомнить меня"></Checkbox>
				<NavLink to="/reg" className={style.remindPassword}>Забыли пароль?</NavLink>
			</div>
			<Button className={style.button}>Войти</Button>
		</div>
	)
}

export default AuthForm;
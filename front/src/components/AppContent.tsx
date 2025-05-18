import WelcomeContent from "./WelcomeContent.tsx";
import AuthContent from "./AuthContent.tsx";
import FormLogin from "./FormLogin.tsx";
import {useState} from "react";
import type {LoginProps, RegisterProps} from "../interfaces/CredentialsProps.tsx";
import LoginButtons from "./LoginButtons.tsx";
import axios from "axios";

export default function AppContent() {
	const [componentToShow, setComponentToShow] = useState("welcome");

	const logOut = () => {
		axios.post("http://localhost:8080/logout")
		setComponentToShow("welcome")
	}

	const onLogin = ({e, ...props}: LoginProps) => {
		e.preventDefault();
		axios.post("/login", {username: props.username, password: props.password})
			.then((res) => {
				setComponentToShow("tchat");
				console.log(res)
			})
			.catch((er) => {
				setComponentToShow("welcome");
				console.log(er);
				console.log(er.response.data.error);
			});
	}

	const onRegister = ({e, username, password,}: RegisterProps) => {
		e.preventDefault();
		axios.post("/register", {username: username, password: password})
			.then(() => {
				setComponentToShow("tchat");
			})
			.catch(() => {
				setComponentToShow("welcome");
			});
	}

	return (
		<>
			<LoginButtons onLogin = {() => setComponentToShow("login")} onLogout={logOut}/>

			{componentToShow === "welcome" && <WelcomeContent /> }
			{componentToShow === "tchat" && <AuthContent /> }
			{componentToShow === "login" && <FormLogin onLogin={onLogin} onRegister={onRegister} /> }
		</>
	)
};
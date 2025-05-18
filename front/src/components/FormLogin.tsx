import {type ChangeEvent, type FormEvent, useState} from "react";
import type {LoginProps, RegisterProps} from "../interfaces/CredentialsProps.tsx";

interface FormLoginProps {
 onLogin: (props: LoginProps) => void;
 onRegister: (props: RegisterProps) => void;
}

export default function FormLogin({onLogin, onRegister}: FormLoginProps) {
	const [active, setActive] = useState("login");
	const [user, setUser] = useState({
		username: "",
		password: "",
	})

	function handleChange(e: ChangeEvent<HTMLInputElement>) {
		setUser({
			...user,
			[e.target.name]: e.target.value,
		})
	}

	function onSubmitLogin(e: FormEvent<HTMLFormElement>) {
		onLogin({e, ...user});
	}

	function onSubmitRegister(e: FormEvent<HTMLFormElement>) {
		onRegister(
			{
				e,
				...user
			}
		);
	}

	return (
		<div className="row justify-content-center">
			<div className="col-4">
				<ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
					<li className="nav-item" role="presentation">
						<button className={"nav-link" + (active === "login" ? " active" : "")}
						onClick={() => setActive("login")}>Login</button>
					</li>
					<li className="nav-item" role="presentation">
						<button className={"nav-link" + (active === "register" ? " active" : "")}
						onClick={() => setActive("register")}>Register</button>
					</li>
				</ul>

				<div className="tab-content">
					<div className={"tab-pane fade" + (active === "login" ? " show active" : "")} id={"pills-login"}>
						<form onSubmit={onSubmitLogin}>
							<div className="form-outline mb-4">
								<input type="login" id="loginName" name="username" className="form-control" onChange={handleChange}/>
								<label className="form-label" htmlFor="loginName" >Username</label>
							</div>

							<div className="form-outline mb-4">
								<input type="password" id="loginPassword" name="password" className="form-control" onChange={handleChange}/>
								<label className="form-label" htmlFor="loginPassword">Password</label>
							</div>

							<button className="btn btn-primary btn-block mb-4" type="submit">Sign in</button>
						</form>
					</div>

					<div className={"tab-pane fade" + (active === "register" ? " show active" : "")} id={"pills-register"}>
						<form onSubmit={onSubmitRegister}>
							<div className="form-outline mb-4">
								<input type="text" id="firstName" name="firstName" className="form-control"/>
								<label className="form-label" htmlFor="firstName">First Name</label>
							</div>

							<div className="form-outline mb-4">
								<input type="text" id="lastName" name="lastName" className="form-control"/>
								<label className="form-label" htmlFor="lastName">Last Name</label>
							</div>

							<div className="form-outline mb-4">
								<input type="login" id="username" name="username" className="form-control" onChange={handleChange}/>
								<label className="form-label" htmlFor="username">Username</label>
							</div>

							<div className="form-outline mb-4">
								<input type="password" id="password" name="password" className="form-control" onChange={handleChange}/>
								<label className="form-label" htmlFor="password">Password</label>
							</div>

							<button className="btn btn-primary btn-block mb-4" type="submit">Sign in</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	)
}
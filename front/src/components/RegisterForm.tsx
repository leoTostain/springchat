import {Form} from "react-router-dom";

export default function RegisterForm() {
	return (
		<div>
			<Form action="/register" method="POST">
				<div className="form-outline mb-4">
					<input type="text" id="firstName" name="firstName" className="form-control"/>
					<label className="form-label" htmlFor="firstName">First Name</label>
				</div>

				<div className="form-outline mb-4">
					<input type="text" id="lastName" name="lastName" className="form-control"/>
					<label className="form-label" htmlFor="lastName">Last Name</label>
				</div>

				<div className="form-outline mb-4">
					<input type="login" id="username" name="username" className="form-control"/>
					<label className="form-label" htmlFor="username">Username</label>
				</div>

				<div className="form-outline mb-4">
					<input type="password" id="password" name="password" className="form-control"/>
					<label className="form-label" htmlFor="password">Password</label>
				</div>

				<button className="btn btn-primary btn-block mb-4" type="submit">Sign in</button>
			</Form>
		</div>
	)
}
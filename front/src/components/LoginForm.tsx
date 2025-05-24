import {Form, useActionData} from "react-router-dom";

export default function LoginForm() {
	let actionData = useActionData();
	console.log(actionData);

	return (
		<div>
			<Form action="/login" method="POST">
				<div className="form-outline mb-4">
					<input type="login" id="loginName" name="username" className="form-control"/>
					<label className="form-label" htmlFor="loginName" >Username</label>
				</div>

				<div className="form-outline mb-4">
					<input type="password" id="loginPassword" name="password" className="form-control"/>
					<label className="form-label" htmlFor="loginPassword">Password</label>
				</div>

				{actionData && actionData.error ? (<p>{actionData.error}</p>) : null}

				<button className="btn btn-primary btn-block mb-4" type="submit">Sign in</button>
			</Form>
		</div>
	)
}
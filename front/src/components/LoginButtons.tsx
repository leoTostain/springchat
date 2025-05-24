import axios from "axios";
import {NavLink} from "react-router";

export default function LoginButtons() {
	return (
		<div className="row justify-content-center">
			<div className="col-4 text-center mt-3">
				<NavLink to={"/login"} className={"btn btn-primary m-1"}>Login</NavLink>
				<NavLink to={"/"} className={"btn btn-dark m-1"} onClick={() => axios.post("http://localhost:8080/logout")}>Logout</NavLink>
			</div>
		</div>
	)
}

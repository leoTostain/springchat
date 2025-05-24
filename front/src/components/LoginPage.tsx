import {NavLink, Outlet} from "react-router";

export default function LoginPage() {
	return (
		<div className="col-4">
			<ul className="nav nav-pills nav-justified mb-3">
				<li className="nav-item">
					<NavLink to={"/login"} className={({isActive}) => {
						return "nav-link " + (isActive ? "active" : "")
					}}>
						Login
					</NavLink>
				</li>
				<li className="nav-item">
					<NavLink to={"/register"} className={({isActive}) => {
						return "nav-link " + (isActive ? "active" : "")
					}}>
						Register
					</NavLink>
				</li>
			</ul>
			<Outlet />
		</div>
	)
}
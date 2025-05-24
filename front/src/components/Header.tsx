import './Header.css'
import viteLogo from '/vite.svg'
import reactLogo from '/react.svg';
import {NavLink} from "react-router";

export default function Header() {
	return (
		<header>
			<a href="https://vite.dev" target="_blank">
				<img src={viteLogo} className="logo vite" alt="Vite logo" />
			</a>
			<a href="https://react.dev" target="_blank">
				<img src={reactLogo} className="logo react" alt="React logo" />
			</a>
			<NavLink to={"/"} className={"text-decoration-none"}><h1 className="app-title">SpringChat</h1></NavLink>
		</header>
	)
}
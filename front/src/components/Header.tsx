import './Header.css'
import viteLogo from '/vite.svg'
import reactLogo from '/react.svg';

export default function Header({title}: {title: string}) {
	return (
		<header>
			<a href="https://vite.dev" target="_blank">
				<img src={viteLogo} className="logo vite" alt="Vite logo" />
			</a>
			<a href="https://react.dev" target="_blank">
				<img src={reactLogo} className="logo react" alt="React logo" />
			</a>
			<h1 className="app-title">{title}</h1>
		</header>
	)
}
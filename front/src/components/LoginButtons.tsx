interface ButtonsProps {
	onLogin: () => void
	onLogout: () => void
}

export default function LoginButtons({onLogin, onLogout}: ButtonsProps) {
	return (
		<div className="row justify-content-center">
			<div className="col-4 text-center mt-3">
				<button className="btn btn-primary m-1" onClick={onLogin}>login</button>
				<button className="btn btn-dark m-1" onClick={onLogout}>logout</button>
			</div>
		</div>
	)
}

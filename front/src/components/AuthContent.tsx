import {useLoaderData} from "react-router-dom";

export default function AuthContent() {
	const users = useLoaderData()

	return (
		<div className="row justify-content-md-center">
			<div className="col-4">
				<ul>
					{users.map((el, index) => <li key={index}>{el.username}</li>)}
				</ul>
			</div>
		</div>
	)
}
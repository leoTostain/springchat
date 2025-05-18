import {useEffect, useState} from "react";
import axios from "axios";

export default function AuthContent() {
	const [data, setData] = useState([]);

	useEffect(() => {
		axios.get("/users")
			.then((response) => {
				setData(response.data);
				console.log(response.data);
			}).catch(er => {
				console.log(er);
				console.log(er.response.data.error);
		})
	})

	return (
		<div className="row justify-content-md-center">
			<div className="col-4">
				<ul>
					{data && data.map((el, index) => <li key={index}>{el.username}</li>)}
				</ul>
			</div>
		</div>
	)
}
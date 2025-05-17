import {useEffect, useState} from "react";
import {request} from "../axios_helper";

export default function AuthContent() {
	const [data, setData] = useState([]);

	useEffect(() => {
		request(
			"GET",
			"/greetings",
			{}
		).then((response) => {
			setData(response.data);
		})
	})

	return (
		<div>
			{data && <p>{data}</p>}
		</div>
	)
}
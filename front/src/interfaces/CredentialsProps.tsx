import type {FormEvent} from "react";

export interface LoginProps {
	e: FormEvent<HTMLFormElement>,
	username: string,
	password: string
}

export interface RegisterProps {
	e: FormEvent<HTMLFormElement>,
	username: string,
	password: string
	// firstName: string,
	// lastName: string,
}
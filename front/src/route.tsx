import {createBrowserRouter, redirect} from "react-router-dom";
import HomePage from "./components/HomePage";
import axios from "axios";
import AuthContent from "./components/AuthContent";
import LoginPage from "./components/LoginPage";
import LoginForm from "./components/LoginForm.tsx";
import RegisterForm from "./components/RegisterForm.tsx";
import App from "./components/App.tsx";

export const router = createBrowserRouter([
    {
        path: "/",
        Component: App,
        children: [
            {
                path: "/",
                Component: HomePage,
            },
            {
                Component: LoginPage,
                children: [
                    {
                        path: "login",
                        Component: LoginForm,
                        action: async ({request}) => {
                            const formData = await request.formData();

                            try {
                                await axios.post("/login", {username: formData.get("username"), password: formData.get("password")});
                                return redirect("/tchat")
                            } catch (error) {
                                return error.data;
                            }
                        }
                    },
                    {
                        path: "register",
                        Component: RegisterForm,
                        action: async ({request}) => {
                            const formData = await request.formData();

                            try {
                                await axios.post("/register", {username: formData.get("username"), password: formData.get("password")});
                                return redirect("/tchat")
                            } catch (error) {
                                return error.data;
                            }
                        }
                    }
                ]
            },
            {
                path: "/tchat",
                loader: async () => {
                    return {response: await axios.get("/users")}.response.data
                },
                Component: AuthContent
            }
        ]
    }
])
import './App.css'
import Header from "./Header.tsx";
import {Outlet} from "react-router";

function App() {
    return (
    <>
      <Header />
      <div className="container-fluid">
        <div className="row justify-content-center">
          <Outlet />
        </div>
      </div>
    </>
  )
}

export default App

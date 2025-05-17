import './App.css'
import Header from "./Header.tsx";
import WelcomeComponent from "./WelcomeComponent.tsx";
import AuthContent from "./AuthContent.tsx";

function App() {
  return (
    <>
      <Header title={"My app"}/>
      <div className="container-fluid">
        <div className="row">
          <WelcomeComponent />
          <AuthContent />
        </div>
      </div>
    </>
  )
}

export default App

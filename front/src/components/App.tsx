import './App.css'
import Header from "./Header.tsx";
import AppContent from "./AppContent.tsx";

function App() {
    return (
    <>
      <Header title={"My app"}/>
      <div className="container-fluid">
        <div className="row justify-content-center">
          <AppContent />
        </div>
      </div>
    </>
  )
}

export default App

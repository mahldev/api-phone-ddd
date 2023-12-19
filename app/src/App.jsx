import "./App.css";

import { Routes, Route } from "react-router-dom";
import PhoneDetailsPage from "./pages/PhoneDetailsPage";
import PhonesPage from "./pages/PhonesPage";
import IndexPage from "./pages/IndexPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<IndexPage />} />
      <Route path="/phones/:id" element={<PhoneDetailsPage />} />
      <Route path="/phones/" element={<PhonesPage />} />
    </Routes>
  );
}

export default App;

import React, { useState } from "react";
import "./styles.css";
import AdminLogin from "./AdminLogin";
import AdminHome from "./AdminHome";
import AdminDashboard from "./AdminDashboard";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [showDashboard, setShowDashboard] = useState(false);

  if (!loggedIn) {
    return <AdminLogin onLoginSuccess={() => setLoggedIn(true)} />;
  }

  if (!showDashboard) {
    return <AdminHome goToDashboard={() => setShowDashboard(true)} />;
  }

  return <AdminDashboard />;
}

export default App;
``
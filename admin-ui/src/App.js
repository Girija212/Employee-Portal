import React, { useState } from "react";
import "./styles.css";
import AdminLogin from "./AdminLogin";
import AdminDashboard from "./AdminDashboard";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <div>
      {loggedIn ? (
        <AdminDashboard />
      ) : (
        <AdminLogin onLoginSuccess={() => setLoggedIn(true)} />
      )}
    </div>
  );
}

export default App;
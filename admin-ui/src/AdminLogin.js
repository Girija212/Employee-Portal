import React, { useState } from "react";
import "./styles.css";

function AdminLogin({ onLoginSuccess }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const loginAdmin = async (e) => {
    e.preventDefault();

    const url = "http://localhost:3000/login";

    const formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);

    try {
      const res = await fetch(url, {
        method: "POST",
        body: formData,
        credentials: "include",
        redirect: "follow"
      });

      if (res.url.includes("/admin/home")) {
        onLoginSuccess();
        return;
      }else{
        setError("Invalid username or password");
      }

    } catch (err) {
      setError("Server error");
    }
  };

  return (
    <div className="login-container">
      <h2>Admin Login</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={loginAdmin}>
        <div className="input-box">
          <label>Username</label>
          <input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>

        <div className="input-box">
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <button className="btn" type="submit">Login</button>
      </form>
    </div>
  );
}

export default AdminLogin;
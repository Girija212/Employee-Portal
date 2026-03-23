import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../api/api';
import '../styles.css';

const LoginPage = () => {
  const [empId, setEmpId] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const login = async (e) => {
    e.preventDefault();

    const response = await loginUser(empId, password);

    if (response === "LOGIN_SUCCESS") {
      localStorage.setItem("empId", empId);
      navigate("/home");
    } 
    else if (response === "FIRST_LOGIN") {
      localStorage.setItem("empId", empId);
      navigate("/reset");
    } 
    else {
      setError("Invalid credentials");
    }
  };

  return (
    <div className="login-container">
      <h2>User Login</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={login}>
        <div className="input-box">
          <label>Employee ID</label>
          <input
            type="text"
            value={empId}
            onChange={(e) => setEmpId(e.target.value)}
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

      <div style={{ textAlign: "center", marginTop: "15px" }}>
        <a href="/forgot" style={{ color: "#0066ff", textDecoration: "none", fontSize: "14px" }}>
          Forgot Password?
        </a>
      </div>
    </div>
  );
};

export default LoginPage;

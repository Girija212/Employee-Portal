import React, { useState } from "react";
import { forgotPassword } from "../api/api";
import "../styles.css";

function ForgotPage() {
  const [empId, setEmpId] = useState("");
  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false);

  const send = async () => {
    if (!empId) {
      setMsg("Please enter your Employee ID");
      return;
    }
    setLoading(true);
    const res = await forgotPassword(empId);
    setMsg(res);
    setLoading(false);
  };

  return (
    <div className="login-container">
      <h2>Forgot Password</h2>

      <div className="input-box">
        <label>Employee ID</label>
        <input
          type="text"
          placeholder="Enter your Employee ID"
          value={empId}
          onChange={(e) => setEmpId(e.target.value)}
        />
      </div>

      <button className="btn" onClick={send} disabled={loading}>
        {loading ? "Sending..." : "Send Default Password"}
      </button>

      {msg && (
        <p style={{ marginTop: "15px", color: msg.includes("Error") || msg.includes("not found") ? "red" : "green", textAlign: "center" }}>
          {msg}
        </p>
      )}
    </div>
  );
}

export default ForgotPage;
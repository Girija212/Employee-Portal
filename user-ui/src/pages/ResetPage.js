import React, { useState } from "react";
import { resetPassword } from "../api/api";
import { useNavigate } from "react-router-dom";

function ResetPage() {
  const [empId, setEmpId] = useState("");
  const [newPassword, setNewPwd] = useState("");
  const [msg, setMsg] = useState("");
  const navigate = useNavigate();

  const reset = async () => {
    const res = await resetPassword(empId, newPassword);
    setMsg(res);
    if (res.includes("success")) navigate("/");
  };

  return (
    <div className="container">
      <div className="reset-title">Reset Password</div>
      <form className="reset-form" onSubmit={e => { e.preventDefault(); reset(); }}>
        <input
          placeholder="Employee ID"
          value={empId}
          onChange={(e) => setEmpId(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="New Password"
          value={newPassword}
          onChange={(e) => setNewPwd(e.target.value)}
          required
        />
        <button type="submit">Reset</button>
      </form>
      {msg && <div className="reset-message">{msg}</div>}
    </div>
  );
}

export default ResetPage;
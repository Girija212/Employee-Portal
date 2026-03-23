import React, { useState } from "react";
import "./styles.css";

function AdminDashboard() {
  const [file, setFile] = useState(null);
  const [msg, setMsg] = useState("");

  const uploadExcel = async () => {
    if (!file) {
      setMsg("Please select an Excel file");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      const res = await fetch("http://localhost:3000/admin/upload", {
        method: "POST",
        body: formData,
        credentials: "include"
      });

      const text = await res.text();
      setMsg(text);
    } catch (err) {
      setMsg("Upload failed! Server error.");
    }
  };

  const logout = () => {
    fetch("http://localhost:3000/logout",{
      method: "POST",
      credentials: "include"
    }).then(() => {
      window.location.href = "http://localhost:3002";
    });
  };

  return (
    <div className="dashboard-container">
      <h2>Admin Dashboard</h2>

      <h3>Upload Excel File</h3>

      <input
        type="file"
        accept=".xlsx,.xls"
        onChange={(e) => setFile(e.target.files[0])}
      />

      <button className="btn" style={{ marginTop: "10px" }} onClick={uploadExcel}>
        Upload
      </button>

      {msg && <p>{msg}</p>}

      <button className="logout" onClick={logout}>
        Logout
      </button>
    </div>
  );
}

export default AdminDashboard;
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { markSurveySubmitted } from "../api/api";
import "../styles.css";

function SurveyCompleted() {
  const navigate = useNavigate();
  const [message, setMessage] = useState("Processing your submission...");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const markAsSubmitted = async () => {
      const empId = localStorage.getItem("empId");
      
      if (!empId) {
        setMessage("Employee ID not found. Please login again.");
        setLoading(false);
        setTimeout(() => navigate("/home"), 3000);
        return;
      }

      try {
        const response = await markSurveySubmitted(empId);
        setMessage("✓ Survey successfully recorded in the system!");
        setLoading(false);
        // Redirect to home after 2 seconds
        setTimeout(() => navigate("/home"), 2000);
      } catch (err) {
        setMessage("Error recording submission. Redirecting...");
        setLoading(false);
        setTimeout(() => navigate("/home"), 3000);
      }
    };

    markAsSubmitted();
  }, [navigate]);

  return (
    <div className="login-container">
      <h2>Survey Submission</h2>
      <p style={{ marginTop: "20px", textAlign: "center", fontSize: "16px" }}>
        {message}
      </p>
      {loading && <p style={{ textAlign: "center", marginTop: "10px" }}>Redirecting...</p>}
    </div>
  );
}

export default SurveyCompleted;

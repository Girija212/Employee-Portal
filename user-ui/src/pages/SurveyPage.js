import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { markSurveySubmitted, submitSurvey } from "../api/api";
import "../styles.css";

function SurveyPage() {
  const microsoftFormUrl = "https://forms.office.com/r/Bfk92DmFqM";
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const handleFormCompletion = async () => {
    const empId = localStorage.getItem("empId");
    if (!empId) {
      alert("Employee ID not found. Please login again.");
      return;
    }
    setLoading(true);
    try {
      const answers = "done";
      await submitSurvey(empId, answers);
      await markSurveySubmitted(empId);
      navigate("/home");
    } catch (err) {
      alert("Error saving your submission. Please try again.");
      setLoading(false);
    }
  };

  return (
    <div className="login-container">
      <h2>Employee Survey</h2>

      <p style={{ marginTop: "10px", marginBottom: "25px" }}>
        Please click the button below to complete the survey.
      </p>

      <div style={{ display: "flex", gap: "10px", flexDirection: "column" }}>
        <a
          href={microsoftFormUrl}
          target="_blank"
          rel="noopener noreferrer"
        >
          <button className="btn">Open Survey Form</button>
        </a>

        <p style={{ marginTop: "10px", marginBottom: "10px", fontSize: "14px", color: "#666", textAlign: "center" }}>
          After completing and submitting the survey form, click the button below:
        </p>

        <button 
          className="btn" 
          onClick={handleFormCompletion} 
          disabled={loading}
          style={{ backgroundColor: "#28a745" }}
          onMouseOver={(e) => !loading && (e.target.style.backgroundColor = "#218838")}
          onMouseOut={(e) => !loading && (e.target.style.backgroundColor = "#28a745")}
        >
          {loading ? "Saving to Database..." : "✓ Survey Completed"}
        </button>
      </div>
    </div>
  );
}

export default SurveyPage;  
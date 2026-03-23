import React from "react";
import { Link } from "react-router-dom";
import "../styles.css";

function HomePage() {
  return (
    <div className="home-wrapper">
      <div className="home-card">
        <h2 className="home-title">Welcome!</h2>
        <p className="home-subtitle">Click below to submit your survey.</p>

        <Link to="/survey" className="survey-btn">
          Submit Survey
        </Link>
      </div>
    </div>
  );
}

export default HomePage;
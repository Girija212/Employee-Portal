const BASE = "http://localhost:4000/auth";

export const loginUser = (empId, password) => {
  return fetch(`${BASE}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ empId, password })
  }).then(res => res.text());
};

export const forgotPassword = (empId) => {
  return fetch(`${BASE}/forgot`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ empId })
  }).then(res => res.text());
};

export const resetPassword = (empId, newPassword) => {
  return fetch(`${BASE}/reset`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ empId, newPassword })
  }).then(res => res.text());
};

const SURVEY_BASE = "http://localhost:5000/survey";


export const checkSurveyStatus = (empId) => {
  return fetch(`${SURVEY_BASE}/status/${empId}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" }
  }).then(res => res.json());
};

export const markSurveySubmitted = (empId) => {
  return fetch(`${SURVEY_BASE}/mark-submitted`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ empId })
  }).then(res => res.text());
};
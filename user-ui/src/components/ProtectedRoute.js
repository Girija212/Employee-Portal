import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ element }) => {
  const empId = localStorage.getItem('empId');

  // If user is logged in (empId exists), show the component
  if (empId) {
    return element;
  }

  // If user is not logged in, redirect to unauthorized page
  return <Navigate to="/unauthorized" replace />;
};

export default ProtectedRoute;

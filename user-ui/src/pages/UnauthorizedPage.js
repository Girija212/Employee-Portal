import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles.css';

const UnauthorizedPage = () => {
  const navigate = useNavigate();

  return (
    <div className="error-container" style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      height: '100vh',
      backgroundColor: '#f8f9fa',
      flexDirection: 'column',
    }}>
      <div style={{
        backgroundColor: 'white',
        padding: '40px',
        borderRadius: '8px',
        boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
        textAlign: 'center',
        maxWidth: '500px',
      }}>
        <h1 style={{ color: '#d32f2f', fontSize: '48px', margin: '0 0 20px 0' }}>
          🔒 401
        </h1>
        <h2 style={{ color: '#333', margin: '0 0 15px 0' }}>
          Authentication Error
        </h2>
        <p style={{ color: '#666', fontSize: '16px', lineHeight: '1.6', margin: '0 0 30px 0' }}>
          You are not authorized to access this page. Please log in with your credentials to continue.
        </p>
        
        <button 
          onClick={() => navigate('/')}
          style={{
            backgroundColor: '#0066ff',
            color: 'white',
            border: 'none',
            padding: '12px 30px',
            fontSize: '16px',
            borderRadius: '4px',
            cursor: 'pointer',
            transition: 'background-color 0.3s'
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = '#0052cc'}
          onMouseLeave={(e) => e.target.style.backgroundColor = '#0066ff'}
        >
          Go to Login
        </button>
      </div>
    </div>
  );
};

export default UnauthorizedPage;

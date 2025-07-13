import { Navigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const ProtectedRoute = ({ children, role }) => {
  const token = localStorage.getItem('token');

  if (!token) {
    // No token, redirect to login
    return <Navigate to="/login" replace />;
  }

  try {
    const decoded = jwtDecode(token);
    if (!decoded.role || decoded.role.toUpperCase() !== role.toUpperCase()) {
      // Role mismatch, show Access Denied message
      return (
        <div style={{ padding: '2em', textAlign: 'center', color: 'white' }}>
          <h2>Access Denied</h2>
          <p>You do not have permission to view this page.</p>
        </div>
      );
    }
  } catch (error) {
    // Invalid token, redirect to login
    return <Navigate to="/login" replace />;
  }

  // Authorized, render children
  return children;
};

export default ProtectedRoute;

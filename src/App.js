import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from 'semantic-ui-react';

import Navbar from './components/common/Navbar';
import Footer from './components/common/Footer';
import HomePage from './components/common/HomePage';
import ProtectedRoute from './components/common/ProtectedRoute';

import Login from './components/auth/Login';
import Register from './components/auth/Register';

import UserDashboard from './components/user/UserDashboard';
import SeatBooking from './components/user/SeatBooking';
import Payment from './components/user/Payment';
import BookingHistory from './components/user/BookingHistory';

import ManageBus from './components/busOperator/ManageBus';
import ManageRoute from './components/busOperator/ManageRoute';

import ManageUsers from './components/admin/ManageUsers';
import ManageBuses from './components/admin/ManageBuses';
import ManageRoutes from './components/admin/ManageRoutes';

import './App.css';
import BusSearch from './components/user/BusSearch';
import ViewBooking from './components/busOperator/ViewBooking';
import Booking from './components/user/Booking';
import ForgotPassword from './components/auth/ForgotPassword';

const App = () => {
  return (
    <Router>
      <Navbar />
      <Container fluid style={{ marginTop: '7em', minHeight: '80vh' }}>
        <Routes>
          {/* Public Routes */}
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />

          {/* User Routes */}
          <Route path="/user/dashboard" element={<ProtectedRoute role="USER"><UserDashboard /></ProtectedRoute>} />
          <Route path="/user/book" element={<ProtectedRoute role="USER"><SeatBooking /></ProtectedRoute>} />
          <Route path="/user/payment" element={<ProtectedRoute role="USER"><Payment /></ProtectedRoute>} />
          <Route path="/user/history" element={<ProtectedRoute role="USER"><BookingHistory /></ProtectedRoute>} />
          <Route path="/user/search" element={<ProtectedRoute role="USER"><BusSearch /></ProtectedRoute>} />
          <Route path="/user/booking" element={<ProtectedRoute role="USER"><Booking/></ProtectedRoute>} />

          {/* Bus Operator Routes */}
          <Route path="/operator/manage-bus" element={<ProtectedRoute role="BUS_OPERATOR"><ManageBus /></ProtectedRoute>} />
          <Route path="/operator/manage-route" element={<ProtectedRoute role="BUS_OPERATOR"><ManageRoute /></ProtectedRoute>} />
          <Route path="/operator/bookings" element={<ProtectedRoute role="BUS_OPERATOR"><ViewBooking /></ProtectedRoute>} />

          {/* Admin Routes */}
          <Route path="/admin/manage-users" element={<ProtectedRoute role="ADMIN"><ManageUsers /></ProtectedRoute>} />
          <Route path="/admin/manage-buses" element={<ProtectedRoute role="ADMIN"><ManageBuses /></ProtectedRoute>} />
          <Route path="/admin/manage-routes" element={<ProtectedRoute role="ADMIN"><ManageRoutes /></ProtectedRoute>} />
        </Routes>
      </Container>
      <Footer />
    </Router>
  );
};

export default App;
        
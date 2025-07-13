import React, { useState } from 'react';
import { Form, Button, Segment, Header, Icon, Message, Divider } from 'semantic-ui-react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const Login = () => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e, { name, value }) => {
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async () => {
    setLoading(true);
    setError('');
    try {
      const res = await axios.post('http://localhost:9000/api/auth/login', formData);
      const token = res.data.replace('Bearer ', '');
      localStorage.setItem('token', token);
      const decoded = jwtDecode(token);
      const role = decoded.role;

      // Redirect based on role
      if (role === 'USER') navigate('/user/dashboard');
      else if (role === 'BUS_OPERATOR') navigate('/operator/manage-bus');
      else if (role === 'ADMIN') navigate('/admin/manage-users');
    } catch (err) {
      setError(err.response?.data || 'Login failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Segment padded="very" stacked style={{ maxWidth: 500, margin: 'auto', marginTop: '5em', background: '#f5e6fa' }}>
      <Header as="h2" icon textAlign="center" style={{ color: '#6B008C' }}>
        <Icon name="sign-in" />
        Login to FastX
      </Header>

      <Form onSubmit={handleSubmit} loading={loading} error={!!error}>
        <Form.Input
          label="Email"
          icon="mail"
          iconPosition="left"
          placeholder="Enter your email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <Form.Input
          label="Password"
          icon="lock"
          iconPosition="left"
          placeholder="Enter your password"
          name="password"
          type="password"
          value={formData.password}
          onChange={handleChange}
          required
        />

        <Message error content={error} />

        <Button color="purple" fluid>Login</Button>

        <div style={{ marginTop: '1em', textAlign: 'center' }}>
          <Button color="google plus" icon="google" content="Sign in with Google" disabled />
        </div>

        <Divider />

        <div style={{ textAlign: 'center' }}>
          <span style={{color:'grey'}}>Don't have an account?</span>
          <Button basic color="purple" size="tiny" onClick={() => navigate('/register')} style={{ marginLeft: '5px' }}>
            Register Now
          </Button>
        </div>

        <div style={{ textAlign: 'center', marginTop: '1em' }}>
          <Button basic color="pink" size="tiny" onClick={() => navigate('/forgot-password')}>
            Forgot Password?
          </Button>
        </div>
      </Form>
    </Segment>
  );
};

export default Login;

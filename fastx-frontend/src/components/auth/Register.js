// src/components/auth/Register.js
import React, { useState } from 'react';
import {
  Form,
  Button,
  Segment,
  Header,
  Icon,
  Message,
  Dropdown,
} from 'semantic-ui-react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const roleOptions = [
  { key: 'user', value: 'passenger', text: 'User' },
  { key: 'operator', value: 'bus operator', text: 'Bus Operator' },
  { key: 'admin', value: 'admin', text: 'Admin' },
];

const genderOptions = [
  { key: 'm', value: 'Male', text: 'Male' },
  { key: 'f', value: 'Female', text: 'Female' },
  { key: 'o', value: 'Other', text: 'Other' },
];

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    gender: '',
    email: '',
    contactNumber: '',
    password: '',
    role: '',
  });

  const [loading, setLoading] = useState(false);
  const [successMsg, setSuccessMsg] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e, { name, value }) => {
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async () => {
    setLoading(true);
    setError('');
    setSuccessMsg('');
    try {
      const res = await axios.post('http://localhost:9000/api/users/register', formData);
      setSuccessMsg(res.data);
      setFormData({
        name: '',
        gender: '',
        email: '',
        contactNumber: '',
        password: '',
        role: '',
      });
    } catch (err) {
      setError(err.response?.data || 'Registration failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Segment stacked padded="very" style={{ maxWidth: 600, margin: '5em auto', background: '#f8e6ff' }}>
      <Header as="h2" icon textAlign="center" style={{ color: '#6B008C' }}>
        <Icon name="signup" />
        Register New Account
      </Header>

      <Form onSubmit={handleSubmit} loading={loading} success={!!successMsg} error={!!error}>
        <Form.Input
          label="Full Name"
          placeholder="Enter your full name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <Form.Field>
          <label>Gender</label>
          <Dropdown
            placeholder="Select Gender"
            fluid
            selection
            options={genderOptions}
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            required
          />
        </Form.Field>
        <Form.Input
          label="Email"
          name="email"
          placeholder="Enter your email"
          value={formData.email}
          onChange={handleChange}
          icon="mail"
          iconPosition="left"
          required
          type="email"
        />
        <Form.Input
          label="Mobile Number"
          name="contactNumber"
          placeholder="Enter mobile number"
          value={formData.contactNumber}
          onChange={handleChange}
          icon="phone"
          iconPosition="left"
          required
        />
        <Form.Input
          label="Password"
          name="password"
          type="password"
          placeholder="Create password"
          value={formData.password}
          onChange={handleChange}
          icon="lock"
          iconPosition="left"
          required
        />
        <Form.Field>
          <label>Role</label>
          <Dropdown
            placeholder="Select Role"
            fluid
            selection
            options={roleOptions}
            name="role"
            value={formData.role}
            onChange={handleChange}
            required
          />
        </Form.Field>

        <Message success content={successMsg} />
        <Message error content={error} />

        <Button color="purple" fluid>Register</Button>

        <div style={{ textAlign: 'center', marginTop: '1em' }}>
          Already have an account?{' '}
          <Button basic color="purple" size="tiny" onClick={() => navigate('/login')}>
            Login
          </Button>
        </div>
      </Form>
    </Segment>
  );
};

export default Register;

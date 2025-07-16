import React, { useState } from 'react';
import { Form, Button, Segment, Header, Icon, Message } from 'semantic-ui-react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ForgotPassword = () => {
  const [formData, setFormData] = useState({ email: '', newPassword: '' });
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e, { name, value }) => {
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async () => {
    setLoading(true);
    setSuccess('');
    setError('');

    try {
      const response = await axios.post('http://localhost:9000/api/users/forgot-password', formData);
      setSuccess(response.data);
      setFormData({ email: '', newPassword: '' });
    } catch (err) {
      setError(err.response?.data || 'Failed to reset password');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Segment padded="very" stacked style={{ maxWidth: 500, margin: 'auto', marginTop: '5em', background: '#f5e6fa' }}>
      <Header as="h2" icon textAlign="center" style={{ color: '#6B008C' }}>
        <Icon name="unlock alternate" />
        Forgot Password
      </Header>

      <Form onSubmit={handleSubmit} loading={loading} success={!!success} error={!!error}>
        <Form.Input
          label="Email"
          icon="mail"
          iconPosition="left"
          placeholder="Enter your registered email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <Form.Input
          label="New Password"
          icon="lock"
          iconPosition="left"
          placeholder="Enter new password"
          type="password"
          name="newPassword"
          value={formData.newPassword}
          onChange={handleChange}
          required
        />

        <Message success content={success} />
        <Message error content={error} />

        <Button fluid color="purple">Reset Password</Button>

        <div style={{ textAlign: 'center', marginTop: '1em' }}>
          <Button basic color="blue" size="tiny" onClick={() => navigate('/login')}>
            Back to Login
          </Button>
        </div>
      </Form>
    </Segment>
  );
};

export default ForgotPassword;

import { Card, Row, Col, Typography } from 'antd';
import { CheckCircleOutlined, ClockCircleOutlined, SafetyCertificateOutlined } from '@ant-design/icons';

const { Title } = Typography;

const features = [
  {
    title: 'Easy Booking',
    description: 'Smooth and user-friendly interface to search and book buses in seconds.',
    icon: <CheckCircleOutlined style={{ fontSize: '2rem', color: '#bb6ae0' }} />
  },
  {
    title: 'On-Time Guarantee',
    description: 'We ensure punctual schedules and fast updates on delays.',
    icon: <ClockCircleOutlined style={{ fontSize: '2rem', color: '#bb6ae0' }} />
  },
  {
    title: 'Secure Payments',
    description: 'Payments are encrypted and 100% secure using trusted gateways.',
    icon: <SafetyCertificateOutlined style={{ fontSize: '2rem', color: '#bb6ae0' }} />
  }
];

const WhyChooseUs = () => (
  <div style={{ marginTop: '4rem', textAlign: 'center' }}>
    <Title level={2} style={{ color: '#f3e6ff', marginBottom: '2rem' }}>Why Choose FastX?</Title>
    <Row justify="center" gutter={[30, 30]}>
      {features.map((feat, idx) => (
        <Col xs={24} sm={12} md={6} key={idx}>
          <Card
            bordered={false}
            hoverable
            style={{
              borderRadius: '16px',
              background: 'rgba(255, 255, 255, 0.05)',
              color: '#fff',
              backdropFilter: 'blur(10px)',
              minHeight: '220px',
              boxShadow: '0 6px 20px rgba(0,0,0,0.2)',
              transition: '0.3s all'
            }}
          >
            <div style={{ marginBottom: '1rem' }}>{feat.icon}</div>
            <Title level={4} style={{ color: '#e3c3ff' }}>{feat.title}</Title>
            <p style={{ color: '#eee', fontSize: '0.95rem' }}>{feat.description}</p>
          </Card>
        </Col>
      ))}
    </Row>
  </div>
);

export default WhyChooseUs;

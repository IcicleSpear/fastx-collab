import React from "react";
import { Card, Icon, Grid, Header, Container, Segment } from "semantic-ui-react";
import { useNavigate } from "react-router-dom";

const AdminDashboard = () => {
  const navigate = useNavigate();

  const sections = [
    { title: "Manage Users", icon: "users", path: "/admin/manage-users" },
    { title: "Manage Buses", icon: "bus", path: "/admin/manage-buses" },
    { title: "Manage Routes", icon: "road", path: "/admin/manage-routes" },
    { title: "Manage Amenities", icon: "list", path: "/admin/manage-amenities" },
    { title: "View Bookings", icon: "ticket", path: "/admin/manage-bookings" },
  ];

  return (
    <div
      style={{
        background: "linear-gradient(to bottom right, #1e0033, #3A004F)",
        minHeight: "100vh",
        padding: "4rem 0",
      }}
    >
      <Container>
        <Segment
          raised
          style={{
            borderRadius: "12px",
            background: "linear-gradient(135deg, #f5f0fa, #d9c7f7)",
            padding: "3rem",
            boxShadow: "0 0 15px rgba(0, 0, 0, 0.2)",
          }}
        >
          <Header
            as="h2"
            textAlign="center"
            style={{
              color: "#4a007b",
              fontWeight: "bold",
              marginBottom: "2rem",
              fontSize: "2.5rem",
            }}
          >
            👋 Welcome, Admin
          </Header>

          <Grid columns={3} stackable centered>
            {sections.map((section, idx) => (
              <Grid.Column key={idx} style={{ display: "flex", justifyContent: "center" }}>
                <Card
                  onClick={() => navigate(section.path)}
                  style={{
                    cursor: "pointer",
                    backgroundColor: "#fff",
                    borderRadius: "10px",
                    padding: "1.5rem",
                    minWidth: "200px",
                    maxWidth: "220px",
                    textAlign: "center",
                    transition: "transform 0.2s ease, box-shadow 0.2s",
                    boxShadow: "0 4px 10px rgba(0,0,0,0.15)",
                  }}
                  onMouseEnter={(e) => {
                    e.currentTarget.style.transform = "scale(1.05)";
                    e.currentTarget.style.boxShadow = "0 6px 15px rgba(0,0,0,0.2)";
                  }}
                  onMouseLeave={(e) => {
                    e.currentTarget.style.transform = "scale(1)";
                    e.currentTarget.style.boxShadow = "0 4px 10px rgba(0,0,0,0.15)";
                  }}
                >
                  <div style={{ marginBottom: "10px", display: "flex", justifyContent: "center" }}>
                    <Icon name={section.icon} size="huge" color="violet" />
                  </div>

                  <Card.Content>
                    <Card.Header
                      style={{
                        fontSize: "1.2rem",
                        fontWeight: "bold",
                        color: "#4a007b",
                      }}
                    >
                      {section.title}
                    </Card.Header>
                  </Card.Content>
                </Card>
              </Grid.Column>
            ))}
          </Grid>
        </Segment>
      </Container>
    </div>
  );
};

export default AdminDashboard;

import React, { useEffect, useState } from "react";
import {
  Segment,
  Header,
  Table,
  Button,
  Icon,
  Label,
  Modal,
  Form,
  Message,
  Dropdown,
} from "semantic-ui-react";
import axios from "axios";

const statusOptions = [
  { key: "all", text: "All", value: "" },
  { key: "confirmed", text: "Confirmed", value: "CONFIRMED" },
  { key: "cancelled", text: "Cancelled", value: "CANCELLED" },
];

const ManageBookings = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedBooking, setSelectedBooking] = useState(null);

  // Filters
  const [filterUserId, setFilterUserId] = useState("");
  const [filterDate, setFilterDate] = useState("");
  const [filterTicket, setFilterTicket] = useState("");
  const [filterStatus, setFilterStatus] = useState("");

  const token = localStorage.getItem("token");

  // Helper to fetch bookings with optional filters
  const fetchFilteredBookings = async () => {
    setLoading(true);
    setError("");

    try {
      // Priority order: ticket -> userId -> date -> status -> all
      if (filterTicket.trim()) {
        // Fetch by ticket number
        const res = await axios.get(
          `http://localhost:9000/api/bookings/ticket/${filterTicket.trim()}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setBookings(res.data ? [res.data] : []);
      } else if (filterUserId.trim()) {
        // Fetch by user ID
        const res = await axios.get(
          `http://localhost:9000/api/bookings/user/${filterUserId.trim()}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setBookings(res.data);
      } else if (filterDate.trim()) {
        // Fetch by date
        const res = await axios.get(
          `http://localhost:9000/api/bookings/date?date=${filterDate.trim()}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setBookings(res.data);
      } else if (filterStatus) {
        // Fetch by status
        const res = await axios.get(
          `http://localhost:9000/api/bookings/status?status=${filterStatus}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setBookings(res.data);
      } else {
        // Fetch all
        const res = await axios.get("http://localhost:9000/api/bookings", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setBookings(res.data);
      }
    } catch (err) {
      setError("Failed to fetch bookings with filters.");
      setBookings([]);
    } finally {
      setLoading(false);
    }
  };

  const handleFilter = (e) => {
    e.preventDefault();
    fetchFilteredBookings();
  };

  const cancelBooking = async (bookingId) => {
    if (
      !window.confirm(
        `Are you sure you want to cancel booking #${bookingId}? This action cannot be undone.`
      )
    )
      return;

    try {
      await axios.delete(`http://localhost:9000/api/bookings/${bookingId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setBookings((prev) =>
        prev.map((b) =>
          b.bookingId === bookingId ? { ...b, status: "CANCELLED" } : b
        )
      );
      alert("Booking cancelled successfully.");
    } catch (err) {
      alert("Failed to cancel booking.");
    }
  };

  const formatDateTime = (dtString) =>
    dtString ? new Date(dtString).toLocaleString() : "";

  const openModal = (booking) => {
    setSelectedBooking(booking);
    setModalOpen(true);
  };

  useEffect(() => {
    fetchFilteredBookings();
    // eslint-disable-next-line
  }, []);

  return (
    <Segment
      style={{
        background: "#f9f7fd",
        minHeight: "80vh",
        padding: "2rem 3rem",
        boxShadow: "0 0 15px rgb(106 13 173 / 0.15)",
        borderRadius: "10px",
      }}
    >
      <Header
        as="h2"
        icon
        textAlign="center"
        style={{ color: "#6a0dad", marginBottom: "1.5rem" }}
      >
        <Icon name="clipboard list" />
        Manage Bookings
      </Header>

      {/* Filter Form */}
      <Form onSubmit={handleFilter} style={{ marginBottom: "2rem" }}>
        <Form.Group widths="equal">
          <Form.Input
            label="Filter by User ID"
            placeholder="User ID"
            value={filterUserId}
            onChange={(e) => setFilterUserId(e.target.value)}
            type="number"
            min="1"
          />
          <Form.Input
            label="Filter by Date"
            placeholder="YYYY-MM-DD"
            type="date"
            value={filterDate}
            onChange={(e) => setFilterDate(e.target.value)}
          />
          <Form.Input
            label="Filter by Ticket Number"
            placeholder="Ticket Number"
            value={filterTicket}
            onChange={(e) => setFilterTicket(e.target.value)}
          />
          <Form.Field>
            <label>Status</label>
            <Dropdown
              placeholder="Select Status"
              fluid
              selection
              options={statusOptions}
              value={filterStatus}
              onChange={(e, { value }) => setFilterStatus(value)}
            />
          </Form.Field>
        </Form.Group>
        <Button color="purple" type="submit" icon labelPosition="left">
          <Icon name="filter" />
          Apply Filters
        </Button>
        <Button
          type="button"
          onClick={() => {
            setFilterUserId("");
            setFilterDate("");
            setFilterTicket("");
            setFilterStatus("");
            fetchFilteredBookings();
          }}
          basic
          color="grey"
          style={{ marginLeft: "1em" }}
        >
          Clear Filters
        </Button>
      </Form>

      {error && (
        <Message negative>
          <Message.Header>Error</Message.Header>
          <p>{error}</p>
        </Message>
      )}

      <Table celled striped compact selectable stackable>
        <Table.Header>
          <Table.Row style={{ backgroundColor: "#e3d7f5" }}>
            <Table.HeaderCell>#</Table.HeaderCell>
            <Table.HeaderCell>Booking ID</Table.HeaderCell>
            <Table.HeaderCell>User ID</Table.HeaderCell>
            <Table.HeaderCell>Route ID</Table.HeaderCell>
            <Table.HeaderCell>Bus ID</Table.HeaderCell>
            <Table.HeaderCell>Seats</Table.HeaderCell>
            <Table.HeaderCell>Booking Time</Table.HeaderCell>
            <Table.HeaderCell>Total Amount (₹)</Table.HeaderCell>
            <Table.HeaderCell>Ticket Number</Table.HeaderCell>
            <Table.HeaderCell>Status</Table.HeaderCell>
            <Table.HeaderCell textAlign="center">Actions</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {loading ? (
            <Table.Row>
              <Table.Cell colSpan="11" textAlign="center">
                Loading...
              </Table.Cell>
            </Table.Row>
          ) : bookings.length === 0 ? (
            <Table.Row>
              <Table.Cell colSpan="11" textAlign="center">
                No bookings found.
              </Table.Cell>
            </Table.Row>
          ) : (
            bookings.map((b, idx) => (
              <Table.Row key={b.bookingId}>
                <Table.Cell>{idx + 1}</Table.Cell>
                <Table.Cell>{b.bookingId}</Table.Cell>
                <Table.Cell>{b.userId}</Table.Cell>
                <Table.Cell>{b.routeId}</Table.Cell>
                <Table.Cell>{b.busId}</Table.Cell>
                <Table.Cell>{b.seatIds?.join(", ") || "-"}</Table.Cell>
                <Table.Cell>{formatDateTime(b.bookingTime)}</Table.Cell>
                <Table.Cell>{b.totalamount}</Table.Cell>
                <Table.Cell>{b.ticketNumber}</Table.Cell>
                <Table.Cell>
                  <Label
                    color={b.status === "CONFIRMED" ? "green" : "red"}
                    horizontal
                  >
                    {b.status}
                  </Label>
                </Table.Cell>
                <Table.Cell textAlign="center">
                  <Button
                    icon="eye"
                    size="small"
                    color="purple"
                    title="View Details"
                    onClick={() => openModal(b)}
                    style={{ marginRight: "0.5em" }}
                  />
                  {b.status !== "CANCELLED" && (
                    <Button
                      icon="cancel"
                      size="small"
                      color="red"
                      title="Cancel Booking"
                      onClick={() => cancelBooking(b.bookingId)}
                    />
                  )}
                </Table.Cell>
              </Table.Row>
            ))
          )}
        </Table.Body>
      </Table>

      {/* Modal to show full booking details */}
      <Modal
        open={modalOpen}
        onClose={() => setModalOpen(false)}
        size="small"
        closeIcon
      >
        <Header icon="info circle" content="Booking Details" />
        <Modal.Content>
          {selectedBooking ? (
            <div style={{ lineHeight: "1.6", fontSize: "1rem" }}>
              <p>
                <strong>Booking ID:</strong> {selectedBooking.bookingId}
              </p>
              <p>
                <strong>User ID:</strong> {selectedBooking.userId}
              </p>
              <p>
                <strong>Route ID:</strong> {selectedBooking.routeId}
              </p>
              <p>
                <strong>Bus ID:</strong> {selectedBooking.busId}
              </p>
              <p>
                <strong>Seat IDs:</strong>{" "}
                {selectedBooking.seatIds?.join(", ") || "-"}
              </p>
              <p>
                <strong>Booking Time:</strong>{" "}
                {formatDateTime(selectedBooking.bookingTime)}
              </p>
              <p>
                <strong>Total Amount:</strong> ₹{selectedBooking.totalamount}
              </p>
              <p>
                <strong>Ticket Number:</strong> {selectedBooking.ticketNumber}
              </p>
              <p>
                <strong>Status:</strong>{" "}
                <Label
                  color={
                    selectedBooking.status === "CONFIRMED" ? "green" : "red"
                  }
                  horizontal
                >
                  {selectedBooking.status}
                </Label>
              </p>
            </div>
          ) : (
            <p>No booking selected.</p>
          )}
        </Modal.Content>
      </Modal>
    </Segment>
  );
};

export default ManageBookings;

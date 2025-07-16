import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  Table,
  Dropdown,
  Header,
  Button,
  Icon,
  Segment,
  Loader,
  Modal,
  Form,
  Input,
  Confirm,
} from "semantic-ui-react";

const roleOptions = [
  { key: "all", text: "All Users", value: "all" },
  { key: "admin", text: "Admin", value: "admin" },
  { key: "passenger", text: "Passenger", value: "passenger" },
  { key: "operator", text: "Bus Operator", value: "bus operator" },
];

const ManageUsers = () => {
  const [users, setUsers] = useState([]);
  const [filterRole, setFilterRole] = useState("all");
  const [loading, setLoading] = useState(false);

  const [showAdd, setShowAdd] = useState(false);
  const [showEdit, setShowEdit] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);
  const [deleteConfirm, setDeleteConfirm] = useState({ open: false, userId: null });

  const [formData, setFormData] = useState({ name: "", email: "", role: "", password: "", gender:"", contactNumber:"" });

  const token = localStorage.getItem("token");

  const fetchUsers = async (role) => {
    setLoading(true);
    try {
      const url = role === "all"
        ? "http://localhost:9000/api/users/getAllUsers"
        : `http://localhost:9000/api/users/role/${role}`;
      const res = await axios.get(url, { headers: { Authorization: `Bearer ${token}` } });
      setUsers(res.data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers("all");
  }, []);

  const openEdit = u => {
    setSelectedUser(u);
    setFormData({ name: u.name, email: u.email, role: u.role });
    setShowEdit(true);
  };

  const handleSubmit = async () => {
    try {
      const url = showAdd
        ? "http://localhost:9000/api/users/register"
        : `http://localhost:9000/api/users/${selectedUser.userId}`;
      const method = showAdd ? "post" : "put";
      const payload = showAdd ? formData : { ...selectedUser, ...formData };

      await axios[method](url, payload, { headers: { Authorization: `Bearer ${token}` } });
      fetchUsers(filterRole);
      setShowAdd(false);
      setShowEdit(false);
      setFormData({});
      setSelectedUser(null);
    } catch (err) {
      console.error(err);
      alert("Operation failed");
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:9000/api/users/${deleteConfirm.userId}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      fetchUsers(filterRole);
    } catch (err) {
      console.error(err);
      alert("Deletion failed");
    } finally {
      setDeleteConfirm({ open: false, userId: null });
    }
  };

  return (
    <div style={{
      padding: "2rem",
      maxWidth: "1200px",
      margin: "0 auto",
      background: "linear-gradient(135deg, #f5f0fa, #d9c7f7)",
      minHeight: "100vh"
    }}>
      <Header as="h2" textAlign="center" style={{ color: "#4a007b", fontWeight: "bold" }}>
        <Icon name="users" /> Manage Users
      </Header>

      <Segment style={{ padding: "1rem", borderRadius: "8px",background: "linear-gradient(135deg, #f5f0fa, #d9c7f7)"}}>
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center"}}>
          <Dropdown
            selection
            options={roleOptions}
            value={filterRole}
            onChange={(e, { value }) => {
              setFilterRole(value);
              fetchUsers(value);
            }}
            style={{ minWidth: "160px", fontSize: "1em",fontWeight: "bold",height: "60px",padding: "4px 8px",background: "#bb6ae0",color: "white"}}
          />
          <Button style={{background: "#bb6ae0",color: "white"}} onClick={() => { setFilterRole("all"); fetchUsers("all"); }}>
            Clear Filter
          </Button>
          <Button style={{background: "#bb6ae0",color: "white"}}  onClick={() => { setShowAdd(true); setFormData({}); }}>
            <Icon name="add user" /> Add User
          </Button>
        </div>

        {loading ? (
          <Loader active content="Fetching users..." />
        ) : (
          <Table celled striped compact style={{ marginTop: "1rem"}}>
            <Table.Header>
              <Table.Row>
                <Table.HeaderCell>#</Table.HeaderCell>
                <Table.HeaderCell>Name</Table.HeaderCell>
                <Table.HeaderCell>Email</Table.HeaderCell>
                <Table.HeaderCell>Role</Table.HeaderCell>
                <Table.HeaderCell textAlign="center">Actions</Table.HeaderCell>
              </Table.Row>
            </Table.Header>
            <Table.Body>
              {users.map((u, i) => (
                <Table.Row key={u.userId}>
                  <Table.Cell>{i + 1}</Table.Cell>
                  <Table.Cell>{u.name}</Table.Cell>
                  <Table.Cell>{u.email}</Table.Cell>
                  <Table.Cell>{u.role}</Table.Cell>
                  <Table.Cell textAlign="center">
                    <Button icon color="green" onClick={() => openEdit(u)}>
                      <Icon name="edit" />
                    </Button>
                    <Button icon color="red" onClick={() => setDeleteConfirm({ open: true, userId: u.userId })}>
                      <Icon name="trash alternate" />
                    </Button>
                  </Table.Cell>
                </Table.Row>
              ))}
            </Table.Body>
          </Table>
        )}
      </Segment>

      {/* Add/Edit Modal */}
      <Modal open={showAdd || showEdit} onClose={() => {setShowAdd(false); setShowEdit(false);}}>
        <Modal.Header>{showAdd ? "Add User" : "Edit User"}</Modal.Header>
        <Modal.Content>
          <Form>
            <Form.Field>
              <label>Name</label>
              <Input
                value={formData.name || ""}
                onChange={(_, { value }) => setFormData(prev => ({ ...prev, name: value }))}
              />
            </Form.Field>
            <Form.Field>
              <label>Email</label>
              <Input
                type="email"
                value={formData.email || ""}
                onChange={(_, { value }) => setFormData(prev => ({ ...prev, email: value }))}
              />
            </Form.Field>
            <Form.Field>
              <label>Role</label>
              <Dropdown
                selection
                options={roleOptions.slice(1)} // no 'all'
                value={formData.role || ""}
                onChange={(_, { value }) => setFormData(prev => ({ ...prev, role: value }))}
                style={{ fontSize: "0.9em"}}
              />
            </Form.Field>
            {showAdd && (
              <>
                <Form.Field>
                  <label>Password</label>
                  <Input
                    type="password"
                    value={formData.password || ""}
                    onChange={(_, { value }) => setFormData(prev => ({ ...prev, password: value }))}
                  />
                </Form.Field>
                <Form.Field>
                  <label>Gender</label>
                  <Dropdown
                    fluid selection
                    options={[
                      { key: 'm', text: 'Male', value: 'Male' },
                      { key: 'f', text: 'Female', value: 'Female' },
                      { key: 'o', text: 'Other', value: 'Other' }
                    ]}
                    value={formData.gender || ""}
                    onChange={(_, { value }) => setFormData(prev => ({ ...prev, gender: value }))}
                    style={{ fontSize: "0.9em" }}
                  />
                </Form.Field>
                <Form.Field>
                  <label>Contact Number</label>
                  <Input
                    value={formData.contactNumber || ""}
                    onChange={(_, { value }) => setFormData(prev => ({ ...prev, contactNumber: value }))}
                  />
                </Form.Field>
              </>
            )}
          </Form>
        </Modal.Content>
        <Modal.Actions>
          <Button onClick={() => { setShowAdd(false); setShowEdit(false); }}>
            Cancel
          </Button>
          <Button positive onClick={handleSubmit}>
            {showAdd ? "Add" : "Update"}
          </Button>
        </Modal.Actions>
      </Modal>

      {/* Delete Confirmation */}
      <Confirm
        open={deleteConfirm.open}
        header="Delete User"
        content="Are you sure you want to delete this user?"
        onCancel={() => setDeleteConfirm({ open: false, userId: null })}
        onConfirm={handleDelete}
      />
    </div>
  );
};

export default ManageUsers;

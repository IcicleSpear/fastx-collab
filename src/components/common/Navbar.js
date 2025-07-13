import { useNavigate, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import { Menu, Container, Button } from "semantic-ui-react";
import { jwtDecode } from 'jwt-decode';
import './Navbar.css';

const Navbar = () => {
    const [role, setRole] = useState(null);
    const navigate = useNavigate();
    const location = useLocation(); // Track route changes

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            try {
                const decoded = jwtDecode(token);
                setRole(decoded.role);
            } catch (err) {
                console.log("Invalid token");
                setRole(null); // In case token is invalid
            }
        } else {
            setRole(null); // No token means logout
        }
    }, [location]); // Run this effect on route change

    const logout = () => {
        localStorage.removeItem("token");
        navigate("/");
        setRole(null);
    };

    return (
        <Menu className="navbar-gradient" stackable>
            <Container fluid>
                <Menu.Item
                    header
                    className="navbar-header no-hover"
                    onClick={() => navigate("/")}
                >
                    🚍 FastX - Online Bus Booking
                </Menu.Item>

                <Menu.Menu position="right">
                    {!role && (
                        <>
                            <Menu.Item name="Login" className="navbar-item" onClick={() => navigate("/login")} />
                            <Menu.Item name="Register" className="navbar-item" onClick={() => navigate("/register")} />
                        </>
                    )}

                    {role === "USER" && (
                        <>
                            <Menu.Item name="Dashboard" className="navbar-item" onClick={() => navigate("/user/dashboard")} />
                            <Menu.Item name="Book Seats" className="navbar-item" onClick={() => navigate("/user/book")} />
                            <Menu.Item name="My Bookings" className="navbar-item" onClick={() => navigate("/user/history")} />
                            <Menu.Item name="Payment" className="navbar-item" onClick={() => navigate("/user/payment")} />
                        </>
                    )}

                    {role === "BUS_OPERATOR" && (
                        <>
                            <Menu.Item name="Manage Buses" className="navbar-item" onClick={() => navigate("/operator/manage-bus")} />
                            <Menu.Item name="Manage Routes" className="navbar-item" onClick={() => navigate("/operator/manage-route")} />
                            <Menu.Item name="View Bookings" className="navbar-item" onClick={() => navigate("/operator/bookings")} />
                        </>
                    )}

                    {role === "ADMIN" && (
                        <>
                            <Menu.Item name="Manage Users" className="navbar-item" onClick={() => navigate("/admin/manage-users")} />
                            <Menu.Item name="Manage Buses" className="navbar-item" onClick={() => navigate("/admin/manage-buses")} />
                            <Menu.Item name="Manage Routes" className="navbar-item" onClick={() => navigate("/admin/manage-routes")} />
                        </>
                    )}

                    {role && (
                        <Menu.Item>
                            <Button color="pink" onClick={logout}>Logout</Button>
                        </Menu.Item>
                    )}
                </Menu.Menu>
            </Container>
        </Menu>
    );
};

export default Navbar;

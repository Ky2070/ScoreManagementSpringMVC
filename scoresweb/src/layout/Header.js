import { useEffect, useState } from "react";
import {Container, Nav, NavDropdown, Navbar, Spinner } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
const Header = () => {
    const [classes, setClasses] = useState(null)

    const loadCates = async () => {
        let res = await Apis.get(endpoints['classes'])
        //Example: server-domain = "http://localhost:8080/WebAppSpringMVC/api/classes"
        // let res = await fetch("SERVER-DOMAIN");
        // let data = await res.json();
        //Lấy dữ liệu sau đó cập nhật state để hiển thị giao diện
        setClasses(res.data);
    }

    useEffect(() => {
        loadCates();
    }, [])

    if(classes === null)
        return <Spinner animation="border" />;
    // test dể render dữ liệu
    return (<>   
     <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">Score Management</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <NavDropdown title="Danh sách lớp" id="basic-nav-dropdown">
              {classes.map(c => <NavDropdown.Item href="#action/3.1"key ={c.id}>{c.name}</NavDropdown.Item>)}
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
    </>)
}
export default Header;
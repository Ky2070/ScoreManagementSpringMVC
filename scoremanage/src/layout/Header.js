import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import ExampleCarouselImage from "../components/ExampleCarouselImage";
import Carousel from 'react-bootstrap/Carousel';
import { Link } from 'react-router-dom';
const Header = () => {
    return (<>
        <Carousel>
            <Carousel.Item>
                <ExampleCarouselImage text="First slide" />
                <Carousel.Caption>
                    <h3>Sân trường</h3>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <ExampleCarouselImage text="Second slide" />
                <Carousel.Caption>
                    <h3>Sân đá banh</h3>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <ExampleCarouselImage text="Third slide" />
                <Carousel.Caption>
                    <h3>Phong cảnh trường</h3>

                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>


        <Navbar expand="lg" className="bg-body-tertiary">
            <Container fluid>
                <Navbar.Brand href="/" style={{ fontSize: '1.2rem', fontWeight: 'bold' }} className="d-flex align-items-center">
                    <img
                        src="https://www.logoground.com/uploads/2017100232017-07-023321359U%20unique%20logo.jpg"
                        alt="Logo"
                        width="70"
                        height="70"
                        className="d-inline-block align-top"
                    />
                    <span className="ms-2">Trường Đại Học Nghĩa Kỳ</span>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: '100px' }} navbarScroll>
                        <Nav.Link href="#action1" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                            Trang chủ
                        </Nav.Link>
                        <Nav.Link href="#action2" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                            Tin tức
                        </Nav.Link>
                        <NavDropdown title="Khoa" id="navbarScrollingDropdown" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                            <NavDropdown.Item href="#action3" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                CNTT
                            </NavDropdown.Item>
                            <NavDropdown.Item href="#action4" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Quản lí hệ thống
                            </NavDropdown.Item>
                            <NavDropdown.Item href="#action5" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Ngôn ngữ Anh
                            </NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link as={Link} to="/login" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                            Đăng nhập/Đăng ký
                        </Nav.Link>
                    </Nav>
                    <Form className="d-flex">
                        <Form.Control
                            type="search"
                            placeholder="Search"
                            className="me-2"
                            aria-label="Search"
                        />
                        <Button variant="outline-success">Search</Button>
                    </Form>

                </Navbar.Collapse>
            </Container>
        </Navbar>
    </>)
}
export default Header; 
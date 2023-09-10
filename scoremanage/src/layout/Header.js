import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import ExampleCarouselImage from "../components/ExampleCarouselImage";
import Carousel from 'react-bootstrap/Carousel';
import { Link } from 'react-router-dom';
import { useContext } from 'react';
import { MyUserContext } from '../App';
import { useSchoolYear } from '../reducers/SchoolYearContext';
const Header = () => {

    const [user, dispatch] = useContext(MyUserContext);
    const { selectedSchoolYearId, setSelectedSchoolYearId } = useSchoolYear();
    const logout = () => {
        dispatch({
            "type": "logout"
        })
        setSelectedSchoolYearId(null);
        localStorage.removeItem('selectedSchoolYearId');
    }

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
                <Navbar.Brand as={Link} to="/" style={{ fontSize: '1.2rem', fontWeight: 'bold' }} className="d-flex align-items-center">
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
                        {selectedSchoolYearId === null ? (
                            // Hiển thị phần "Học kì" chỉ khi selectedSchoolYearId là null
                            <Nav.Link as={Link} to="/schoolyear" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Học kì
                            </Nav.Link>
                        ) : null}
                        {/* <Nav.Link as={Link} to="/listclass" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                            Lớp phụ trách
                        </Nav.Link> */}

                        {user !== null && (user.roleID.id === 2 || user.roleID.id === 1) ? (
                            <Nav.Link as={Link} to="/listclass" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Lớp phụ trách
                            </Nav.Link>
                        ) : null}
                        {user !== null && (user.roleID.id === 3) ? (
                            <Nav.Link as={Link} to="/listoldclass" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Xem môn học
                            </Nav.Link>
                        ) : null}
                        {user !== null && (user.roleID.id === 1 || user.roleID.id === 2 || user.roleID.id === 3 ) ? (
                            <Nav.Link as={Link} to="/chatfirebase" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Chat Online
                            </Nav.Link>
                        ) : null}
                        {user === null ? <>
                            <Nav.Link as={Link} to="/login" style={{ fontSize: '1.2rem', fontWeight: 600 }}>
                                Đăng nhập/Đăng ký
                            </Nav.Link>
                        </> : <>
                            <img src={user.image} alt={user.name} style={{ width: '50px', height: '50px', borderRadius: '50%' }} />
                            <Nav.Link as={Link} to="/" style={{ fontSize: '1.2rem', fontWeight: 600, color: "#dd3f3f" }}>
                                Chào {user.name}!
                            </Nav.Link>
                            <Button variant="secondary" onClick={logout} >Đăng xuất</Button>
                        </>}

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
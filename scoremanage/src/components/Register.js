import React, { useRef, useState } from 'react';
import { Form, Button, Container, Row, Col, Card, Alert } from 'react-bootstrap';
import axios, { endpoints } from '../configs/Apis';
import Apis from '../configs/Apis';
import bcrypt from 'bcryptjs';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [isLoading, setIsLoading] = useState(false);

    const [formData, setFormData] = useState({
        'email': '',
        'password': '',
        'confirmPassword': '',
    });
    const [errors, setErrors] = useState({
        email: '',
        password: '',
        confirmPassword: '',
    });
    const [errorMessage, setErrorMessage] = useState('');

    const validateForm = () => {
        const newErrors = {
            email: '',
            password: '',
            confirmPassword: '',
        };

        // Add validation logic here
        if (!formData.email) {
            newErrors.email = 'Email is required';
        } else if (!isValidEmail(formData.email)) {
            newErrors.email = 'Invalid email format';
        }

        if (!formData.password) {
            newErrors.password = 'Password is required';
        }

        if (formData.password !== formData.confirmPassword) {
            newErrors.confirmPassword = 'Passwords do not match';
        }

        setErrors(newErrors);


        return Object.values(newErrors).every((error) => error === '');
    };

    const isValidEmail = (email) => {
        const emailRegex = /^[A-Za-z0-9._%+-]+@dh\.edu\.com$/;
        return emailRegex.test(email);
    };

    const nav = useNavigate();
    const avatar = useRef();

    // const register = async (e) => {
    //     e.preventDefault();

    //     if (validateForm()) {
    //         try {


    //             const requestData = {
    //                 email: formData.email,
    //                 password: formData.password,
    //                 avatar: ''
    //               };
    //               console.log(requestData);
    //             const response = await Apis.post(endpoints['register'], requestData, {
    //                 headers: {
    //                     'Content-Type': 'application/json', 
    //                 },
    //             });

    //             if (response.status === 200) {
    //                     nav("/login");
    //             } else {
    //                 console.error(response.data);
    //                 console.log(response.status);
    //             }
    //         } catch (error) {
    //             console.error('Error:', error);
    //         }
    //     }
    // };

    // const register = async (e) => {
    //     e.preventDefault();
    //     if (!avatar.current.files.length) {
    //         // Không có tệp nào được chọn
    //         setErrorMessage("Vui lòng chọn tệp hình ảnh đại diện");
    //         return;
    //     }

    //     const file = avatar.current.files[0];
    //     const reader = new FileReader();

    //     reader.onload = async (e) => {
    //         const base64String = e.target.result.replace(/^data:image\/[a-z]+;base64,/, '');

    //         try {
    //             const requestData = {
    //                 email: formData.email,
    //                 password: formData.password,
    //                 avatar: base64String,
    //             };

    //             const response = await Apis.post(endpoints['register'], requestData, {
    //                 headers: {
    //                     'Content-Type': 'application/json',
    //                 },
    //             });

    //             if (response.status === 200) {
    //                 setErrorMessage(response.data);
    //                 nav("/login");
    //                 console.log(response.data);
    //             } else {
    //                 console.error(response.data);
    //                 console.log(response.status);
    //             }
    //         } catch (error) {
    //             if (error.response && error.response.status === 400) {
    //                 // Đăng ký thất bại với lỗi 400
    //                 setErrorMessage(error.response.data);
    //             } else {
    //                 console.error('Error:', error);
    //             }
    //         }
    //     };

    //     reader.readAsDataURL(file);
    // };

    // const register = async (e) => {
    //     e.preventDefault();
    //     if (!avatar.current.files.length) {
    //         // Không có tệp nào được chọn
    //         setErrorMessage("Vui lòng chọn tệp hình ảnh đại diện");
    //         return;
    //     }

    //     const file = avatar.current.files[0];
    //     const reader = new FileReader();

    //     reader.onload = async (e) => {
    //         const base64String = e.target.result.replace(/^data:image\/[a-z]+;base64,/, '');

    //         try {
    //             // Cắt bỏ khoảng trống ở đầu và cuối của email, password và confirmPassword
    //             const trimmedEmail = formData.email.trim();
    //             const trimmedPassword = formData.password.trim();
    //             const trimmedConfirmPassword = formData.confirmPassword.trim();

    //             const requestData = {
    //                 email: trimmedEmail, // Sử dụng giá trị đã cắt bỏ khoảng trống
    //                 password: trimmedPassword, // Sử dụng giá trị đã cắt bỏ khoảng trống
    //                 avatar: base64String,
    //             };

    //             const response = await Apis.post(endpoints['register'], requestData, {
    //                 headers: {
    //                     'Content-Type': 'application/json',
    //                 },
    //             });

    //             if (response.status === 200) {
    //                 setErrorMessage(response.data);
    //                 nav("/login");
    //                 console.log(response.data);
    //             } else {
    //                 console.error(response.data);
    //                 console.log(response.status);
    //             }
    //         } catch (error) {
    //             if (error.response && error.response.status === 400) {
    //                 // Đăng ký thất bại với lỗi 400
    //                 setErrorMessage(error.response.data);
    //             } else {
    //                 console.error('Error:', error);
    //             }
    //         }
    //     };

    //     reader.readAsDataURL(file);
    // };

    const register = async (e) => {
        e.preventDefault();
        setIsLoading(true); // Hiển thị dấu hiệu xử lý

        if (!avatar.current.files.length) {
            setIsLoading(false); // Ẩn dấu hiệu xử lý nếu không có tệp nào được chọn
            setErrorMessage("Vui lòng chọn tệp hình ảnh đại diện");
            return;
        }

        const file = avatar.current.files[0];
        const reader = new FileReader();

        reader.onload = async (e) => {
            const base64String = e.target.result.replace(/^data:image\/[a-z]+;base64,/, '');

            try {
                // Cắt bỏ khoảng trống ở đầu và cuối của email, password và confirmPassword
                const trimmedEmail = formData.email.trim();
                const trimmedPassword = formData.password.trim();
                const trimmedConfirmPassword = formData.confirmPassword.trim();

                const requestData = {
                    email: trimmedEmail, // Sử dụng giá trị đã cắt bỏ khoảng trống
                    password: trimmedPassword, // Sử dụng giá trị đã cắt bỏ khoảng trống
                    avatar: base64String,
                };

                const response = await Apis.post(endpoints['register'], requestData, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                if (response.status === 200) {
                    setErrorMessage(response.data);
                    nav("/login");
                    console.log(response.data);
                } else {
                    console.error(response.data);
                    console.log(response.status);
                }
            } catch (error) {
                if (error.response && error.response.status === 400) {
                    // Đăng ký thất bại với lỗi 400
                    setErrorMessage(error.response.data);
                } else {
                    console.error('Error:', error);
                }
            } finally {
                setIsLoading(false); // Ẩn dấu hiệu xử lý sau khi hoàn thành xử lý
            }
        };

        reader.readAsDataURL(file);
    };

    const handleInputChange = (e) => {
        const { name, value, files } = e.target;

        if (name === 'avatar') {
            setFormData({
                ...formData,
                avatar: files[0],
            });
        } else {
            setFormData({
                ...formData,
                [name]: value,
            });
        }


        if (name === 'email') {
            const isValid = isValidEmail(value);
            setErrors({
                ...errors,
                email: isValid ? '' : 'Invalid email format',
            });
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (name === 'password' || name === 'confirmPassword') {
            const password = name === 'password' ? value : formData.password;
            const confirmPassword = name === 'confirmPassword' ? value : formData.confirmPassword;

            if (password !== confirmPassword) {
                setErrors({
                    ...errors,
                    confirmPassword: 'Passwords do not match',
                });
            } else {
                setErrors({
                    ...errors,
                    confirmPassword: '',
                });
            }
        }

        setFormData({
            ...formData,
            [name]: value,
        });
    };

    return (
        <Container fluid>
            <Row className='justify-content-center align-items-center m-5'>
                <Col md='15'>
                    <Card style={{ backgroundColor: 'rgb(255 241 236)' }}>
                        <Card.Body className='px-4'>
                            <div className='d-flex justify-content-center align-items-center mb-4' style={{ margin: '20px' }}>
                                <img
                                    src='https://i.pinimg.com/originals/56/bd/ee/56bdee62e5dfb18c83b9ab7745124d3d.jpg'
                                    alt='Registration Image'
                                    style={{ maxWidth: '500px', marginRight: '50px' }}
                                />
                                <div>
                                    <h3 className="fw-bold mb-4 pb-2 pb-md-0 mb-md-5">Đăng ký tài khoản</h3>
                                    {errorMessage && (
                                        <Alert variant="danger">
                                            {errorMessage}
                                        </Alert>
                                    )}
                                    <Form onSubmit={register}>
                                        <Form.Group className="mb-3">
                                            <Form.Label>Email</Form.Label>
                                            <Form.Control
                                                type="email"
                                                name="email"
                                                placeholder="Email"
                                                onChange={handleInputChange}
                                                isInvalid={!!errors.email}
                                            />
                                            <Form.Control.Feedback type="invalid">
                                                {errors.email}
                                            </Form.Control.Feedback>
                                        </Form.Group>

                                        <Form.Group className="mb-3">
                                            <Form.Label>Mật khẩu</Form.Label>
                                            <Form.Control
                                                type="password"
                                                name="password"
                                                placeholder="Mật khẩu"
                                                onChange={handleInputChange}
                                                isInvalid={!!errors.password}
                                            />
                                            <Form.Control.Feedback type="invalid">
                                                {errors.password}
                                            </Form.Control.Feedback>
                                        </Form.Group>

                                        <Form.Group className="mb-3">
                                            <Form.Label>Xác nhận mật khẩu</Form.Label>
                                            <Form.Control
                                                type="password"
                                                name="confirmPassword"
                                                placeholder="Xác nhận mật khẩu"
                                                onChange={handleInputChange}
                                                isInvalid={!!errors.confirmPassword}
                                            />
                                            <Form.Control.Feedback type="invalid">
                                                {errors.confirmPassword}
                                            </Form.Control.Feedback>
                                        </Form.Group>

                                        <Form.Group className="mb-3">
                                            <Form.Label>Ảnh đại diện</Form.Label>
                                            <Form.Control
                                                type="file"

                                                ref={avatar}
                                            />
                                        </Form.Group>

                                        {/* <Form.Group className="mb-3">
                                            <Button variant="info" type="submit">
                                                Đăng ký
                                            </Button>
                                        </Form.Group> */}
                                        <Form.Group className="mb-3">
                                            <Button variant="info" type="submit" disabled={isLoading}>
                                                {isLoading ? 'Đang xử lý...' : 'Đăng ký'}
                                            </Button>
                                        </Form.Group>
                                    </Form>
                                    {isLoading && <p>Đang xử lý...</p>}
                                    {/* {errorMessage && <Alert variant="danger">{errorMessage}</Alert>} */}
                                </div>
                            </div>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default Register;
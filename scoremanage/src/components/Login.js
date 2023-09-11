import React, { useContext, useEffect, useState } from 'react';
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBIcon,
    MDBInput
}
    from 'mdb-react-ui-kit';
import { Alert, Button, Form } from 'react-bootstrap';
import axios from 'axios';
import Apis, { authApi, endpoints } from '../configs/Apis';
import cookie from "react-cookies";
import { MyUserContext } from "../App";
import { Navigate } from 'react-router-dom';
const Login = () => {

    const [user, dispatch] = useContext(MyUserContext);
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        roleID: '3',
    });
    const [error, setError] = useState(null);
    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    // const handleRoleChange = (event) => {
    //     const value = event.target.value;
    //     setFormData({ ...formData, roleID: value });
    //     console.log(formData);
    // };

    // const handleLogin = async (event) => {
    //     event.preventDefault();
    //     console.log(formData);

    //     try {
    //         const requestBody = {
    //             username: formData.username,
    //             password: formData.password,
    //             roleID: formData.roleID,
    //         };
    //         const response = await Apis.post(endpoints['login'], requestBody);
    //         cookie.save("token",response.data);
    //         let {data} = await  authApi().get(endpoints["current-user"]);
    //         cookie.save("user", data);
    //         dispatch({
    //             "type": "login",
    //             "payload": data
    //         });
    //         if (response.status == 200) {
    //             console.log(response.data); // Log phản hồi từ máy chủ
    //         }


    //     } catch (error) {
    //         if (error.response.status === 401) {
    //             const errorData = error.response.data;

    //             setError(errorData);

    //         } else {
    //             console.error('Lỗi không xác định:', error);
    //         }
    //     }
    // };
    const handleLogin = async (event) => {
        event.preventDefault();
        console.log(formData);
    
        try {
            // Cắt bỏ khoảng trống ở đầu và cuối của username
            const trimmedUsername = formData.username.trim();
    
            const requestBody = {
                username: trimmedUsername, // Sử dụng giá trị đã cắt bỏ khoảng trống
                password: formData.password,
                roleID: formData.roleID,
            };
            const response = await Apis.post(endpoints['login'], requestBody);
            cookie.save("token", response.data);
            let { data } = await authApi().get(endpoints["current-user"]);
            cookie.save("user", data);
            dispatch({
                "type": "login",
                "payload": data
            });
            if (response.status == 200) {
                console.log(response.data); // Log phản hồi từ máy chủ
            }
        } catch (error) {
            if (error.response.status === 401) {
                const errorData = error.response.data;
                setError(errorData);
            } else {
                console.error('Lỗi không xác định:', error);
            }
        }
    };

    if (user != null)
        return <Navigate to="/" />

    return (

        <>
            <MDBContainer fluid>
                <MDBRow style={{ border: '2px solid #ccc', borderRadius: '10px' }}>

                    <MDBCol sm='6'>

                        <div className='d-flex flex-row ps-5 pt-5'>
                            <MDBIcon fas icon="crow fa-3x me-3" style={{ color: '#709085', paddingLeft: 130 }} />
                            <span className="h1 fw-bold mb-0">Đăng nhập</span>
                        </div>

                        <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4' style={{ marginLeft: '58px' }}>

                            <Form onSubmit={handleLogin}>
                                <Form.Group className='mb-4'>
                                    <Form.Label>UserName/Email</Form.Label>
                                    <Form.Control
                                        type='text'
                                        size='lg'
                                        name='username'
                                        value={formData.username}
                                        onChange={handleInputChange}
                                    />
                                </Form.Group>

                                <Form.Group className='mb-4'>
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control
                                        type='password'
                                        size='lg'
                                        name='password'
                                        value={formData.password}
                                        onChange={handleInputChange}
                                    />
                                </Form.Group>

                                <Form.Group className='mb-4'>
                                    <Form.Label>Chọn vai trò:</Form.Label>
                                    <Form.Select
                                        size='lg'
                                        name='roleID'
                                        value={formData.roleID}
                                        onChange={handleInputChange}
                                    >
                                        <option value='3'>Học sinh</option>
                                        <option value='2'>Giáo viên</option>
                                        <option value='1'>Admin</option>
                                    </Form.Select>
                                </Form.Group>

                                <Button className='mb-4 w-100' variant='info' size='lg' type='submit'>
                                    Login
                                </Button>
                            </Form>
                            {error && <Alert variant='danger'>{error}</Alert>}
                            <p className="small mb-5 pb-lg-3 ms-5"><a class="text-muted" href="#!">Forgot password?</a></p>
                            <p className='ms-5'>Bạn có tài khoản chưa? <a href="/register" class="link-info">Đăng ký tại đây</a></p>

                        </div>

                    </MDBCol>

                    <MDBCol sm='6' className='d-none d-sm-block px-0'>
                        <img src="https://i.pinimg.com/originals/b2/85/6c/b2856c048d12e7c93741c53e85ec8df5.jpg"
                            alt="Login image" className="w-100" style={{ objectFit: 'cover', objectPosition: 'left', height: "700px", padding: '47px 47px 47px 0' }} />
                    </MDBCol>

                </MDBRow>

            </MDBContainer>
        </>
    );
};

export default Login;
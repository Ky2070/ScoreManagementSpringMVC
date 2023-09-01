import React, { useEffect, useState } from 'react';
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBIcon,
    MDBInput
}
    from 'mdb-react-ui-kit';

const Login = () => {
    // const [listUser, setListUer] = useState(null);
    // const loadUser = async () => {
    //     try {
    //         let res = await fetch("http://localhost:8080/WebAppSpringMVC/api/user");
    //         if (!res.ok) {
    //             throw new Error(`HTTP error! Status: ${res.status}`);
    //         }
    //         let data = await res.json();
    //         setListUer(data);
    //     } catch (error) {
    //         console.error("Error loading users:", error);
    //     }
    // };

    const handleLogin = async () => {
        // Lấy thông tin từ các trường đăng nhập
        const userName = document.getElementById('formControlLg').value;
        const password = document.getElementById('passwordControlLg').value;
        const role = document.getElementById('roleSelect').value;
    
        // Gửi dữ liệu đăng nhập lên server
        try {
            const response = await fetch("http://localhost:8080/WebAppSpringMVC/api/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ userName, password, role })
            });
    
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
    
            const data = await response.json();
            // Xử lý dữ liệu trả về từ server sau khi đăng nhập thành công
        } catch (error) {
            console.error("Error logging in:", error);
        }
    };
    useEffect(() => {
        // loadUser();
    }
    ,[])
    return (

        <>
            <MDBContainer fluid>
                <MDBRow style={{ border: '2px solid #ccc', borderRadius: '10px'}}>

                    <MDBCol sm='6'>

                        <div className='d-flex flex-row ps-5 pt-5'>
                            <MDBIcon fas icon="crow fa-3x me-3" style={{ color: '#709085', paddingLeft: 130 }} />
                            <span className="h1 fw-bold mb-0">Đăng nhập</span>
                        </div>

                        <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>

                            <MDBInput wrapperClass='mb-4 mx-5 w-100' label='UserName/Email' id='formControlLg' type='text' size="lg" />
                            <MDBInput wrapperClass='mb-4 mx-5 w-100' label='Password' id='formControlLg' type='password' size="lg" />

                            <label htmlFor='roleSelect' className='form-label ms-5'>Chọn vai trò:</label>
                            <select className='form-select mb-4 mx-5 w-100' id='roleSelect'>
                                <option value='student'>Học sinh</option>
                                <option value='teacher'>Giáo viên</option>
                                <option value='staff'>Admin</option>
                            </select>


                            <MDBBtn className="mb-4 px-5 mx-5 w-100 shadow-none" color='info' size='lg' onClick={handleLogin}>Login</MDBBtn>
                            <p className="small mb-5 pb-lg-3 ms-5"><a class="text-muted" href="#!">Forgot password?</a></p>
                            <p className='ms-5'>Bạn có tài khoản chưa? <a href="/register" class="link-info">Đăng ký tại đây</a></p>

                        </div>

                    </MDBCol>

                    <MDBCol sm='6' className='d-none d-sm-block px-0'>
                        <img src="https://i.pinimg.com/originals/b2/85/6c/b2856c048d12e7c93741c53e85ec8df5.jpg"
                            alt="Login image" className="w-100" style={{ objectFit: 'cover', objectPosition: 'left', height: "700px",  padding: '47px 47px 47px 0'}} />
                    </MDBCol>

                </MDBRow>

            </MDBContainer>
        </>
    );
};

export default Login;
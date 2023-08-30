import React from 'react';
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
    return (
        <>
            <MDBContainer fluid>
                <MDBRow>

                    <MDBCol sm='6'>

                        <div className='d-flex flex-row ps-5 pt-5'>
                            <MDBIcon fas icon="crow fa-3x me-3" style={{ color: '#709085', paddingLeft: 130}} />
                            <span className="h1 fw-bold mb-0">Đăng nhập</span>
                        </div>

                        <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>

                            <MDBInput wrapperClass='mb-4 mx-5 w-100' label='Email address' id='formControlLg' type='email' size="lg" />
                            <MDBInput wrapperClass='mb-4 mx-5 w-100' label='Password' id='formControlLg' type='password' size="lg" />

                            <MDBBtn className="mb-4 px-5 mx-5 w-100 shadow-none" color='info' size='lg'>Login</MDBBtn>
                            <p className="small mb-5 pb-lg-3 ms-5"><a class="text-muted" href="#!">Forgot password?</a></p>
                            <p className='ms-5'>Don't have an account? <a href="/register" class="link-info">Register here</a></p>

                        </div>

                    </MDBCol>

                    <MDBCol sm='6' className='d-none d-sm-block px-0'>
                        <img src="https://i.pinimg.com/originals/b2/85/6c/b2856c048d12e7c93741c53e85ec8df5.jpg"
                            alt="Login image" className="w-100" style={{ objectFit: 'cover', objectPosition: 'left', height: "auto" }} />
                    </MDBCol>

                </MDBRow>

            </MDBContainer>
        </>
    );
};

export default Login;
import React from 'react';
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBCard,
    MDBCardBody,
    MDBInput,
    MDBRadio
}
    from 'mdb-react-ui-kit';

const Register = () => {
    return (<>
        <MDBContainer fluid>
            <MDBRow className='justify-content-center align-items-center m-5'>
                <MDBCol md='15'>
                    <MDBCard style={{ backgroundColor: 'rgb(255 241 236)' }}>
                        <MDBCardBody className='px-4'>
                            <div className='d-flex justify-content-center align-items-center mb-4' style={{ margin: '20px' }}>
                                {/* Đây là phần hiển thị hình ảnh bên cạnh form */}
                                <img
                                    src='https://i.pinimg.com/originals/56/bd/ee/56bdee62e5dfb18c83b9ab7745124d3d.jpg'
                                    alt='Registration Image'
                                    style={{ maxWidth: '500px', marginRight: '50px' }}
                                />
                                <div>
                                    <h3 className="fw-bold mb-4 pb-2 pb-md-0 mb-md-5">Đăng ký tài khoản</h3>
                                    <MDBInput wrapperClass='mb-4' label='Email' size='lg' id='form4' type='email' />
                                    <MDBInput wrapperClass='mb-4' label='Phone Number' size='lg' id='form5' type='rel' />
                                    <MDBInput wrapperClass='mb-4' label='Password' size='lg' id='form1' type='password' />
                                    {/* Thêm trường đăng tải ảnh đại diện */}
                                    <div className='mb-4'>
                                        <label htmlFor='avatarUpload' className='form-label'>
                                            Avatar
                                        </label>
                                        <input
                                            className='form-control'
                                            type='file'
                                            id='avatarUpload'
                                            accept='image/*'
                                        />
                                    </div>
                                    <MDBBtn className='mb-4' size='lg' style={{ transform: 'none' }}>
                                        Submit
                                    </MDBBtn>
                                </div>
                            </div>
                        </MDBCardBody>
                    </MDBCard>
                </MDBCol>
            </MDBRow>
        </MDBContainer>


    </>)
}

export default Register
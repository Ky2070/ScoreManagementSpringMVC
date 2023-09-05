import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import FormControl from 'react-bootstrap/FormControl';
import '../css/StickyPanel.css'
import { Link } from 'react-router-dom';
const ListStudent = () => {
    
    return (<>
        <div className='list-function-of-teacher'>
            <div className="sticky-panel" id="stickyPanel"> {/* Container cho mục bao gồm các chức năng */}
                <h1 className="function-name">CHỨC NĂNG</h1>
                {/* Các chức năng */}
                <Link className="panel-button" as={Link} to="/liststudent">Xem danh sách sinh viên</Link>
                <Link className="panel-button" as={Link} to="/listscore">Chỉnh sửa điểm</Link>
                <Link className="panel-button" href="#">Xuất bảng điểm</Link>
            </div>

            <div className="content">
                <h1 style={{ textAlign: "center" }}>DANH SÁCH SINH VIÊN LỚP...</h1>
                <Table striped bordered hover>

                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã số sinh viên</th>
                            <th>Họ và tên</th>
                            <th>Gmail</th>
                            <th>Giới tính</th>
                            <th>Ngày sinh</th>
                            <th>Số điện thoại</th>
                        </tr>
                    </thead>
                    <tbody>
                       




                        {Array.from({ length: 5 }, (_, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>205</td>
                                <td>...</td>
                                <td>...</td>
                                <td>...</td>
                                <td>...</td>
                                <td>...</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
        </div>
    </>)
}
export default ListStudent;
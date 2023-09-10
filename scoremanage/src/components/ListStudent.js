
import React, { useContext, useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import FormControl from 'react-bootstrap/FormControl';
import '../css/StickyPanel.css'
import { Link } from 'react-router-dom';
import { useSchoolYear } from '../reducers/SchoolYearContext';
import { MyUserContext } from '../App';
import Apis, { endpoints } from '../configs/Apis';
import cookie from "react-cookies";
import { UniqueSubjectTeacherIdContext } from '../reducers/UniqueSubjectTeacherIdContext';
import { useListStudents } from '../reducers/ListStudentsContext';

const ListStudent = () => {
    const { selectedSchoolYearId } = useSchoolYear();
    const [user] = useContext(MyUserContext);
    const { selectedSubjectTeacherId } = useContext(UniqueSubjectTeacherIdContext);
    const { liststudents, setListStudents } = useListStudents();
    const [searchName, setSearchName] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const requestData = {
                    'selectedSchoolYearId': selectedSchoolYearId,
                    'userUserName': user.username,
                    'subjectTeacherId': selectedSubjectTeacherId
                };
                const headers = {
                    'Authorization': cookie.load('token'),
                    'Content-Type': 'application/json',
                };
                const response = await Apis.post(endpoints['liststudents'], requestData, { headers });

                if (response.status === 200) {
                    const responseData = response.data; // Lấy dữ liệu từ response
                    setListStudents(responseData);
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [selectedSchoolYearId, selectedSubjectTeacherId]);

    const filteredStudents = liststudents.filter((student) =>
        `${student.studentId.firstName} ${student.studentId.lastName}`
        .toLowerCase()
        .includes(searchName.toLowerCase())
    );

    return (
        <>
            <div className='list-function-of-teacher'>
                <div className="sticky-panel" id="stickyPanel">
                    <h1 className="function-name">CHỨC NĂNG</h1>
                    <Link className="panel-button" as={Link} to="/liststudent">Xem danh sách sinh viên</Link>
                    <Link className="panel-button" as={Link} to="/listscore">Chỉnh sửa điểm</Link>
                    <Link className="panel-button" as={Link} to="/exportscore">Xuất bảng điểm</Link>
                </div>

                <div className="content">
                    <h1 style={{ textAlign: "center" }}>DANH SÁCH SINH VIÊN</h1>
                    <input
                        type="text"
                        placeholder="Nhập tên sinh viên..."
                        value={searchName}
                        onChange={(e) => setSearchName(e.target.value)}
                    />
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
                            {Array.isArray(filteredStudents) && filteredStudents.length > 0 ? (
                                filteredStudents.map((student, index) => (
                                    <tr key={index}>
                                        <td>{index + 1}</td>
                                        <td>{student.studentId.studentCode}</td>
                                        <td>{student.studentId.firstName} {student.studentId.lastName}</td>
                                        <td>{student.studentId.email}</td>
                                        <td>{student.studentId.gender === 0 ? 'Nam' : 'Nữ'}</td>
                                        <td>{student.studentId.birthdate}</td>
                                        <td>{student.studentId.phone}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="7">Không có dữ liệu sinh viên phù hợp.</td>
                                </tr>
                            )}
                        </tbody>
                    </Table>
                </div>
            </div>
        </>
    )
}

export default ListStudent;

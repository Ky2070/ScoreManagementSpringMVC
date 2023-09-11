import React, { useContext, useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import FormControl from 'react-bootstrap/FormControl';
import '../css/StickyPanel.css'
import { Link } from 'react-router-dom';
import Apis, { endpoints } from '../configs/Apis';
import cookie from "react-cookies";
import { useSchoolYear } from '../reducers/SchoolYearContext';
import { UniqueSubjectTeacherIdContext } from '../reducers/UniqueSubjectTeacherIdContext';
import { useListStudents } from '../reducers/ListStudentsContext';
import { MyUserContext } from '../App';

const ListScoreOfStudent = () => {
    const [columns, setColumns] = useState(2);
    const [draftMode, setDraftMode] = useState(false);
    const [columnNames, setColumnNames] = useState(['Giữa kỳ', 'Cuối kỳ']);
    const [listScore, setListScore] = useState([]);
    const [editingColumnIndex, setEditingColumnIndex] = useState(-1);
    const { selectedSubjectTeacherId } = useContext(UniqueSubjectTeacherIdContext);
    const { selectedSchoolYearId } = useSchoolYear();
    const [inputError, setInputError] = useState('');
    const { liststudents } = useListStudents();
    const [bonusColumnsCount, setBonusColumnsCount] = useState(0);
    const [searchStudentCode, setSearchStudentCode] = useState('');
    const [user] = useContext(MyUserContext);
    const [columnNameMapping, setColumnNameMapping] = useState({

        'Giữa kỳ': 'midTermScore',
        'Cuối kỳ': 'finalTermScore',
        'Bonus3': 'Bonus3',
        'Bonus4': 'Bonus4',
        'Bonus5': 'Bonus5',
    });

    useEffect(() => {
        const fetchData = async () => {
            try {
                const requestData = {
                    'selectedSchoolYearId': selectedSchoolYearId,
                    'subjectTeacherId': selectedSubjectTeacherId,
                    'userUserName': user.username
                };
                const headers = {
                    'Authorization': cookie.load('token'),
                    'Content-Type': 'application/json',
                };
                const response = await Apis.post(endpoints['listscoreofstudent'], requestData, { headers });

                if (response.status === 200) {
                    const listScore = response.data; // Lấy dữ liệu từ response


                    const studentScoresMap = new Map();

                    listScore.forEach((score) => {
                        //đã có trong dữ liệu
                        const scoreStudentID = score.studentID.id;
                        // Kiểm tra xem học sinh đã tồn tại trong Map chưa
                        if (!studentScoresMap.has(scoreStudentID)) {
  
                                // Nếu có học sinh trùng và chưa tồn tại trong Map, thêm học sinh từ liststudents vào
                                studentScoresMap.set(scoreStudentID, {
                                    student: score.studentID,
                                    midTermScore: null,
                                    finalTermScore: null,
                                });
   
                        }


                        const existingStudent = studentScoresMap.get(scoreStudentID);

                        if (score.scoreType.scoreType === 'Giữa kỳ') {
                            existingStudent.midTermScore = score.scoreValue;
                        } else if (score.scoreType.scoreType === 'Cuối kỳ') {
                            existingStudent.finalTermScore = score.scoreValue;
                        } else if (score.scoreType.scoreType === 'Bonus3') {
                            existingStudent.Bonus3 = score.scoreValue;
                        } else if (score.scoreType.scoreType === 'Bonus4') {
                            existingStudent.Bonus4 = score.scoreValue;
                        } else if (score.scoreType.scoreType === 'Bonus5') {
                            existingStudent.Bonus5 = score.scoreValue;
                        }




                    });
                    const uniqueColumnNames = new Set(columnNames);

                    // Duyệt qua listScore để thêm các scoreType vào uniqueColumnNames
                    listScore.forEach((studentScore) => {
                        if (studentScore.scoreType && studentScore.scoreType.scoreType) {
                            const scoreType = studentScore.scoreType.scoreType;
                            uniqueColumnNames.add(scoreType);
                            setColumns(columns + 1);
                        }
                    });

                    // Chuyển uniqueColumnNames từ Set thành một mảng
                    const updatedColumnNames = [...uniqueColumnNames];

                    // Cập nhật columnNames
                    setColumnNames(updatedColumnNames);
                    // Chuyển danh sách từ Map thành mảng
                    const mergedStudentScores = [...studentScoresMap.values()];

                    console.log(mergedStudentScores);
                    setListScore(mergedStudentScores);
                    console.log(response.data);
                    

                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        }
        fetchData();
    }, []);


    return (
        <div className='list-function-of-teacher'>
            <div className='content'>
                <Table striped bordered hover>
                    <thead>
                        {/* <tr>
                            <td colSpan={columns + 4} style={{ fontWeight: 'bold', fontSize: '35px' }}>Lớp: 12A - Môn học: Toán học</td>
                        </tr>
                        <tr>
                            <td colSpan={columns + 5} style={{ fontWeight: 'bold', fontSize: '35px' }}>HK1: 2023-2024</td>
                        </tr> */}

                        <tr>
                            <th>STT</th>
                            <th>Mã số sinh viên</th>
                            <th>Họ và tên</th>
                            {columnNames.map((columnName, colIndex) => (
                                <th key={colIndex}>
                                    {columnName}
                                </th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {listScore.map((studentScore, rowIndex) => (
                            <tr key={rowIndex}>
                                <td>{rowIndex + 1}</td>
                                <td>{studentScore.student.studentCode}</td>
                                <td>{studentScore.student.firstName} {studentScore.student.lastName}</td>
                                {columnNames.map((columnName, colIndex) => (
                                    <td key={colIndex}>
                                        <FormControl
                                            disabled={!draftMode}
                                            type="number"
                                            step="0.1" // Điều này cho phép người dùng nhập số thập phân với tối đa 1 chữ số sau dấu thập phân
                                            value={studentScore[columnNameMapping[columnName]] || ''}
                                            onChange={(e) => {
                                                const updatedListScore = [...listScore];
                                                const newValue = e.target.value === '' ? '' : parseFloat(e.target.value);

                                                if (newValue === '' || (typeof newValue === 'number' && !isNaN(newValue) && newValue >= 0 && newValue <= 10)) {
                                                    setInputError(''); // Xóa thông báo lỗi nếu giá trị hợp lệ hoặc rỗng
                                                    updatedListScore[rowIndex][columnNameMapping[columnName]] = newValue;
                                                } else {
                                                    setInputError('Vui lòng nhập một số hợp lệ hoặc để trống.');
                                                }

                                                setListScore(updatedListScore);
                                            }}

                                        />
                                    </td>
                                ))}
                            </tr>
                        ))}
                        
                    </tbody>
                </Table>


            </div>
        </div >
    );


};

export default ListScoreOfStudent;



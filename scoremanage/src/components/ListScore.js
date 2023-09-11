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

const ScoreTable = () => {
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
    const [saveStatus, setSaveStatus] = useState(null);
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
                };
                const headers = {
                    'Authorization': cookie.load('token'),
                    'Content-Type': 'application/json',
                };
                const response = await Apis.post(endpoints['listscore'], requestData, { headers });

                if (response.status === 200) {
                    setListScore(response.data);

                    const listScore = response.data; // Lấy dữ liệu từ response


                    const studentScoresMap = new Map();

                    listScore.forEach((score) => {
                        //đã có trong dữ liệu
                        const scoreStudentID = score.studentID.id;

                        // Tìm học sinh có cùng id trong liststudents
                        const matchingStudent = liststudents.find((student) => student.studentId.id === scoreStudentID);
                        // Kiểm tra xem học sinh đã tồn tại trong Map chưa
                        if (!studentScoresMap.has(scoreStudentID)) {
                            if (matchingStudent) {
                                // Nếu có học sinh trùng và chưa tồn tại trong Map, thêm học sinh từ liststudents vào
                                studentScoresMap.set(scoreStudentID, {
                                    student: matchingStudent.studentId,
                                    midTermScore: null,
                                    finalTermScore: null,
                                });
                            }
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
                    const notMatchingStudents = liststudents.filter((student) => {
                        const studentID = student.studentId.id;
                        return !studentScoresMap.has(studentID);
                    });

                    notMatchingStudents.forEach((student) => {
                        const studentID = student.studentId.id;
                        studentScoresMap.set(studentID, {
                            student: student.studentId,
                            midTermScore: null,
                            finalTermScore: null,
                        });
                    });
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


    // const savelistscore = async () => {
    //     try {
    //         const dataToSend = {
    //             listScore: listScore,
    //             selectedSchoolYearId: selectedSchoolYearId,
    //             selectedSubjectTeacherId: selectedSubjectTeacherId,
    //         };
    //         const headers = {
    //             'Authorization': cookie.load('token'),
    //         };
    //         const response = await Apis.post(endpoints['savelistscore'], dataToSend, { headers });

    //         if (response.status === 200) {
    //             const responseData = response.data;

    //             console.log(responseData);
    //         }
    //     } catch (error) {
    //         console.error('Error fetching data:', error);
    //     }
    // };
    const savelistscore = async () => {
        try {
            const dataToSend = {
                listScore: listScore,
                selectedSchoolYearId: selectedSchoolYearId,
                selectedSubjectTeacherId: selectedSubjectTeacherId,
            };
            const headers = {
                'Authorization': cookie.load('token'),
            };
            const response = await Apis.post(endpoints['savelistscore'], dataToSend, { headers });

            if (response.status === 200) {
                const responseData = response.data;
                setSaveStatus('Lưu điểm thành công'); // Thiết lập thông báo thành công
                console.log(responseData);
            } else {
                setSaveStatus('Lưu điểm không thành công'); // Thiết lập thông báo không thành công
                console.error('Lưu điểm không thành công:', response);
            }
        } catch (error) {
            setSaveStatus('Lỗi khi lưu điểm'); // Thiết lập thông báo lỗi
            console.error('Error fetching data:', error);
        }
    };

    const handleAddColumn = () => {
        if (columns < 5) {
            const existingColumns = new Set(columnNames);
            const availableBonusColumns = ['Bonus3', 'Bonus4', 'Bonus5'];
            const newColumnName = availableBonusColumns.find(col => !existingColumns.has(col));

            if (newColumnName) {
                // Cập nhật giá trị của columns thành số lượng phần tử trong columnNames
                setColumns(columnNames.length + 1);

                const newColumnData = listScore.map((studentScore) => ({
                    ...studentScore,
                    [newColumnName]: '', // Điểm mặc định cho cột mới
                }));

                setListScore(newColumnData);
                setColumnNames([...columnNames, newColumnName]);
                setDraftMode(true);
            }
        }
    };


    const handleSaveDraft = () => {
        setDraftMode(true);
    };

    const handleLockScores = () => {
        setDraftMode(false);
        setEditingColumnIndex(-1); // Hủy chỉnh sửa cột nào đó (nếu có)
        console.log(listScore);
        console.log(liststudents);
    };



    const handleDeleteColumn = (columnIndex) => {
        if (columns > 2) {
            const columnNameToDelete = columnNames[columnIndex];

            if (columnNameToDelete !== 'Giữa kỳ' && columnNameToDelete !== 'Cuối kỳ') {
                setColumns(columns - 1);

                const updatedListScore = listScore.map((studentScore) => {
                    const updatedStudentScore = { ...studentScore };
                    if (columnNameMapping[columnNameToDelete]) {
                        delete updatedStudentScore[columnNameMapping[columnNameToDelete]];
                    }
                    return updatedStudentScore;
                });

                setListScore(updatedListScore);
                setColumnNames(columnNames.filter((_, index) => index !== columnIndex));

                if (editingColumnIndex === columnIndex) {
                    setEditingColumnIndex(-1);
                }

                // Giảm giá trị của bonusColumnsCount nếu cột bị xóa là cột "Bonus"
                if (columnNameToDelete.startsWith('Bonus')) {
                    setBonusColumnsCount(bonusColumnsCount - 1);
                }
            }
        }
    };



    return (
        <div className='list-function-of-teacher'>
            <div className="sticky-panel" id="stickyPanel">
                <h1 className="function-name">CHỨC NĂNG</h1>
                <Link className="panel-button" as={Link} to="/liststudent">Xem danh sách sinh viên</Link>
                <Link className="panel-button" as={Link} to="/listscore">Chỉnh sửa điểm</Link>
                <Link className="panel-button" as={Link} to="/exportscore">Xuất bảng điểm</Link>
            </div>
            <div className='content'>
                <Button className="change-score" onClick={handleAddColumn} disabled={columns >= 5}>
                    Thêm cột điểm
                </Button>
                {draftMode ? (
                    <Button className="change-score" onClick={handleLockScores}>Khóa điểm</Button>
                ) : (
                    <Button className="change-score" onClick={handleSaveDraft}>Lưu nháp</Button>
                )}
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <td colSpan={columns + 4} style={{ fontWeight: 'bold', fontSize: '35px' }}>Lớp: 12A - Môn học: Toán học</td>
                        </tr>
                        <tr>
                            <td colSpan={columns + 5} style={{ fontWeight: 'bold', fontSize: '35px' }}>HK1: 2023-2024</td>
                        </tr>
                        <input
                            type="text"
                            placeholder="Nhập mã số sinh viên..."
                            value={searchStudentCode}
                            onChange={(e) => setSearchStudentCode(e.target.value)}
                        />
                        <tr>
                            <th>STT</th>
                            <th>Mã số sinh viên</th>
                            <th>Họ và tên</th>
                            {columnNames.map((columnName, colIndex) => (
                                <th key={colIndex}>
                                    {columnName}
                                    {draftMode && columnNames.length > 2 && (
                                        <Button
                                            variant="link"
                                            size="sm"
                                            onClick={() => handleDeleteColumn(colIndex)}
                                        >
                                            Xóa cột
                                        </Button>
                                    )}
                                </th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {/* {listScore.map((studentScore, rowIndex) => (
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
                        ))} */}
                        {listScore
                            .filter((studentScore) => {
                                return studentScore.student.studentCode.toLowerCase().includes(searchStudentCode.toLowerCase());
                            })
                            .map((studentScore, rowIndex) => (
                                <tr key={rowIndex}>
                                    <td>{rowIndex + 1}</td>
                                    <td>{studentScore.student.studentCode}</td>
                                    <td>{studentScore.student.firstName} {studentScore.student.lastName}</td>
                                    {columnNames.map((columnName, colIndex) => (
                                        <td key={colIndex}>
                                            <FormControl
                                                disabled={!draftMode}
                                                type="number"
                                                step="0.1"
                                                value={studentScore[columnNameMapping[columnName]] || ''}
                                                onChange={(e) => {
                                                    const updatedListScore = [...listScore];
                                                    const newValue = e.target.value === '' ? '' : parseFloat(e.target.value);

                                                    if (newValue === '' || (typeof newValue === 'number' && !isNaN(newValue) && newValue >= 0 && newValue <= 10)) {
                                                        setInputError('');
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
                <Button onClick={savelistscore} style={{
                    fontSize: "25px",
                    padding: "9px 25px",
                    marginLeft: "924px"
                }}>Lưu</Button>
                {saveStatus && (
                    <div>
                        {saveStatus === 'Lưu điểm thành công' && <p style={{ color: 'green', fontSize: "20px" }}>{saveStatus}</p>}
                        {saveStatus !== 'Lưu điểm thành công' && <p style={{ color: 'red', fontSize: "20px" }}>{saveStatus}</p>}
                    </div>
                )}
            </div>
        </div >
    );


};

export default ScoreTable;



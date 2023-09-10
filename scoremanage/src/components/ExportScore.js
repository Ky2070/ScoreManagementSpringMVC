// import React, { useContext, useEffect, useState } from 'react';
// import Table from 'react-bootstrap/Table';
// import Button from 'react-bootstrap/Button';
// import FormControl from 'react-bootstrap/FormControl';
// import '../css/StickyPanel.css'
// import { Link } from 'react-router-dom';
// import Apis, { endpoints } from '../configs/Apis';
// import cookie from "react-cookies";
// import { useSchoolYear } from '../reducers/SchoolYearContext';
// import { UniqueSubjectTeacherIdContext } from '../reducers/UniqueSubjectTeacherIdContext';
// import { useListStudents } from '../reducers/ListStudentsContext';
// import { PDFViewer, Document, Page, Text, View } from '@react-pdf/renderer';
// import { CSVLink } from 'react-csv';
// import Papa from 'papaparse';
// import jsPDF from 'jspdf';

// const ExportScore = () => {
//     const [columns, setColumns] = useState(2);
//     const [draftMode, setDraftMode] = useState(false);
//     const [columnNames, setColumnNames] = useState(['Giữa kỳ', 'Cuối kỳ']);
//     const [listScore, setListScore] = useState([]);
//     const [editingColumnIndex, setEditingColumnIndex] = useState(-1);
//     const { selectedSubjectTeacherId } = useContext(UniqueSubjectTeacherIdContext);
//     const { selectedSchoolYearId } = useSchoolYear();
//     const [inputError, setInputError] = useState('');
//     const { liststudents } = useListStudents();
//     const [bonusColumnsCount, setBonusColumnsCount] = useState(0);
//     const [columnNameMapping, setColumnNameMapping] = useState({
//         'Giữa kỳ': 'midTermScore',
//         'Cuối kỳ': 'finalTermScore',
//         'Bonus3': 'Bonus3',
//         'Bonus4': 'Bonus4',
//         'Bonus5': 'Bonus5',
//     });
//     const [showPDF, setShowPDF] = useState(false);
//     const [showCSV, setShowCSV] = useState(false);

//     const handleExportPDF = () => {
//         setShowPDF(true);
//         setShowCSV(false);
//     };

//     const handleExportCSV = () => {
//         setShowPDF(false);
//         setShowCSV(true);
//     };

//     useEffect(() => {
//         const fetchData = async () => {
//             try {
//                 const requestData = {
//                     'selectedSchoolYearId': selectedSchoolYearId,
//                     'subjectTeacherId': selectedSubjectTeacherId,
//                 };
//                 const headers = {
//                     'Authorization': cookie.load('token'),
//                     'Content-Type': 'application/json',
//                 };
//                 const response = await Apis.post(endpoints['listscore'], requestData, { headers });

//                 if (response.status === 200) {
//                     const listScore = response.data; // Lấy dữ liệu từ response


//                     const studentScoresMap = new Map();

//                     listScore.forEach((score) => {
//                         //đã có trong dữ liệu
//                         const scoreStudentID = score.studentID.id;

//                         // Tìm học sinh có cùng id trong liststudents
//                         const matchingStudent = liststudents.find((student) => student.studentId.id === scoreStudentID);
//                         // Kiểm tra xem học sinh đã tồn tại trong Map chưa
//                         if (!studentScoresMap.has(scoreStudentID)) {
//                             if (matchingStudent) {
//                                 // Nếu có học sinh trùng và chưa tồn tại trong Map, thêm học sinh từ liststudents vào
//                                 studentScoresMap.set(scoreStudentID, {
//                                     student: matchingStudent.studentId,
//                                     midTermScore: null,
//                                     finalTermScore: null,
//                                 });
//                             }
//                         }


//                         const existingStudent = studentScoresMap.get(scoreStudentID);

//                         if (score.scoreType.scoreType === 'Giữa kỳ') {
//                             existingStudent.midTermScore = score.scoreValue;
//                         } else if (score.scoreType.scoreType === 'Cuối kỳ') {
//                             existingStudent.finalTermScore = score.scoreValue;
//                         } else if (score.scoreType.scoreType === 'Bonus3') {
//                             existingStudent.Bonus3 = score.scoreValue;
//                         } else if (score.scoreType.scoreType === 'Bonus4') {
//                             existingStudent.Bonus4 = score.scoreValue;
//                         } else if (score.scoreType.scoreType === 'Bonus5') {
//                             existingStudent.Bonus5 = score.scoreValue;
//                         }




//                     });
//                     const uniqueColumnNames = new Set(columnNames);

//                     // Duyệt qua listScore để thêm các scoreType vào uniqueColumnNames
//                     listScore.forEach((studentScore) => {
//                         if (studentScore.scoreType && studentScore.scoreType.scoreType) {
//                             const scoreType = studentScore.scoreType.scoreType;
//                             uniqueColumnNames.add(scoreType);
//                             setColumns(columns + 1);
//                         }
//                     });

//                     // Chuyển uniqueColumnNames từ Set thành một mảng
//                     const updatedColumnNames = [...uniqueColumnNames];

//                     // Cập nhật columnNames
//                     setColumnNames(updatedColumnNames);
//                     const notMatchingStudents = liststudents.filter((student) => {
//                         const studentID = student.studentId.id;
//                         return !studentScoresMap.has(studentID);
//                     });

//                     notMatchingStudents.forEach((student) => {
//                         const studentID = student.studentId.id;
//                         studentScoresMap.set(studentID, {
//                             student: student.studentId,
//                             midTermScore: null,
//                             finalTermScore: null,
//                         });
//                     });
//                     // Chuyển danh sách từ Map thành mảng
//                     const mergedStudentScores = [...studentScoresMap.values()];

//                     console.log(mergedStudentScores);
//                     setListScore(mergedStudentScores);
//                     console.log(response.data);

//                 }
//             } catch (error) {
//                 console.error('Error fetching data:', error);
//             }
//         }
//         fetchData();
//     }, []);



//     const PDFExport = ({ data }) => {
//         const colWidth = 100 / data.columnNames.length + '%';
//         const styles = {
//             page: {
//                 flexDirection: 'row',
//                 backgroundColor: '#E4E4E4',
//             },
//             section: {
//                 margin: 10,
//                 padding: 10,
//                 flexGrow: 1,
//             },
//             header: {
//                 fontSize: 18,
//                 fontWeight: 'bold',
//                 marginBottom: 10,
//             },
//             table: {
//                 display: 'table',
//                 width: 'auto',
//                 borderStyle: 'solid',
//                 borderWidth: 1,
//                 borderRightWidth: 0,
//                 borderBottomWidth: 0,
//             },
//             tableRow: {
//                 margin: 'auto',
//                 flexDirection: 'row',
//             },
//             tableCol: {
//                 width: colWidth, // Sử dụng độ rộng tính toán
//                 borderStyle: 'solid',
//                 borderWidth: 1,
//                 borderLeftWidth: 0,
//                 borderTopWidth: 0,
//             },
//             cell: {
//                 margin: 'auto',
//                 marginTop: 5,
//                 fontSize: 12,
//             },
//         };

//         return (
//             <PDFViewer width="100%" height="500px">
//                 <Document>
//                     <Page size="A4" style={styles.page}>
//                         <View style={styles.section}>
//                             <Text style={styles.header}>Bảng Điểm</Text>
//                             <View style={styles.table}>
//                                 <View style={styles.tableRow}>
//                                     <View style={styles.tableCol}>
//                                         <Text style={styles.cell}>STT</Text>
//                                     </View>
//                                     <View style={styles.tableCol}>
//                                         <Text style={styles.cell}>Mã số sinh viên</Text>
//                                     </View>
//                                     <View style={styles.tableCol}>
//                                         <Text style={styles.cell}>Họ và tên</Text>
//                                     </View>
//                                     {data.columnNames.map((columnName, colIndex) => (
//                                         <View key={colIndex} style={styles.tableCol}>
//                                             <Text style={styles.cell}>{columnName}</Text>

//                                             {console.log(columnName)}
//                                         </View>

//                                     ))}
//                                 </View>
//                                 {data.listScore.map((studentScore, rowIndex) => (
//                                     <View key={rowIndex} style={styles.tableRow}>
//                                         <View style={styles.tableCol}>
//                                             <Text style={styles.cell}>{rowIndex + 1}</Text>
//                                         </View>
//                                         <View style={styles.tableCol}>
//                                             <Text style={styles.cell}>{studentScore.student.studentCode}</Text>
//                                         </View>
//                                         <View style={styles.tableCol}>
//                                             <Text style={styles.cell}>
//                                                 {studentScore.student.firstName} {studentScore.student.lastName}
//                                             </Text>
//                                         </View>

//                                         {data.columnNames.map((columnName, colIndex) => (
//                                             <View key={colIndex} style={styles.tableCol}>
//                                                 <Text style={styles.cell}>
//                                                     {studentScore[columnNameMapping[columnName]] || ''}
//                                                     {console.log(studentScore[columnNameMapping[columnName]])}
//                                                 </Text>
//                                             </View>
//                                         ))}
//                                     </View>
//                                 ))}
//                             </View>
//                         </View>
//                     </Page>
//                 </Document>
//             </PDFViewer>
//         );
//     };

//     const exportToCSV = () => {
//         const data = listScore.map((studentScore, rowIndex) => {
//             const row = {
//                 STT: rowIndex + 1,
//                 'Mã số sinh viên': studentScore.student.studentCode,
//                 'Họ và tên': `${studentScore.student.firstName} ${studentScore.student.lastName}`,
//             };

//             columnNames.forEach((columnName) => {
//                 row[columnName] = studentScore[columnNameMapping[columnName]] || '';
//             });

//             return row;
//         });

//     }
//     return (
//         <div className='list-function-of-teacher'>
//             <div className="sticky-panel" id="stickyPanel">
//                 <h1 className="function-name">CHỨC NĂNG</h1>
//                 <Link className="panel-button" as={Link} to="/liststudent">Xem danh sách sinh viên</Link>
//                 <Link className="panel-button" as={Link} to="/listscore">Chỉnh sửa điểm</Link>
//                 <Link className="panel-button" href="#">Xuất bảng điểm</Link>
//             </div>
//             <div className='content'>
//                 <Table striped bordered hover id="table-score">
//                     <thead>
//                         <tr>
//                             <td colSpan={columns + 4} style={{ fontWeight: 'bold', fontSize: '35px' }}>Lớp: 12A - Môn học: Toán học</td>
//                         </tr>
//                         <tr>
//                             <td colSpan={columns + 5} style={{ fontWeight: 'bold', fontSize: '35px' }}>HK1: 2023-2024</td>
//                         </tr>
//                         <tr>
//                             <th>STT</th>
//                             <th>Mã số sinh viên</th>
//                             <th>Họ và tên</th>
//                             {columnNames.map((columnName, colIndex) => (
//                                 <th key={colIndex}>
//                                     {columnName}
//                                 </th>
//                             ))}
//                         </tr>
//                     </thead>
//                     <tbody>
//                         {listScore.map((studentScore, rowIndex) => (
//                             <tr key={rowIndex}>
//                                 <td>{rowIndex + 1}</td>
//                                 <td>{studentScore.student.studentCode}</td>
//                                 <td>{studentScore.student.firstName} {studentScore.student.lastName}</td>
//                                 {columnNames.map((columnName, colIndex) => (
//                                     <td key={colIndex}>
//                                         <FormControl
//                                             disabled={!draftMode}
//                                             type="number"
//                                             step="0.1" // Điều này cho phép người dùng nhập số thập phân với tối đa 1 chữ số sau dấu thập phân
//                                             value={studentScore[columnNameMapping[columnName]] || ''}
//                                             onChange={(e) => {
//                                                 const updatedListScore = [...listScore];
//                                                 const newValue = e.target.value === '' ? '' : parseFloat(e.target.value);

//                                                 if (newValue === '' || (typeof newValue === 'number' && !isNaN(newValue) && newValue >= 0 && newValue <= 10)) {
//                                                     setInputError(''); // Xóa thông báo lỗi nếu giá trị hợp lệ hoặc rỗng
//                                                     updatedListScore[rowIndex][columnNameMapping[columnName]] = newValue;
//                                                 } else {
//                                                     setInputError('Vui lòng nhập một số hợp lệ hoặc để trống.');
//                                                 }

//                                                 setListScore(updatedListScore);
//                                             }}

//                                         />
//                                     </td>
//                                 ))}
//                             </tr>
//                         ))}
//                     </tbody>
//                 </Table>
//                 <div style={{
//                     display: "flex",
//                     justifyContent: "end"
//                 }}>
//                     <Button
//                         style={{
//                             fontSize: "15px",
//                             padding: "9px 25px",
//                             margin: "2px"
//                         }}
//                         onClick={() => PDFExport({ columnNames, listScore })} // Truyền tham số data vào hàm PDFExport
//                     >
//                         Xuất PDF
//                     </Button>
//                     <Button
//                         style={{
//                             fontSize: "15px",
//                             padding: "9px 25px",
//                             margin: "2px"
//                         }}
//                         onClick={exportToCSV}
//                     >
//                         Xuất CSV
//                     </Button>
//                 </div>
//                 {/* {showPDF && <PDFExport data={{ columnNames, listScore }} />} */}


//             </div>
//         </div >
//     );
// };

// export default ExportScore;

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
import { PDFViewer, Document, Page, Text, View } from '@react-pdf/renderer';
import { CSVLink } from 'react-csv';
import jsPDF from 'jspdf';

const ExportScore = () => {
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
    const [columnNameMapping, setColumnNameMapping] = useState({
        'Giữa kỳ': 'midTermScore',
        'Cuối kỳ': 'finalTermScore',
        'Bonus3': 'Bonus3',
        'Bonus4': 'Bonus4',
        'Bonus5': 'Bonus5',
    });
    const [showPDF, setShowPDF] = useState(false);
    const [showCSV, setShowCSV] = useState(false);

    const handleExportPDF = () => {
        setShowPDF(true);
        setShowCSV(false);
    };

    const handleExportCSV = () => {
        setShowPDF(false);
        setShowCSV(true);
    };

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
        };
        fetchData();
    }, []);

    const PDFExport = () => {
        const colWidth = 100 / columnNames.length + '%';
        const styles = {
            page: {
                flexDirection: 'row',
                backgroundColor: '#E4E4E4',
            },
            section: {
                margin: 10,
                padding: 10,
                flexGrow: 1,
            },
            header: {
                fontSize: 18,
                fontWeight: 'bold',
                marginBottom: 10,
            },
            table: {
                display: 'table',
                width: 'auto',
                borderStyle: 'solid',
                borderWidth: 1,
                borderRightWidth: 0,
                borderBottomWidth: 0,
            },
            tableRow: {
                margin: 'auto',
                flexDirection: 'row',
            },
            tableCol: {
                width: colWidth,
                borderStyle: 'solid',
                borderWidth: 1,
                borderLeftWidth: 0,
                borderTopWidth: 0,
            },
            cell: {
                margin: 'auto',
                marginTop: 5,
                fontSize: 12,
            },
        };

        if (showPDF) {
            return (
                <PDFViewer width="100%" height="500px">
                    <Document>
                        <Page size="A4" style={styles.page}>
                            <View style={styles.section}>
                                <Text style={styles.header}>Bảng Điểm</Text>
                                <View style={styles.table}>
                                    <View style={styles.tableRow}>
                                        <View style={styles.tableCol}>
                                            <Text style={styles.cell}>STT</Text>
                                        </View>
                                        <View style={styles.tableCol}>
                                            <Text style={styles.cell}>Mã số sinh viên</Text>
                                        </View>
                                        <View style={styles.tableCol}>
                                            <Text style={styles.cell}>Họ và tên</Text>
                                        </View>
                                        {columnNames.map((columnName, colIndex) => (
                                            <View key={colIndex} style={styles.tableCol}>
                                                <Text style={styles.cell}>{columnName}</Text>
                                            </View>
                                        ))}
                                    </View>
                                    {listScore.map((studentScore, rowIndex) => (
                                        <View key={rowIndex} style={styles.tableRow}>
                                            <View style={styles.tableCol}>
                                                <Text style={styles.cell}>{rowIndex + 1}</Text>
                                            </View>
                                            <View style={styles.tableCol}>
                                                <Text style={styles.cell}>{studentScore.student.studentCode}</Text>
                                            </View>
                                            <View style={styles.tableCol}>
                                                <Text style={styles.cell}>
                                                    {studentScore.student.firstName} {studentScore.student.lastName}
                                                </Text>
                                            </View>
                                            {columnNames.map((columnName, colIndex) => (
                                                <View key={colIndex} style={styles.tableCol}>
                                                    <Text style={styles.cell}>
                                                        {studentScore[columnNameMapping[columnName]] || ''}
                                                    </Text>
                                                </View>
                                            ))}
                                        </View>
                                    ))}
                                </View>
                            </View>
                        </Page>
                    </Document>
                </PDFViewer>
            );
        } else {
            return null; // Nếu không hiển thị PDF, trả về null hoặc thêm nội dung bạn muốn hiển thị ở đây
        }
    };

    const exportToCSV = () => {
        const csvData = listScore.map((studentScore, rowIndex) => {
            const row = {
                STT: rowIndex + 1,
                'Mã số sinh viên': studentScore.student.studentCode,
                'Họ và tên': `${studentScore.student.firstName} ${studentScore.student.lastName}`,
            };

            columnNames.forEach((columnName) => {
                row[columnName] = studentScore[columnNameMapping[columnName]] || '';
            });

            return row;
        });

        if (showCSV) {
            return (
                <CSVLink
                    data={csvData}
                    filename={'exported_scores.csv'}
                >
                    Xuất CSV
                </CSVLink>
            );
        } else {
            return null; // Nếu không hiển thị CSV, trả về null hoặc thêm nội dung bạn muốn hiển thị ở đây
        }
    };

    return (
        <div className='list-function-of-teacher'>
            <div className="sticky-panel" id="stickyPanel">
                <h1 className="function-name">CHỨC NĂNG</h1>
                <Link className="panel-button" as={Link} to="/liststudent">Xem danh sách sinh viên</Link>
                <Link className="panel-button" as={Link} to="/listscore">Chỉnh sửa điểm</Link>
                <Link className="panel-button" as={Link} to="/exportscore">Xuất bảng diểm</Link>
            </div>
            <div className='content'>
                <Table striped bordered hover id="table-score">
                    <thead>
                        <tr>
                            <td colSpan={columns + 4} style={{ fontWeight: 'bold', fontSize: '35px' }}>Lớp: 12A - Môn học: Toán học</td>
                        </tr>
                        <tr>
                            <td colSpan={columns + 5} style={{ fontWeight: 'bold', fontSize: '35px' }}>HK1: 2023-2024</td>
                        </tr>
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
                <div style={{
                    display: "flex",
                    justifyContent: "end"
                }}>
                    <Button
                        style={{
                            fontSize: "15px",
                            padding: "9px 25px",
                            margin: "2px"
                        }}
                        onClick={handleExportPDF}// Truyền tham số data vào hàm PDFExport
                    >
                        Xuất PDF
                    </Button>
                    <Button
                        style={{
                            fontSize: "15px",
                            padding: "9px 25px",
                            margin: "2px"
                        }}
                        onClick={handleExportCSV}
                    >
                        Xuất CSV
                    </Button>

                </div>
                {showPDF && <PDFExport />}
                    {showCSV && exportToCSV()}
            </div>
        </div>
    );
};

export default ExportScore;

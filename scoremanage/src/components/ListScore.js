import React, { useState } from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import FormControl from 'react-bootstrap/FormControl';
import '../css/StickyPanel.css'

const ScoreTable = () => {
    const [columns, setColumns] = useState(2); // Số cột điểm mặc định là 2 (giữa kì và cuối kì)
    const [draftMode, setDraftMode] = useState(false); // Trạng thái lưu nháp
    const [columnNames, setColumnNames] = useState(['Giữa kì', 'Cuối kì']); // Tên cột điểm

    const handleAddColumn = () => {
        if (columns < 5) {
            setColumns(columns + 1);
            setColumnNames([...columnNames, `Điểm ${columns + 1}`]);
        }
    };

    const handleSaveDraft = () => {
        setDraftMode(true);
    };

    const handleLockScores = () => {
        setDraftMode(false);
    };

    const handleColumnNameChange = (columnIndex, newName) => {
        const newColumnNames = [...columnNames];
        newColumnNames[columnIndex] = newName;
        setColumnNames(newColumnNames);
    };
    const handleDeleteColumn = (columnIndex) => {
        if (columns > 2) {
            setColumns(columns - 1);
            setColumnNames(columnNames.filter((_, index) => index !== columnIndex));
        }
    };

    return (
        <div className='list-function-of-teacher'>
            <div className="sticky-panel" id="stickyPanel"> {/* Container cho mục bao gồm các chức năng */}
                <h1 className="function-name">CHỨC NĂNG</h1>
                {/* Các chức năng */}
                <a className="panel-button" href="/liststudent">Xem danh sách sinh viên</a>
                <a className="panel-button" href="/listscore">Chỉnh sửa điểm</a>
                <a className="panel-button" href="#">Xuất bảng điểm</a>
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
                        <tr>
                            <th>STT</th>
                            <th>Mã số sinh viên</th>
                            <th>Họ và tên</th>
                            {columnNames.map((columnName, columnIndex) => (
                                <th key={columnIndex}>
                                    {columnIndex >= 2 ? (
                                        <div>
                                            <FormControl
                                                value={columnName}
                                                onChange={(e) =>
                                                    handleColumnNameChange(columnIndex, e.target.value)
                                                }
                                                disabled={!draftMode}
                                            />
                                            {draftMode && columnIndex >= 2 && (
                                                <Button
                                                    variant="link"
                                                    size="sm"
                                                    onClick={() => handleDeleteColumn(columnIndex)}
                                                >
                                                    Xóa
                                                </Button>
                                            )}
                                        </div>
                                    ) : (
                                        columnName
                                    )}
                                </th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {[...Array(5)].map((_, rowIndex) => (
                            <tr key={rowIndex}>
                                <td>{rowIndex + 1}</td>
                                <td>205</td>
                                <td>Tên {rowIndex + 1}</td>
                                {[...Array(columns)].map((_, colIndex) => (
                                    <td key={colIndex}>
                                        <FormControl
                                            disabled={!draftMode}
                                            type="text"
                                        // Thêm handle để cập nhật điểm
                                        />
                                    </td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
        </div>
    );
};

export default ScoreTable;
import React, { useContext, useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { Link } from 'react-router-dom';
import { useSchoolYear } from '../reducers/SchoolYearContext';
import { MyUserContext } from '../App';
import Apis, { endpoints } from '../configs/Apis';
import cookie from "react-cookies";
import { UniqueSubjectTeacherIdContext } from '../reducers/UniqueSubjectTeacherIdContext';
const ListOldClass = () => {
    const { selectedSchoolYearId } = useSchoolYear();
    const [user] = useContext(MyUserContext);
    const [uniqueSubjectTeacherIdList, setUniqueSubjectTeacherIdList] = useState([]);
    const {selectedSubjectTeacherId, setSelectedSubjectTeacherId } = useContext(UniqueSubjectTeacherIdContext);

    const [listOldClass, setListOldClass] = useState([]);
    
    useEffect(() => {
        const fetchData = async () => {
            try {
                const requestData = {
                    'selectedSchoolYearId': selectedSchoolYearId,
                    'userUserName': user.username,
                };
                const headers = {
                    'Authorization': cookie.load('token'),
                    'Content-Type': 'application/json',
                };
                const response = await Apis.post(endpoints['listoldclass'], requestData, { headers });

                if (response.status === 200) {
                    const responseData = response.data; // Lấy dữ liệu từ response

                    setListOldClass(responseData);

                    // console.log(responseData);
                    // const uniqueSubjectTeacherIdList = responseData.uniqueSubjectTeacherIdList;
                    // const listSubject = responseData.listSubject;
                    // setSubjects(listSubject); // Đặt giá trị subjects
                    // setUniqueSubjectTeacherIdList(uniqueSubjectTeacherIdList);;
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    });

    const handleGoToClass = (uniqueId) => {
        // Thực hiện xử lý khi nhấp vào nút "Đi đến môn học" với uniqueId được truyền vào.
        console.log(`Đã nhấp vào môn học với ID: ${uniqueId}`);
        
        // Đặt giá trị selectedSubjectTeacherId để truyền cho các thành phần khác
        setSelectedSubjectTeacherId(uniqueId);
    };

    const renderCards = (listOldClass) => {
        return listOldClass.map((subject, index) => (
            <Card key={index} id={subject.id} style={{ width: '18rem', margin: '10px 10px 10px 0' }}>
                {/* {console.log(selectedSubjectTeacherId)} */}
                <Card.Img variant="top" src="https://img.lovepik.com/photo/40006/4475.jpg_wh860.jpg" />
                <Card.Body>
                    <Card.Title>{subject.subjectId.subjectName}</Card.Title>
                    <Card.Text>
                        Số tín chỉ: {subject.subjectId.credits}
                    </Card.Text>
                    <Button
                        as={Link}
                        variant="secondary"
                        to="/listscoreofstudent"
                        onClick={() => handleGoToClass(subject.id)}
                    >
                        Đi đến môn học
                    </Button>
                </Card.Body>
            </Card>
        ));
    };

    return (
        <>
            <h1>LỚP PHỤ TRÁCH</h1>
            <div style={{
                display: 'flex',
                flexDirection: 'row',
                flexWrap: 'wrap',
                border: '1px solid #ccc',
                padding: '10px',
            }}>
                {renderCards(listOldClass)}
            </div>
        </>
    );
};

export default ListOldClass;
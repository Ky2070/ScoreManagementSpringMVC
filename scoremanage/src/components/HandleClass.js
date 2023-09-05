import React, { useContext, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { Link } from 'react-router-dom';
import { useSchoolYear } from '../reducers/SchoolYearContext';
import { MyUserContext } from '../App';
import Apis, { endpoints } from '../configs/Apis';
import cookie from "react-cookies";
const HandleClass = () => {
    const { selectedSchoolYearId } = useSchoolYear();
    const [user] = useContext(MyUserContext);



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
                const response = await Apis.post(endpoints['listsubject'], requestData, { headers });

                if (response.status === 200) {
                    console.log(response.data); // Log phản hồi từ máy chủ
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData(); // Gọi hàm fetchData để gửi yêu cầu API
    });

    const renderCards = () => {
        const cards = [];

        for (let i = 0; i < 4; i++) {
            cards.push(
                <Card key={i} style={{ width: '18rem', margin: '10px 10px 10px 0' }}>
                    <Card.Img variant="top" src="https://img.lovepik.com/photo/40006/4475.jpg_wh860.jpg" />
                    <Card.Body>
                        <Card.Title>Card Title</Card.Title>
                        <Card.Text>
                            Some quick example text to build on the card title and make up the
                            bulk of the card's content.
                        </Card.Text>
                        <Button as={Link} variant="secondary" to="/liststudent">Đi đến môn học</Button>
                    </Card.Body>
                </Card>
            );
        }

        return cards;
    };

    return (
        <>
            <h1>LỚP PHỤ TRÁCH</h1>
            {user !== null ? <h1>{user.username}</h1> : null}
            <h1>{selectedSchoolYearId}</h1>
            <div style={{
                display: 'flex',
                flexDirection: 'row',
                flexWrap: 'wrap',
                border: '1px solid #ccc',
                padding: '10px',
            }}>
                {renderCards()}
            </div>
        </>
    );
};

export default HandleClass;
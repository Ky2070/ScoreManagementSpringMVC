import React from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

const HandleClass = () => {
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
                        <Button variant="secondary" href="/liststudent">Đi đến môn học</Button>
                    </Card.Body>
                </Card>
            );
        }

        return cards;
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
            {renderCards()}
        </div>
        </>
    );
};

export default HandleClass;
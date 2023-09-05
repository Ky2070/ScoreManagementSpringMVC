import { ListGroup, ListGroupItem } from "react-bootstrap";
import { Link } from "react-router-dom";
import '../css/StickyPanel.css'
import Apis, { endpoints } from "../configs/Apis";
import { useEffect, useState } from "react";
const SchoolYear = () => {


    const [schoolYears, setSchoolYears] = useState([]);
    const currentYear = new Date().getFullYear();

    useEffect(() => {
        Apis.get(endpoints['schoolyear'])
            .then(response => {
                console.log(response.data)
                setSchoolYears(response.data);
            })
            .catch(error => {
                console.error('Error fetching school years:', error);
            });
    }, [currentYear]);


    return (<>

        <ListGroup>
            {schoolYears.map(schoolYear => (
                <Link
                    key={schoolYear.id} // Sử dụng key duy nhất cho mỗi học kỳ
                    className="panel-button"
                    to={`/schoolyear/${schoolYear.id}`} // Thay đổi đường dẫn dựa trên schoolYear.id hoặc thông tin phù hợp khác
                >
                    {schoolYear.semesterName}: {schoolYear.nameYear} {/* Hiển thị tên học kỳ hoặc thông tin phù hợp khác */}
                </Link>
            ))}
        </ListGroup>

    </>)
}
export default SchoolYear;
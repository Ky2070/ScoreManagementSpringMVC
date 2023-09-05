import { ListGroup, ListGroupItem } from "react-bootstrap";
import { Link } from "react-router-dom";
import '../css/StickyPanel.css'
import Apis, { endpoints } from "../configs/Apis";
import { useEffect, useState } from "react";
// const SchoolYear = () => {


//     const [schoolYears, setSchoolYears] = useState([]);
//     const currentYear = new Date().getFullYear();

//     useEffect(() => {
//         Apis.get(endpoints['schoolyear'])
//             .then(response => {
//                 console.log(response.data)
//                 setSchoolYears(response.data);
//             })
//             .catch(error => {
//                 console.error('Error fetching school years:', error);
//             });
//     }, [currentYear]);


//     return (<>

//         <ListGroup>
//             {schoolYears.map(schoolYear => (
//                 <Link
//                     key={schoolYear.id} // Sử dụng key duy nhất cho mỗi học kỳ
//                     className="panel-button"
//                     to={`/schoolyear/${schoolYear.id}`} // Thay đổi đường dẫn dựa trên schoolYear.id hoặc thông tin phù hợp khác
//                 >
//                     {schoolYear.semesterName}: {schoolYear.nameYear} {/* Hiển thị tên học kỳ hoặc thông tin phù hợp khác */}
//                 </Link>
//             ))}
//         </ListGroup>

//     </>)
// }
// export default SchoolYear;
import { useSchoolYear } from '../reducers/SchoolYearContext';

const SchoolYear = () => {
    const { selectedSchoolYearId, setSelectedSchoolYearId } = useSchoolYear();// State để lưu giá trị yearID
    const storedSchoolYearId = localStorage.getItem('selectedSchoolYearId');
    const [schoolYears, setSchoolYears] = useState([]);
    const currentYear = new Date().getFullYear();

    useEffect(() => {
        Apis.get(endpoints['schoolyear'])
            .then((response) => {
                console.log(response.data);
                setSchoolYears(response.data);
            })
            .catch((error) => {
                console.error('Error fetching school years:', error);
            });
            if (storedSchoolYearId !== null) {
                setSelectedSchoolYearId(storedSchoolYearId);
            }    
    }, [currentYear]);

    // Hàm xử lý khi người dùng chọn học kỳ
    const handleSchoolYearClick = (yearID) => {
        setSelectedSchoolYearId(yearID);
        localStorage.setItem('selectedSchoolYearId', yearID);
    };

    return (
        <>
            <ListGroup>
                {schoolYears.map((schoolYear) => (
                    <Link
                        key={schoolYear.id}
                        className={`panel-button ${schoolYear.id === selectedSchoolYearId ? 'active' : ''}`}
                        onClick={() => handleSchoolYearClick(schoolYear.id)} // Gọi hàm xử lý khi người dùng click
                        to="/login"
                    >
                        {schoolYear.semesterName}: {schoolYear.nameYear}
                    </Link>
                ))}
            </ListGroup>

        </>
    );
};

export default SchoolYear;
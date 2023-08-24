import axios from "axios";

const SERVER_CONTEXT = "/WebAppSpringMVC"
//định nghĩa các endpoints hoặc api cho dễ quản lý
export const endpoints = {
    "classes":`${SERVER_CONTEXT}/api/classes`,
    "students":`${SERVER_CONTEXT}/api/students`,
    "score":`${SERVER_CONTEXT}/api/score`,
    "subject":`${SERVER_CONTEXT}/api/subject`
}
export default axios.create({
    baseURL:"http://localhost:8080"
})
import axios from "axios";
import cookie from "react-cookies";
const SERVER_CONTEXT = "/WebAppSpringMVC";
const SERVER = "http://localhost:8080";

export const endpoints = {

    "login": `${SERVER_CONTEXT}/api/login`,
    "current-user": `${SERVER_CONTEXT}/api/current-user`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "schoolyear": `${SERVER_CONTEXT}/api/schoolyear`,
    "listsubject": `${SERVER_CONTEXT}/api/listsubject`,
    "liststudents": `${SERVER_CONTEXT}/api/listsubject/liststudents`,
    "listscore": `${SERVER_CONTEXT}/api/listscore`,
    "savelistscore": `${SERVER_CONTEXT}/api/savelistscore`,
    "listoldclass": `${SERVER_CONTEXT}/api/listoldclass`,
    "listscoreofstudent": `${SERVER_CONTEXT}/api/listscoreofstudent`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})
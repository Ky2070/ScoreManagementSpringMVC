import axios from "axios";
import cookie from "react-cookies";
// import cloudinary from 'cloudinary';
// cloudinary.config({
//     cloud_name: 'vhuunghia',
//     api_key: '765423634639927',
//     api_secret: 'ziSI1aBDOGpEkuEeVTC2mc5CKXM'
//   });
const SERVER_CONTEXT = "/WebAppSpringMVC";
const SERVER = "http://localhost:8080";

export const endpoints = {

    "login": `${SERVER_CONTEXT}/api/login`,
    "current-user": `${SERVER_CONTEXT}/api/current-user`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "schoolyear": `${SERVER_CONTEXT}/api/schoolyear`,
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
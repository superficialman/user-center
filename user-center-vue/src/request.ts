import axios from 'axios';

const DEV_BASE_URL = "http://localhost:8080/api";      //开发环境
const PROD_BASE_URL = "http://182.92.218.14:8080"; //生产环境

const myAxios = axios.create({
  baseURL: DEV_BASE_URL,             // 基础 URL
  timeout: 5000,                     // 超时时间
  withCredentials: true,             // 允许携带 Cookie
});

// 请求拦截器
myAxios.interceptors.request.use(
  (config) => {
    console.log('请求发送前:', config);
    return config;
  },
  (error) => {
    console.error('请求错误:', error);
    return Promise.reject(error);
  }
);

// 响应拦截器
myAxios.interceptors.response.use(
  (response) => {
    console.log('响应返回后:', response);
    return response;
  },
  (error) => {
    console.error('响应错误:', error);
    return Promise.reject(error);
  }
);

export default myAxios;

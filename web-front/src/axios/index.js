import axios from 'axios'
import Vue from 'vue'
import { Loading } from 'element-ui';
let loadingInstance = null;
const $axios = axios.create({
  baseURL: '/api',
  timeout: 60000
})
// http响应拦截器
$axios.interceptors.request.use(config => {
  loadingInstance= Loading.service({

  });
  return config
}, error => {
  loadingInstance.close();
  return Promise.reject(error)
})

$axios.interceptors.response.use(data => {
  loadingInstance.close();
  return data
}, error => {
  loadingInstance.close();
  return Promise.reject(error)
})
export default $axios;

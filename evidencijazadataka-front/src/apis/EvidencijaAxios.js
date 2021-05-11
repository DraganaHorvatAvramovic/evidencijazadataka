import axios from 'axios';

var EvidencijaAxios = axios.create({
    baseURL: 'http://localhost:8080/api',
    /* other custom settings */
  });

  EvidencijaAxios.interceptors.request.use(
    function success(config){
        const token = window.localStorage['jwt'];
        if (token){
          config.headers['Authorization'] = 'Bearer ' + token;
        }
        return config;
      }
  );

  export default EvidencijaAxios;
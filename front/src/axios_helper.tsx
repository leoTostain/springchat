import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8'
axios.defaults.withCredentials = true;
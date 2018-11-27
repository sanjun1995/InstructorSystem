import axios from 'axios'

/*axios.defaults.baseURL = 'http://localhost:80';*/

export const requestLogin = params => { return axios.post('/user/login', params).then(res => res.data) }



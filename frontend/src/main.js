// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import './assets/icon/iconfont.css'
import axios from 'axios'
import Vuex from 'vuex'

// 设置Vue.config.productionTip = false 来关闭生产模式下给出的提示
Vue.config.productionTip = false
Vue.prototype.$axios = axios
// 代表使用ElementUI组件
Vue.use(ElementUI)
Vue.use(Vuex)

const state  = sessionStorage.getItem('state') ? JSON.parse(sessionStorage.getItem('state')) : {
  account: '',
  name: ''
}

const mutations = {
  setAccount(state, str) {
    state.account = str;
  },
  setName(state, str) {
    state.name = str;
  }
}

const store = new Vuex.Store({
  state: state,
  mutations: mutations
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})


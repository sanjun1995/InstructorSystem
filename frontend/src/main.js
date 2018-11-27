// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
// 引入vuex配置文件
// import store from './store'

// 设置Vue.config.productionTip = false 来关闭生产模式下给出的提示
Vue.config.productionTip = false
// 代表使用ElementUI组件
Vue.use(ElementUI)
// 将axios挂载到Vue原型上方便调用
/*Vue.prototype.$http = axios;*/

/*
import Mock from './mock'
Mock.init()
*/

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

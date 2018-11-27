import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/Login'
import SysManager from '@/views/sysmanager/SysManager'
import Student from '@/views/student/Student'

// 懒加载方式，当路由被访问的时候才加载对应组件
// const Login = resolve => require(['@/components/Login'], resolv)

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      component: Login
    }, {
      path: '/login',
      name: '登录',
      component: Login
    }, {
      path: '/sysmanager',
      name: '管理员界面',
      component: SysManager
    }, {
      path: '/student',
      name: '管理员界面',
      component: Student
    }
  ]
})

// 访问之前，都检查下是否登录了
router.beforeEach((to, from, next) => {
  // console.log('to:' + to.path)
  if (to.path.startsWith('/login')) {
    window.sessionStorage.removeItem('access-token')
    next()
  } else {
    let token = window.sessionStorage.getItem('access-token')
    if (!token) {
      next({path: '/login'})
    } else {
      next()
    }
  }
})

export default router;

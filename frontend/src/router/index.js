import Vue from 'vue'
import vueRouter from 'vue-router'
import Login from '@/views/login/Login'
import SysManager from '@/views/sysmanager/SysManager'
import Student from '@/views/student/Student'


Vue.use(vueRouter)

let router = new vueRouter({
  routes: [
    {
      path: '/',
      redirect: {
        name: 'login'
      }
    }, {
      path: '/login',
      name: 'login',
      component: Login
    }, {
      path: '/sysmanager',
      name: 'sysmanager',
      component: SysManager
    }, {
      path: '/student',
      name: 'student',
      component: Student
    }
  ]
})

// 访问之前，都检查下是否登录了
router.beforeEach((to, from, next) => {

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

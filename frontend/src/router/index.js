import Vue from 'vue'
import vueRouter from 'vue-router'
import Login from '@/views/login/Login'
import SysManager from '@/views/sysmanager/SysManager'
import Student from '@/views/student/Student'
import HomePage from '@/views/student/HomePage'
import PersonalCenter from '@/views/student/PersonalCenter'
import Leave from '@/views/student/Leave'
import Appointment from '@/views/student/Appointment'
import ChangePassword from '@/views/student/ChangePassword'


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
      path: '/sysManager',
      name: 'sysManager',
      component: SysManager
    }, {
      path: '/student',
      name: 'student',
      component: Student,
      children: [
        {path: 'homePage', component: HomePage, name: 'homePage'},
        {path: 'personalCenter', component: PersonalCenter, name: 'personalCenter'},
        {path: 'leave', component: Leave, name: 'leave'},
        {path: 'appointment', component: Appointment, name: 'appointment'},
        {path: 'changePassword', component: ChangePassword, name: 'changePassword'}
      ]
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

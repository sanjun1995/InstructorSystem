import Vue from 'vue'
import vueRouter from 'vue-router'
import Login from '@/views/login/Login'
import Instructor from '@/views/instructor/Instructor'
import SysManager from '@/views/sysmanager/SysManager'
import Student from '@/views/student/Student'
import StuHomePage from '@/views/student/HomePage'
import StuPersonalCenter from '@/views/student/PersonalCenter'
import StuLeave from '@/views/student/Leave'
import StuAppointment from '@/views/student/Appointment'
import StuChangePassword from '@/views/student/ChangePassword'
import StuNotice from '@/views/student/Notice'
import InsHomePage from '@/views/instructor/HomePage'
import InsPersonalCenter from '@/views/instructor/PersonalCenter'
import InsLeave from '@/views/instructor/Leave'
import InsAppointment from '@/views/instructor/Appointment'
import InsChangePassword from '@/views/instructor/ChangePassword'
import InsClassInfo from '@/views/instructor/ClassInfo'
import InsNotice from '@/views/instructor/Notice'
import InsBulletinCriticism from '@/views/instructor/BulletinCriticism'
import InsScore from '@/views/instructor/Score'
import InsNotifications from '@/views/instructor/Notifications'
import SysManagerStudentManagement from '@/views/sysmanager/StudentManagement'
import SysManagerInstructorManagement from '@/views/sysmanager/InstructorManagement'
import SysManagerInformationManagement from '@/views/sysmanager/InformationManagement'
import SysManagerChangePassword from '@/views/sysmanager/ChangePassword'

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
      meta: { title: '管理员端'},
      component: SysManager,
      children: [
        {path: 'sysManagerStudentManagement', component: SysManagerStudentManagement, name: 'sysManagerStudentManagement', meta: { title: '学生管理'}},
        {path: 'sysManagerInstructorManagement', component: SysManagerInstructorManagement, name: 'sysManagerInstructorManagement', meta: { title: '辅导员管理'}},
        {path: 'sysManagerInformationManagement', component: SysManagerInformationManagement, name: 'sysManagerInformationManagement', meta: { title: '信息管理'}},
        {path: 'sysManagerChangePassword', component: SysManagerChangePassword, name: 'sysManagerChangePassword', meta: { title: '修改密码'}}
      ]
    }, {
      path: '/student',
      name: 'student',
      meta: { title: '学生端'},
      component: Student,
      children: [
        {path: 'stuHomePage', component: StuHomePage, name: 'stuHomePage', meta: { title: '首页'}},
        {path: 'stuPersonalCenter', component: StuPersonalCenter, name: 'stuPersonalCenter', meta: { title: '个人中心'}},
        {path: 'stuLeave', component: StuLeave, name: 'stuLeave', meta: { title: '请假'}},
        {path: 'stuAppointment', component: StuAppointment, name: 'stuAppointment', meta: { title: '预约'}},
        {path: 'stuNotice', component: StuNotice, name: 'stuNotice', meta: { title: '公告'}},
        {path: 'stuChangePassword', component: StuChangePassword, name: 'stuChangePassword', meta: { title: '修改密码'}}
      ]
    }, {
      path: '/instructor',
      name: 'instructor',
      meta: { title: '辅导员端'},
      component: Instructor,
      children: [
        {path: 'insHomePage', component: InsHomePage, name: 'insHomePage', meta: { title: '首页'}},
        {path: 'insPersonalCenter', component: InsPersonalCenter, name: 'insPersonalCenter', meta: { title: '个人中心'}},
        {path: 'insLeave', component: InsLeave, name: 'insLeave', meta: { title: '请假管理'}},
        {path: 'insAppointment', component: InsAppointment, name: 'insAppointment', meta: { title: '预约管理'}},
        {path: 'insChangePassword', component: InsChangePassword, name: 'insChangePassword', meta: { title: '修改密码'}},
        {path: 'insClassInfo', component: InsClassInfo, name: 'insClassInfo', meta: { title: '班级管理'}},
        {path: 'insNotifications', component: InsNotifications, name: 'insNotifications', meta: { title: '消息管理'}},
        {path: 'insNotice', component: InsNotice, name: 'insNotice', meta: { title: '公告'}},
        {path: 'insBulletinCriticism', component: InsBulletinCriticism, name: 'insBulletinCriticism', meta: { title: '通报管理'}},
        {path: 'insScore', component: InsScore, name: 'insScore', meta: { title: '成绩管理'}}
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

<template>
  <div class="login-body">
    <el-form ref="accountForm" :model="accountForm" :rules="rules" label-position="left" label-width="0px" class="demo-acountForm login-container">
    <h3 class="title">辅导员管理系统</h3>
    <el-form-item prop="username">
      <el-input type="text" label="账号" v-model="accountForm.account" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="pwd">
        <el-input type="password" v-model="accountForm.password" @keyup.enter.native="handleLogin" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-radio-group v-model="accountForm.role">
          <el-radio label="1">学生</el-radio>
          <el-radio label="2">辅导员</el-radio>
          <el-radio label="3">管理员</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click.native.prevent="handleLogin">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        accountForm: {
          account: '19990',
          password: '123456',
          name: '',
          role: '2'
        },
        rules: {
        }
      };
    },
    methods: {
      handleLogin() {
        this.$refs.accountForm.validate((valid) => {
          if (valid) {
            var role = this.accountForm.role;
            if (role == 1) {
              var studentAxios = axios.create({
                baseURL: 'http://localhost:8080/api/student/'
              });
              studentAxios.post('login', this.accountForm).then(res => {
                if (res.data.code == 200) {
                  //登录成功，把用户信息保存在sessionStorage中
                  sessionStorage.setItem('access-token', res.data.token);
                  this.$store.commit("setAccount", res.data.data[0].account);
                  this.$store.commit("setInsAccount", res.data.data[0].insAccount);
                  this.$store.commit("setName", res.data.data[0].stuName);
                  //跳转到后台主界面
                  this.$router.push({ path: '/student/stuHomePage' });
                } else {
                  alert("账号或密码错误！");
                }
              });
            } else if (role == 2) {
              var instructorAxios = axios.create({
                baseURL: 'http://localhost:8081/api/instructor/'
              });
              instructorAxios.post('login', this.accountForm).then(res => {
                if (res.data.code == 200) {
                  //登录成功，把用户信息保存在sessionStorage中
                  sessionStorage.setItem('access-token', res.data.token);
                  this.$store.commit("setAccount", res.data.data[0].account);
                  this.$store.commit("setName", res.data.data[0].insName);
                  //跳转到后台主界面
                  this.$router.push({ path: '/instructor/insHomePage' });
                } else {
                  alert("账号或密码错误！");
                }
              });
            } else {
              var sysManagerAxios = axios.create({
                baseURL: 'http://localhost:8082/api/sysManager/'
              });
              sysManagerAxios.post('login', this.accountForm).then(res => {
                if (res.data.code == 200) {
                  //登录成功，把用户信息保存在sessionStorage中
                  sessionStorage.setItem('access-token', res.data.token);
                  this.$store.commit("setAccount", res.data.data[0].account);
                  this.$store.commit("setName", res.data.data[0].sysName);
                  //跳转到后台主界面
                  this.$router.push({ path: '/sysManager/sysManagerStudentManagement' });
                } else {
                  alert("账号或密码错误！");
                }
              });
            }
          } else {
            alert("输入有误，请重新登录!")
          }
        });
      }
    }
  }
</script>

<style>
  .login-body{
    height: 800px;
    background-repeat:no-repeat;
    background-size:100% 100%;
    background-image: url("../../assets/compus.jpg");
    overflow: hidden;
  }
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 0 auto;
    margin-top: 100px;
    width: 300px;
    padding: 35px 35px 15px 35px;
    /*background: #fff;*/
    background: rgba(255,255,255, 0.3);
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
</style>

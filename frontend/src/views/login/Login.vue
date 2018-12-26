<template>
  <div class="login-body">
    <el-form ref="accountForm" :model="accountForm" :rules="rules" label-position="left" label-width="0px" class="demo-acountForm login-container">
    <h3 class="title">账号密码登录</h3>
    <el-form-item prop="username">
      <el-input type="text" label="账号" v-model="accountForm.account" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="pwd">
        <el-input type="password" v-model="accountForm.password" @keyup.enter.native="handleLogin" auto-complete="off" placeholder="密码"></el-input>
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
          account: '',
          password: '',
          role: '1'
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
              var managerAxios = axios.create({
                baseURL: 'http://localhost:8080/api/student/'
              });
              managerAxios.post('login', this.accountForm).then(res => {
                if (res.data.code == 200) {

                  //登录成功，把用户信息保存在sessionStorage中
                  sessionStorage.setItem('access-token', res.data.token);
                  //跳转到后台主界面
                  this.$router.push({ path: '/student/homePage' });
                } else {
                  alert("账号或密码错误！");
                }
              });
            } else if (role == 2) {

            } else {
              var studentAxios = axios.create({
                baseURL: 'http://localhost:8081/api/sysManager/'
              });
              studentAxios.post('login', this.accountForm);
            }
          } else {
            alert("输入有误!")
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
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 0 auto;
    margin-top:100px; 
    width: 300px;
    padding: 35px 35px 15px 35px;
    background: #fff;
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

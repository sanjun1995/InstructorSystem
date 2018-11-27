<template>
  <el-form ref="accountForm" :model="account" :rules="rules" label-position="left" label-width="0px" class="demo-acountForm login-container">
    <h3 class="title">账号密码登录</h3>
    <el-form-item prop="username">
      <el-input type="text" label="账号" v-model="account.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input type="password" v-model="account.pwd" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleLogin" :loading="logining">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {requestLogin} from "../../api/api";
  // import NProgress from 'nprogress'
  export default {
    data() {
      return {
        logining: false,
        account: {
          username: 'admin',
          pwd: '123456'
        },
        rules: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'},
            //{ validator: validaePass }
          ],
          pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
      handleLogin() {
        this.$refs.accountForm.validate((valid) => {
          if (valid) {
            this.logining = true;
            //NProgress.start();
            var loginParams = { username: this.account.username, password: this.account.pwd };
            //    let loginParams = new URLSearchParams();
            //    loginParams.append("username",this.account.username);
            //   loginParams.append("password",this.account.pwd);
            requestLogin(loginParams).then(data => {
              this.logining = false;

              let { msg, code, token } = data;
              if(code == '200'){
                //登录成功，把用户信息保存在sessionStorage中
                sessionStorage.setItem('access-token', token);
                //跳转到后台主界面
                this.$router.push({ path: '/sysmanager' });
              }else{
                this.$message({
                  message: msg,
                  type: 'error'
                });
              }

            });

          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  body{
    background: #DFE9FB;
  }
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
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

<template>
  <el-container>
    <el-main>
      <div class="change-pwd">
        <p style="font-weight: bold; color: red">修改密码</p><br/><br/>
        <el-form :model="ruleForm" status-icon :rules="rules" size="small" ref="ruleForm" label-width="100px" class="demo-ruleForm form-container">
          <el-form-item label="原密码" prop="originPass">
            <el-input type="password" v-model="ruleForm.originPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPass">
            <el-input type="password" v-model="ruleForm.newPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      var validateOriginPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      var validateNewPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.newPass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          originPass: '',
          newPass: '',
          checkPass: '',
        },
        rules: {
          originPass: [
            { validator: validateOriginPass, trigger: 'blur' }
          ],
          newPass: [
            { validator: validateNewPass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.ruleForm.token = sessionStorage.getItem("access-token");
            var instructorAxios = axios.create({
              baseURL: 'http://localhost:8081/api/instructor/'
            });
            instructorAxios.post('changePassword', this.ruleForm).then(res => {
              if (res.data.code == 200) {
                this.$message.success('修改密码成功!');
                this.resetForm('ruleForm');
              } else {
                this.$message.error('修改失败，原密码不正确!');
              }
            });
          } else {
            this.$message.error('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
  .el-main {
    text-align: center;
  }
  .form-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 0 auto;
    margin-top: 30px;
    width: 300px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
</style>

<template>
  <el-container>
    <el-main>
      <div class="personal-center">
        <p style="font-weight: bold; color: red">个人中心</p><br/><br/>
        <el-form ref="form" :model="form" label-width="80px" class="form-container">
          <el-form-item>
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="form.imageUrl" :src="form.imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              <p><em>头像，点击上传</em></p>
            </el-upload>
          </el-form-item>
          <el-form-item>
            学号：<el-input v-model="form.num" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item>
            姓名：<el-input v-model="form.name" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item>
            性别：<el-radio-group v-model="form.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            籍贯：<el-input v-model="form.address" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item>
            手机号码：<el-input v-model="form.phoneNum" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item>
            高中毕业学校：<el-input v-model="form.school" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          imageUrl: '',
          num: '151543107',
          name: '曹志鑫',
          sex: '男',
          address: '广东廉江',
          phoneNum: '13556115197',
          school: '廉江市第三中学'
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
</script>

<style scoped>
  .el-main {
    text-align: center;
  }
  .el-form-item label {
    text-align: center;
  }
  .form-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 0 auto;
    margin-top: 0px;
    align-items: center;
    width: 800px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

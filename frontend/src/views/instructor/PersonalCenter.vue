<template>
  <div>
    <div class="container">
      <div class="form-box">
        <el-form ref="form" :model="params" label-width="150px">
          <el-form-item label="头像：">
            <div class="crop-demo">
              <img :src="params.instructor.headPath" class="pre-img">
              <div class="crop-demo-btn">选择图片
                <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage"/>
              </div>
            </div>

            <el-dialog title="裁剪图片" :visible.sync="dialogVisible" width="30%">
              <vue-cropper ref='cropper' :src="params.instructor.headPath" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage" style="width:100%;height:300px;"></vue-cropper>
              <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelCrop">取 消</el-button>
                    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
                </span>
            </el-dialog>
          </el-form-item>
          <el-form-item label="工号：">
            <el-input v-model="params.instructor.account" :disabled="true" style="width: 200px"></el-input>
          </el-form-item>
          <el-form-item label="姓名：">
            <el-input v-model="params.instructor.insName" :disabled="true" style="width: 200px"></el-input>
          </el-form-item>
          <el-form-item label="性别：">
            <el-radio-group v-model="params.instructor.sex">
              <el-radio :label="0">男</el-radio>
              <el-radio :label="1">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="手机号码：">
            <el-input v-model="params.instructor.phoneNum" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item label="办公室地址：">
            <el-input v-model="params.instructor.officeLocation" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

  </div>
</template>

<script>
  import axios from 'axios';
  import VueCropper  from 'vue-cropperjs';
  export default {
    data: function(){
      return {
        params: {
          token: '',
          instructor: {
            account: '',
            insName: '',
            sex: '',
            phoneNum: '',
            officeLocation: '',
            headPath: ''
          }
        },
        defaultSrc: '../static/images/img.jpg',
        dialogVisible: false
      }
    },
    components: {
      VueCropper
    },
    methods: {
      onSubmit() {
        this.params.token = sessionStorage.getItem("access-token");
        var instructorAxios = axios.create({
          baseURL: 'http://localhost:8081/api/instructor/'
        });
        instructorAxios.post('updatePersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success('提交成功！');
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getData() {
        this.params.token = sessionStorage.getItem("access-token");
        var instructorAxios = axios.create({
          baseURL: 'http://localhost:8081/api/instructor/'
        });
        instructorAxios.post('getPersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.instructor = (res.data.data)[0];
            if (this.params.instructor.headPath != null) {
              this.defaultSrc = this.params.instructor.headPath;
            } else {
              this.params.instructor.headPath = this.defaultSrc;
            }
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      setImage(e){
        const file = e.target.files[0];
        if (!file.type.includes('image/')) {
          return;
        }
        const reader = new FileReader();
        reader.onload = (event) => {
          this.dialogVisible = true;
          this.params.instructor.headPath = event.target.result;
          this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
        };
        reader.readAsDataURL(file);
      },
      cropImage () {
        this.params.instructor.headPath = this.$refs.cropper.getCroppedCanvas().toDataURL();
      },
      cancelCrop(){
        this.dialogVisible = false;
        this.params.instructor.headPath = this.defaultSrc;
      }
    },
    created(){
      this.params.instructor.headPath = this.defaultSrc;
      this.getData();
    }
  }
</script>
<style scoped>
  .pre-img{
    width: 100px;
    height: 100px;
    background: #f8f8f8;
    border: 1px solid #eee;
    border-radius: 5px;
  }
  .crop-demo{
    display: flex;
    align-items: flex-end;
  }
  .crop-demo-btn{
    position: relative;
    width: 100px;
    height: 40px;
    line-height: 40px;
    padding: 0 20px;
    margin-left: 30px;
    background-color: #409eff;
    color: #fff;
    font-size: 14px;
    border-radius: 4px;
    box-sizing: border-box;
  }
  .crop-input{
    position: absolute;
    width: 100px;
    height: 40px;
    left: 0;
    top: 0;
    opacity: 0;
    cursor: pointer;
  }

  /*Upload*/

  .pure-button {
    width: 150px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    color: #fff;
    border-radius: 3px;
  }

  .g-core-image-corp-container .info-aside {
    height: 45px;
  }

  .el-upload--text {
    background-color: #fff;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    box-sizing: border-box;
    width: 360px;
    height: 180px;
    text-align: center;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .el-upload--text .el-icon-upload {
    font-size: 67px;
    color: #97a8be;
    margin: 40px 0 16px;
    line-height: 50px;
  }

  .el-upload--text {
    color: #97a8be;
    font-size: 14px;
    text-align: center;
  }

  .el-upload--text em {
    font-style: normal;
  }
</style>

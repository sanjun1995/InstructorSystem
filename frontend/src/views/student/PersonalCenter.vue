<template>
  <div>
    <div class="container">
      <div class="form-box">
        <el-form ref="form" :model="params" label-width="150px">
          <el-form-item label="头像：">
            <div class="crop-demo">
              <img :src="params.student.headPath" class="pre-img">
              <div class="crop-demo-btn">选择图片
                <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage"/>
              </div>
            </div>

            <el-dialog title="裁剪图片" :visible.sync="dialogVisible" width="30%">
              <vue-cropper ref='cropper' :src="params.student.headPath" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage" style="width:100%;height:300px;"></vue-cropper>
              <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelCrop">取 消</el-button>
                    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
                </span>
            </el-dialog>
          </el-form-item>
          <el-form-item label="学号：">
            <el-input v-model="params.student.account" :disabled="true" style="width: 200px"></el-input>
          </el-form-item>
          <el-form-item label="姓名：">
            <el-input v-model="params.student.stuName" :disabled="true" style="width: 200px"></el-input>
          </el-form-item>
          <el-form-item label="性别：">
          <el-radio-group v-model="params.student.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
          </el-form-item>
          <el-form-item label="籍贯：">
            <el-input v-model="params.student.address" :disabled="true" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item label="生日：">
          <el-input v-model="params.student.birth" :disabled="true" style="width: 250px"></el-input>
        </el-form-item>
          <el-form-item label="宿舍：">
            <el-input v-model="params.student.dormitory" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item label="手机号码：">
            <el-input v-model="params.student.phoneNum" style="width: 250px"></el-input>
          </el-form-item>
          <el-form-item label="高中毕业学校：">
            <el-input v-model="params.student.graduateSchool" style="width: 250px"></el-input>
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
          student: {
            account: '',
            stuName: '',
            sex: '',
            phoneNum: '',
            address: '',
            graduateSchool: '',
            birth: '',
            dormitory: '',
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
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/student/'
        });
        studentAxios.post('updatePersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success('提交成功！');
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getData() {
        this.params.token = sessionStorage.getItem("access-token");
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/student/'
        });
        studentAxios.post('getPersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.student = (res.data.data)[0];
            if (this.params.student.headPath != null) {
              this.defaultSrc = this.params.student.headPath;
            } else {
              this.params.student.headPath = this.defaultSrc;
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
          this.params.student.headPath = event.target.result;
          this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
        };
        reader.readAsDataURL(file);
      },
      cropImage () {
        this.params.student.headPath = this.$refs.cropper.getCroppedCanvas().toDataURL();
      },
      cancelCrop(){
        this.dialogVisible = false;
        this.params.student.headPath = this.defaultSrc;
      }
    },
    created(){
      this.params.student.headPath = this.defaultSrc;
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
</style>

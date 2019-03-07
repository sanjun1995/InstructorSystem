<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <div class="add-btn">
          <el-button type="primary" @click="addStudent">增加</el-button>
        </div>
        <el-select v-model="selectClass" @change="currentSel" placeholder="选择班级" class="handle-select">
          <el-option key="1" label="全部班级" value=""></el-option>
          <el-option key="2" label="1515431" value="1515431"></el-option>
          <el-option key="3" label="1515432" value="1515432"></el-option>
        </el-select>
        <div class="select-stu">
          学号：<el-input v-model="params.student.account" class="handle-input"></el-input>
          姓名：<el-input v-model="params.student.stuName" class="handle-input"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </div>
      </div>
      <div class="handle-table">
        <el-table :data="tableData" border ref="multipleTable" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column align="center" label="学号" width="100">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.account, params.student.account)" ></span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="姓名" width="80">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.stuName, params.student.stuName)" ></span>
            </template>
          </el-table-column>
          <el-table-column prop="sex" align="center" :formatter="formatSex" label="性别" width="50">
          </el-table-column>
          <el-table-column prop="birth" align="center" label="出生年月日" width="120">
          </el-table-column>
          <el-table-column prop="address" align="center" label="籍贯" width="150">
          </el-table-column>
          <el-table-column prop="dormitory" align="center" label="宿舍" width="120">
          </el-table-column>
          <el-table-column prop="phoneNum" align="center" label="手机号码" width="120">
          </el-table-column>
          <el-table-column prop="graduateSchool" align="center" label="高中毕业学校" width="150">
          </el-table-column>
          <el-table-column label="操作" width="250" align="center">
            <template slot-scope="scope">
              <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
        </el-pagination>
      </div>
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="25%">
      <el-form ref="studentForm" :rules="rules" :model="params.student" label-width="120px">
        <el-form-item label="学号" prop="account">
          <el-input v-model="params.student.account" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码" v-model="params.student.password" class="handle-input" type="password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="params.student.stuName" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="性别：" prop="sex">
          <el-radio-group v-model="params.student.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="籍贯" prop="address">
          <el-input v-model="params.student.address" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="生日" prop="birth">
          <el-date-picker
            v-model="params.student.birth"
            type="date"
            class="handle-input"
            placeholder="选择日期"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="宿舍" prop="dormitory">
          <el-input v-model="params.student.dormitory" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNum">
          <el-input v-model="params.student.phoneNum" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="高中毕业学校" prop="graduateSchool">
          <el-input v-model="params.student.graduateSchool" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="辅导员" prop="insAccount">
          <el-select v-model="params.student.insAccount" @change="selectInsAccount" placeholder="请选择" class="handle-input">
            <el-option key="1" label="卜翠娟(19990)" value="19990"></el-option>
            <el-option key="2" label="钟培权(19991)" value="19991"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveStudent">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        tableData: [],
        multipleSelection: [],
        selectClass: '',
        editVisible: false,
        delVisible: false,
        isUpdate: false,
        params: {
          pageNum: '1',
          student: {
            id: '',
            stuName: '',
            account: '',
            password: '',
            sex: '',
            address: '',
            graduateSchool: '',
            birth: '',
            insAccount: '',
            phoneNum: '',
            dormitory: ''
          }
        },
        rules: {
          account: [
            {required: true, message: '请输入学号', trigger: 'blur'},
            {min: 9, max: 9, message: '长度为9个数字', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 16, message: '长度为6至16位', trigger: 'blur'}
          ],
          stuName: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
          ],
          sex: [
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          address: [
            {required: true, message: '请输入籍贯', trigger: 'blur'},
          ],
          birth: [
            { type: 'string', required: true, message: '请选择日期', trigger: 'change' }
          ],
          dormitory: [
            {required: true, message: '请输入宿舍号', trigger: 'blur'},
          ],
          phoneNum: [
            {required: true, message: '请输入手机号码', trigger: 'blur'},
          ],
          graduateSchool: [
            {required: true, message: '请输入高中学校', trigger: 'blur'},
          ],
          insAccount: [
            { required: true, message: '请选择辅导员', trigger: 'change' }
          ]
        }
      }
    },
    created() {
      this.getData();
    },
    methods: {
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
        this.getData();
      },
      getData() {
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/student/'
        });
        studentAxios.post('getStudentInfos', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      brightenKeyword(val, keyword) {
        val = val + '';
        if (val.indexOf(keyword) !== -1 && keyword !== '') {
          return val.replace(keyword, '<font color="red">' + keyword + '</font>')
        } else {
          return val
        }
      },
      formatSex(row, column) {
        return row.sex == '1' ? "女" : "男";
      },
      currentSel(val) {
        this.params.student.account = val;
        this.getData();
      },
      selectInsAccount(val) {
        this.params.student.insAccount = val;
      },
      addStudent() {
        this.params.student = {};
        this.editVisible = true;
      },
      search() {
        this.getData();
      },
      handleEdit(index, row) {
        const item = this.tableData[index];
        this.params.student = {
          id: item.id,
          account: item.account,
          password: item.password,
          stuName: item.stuName,
          sex: item.sex,
          phoneNum: item.phoneNum,
          address: item.address,
          graduateSchool: item.graduateSchool,
          birth: item.birth,
          dormitory: item.dormitory,
          insAccount: item.insAccount
        };
        this.editVisible = true;
        this.isUpdate = true;
      },
      handleDelete(index, row) {
        this.params.student.id = row.id;
        this.delVisible = true;
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 保存学生信息
      saveStudent() {
        this.$refs.studentForm.validate((valid) => {
            if (valid) {
              this.editVisible = false;
              var studentAxios = axios.create({
                baseURL: 'http://localhost:8080/api/student/'
              });
              if (this.isUpdate) {
                studentAxios.post('updateStudentInfo', this.params).then(res => {
                  if (res.data.code == 200) {
                    this.$message.success("修改学生信息成功!");
                    this.isUpdate = false;
                    this.getData();
                  } else {
                    this.$message.error(res.data.msg);
                  }
                });
                return;
              }
              studentAxios.post('insertStudentInfo', this.params).then(res => {
                if (res.data.code == 200) {
                  this.$message.success("增加学生信息成功!");
                  this.getData();
                } else {
                  this.$message.error(res.data.msg);
                }
              });
            } else {
              alert("输入错误，请重新输入!")
            }
        });
      },
      // 确定删除
      deleteRow(){
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/student/'
        });
        studentAxios.post('deleteStudentInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("删除学生信息成功!");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
        this.delVisible = false;
      }
    }
  }

</script>

<style scoped>

  .add-btn {
    padding-bottom: 20px;
  }
  .select-stu {
    float: left;
    padding-left: 200px;
    padding-bottom: 20px;
  }

  .handle-box {
    margin-bottom: 10px;
  }

  .handle-select {
    float: left;
    width: 120px;
  }

  .handle-input {
    width: 200px;
    display: inline-block;
  }
  .del-dialog-cnt{
    font-size: 16px;
    text-align: center
  }
  .table{
    width: 100%;
    font-size: 14px;
  }
  .red{
    color: #ff0000;
  }
</style>

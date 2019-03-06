<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <div class="add-btn">
          <el-button type="primary" @click="addInstructor">增加</el-button>
        </div>
        <div class="select-ins">
          工号：<el-input v-model="params.instructor.account" class="handle-input"></el-input>
          姓名：<el-input v-model="params.instructor.insName" class="handle-input"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </div>
      </div>
      <div class="handle-table">
        <el-table :data="tableData" border ref="multipleTable" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column align="center" label="工号" width="100">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.account, params.instructor.account)" ></span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="姓名" width="80">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.insName, params.instructor.insName)" ></span>
            </template>
          </el-table-column>
          <el-table-column prop="sex" align="center" :formatter="formatSex" label="性别" width="50">
          </el-table-column>
          <el-table-column prop="phoneNum" align="center" label="手机号码" width="120">
          </el-table-column>
          <el-table-column prop="officeLocation" align="center" label="办公室地址" width="150">
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
      <el-form ref="instructorForm" :rules="rules" :model="params.instructor" label-width="120px">
        <el-form-item label="工号" prop="account">
          <el-input v-model="params.instructor.account" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码" v-model="params.instructor.password" class="handle-input" type="password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="insName">
          <el-input v-model="params.instructor.insName" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="性别：" prop="sex">
          <el-radio-group v-model="params.instructor.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNum">
          <el-input v-model="params.instructor.phoneNum" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="办公室地址" prop="officeLocation">
          <el-input v-model="params.instructor.officeLocation" class="handle-input"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveInstructor">确 定</el-button>
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
          instructor: {
            id: '',
            insName: '',
            account: '',
            password: '',
            sex: '',
            phoneNum: '',
            officeLocation: ''
          }
        },
        rules: {
          account: [
            {required: true, message: '请输入学号', trigger: 'blur'},
            {min: 5, max: 9, message: '长度为5至9位', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 16, message: '长度为6至16位', trigger: 'blur'}
          ],
          insName: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
          ],
          sex: [
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          phoneNum: [
            {required: true, message: '请输入手机号码', trigger: 'blur'},
          ],
          officeLocation: [
            {required: true, message: '请输入办公室地址', trigger: 'blur'},
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
        var instructorAxios = axios.create({
          baseURL: 'http://localhost:8081/api/instructor/'
        });
        instructorAxios.post('getInstructorInfos', this.params).then(res => {
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
        this.params.instructor.account = val;
        this.getData();
      },
      addInstructor() {
        this.params.instructor = {};
        this.editVisible = true;
      },
      search() {
        this.getData();
      },
      handleEdit(index, row) {
        const item = this.tableData[index];
        this.params.instructor = {
          id: item.id,
          account: item.account,
          password: item.password,
          insName: item.insName,
          sex: item.sex,
          phoneNum: item.phoneNum,
          officeLocation: item.officeLocation
        };
        this.editVisible = true;
        this.isUpdate = true;
      },
      handleDelete(index, row) {
        this.params.instructor.id = row.id;
        this.delVisible = true;
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 保存学生信息
      saveInstructor() {
        this.$refs.instructorForm.validate((valid) => {
          if (valid) {
            this.editVisible = false;
            var instructorAxios = axios.create({
              baseURL: 'http://localhost:8081/api/instructor/'
            });
            if (this.isUpdate) {
              instructorAxios.post('updatePersonalInfo', this.params).then(res => {
                if (res.data.code == 200) {
                  this.$message.success("修改辅导员信息成功!");
                  this.isUpdate = false;
                  this.getData();
                } else {
                  this.$message.error(res.data.msg);
                }
              });
              return;
            }
            instructorAxios.post('insertInstructorInfo', this.params).then(res => {
              if (res.data.code == 200) {
                this.$message.success("增加辅导员信息成功!");
                this.getData();
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            alert("输入有误!")
          }
        });
      },
      // 确定删除
      deleteRow(){
        var instructorAxios = axios.create({
          baseURL: 'http://localhost:8081/api/instructor/'
        });
        instructorAxios.post('deleteInstructorInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("删除辅导员信息成功!");
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

  .handle-box {
    margin-bottom: 10px;
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

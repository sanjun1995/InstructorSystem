<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-select v-model="selectClass" @change="currentSel" placeholder="选择班级" class="handle-select">
          <el-option key="1" label="全部班级" value=""></el-option>
          <el-option key="2" label="1515431" value="1515431"></el-option>
          <el-option key="3" label="1515432" value="1515432"></el-option>
        </el-select>
        <div class="select-stu">
          姓名：<el-input v-model="params.student.stuName" class="handle-input"></el-input>
          宿舍：<el-input v-model="params.student.dormitory" class="handle-input"></el-input>
          <el-button type="primary" icon="search" @click="search">搜索</el-button>
        </div>
      </div>
      <div class="handle-table">
        <el-table :data="tableData" border ref="multipleTable" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column prop="account" align="center" label="学号" width="120">
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
          <el-table-column align="center" label="宿舍" width="120">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.dormitory, params.student.dormitory)" ></span>
            </template>
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
    <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="50px">
        <el-form-item label="日期">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.date" value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
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
    name: 'basetable',
    data() {
      return {
        url: './static/vuetable.json',
        stuName: '',
        tableData: [],
        multipleSelection: [],
        selectClass: '',
        delList: [],
        editVisible: false,
        delVisible: false,
        form: {
          name: '',
          date: '',
          address: ''
        },
        params: {
          pageNum: '1',
          student: {
            stuName: '',
            account: '',
            dormitory: ''
          }
        },
        idx: -1
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
            alert(res.data.msg);
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
      search() {
        this.getData();
      },
      handleEdit(index, row) {
        this.idx = index;
        const item = this.tableData[index];
        this.form = {
          name: item.stuName,
          date: item.date,
          address: item.address
        }
        this.editVisible = true;
      },
      handleDelete(index, row) {
        this.idx = index;
        this.delVisible = true;
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 保存编辑
      saveEdit() {
        this.$set(this.tableData, this.idx, this.form);
        this.editVisible = false;
        this.$message.success(`修改第 ${this.idx+1} 行成功`);
      },
      // 确定删除
      deleteRow(){
        this.tableData.splice(this.idx, 1);
        this.$message.success('删除成功');
        this.delVisible = false;
      }
    }
  }

</script>

<style scoped>
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
    width: 120px;
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

<template>
  <div class="table">
    <div class="container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="预约信息" name="first">
          <div class="handle-box">
            <div class="select-stu">
              姓名：<el-input v-model="params.student.stuName" class="handle-input"></el-input>
              请假类型：<el-input v-model="params.student.dormitory" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="tableData">
              <el-table-column prop="account" align="center" label="学号" width="100">
              </el-table-column>
              <el-table-column prop="stuName" align="center" label="姓名" width="100">
              </el-table-column>
              <el-table-column prop="leaveType" align="center" :formatter="formatType" label="休假类型" width="100">
              </el-table-column>
              <el-table-column prop="operationTime" align="center" :formatter="formatOperationTime" label="申请时间" width="160">
              </el-table-column>
              <el-table-column prop="startTime" align="center" :formatter="formatStartTime" label="开始时间" width="160">
              </el-table-column>
              <el-table-column prop="endTime" align="center" :formatter="formatEndTime" label="结束时间" width="160">
              </el-table-column>
              <el-table-column prop="duration" align="center" label="时长" width="80">
              </el-table-column>
              <el-table-column prop="reason" align="center" label="事由" width="240">
              </el-table-column>
              <el-table-column prop="attachment" align="center" label="附件" width="100">
              </el-table-column>
              <el-table-column align="center" label="审批状态" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.status)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="rejectReason" align="center" label="驳回原因" width="240">
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination">
            <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
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
        activeName: 'first',
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
        studentAxios.post('getLeaveInfosByPage', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      formatOperationTime(row, column) {
        var date = new Date(row.operationTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
      },
      formatStartTime(row, column) {
        var date = new Date(row.startTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
      },
      formatEndTime(row, column) {
        var date = new Date(row.endTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
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

<template>
  <div class="table">
    <div class="container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="预约信息" name="first">
          <div class="handle-box">
            <div class="select-stu">
              <el-select v-model="selectType" @change="currentSel" placeholder="预约类型" class="handle-select">
                <el-option key="1" label="宿舍矛盾" value="1"></el-option>
                <el-option key="2" label="班级管理" value="2"></el-option>
                <el-option key="3" label="心理辅导" value="3"></el-option>
                <el-option key="4" label="思想交流" value="4"></el-option>
                <el-option key="5" label="职业规划" value="5"></el-option>
                <el-option key="6" label="其他" value="6"></el-option>
              </el-select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              姓名：<el-input v-model="params.appointment.stuName" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="tableData">
              <el-table-column prop="account" align="center" label="学号" width="100">
              </el-table-column>
              <el-table-column align="center" label="姓名" width="150">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForSearch(scope.row.stuName, params.appointment.stuName)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="leaveType" align="center" :formatter="formatType" label="休假类型" width="100">
              </el-table-column>
              <el-table-column prop="operationTime" align="center" :formatter="formatOperationTime" label="申请时间" width="160">
              </el-table-column>
              <el-table-column prop="startTime" align="center" :formatter="formatStartTime" label="开始时间" width="160">
              </el-table-column>
              <el-table-column prop="endTime" align="center" :formatter="formatEndTime" label="结束时间" width="160">
              </el-table-column>
              <el-table-column prop="reason" align="center" label="事由" width="240">
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
  </div>
</template>

<script>
  import axios from 'axios';
  import {formatDate} from "../../../static/js/date";
  export default {
    name: 'basetable',
    data() {
      return {
        activeName: 'first',
        selectType: '',
        tableData: [],
        params: {
          pageNum: '1',
          appointment: {
            stuName: '',
            insAccount: ''
          }
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
        this.params.appointment.insAccount = this.$store.state.account;
        var appointmentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/appointment/'
        });
        appointmentAxios.post('getAppointmentInfosByInsAccount', this.params).then(res => {
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
      currentSel(val) {
        this.params.appointment.appointmentType = val;
        this.getData();
      },
      search() {
        this.getData();
      },
      handleClick(tab, event) {
      var active = this.activeName;
      if (active == 'first') {
        this.getData();
      }
    },
      formatType(row, column) {
        var result = '';
        if (row.appointmentType == 1) {
          result = '宿舍矛盾';
        } else if (row.appointmentType == 2) {
          result = '班级管理';
        } else if (row.appointmentType == 3) {
          result = '心理辅导';
        } else if (row.appointmentType == 4) {
          result = '思想交流';
        } else if (row.appointmentType == 5) {
          result = '职业规划';
        } else {
          result = '其他';
        }
        return result;
      },
      brightenKeyword(val) {
        if (val == 0) {
          return '<font color="red">待审批</font>';
        } else if (val == 1) {
          return '<font color="green">通过</font>';
        } else {
          return '<font color="red">驳回</font>';
        }
      },
      brightenKeywordForSearch(val, keyword) {
        val = val + '';
        if (val.indexOf(keyword) !== -1 && keyword !== '') {
          return val.replace(keyword, '<font color="red">' + keyword + '</font>')
        } else {
          return val
        }
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

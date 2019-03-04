<template>
  <div class="table">
    <div class="container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="请假信息" name="first">
          <div class="handle-box">
            <div class="select-stu">
              <el-select v-model="selectType" @change="currentSel" placeholder="请假类型" class="handle-select">
                <el-option key="1" label="事假" value="1"></el-option>
                <el-option key="2" label="病假" value="2"></el-option>
                <el-option key="3" label="公假" value="3"></el-option>
                <el-option key="4" label="其他" value="4"></el-option>
              </el-select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              姓名：<el-input v-model="params.leave.stuName" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="tableData">
              <el-table-column prop="account" align="center" label="学号" width="100">
              </el-table-column>
              <el-table-column align="center" label="姓名" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForSearch(scope.row.stuName, params.leave.stuName)" ></span>
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
              <el-table-column prop="duration" align="center" label="时长" width="80">
              </el-table-column>
              <el-table-column prop="reason" align="center" label="事由" width="240">
              </el-table-column>
              <el-table-column align="center" label="审批状态" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.status)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="attachment" align="center" label="附件" width="100">
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
        <el-tab-pane label="请假排序" name="second">
          <div class="handle-table">
            <el-table :data="rankingData">
              <el-table-column prop="account" align="center" label="学号" width="150">
              </el-table-column>
              <el-table-column align="center" label="姓名" width="150">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForSearch(scope.row.stuName, params.leave.stuName)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="leaveTimes" align="center" label="请假次数" width="150">
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
        tableData: [],
        rankingData: [],
        selectType: '',
        params: {
          pageNum: '1',
          leave: {
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
        this.params.leave.insAccount = this.$store.state.account;
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        studentAxios.post('getLeaveInfosByInsAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getRankingData() {
        this.params.leave.insAccount = this.$store.state.account;
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        studentAxios.post('getLeaveRankingInfos', this.params).then(res => {
          if (res.data.code == 200) {
            this.rankingData = res.data.data;
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
        this.params.leave.leaveType = val;
        this.getData();
      },
      search() {
        this.getData();
      },
      handleClick(tab, event) {
        var active = this.activeName;
        if (active == 'first') {
          this.getData();
        } else {
          this.getRankingData();
        }
      },
      formatType(row, column) {
        var result = '';
        if (row.leaveType == 1) {
          result = '事假';
        } else if (row.leaveType == 2) {
          result = '病假';
        } else if (row.leaveType == 3) {
          result = '公假';
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

  .table{
    width: 100%;
    font-size: 14px;
  }
  .red{
    color: #ff0000;
  }
</style>

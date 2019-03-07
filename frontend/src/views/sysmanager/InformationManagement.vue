<template>
  <div class="table">
    <div class="container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="请假信息" name="first">
          <div class="handle-box">
            <div class="select-leave">
              学号：<el-input v-model="params.leave.account" class="handle-input"></el-input>
              姓名：<el-input v-model="params.leave.stuName" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="searchLeave">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="leaveData">
              <el-table-column prop="account" align="center" label="学号" width="100">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.account, params.leave.account)" ></span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="姓名" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.stuName, params.leave.stuName)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="leaveType" align="center" :formatter="formatLeaveType" label="休假类型" width="100">
              </el-table-column>
              <el-table-column prop="operationTime" align="center" :formatter="formatOperationTime" label="申请时间" width="160">
              </el-table-column>
              <el-table-column prop="reason" align="center" label="事由" width="240">
              </el-table-column>
              <el-table-column align="center" label="审批状态" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForStatus(scope.row.status)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="rejectReason" align="center" label="驳回原因" width="240">
              </el-table-column>
              <el-table-column label="操作" width="300px" align="center">
                <template slot-scope="scope">
                  <el-button type="text" icon="el-icon-edit" @click="handleLeaveEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="text" icon="el-icon-delete" class="red" @click="handleLeaveDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination">
            <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="预约信息" name="second">
          <div class="handle-box">
            <div class="select-appointment">
              学号：<el-input v-model="params.appointment.account" class="handle-input"></el-input>
              姓名：<el-input v-model="params.appointment.insName" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="searchAppointment">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="appointmentData">
              <el-table-column align="center" label="学号" width="100">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.account, params.appointment.account)" ></span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="姓名" width="150">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.stuName, params.appointment.stuName)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="leaveType" align="center" :formatter="formatAppointmentType" label="预约类型" width="100">
              </el-table-column>
              <el-table-column prop="operationTime" align="center" :formatter="formatOperationTime" label="申请时间" width="160">
              </el-table-column>
              <el-table-column prop="reason" align="center" label="事由" width="240">
              </el-table-column>
              <el-table-column align="center" label="审批状态" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForStatus(scope.row.status)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="rejectReason" align="center" label="驳回原因" width="240">
              </el-table-column>
              <el-table-column label="操作" width="300px" align="center">
                <template slot-scope="scope">
                  <el-button type="text" icon="el-icon-edit" @click="handleAppointmentEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="text" icon="el-icon-delete" class="red" @click="handleAppointmentDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination">
            <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="公告信息" name="three">
          <div class="handle-box">
            <div class="select-notice">
              工号：<el-input v-model="params.notice.insAccount" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="searchNotice">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="noticeData">
              <el-table-column prop="id" align="center" label="id" width="100px">
              </el-table-column>
              <el-table-column prop="noticeContent" align="center" label="公告内容" width="500px">
              </el-table-column>
              <el-table-column prop="noticeTime" align="center" :formatter="formatTime" label="公告时间" width="200px">
              </el-table-column>
              <el-table-column label="操作" width="300px" align="center">
                <template slot-scope="scope">
                  <el-button type="text" icon="el-icon-edit" @click="handleAppointmentEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="text" icon="el-icon-delete" class="red" @click="handleAppointmentDelete(scope.$index, scope.row)">删除</el-button>
                </template>
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

    <!-- 编辑请假信息弹出框 -->
    <el-dialog title="编辑请假信息" :visible.sync="editLeaveVisible" width="25%">
      <el-form ref="leaveForm" :model="params.leave" label-width="120px">
        <el-form-item label="学号" prop="account">
          <el-input v-model="params.leave.account" :disabled="true" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="params.leave.stuName" :disabled="true" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="请假事由" prop="reason">
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.leave.reason" class="handle-textarea">
          </el-input>
        </el-form-item>
        <el-form-item label="驳回原因" prop="reason">
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.leave.rejectReason" class="handle-textarea">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveLeave">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 删除请假信息提示框 -->
    <el-dialog title="提示" :visible.sync="delLeaveVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delLeaveVisible = false">取 消</el-button>
                <el-button type="primary" @click="delLeaveRow">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 编辑预约信息弹出框 -->
    <el-dialog title="编辑预约信息" :visible.sync="editAppointmentVisible" width="25%">
      <el-form ref="appointmentForm" :model="params.appointment" label-width="120px">
        <el-form-item label="学号" prop="account">
          <el-input v-model="params.appointment.account" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="insName">
          <el-input v-model="params.appointment.insName" class="handle-input"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editAppointmentVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAppointment">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 删除预约信息提示框 -->
    <el-dialog title="提示" :visible.sync="delAppointmentVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delAppointmentVisible = false">取 消</el-button>
                <el-button type="primary" @click="delAppointmentRow">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 编辑公告信息弹出框 -->
    <el-dialog title="编辑公告信息" :visible.sync="editNoticeVisible" width="25%">
      <el-form ref="instructorForm" :model="params.notice" label-width="120px">
        <el-form-item label="工号" prop="account">
          <el-input v-model="params.notice.insAccount" class="handle-input"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editNoticeVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveNotice">确 定</el-button>
            </span>
    </el-dialog>

    <!-- 删除公告信息提示框 -->
    <el-dialog title="提示" :visible.sync="delNoticeVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delNoticeVisible = false">取 消</el-button>
                <el-button type="primary" @click="delNoticeRow">确 定</el-button>
            </span>
    </el-dialog>
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
        leaveData: [],
        appointmentData: [],
        noticeData: [],
        params: {
          pageNum: '1',
          leave: {
            id: '',
            leaveType: '',
            account: '',
            stuName: '',
            reason: '',
            attachment: '',
            status: '0',
            orderNumber: '',
            rejectReason: ''
          },
          appointment: {
            id: '',
            appointmentType: '',
            account: '',
            stuName: '',
            startTime: '',
            endTime: '',
            reason: '',
            status: '0',
            orderNumber: '',
            rejectReason: '',
            appointmentNumber: ''
          },
          notice: {
            id: '',
            insAccount: '',
            noticeContent: '',
            noticeTime: ''
          }
        },
        editLeaveVisible: false,
        editAppointmentVisible: false,
        editNoticeVisible: false,
        delLeaveVisible: false,
        delAppointmentVisible: false,
        delNoticeVisible: false
      }
    },
    methods: {
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
        this.getData();
      },
      getLeaveData() {
        var leaveAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        leaveAxios.post('getLeaveInfosByStuAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.leaveData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getAppointmentData() {
        var appointmentAios = axios.create({
          baseURL: 'http://localhost:8080/api/appointment/'
        });
        appointmentAios.post('getAppointmentInfosByStuAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.appointmentData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getNoticeData() {
        var unreadAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notice/'
        });
        unreadAxios.post('getNoticeInfosByInsAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.noticeData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      brightenKeywordForStatus(val) {
        if (val == 0) {
          return '<font color="red">待审批</font>';
        } else if (val == 1) {
          return '<font color="green">通过</font>';
        } else {
          return '<font color="red">驳回</font>';
        }
      },
      brightenKeyword(val, keyword) {
        val = val + '';
        if (val.indexOf(keyword) !== -1 && keyword !== '') {
          return val.replace(keyword, '<font color="red">' + keyword + '</font>')
        } else {
          return val
        }
      },
      formatOperationTime(row, column) {
        var date = new Date(row.operationTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
      },
      formatTime(row, column) {
        var date = new Date(row.noticeTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
      },
      formatLeaveType(row, column) {
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
      formatAppointmentType(row, column) {
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
      handleClick(tab, event) {
        var active = this.activeName;
        if (active == 'first') {
          this.getLeaveData();
        } else if (active == 'second') {
          this.getAppointmentData();
        } else {
          this.getNoticeData();
        }
      },
      searchAppointment() {
        this.getAppointmentData();
      },
      searchLeave() {
        this.getLeaveData();
      },
      searchNotice() {
        this.getNoticeData();
      },
      saveLeave() {
        this.editLeaveVisible = false;
        var leaveAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        leaveAxios.post('updateLeaveInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("更新请假信息成功!");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      saveAppointment() {
        this.editVisible = false;
        this.params.token = sessionStorage.getItem("access-token");
        this.params.leave.stuName = this.$store.state.name;
        this.params.leave.account = this.$store.state.account;
        this.params.leave.insAccount = this.$store.state.insAccount;
        var leaveAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        leaveAxios.post('insertLeaveInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("请假已进入审批中");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      saveNotice() {
        this.editNoticeVisible = false;
        this.params.token = sessionStorage.getItem("access-token");
        this.params.leave.stuName = this.$store.state.name;
        this.params.leave.account = this.$store.state.account;
        this.params.leave.insAccount = this.$store.state.insAccount;
        var leaveAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        leaveAxios.post('insertLeaveInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("请假已进入审批中");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      delLeaveRow() {
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
      },
      delAppointmentRow() {
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
      },
      delNoticeRow() {
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
      },
      handleLeaveEdit(index, row) {
        const item = this.leaveData[index];
        this.params.leave = {
          id: item.id,
          account: item.account,
          stuName: item.stuName,
          leaveType: item.leaveType,
          operationTime: item.operationTime,
          reason: item.reason,
          status: item.status,
          rejectReason: item.rejectReason
        };
        this.editLeaveVisible = true;
      },
      handleAppointmentEdit(index, row) {
        const item = this.appointmentData[index];
        this.editAppointmentVisible = true;
      },
      handleNoticeEdit(index, row) {
        const item = this.noticeData[index];
        this.editNoticeVisible = true;
      },
      handleLeaveDelete(index, row) {
        this.params.leave.id = row.id;
        this.delLeaveVisible = true;
      },
      handleAppointmentDelete(index, row) {
        this.params.appointment.id = row.id;
        this.delAppointmentVisible = true;
      },
      handleNoticeDelete(index, row) {
        this.params.notice.id = row.id;
        this.delNoticeVisible = true;
      },
      handleDelete(index, row) {
        this.params.instructor.id = row.id;
        this.delVisible = true;
      },
    },
    created() {
      this.getLeaveData();
    }
  }

</script>

<style scoped>
  .table{
    width: 100%;
    font-size: 14px;
  }
  .handle-box {
    margin-bottom: 10px;
  }
  .select-leave {
    float: left;
    padding-left: 200px;
    padding-bottom: 20px;
  }
  .select-appointment {
    float: left;
    padding-left: 200px;
    padding-bottom: 20px;
  }
  .select-notice {
    float: left;
    padding-left: 200px;
    padding-bottom: 20px;
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

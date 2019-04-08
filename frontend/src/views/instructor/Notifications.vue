<template>
  <div class="table">
      <div class="container">
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
          <el-tab-pane label="未处理" name="first">
          <div class="handle-table">
            <el-table :data="unreadData">
              <el-table-column prop="newsType" align="center" :formatter="formatType" label="消息类型" width="150">
              </el-table-column>
              <el-table-column prop="account" align="center" label="学号" width="230">
              </el-table-column>
              <el-table-column prop="stuName" align="center" label="姓名" width="230">
              </el-table-column>
              <el-table-column label="操作" width="250" align="center">
                <template slot-scope="scope">
                  <el-button type="danger" icon="el-icon-edit" @click="handleUnreadEdit(scope.$index, scope.row)">处理</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination">
            <el-pagination background @current-change="handleUnreadDataChange" layout="prev, pager, next" :total="500">
            </el-pagination>
          </div>
          </el-tab-pane>
          <el-tab-pane label="已处理" name="second">
            <div class="handle-table">
              <el-table :data="readData">
                <el-table-column prop="newsType" align="center" :formatter="formatType" label="消息类型" width="150">
                </el-table-column>
                <el-table-column prop="account" align="center" label="学号" width="230">
                </el-table-column>
                <el-table-column prop="stuName" align="center" label="姓名" width="230">
                </el-table-column>
                <el-table-column label="操作" width="250" align="center">
                  <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-search" @click="handleReadEdit(scope.$index, scope.row)">查看</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div class="pagination">
              <el-pagination background @current-change="handleReadDataChange" layout="prev, pager, next" :total="500">
              </el-pagination>
            </div>
          </el-tab-pane>
          <el-tab-pane label="班级负责人" name="third">
            <div class="handle-box">
              <div class="punishment-btn">
                <el-button type="primary" @click="addMonitor">添加负责人</el-button>
              </div>
            </div>
            <div class="handle-table">
              <el-table :data="monitorData">
                <el-table-column prop="account" align="center" label="学号" width="120px">
                </el-table-column>
                <el-table-column prop="name" align="center" label="姓名" width="150px">
                </el-table-column>
                <el-table-column prop="phoneNum" align="center" label="手机号码" width="150px">
                </el-table-column>
                <el-table-column label="操作" width="300px" align="center">
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
          </el-tab-pane>
        </el-tabs>
        </div>

    <!-- 请假消息弹出框 -->
    <el-dialog title="请假信息" :visible.sync="editLeaveVisible" width="25%">
      <el-form ref="form" :model="params.leave">
        <div class="account">
          <span class="demonstration">学号：</span>
          {{params.leave.account}}
        </div>
        <div class="stuName">
          <span class="demonstration">姓名：</span>
          {{params.leave.stuName}}
        </div>
        <div class="leave-type">
          <span class="demonstration">休假类型：</span>
          {{params.leave.leaveType}}
        </div>
        <div class="start-time">
          <span class="demonstration">开始时间：</span>
          {{params.leave.startTime}}
        </div>
        <div class="end-time">
          <span class="demonstration">结束时间：</span>
          {{params.leave.endTime}}
        </div>
        <div class="duration">
          <span class="demonstration">时长：</span>
          {{params.leave.duration}}
        </div>
        <div class="cause">
          <span class="demonstration">事由：</span>
          {{params.leave.reason}}
        </div>
        <div class="attachment">
          <span class="demonstration">附件：</span>
          {{params.leave.attachment}}
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="leaveAgree">同意</el-button>
        <el-button type="danger" @click="leaveReject">驳回</el-button>
        <el-button @click="editLeaveVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 预约消息弹出框 -->
    <el-dialog title="预约信息" :visible.sync="editAppointmentVisible" width="25%">
      <el-form ref="form" :model="params.appointment">
        <div class="account">
          <span class="demonstration">学号：</span>
          {{params.appointment.account}}
        </div>
        <div class="stuName">
          <span class="demonstration">姓名：</span>
          {{params.appointment.stuName}}
        </div>
        <div class="leave-type">
          <span class="demonstration">预约类型：</span>
          {{params.appointment.appointmentType}}
        </div>
        <div class="leave-type">
          <span class="demonstration">预约人数：</span>
          {{params.appointment.appointmentNumber}}
        </div>
        <div class="start-time">
          <span class="demonstration">开始时间：</span>
          {{params.appointment.startTime}}
        </div>
        <div class="end-time">
          <span class="demonstration">结束时间：</span>
          {{params.appointment.endTime}}
        </div>
        <div class="cause">
          <span class="demonstration">事由：</span>
          {{params.appointment.reason}}
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="appointmentAgree">同意</el-button>
        <el-button type="danger" @click="appointmentReject">驳回</el-button>
        <el-button @click="editAppointmentVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 查看请假信息弹出框 -->
    <el-dialog title="请假信息" :visible.sync="checkLeaveVisible" width="25%">
      <el-form ref="form" :model="params.leave">
        <div class="status">
          <span class="demonstration">审批状态：</span>
          <span v-html="brightenKeyword(params.leave.status)" ></span>
        </div>
        <div class="rejectReason">
          <span class="demonstration">驳回理由：</span>
          {{params.leave.rejectReason}}
        </div>
        <div class="account">
          <span class="demonstration">学号：</span>
          {{params.leave.account}}
        </div>
        <div class="stuName">
          <span class="demonstration">姓名：</span>
          {{params.leave.stuName}}
        </div>
        <div class="leave-type">
          <span class="demonstration">休假类型：</span>
          {{params.leave.leaveType}}
        </div>
        <div class="start-time">
          <span class="demonstration">开始时间：</span>
          {{params.leave.startTime}}
        </div>
        <div class="end-time">
          <span class="demonstration">结束时间：</span>
          {{params.leave.endTime}}
        </div>
        <div class="duration">
          <span class="demonstration">时长：</span>
          {{params.leave.duration}}
        </div>
        <div class="cause">
          <span class="demonstration">事由：</span>
          {{params.leave.reason}}
        </div>
        <div class="attachment">
          <span class="demonstration">附件：</span>
          {{params.leave.attachment}}
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="checkLeaveVisible = false">确定</el-button>
      </span>
    </el-dialog>

    <!-- 查看预约信息弹出框 -->
    <el-dialog title="请假信息" :visible.sync="checkAppointmentVisible" width="25%">
      <el-form ref="form" :model="params.appointment">
        <div class="status">
          <span class="demonstration">审批状态：</span>
          <span v-html="brightenKeyword(params.appointment.status)" ></span>
        </div>
        <div class="rejectReason">
          <span class="demonstration">驳回理由：</span>
          {{params.appointment.rejectReason}}
        </div>
        <div class="account">
          <span class="demonstration">学号：</span>
          {{params.appointment.account}}
        </div>
        <div class="stuName">
          <span class="demonstration">姓名：</span>
          {{params.appointment.stuName}}
        </div>
        <div class="leave-type">
          <span class="demonstration">预约类型：</span>
          {{params.appointment.appointmentType}}
        </div>
        <div class="leave-type">
          <span class="demonstration">预约人数：</span>
          {{params.appointment.appointmentNumber}}
        </div>
        <div class="start-time">
          <span class="demonstration">开始时间：</span>
          {{params.appointment.startTime}}
        </div>
        <div class="end-time">
          <span class="demonstration">结束时间：</span>
          {{params.appointment.endTime}}
        </div>
        <div class="cause">
          <span class="demonstration">事由：</span>
          {{params.appointment.reason}}
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="checkAppointmentVisible = false">确定</el-button>
      </span>
    </el-dialog>

    <!-- 驳回请假弹出框 -->
    <el-dialog title="驳回理由" :visible.sync="editLeaveRejectVisible" width="25%">
      <el-form ref="form" :model="params.leave">
        <div class="reject-reason">
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.leave.rejectReason" class="handle-textarea">
          </el-input>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmLeaveReject">确认</el-button>
        <el-button @click="editLeaveRejectVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 驳回预约弹出框 -->
    <el-dialog title="驳回理由" :visible.sync="editAppointmentRejectVisible" width="25%">
      <el-form ref="form" :model="params.appointment">
        <div class="reject-reason">
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.appointment.rejectReason" class="handle-textarea">
          </el-input>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmAppointmentReject">确认</el-button>
        <el-button @click="editAppointmentRejectVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 编辑负责人信息-->
    <el-dialog title="编辑负责人信息" :visible.sync="editMonitorVisible" width="25%">
      <el-form ref="monitorForm" :rules="rules" :model="params.monitor" label-width="120px">
        <el-form-item label="学号" prop="account">
          <el-input v-model="params.monitor.account" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="params.monitor.name" class="handle-input"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNum">
          <el-input v-model="params.monitor.phoneNum" class="handle-input"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editMonitorVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveMonitor">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="提示" :visible.sync="delMonitorVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delMonitorVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
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
        params: {
          pageNum: '1',
          leave: {
            leaveType: '',
            account: '',
            stuName: '',
            startTime: '',
            endTime: '',
            duration: '',
            reason: '',
            attachment: '',
            status: '0',
            orderNumber: '',
            rejectReason: ''
          },
          appointment: {
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
          monitor: {
            id: '',
            name: '',
            account: '',
            phoneNum: '',
            insAccount: ''
          }
        },
        rules: {
          account: [
            {required: true, message: '请输入学号', trigger: 'blur'},
            {min: 9, max: 9, message: '长度为9个数字', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
          ],
          phoneNum: [
            {required: true, message: '请输入手机号码', trigger: 'blur'},
          ]
        },
        unreadParams: {
          pageNum: '1',
          notification: {
            isAccount: '',
            isRead: '',
            orderNumber: ''
          }
        },
        readParams: {
          pageNum: '1',
          notification: {
            isAccount: '',
            isRead: '',
            orderNumber: ''
          }
        },
        editLeaveVisible: false,
        editAppointmentVisible: false,
        editLeaveRejectVisible: false,
        editAppointmentRejectVisible: false,
        editMonitorVisible: false,
        checkLeaveVisible: false,
        checkAppointmentVisible: false,
        delMonitorVisible: false,
        unreadData: [],
        readData: [],
        monitorData: [],
        isUpdate: false,
        ws: null
      }
    },
    methods: {
      // 分页导航
      handleUnreadDataChange(val) {
        this.unreadParams.pageNum = val;
        this.getUnreadData();
      },
      handleReadDataChange(val) {
        this.readParams.pageNum = val;
        this.getReadData();
      },
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
        this.getMonitorData();
      },
      getUnreadData() {
        var unreadAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notification/'
        });
        this.unreadParams.notification.isAccount = this.$store.state.account;
        this.unreadParams.notification.isRead = 0;
        unreadAxios.post('getNotificationsByPage', this.unreadParams).then(res => {
          if (res.data.code == 200) {
            this.unreadData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getReadData() {
        var unreadAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notification/'
        });
        this.readParams.notification.isAccount = this.$store.state.account;
        this.readParams.notification.isRead = 1;
        unreadAxios.post('getNotificationsByPage', this.readParams).then(res => {
          if (res.data.code == 200) {
            this.readData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getLeaveData() {
        var leaveAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        leaveAxios.post('getLeaveInfoByOrderNumber', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.leave = (res.data.data)[0];
            var type = this.params.leave.leaveType;
            if (type == 1) {
              this.params.leave.leaveType = '事假';
            } else if (type == 2) {
              this.params.leave.leaveType = '病假';
            } else if (type == 3) {
              this.params.leave.leaveType = '公假';
            } else {
              this.params.leave.leaveType = '其他';
            }
            var startDate = new Date(this.params.leave.startTime);
            var endDate = new Date(this.params.leave.endTime);
            this.params.leave.startTime = formatDate(startDate, "yyyy-MM-dd hh:mm:ss");
            this.params.leave.endTime = formatDate(endDate, "yyyy-MM-dd hh:mm:ss");

            if (this.params.leave.attachment == null || this.params.leave.attachment == "") {
              this.params.leave.attachment = "无";
            }
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getAppointmentData() {
        var appointmentAios = axios.create({
          baseURL: 'http://localhost:8080/api/appointment/'
        });
        appointmentAios.post('getAppointmentInfoByOrderNumber', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.appointment = (res.data.data)[0];
            var type = this.params.appointment.appointmentType;
            if (type == 1) {
              this.params.appointment.appointmentType = '宿舍矛盾';
            } else if (type == 2) {
              this.params.appointment.appointmentType = '班级管理';
            } else if (type == 3) {
              this.params.appointment.appointmentType = '心理辅导';
            } else if (type == 4) {
              this.params.appointment.appointmentType = '思想交流';
            } else if (type == 5) {
              this.params.appointment.appointmentType = '职业规划';
            }else {
              this.params.appointment.appointmentType = '其他';
            }
            var startDate = new Date(this.params.appointment.startTime);
            var endDate = new Date(this.params.appointment.endTime);
            this.params.appointment.startTime = formatDate(startDate, "yyyy-MM-dd hh:mm:ss");
            this.params.appointment.endTime = formatDate(endDate, "yyyy-MM-dd hh:mm:ss");
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getMonitorData() {
        var monitorAxios = axios.create({
          baseURL: 'http://localhost:8080/api/monitor/'
        });
        this.params.monitor.insAccount = this.$store.state.account;
        monitorAxios.post('getMonitorInfosByInsAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.monitorData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
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
      formatType(row, column) {
        var result = '';
        if (row.newsType == 1) {
          result = '请假';
        } else if (row.newsType == 2) {
          result = '预约';
        } else {
          result = '其他';
        }
        return result;
      },
      handleClick(tab, event) {
        var active = this.activeName;
        if (active == 'first') {
          this.getUnreadData();
        } else if (active == 'second'){
          this.getReadData();
        } else {
          this.getMonitorData();
        }
      },
      handleUnreadEdit(index, row) {
        const item = this.unreadData[index];
        if (item.newsType == 1) {
          this.handleUnreadLeaveEdit(index, row);
        } else if (item.newsType == 2) {
          this.handleUnreadAppointmentEdit(index, row);
        }
      },
      handleUnreadLeaveEdit(index, row) {
        const item = this.unreadData[index];
        this.params.leave = {};
        this.params.leave.orderNumber = item.orderNumber;
        this.unreadParams.notification.orderNumber = item.orderNumber;
        this.getLeaveData();
        this.editLeaveVisible = true;
      },
      handleUnreadAppointmentEdit(index, row) {
        const item = this.unreadData[index];
        this.params.appointment = {},
        this.params.appointment.orderNumber = item.orderNumber
        this.unreadParams.notification.orderNumber = item.orderNumber;
        this.getAppointmentData();
        this.editAppointmentVisible = true;
      },
      handleReadEdit(index, row) {
        const item = this.readData[index];
        if (item.newsType == 1) {
          this.handleReadLeaveEdit(index, row);
        } else if (item.newsType == 2) {
          this.handleReadAppointmentEdit(index, row);
        }
      },
      handleReadLeaveEdit(index, row) {
        const item = this.readData[index];
        this.params.leave = {};
        this.params.leave.orderNumber = item.orderNumber;
        this.readParams.notification.orderNumber = item.orderNumber;
        this.getLeaveData();
        this.checkLeaveVisible = true;
      },
      handleReadAppointmentEdit(index, row) {
        const item = this.readData[index];
        this.params.appointment = {};
          this.params.appointment.orderNumber = item.orderNumber;
        this.readParams.notification.orderNumber = item.orderNumber;
        this.getAppointmentData();
        this.checkAppointmentVisible = true;
      },
      leaveAgree() {
        this.$confirm('您确认同意吗?').then(e => {
          this.websocketsend("leave-agree-" + this.params.leave.orderNumber);
          var leaveAxios = axios.create({
            baseURL: 'http://localhost:8081/api/notification/'
          });
          this.unreadParams.notification.isRead = 1;
          leaveAxios.post('updateNotificationInfo', this.unreadParams).then(res => {
            if (res.data.code == 200) {
              this.$message.success("同意请假!");
              this.getUnreadData();
              this.editLeaveVisible = false;
            } else {
              this.$message.error(res.data.msg);
            }
          });
        });
      },
      appointmentAgree() {
        this.$confirm('您确认同意吗?').then(e => {
          this.websocketsend("appointment-agree-" + this.params.appointment.orderNumber);
          var leaveAxios = axios.create({
            baseURL: 'http://localhost:8081/api/notification/'
          });
          this.unreadParams.notification.isRead = 1;
          leaveAxios.post('updateNotificationInfo', this.unreadParams).then(res => {
            if (res.data.code == 200) {
              this.$message.success("同意预约!");
              this.getUnreadData();
              this.editAppointmentVisible = false;
            } else {
              this.$message.error(res.data.msg);
            }
          });
        });
      },
      leaveReject() {
        this.editLeaveRejectVisible = true;
      },
      appointmentReject() {
        this.editAppointmentRejectVisible = true;
      },
      addMonitor() {
        this.params.monitor = {};
        this.editMonitorVisible = true;
      },
      confirmLeaveReject() {
        this.$confirm('您确认驳回吗?').then(e => {
          this.websocketsend("leave-reject-" + this.params.leave.orderNumber + "-" + this.params.leave.rejectReason);
          var leaveAxios = axios.create({
            baseURL: 'http://localhost:8081/api/notification/'
          });
          this.unreadParams.notification.isRead = 1;
          leaveAxios.post('updateNotificationInfo', this.unreadParams).then(res => {
            if (res.data.code == 200) {
              this.$message.success("已经驳回!");
              this.getUnreadData();
              this.editLeaveRejectVisible = false;
              this.editLeaveVisible = false;
            } else {
              this.$message.error(res.data.msg);
            }
          });
        });
      },
      confirmAppointmentReject() {
        this.$confirm('您确认驳回吗?').then(e => {
          this.websocketsend("appointment-reject-" + this.params.appointment.orderNumber + "-" + this.params.leave.rejectReason);
          var leaveAxios = axios.create({
            baseURL: 'http://localhost:8081/api/notification/'
          });
          this.unreadParams.notification.isRead = 1;
          leaveAxios.post('updateNotificationInfo', this.unreadParams).then(res => {
            if (res.data.code == 200) {
              this.$message.success("已经驳回!");
              this.getUnreadData();
              this.editAppointmentRejectVisible = false;
              this.editAppointmentVisible = false;
            } else {
              this.$message.error(res.data.msg);
            }
          });
        });
      },
      // 保存负责人信息
      saveMonitor() {
        this.$refs.monitorForm.validate((valid) => {
          if (valid) {
            this.editMonitorVisible = false;
            var monitorAxios = axios.create({
              baseURL: 'http://localhost:8080/api/monitor/'
            });
            if (this.isUpdate) {
              monitorAxios.post('updateMonitorInfo', this.params).then(res => {
                if (res.data.code == 200) {
                  this.$message.success("修改负责人信息成功!");
                  this.isUpdate = false;
                  this.getMonitorData();
                } else {
                  this.$message.error(res.data.msg);
                }
              });
              return;
            }
            monitorAxios.post('insertMonitorInfo', this.params).then(res => {
              if (res.data.code == 200) {
                this.$message.success("增加负责人信息成功!");
                this.getMonitorData();
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
        var monitorAxios = axios.create({
          baseURL: 'http://localhost:8080/api/monitor/'
        });
        monitorAxios.post('deleteMonitorInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("删除学生信息成功!");
            this.getMonitorData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
        this.delMonitorVisible = false;
      },
      handleEdit(index, row) {
        const item = this.monitorData[index];
        this.params.monitor = {
          id: item.id,
          account: item.account,
          name: item.name,
          phoneNum: item.phoneNum
        };
        this.editMonitorVisible = true;
        this.isUpdate = true;
      },
      handleDelete(index, row) {
        this.params.monitor.id = row.id;
        this.delMonitorVisible = true;
      },
      initWebSocket() {
        this.ws = new WebSocket('ws://localhost:8080/websocket/instructor');
        this.ws.onopen = this.websocketonopen;
        this.ws.onerror = this.websocketonerror;
        this.ws.onmessage = this.websocketonmessage;
        this.ws.onclose = this.websocketclose;
        // 组件销毁时调用，中断websocket连接
        this.over = () => {
          ws.close();
        }
      },
      websocketonopen() {

      },
      websocketonerror() {
        console.log("WebSocket连接发生错误");
      },
      websocketonmessage(e) { //数据接收
        console.log(e.data);
      },
      websocketsend(agentData){//数据发送
        this.ws.send(agentData);
      },
      websocketclose(e) {
        console.log("connection closed (" + e.code + ")");
      }
    },
    created() {
      this.getUnreadData();
      this.initWebSocket();
    }
  }

</script>

<style scoped>
  .status {
    padding-left: 30px;
  }
  .rejectReason {
    padding-left: 30px;
    padding-top: 15px;
  }
  .account {
    padding-left: 30px;
    padding-top: 15px;
  }
  .stuName {
    padding-left: 30px;
    padding-top: 15px;
  }
  .start-time {
    padding-left: 30px;
    padding-top: 15px;
  }
  .end-time {
    padding-left: 30px;
    padding-top: 15px;
  }
  .leave-type {
    padding-left: 30px;
    padding-top: 15px;
  }
  .duration {
    padding-left: 30px;
    padding-top: 15px;
  }
  .cause {
    padding-left: 30px;
    padding-top: 15px;
    line-height: 25px;
  }
  .attachment {
    padding-left: 30px;
    padding-top: 15px;
  }
  .table{
    width: 100%;
    font-size: 14px;
  }
  .handle-input {
    width: 200px;
    display: inline-block;
  }
  .red{
    color: #ff0000;
  }
</style>

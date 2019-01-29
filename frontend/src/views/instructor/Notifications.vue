<template>
  <div class="table">
      <div class="container">
        <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
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
                    <el-button type="danger" icon="el-icon-edit" @click="handleReadEdit(scope.$index, scope.row)">处理</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div class="pagination">
              <el-pagination background @current-change="handleReadDataChange" layout="prev, pager, next" :total="500">
              </el-pagination>
            </div>
          </el-tab-pane>
        </el-tabs>
        </div>

    <!-- 请假消息弹出框 -->
    <el-dialog title="请假信息" :visible.sync="editVisible" width="25%">
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
        <el-button @click="editVisible = false">取 消</el-button>
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
        activeName2: 'first',
        params: {
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
            orderNumber: ''
          }
        },
        unreadParams: {
          pageNum: '1',
          notification: {
            isAccount: '',
            isRead: ''
          }
        },
        readParams: {
          pageNum: '1',
          notification: {
            isAccount: '',
            isRead: ''
          }
        },
        editVisible: false,
        unreadData: [],
        readData: [],
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
      },
      handleUnreadEdit(index, row) {
        const item = this.unreadData[index];
        if (item.newsType == 1) {
          this.handleUnreadLeaveEdit(index, row);
        }
      },
      handleUnreadLeaveEdit(index, row) {
        const item = this.unreadData[index];
        this.params.leave.orderNumber = item.orderNumber;
        this.getLeaveData();
        this.editVisible = true;
      },
      handleReadEdit(index, row) {
        this.idx = index;
        const item = this.unreadData[index];
        this.editVisible = true;
      },
      leaveAgree() {
        this.websocketsend("leave-agree-" + this.params.leave.orderNumber);
        this.editVisible = false;
      },
      leaveReject() {
        this.websocketsend("reject");
        this.editVisible = false;
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
        console.log("8080连接成功!");
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
  .account {
    padding-left: 30px;
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
</style>

<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <div class="appointment-btn">
          <el-button type="primary" @click="showAppointment">预约</el-button>
        </div>
      </div>
      <div class="handle-table">
        <el-table :data="tableData">
          <el-table-column prop="appointmentType" align="center" :formatter="formatType" label="预约类型" width="100">
          </el-table-column>
          <el-table-column prop="operationTime" align="center" :formatter="formatOperationTime" label="申请时间" width="160">
          </el-table-column>
          <el-table-column prop="startTime" align="center" :formatter="formatStartTime" label="开始时间" width="160">
          </el-table-column>
          <el-table-column prop="endTime" align="center" :formatter="formatEndTime" label="结束时间" width="160">
          </el-table-column>
          <el-table-column prop="appointmentNumber" align="center" :formatter="formatNum" label="预约人数" width="100">
          </el-table-column>
          <el-table-column prop="reason" align="center" label="事由" width="260">
          </el-table-column>
          <el-table-column align="center" label="审批状态" width="120">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.status)" ></span>
            </template>
          </el-table-column>
          <el-table-column prop="rejectReason" align="center" label="驳回原因" width="300">
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
        </el-pagination>
      </div>
    </div>
    <!-- 请假弹出框 -->
    <el-dialog title="预约" :visible.sync="editVisible" width="42%">
      <el-form ref="form" :model="params.appointment">
        <div class="start-time">
          <span class="demonstration"><font color="red">*  </font>开始时间</span>
          <el-date-picker
            v-model="params.appointment.startTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="08:00:00">
          </el-date-picker>
        </div>
        <div class="end-time">
          <span class="demonstration"><font color="red">*  </font>结束时间</span>
          <el-date-picker
            v-model="params.appointment.endTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="20:30:00">
          </el-date-picker>
        </div>
        <div class="appointment-type">
          <span class="demonstration"><font color="red">*  </font>预约类型</span>
          <el-select v-model="selectType" @change="currentSelType" placeholder="选择类型" class="handle-select">
            <el-option key="1" label="宿舍矛盾" value="1"></el-option>
            <el-option key="2" label="班级管理" value="2"></el-option>
            <el-option key="3" label="心理辅导" value="3"></el-option>
            <el-option key="4" label="思想交流" value="4"></el-option>
            <el-option key="5" label="职业规划" value="5"></el-option>
            <el-option key="6" label="其他" value="6"></el-option>
          </el-select>
        </div>
        <div class="appointment-num">
          <span class="demonstration"><font color="red">*  </font>预约人数</span>
          <el-select v-model="selectNum" @change="currentSelNum" placeholder="选择人数" class="handle-select">
            <el-option key="1" label="1个" value="1"></el-option>
            <el-option key="2" label="2个" value="2"></el-option>
            <el-option key="3" label="3个" value="3"></el-option>
            <el-option key="4" label="4个" value="4"></el-option>
            <el-option key="5" label="5个以上" value="5"></el-option>
          </el-select>
        </div>
        <div class="cause">
          <span class="demonstration">事由</span>
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.appointment.reason" class="handle-textarea">
          </el-input>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveAppointment">确 定</el-button>
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
        params: {
          token: '',
          pageNum: '1',
          appointment: {
            appointmentType: '',
            account: '',
            stuName: '',
            startTime: '',
            endTime: '',
            reason: '',
            appointmentNumber: '',
            status: '0',
            insAccount: ''
          }
        },
        selectType: '',
        selectNum: '',
        editVisible: false,
        tableData: []
      }
    },
    methods: {
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
        this.getData();
      },
      getData() {
        this.params.appointment.account = this.$store.state.account;
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/appointment/'
        });
        studentAxios.post('getAppointmentInfosByStuAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      showAppointment() {
        this.editVisible = true;
      },
      saveAppointment() {
        this.editVisible = false;
        this.params.token = sessionStorage.getItem("access-token");
        this.params.appointment.stuName = this.$store.state.name;
        this.params.appointment.account = this.$store.state.account;
        this.params.appointment.insAccount = this.$store.state.insAccount;
        this.params.phoneNum = this.$store.state.phoneNum;
        var appointmentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/appointment/'
        });
        appointmentAxios.post('insertAppointmentInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("请假已进入审批中");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      currentSelType(val) {
        this.params.appointment.appointmentType = val;
      },
      currentSelNum(val) {
        this.params.appointment.appointmentNumber = val;
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
      formatNum(row, column) {
        var result = '';
        if (row.appointmentNumber == 1) {
          result = '1个';
        } else if (row.appointmentNumber == 2) {
          result = '2个';
        } else if (row.appointmentNumber == 3) {
          result = '3个';
        } else if (row.appointmentNumber == 4) {
          result = '4个';
        } else if (row.appointmentNumber == 5) {
          result = '5个以上';
        }
        return result;
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
      brightenKeyword(val) {
        if (val == 0) {
          return '<font color="red">待审批</font>';
        } else if (val == 1) {
          return '<font color="green">通过</font>';
        } else if (val == 2) {
          return '<font color="red">驳回</font>';
        }
      }
    },
    created() {
      this.getData();
    }
  }

</script>

<style scoped>
  .start-time {
    float: left;
  }

  .end-time {
    float: right;
    padding-left: 10px;
  }

  .appointment-type {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .appointment-num {
    float: right;
    padding-top: 30px;
    padding-left: 10px;
  }

  .cause {
    clear: both;
    padding-top: 30px;
  }

  .handle-textarea {
    width: 94%;
    float: right;
  }

  .attachment {
    clear: both;
    padding-top: 30px;
  }

  .upload-demo {
    float: right;
    padding-right: 500px;
  }

  .handle-box {
    margin-bottom: 10px;
  }

  .table{
    width: 100%;
    font-size: 14px;
  }
</style>

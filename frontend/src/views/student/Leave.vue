<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <div class="leave-btn">
          <el-button type="primary" @click="showLeave">请假</el-button>
        </div>
      </div>
      <div class="handle-table">
        <el-table :data="tableData">
          <el-table-column prop="leaveType" align="center" :formatter="formatType" label="休假类型" width="150">
          </el-table-column>
          <el-table-column prop="startTime" align="center" :formatter="formatStartTime" label="开始时间" width="230">
          </el-table-column>
          <el-table-column prop="endTime" align="center" :formatter="formatEndTime" label="结束时间" width="230">
          </el-table-column>
          <el-table-column prop="duration" align="center" label="时长" width="100">
          </el-table-column>
          <el-table-column prop="reason" align="center" label="事由" width="240">
          </el-table-column>
          <el-table-column prop="attachment" align="center" label="附件" width="100">
          </el-table-column>
          <el-table-column align="center" label="审批状态" width="150">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.status)" ></span>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
        </el-pagination>
      </div>
    </div>

    <!-- 请假弹出框 -->
    <el-dialog title="请假" :visible.sync="editVisible" width="42%">
      <el-form ref="form" :model="params.leave">
        <div class="start-time">
          <span class="demonstration"><font color="red">*  </font>开始时间</span>
          <el-date-picker
            v-model="params.leave.startTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="08:00:00">
          </el-date-picker>
        </div>
        <div class="end-time">
          <span class="demonstration"><font color="red">*  </font>结束时间</span>
          <el-date-picker
            v-model="params.leave.endTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="20:30:00">
          </el-date-picker>
        </div>
        <div class="leave-type">
          <span class="demonstration"><font color="red">*  </font>休假类型</span>
          <el-select v-model="selectType" @change="currentSel" placeholder="选择类型" class="handle-select">
            <el-option key="1" label="事假" value="1"></el-option>
            <el-option key="2" label="病假" value="2"></el-option>
            <el-option key="3" label="公假" value="3"></el-option>
            <el-option key="4" label="其他" value="4"></el-option>
          </el-select>
        </div>
        <div class="duration">
          <span class="demonstration">时长</span>
          <el-input
            placeholder=""
            v-model="params.leave.duration"
            :disabled="true"
            class="handle-input">
          </el-input>
        </div>
        <div class="cause">
          <span class="demonstration">事由</span>
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.leave.reason" class="handle-textarea">
          </el-input>
        </div>
        <div class="attachment">
          <span class="demonstration">附件</span>
          <el-upload
            class="upload-demo"
            action="">
            <el-button size="small" type="primary">上传</el-button>
          </el-upload>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveLeave">确 定</el-button>
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
            insAccount: ''
          }
        },
        selectType: '',
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
        this.params.leave.account = this.$store.state.account;
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/leave/'
        });
        studentAxios.post('getLeaveInfos', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      showLeave() {
        this.editVisible = true;
      },
      saveLeave() {
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
      currentSel(val) {
        this.params.leave.leaveType = val;
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
        } else {
          return '<font color="green">通过</font>';
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

  .leave-type {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .duration {
    float: right;
    padding-top: 30px;
  }

  .handle-input {
    width: 220px;
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

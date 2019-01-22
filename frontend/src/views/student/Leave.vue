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
          <el-table-column prop="type" align="center" label="休假类型" width="150">
          </el-table-column>
          <el-table-column prop="startTime" align="center" label="开始时间" width="230">
          </el-table-column>
          <el-table-column prop="endTime" align="center" label="结束时间" width="230">
          </el-table-column>
          <el-table-column prop="duration" align="center" label="时长" width="100">
          </el-table-column>
          <el-table-column prop="cause" align="center" label="事由" width="240">
          </el-table-column>
          <el-table-column prop="attachment" align="center" label="附件" width="100">
          </el-table-column>
          <el-table-column prop="state" align="center" label="审批状态" width="150">
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
            placeholder="选择日期时间">
          </el-date-picker>
        </div>
        <div class="end-time">
          <span class="demonstration"><font color="red">*  </font>结束时间</span>
          <el-date-picker
            v-model="params.leave.endTime"
            type="datetime"
            placeholder="选择日期时间">
          </el-date-picker>
        </div>
        <div class="leave-type">
          <span class="demonstration"><font color="red">*  </font>休假类型</span>
          <el-select v-model="selectType" @change="currentSel" placeholder="选择类型" class="handle-select">
            <el-option key="1" label="事假" value="1"></el-option>
            <el-option key="2" label="病假" value="2"></el-option>
            <el-option key="3" label="公假" value="3"></el-option>
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
            v-model="params.leave.cause" class="handle-textarea">
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
          <el-button @click="editVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveLeave">确 定</el-button>
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
        params: {
          leave: {
            type: '',
            startTime: '',
            endTime: '',
            duration: '',
            cause: ''
          }
        },
        tableData: [
          {
            type: '事假',
            startTime: '2019-01-11 09:00:00',
            endTime: '2019-01-18 18:00:00',
            duration: '6天',
            cause: '学校班级出游',
            attachment: '',
            state: '通过'
          }, {
            type: '病假',
            startTime: '2018-12-22 09:00:00',
            endTime: '2018-12-22 18:00:00',
            duration: '1天',
            cause: '感冒，看医生',
            attachment: '',
            state: '待审批'
          }
        ],
        selectType: '',
        editVisible: false,
        delVisible: false,
        idx: -1
      }
    },
    methods: {
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
      },
      showLeave() {
        this.editVisible = true;
      },
      saveLeave() {

      },
      currentSel(val) {
        this.params.leave.type = val;
      }
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

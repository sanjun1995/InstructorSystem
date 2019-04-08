<template>
  <el-container>
    <el-main>
      <div class="instructor-info">
        <el-card shadow="hover" class="instructor-info-card">
        <div slot="header" class="clearfix">
          <span>辅导员<span style="color: red; font-weight: bold">信息栏</span></span>
        </div>
        <div class="text item">
          姓名：{{params.instructor.insName}}<br/><br/>
          手机号码：{{params.instructor.phoneNum}}<br/><br/>
          办公室：{{params.instructor.officeLocation}}
        </div>
        </el-card>
      </div>

      <div class="free-time">
        <el-card class="free-time-card">
          <div slot="header" class="clearfix">
            <span>辅导员<span style="color:red">可预约时间</span></span>
          </div>
          <div class="text item">
            <div v-html="params.appointmentNotice.noticeContent" class="ql-editor">
              {{params.appointmentNotice.content}}
            </div>
            <div class="title-time">
              通知时间: <span>{{formatTime(params.appointmentNotice)}}</span>
            </div>
          </div>
        </el-card>
      </div>

      <div class="notice">
        <el-card class="notice-card">
          <div slot="header" class="clearfix">
            <span style="color:red">公告</span>
            <el-button @click="jumpTo('/student/stuNotice')" style="float: right; padding: 3px 0;color: blueviolet" type="text">更多</el-button>
          </div>
          <div class="text item">
            <div v-html="params.notice.noticeContent" class="ql-editor">
              {{params.notice.content}}
            </div>
            <div class="title-time">
              通知时间: <span>{{formatTime(params.notice)}}</span>
            </div>
          </div>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  import axios from 'axios';
  import {formatDate} from "../../../static/js/date";
    export default {
      data: function(){
        return {
          params: {
            pageNum: '1',
            token: '',
            instructor: {
              account: '',
              insName: '',
              phoneNum: '',
              officeLocation: ''
            },
            notice: {
              id: '',
              insAccount: '',
              noticeContent: '',
              noticeTime: '',
              noticeType: 1
            },
            appointmentNotice: {
              noticeContent: '',
              noticeTime: '',
              noticeType: 2
            }
          }
        }
      },
      methods: {
        getInstructorData() {
          var instructorAxios = axios.create({
            baseURL: 'http://localhost:8081/api/instructor/'
          });
          this.params.instructor.account = this.$store.state.insAccount;
          instructorAxios.post('getPersonalInfo', this.params).then(res => {
            if (res.data.code == 200) {
              this.params.instructor = (res.data.data)[0];
              this.$store.commit("setPhoneNum", this.params.instructor.phoneNum);
            } else {
              this.$message.error(res.data.msg);
            }
          });
        },
        getNoticeData() {
          var noticeAxios = axios.create({
            baseURL: 'http://localhost:8081/api/notice/'
          });
          this.params.notice.insAccount = this.$store.state.insAccount;
          noticeAxios.post('getLastNotice', this.params).then(res => {
            if (res.data.code == 200) {
              this.params.notice = res.data.data[0];
            } else {
              this.$message.error(res.data.msg);
            }
          });
        },
        getAppointmentData() {
          var noticeAxios = axios.create({
            baseURL: 'http://localhost:8081/api/notice/'
          });
          this.params.appointmentNotice.insAccount = this.$store.state.insAccount;
          noticeAxios.post('getLastAppointmentNotice', this.params).then(res => {
            if (res.data.code == 200) {
              this.params.appointmentNotice = res.data.data[0];
            } else {
              this.$message.error(res.data.msg);
            }
          });
        },
        formatTime(row) {
          var date = new Date(row.noticeTime);
          return formatDate(date, "yyyy-MM-dd hh:mm:ss");
        },
        jumpTo(url){
          this.$router.push(url); // 用go刷新
        }
      },
      created() {
        this.getInstructorData();
        this.getNoticeData();
        this.getAppointmentData();
      }
    }
</script>

<style>
  .el-main {
    background-color: #E9EEF3;
    color: #333;
  }
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before {
    line-height: 60px;
  }

  .clearfix:after {
    display: table;

    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .instructor-info {
    float: left;
    margin-left: 100px;
  }
  .free-time {
    float: left;
    margin-left: 100px;
  }

  .notice {
    clear: both;
    padding-top: 20px;
    margin-left: 200px;
  }

  .free-time-card {
       background: lightgreen;
       font-weight: bolder;
       width: 300px;
       height: 250px;
  }

  .notice-card {
    background: burlywood;
    font-weight: bolder;
    width: 450px;
    height: 250px;
  }
  .instructor-info-card {
    background: gainsboro;
    font-weight: bolder;
    width: 350px;
    height: 250px;
  }
  .title-time {
    padding-top: 20px;
    text-align: right;
  }
</style>

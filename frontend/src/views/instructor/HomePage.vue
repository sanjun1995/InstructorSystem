<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="hover" class="mgb20" style="height:275px;">
          <div slot="header" class="clearfix">
            <span style="color: red;">预约空闲时间</span>
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
        <el-card shadow="hover" class="mgb20" style="height:275px;">
          <div slot="header" class="clearfix">
            <span style="color:red">公告</span>
            <el-button @click="jumpTo('/instructor/insNotice')" style="float: right; padding: 3px 0;color: blueviolet" type="text">更多</el-button>
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
      </el-col>
      <el-col :span="16">
        <el-row :gutter="20" class="mgb20">
        </el-row>
        <el-card shadow="hover" style="height: 550px;">
          <div slot="header" class="clearfix">
            <span>班级风采</span>
          </div>
          <div class="block">
            <el-carousel indicator-position="outside" height="400px">
              <el-carousel-item>
                <img class="setitem-btn" src="./img/dalitang.jpg" />
              </el-carousel-item>
              <el-carousel-item>
                <img class="setitem-btn" src="./img/bahe.jpg" />
              </el-carousel-item>
              <el-carousel-item>
                <img class="setitem-btn" src="./img/net.jpg" />
              </el-carousel-item>
              <el-carousel-item>
                <img class="setitem-btn" src="./img/zhaoqing.jpg" />
              </el-carousel-item>
            </el-carousel>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="hover">
          <schart ref="line" class="schart" canvasId="line" :data="data" type="line" :options="options"></schart>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <schart class="schart" canvasId="pie" :data="data2" type="pie" :options="options2"></schart>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Schart from 'vue-schart';
  import axios from 'axios';
  import {formatDate} from "../../../static/js/date";
  export default {
    name: 'dashboard',
    data() {
      return {
        bannerHeight: '',
        name: localStorage.getItem('ms_username'),
        data: [
          {
          name: '2018/09/04',
          value: 13
          },
          {
            name: '2018/09/05',
            value: 9
          },
          {
            name: '2018/09/06',
            value: 11
          },
          {
            name: '2018/09/07',
            value: 6
          },
          {
            name: '2018/09/08',
            value: 7
          },
          {
            name: '2018/09/09',
            value: 14
          },
          {
            name: '2018/09/10',
            value: 9
          }
        ],
        data2: [
          {name:'宿舍矛盾',value:3},
          {name:'职业规划',value:5},
          {name:'班级管理',value:8},
          {name:'思想辅导',value:4},
          {name:'其他',value:14}
        ],
        options: {
          title: '本周请假人数',
          fillColor: '#FC6FA1',
          axisColor: '#008ACD',
          contentColor: '#EEEEEE',
          bgColor: '#F5F8FD',
          bottomPadding: 30,
          topPadding: 30
        },
        options2: {
          title: '预约分析',
          bgColor: '#F5F8FD',
          titleColor: '#000',
          legendColor: '#000',
          radius: 120
        },
        params: {
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
        },
      }
    },
    components: {
      Schart
    },
    computed: {
      role() {
        return this.name === 'admin' ? '超级管理员' : '普通用户';
      }
    },
    methods: {
      changeDate(){
        const now = new Date().getTime();
        this.data.forEach((item, index) => {
          const date = new Date(now - (6 - index) * 86400000);
          item.name = `${date.getFullYear()}/${date.getMonth()+1}/${date.getDate()}`
        })
      },
      handleBus(msg){
        setTimeout(() => {
          this.renderChart()
        }, 300);
      },
      renderChart(){
        this.$refs.bar.renderChart();
        this.$refs.line.renderChart();
      },
      getNoticeData() {
        var noticeAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notice/'
        });
        this.params.notice.insAccount = this.$store.state.account;
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
        this.params.appointmentNotice.insAccount = this.$store.state.account;
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
      this.getNoticeData();
      this.getAppointmentData();
    }
  }

</script>


<style scoped>
  .schart{
    width: 500px;
    height: 350px;
  }
  .setitem-btn {
    height: 100%;
    width: 100%;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  .text {
    font-size: 14px;
  }
  .item {
    margin-bottom: 18px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
  }
  .grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
  }
  .grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
  }
  .grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
  }
  .grid-con-3 .grid-con-icon {
    background: rgb(242, 94, 67);
  }
  .grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
  }
  .user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
  }
  .user-info-list span {
    margin-left: 70px;
  }
  .mgb20 {
    margin-bottom: 20px;
  }
  .schart {
    width: 100%;
    height: 300px;
  }
  .title-time {
    padding-top: 20px;
    text-align: right;
  }
</style>


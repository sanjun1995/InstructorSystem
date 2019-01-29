<template>
  <el-container style="height: 750px; border: 1px solid #eee">
    <el-header style="height: 60px;">
      <div class="topbar-logo">
        <a href="/"><img src="../../assets/gduf.png" style="height: 60px; width: 180px;"></a>
      </div>
      <div class="topbar-title">
        <span style="font-size: 18px;color: #fff;">辅导员管理系统（辅导员端）</span>
      </div>
      <div class="topbar-account topbar-btn">
        <!-- 消息中心 -->
        <div class="btn-bell">
          <el-tooltip effect="dark" :content="message?`有${message}条未处理 消息`:`消息中心`" placement="bottom">
            <router-link to="/instructor/insNotifications">
              <i class="el-icon-bell"></i>
            </router-link>
          </el-tooltip>
          <span class="btn-bell-badge" v-if="message"></span>
        </div>
        <div class="user-avator"><img :src="params.instructor.headPath"></div>
        <el-dropdown trigger="click" style="font-size: 17px;color: #fff;">
          <span class="el-dropdown-link">
            {{params.instructor.insName}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="jumpTo('/instructor/insPersonalCenter')"><span style="color: #555;font-size: 14px;">个人中心</span></div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="jumpTo('/instructor/insChangePassword')"><span style="color: #555;font-size: 14px;">修改密码</span></div>
            </el-dropdown-item>
            <el-dropdown-item divided @click.native = "logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="230px" style="background-color: #333744">
        <el-row class="tac">
          <el-col :span="24">
            <el-menu
              default-active="/instructor/insHomePage"
              class="el-menu-vertical-demo"
              @select="handleSelect"
              background-color="#333744"
              align="center"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-menu-item index="/instructor/insHomePage">
                <span class="fontFamily ins-shouye"></span>
                <span slot="title" class="menu-title">首页</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insPersonalCenter">
                <span class="fontFamily ins-gerenzhongxin"></span>
                <span slot="title" class="menu-title">个人中心</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insClassInfo">
                <span class="fontFamily ins-gerenzhongxin"></span>
                <span slot="title" class="menu-title">班级信息</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insLeave">
                <span class="fontFamily ins-qingjia"></span>
                <span slot="title" class="menu-title">请假管理</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insAppointment">
                <span class="fontFamily ins-yidongduanicon-"></span>
                <span slot="title" class="menu-title">预约管理</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insNotifications">
                <span class="el-icon-bell"></span>
                <span slot="title" class="menu-title">消息管理</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insNotice">
                <span class="fontFamily ins-gonggao"></span>
                <span slot="title" class="menu-title">公告</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insMemorandum">
                <span class="fontFamily ins-beiwanglushili"></span>
                <span slot="title" class="menu-title">备忘录</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insBulletinCriticism">
                <span class="fontFamily ins-jinggao"></span>
                <span slot="title" class="menu-title">通报管理</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insScore">
                <span class="fontFamily ins-chengjiguanli"></span>
                <span slot="title" class="menu-title">成绩管理</span>
              </el-menu-item>
              <el-menu-item index="/instructor/insChangePassword">
                <span class="fontFamily ins-xiugaimima"></span>
                <span slot="title" class="menu-title">修改密码</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <div class="content-box">
          <v-tags></v-tags>
          <div class="content">
            <transition name="move" mode="out-in">
              <keep-alive :include="tagsList">
                <router-view></router-view>
              </keep-alive>
            </transition>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import vTags from './Tags.vue';
  import bus from './bus.js';
  import axios from 'axios';
  export default {
    data() {
      return {
        defaultActiveIndex: '',
        tagsList: [],
        message: 0,
        params: {
          token: '',
          instructor: {
            account: '',
            insName: '',
            headPath: ''
          },
        },
       notificationParams: {
          notification: {
            isAccount: '',
            isRead: ''
          }
        },
        path: [
          '/instructor/insHomePage'
        ],
        defaultSrc: '../static/images/img.jpg'
      }
    },
    components: {
      vTags
    },
    methods: {
      getData() {
        this.params.token = sessionStorage.getItem("access-token");
        var instructorAxios = axios.create({
          baseURL: 'http://localhost:8081/api/instructor/'
        });
        instructorAxios.post('getPersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.instructor = (res.data.data)[0];
            if (this.params.instructor.headPath != null) {
              this.defaultSrc = this.params.instructor.headPath;
            } else {
              this.params.instructor.headPath = this.defaultSrc;
            }
          } else {
            this.$message.error(res.data.msg);
          }
        });
        this.getNotificationData();
      },
      getNotificationData() {
        var notificationAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notification/'
        });
        this.notificationParams.notification.isAccount = this.$store.state.account;
        this.notificationParams.notification.isRead = 0;
        notificationAxios.post('getNotificationsByPage', this.notificationParams).then(res => {
          if (res.data.code == 200) {
            this.message = res.data.data.length;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      jumpTo(url){
        this.defaultActiveIndex = url;
        this.$router.push(url); // 用go刷新
      },
      handleSelect(route) {
          this.$router.push({path: route});
      },
      logout(){
        this.$confirm('确认退出吗?', '提示', {
          confirmButtonClass: 'el-button--warning'
        }).then(() => {
          window.sessionStorage.removeItem('access-token');
          this.over();
          this.$router.push("/login");
        }).catch(() => {});
      },
      initWebSocket() {
        let ws = new WebSocket('ws://localhost:8081/websocket/instructor')
        ws.onopen = () => {
        }
        ws.onmessage = evt => {
          this.getNotificationData();
        }
        ws.onclose = () => {
          console.log('连接已关闭');
        }
        // 组件销毁时调用，中断websocket连接
        this.over = () => {
          ws.close();
        }
      }
    },
    created() {
      // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
      bus.$on('tags', msg => {
        let arr = [];
        for(let i = 0, len = msg.length; i < len; i ++){
          msg[i].name && arr.push(msg[i].name);
        }
        this.tagsList = arr;
      });
      this.params.instructor.headPath = this.defaultSrc;
      this.getData();
      this.initWebSocket();
    }
  };
</script>
<style>

  .menu-title {
    padding-left: 10px;
  }

  .btn-bell {
    position: relative;
    float: left;
    padding-right: 10px;
    width: 30px;
    height: 30px;
    text-align: center;
    border-radius: 15px;
    cursor: pointer;
  }

  .btn-bell-badge {
    position: absolute;
    top: 13px;
    right: 11px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
  }

  .btn-bell .el-icon-bell{
    font-size: 22px;
    color: #fff;
  }

  .user-avator img{
    display: block;
    float: left;
    width: 50px;
    height: 50px;
    border-radius: 50%;
  }

  .el-header {
    background-color: #545c64;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }

  .el-dropdown {
    padding-left: 10px;
  }

  .topbar-logo {
    float: left;
    line-height: 26px;
  }

  .topbar-title {
    float: left;
    text-align: left;
    width: 300px;
    margin-left: 30px;
    padding-left: 20px;
    border-left: 1px solid #fff;
  }

  .topbar-account {
    float: right;
    width: 180px;
    padding-right: 12px;
  }
  .topbar-btn {
    color: #fff;
  }
</style>

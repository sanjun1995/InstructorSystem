<template>
  <el-container style="height: 750px; border: 1px solid #eee">
    <el-header style="height: 60px;">
      <div class="topbar-logo">
        <a href="/"><img src="../../assets/gduf.png" style="height: 60px; width: 180px;"></a>
      </div>
      <div class="topbar-title">
        <span style="font-size: 18px;color: #fff;">辅导员管理系统（学生端）</span>
      </div>
      <div class="topbar-account topbar-btn">
        <div class="user-avator"><img :src="params.student.headPath"></div>
        <el-dropdown trigger="click" style="font-size: 15px;color: #fff;">
          <span class="el-dropdown-link">
            {{params.student.stuName}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="jumpTo('/student/stuPersonalCenter')"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="jumpTo('/student/stuChangePassword')"><span style="color: #555;font-size: 14px;">修改密码</span></div>
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
              default-active="1"
              class="el-menu-vertical-demo"
              @select="handleSelect"
              background-color="#333744"
              align="center"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-menu-item index="/student/stuHomePage">
                <span class="fontFamily ins-shouye"></span>
                <span slot="title" class="menu-title">首页</span>
              </el-menu-item>
              <el-menu-item index="/student/stuPersonalCenter">
                <span class="fontFamily ins-gerenzhongxin"></span>
                <span slot="title" class="menu-title">个人中心</span>
              </el-menu-item>
              <el-menu-item index="/student/stuLeave">
                <span class="fontFamily ins-qingjia"></span>
                <span slot="title" class="menu-title">请假</span>
              </el-menu-item>
              <el-menu-item index="/student/stuAppointment">
                <span class="fontFamily ins-yidongduanicon-"></span>
                <span slot="title" class="menu-title">预约</span>
              </el-menu-item>
              <el-menu-item index="/student/stuChangePassword">
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
        params: {
          token: '',
          student: {
            account: '',
            stuName: '',
            headPath: ''
          }
        },
        path: [
          '/student/insHomePage'
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
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8080/api/student/'
        });
        studentAxios.post('getPersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.student = (res.data.data)[0];
            if (this.params.student.headPath != null) {
              this.defaultSrc = this.params.student.headPath;
            } else {
              this.params.student.headPath = this.defaultSrc;
            }
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
          this.$router.push("/login");
        }).catch(() => {});
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
      this.params.student.headPath = this.defaultSrc;
      this.getData();
    }
  };
</script>
<style>

  .menu-title {
    padding-left: 10px;
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
    width: 130px;
    padding-right: 10px;
  }

  .user-avator img{
    display: block;
    float: left;
    width: 50px;
    height: 50px;
    border-radius: 50%;
  }

  .topbar-btn {
    color: #fff;
  }
</style>

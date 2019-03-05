<template>
  <el-container style="height: 750px; border: 1px solid #eee">
    <el-header style="height: 60px;">
      <div class="topbar-logo">
        <a href="/"><img src="../../assets/gduf.png" style="height: 60px; width: 180px;"></a>
      </div>
      <div class="topbar-title">
        <span style="font-size: 18px;color: #fff;">辅导员管理系统（管理员端）</span>
      </div>
      <div class="topbar-account topbar-btn">
        <div class="user-avator"><img :src="defaultSrc"></div>
        <el-dropdown trigger="click" style="font-size: 15px;color: #fff;">
          <span class="el-dropdown-link">
            {{params.sysManager.sysName}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="jumpTo('/sysManager/sysManagerChangePassword')"><span style="color: #555;font-size: 14px;">修改密码</span></div>
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
              default-active="2"
              class="el-menu-vertical-demo"
              @select="handleSelect"
              background-color="#333744"
              align="center"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-menu-item index="/sysManager/sysManagerStudentManagement">
                <span class="fontFamily ins-gerenzhongxin"></span>
                <span slot="title" class="menu-title">学生管理</span>
              </el-menu-item>
              <el-menu-item index="/sysManager/sysManagerInstructorManagement">
                <span class="fontFamily ins-gerenzhongxin"></span>
                <span slot="title" class="menu-title">辅导员管理</span>
              </el-menu-item>
              <el-menu-item index="/sysManager/sysManagerInformationManagement">
                <span class="el-icon-bell"></span>
                <span slot="title" class="menu-title">信息管理</span>
              </el-menu-item>
              <el-menu-item index="/sysManager/sysManagerChangePassword">
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
        tagsList: [],
        params: {
          token: '',
          sysManager: {
            account: '',
            sysName: ''
          }
        },
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
          baseURL: 'http://localhost:8082/api/sysManager/'
        });
        studentAxios.post('getPersonalInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.params.sysManager = (res.data.data)[0];
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      jumpTo(url){
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

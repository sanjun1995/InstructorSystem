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
          <el-tooltip effect="dark" :content="message?`有${message}条未读消息`:`消息中心`" placement="bottom">
            <router-link to="/tabs">
              <i class="el-icon-bell"></i>
            </router-link>
          </el-tooltip>
          <span class="btn-bell-badge" v-if="message"></span>
        </div>
        <div class="user-avator"><img src="./img/img.jpg"></div>
        <el-dropdown trigger="click" style="font-size: 17px;color: #fff;">
          <span class="el-dropdown-link">
            {{params.stuName}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="jumpTo('/student/profile')"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="jumpTo('/student/changepwd')"><span style="color: #555;font-size: 14px;">修改密码</span></div>
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
                <span slot="title" class="menu-title">个人信息</span>
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
              <el-menu-item index="/instructor/insChangePassword">
                <span class="fontFamily ins-gonggao"></span>
                <span slot="title" class="menu-title">公告</span>
              </el-menu-item>
              <el-menu-item index="">
                <span class="fontFamily ins-beiwanglushili"></span>
                <span slot="title" class="menu-title">备忘录</span>
              </el-menu-item>
              <el-menu-item index="">
                <span class="fontFamily ins-jinggao"></span>
                <span slot="title" class="menu-title">通报管理</span>
              </el-menu-item>
              <el-menu-item index="">
                <span class="fontFamily ins-chengjiguanli"></span>
                <span slot="title" class="menu-title">成绩管理</span>
              </el-menu-item>
              <el-menu-item index="">
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
  export default {
    data() {
      return {
        defaultActiveIndex: "0",
        tagsList: [],
        message: 2,
        params: {
          stuName: '卜翠娟'
        },
        path: [
          '/instructor/insHomePage'
        ],
      }
    },
    components: {
      vTags
    },
    methods: {
      jumpTo(url){
        this.defaultActiveIndex = url;
        this.$router.push(url); // 用go刷新
      },
      handleSelect(route) {
        if (route !== "") {
          this.$router.push({path: route});
        } else {
          alert("该模块还在开发进行中")
        }
      },
      logout(){
        let that = this;
        this.$confirm('确认退出吗?', '提示', {
          confirmButtonClass: 'el-button--warning'
        }).then(() => {
          //确认
          that.loading = true;
          API.logout().then(function (result) {
            that.loading = false;
            localStorage.removeItem('access-user');
            that.$router.go('/login'); //用go刷新
          }, function (err) {
            that.loading = false;
            that.$message.error({showClose: true, message: err.toString(), duration: 2000});
          }).catch(function (error) {
            that.loading = false;
            console.log(error);
            that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
          });
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
      })
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
    width:40px;
    height:40px;
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
    width: 160px;
    padding-right: 12px;
  }
  .topbar-btn {
    color: #fff;
  }
</style>

<template>
  <el-container>
    <el-main>
      <div class="instructor-info">
        <el-card shadow="hover" class="instructor-info-card">
        <div slot="header" class="clearfix">
          <span>辅导员<span style="color: red; font-weight: bold">信息栏</span></span>
        </div>
        <div class="text item">
          姓名：卜翠娟<br/><br/>
          手机号码：13556115197<br/><br/>
          办公室：系办1206
        </div>
        </el-card>
      </div>

      <div class="free-time">
        <el-card class="free-time-card">
          <div slot="header" class="clearfix">
            <span>辅导员<span style="color:red">可预约时间</span></span>
          </div>
          <div class="text item">
            上午时间：（8:30 - 11:30）<br/><br/>
            下午时间：（14:30 - 17:00）
          </div>
        </el-card>
      </div>

      <div class="notice">
        <el-card class="notice-card">
          <div slot="header" class="clearfix">
            <span style="color:red">公告</span>
            <el-button style="float: right; padding: 3px 0;color: blueviolet" type="text">更多</el-button>
          </div>
          <div class="text item">
            <p>12月26号下午14:30分，请1515431班所有同学过来领奖。</p>
            <br/><br />
            <p>不允许请假！班长做好记录！</p>
          </div>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<script>
    export default {
      data() {
        return {
          name: "HomePage"
        }
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
</style>

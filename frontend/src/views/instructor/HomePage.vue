<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="hover" class="mgb20" style="height:252px;">
          <div slot="header" class="clearfix">
            <span style="color: red; font-weight: bold">公告</span>
            <el-button style="float: right; padding: 3px 0" type="text">更多</el-button>
          </div>
          <div class="text item">
            <p>请各位同学注意宿舍卫生，不能再扔垃圾到走廊上，谢谢大家的配合。</p>
            <p style="padding-top: 100px;padding-left: 150px">发布时间：2018-12-30 14:24</p>
          </div>
        </el-card>
        <el-card shadow="hover" style="height: 350px;">
          <div slot="header" class="clearfix">
            <span style="color: red; font-weight: bold" >备忘录</span>
            <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
          </div>
          <el-table :data="todoList" :show-header="false" height="304" style="width: 100%;font-size:14px;">
            <el-table-column width="40">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.status"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
              </template>
            </el-table-column>
            <el-table-column width="60">
              <template slot-scope="scope">
                <i class="el-icon-edit"></i>
                <i class="el-icon-delete"></i>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-row :gutter="20" class="mgb20">
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-1">
                <span class="fontFamily ins-geren grid-con-icon"></span>
                <div class="grid-cont-right">
                  <div class="grid-num">34</div>
                  <div>请假人数</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-2">
                <span class="fontFamily ins-geren grid-con-icon"></span>
                <div class="grid-cont-right">
                  <div class="grid-num">21</div>
                  <div>预约人数</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{padding: '0px'}">
              <div class="grid-content grid-con-3">
                <span class="fontFamily ins-geren grid-con-icon"></span>
                <div class="grid-cont-right">
                  <div class="grid-num">13</div>
                  <div>通报人数</div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-card shadow="hover" style="height: 500px;">
          <div slot="header" class="clearfix">
            <span>班级风采</span>
          </div>
          <div class="block">
            <el-carousel indicator-position="outside" height="380px">
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
  import bus from './bus.js';
  export default {
    name: 'dashboard',
    data() {
      return {
        bannerHeight: '',
        name: localStorage.getItem('ms_username'),
        todoList: [
          {
            title: '12月30日上午10点参加计科1班的班会',
            status: false,
          },
          {
            title: '12月29日下午2点党支部会议，地点：院办',
            status: false,
          },
          {
            title: '通表转正大会在周三和周四下午',
            status: true,
          },
          {
            title: '提醒自己下周日1月3号要加党员资料',
            status: false,
          },
        ],
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
        }
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
      }
    }
  }

</script>


<style scoped>
  .schart-box{
    display: inline-block;
  }
  .schart{
    width: 500px;
    height: 350px;
  }
  .content-title{
    clear: both;
    line-height: 10px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
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

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  .el-row {
    margin-bottom: 20px;
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

  .box-card {
    width: 480px;
  }

  .grid-content {
    display: flex;
    align-items: center;
    height: 100px;
  }

  .grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
  }

  .grid-num {
    font-size: 30px;
    font-weight: bold;
  }

  .grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
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

  .user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
  }

  .user-avator {
    width: 120px;
    height: 120px;
    border-radius: 50%;
  }

  .user-info-cont {
    padding-left: 50px;
    flex: 1;
    font-size: 14px;
    color: #999;
  }

  .user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
  }

  .user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
  }

  .user-info-list span {
    margin-left: 70px;
  }

  .mgb20 {
    margin-bottom: 20px;
  }

  .todo-item {
    font-size: 14px;
  }

  .todo-item-del {
    text-decoration: line-through;
    color: #999;
  }

  .schart {
    width: 100%;
    height: 300px;
  }

</style>


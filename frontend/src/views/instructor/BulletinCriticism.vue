<template>
  <div class="table">
    <div class="container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="通报信息" name="first">
          <div class="handle-box">
            <div class="punishment-btn">
              <el-button type="primary" @click="showPunishment">通报</el-button>
            </div>
            <el-select v-model="selectClass" @change="currentSel" placeholder="选择班级" class="handle-select">
              <el-option key="1" label="全部班级" value=""></el-option>
              <el-option key="2" label="1515431" value="1515431"></el-option>
              <el-option key="3" label="1515432" value="1515432"></el-option>
            </el-select>
            <div class="select-stu">
              学号：<el-input v-model="params.punishment.account" class="handle-input"></el-input>
              姓名：<el-input v-model="params.punishment.stuName" class="handle-input"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="tableData">
              <el-table-column align="center" label="学号" width="150">
                <template slot-scope="scope">
                  <span v-html="brightenKeyword(scope.row.account, params.punishment.account)" ></span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="姓名" width="120">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForSearch(scope.row.stuName, params.punishment.stuName)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="punishmentTime" align="center" :formatter="formatTime" label="通报时间" width="200">
              </el-table-column>
              <el-table-column prop="punishmentReason" align="center" label="通报原因" width="300">
              </el-table-column>
              <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                  <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination">
            <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="通报次数" name="second">
          <div class="handle-table">
            <el-table :data="rankingData">
              <el-table-column prop="account" align="center" label="学号" width="150">
              </el-table-column>
              <el-table-column align="center" label="姓名" width="150">
                <template slot-scope="scope">
                  <span v-html="brightenKeywordForSearch(scope.row.stuName, params.punishment.stuName)" ></span>
                </template>
              </el-table-column>
              <el-table-column prop="punishmentTimes" align="center" label="通报次数" width="150">
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination">
            <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- 通报弹出框 -->
    <el-dialog title="通报" :visible.sync="editVisible" width="25%">

      <el-form ref="form" :model="params.punishment">
        <div class="account">
          <span class="demonstration">学号</span>
          <el-input
            placeholder=""
            v-model="params.punishment.account"
            class="handle-input">
          </el-input>
        </div>
        <div class="stuName">
          <span class="demonstration">姓名</span>
          <el-input
            placeholder=""
            v-model="params.punishment.stuName"
            class="handle-input">
          </el-input>
        </div>
        <div class="punishment-reason">
          <span class="demonstration"><font color="red">*  </font>通报原因</span>
          <el-input
            type="textarea"
            :rows="4"
            placeholder=""
            v-model="params.punishment.punishmentReason" class="handle-textarea">
          </el-input>
        </div>
        <div class="punishment-time">
          <span class="demonstration"><font color="red">*  </font>通报时间</span>
          <el-date-picker
            v-model="params.punishment.punishmentTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="08:00:00">
          </el-date-picker>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="savePunishment">确 定</el-button>
        <el-button @click="editVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
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
        activeName: 'first',
        tableData: [],
        rankingData: [],
        selectClass: '',
        editVisible: false,
        delVisible: false,
        params: {
          pageNum: '1',
          punishment: {
            id: '',
            stuName: '',
            insAccount: '',
            account: '',
            punishmentTime: '',
            punishmentReason: ''
          }
        }
      }
    },
    created() {
      this.getData();
    },
    methods: {
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
        this.getData();
      },
      getData() {
        this.params.punishment.insAccount = this.$store.state.account;
        var punishmentAxios = axios.create({
          baseURL: 'http://localhost:8081/api/punishment/'
        });
        punishmentAxios.post('getPunishmentInfosByInsAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      getRankingData() {
        this.params.punishment.insAccount = this.$store.state.account;
        var punishmentAxios = axios.create({
          baseURL: 'http://localhost:8081/api/punishment/'
        });
        punishmentAxios.post('getPunishmentRankingInfos', this.params).then(res => {
          if (res.data.code == 200) {
            this.rankingData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      formatTime(row, column) {
        var date = new Date(row.punishmentTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
      },
      handleDelete(index, row) {
        this.params.punishment.id = row.id;
        this.delVisible = true;
      },
      deleteRow() {
        var punishmentAxios = axios.create({
          baseURL: 'http://localhost:8081/api/punishment/'
        });
        punishmentAxios.post('deletePunishmentInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("删除通报信息成功!");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
        this.delVisible = false;
      },
    brightenKeyword(val, keyword) {
        val = val + '';
        if (val.indexOf(keyword) !== -1 && keyword !== '') {
          return val.replace(keyword, '<font color="red">' + keyword + '</font>')
        } else {
          return val
        }
      },
      formatSex(row, column) {
        return row.sex == '1' ? "女" : "男";
      },
      showPunishment() {
        this.editVisible = true;
      },
      savePunishment() {
        this.editVisible = false;
        this.params.punishment.insAccount = this.$store.state.account;
        var punishmentAxios = axios.create({
          baseURL: 'http://localhost:8081/api/punishment/'
        });
        punishmentAxios.post('insertPunishmentInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("登记通报信息成功!");
            this.params.punishment.account = '';
            this.params.punishment.stuName = '';
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      currentSel(val) {
        this.params.punishment.account = val;
        this.getData();
      },
      search() {
        this.getData();
      },
      handleClick(tab, event) {
        var active = this.activeName;
        if (active == 'first') {
          this.getData();
        } else {
          this.getRankingData();
        }
      },
      brightenKeywordForSearch(val, keyword) {
        val = val + '';
        if (val.indexOf(keyword) !== -1 && keyword !== '') {
          return val.replace(keyword, '<font color="red">' + keyword + '</font>')
        } else {
          return val
        }
      }
    }
  }

</script>

<style scoped>
  .punishment-btn {
    padding-bottom: 20px;
  }

  .select-stu {
    float: left;
    padding-left: 200px;
    padding-bottom: 20px;
  }

  .handle-box {
    margin-bottom: 10px;
  }

  .handle-select {
    float: left;
    width: 120px;
  }

  .handle-input {
    width: 140px;
    display: inline-block;
  }

  .table{
    width: 100%;
    font-size: 14px;
  }
  .red{
    color: #ff0000;
  }

  .account {
    float: left;
  }

  .stuName {
    float: right;
    padding-left: 20px;
  }

  .punishment-reason {
    clear: both;
    padding-top: 20px;
  }

  .punishment-time {
    padding-top: 20px;
  }

</style>


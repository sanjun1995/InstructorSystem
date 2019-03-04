<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-select v-model="selectClass" @change="currentSel" placeholder="选择班级" class="handle-select">
          <el-option key="1" label="全部班级" value=""></el-option>
          <el-option key="2" label="1515431" value="1515431"></el-option>
          <el-option key="3" label="1515432" value="1515432"></el-option>
        </el-select>
        <div class="select-stu">
          学号：<el-input v-model="params.achievement.account" class="handle-input"></el-input>
          姓名：<el-input v-model="params.achievement.stuName" class="handle-input"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </div>
      </div>
      <div class="handle-table">
        <el-table :data="tableData" border>
          <el-table-column align="center" label="学号" width="120">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.account, params.achievement.account)" ></span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="姓名" width="85">
            <template slot-scope="scope">
              <span v-html="brightenKeyword(scope.row.stuName, params.achievement.stuName)" ></span>
            </template>
          </el-table-column>
          <el-table-column prop="score1" align="center" label="第一学年平均分" width="130">
          </el-table-column>
          <el-table-column prop="score1Rank" align="center" label="排名" width="50">
          </el-table-column>
          <el-table-column prop="comprehensive1" align="center" label="第一学年综测分" width="150">
          </el-table-column>
          <el-table-column prop="comprehensive1Rank" align="center" label="排名" width="50">
          </el-table-column>
          <el-table-column prop="score2" align="center" label="第二学年平均分" width="150">
          </el-table-column>
          <el-table-column prop="score2Rank" align="center" label="排名" width="50">
          </el-table-column>
          <el-table-column prop="comprehensive2" align="center" label="第二学年综测分" width="150">
          </el-table-column>
          <el-table-column prop="comprehensive2Rank" align="center" label="排名" width="50">
          </el-table-column>
          <el-table-column prop="score3" align="center" label="第三学年平均分" width="150">
          </el-table-column>
          <el-table-column prop="score3Rank" align="center" label="排名" width="50">
          </el-table-column>
          <el-table-column prop="comprehensive3" align="center" label="第三学年综测分" width="150">
          </el-table-column>
          <el-table-column prop="comprehensive3Rank" align="center" label="排名" width="50">
          </el-table-column>
          <el-table-column label="操作" width="250" align="center">
            <template slot-scope="scope">
              <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
        </el-pagination>
      </div>
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="成绩" :visible.sync="editVisible" width="30%">
      <el-form ref="form" :model="params.achievement" label-width="50px">
        <div class="account">
          <span class="demonstration">学号</span>
          <el-input v-model="params.achievement.account" :disabled="true" class="handle-input"></el-input>
        </div>
        <div class="stuName">
          <span class="demonstration">姓名</span>
          <el-input v-model="params.achievement.stuName" :disabled="true" class="handle-input"></el-input>
        </div>
        <div class="score1">
          <span class="demonstration">第一学年平均分</span>
          <el-input v-model="params.achievement.score1" class="handle-input"></el-input>
        </div>
        <div class="score1Rank">
          <span class="demonstration">排名</span>
          <el-input v-model="params.achievement.score1Rank" class="handle-input"></el-input>
        </div>
        <div class="comprehensive1">
          <span class="demonstration">第一学年综测分</span>
          <el-input v-model="params.achievement.comprehensive1" class="handle-input"></el-input>
        </div>
        <div class="comprehensive1Rank">
          <span class="demonstration">排名</span>
          <el-input v-model="params.achievement.comprehensive1Rank" class="handle-input"></el-input>
        </div>
        <div class="score2">
          <span class="demonstration">第二学年平均分</span>
          <el-input v-model="params.achievement.score2" class="handle-input"></el-input>
        </div>
        <div class="score2Rank">
          <span class="demonstration">排名</span>
          <el-input v-model="params.achievement.score2Rank" class="handle-input"></el-input>
        </div>
        <div class="comprehensive2">
          <span class="demonstration">第二学年综测分</span>
          <el-input v-model="params.achievement.comprehensive2" class="handle-input"></el-input>
        </div>
        <div class="comprehensive2Rank">
          <span class="demonstration">排名</span>
          <el-input v-model="params.achievement.comprehensive2Rank" class="handle-input"></el-input>
        </div>
        <div class="score3">
          <span class="demonstration">第三学年平均分</span>
          <el-input v-model="params.achievement.score3" class="handle-input"></el-input>
        </div>
        <div class="score3Rank">
          <span class="demonstration">排名</span>
          <el-input v-model="params.achievement.score3Rank" class="handle-input"></el-input>
        </div>
        <div class="comprehensive3">
          <span class="demonstration">第三学年综测分</span>
          <el-input v-model="params.achievement.comprehensive3" class="handle-input"></el-input>
        </div>
        <div class="comprehensive3Rank">
          <span class="demonstration">排名</span>
          <el-input v-model="params.achievement.comprehensive3Rank" class="handle-input"></el-input>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        tableData: [],
        selectClass: '',
        editVisible: false,
        params: {
          pageNum: '1',
          achievement: {
            account: '',
            stuName: '',
            score1: '',
            score2: '',
            score3: '',
            score1Rank: '',
            score2Rank: '',
            score3Rank: '',
            comprehensive1: '',
            comprehensive2: '',
            comprehensive3: '',
            comprehensive1Rank: '',
            comprehensive2Rank: '',
            comprehensive3Rank: ''
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
        this.params.achievement.insAccount = this.$store.state.account;
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8081/api/achievement/'
        });
        studentAxios.post('getAchievementsByPage', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      saveData() {
        var studentAxios = axios.create({
          baseURL: 'http://localhost:8081/api/achievement/'
        });
        studentAxios.post('updateAchievementInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("修改成绩成功!");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
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
      currentSel(val) {
        this.params.achievement.account = val;
        this.getData();
      },
      search() {
        this.getData();
      },
      handleEdit(index, row) {
        const item = this.tableData[index];
        this.params.achievement = {
          account: item.account,
          stuName: item.stuName,
          score1: item.score1,
          score2: item.score2,
          score3: item.score3,
          score1Rank: item.score1Rank,
          score2Rank: item.score2Rank,
          score3Rank: item.score3Rank,
          comprehensive1: item.comprehensive1,
          comprehensive2: item.comprehensive2,
          comprehensive3: item.comprehensive3,
          comprehensive1Rank: item.comprehensive1Rank,
          comprehensive2Rank: item.comprehensive2Rank,
          comprehensive3Rank: item.comprehensive3Rank
        }
        this.editVisible = true;
      },
      // 保存编辑
      saveEdit() {
        this.saveData();
        this.editVisible = false;
      }
    }
  }

</script>

<style scoped>
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
    width: 150px;
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
    padding-left: 10px;
  }

  .score1 {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .score1Rank {
    float: right;
    padding-top: 30px;
  }

  .comprehensive1 {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .comprehensive1Rank {
    float: right;
    padding-top: 30px;
  }

  .score2 {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .score2Rank {
    float: right;
    padding-top: 30px;
  }

  .comprehensive2 {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .comprehensive2Rank {
    float: right;
    padding-top: 30px;
  }

  .score3 {
    clear: both;
    float: left;
    padding-top: 30px;
  }

  .score3Rank {
    float: right;
    padding-top: 30px;
  }

  .comprehensive3 {
    clear: both;
    float: left;
    padding-top: 30px;
    padding-bottom: 30px;
  }

  .comprehensive3Rank {
    float: right;
    padding-top: 30px;
    padding-bottom: 30px;
  }
</style>

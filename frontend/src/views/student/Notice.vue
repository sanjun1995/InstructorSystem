<template>
  <div class="table">
    <div class="container">
        <div class="handle-table">
          <el-table :data="tableData">
            <el-table-column align="center" label="公告" width="1200px">
              <template slot-scope="scope">
                <div v-html="scope.row.noticeContent" class="ql-editor">
                  {{scope.row.content}}
                </div>
                <div class="title-time">
                  通知时间: <span>{{formatTime(scope.row)}}</span>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="pagination">
          <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="500">
          </el-pagination>
        </div>
    </div>
  </div>
</template>

<script>
  import 'quill/dist/quill.core.css';
  import 'quill/dist/quill.snow.css';
  import 'quill/dist/quill.bubble.css';
  import { quillEditor } from 'vue-quill-editor';
  import axios from 'axios';
  import {formatDate} from "../../../static/js/date";
  export default {
    name: 'editor',
    data: function(){
      return {
        activeName: 'first',
        tableData: [],
        params: {
          pageNum: '1',
          notice: {
            id: '',
            insAccount: '',
            noticeContent: '',
            noticeTime: '',
            noticeType: 0
          }
        }
      }
    },
    components: {
      quillEditor
    },
    created() {
      this.params.notice.noticeType = 1;
      this.getData();
    },
    methods: {
      // 分页导航
      handleCurrentChange(val) {
        this.params.pageNum = val;
        this.getData();
      },
      getData() {
        var noticeAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notice/'
        });
        this.params.notice.noticeType = 1;
        this.params.notice.insAccount = this.$store.state.insAccount;
        noticeAxios.post('getNoticeInfosByInsAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      formatTime(row) {
        var date = new Date(row.noticeTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
      }
    }
  }
</script>
<style scoped>
  .red{
    color: #ff0000;
  }
  .ql-editor {
    text-align: -moz-center;
  }
  .title-time {
    text-align: right;
  }
</style>

<template>
  <div class="table">
    <div class="container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="公告信息" name="first">
          <div class="handle-box">
            <div class="punishment-btn">
              <el-button type="primary" @click="addNotice">发布公告</el-button>
            </div>
          </div>
          <div class="handle-table">
            <el-table :data="tableData">
              <el-table-column prop="id" align="center" label="id" width="100px">
              </el-table-column>
              <el-table-column prop="noticeContent" align="center" label="公告内容" width="500px">
              </el-table-column>
              <el-table-column prop="noticeTime" align="center" :formatter="formatTime" label="公告时间" width="200px">
              </el-table-column>
              <el-table-column label="操作" width="300px" align="center">
                <template slot-scope="scope">
                  <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
      </el-tabs>
    </div>

      <!-- 公告弹出框 -->
      <el-dialog title="发布公告" :visible.sync="editVisible" width="60%">
        <el-form ref="form" :model="params.notice">
            <quill-editor ref="myTextEditor" v-model="params.notice.noticeContent" :options="editorOption"></quill-editor>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submit">提交</el-button>
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
        editVisible: false,
        delVisible: false,
        isUpdate: false,
        editorOption: {
          placeholder: '请输入公告内容...'
        },
        params: {
          pageNum: '1',
          notice: {
            id: '',
            noticeContent: '',
            noticeTime: '',
            noticeType: 1
          }
        }
      }
    },
    components: {
      quillEditor
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
        this.params.notice.insAccount = this.$store.state.account;
        var noticeAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notice/'
        });
        noticeAxios.post('getNoticeInfosByInsAccount', this.params).then(res => {
          if (res.data.code == 200) {
            this.tableData = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      handleClick(tab, event) {
        var active = this.activeName;
        if (active == 'first') {
          this.getData();
        }
      },
      addNotice() {
        this.editVisible = true;
      },
      submit(){
        this.editVisible = false;
        this.params.notice.insAccount = this.$store.state.account;
        var noticeAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notice/'
        });
        if (this.isUpdate) {
          noticeAxios.post('updateNoticeInfo', this.params).then(res => {
            if (res.data.code == 200) {
              this.$message.success("修改公告成功!");
              this.isUpdate = false;
              this.getData();
            } else {
              this.$message.error(res.data.msg);
            }
          });
          return;
        }
        noticeAxios.post('insertNoticeInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("发布公告成功!");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
      },
      deleteRow() {
        var noticeAxios = axios.create({
          baseURL: 'http://localhost:8081/api/notice/'
        });
        noticeAxios.post('deleteNoticeInfo', this.params).then(res => {
          if (res.data.code == 200) {
            this.$message.success("删除公告信息成功!");
            this.getData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
        this.delVisible = false;
      },
      formatTime(row, column) {
        var date = new Date(row.noticeTime);
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
     },
      handleDelete(index, row) {
        this.params.notice.id = row.id;
        this.delVisible = true;
      },
      handleEdit(index, row) {
        const item = this.tableData[index];
        this.params.notice = {
          id: item.id,
          noticeContent: item.noticeContent,
        };
        this.editVisible = true;
        this.isUpdate = true;
      }
    }
  }
</script>
<style scoped>
  .red{
    color: #ff0000;
  }
</style>

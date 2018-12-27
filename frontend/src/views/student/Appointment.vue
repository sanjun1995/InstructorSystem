<template>
  <div class="leave">
    选择预约时间：
    <el-time-select
      v-model="startTime"
      :picker-options="{
          start: '08:30',
          step: '00:30',
          end: '18:30'
        }"
      placeholder="选择开始时间">
    </el-time-select>
    <el-time-select
      v-model="endTime"
      :picker-options="{
          start: '08:30',
          step: '00:30',
          end: '18:30'
        }"
      placeholder="选择结束时间">
    </el-time-select>
    <div class="step">
      <el-steps :space="200" :active="1" align-center="center" finish-status="success">
        <el-step title="已完成"></el-step>
        <el-step title="进行中"></el-step>
        <el-step title="步骤 3"></el-step>
      </el-steps>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        startTime: '',
        endTime: ''
      }
    }
  }
</script>

<style scoped>
  .leave {
    padding-top: 100px;
    text-align: center;
  }
  .step {
    padding-top: 100px;
    padding-left: 400px;
  }
</style>

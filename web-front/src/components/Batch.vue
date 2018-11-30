<template>
  <div>
    <h1>批量发送</h1>

    <div style="text-align: left">
      <el-form label-width="120px" label-position="left">
        <el-form-item label="发送人">
          <el-input v-model="config.sender" :disabled="true" laceholder="请输入发件人邮箱地址"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="config.password" :disabled="true" type="password" laceholder="请输入发件人邮箱密码"></el-input>
        </el-form-item>
        <el-form-item label="主题" laceholder="请输入邮件主题">
          <el-input v-model="config.topic" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <div v-html="config.template"></div>
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary" @click="sendMail">批量发送</el-button>
      </div>
    </div>



  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'Batch',
  data () {
    return {
    }
  },
  methods :{
    async sendMail(){

      this.$confirm("是否确定发送") .then(() => {
        let response = this.$axios.post("/send",{
          config: this.config
        })
        this.$message("发送完成！")
        console.log(response.data)
      })

    }
  },
  computed: mapState({
    config: state => state.config,
  })
}
</script>
<style>


</style>

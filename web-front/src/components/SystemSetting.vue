<template>
  <div>
    <h1>系统设置</h1>

    <el-form label-width="120px" :model="config">
      <el-form-item label="发件人">
        <el-input v-model="config.sender" placeholder="请输入发件人邮箱地址"></el-input>
      </el-form-item>
      <el-form-item label="发件人密码" placeholder="请输入发件人邮箱密码">
        <el-input v-model="config.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="邮件主题" placeholder="请输入邮件主题">
        <el-input v-model="config.topic"></el-input>
      </el-form-item>

      <div>
        <el-button type="primary" @click="saveToServer">保存到服务端</el-button>
        <!--<el-button type="default" @click="loadFromServer">从服务端获取</el-button>-->
      </div>
    </el-form>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'MailSenderApp',
  data () {
    return {
    }
  },
  methods: {
    async saveToServer(){
      let response=await this.$axios.post("/save",{
        config: this.config
      })
      // console.log(response)
      this.$message("保存完成！")
    }

  },
  computed: mapState({
    config: state => state.config,
  })
}
</script>
<style>
.mce-edit-area iframe{
  min-height: 400px;
}
</style>

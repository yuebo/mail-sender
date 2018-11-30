<template>
  <div>
    <h1>邮件发送</h1>

    <div>
        <div v-show="config.file.length>0">
          <el-input  :rows="10" v-model="config.file" type="textarea" placeholder="请先选择一个csv文件" :autosize="false"></el-input>
        </div>
        <div v-show="config.file.length===0">
          <el-upload  :limit="1" :before-upload="loadAsText" action="test" drag
                      ref="upload">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </div>
        <div>
          <p style="font-size: small">必须是逗号分隔的问卷，第一列必须是收件人邮箱，如果无@符号，则后面自动匹配@qq.com</p>
        </div>
    </div>

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
  methods :{
    loadAsText(file){
      // console.log(file)
      var reader = new FileReader();
      reader.readAsText(file,'UTF-8');
      reader.onload= (e) => {
        this.config.file=e.currentTarget.result
      }
      return false;
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

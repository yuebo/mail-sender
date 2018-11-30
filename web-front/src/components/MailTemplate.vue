<template>
  <div>
    <h1>模板编辑</h1>
    <div>
      <quill-editor id="editor" v-model="config.template">
      </quill-editor>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'MailSenderApp',
  data () {
    return {
      data: ""
    }
  },
  methods: {
    // change(e){
    //   this.template = this.data
    // },
    // init(){
    //   this.data = this.template
    // }
  },
  computed: mapState({
    config: state => state.config,
  }),
  mounted(){
    this.data = this.config.template
  }
}
</script>
<style scoped>
.quill-editor {
  height: 680px;
}
.ql-container {
  height: 680px;
}

.limit {
  height: 30px;
  border: 1px solid #ccc;
  line-height: 30px;
  text-align: right;
}
span {
  color: #ee2a7b;
}

.ql-snow .ql-editor img {
  max-width: 480px;
}

.ql-editor .ql-video {
  max-width: 480px;
}
</style>

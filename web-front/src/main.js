// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import { quillEditor } from 'vue-quill-editor'
import Element from 'element-ui'
import store from './store'
import axios from  './axios'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Element)
Vue.component('quill-editor', quillEditor)
Vue.config.productionTip = false

Vue.prototype.$axios= axios;
// Vue.mixin({
//   "$axios": axios
// })
/* eslint-disable no-new */
let vue=new Vue({
  el: '#app',
  router,store,
  components: { App },
  template: '<App/>'
})


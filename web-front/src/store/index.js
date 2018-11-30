import Vuex from "vuex"
import Vue from "vue";

Vue.use(Vuex)
const store = new Vuex.Store({
  state: {
    config: {
      sender:"",
      password:"",
      topic:"",
      template:"",
      file:""
    }
  }
})

export default store

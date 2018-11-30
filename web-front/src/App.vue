<template>
  <div id="app">
    <el-container>
      <!--<el-header>Header</el-header>-->
      <el-container>
        <el-aside width="200px">
          <el-menu :router="true" default-active="/conf" :default-openeds="['0']">
            <el-submenu index="0">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>系统设置</span>
              </template>
              <el-menu-item-group >
                <el-menu-item index="/conf">邮件设置</el-menu-item>
                <el-menu-item index="/template">模板编辑</el-menu-item>

                <el-menu-item index="/send">邮件发送</el-menu-item>
                <el-menu-item index="/batch">批量发送</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>

        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'App',
  methods:{
    async loadFromServer(){
      let response=await this.$axios.get("/load")
      let data=response.data
      for(let prop in data.config){
        this.config[prop]= data.config[prop]
      }
      console.log(data)
    }
  },
  created(){
    this.loadFromServer();
  },
  computed: mapState({
    config: state => state.config,
  })
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}
.btn{
  text-align: left;
  margin-top: 20px;
}
</style>

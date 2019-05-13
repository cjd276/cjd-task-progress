<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 用户</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form"  label-width="80px">
                    <el-form-item label="用户名称">
                        <el-input v-model="user.username"></el-input>
                    </el-form-item>
                    <el-form-item label="用户密码">
                        <el-input v-model="user.password"></el-input>
                    </el-form-item>
                    <el-form-item label="昵称">
                        <el-input v-model="user.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="战队">
                        <el-input v-model="team.name"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <el-input v-model="user.create_time"></el-input>
                    </el-form-item>
                    <el-form-item label="修改时间">
                        <el-input v-model="user.modify_time"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'用户'" ref="addProjectDyna"/>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    data() {
      return {
        user:{},  
        team:{},    
      }
    },
    created() {
      this.getUser();
    },

    methods: {
      
      getUser:function(){
        var user_id = this.$route.query.id;
        console.log(this.user);
        var params = {id:user_id}
        this.$dataPostXD("/user/get", params, (resultMap)=>{
          this.user = resultMap.user;
          var sysKeyValues = resultMap.sysKeyValues;
          this.$refs.addProjectDyna.setKvs(sysKeyValues);
          if(resultMap.team){
            this.team = resultMap.team;
          }
          
        });
      }
    },
    components:{
      dynamicProperties,
      
    },
  }
</script>
<style type="text/css">
  
  .addProjectBtn{
    margin-top: 20px;
  }
</style>
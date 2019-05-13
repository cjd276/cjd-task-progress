<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 战队</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form :model="team" ref="teamForm"  label-width="80px" :rules="rules">
                    <el-form-item label="战队名称" prop="name">
                        <el-input v-model="team.name"></el-input>
                    </el-form-item>
                    <el-form-item label="战队编码" prop="code">
                        <el-input v-model="team.code"></el-input>
                    </el-form-item>
                     <el-form-item label="钉钉通知" prop="ding_url">
                        <el-input v-model="team.ding_url"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'战队'" ref="addProjectDyna" :isEdit="1"/>
    <el-button class="addProjectBtn" plain  @click="addTeam">添加战队</el-button>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    data() {
      return {
        addTeamForm:{},
        team:{},
        kvs:[],
        rules: {
              name: [
                { required: true, message: '请输入战队名称', trigger: 'blur' },
              ],
              code: [
                { required: true, message: '请输入战队编码', trigger: 'blur' },
              ],
              ding_url: [
                { required: true, message: '请输入钉钉通知', trigger: 'blur' },
              ]

            }
      }
    },
    created() {

    },

    methods: {
      
      addTeam:function(){
        this.$refs["teamForm"].validate((valid) => {
              if (valid) {
                var kvs = this.$refs.addProjectDyna.getKvs();
                var params = {team:this.team,kvs:kvs}
                this.$dataPostXD("/team/add", params);

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
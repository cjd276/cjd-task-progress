<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 项目</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form"  label-width="80px">
                    <el-form-item label="项目名称">
                        <el-input v-model="project.name"></el-input>
                    </el-form-item>
                    <el-form-item label="工程名称">
                        <el-input v-model="project.code"></el-input>
                    </el-form-item>

                    <el-form-item label="战队">
                        <el-input v-model="project.team_name"></el-input>
                    </el-form-item>
                    <el-form-item label="版本">
                        <el-input v-model="project.current_version"></el-input>
                    </el-form-item>
                    <el-form-item label="版本前缀">
                        <el-input v-model="project.current_version_prefix"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <el-input v-model="project.create_time"></el-input>
                    </el-form-item>
                    <el-form-item label="修改时间">
                        <el-input v-model="project.modify_time"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'项目'" ref="addProjectDyna"/>
    <module :title="'项目'"  ref="addModules"/>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  import module from '@/components/custom/module.vue'
  export default {
    data() {
      return {
        project:{},      

      }
    },
    created() {
      this.getProject();
    },

    methods: {
      
      getProject:function(){
        var params = {id:this.$route.query.id};
        var _this = this;
        this.$dataPostXD("/project/get", params, (resultMap)=>{
          _this.project = resultMap.project;
          _this.$refs.addProjectDyna.setKvs(resultMap.sysKeyValues)
          _this.$refs.addModules.setModules(resultMap.modules)
       
          
        });
      }
    },
    components:{
      dynamicProperties,
      module,
      
    },
  }
</script>
<style type="text/css">
  
  .addProjectBtn{
    margin-top: 20px;
  }
</style>
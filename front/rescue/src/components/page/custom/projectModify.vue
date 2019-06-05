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
                <el-form  :model="project" ref="projectForm"  label-width="100px" :rules="rules">
                    <el-form-item label="项目名称" prop="name">
                        <el-input v-model="project.name"></el-input>
                    </el-form-item>
                    <el-form-item label="工程编码" prop="code">
                        <el-input v-model="project.code"></el-input>
                    </el-form-item>
                    <el-form-item label="版本号" prop="current_version">
                        <el-input v-model="project.current_version"></el-input>
                    </el-form-item>
                    <el-form-item label="版本号前缀" prop="current_version_prefix">
                        <el-input v-model="project.current_version_prefix"></el-input>
                    </el-form-item>
                    
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'项目'" ref="addProjectDyna" :isEdit="1"/>

    <module :title="'项目'" :isEdit="1" ref="addModules"/>

    <el-button class="addProjectBtn" plain  @click="addProject">编辑项目</el-button>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  import module from '@/components/custom/module.vue'
  export default {
    data() {
      return {
        project:{},

        rules: {
              name: [
                { required: true, message: '请输入项目名称', trigger: 'blur' },
              ],
              code: [
                { required: true, message: '请输入工程编码', trigger: 'blur' },
              ],
              current_version: [
                { required: true, message: '请输入版本号', trigger: 'blur' },
              ],
              current_version_prefix: [
                { required: true, message: '请输入版本号前缀', trigger: 'blur' },
              ]

            }
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
      },
    
      addProject:function(){
        var _this = this;
        this.$refs["projectForm"].validate((valid) => {
              if (valid) {
                var kvs = _this.$refs.addProjectDyna.getKvs();
                if(_this.$refs.addModules.getModules()){
                  var modules = this.$refs.addModules.getModules();
                  var params = {project:_this.project,kvs:kvs,modules:modules}
                  _this.$dataPostXD("/project/modify", params);
                }
                

              }

          });

        
      },

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
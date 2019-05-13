<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 任务</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form"  label-width="80px">
                    <el-form-item label="任务名称">
                        <el-input v-model="task.name"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间">
                         <el-date-picker
                          v-model="task.start_time"
                          type="datetime"
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="结束时间">
                         <el-date-picker
                          v-model="task.end_time"
                          type="datetime"
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="分支">
                        <el-input v-model="task.branch"></el-input>
                    </el-form-item>
                    <el-form-item label="个人分支">
                        <el-input v-model="task.personal_branch"></el-input>
                    </el-form-item>
                    <el-form-item label="进度">
                        <el-input :value="task.task_process_name"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间">
                        <el-input v-model="task.start_time"></el-input>
                    </el-form-item>
                    <el-form-item label="结束时间">
                        <el-input v-model="task.end_time"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <el-input v-model="task.create_time"></el-input>
                    </el-form-item>
                    <el-form-item label="修改时间">
                        <el-input v-model="task.modify_time"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        

    </div>




    <dynamicProperties :title="'任务'" ref="addProjectDyna" :isEdit="0"/>
    <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 任务</el-breadcrumb-item>
                <el-breadcrumb-item>成员</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
          <div class="filter-container">
            <userChunk :taskId="this.$route.query.id" />
          </div>
        </div>
        <div class="crumbs">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 任务</el-breadcrumb-item>
            <el-breadcrumb-item>涉及项目模块</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div class="container">
      <div class="filter-container">
        <template v-for="projectModule in projectModuleList">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>{{projectModule.name}}</span>
              <span style="float: right;">{{projectModule.code}}</span>
            </div>
            <div v-for="module in projectModule.modules" :key="module.id" class="text item">             
              <el-checkbox v-model="module.checked">{{module.name}}</el-checkbox>
              <div style="display:inline-block;float:right">{{module.code}}</div>
            </div>
          </el-card>
        </template>
        
      </div>
    </div>
  </div>
  
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'

   import userChunk from '@/components/custom/userChunk.vue'
  export default {
    data() {
      return {
        task:{},
        kvs:[],
        projectModuleList:[],
      }
    },
    created() {
      this.getTaskDetail();
      this.listProjectModuleList();
    },

    methods: {

      getTaskDetail:function(){
        var task_id =  this.$route.query.id;
        this.task['id'] = task_id;


        this.$dataPostXD("/task/get", this.task,(resultMap)=>{
          this.task = resultMap.task;
          var sysKeyValues = resultMap.sysKeyValues;
          this.$refs.addProjectDyna.setKvs(sysKeyValues)

        });
      },
      listProjectModuleList:function(){
        var task_id =  this.$route.query.id;
         var team_id =  this.$route.query.team_id;
        this.$dataPostXD("/task/listProjectModuleList", {id:task_id,team_id:team_id},(resultMap)=>{

          this.projectModuleList = resultMap.projects;
          

          

        });
      },

      
    },
    components:{
      dynamicProperties,
      userChunk,
      
    },
  }
</script>
<style type="text/css">
  
  .addProjectBtn{
    margin-top: 20px;
  }
</style>
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
                <el-form label-width="80px">
                    <el-form-item label="任务名称">
                        <el-input disabled  v-model="task.name"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间">
                         <el-date-picker
                          v-model="task.start_time"
                          type="datetime"
                          disabled
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="结束时间">
                         <el-date-picker
                          v-model="task.end_time"
                          type="datetime"
                          disabled
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="分支" >
                        <el-input v-model="task.branch" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="个人分支">
                        <el-input v-model="task.personal_branch"></el-input>
                    </el-form-item>
                    <el-form-item label="任务进度">
                      <el-select v-model="task.task_process" value-key="id" placeholder="请选择进度" >
                        <el-option
                  
                          v-for="item in taskProcess"
                          :key="item.id"
                          :label="item.name"
                          :value="item">
                         

                        </el-option>
                      </el-select>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="crumbs">
          <el-breadcrumb separator="/">
              <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 任务</el-breadcrumb-item>
              <el-breadcrumb-item>阶段设置</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="container">
          <div class="form-box">
            <template v-for="(item,index) in taskProcess">
              <el-form :model="item" ref="processForm"  label-width="100px">
                
                  <el-form-item label="进度名称">
                      <el-input v-model="item.name" disabled></el-input>
                  </el-form-item>
                  <el-form-item label="进度截止时间">
                      <el-date-picker
                          v-model="item.end_notice_time"
                          type="datetime"
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                  </el-form-item>
                 
                    
              </el-form>
              <div class="spline-line"></div>
               </template>
        </div>
        </div>
    </div>




    <dynamicProperties :title="'任务'" ref="addProjectDyna" :isEdit="1"/>



    <div class="crumbs">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 任务</el-breadcrumb-item>
            <el-breadcrumb-item>涉及项目模块及apllo设置</el-breadcrumb-item>
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
               <el-button v-if="module.checked"  plain  @click="setApollo(module.id,task.plan_id)">apollo设置</el-button>
            </div>
          </el-card>
        </template>
        
      </div>
    </div>
    <el-button class="addProjectBtn" plain  @click="updateTask">更新任务</el-button>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'

  export default {
    data() {
      return {
        task:{},
        kvs:[],
        taskProcess:[],
        updateTaskBean:{},
        projectModuleList:[],

      }
    },
    created() {
      this.getTask();
      this.listProjectModuleList();

    },

    methods: {
      setApollo:function(module_id,plan_id){
        this.$router.push({
          path: "/apolloSetting",
            query: {
              module_id: module_id,
              plan_id:plan_id
            }
          })
      },
      getTask:function(){
        var task_id =  this.$route.query.id;
        this.updateTask["id"] = task_id;
        this.$dataPostXD("/task/get", {id:task_id},(resultMap)=>{
          var sysKeyValues = resultMap.sysKeyValues;
          this.$refs.addProjectDyna.setKvs(sysKeyValues);
          this.task = resultMap.task;
          this.taskProcess = resultMap.taskProcesses;


        });
      },
      updateTask:function(){
        this.updateTaskBean['id'] = this.task.id;
          this.updateTaskBean['personal_branch'] = this.task.personal_branch;
          this.updateTaskBean['task_process_id'] = this.task.task_process.id;
           this.updateTaskBean['task_process_name'] = this.task.task_process.name;
          this.updateTaskBean['plan_id'] = this.task.plan_id;
          this.updateTaskBean['name'] = this.task.name;
          this.updateTaskBean['plan_name'] = this.task.plan_name;
          var task_id =  this.$route.query.id;
        var kvs = this.$refs.addProjectDyna.getKvs();
        var params = {task:this.updateTaskBean,kvs:kvs,projects:this.projectModuleList,taskProcessList:this.taskProcess}
        console.log(params)
        this.$dataPostXD("/task/update", params);
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
      
    },
  }
</script>
<style type="text/css" scoped>
  
  .addProjectBtn{
    margin-top: 20px;
  }
  .spline-line{
    width: 100%;
    height: 10px;
    background-color: #CCC;
    margin-top: 20px;
    border-radius: 5px;
    margin-bottom: 20px;
  }



    .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
    display: inline-block;
    margin-right: 20px;
  }
</style>
<template>
  <div class="app-container">


    <taskProcess ref="taskProcessesListRef"/>

    <el-button class="addProjectBtn" plain  @click="addOrUpdateTaskProcess">设置任务进度</el-button>
  </div>
</template>
<script>
  import taskProcess from '@/components/custom/taskProcess.vue'
  export default {
    data() {
      return {
        taskProcessesList:[],

      }
    },
    created() {
      this.getTaskProcess();
    },

    methods: {
      addOrUpdateTaskProcess:function(){
        if(this.$refs.taskProcessesListRef.getTaskProcessList()){
          var tpl = this.$refs.taskProcessesListRef.getTaskProcessList();
          console.log(tpl);
          this.$dataPostXD("/taskProcess/add", {taskProcesses:tpl});
        }
        
      },
      getTaskProcess:function(){
        this.$dataPostXD("/taskProcess/list", {}, (resultMap)=>{

                this.$refs.taskProcessesListRef.setTaskProcessList(resultMap.taskProcesses);

            });
      }
     
    },
    components:{
      taskProcess,
      
    },
  }
</script>
<style type="text/css">
  
  .addProjectBtn{
    margin-top: 20px;
  }
</style>
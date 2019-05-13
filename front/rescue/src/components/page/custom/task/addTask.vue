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
                <el-form ref="taskForm"  label-width="80px" :model="task" :rules="rules">
                    <el-form-item label="任务名称" prop="name">
                        <el-input v-model="task.name"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间" prop="start_time">
                         <el-date-picker
                          v-model="task.start_time"
                          type="datetime"
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="结束时间" prop="end_time">
                         <el-date-picker
                          v-model="task.end_time"
                          type="datetime"
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="源分支" prop="branch">
                        <el-input v-model="task.branch"></el-input>
                    </el-form-item>
                    <el-form-item label="个人分支" prop="personal_branch">
                        <el-input v-model="task.personal_branch"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'任务'" ref="addProjectDyna" :isEdit="1"/>
    <el-button class="addProjectBtn" plain  @click="addTask">添加任务</el-button>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    data() {
      return {
        task:{},
        kvs:[],
        rules: {
              name: [
                { required: true, message: '请输入任务名称', trigger: 'blur' },
              ],
              start_time: [
                { required: true, message: '请选择开始时间', trigger: 'blur' },
              ],
              end_time: [
                { required: true, message: '请选择结束时间', trigger: 'blur' },
              ],
              branch: [
                { required: true, message: '请输入源分支', trigger: 'blur' },
              ],
              personal_branch: [
                { required: true, message: '请输入个人分支', trigger: 'blur' },
              ]

            }
      }
    },
    created() {

    },

    methods: {
      
      addTask:function(){

          this.$refs["taskForm"].validate((valid) => {
              if (valid) {
                var plan_id =  this.$route.query.id;
                var plan_name =  this.$route.query.plan_name;
                this.task['plan_id'] = plan_id;
                this.task['plan_name'] = plan_name;
                var kvs = this.$refs.addProjectDyna.getKvs();
                var params = {task:this.task,kvs:kvs}
                this.$dataPostXD("/task/add", params);

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
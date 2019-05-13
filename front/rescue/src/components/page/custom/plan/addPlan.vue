<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 上线计划</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="planForm"  label-width="80px" :rules="rules" :model="plan">
                    <el-form-item label="计划名称" prop="name">
                        <el-input v-model="plan.name"></el-input>
                    </el-form-item>
                    <el-form-item label="上线时间" prop="expect_time">
                         <el-date-picker
                          v-model="plan.expect_time"
                          type="datetime"
                          placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="阶段" prop="planStatus">
                       <el-select v-model="plan.planStatus" placeholder="请选进度状态" >
                        <el-option
                          v-for="item in planStatus"
                          :key="item.key"
                          :label="item.value"
                          :value="item.value">
                          <span style="float: left">{{ item.value }}</span>
                          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.key }}</span>
                        </el-option>
                      </el-select>
                    </el-form-item> 
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'上线计划'" ref="addProjectDyna" :isEdit="1"/>
    <el-button class="addProjectBtn" plain  @click="addPlan">添加计划</el-button>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  import planStatus from '@/components/common/PlanStatus.js'
  export default {
    data() {
      return {
        plan:{},
        kvs:[],
        planStatus:planStatus,
        rules: {
              name: [
                { required: true, message: '请输入计划名称', trigger: 'blur' },
              ],
              expect_time: [
                { required: true, message: '请选择上线时间', trigger: 'blur' },
              ],
              planStatus: [
                { required: true, message: '请选择阶段', trigger: 'blur' },
              ],


            }
      }
    },
    created() {

    },

    methods: {
      
      addPlan:function(){

        this.$refs["planForm"].validate((valid) => {
              if (valid) {
                var kvs = this.$refs.addProjectDyna.getKvs();
                var params = {plan:this.plan,kvs:kvs}
                 this.$dataPostXD("/plan/add", params);

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
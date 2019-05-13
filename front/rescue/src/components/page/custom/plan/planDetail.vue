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
                <el-form ref="form"  label-width="80px">
                    <el-form-item label="计划名称">
                        <el-input v-model="plan.name"></el-input>
                    </el-form-item>
                    <el-form-item label="上线时间">
                         <el-date-picker
                          v-model="plan.expect_time"
                          type="datetime"
                          placeholder="选择日期时间">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="阶段">
                       <el-select v-model="plan.plan_status" placeholder="请选进度状态" >
                        <el-option
                          v-for="item in planStatus"
                          :key="item.key"
                          :label="item.value"
                          :value="item.key">
                          <span style="float: left">{{ item.value }}</span>
                          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.key }}</span>
                        </el-option>
                      </el-select>
                    </el-form-item> 
                </el-form>
            </div>
        </div>
        
    </div>




    <dynamicProperties :title="'上线计划'" ref="addProjectDyna"/>
    <tasks :plan_id="this.$route.query.id" />
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  import tasks from '@/components/custom/tasks.vue'
  import planStatus from '@/components/common/PlanStatus.js'

  export default {
    data() {
      return {
        plan:{}, 
        planStatus:planStatus,     
      }
    },
    created() {
      this.getPlan();
    },

    methods: {
      
      getPlan:function(){
        var params = {id:this.$route.query.id}
        this.$dataPostXD("/plan/get", params, (resultMap)=>{
          this.plan = resultMap.plan;
          var sysKeyValues = resultMap.sysKeyValues;
          this.$refs.addProjectDyna.setKvs(sysKeyValues)
        });
      }
    },
    components:{
      dynamicProperties,
      tasks,
      
    },
  }
</script>
<style type="text/css">
  
  .addProjectBtn{
    margin-top: 20px;
  }
</style>
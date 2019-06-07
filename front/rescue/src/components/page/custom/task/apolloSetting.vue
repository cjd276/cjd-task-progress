<template>
  <div class="ontainer">
    <template v-for="(apollo, index) in apollos">
    <el-form ref="apollosForm" label-width="0px"  class="form-inline" style="width: 1000px;">
      
      <el-form-item 
        style="display: inline-block;" 
        prop="key_str"
        
       >
        <el-input v-model="apollo.key_str" placeholder="键" >
          
        </el-input>
        </el-form-item>
        <el-form-item 
        style="display: inline-block;margin-left: 10px" 
        prop="value_str"
       >
        <el-input v-model="apollo.value_str" placeholder="值" style="margin-left: 10px;">
          
        </el-input>
      </el-form-item>
      <el-form-item style="display: inline-block;margin-left: 10px" >
        <el-select v-model="apollo.operate_type" placeholder="请选择" style="margin-left: 10px;">
          <el-option
            v-for="item in operate_types"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-button @click.prevent="removeDomain(apollo)" style="margin-left: 10px;">删除</el-button>
      </el-form-item>
      
      
    </el-form>
  
    </template>
    <el-form>
    <el-form-item>
        <el-button type="primary" @click="submitForm('apollosForm')">设置apollo</el-button>
        <el-button @click="addDomain">新增apollo配置</el-button>
      </el-form-item>
  </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        operate_types:["添加","修改","删除"],
        apollos: [{
          key_str:'',
          value_str:'',
          operate_type:'添加',
          module_id:this.$route.query.module_id,
          plan_id:this.$route.query.plan_id
        }],
  
      };
    },
    created() {
      this.getModuleApollo();


    },
    methods: {
      getModuleApollo:function(){
        var module_id = this.$route.query.module_id;
        var plan_id = this.$route.query.plan_id;
        this.$dataPost("/plan/getModuleApollo", {plan_id:plan_id,module_id:module_id},(resultMap)=>{
          if(null != resultMap.apollos && resultMap.apollos.length > 0){
            this.apollos = resultMap.apollos;
          }
          


        });

        
      },
      submitForm(formName) {
        var module_id = this.$route.query.module_id;
        var plan_id = this.$route.query.plan_id;

        
        this.$dataPostXD("/plan/setModuleApollo", {apollo:{plan_id:plan_id,module_id:module_id},apollos:this.apollos},(resultMap)=>{
   
        });
        
      },

      removeDomain(item) {
        var index = this.apollos.indexOf(item)
        if (index !== -1) {
          this.apollos.splice(index, 1)
        }
      },
      addDomain() {
        this.apollos.push({
          key_str:'',
          value_str:'',
          operate_type:'添加',
          module_id:this.$route.query.module_id,
          plan_id:this.$route.query.plan_id,
          _id: Math.random()
        });
      }
    }
  }
</script>
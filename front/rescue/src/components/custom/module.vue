<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> {{title}}</el-breadcrumb-item>
                <el-breadcrumb-item>模块属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">

            <div class="form-box">
           <el-form  label-width="100px">

                    <el-form-item  v-if="isEdit==1">
                        <el-button plain @click="addModule()" class="lef20">添加模块</el-button>
                     </el-form-item>
                     

                    
                </el-form>
              </div>
              
            <div class="form-box form-box-w">

               <template v-for="(module,index) in modules">
                
                  <el-form  label-width="100px" :model="module" ref="moduleForm" :rules="rules">
                  
                    
                    
                    
                    <el-form-item label="模块名称" prop="name">
                        <el-input v-model="module.name"></el-input>
                    </el-form-item>
                    <el-form-item label="模块包名称" prop="code">
                        <el-input v-model="module.code"></el-input>
                    </el-form-item>
                    <el-form-item label="操作模板" prop="operate">
                        <el-input type="textarea"  v-model="module.operate" ></el-input>
                      </el-form-item>
                      <el-form-item label="回退模板" prop="operate_return">
                        <el-input type="textarea"  v-model="module.operate_return"></el-input>
                      </el-form-item>
                      <el-form-item label="服务监控项" prop="service_monitor">
                        <el-input type="textarea"  v-model="module.service_monitor"></el-input>
                      </el-form-item>
                      <div class="moduleDyna">
                       <dynamicProperties :title="'项目/模块'" :ref="'addModuleDyna'+index" :isEdit="isEdit"/>
                     </div>
                    <el-form-item  v-if="isEdit==1">
                        <el-button v-if="isEdit==1" plain  @click="deleteModule(index)" class="lef20">删除模块</el-button>
                     </el-form-item>
                 
           
                  

                    
                  </el-form>
                  <div class="spliteLine" v-if="index != modules.length - 1"></div>
                  </template>
                 
            </div>
            
            

        </div>

        

    </div>
</template>
<script>
  
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    name:"module",
    props:{
      title:{
        type:String,
        default:'对象'
      },
      isEdit:{
        type:Number,
        default:0
      },
    },
    data() {
      return {
        modules:[],
        rules: {
              name: [
                { required: true, message: '请输入角色名称', trigger: 'blur' },
              ],
              code: [
                { required: true, message: '请输入模块war名称', trigger: 'blur' },
              ]

            }
      }
    },
    created() {
      if(this.isEdit == 1){
        this.addModule();
        
      }
    },

    methods: {
      setModuleKvs:function(){
        for (var i = this.modules.length - 1; i >= 0; i--) {
          var kvs = this.modules[i].sysKeyValues;
          var key = "addModuleDyna"+i;
          var ss = this.$refs;
          this.$refs[key][0].setKvs(kvs);
        }
      },
      addModule:function(){
        var temp = {
          name:'',
          code:'',
          _id:Math.random()
        };
        this.modules.push(temp);
      },
      deleteModule:function(index){
        this.modules.splice(index,1);
      },
      getModules:function(){


        /*this.$refs["moduleForm"].validate((valid) => {
              if (valid) {
                for (var i = this.modules.length - 1; i >= 0; i--) {
        
                  var key = "addModuleDyna"+i;
                  var temp = this.$refs[key][0].getKvs();
                  this.modules[i].sysKeyValues = temp;
                }
                return this.modules;

              }else{
                return false;
              }

          });*/
        if(this.myvalidate(this.$refs["moduleForm"])){
          for (var i = this.modules.length - 1; i >= 0; i--) {
            var key = "addModuleDyna"+i;
            var temp = this.$refs[key][0].getKvs();
            this.modules[i].sysKeyValues = temp;
          }
          return this.modules;
        }
        
      },
      setModules:function(modulesParam){
        
        this.modules = modulesParam;
        
        
      },
      myvalidate:function(forms){
        var result =true;
        if(forms instanceof Array && forms.length > 0){

          for (var i = forms.length - 1; i >= 0; i--) {
            forms[i].validate((valid)=>{
              if(!valid){
                result = false;
              }
            });
          }
          return result;
        }
 
        return false;
      }



    },
    watch:{
      modules:function(){
        this.$nextTick(function(){
          this.setModuleKvs()
        });
      }
    },
    components:{

      dynamicProperties,
    },
  }
</script>
<style type="text/css">
  .lef20{
     float: right;
  }
  .spliteLine{
    width: 100%;
    height: 10px;
    background: #F0F0F0;
    margin-bottom: 20px;
    border-radius: 5px;
  }
  .moduleDyna{
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .moduleDyna .container{
    padding-left: 0 !important;
    border: none !important;
  }
  .form-box-w{
    width: 700px !import;
  }
</style>
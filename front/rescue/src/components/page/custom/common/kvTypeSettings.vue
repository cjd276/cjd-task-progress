<template>


    <div class="container">
    	<div class="form-box">
           <el-form  label-width="100px">

            <el-form-item>
                <el-button plain @click="addKvType()" class="lef20">添加动态属性类型</el-button>
             </el-form-item>         
	        </el-form>
	      </div>
    	
	        <div class="form-box">
            <template v-for="(item,index) in kvTypes">
	            <el-form :model="item" ref="processForm"  label-width="150px" :rules="rules">
                
	                <el-form-item label="动态属性类型名称" prop="kv_type_name">
	                    <el-input v-model="item.kv_type_name"></el-input>
	                </el-form-item>
	             
	                <el-form-item label="动态属性类型排序" prop="kv_sort">
	                    <el-input v-model="item.kv_sort" ></el-input>
	                </el-form-item>
	                <el-form-item >
                        <el-button plain  @click="deleteKvType(index)" class="lef20">删除动态属性类型</el-button>
                     </el-form-item>
                    
	            </el-form>
            </template>
	        </div>
        <el-button class="addProjectBtn" plain  @click="addOrUpdateKvType">设置动态属性类型</el-button>
		
    </div>

</template>
<script>
  export default {
    name:"taskProcess",
    props:{
      
    },
    data() {
      return {
        kvTypes:[],
        rules: {
                kv_type_name: [
                  { required: true, message: '请输入进度名称', trigger: 'blur' },
                ],
                kv_sort: [
                  { required: true, message: '请输入阶段排序', trigger: 'blur' },
                ],
                

              }

      }
    },
    created() {
      this.setkvTypes();
    },

    methods: {
      addKvType:function(){
      	 
      	var kvTypesTemplate = {
      		_id:Math.random(),
      		kv_type_name:'',
      		kv_sort:''
      	};
        this.kvTypes.push(kvTypesTemplate); 
      },
      deleteKvType:function(index){
		this.kvTypes.splice(index,1);
      },

      getkvTypes:function(){

        if(this.myvalidate(this.$refs["processForm"])){
          return this.kvTypes;
        }
        return false;

      
      },
      addOrUpdateKvType:function(){
        if(this.getkvTypes()){
          
          this.$dataPostXD("/kvType/deleteAndAdd", {kvTypeList:this.kvTypes});
        }
      },
      setkvTypes:function(kvTypes){
        this.$dataPost("/kvType/list", {}, (resultMap)=>{
                this.kvTypes = resultMap.list;
                if(!this.kvTypes || this.kvTypes.length == 0){
                  this.addKvType();
                }
            });
      	
        
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
    components:{

      
    },
  }
</script>
<style type="text/css">
  .lef20{
     float: right;
  }
</style>
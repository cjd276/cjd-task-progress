<template>


    <div class="container">
    	<div class="form-box">
           <el-form  label-width="100px">

            <el-form-item>
                <el-button plain @click="addTaskProcess()" class="lef20">添加进度项</el-button>
             </el-form-item>         
	        </el-form>
	      </div>
    	
	        <div class="form-box">
            <template v-for="(item,index) in taskProcessList">
	            <el-form :model="item" ref="processForm"  label-width="100px" :rules="rules">
                
	                <el-form-item label="进度名称" prop="name">
	                    <el-input v-model="item.name"></el-input>
	                </el-form-item>
	              <!--   <el-form-item label="进度权重" prop="weight">
                    <el-input v-model="item.weight"></el-input>
                </el-form-item> -->
	                <el-form-item label="阶段排序" prop="process_sort">
	                    <el-input v-model="item.process_sort" ></el-input>
	                </el-form-item>
	                <el-form-item >
                        <el-button plain  @click="deleteProcess(index)" class="lef20">删除进度项</el-button>
                     </el-form-item>
                    
	            </el-form>
               </template>
	        </div>

		
    </div>

</template>
<script>
  export default {
    name:"taskProcess",
    props:{
      
    },
    data() {
      return {
        taskProcessList:[],
        rules: {
                name: [
                  { required: true, message: '请输入进度名称', trigger: 'blur' },
                ],
                weight: [
                  { required: true, message: '请输入进度权重', trigger: 'blur' },
                ],
                process_sort: [
                  { required: true, message: '请输入阶段排序', trigger: 'blur' },
                ],
                

              }

      }
    },
    created() {
    },

    methods: {
      addTaskProcess:function(){
      	 
      	var taskProcessTemplate = {
      		_id:Math.random(),
      		name:'',
      		weight:'',
      		process_sort:''
      	};
        this.taskProcessList.push(taskProcessTemplate); 
      },
      deleteProcess:function(index){
		this.taskProcessList.splice(index,1);
      },

      getTaskProcessList:function(){

        if(this.myvalidate(this.$refs["processForm"])){
          return this.taskProcessList;
        }
        return false;

      
      },
      setTaskProcessList:function(taskProcessList){
      	if(!taskProcessList || taskProcessList.length == 0){
      		this.addTaskProcess();
      	}else{
      		this.taskProcessList = taskProcessList;
      	}
        
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
<template>
	<div class="planChunkContainer" :class="[isBtn==1?'btnCls':'addCls',showActive]"  @mouseenter="enter(isBtn)" @mouseleave="leave()" @click="chunkClick(isBtn,item)">
		<div v-if="isBtn==0" class="name">{{item.name}}</div>
		<div v-else class="nameBig"><i class="el-icon-circle-plus-outline"></i></div>
		<div v-if="isBtn==0" class="desc">{{item.expect_time}}</div>
		<div v-else class="desc">{{title}}</div>
		<div v-if="isBtn==0" class="desc">{{planStatusAlter(item.plan_status)}}</div>
		<el-button v-show="chunkDeleteShow" v-if="isBtn==0" icon="el-icon-delete" class="chunkDelete" @click.stop="deleteChunk(item)"></el-button>
		<el-button style="margin-left:0px" v-show="chunkDeleteShow" v-if="isBtn==0" icon="el-icon-plus" class="chunkAddTask" @click.stop="addTask(item)"></el-button>
	</div>
</template>
<script type="text/javascript">
	export default {
	name:"planChunk",
	props:{
		item:{
			type: Object,
			default: ()=>{}
		},
		isBtn:{
			type:Number,

		},
		title:{
			type:String,
			default:'创建项目'

		}	
	},
	data(){
		return {
			showActive:"",
			chunkDeleteShow:0
		}

	},

	methods:{
		planStatusAlter:function(plan_status){
			var planStatusText = '';
			switch(plan_status)
			{
			case '1':
			  planStatusText = '准备中';
			  break;
			case '2':
			  planStatusText = '进行中';
			  break;
			default:
			  planStatusText = '准备中';
			}
			return planStatusText;
		},
		enter:function(isBtn){
			if(isBtn == '1'){
				this.showActive = 'btnActive';
			}else{
				this.showActive = 'unBtnActive';
			}
			this.chunkDeleteShow = 1; 
		},
		leave:function(){
			this.showActive = "";
			this.chunkDeleteShow = 0;
		},
		chunkClick:function(isBtn,item){
			this.$emit('chunkClick',isBtn,item)
		},
		deleteChunk:function(item){
			this.$emit('deleteChunk',item);
		},
		addTask:function(item){
			this.$router.push({
          	  path: "/addTask",
	          query: {
	            id: item.id
	          }
          })
		}
	}
}
</script>



<style type="text/css">
	.planChunkContainer{
		width: 200px;
    height: 120px;
    text-align: center;

    float: left;
    margin-left: 20px;
    margin-top: 20px;
    cursor: pointer;
        position: relative;
		
	}
	.btnCls{
		background-color: #E6A23C;
		border-radius: 5px;
		border:1px solid #DCDFE6;
		color: white;
	}
	.addCls{
		background-color: #409EFF;
		border-radius: 5px;
		border:1px solid #DCDFE6;
		color: white;
	}
	.desc{
		width: 100%;
		height: 40px;
		vertical-align: center;
		line-height: 40px;
	}
	.name{
		width: 100%;
		height: 40px;
		line-height: 40px;
	}
	.nameBig{
		width: 100%;
		height: 80px;
		line-height: 40px;
	}
	.btnActive{
		background-color: #67C23A;
	}
	.unBtnActive{
		background-color: #67C23A;
		color: white;
	}
	.chunkDelete{
		position: absolute;
    top: 0;
    right: 0;
        border: none;
    opacity: 0.5;
    background: none;
	}
	.chunkAddTask{
		position: absolute;
    top: 0;
    left: 0;
        border: none;
    opacity: 0.5;
    background: none;

	}
</style>
</style>
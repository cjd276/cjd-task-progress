<template>
	<div >
		<div class="userChunkContainer" :class="['addCls']"  @mouseenter="enter($event)" @mouseleave="leave($event)" @click="chunkClick(item)" v-for="item in userList">
			<div class="name">{{item.username}}</div>
			<div class="desc">{{item.nickname}}</div>
		</div>
	</div>
	
</template>
<script type="text/javascript">
	export default {
	name:"chunk",
	props:{
		taskId:{
			type: String,
			default: ''
		},
			
	},
	data(){
		return {
			showActive:"",
			chunkDeleteShow:0,
			userList:[],
		}

	},
	methods:{

		enter:function($event){
			$event.currentTarget.className = "unBtnActive addCls userChunkContainer";

			
		},
		leave:function($event){
			$event.currentTarget.className = "addCls userChunkContainer";
		},
		chunkClick:function(item){
			this.$router.push({
          	  path: "/userDetail",
	          query: {
	            id: item.id
	          }
          })
		},
		getTaskUser:function(){
			this.$dataPost("/task/listTaskUser", {id:this.taskId}, (resultMap)=>{
	          this.userList = resultMap.userList;
	        });
			
		}

	},
	created:function(){
		this.getTaskUser();
	}
}
</script>



<style type="text/css">
	.userChunkContainer{
		width: 200px;
    height: 80px;
    text-align: center;
    margin-right: 20px;
    
    cursor: pointer;
        position: relative;
        display: inline-block;
		
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
	}
	.name{
		width: 100%;
		height: 40px;
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
</style>
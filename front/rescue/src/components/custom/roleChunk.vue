<template>
	<div class="chunkContainer" :class="[isBtn==1?'btnCls':'addCls',showActive]"  @mouseenter="enter(isBtn)" @mouseleave="leave()" @click="chunkClick(isBtn,item)">
		<div v-if="isBtn==0" class="name">{{item.code}}</div>
		<div v-else class="name"><i class="el-icon-circle-plus-outline"></i></div>
		<div v-if="isBtn==0" class="desc">{{item.name}}</div>
		<div v-else class="desc">{{title}}</div>
		<el-button v-show="chunkDeleteShow" v-if="isBtn==0" icon="el-icon-delete" class="chunkDelete" @click.stop="deleteChunk(item)"></el-button>
		<el-button style="margin-left:0px" v-show="chunkDeleteShow" v-if="isBtn==0" icon="el-icon-plus" class="chunkRoleToMenu" @click.stop="roleToMenu(item)"></el-button>
	</div>
</template>
<script type="text/javascript">
	export default {
	name:"roleChunk",
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
		roleToMenu:function(item){
			this.$emit('roleToMenu',item)
		}
	}
}
</script>



<style type="text/css">
	.chunkContainer{
		width: 200px;
    height: 80px;
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
	.chunkRoleToMenu{
		position: absolute;
    top: 0;
    left: 0;
        border: none;
    opacity: 0.5;
    background: none;

	}
</style>
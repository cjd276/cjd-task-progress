<template>


	<div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 用户</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
			<div class="form-box" style="display:inline-block">

				<el-form :model="menu" ref="menuForm" :rules="rules" label-width="80px">
				  <el-form-item label="菜单图标" prop="icon">
				    <el-input v-model="menu.icon"></el-input>
				  </el-form-item>
				  <el-form-item label="菜单路径" prop="path">
				    <el-input v-model="menu.path"></el-input>
				  </el-form-item>
				  <el-form-item label="菜单排序" prop="sort">
				    <el-input v-model="menu.sort"></el-input>
				  </el-form-item>
				  <el-form-item label="菜单类型" prop="menue_type">
				    <el-select v-model="menu.menue_type" placeholder="请选择">
					    <el-option
					      v-for="item in options"
					      :key="item.value"
					      :label="item.label"
					      :value="item.value">
					    </el-option>
					  </el-select>
				  </el-form-item>
				  
				  <el-form-item label="父菜单" prop="parent" v-model="mtemp">
				    <el-tree
					  :data="treeData"
					  node-key="id"
					  
					  ref="tree"
					  show-checkbox
					  check-strictly
					  @check-change="checkChange"
		  			@node-click="nodeClick"
		  			:props="tprops"
		  			
					  >
					</el-tree>
				  </el-form-item>
				  <el-form-item label="菜单名称" prop="menu_name">
				    <el-input v-model="menu.menu_name"></el-input>
				  </el-form-item>
				  <el-form-item label="权限字符" prop="permission_code">
				    <el-input v-model="menu.permission_code"></el-input>
				  </el-form-item>
				  <el-form-item label="权限名称" prop="permission_name">
				    <el-input v-model="menu.permission_name"></el-input>
				  </el-form-item>


				</el-form>

				
			</div>
			

		</div>
    </div>




    <el-button class="addProjectBtn" plain  @click="addMenu">添加菜单</el-button>
  </div>




</template>
<script type="text/javascript">
	

	export default{
		data(){
		    const validateTree = (rule, value, callback) => {
		      if (this.mtemp == 1) {
		        callback()
		      } else {
		        callback(new Error('请选择父菜单'))
		      }
		    }
			return {
				mtemp:"",
				tprops:{
					label:"menu_name",
					children:"children"
				},
				menu:{},
				delete_status:false,
				options:[
					{
			          value: "1",
			          label: '菜单'
			        }, {
			          value: '2',
			          label: '按钮'
			        }
				],
				
	        	treeData:[],
	        	defaultProps: {
		          children: 'children',
		          label: 'label'
		        },
		     
		        rules: {
	              icon: [
	                { required: true, message: '请输入菜单图标', trigger: 'blur' },
	              ],
	              path: [
	                { required: true, message: '请输入菜单路径', trigger: 'blur' },
	              ],
	              sort: [
	                { required: true, message: '请输入菜单排序', trigger: 'blur' },
	              ],
	              menue_type: [
	                { required: true, message: '请选择菜单类型', trigger: 'blur' },
	              ],
	              parent: [
	                { required: true, validator: validateTree, trigger: 'blur' },
	              ],
	              menu_name: [
	                { required: true, message: '请输入菜单名称', trigger: 'blur' },
	              ],
	              permission_code: [
	                { required: true, message: '请输入权限字符', trigger: 'blur' },
	              ],
	              permission_name: [
	                { required: true, message: '请输入权限名称', trigger: 'blur' },
	              ]

	            }
		      
			}
		},
		created(){
			this.queryMenuTree();
		},
		methods:{
			queryMenuTree:function(){
				this.$dataPost("/menu/queryMenuTree", {},(resultMap)=>{
					console.log(resultMap.menuTree);
					this.treeData=resultMap.menuTree;
				});
			},
			addMenu:function(){

				this.$refs["menuForm"].validate((valid) => {
	              if (valid) {
	                var checkNodes = this.$refs.tree.getCheckedNodes();
					if(checkNodes && checkNodes.length > 0	){
						this.menu['parent_id'] = checkNodes[0].id;
					}

					this.$dataPost("/menu/add", this.menu);

	              }

	          });




				
			},
			parentChange:function(item){
				console.log(item);

			},
			checkChange(item, node, self){
				if (node==true) {//点击未选中复选框
					this.editCheckId = item.id;
					this.$refs.tree.setCheckedKeys([item.id]);
				} else {
					if (this.editCheckId == item.id) {//点击已选中复选框，保证至少一个选中
						this.$refs.tree.setCheckedKeys([item.id]);
					}
				}
			},	
			nodeClick(item, node, self){
				this.mtemp = 1;
				this.editCheckId = item.id;
				this.$refs.tree.setCheckedKeys([item.id]);
			}	

		},
		components:{

		}
	}




</script>
<style type="text/css">
	.addProjectBtn{
    margin-top: 20px;
  }
  .incon-container{
  	width: 800px;
  	display: inline-block;
  }
</style>
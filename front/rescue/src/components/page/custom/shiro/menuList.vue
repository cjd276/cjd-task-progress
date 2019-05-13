  <template>
	<div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 基础表格</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="container">
        	<div class="handle-box">
                <el-input v-model="menu.menu_name" placeholder="菜单名称搜索" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>

                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addMenu">添加菜单</el-button>
            </div>
			<el-table
		      :data="menuList"
		      style="width: 100%">
		      <el-table-column
		        prop="path"
		        label="菜单路径"
		        width="180">
		      </el-table-column>
		      <el-table-column
		        prop="menue_type"
		        label="菜单类型"
		        width="180" :formatter="menueTypeFormatter">
		      </el-table-column>
		      <el-table-column
		        prop="menu_name"
		        label="菜单名称">
		      </el-table-column>
			  <el-table-column
		        prop="permission_code"
		        label="权限字符">
		      </el-table-column>
          <el-table-column
            prop="permission_name"
            label="权限名称">
          </el-table-column>
          <el-table-column
            prop="sort"
            label="菜单排序">
          </el-table-column>
          
		      <el-table-column
		        prop="create_time"
		        label="创建时间">
		      </el-table-column>
		      <el-table-column
		        prop="modify_time"
		        label="修改时间">
		      </el-table-column>
			  <el-table-column label="操作" width="260" align="center">
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
		    </el-table>
	  		<el-pagination
	  		@current-change="handleCurrentChange"
			  background
			  layout="prev, pager, next"
        :page-size="15"
			  :total="total">
			</el-pagination>
    	</div>
	</div>



















    
  </template>

  <script>
    export default {
      data() {
        return {
          menuList: [],
          total:100,
          menu:{'pageIndex':1},
        }
      },
      created:function(){
      	this.getMenuList(1);
      },
      methods:{
      	getMenuList:function(){
      	  
          this.$dataPostXD("/menu/list", this.menu, (resultMap)=>{
                this.menuList = resultMap.menuList;
                this.total = resultMap.total;
            });

      	},
      	search:function(){
      		this.getMenuList()
      	},
      	handleCurrentChange:function(pageIndex){
      		this.menu['pageIndex'] = pageIndex;
      		this.getMenuList();
      	},
      	handleEdit:function(){

      	},
      	handleDelete:function(index,row){
      		this.$dataPostXD("/menu/delete", row, (resultMap)=>{
                this.getMenuList();
            });
      	},
      	handleRoleAssign:function(index, row){

      		this.$router.push({
          	  path: "/roleAssign",
	          query: {
	            id: row.id
	          }
          })
      	},
      	addMenu:function(){
      		this.$router.push("/addMenu")
      	},
      	menuDetail:function(index, row){
      		this.$router.push({
          	  path: "/menuDetail",
	          query: {
	            id: row.id
	          }
          })
      	},
        menueTypeFormatter:function(row, column, cellValue){
          if(row.menue_type == 1 ){
            return '菜单';
          }else{
            return '按钮';
          } 
        }

      }

    }
  </script>
<style type="text/css">
	.handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .mr10{
        margin-right: 10px;
    }
</style>



















<!-- 



<template>
	<div class="form-box">

		





		
	</div>





</template>
<script type="text/javascript">
	

	export default{
		data(){
			return {

			}
		},
		created(){

		},
		methods:{

		}
	}




</script>
<style type="text/css">
	
</style> -->
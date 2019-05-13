  <template>
	<div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 基础表格</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="container">
        	<div class="handle-box">
                <el-input v-model="user.username" placeholder="用户名称搜索" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>

                <el-button type="primary" icon="search" class="mr10" @click="addToTeam"><i class="el-icon-arrow-down el-icon--left"></i>添加 || 移动到<i class="el-icon-arrow-right el-icon--right"></i></el-button>
                <el-select v-model="userTeamParam.team_id" placeholder="请选择战队" >
                  <el-option
                  filterable
                    v-for="item in teams"
                    :key="item.name"
                    :label="item.name"
                    :value="item.id">
                    <span style="float: left">{{ item.name }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.code }}</span>
                  </el-option>
                </el-select>
            </div>
			<el-table
		      :data="userList"
		      style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
		      <el-table-column
		        prop="username"
		        label="用户名称"
		        width="180">
		      </el-table-column>
		      <el-table-column
		        prop="password"
		        label="密码"
		        width="180">
		      </el-table-column>
		      <el-table-column
		        prop="nickname"
		        label="昵称">
		      </el-table-column>
          <el-table-column
            prop="team_name"
            label="战队">
          </el-table-column>
			  <el-table-column
		        prop="delete_status"
		        label="是否有效" :formatter="deleteStatusFormatter">
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
                	<el-button type="text" icon="el-icon-star-off" @click="userDetail(scope.$index, scope.row)">详情</el-button>           
                </template>
            </el-table-column>
		    </el-table>
	  		<el-pagination
	  		@current-change="handleCurrentChange"
			  background
			  layout="prev, pager, next"
			  :total="total">
			</el-pagination>
    	</div>
	</div>



















    
  </template>

  <script>
    export default {
      data() {
        return {
          userList: [],
          total:100,
          user:{'pageIndex':1},
          teams:[],
          userTeamParam:{}
        }
      },
      created:function(){
      	this.getUserList(1);
        this.teamList();
      },
      methods:{
      	getUserList:function(){
      	  
          this.$dataPostXD("/team/listUser", this.user, (resultMap)=>{
                this.userList = resultMap.userList;
                this.total = resultMap.total;
            });

      	},
      	search:function(){
      		this.getUserList()
      	},
      	handleCurrentChange:function(pageIndex){
      		this.user['pageIndex'] = pageIndex;
      		this.getUserList();
      	},
      	
      	handleDelete:function(index,row){
      		this.$dataPostXD("/user/delete", row, (resultMap)=>{
                this.getUserList();
            });
      	},
      	deleteStatusFormatter:function(row, column, cellValue){
      		if(row.delete_status == 0 ){
      			return '有效';
      		}else{
      			return '无效';
      		}	

      	},
      
      	userDetail:function(index, row){
      		this.$router.push({
          	  path: "/userDetail",
	          query: {
	            id: row.id
	          }
          })
      	},
        addToTeam:function(){
          this.$dataPostXD("/team/addUserTeam", this.userTeamParam, (resultMap)=>{
            this.getUserList();
          })
        },
        teamList:function(){
          this.$dataPost("/team/list", {levels: 1}, (resultMap)=>{
            this.teams = resultMap.teamList;
          })
        },
        handleSelectionChange:function(selection){
          this.userTeamParam['user_ids'] = selection;
        },

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
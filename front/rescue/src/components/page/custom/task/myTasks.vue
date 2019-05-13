  <template>
	<div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 基础表格</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="container">
        	<div class="handle-box">
                <el-input v-model="task.name" placeholder="任务名称搜索" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
			<el-table
		      :data="taskList"
		      style="width: 100%">
		      <el-table-column
		        prop="name"
		        label="任务名称"
		        width="180">
		      </el-table-column>
		      <el-table-column
		        prop="start_time"
		        label="开始时间"
		        width="180">
		      </el-table-column>
		      <el-table-column
		        prop="end_time"
		        label="结束时间">
		      </el-table-column>
			  <el-table-column
		        prop="task_process_name"
		        label="进度" >
		      </el-table-column>

          <el-table-column
            prop="branch"
            label="源分支">
          </el-table-column>
          <el-table-column
            prop="personal_branch"
            label="个人分支">
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
                	<el-button type="text" icon="el-icon-star-off" @click="taskDetail(scope.$index, scope.row)">详情</el-button>
                    <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    
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
          taskList: [],
          total:100,
          task:{'pageIndex':1},
        }
      },
      created:function(){
      	this.getTasks(1);
      },
      methods:{
      	getTasks:function(){

          this.$dataPostXD("/task/list", this.task, (resultMap)=>{

                this.taskList = resultMap.taskList;
                this.total = resultMap.total;
            });

      	},
      	search:function(){
      		this.getTasks()
      	},
      	handleCurrentChange:function(pageIndex){
      		this.task['pageIndex'] = pageIndex;
      		this.getTasks();
      	},
      	handleEdit:function(index,row){
          this.$router.push({
              path: "/updateTask",
              query: {
                id: row.id,
                team_id:row.team_id
              }
          })
      	},

      	taskDetail:function(index, row){
      		this.$router.push({
          	  path: "/taskDetail",
	          query: {
	            id: row.id,
              team_id:row.team_id
	          }
          })
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
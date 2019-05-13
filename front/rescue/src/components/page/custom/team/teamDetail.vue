<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 战队</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form"  label-width="80px">
                    <el-form-item label="项目名称">
                        <el-input v-model="project.name"></el-input>
                    </el-form-item>
                    <el-form-item label="工程名称">
                        <el-input v-model="project.code"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>

    




    <dynamicProperties :title="'战队'" ref="addProjectDyna"/>

    <div>
        <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 战队成员</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="container">
          <div class="handle-box">
                <el-input v-model="user.username" placeholder="用户名称搜索" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
      <el-table
          :data="userList"
          style="width: 100%">
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

    </div>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    data() {
      return {
        project:{}, 
        userList: [],
          total:100,
          user:{'pageIndex':1},   
      }
    },
    created() {
      this.getTeam();
      this.getUserList();
    },

    methods: {
      
      getTeam:function(){
        var params = {id:this.$route.query.id}
        this.$dataPostXD("/team/get", params, (resultMap)=>{
          this.project = resultMap.team;
          var sysKeyValues = resultMap.sysKeyValues;
          this.$refs.addProjectDyna.setKvs(sysKeyValues)
        });
      },
      getUserList:function(){

          this.user['team_id'] = this.$route.query.id;
          this.$dataPostXD("/team/listTeamUser", this.user, (resultMap)=>{
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
        userDetail:function(index, row){
          this.$router.push({
              path: "/userDetail",
            query: {
              id: row.id
            }
          })
        },
        deleteStatusFormatter:function(row, column, cellValue){
          if(row.delete_status == 0 ){
            return '有效';
          }else{
            return '无效';
          } 

        },
    },
    components:{
      dynamicProperties,
      
    },
  }
</script>
<style type="text/css">
  
  .addProjectBtn{
    margin-top: 20px;
  }
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
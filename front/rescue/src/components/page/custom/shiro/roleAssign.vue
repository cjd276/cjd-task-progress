<template>
  <div class="app-container">
    <div class="filter-container">
      <el-transfer v-model="hasRoleIds" :props="{
      key: 'id',
      label: 'name'
    }" :titles="['角色列表', '已分配角色']" filterable :data="allRoles">
      <el-button class="transfer-footer" slot="right-footer" size="small" @click="assign">分配</el-button>
    </el-transfer>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        allRoles:[],
        hasRoleIds:[]
      }
    },
    created() {
      this.getRoleList();
    },
    computed: {

    },
    methods: {
     
      getRoleList:function(){
        var params = {user_id:this.$route.query.id}
          this.$dataPostXD("/role/assignRole", params, (resultMap)=>{
                this.allRoles = resultMap.allRoles;
                this.hasRoleIds = resultMap.hasRoleIds;
            });

      },
      assign:function(){

        var params = {hasRoleIds:this.hasRoleIds,user_id:this.$route.query.id}
          this.$dataPostXD("/role/assign", params);
      }
    },
    components:{

      
    },
  }
</script>
<style type="text/css">
  .transfer-footer {
    margin-left: 20px;
    padding: 6px 5px;
  }
</style>
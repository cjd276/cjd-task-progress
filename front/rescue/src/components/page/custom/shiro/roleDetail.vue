<template>
  <div class="app-container">

   <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 角色</el-breadcrumb-item>
                <el-breadcrumb-item>基本属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form"  label-width="80px">
                    <el-form-item label="角色名称">
                        <el-input v-model="role.name"></el-input>
                    </el-form-item>
                    <el-form-item label="角色编号">
                        <el-input v-model="role.code"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'项目'" ref="addProjectDyna"/>

    <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 角色</el-breadcrumb-item>
                <el-breadcrumb-item>分配菜单</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-tree
                  :data="treeData"
                  node-key="id"
                  
                  ref="tree"
                  show-checkbox
                  default-expand-all
                  :props="tprops"
                 
                  >
                </el-tree>
            </div>
        </div>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    data() {
      return {
        role:{},   
        tprops:{
          label:"menu_name",
          children:"children"
        },
        treeData:[]   
      }
    },
    created() {
      this.getRole();
      this.roleToMenu();
    },

    methods: {
      
      getRole:function(){
        var params = {id:this.$route.query.id}
        this.$dataPostXD("/role/get", params, (resultMap)=>{
          this.role = resultMap.role;
          var sysKeyValues = resultMap.sysKeyValues;
          this.$refs.addProjectDyna.setKvs(sysKeyValues)
        });
      },
      roleToMenu:function(){
        this.$dataPostXD("/menu/roleToMenu", {id:this.$route.query.id},(resultMap)=>{
          
          this.treeData=resultMap.menuTree;
          this.$refs.tree.setCheckedKeys(resultMap.checkedMenuTree);
          
          
        });
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
</style>
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
                <el-form ref="addRoleForm"  label-width="80px"  :model="role" :rules="rules">
                    <el-form-item label="角色名称" prop="name">
                        <el-input v-model="role.name"></el-input>
                    </el-form-item>
                    <el-form-item label="角色编码" prop="code">
                        <el-input v-model="role.code"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>




    <dynamicProperties :title="'战队'" ref="addProjectDyna" :isEdit="1"/>
    <el-button class="addProjectBtn" plain  @click="addRole">添加角色</el-button>
  </div>
</template>
<script>
  import dynamicProperties from '@/components/custom/dynamicProperties.vue'
  export default {
    data() {
      return {

        role:{},
        kvs:[],
        rules: {
              name: [
                { required: true, message: '请输入角色名称', trigger: 'blur' },
              ],
              code: [
                { required: true, message: '请输入角色编码', trigger: 'blur' },
              ]

            }
      }
    },
    created() {

    },

    methods: {
      
      addRole:function(){

        this.$refs["addRoleForm"].validate((valid) => {
              if (valid) {
                var kvs = this.$refs.addProjectDyna.getKvs();
                var params = {role:this.role,kvs:kvs}
                this.$dataPostXD("/role/add", params);

              }

          });

      }
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
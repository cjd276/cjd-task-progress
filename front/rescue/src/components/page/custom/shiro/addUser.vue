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
			<div class="form-box">

				<el-form :model="user" ref="addUserForm" label-width="80px" :rules="rules">
				  <el-form-item label="用户名称" prop="username">
				    <el-input v-model="user.username"></el-input>
				  </el-form-item>
				  <el-form-item label="用户密码" prop="password">
				    <el-input v-model="user.password"></el-input>
				  </el-form-item>
				  <el-form-item label="昵称" prop="nickname">
				    <el-input v-model="user.nickname"></el-input>
				  </el-form-item>
				  <el-form-item label="手机号" prop="mobile">
				    <el-input v-model="user.mobile"></el-input>
				  </el-form-item>
				  <el-form-item label="有效">
				    <el-switch v-model="delete_status"></el-switch>
				  </el-form-item>
				</el-form>

				
			</div>
		</div>

    </div>



	 <dynamicProperties :title="'用户'" ref="addProjectDyna" :isEdit="1"/>


    <el-button class="addProjectBtn" plain  @click="addUser">添加用户</el-button>
  </div>




</template>
<script type="text/javascript">
	
	import dynamicProperties from '@/components/custom/dynamicProperties.vue'
	export default{
		data(){
			return {
				user:{},
				delete_status:false,
				 rules: {
		          username: [
		            { required: true, message: '请输入用户名称', trigger: 'blur' },
		          ],
		          password: [
		            { required: true, message: '请输入用户密码', trigger: 'blur' },
		          ],
		          nickname: [
		            { required: true, message: '请输入昵称', trigger: 'blur' },
		          ],
		          mobile: [
		            { required: true, message: '请输入手机号', trigger: 'blur' },
		          ],

		        }
		      
			}
		},
		created(){
			
		},
		methods:{
			addUser:function(){
				this.$refs["addUserForm"].validate((valid) => {
		          if (valid) {
		            var kvs = this.$refs.addProjectDyna.getKvs();
					if(this.delete_status == false){
						this.user['delete_status'] = 1;
					}else{
						this.user['delete_status'] = 0;
					}


					var params = {user:this.user,kvs:kvs};

					this.$dataPostXD("/user/add", params);

		          }

		        });


				
			}

		},
		components:{
			dynamicProperties,
		}
	}




</script>
<style type="text/css">
	.addProjectBtn{
    margin-top: 20px;
  }
</style>
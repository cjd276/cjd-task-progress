<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.children && item.children.length > 0">
                    <el-submenu :index="item.path" :key="item.path" v-if="hasPermission(item.permission_code)">
                         <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.menu_name }}</span>
                        </template>
                        <template v-for="subItem in item.children">
                            <el-submenu v-if="subItem.children  && subItem.children.length > 0 && hasPermission(subItem.permission_code)" :index="subItem.path" :key="subItem.path">
                                <template slot="title">{{ subItem.menu_name }}</template>
                                <el-menu-item v-for="(threeItem,i) in subItem.children" :key="i" :index="threeItem.path">
                                    <i :class="threeItem.icon"></i><span slot="title">{{ threeItem.menu_name }}</span>
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item v-else :index="subItem.path" :key="subItem.path">
                                    <template v-if="hasPermission(subItem.permission_code)">
                                    <i :class="subItem.icon"></i><span slot="title">{{ subItem.menu_name }}</span>
                                    </template>
                             
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else >
                    <el-menu-item :index="item.path" :key="item.path" v-if="hasPermission(item.permission_code)">
                     
                            <i :class="item.icon"></i><span slot="title">{{ item.menu_name }}</span>
                 
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items:[],
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
            this.items = JSON.parse(localStorage.getItem('menus'));
        },
        methods:{
            hasPermission(permission_code){
                if(!localStorage.getItem('permissions')){
                    return false;
                }
                var permissions = JSON.parse(localStorage.getItem('permissions'));
                if(permissions && permissions.length >0 ){
                    for (var i = permissions.length - 1; i >= 0; i--) {
                        if(permissions[i].permission_code == permission_code){
                            return true;
                        }
                    }

                }
                return false;
                


            }
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 250px;
    }
    .sidebar > ul {
        height:100%;
    }
</style>

import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/icon',
                    component: resolve => require(['../components/page/Icon.vue'], resolve),
                    meta: { title: '自定义图标' }
                },
                {
                    path: '/table',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve),
                    meta: { title: '基础表格' }
                },
                {
                    path: '/tabs',
                    component: resolve => require(['../components/page/Tabs.vue'], resolve),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                    meta: { title: '基本表单' }
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: { title: '富文本编辑器' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: { title: 'markdown编辑器' }    
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve),
                    meta: { title: '文件上传' }   
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 拖拽Dialog组件
                    path: '/dialog',
                    component: resolve => require(['../components/page/DragDialog.vue'], resolve),
                    meta: { title: '拖拽弹框' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: { title: '403' }
                },
                {
                    path: '/projectList',
                    component: resolve => require(['../components/page/custom/projectList.vue'], resolve),
                    meta: { title: '项目列表' }
                },
                {
                    path: '/projectDetail',
                    component: resolve => require(['../components/page/custom/projectDetail.vue'], resolve),
                    meta: { title: '项目详情' }
                },
                {
                    path: '/addProject',
                    component: resolve => require(['../components/page/custom/addProject.vue'], resolve),
                    meta: { title: '添加项目' }
                },
                {
                    path: '/teamList',
                    component: resolve => require(['../components/page/custom/team/teamList.vue'], resolve),
                    meta: { title: '战队列表' }
                },
                {
                    path: '/teamDetail',
                    component: resolve => require(['../components/page/custom/team/teamDetail.vue'], resolve),
                    meta: { title: '战队详情' }
                },
                {
                    path: '/addTeam',
                    component: resolve => require(['../components/page/custom/team/addTeam.vue'], resolve),
                    meta: { title: '添加战队' }
                },
                {
                    path: '/teamUserManage',
                    component: resolve => require(['../components/page/custom/team/teamUserManage.vue'], resolve),
                    meta: { title: '战队成员' }
                },
                {
                    path: '/planList',
                    component: resolve => require(['../components/page/custom/plan/planList.vue'], resolve),
                    meta: { title: '上线计划列表' }
                },
                {
                    path: '/planDetail',
                    component: resolve => require(['../components/page/custom/plan/planDetail.vue'], resolve),
                    meta: { title: '上线计划详情' }
                },
                {
                    path: '/addPlan',
                    component: resolve => require(['../components/page/custom/plan/addPlan.vue'], resolve),
                    meta: { title: '添加上线计划' }
                },
                {
                    path: '/addUser',
                    component: resolve => require(['../components/page/custom/shiro/addUser.vue'], resolve),
                    meta: { title: '添加用户' }
                },
                {
                    path: '/userManage',
                    component: resolve => require(['../components/page/custom/shiro/userList.vue'], resolve),
                    meta: { title: '用户列表' }
                },{
                    name:'userDetail',
                    path: '/userDetail',
                    component: resolve => require(['../components/page/custom/shiro/userDetail.vue'], resolve),
                    meta: { title: '用户详情' }
                },
                {
                    path: '/roleList',
                    component: resolve => require(['../components/page/custom/shiro/roleList.vue'], resolve),
                    meta: { title: '角色列表' }
                },
                {
                    path: '/roleDetail',
                    component: resolve => require(['../components/page/custom/shiro/roleDetail.vue'], resolve),
                    meta: { title: '角色详情' }
                },
                {
                    path: '/addRole',
                    component: resolve => require(['../components/page/custom/shiro/addRole.vue'], resolve),
                    meta: { title: '添加角色' }
                },
                {
                    path: '/roleAssign',
                    component: resolve => require(['../components/page/custom/shiro/roleAssign.vue'], resolve),
                    meta: { title: '分配角色' }
                },
                {
                    path: '/addTask',
                    component: resolve => require(['../components/page/custom/task/addTask.vue'], resolve),
                    meta: { title: '添加任务' }
                },
                {
                    path: '/myTasks',
                    component: resolve => require(['../components/page/custom/task/myTasks.vue'], resolve),
                    meta: { title: '我的任务' }
                },
                {
                    path: '/taskDetail',
                    component: resolve => require(['../components/page/custom/task/taskDetail.vue'], resolve),
                    meta: { title: '任务详情' }
                },
                {
                    path: '/updateTask',
                    component: resolve => require(['../components/page/custom/task/updateTask.vue'], resolve),
                    meta: { title: '更新任务' }
                },
                {
                    path: '/taskUserManage',
                    component: resolve => require(['../components/page/custom/task/taskUserManage.vue'], resolve),
                    meta: { title: '任务成员' }
                },
                {
                    path: '/menuManage',
                    component: resolve => require(['../components/page/custom/shiro/menuList.vue'], resolve),
                    meta: { title: '菜单管理' }
                },
                {
                    path: '/addMenu',
                    component: resolve => require(['../components/page/custom/shiro/addMenu.vue'], resolve),
                    meta: { title: '添加菜单' }
                },
                {
                    path: '/roleToMenu',
                    component: resolve => require(['../components/page/custom/shiro/roleToMenu.vue'], resolve),
                    meta: { title: '角色资源' }
                },
                {
                    path: '/scheduleSetting',
                    component: resolve => require(['../components/page/custom/task/scheduleSetting.vue'], resolve),
                    meta: { title: '任务进度设置' }
                }




            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})

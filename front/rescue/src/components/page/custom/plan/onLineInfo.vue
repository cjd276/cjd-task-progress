<template>
  <div>
    
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>版本信息</span>
      </div>
      <div v-for="(item,index) in projects" :key="index" class="text item">
        {{(index+1) +'、' + item.code + ':'}}
        <template v-for="mitem in modules" v-if="mitem.operate != null ">
          {{item.current_version_prefix + mitem.code + '_' +item.current_version + '_' + onLineDate +'、'}}
        </template>
      </div>
    </el-card>
    <el-card class="box-card mtop20">
      <div slot="header" class="clearfix">
        <span>上线内容</span>
      </div>
      <div v-for="(item,index) in tasks" :key="index" class="text item">
        {{(index+1) +'、' + item.name }}
      </div>
    </el-card>
    <el-card class="box-card mtop20">
      <div slot="header" class="clearfix">
        <span>apollo配置</span>
      </div>
      <div v-for="(item,index) in modules" :key="index" class="text item">
        {{(index+1) +'、' + item.namespace + ':' }}
        <template v-for="(apolloItem,index) in apollos">
          <div class="text item" v-if="apolloItem.module_id==item.id" style="margin-top: 5px">
            <el-tag style="margin-right: 10px" effect="plain" size="mini" >{{apolloItem.operate_type}}</el-tag>{{apolloItem.key_str+ ':' + apolloItem.value_str}}
          </div>
          
        </template>
        
      </div>
    </el-card>
    <el-card class="box-card mtop20">
      <div slot="header" class="clearfix">
        <span>服务监控项</span>
      </div>
      <div v-for="(item,index) in modules" :key="index" class="text item">
        {{(index+1) +'、' + item.service_monitor}}
      </div>
    </el-card>
    <el-card class="box-card mtop20" >
      <div slot="header" class="clearfix">
        <span>上线步骤</span>
      </div>
      <mavon-editor :toolbarsFlag="toolbarsFlag" defaultOpen="edit" :value="handleOperate"   style="min-height: 300px;min-width: 1000px"/>
      <!-- <div v-for="(item,index) in modules" :key="index" class="text item">
  
        {{(index+1) +'、' + item.operate}}
    
      </div> -->
    </el-card>
    <el-card class="box-card mtop20">
      <div slot="header" class="clearfix">
        <span>回退方案</span>
      </div>
      <mavon-editor :toolbarsFlag="toolbarsFlag" defaultOpen="edit" :value="handleOperateReturn"   style="min-height: 300px;min-width: 1000px"/>
      <!-- <div v-for="(item,index) in modules" :key="index" class="text item">
        {{(index+1) +'、' + item.operate_return}}
      </div> -->
    </el-card>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'
  export default {
    data() {
      return {
        tasks:[],
        modules:[],
        projects:[],
        onLineDate:'',
        apollos:[],
        toolbarsFlag:false,

      }
    },
    created() {
      this.getOnLineInfo();
    },
    computed: {
      handleOperate:function(){
        if(null != this.modules && this.modules.length > 0){
          var temp = '';
          for (var i =0;i<this.modules.length; i++) {
            temp += (i+1) + '、' + this.modules[i].operate + '\r\n';

          }
          return temp;
        }
        return '';
      },
      handleOperateReturn:function(){
        if(null != this.modules && this.modules.length > 0){
          var temp = '';
          for (var i =0;i<this.modules.length; i++) {
            temp += (i+1) + '、' + this.modules[i].operate_return + '\r\n';

          }
          return temp;
        }
        return '';
      }
    },
    methods: {

      getOnLineInfo:function(){
        var plan_id = this.$route.query.plan_id;
          this.$dataPostXD("/plan/getOnLineInfo", {id: plan_id}, (resultMap)=>{
                this.tasks = resultMap.tasks;
                this.modules = resultMap.modules;
                this.projects = resultMap.projects;
                this.onLineDate = resultMap.onLineDate;
                this.apollos = resultMap.apollos;
            });

      },
    },
    components:{


      mavonEditor,
    },

  }
</script>
<style type="text/css" scoped>
  
  .mtop20{
    margin-top: 20px;
  }
</style>
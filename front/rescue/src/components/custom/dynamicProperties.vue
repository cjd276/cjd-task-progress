<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> {{title}}</el-breadcrumb-item>
                <el-breadcrumb-item>动态属性</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form  label-width="100px">

                    <el-form-item label="选择类型" v-if="isEdit==1">
                        <el-select v-model="addType" placeholder="请选择">
                            <el-option key="bbk" label="文本" value="text"></el-option>
                            <el-option key="xtc" label="日期" value="date"></el-option>
                            <el-option key="imoo" label="开关" value="switch"></el-option>
                            <el-option key="imoox" label="多文本" value="textArea"></el-option>
                        </el-select>
                        <el-select v-model="kvType" placeholder="属性类型"  class="lef20 right10">
                            <el-option v-for="item in kvTypeList" :key="item.kv_type_name" :label="item.kv_type_name" :value="item"></el-option>
             
                        </el-select>
                        <el-button plain @click="addField()" class="lef20" style="margin-right: 15px;">添加-></el-button>
                     </el-form-item>
                    <template v-for="(item,index) in textField">
                      <el-form-item>
                        <el-input slot="label" v-model="textField[index]['bond']" disabled></el-input>
                        <el-input v-model="item.val" style="width:60%"></el-input>
                        <el-tag type="info" class="tagxz" effect="plain" size="medium">{{item.kv_type_name}}</el-tag>
                        <el-button v-if="isEdit==1" plain  @click="deleteText(index)" class="lef20">删除属性</el-button>
                      </el-form-item>
                    </template>

                    
                   <template v-for="(item,index) in dateField">
                    <el-form-item>
                      <el-input slot="label" v-model="item.bond" disabled></el-input>
                      
                            <el-date-picker type="date" placeholder="选择日期" v-model="item.val" style="width: 60%;"></el-date-picker>
                       
                        <el-tag type="info" class="tagxz" effect="plain" size="medium">{{item.kv_type_name}}</el-tag>
                         <el-button v-if="isEdit==1" plain  @click="deleteDate(index)" class="lef20">删除属性</el-button>
                    </el-form-item>
                  </template>
                  
                    <el-form-item v-for="(item,index) in switchField" :key="index">
                      <el-input slot="label" v-model="item.bond" disabled></el-input>
                      <el-switch v-model="item.val"></el-switch>
                      <el-tag type="info" class="tagxz" effect="plain" size="medium">{{item.kv_type_name}}</el-tag>
                       <el-button v-if="isEdit==1" plain  @click="deleteSwitch(index)" class="lef20">删除属性</el-button>
                    </el-form-item>
                    <template v-for="(item,index) in textAreaField">
                      <el-form-item>
                        <el-input slot="label" v-model="textAreaField[index]['bond']" disabled></el-input>

                        <el-input  type="textarea" v-model="item.val" style="width:60%;float: left;"></el-input>
                        <el-tag type="info" class="tagxz" effect="plain" size="medium" style="float: left;">{{item.kv_type_name}}</el-tag>
                        <el-button v-if="isEdit==1" plain  @click="deleteTextArea(index)" class="lef20">删除属性</el-button>
                        
                      </el-form-item>
                    </template>


                    
                </el-form>
            </div>
        </div>

    </div>
</template>
<script>
  export default {
    name:"dynamicPropertes",
    props:{
      title:{
        type:String,
        default:'对象'
      },
      isEdit:{
        type:Number,
        default:0
      },
    },
    data() {
      return {
        addType:"text",
        textField:[],
        dateField:[],
        switchField:[],
        textAreaField:[],
        kvTypeList:[],
        kvType:{
          kv_type_name:"散列属性"
        }

      }
    },
    created() {
      this.getKvType();
    },

    methods: {
      getKvType:function(){
        this.$dataPost("/kvType/list", {}, (resultMap)=>{
                this.kvTypeList = resultMap.list;
            });
      },
      addField:function(){
        console.log(this.kvType.kv_type_key)
        console.log(this.kvType.kv_type_name)
        if(this.addType == "text"){
          if(!this.textField){
            this.textField = [];
          }
          var textFileTemplate={
            bond:'文本',
            val:'',
            kv_type_name:this.kvType.kv_type_name,
            _id:Math.random()
          };
          this.textField.push(textFileTemplate);
        }
        if(this.addType == "textArea"){
          var textAreaFileTemplate={
            bond:'多行文本',
            val:'',
            kv_type_name:this.kvType.kv_type_name,
            _id:Math.random()
          };
          this.textAreaField.push(textAreaFileTemplate);
        }
        if(this.addType == "date"){

          var  dateFileTemplate={
            bond:'日期',
            val:'',
            kv_type_name:this.kvType.kv_type_name,
            _id:Math.random()
          };
          this.dateField.push(dateFileTemplate);
        }
        if(this.addType == "switch"){
          var switchFileTemplate = {
            bond:'开关',
            val:0,
            kv_type_name:this.kvType.kv_type_name,
            _id:Math.random()
          };
          this.switchField.push(switchFileTemplate);
        }    
      },
      deleteText:function(index){
        this.textField.splice(index,1);
      },
      deleteDate:function(index){
        this.dateField.splice(index,1);
      },
      deleteSwitch:function(index){
        this.switchField.splice(index,1);
      },
      deleteTextArea:function(index){
        this.textAreaField.splice(index,1);
      },
      getKvs:function(){
        var kvs = Object.assign([],this.textField,this.dateField,this.switchField,this.textAreaField);
        return kvs;
      },
      setKvs:function(kvs){
        this.textField = kvs;
      }

    },
    components:{

      
    },
  }
</script>
<style type="text/css">
  .lef20{
     float: right;
  }
  .tagxz{
    margin-left: 10px;
    max-width: 100px;
    overflow: unset;
  }
  .el-tag{
    background-color: rgba(64,158,255,.1) !important;
    height: 32px !important;
    line-height: 30px !important;
    color: #409EFF !important;
  }

</style>
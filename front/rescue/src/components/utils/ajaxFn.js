import axios from 'axios'
import {Message} from 'element-ui' //引入element的消息框，用于post修改请求时的消息提示，可选
import { Loading } from 'element-ui';
import _this from '@/main.js'
axios.defaults.withCredentials=true;



var domain = "http://localhost:9000"; //api域名
var baseParams = { //基础参数
    token: "",
    userid: 0

}; 

function dataGet(apiName, params, callback){
   let loadingInstance = Loading.service({ fullscreen: true ,customClass:'bgnone'});
    var url = domain + apiName;
    var obj = initFn(params, callback, arguments[1]); //init方法实现见后
    var nparams = obj.nparams;
    callback = obj.callback;

    axios.get(url, {params: nparams}).then((response) => {
        loadingInstance.close();
        var all = response.data; //返回所有数据
        var resultMap = response.data.resultMap; //根据后端实际返回修改

        
        //根据后端实际返回修改
        if(all.code=="000000"){ //成功
            Message({message: "请求成功", type: 'success'});
            if(callback) callback(resultMap);
        }else{ //失败
            console.log(all.code, all.msg);
              Message({message: all.code+ all.msg, type: 'error'});
        }

    }).catch((error)=>{
        console.log(error);
        loadingInstance.close(); 
        toLogin(error.response);   
    });
}

function dataPost(apiName, params, callback){
     let loadingInstance = Loading.service({ fullscreen: true ,customClass:'bgnone'});

        var url = domain + apiName;
    var obj = initFn(params, callback, arguments[1]);
    var nparams = obj.nparams;
    callback = obj.callback;

    axios.post(url, nparams).then((response) => {
        loadingInstance.close();
        var all = response.data;
        var resultMap = response.data.resultMap; //根据后端实际返回修改

        
        //根据后端实际返回修改
        if(all.code=="000000"){ //成功
            //Message({message: "请求成功", type: 'success'});
            if(callback) callback(resultMap);
        }else{ //失败
            console.log(all.code, all.msg);
             Message({message: all.code+ all.msg, type: 'error'});
        }
        
    }).catch((error)=>{
        loadingInstance.close();
        console.log(error);
        toLogin(error.response);
    });


    
}


function dataPostXD(apiName, params, callback){
     let loadingInstance = Loading.service({ fullscreen: true ,customClass:'bgnone'});
    var url = domain + apiName;
    var obj = initFn(params, callback, arguments[1]);
    var nparams = obj.nparams;
    callback = obj.callback;

    axios.post(url, nparams).then((response) => {
        loadingInstance.close();
        var all = response.data;
        var resultMap = response.data.resultMap; //根据后端实际返回修改

     
        
        //根据后端实际返回修改
        if(all.code=="000000"){ //成功
            Message({message: '操作成功！', type: 'success'});
            if(callback) callback(resultMap);

        }else{ //失败
            console.log(all.code, all.msg);
           
            Message({message: all.code+ all.msg, type: 'error'});
            
            
        }
        
    }).catch((error)=>{
        loadingInstance.close();
        console.log(error);
        toLogin(error.response);
    });
}

function initFn(params, callback, argument){
    //如果没有参数
    if (typeof argument == "function"){
        callback = argument;
        params = {};
    }
    //对象合并,确定最终参数
    return {
        nparams: extend({}, params, baseParams),
        callback: callback
    } 

}


function extend() {
    var length = arguments.length;
    var target = arguments[0] || {};
    if (typeof target!="object" && typeof target != "function") {
        target = {};
    }
    if (length == 1) {
        target = this;
        i--;
    }
    for (var i = 1; i < length; i++) { 
        var source = arguments[i]; 
        for (var key in source) { 
            // 使用for in会遍历数组所有的可枚举属性，包括原型。
            if (Object.prototype.hasOwnProperty.call(source, key)) { 
                target[key] = source[key]; 
            } 
        } 
    }
    return target; 
}

function toLogin(response){
    if (!response) {
        Message({message:'服务端未启动，找不到服务',type:'error'});
        return;
    }
    if(!response.status || response.status == 401 ){
        _this.  router.push("/login");
        Message({message: '认证/授权 失败/过期', type: 'error'});
        return;
    }
}
export{
    dataGet, dataPost, dataPostXD
}
<template>
  <div class="app-container">
    <div class="filter-container">
      <planChunk :isBtn="1" @chunkClick="chunkClick" :title="'创建计划'"/>
      <template v-for="item in list">
        <planChunk :isBtn="0" :item="item" @chunkClick="chunkClick" @deleteChunk="deleteChunk"/>
      </template>
    </div>
  </div>
</template>
<script>
  import planChunk from '@/components/custom/planChunk.vue'
  export default {
    data() {
      return {
        list:[]
      }
    },
    created() {
      this.getPlanList();
    },
    computed: {

    },
    methods: {
      chunkClick:function(isBtn,item){
        if(isBtn == 1){//添加项目
          this.$router.push("/addPlan")

        }else{//项目详情
          this.$router.push({
          path: "/planDetail",
          query: {
            id: item.id,
            plan_name:item.name
          }
          })
        }
      },
      deleteChunk:function(item){
        this.$dataPostXD("/plan/delete", {id: item.id}, (resultMap)=>{
                 this.getPlanList();
            });


      },
      getPlanList:function(){
          this.$dataPostXD("/plan/list", {levels: 1}, (resultMap)=>{
                this.list = resultMap.planList;
            });

      },
    },
    components:{
      planChunk,
      
    },
  }
</script>

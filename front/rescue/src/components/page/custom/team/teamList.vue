<template>
  <div class="app-container">
    <div class="filter-container">
      <chunk :isBtn="1" @chunkClick="chunkClick" :title="'创建战队'"/>
      <template v-for="item in list">
        <chunk :isBtn="0" :item="item" @chunkClick="chunkClick" @deleteChunk="deleteChunk"/>
      </template>
    </div>
  </div>
</template>
<script>
  import chunk from '@/components/custom/chunk.vue'
  export default {
    data() {
      return {
        list:[]
      }
    },
    created() {
      this.getProjectList();
    },
    computed: {

    },
    methods: {
      chunkClick:function(isBtn,item){
        if(isBtn == 1){//添加项目
          this.$router.push("/addTeam")

        }else{//项目详情
          this.$router.push({
          path: "/teamDetail",
          query: {
            id: item.id
          }
          })
        }
      },
      deleteChunk:function(item){
        this.$dataPostXD("/team/delete", {id: item.id}, (resultMap)=>{
                 this.getProjectList();
            });


      },
      getProjectList:function(){
          this.$dataPostXD("/team/list", {levels: 1}, (resultMap)=>{
                this.list = resultMap.teamList;
            });

      },
    },
    components:{
      chunk,
      
    },
  }
</script>

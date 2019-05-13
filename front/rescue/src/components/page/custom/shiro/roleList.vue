<template>
  <div class="app-container">
    <div class="filter-container">
      <chunk :isBtn="1" @chunkClick="chunkClick" :title="'创建角色'"/>
      <template v-for="item in list">
        <roleChunk :isBtn="0" :item="item" @chunkClick="chunkClick" @deleteChunk="deleteChunk" @roleToMenu="roleToMenu"/>
      </template>
    </div>
  </div>
</template>
<script>
import chunk from '@/components/custom/chunk.vue'
  import roleChunk from '@/components/custom/roleChunk.vue'
  export default {
    data() {
      return {
        list:[]
      }
    },
    created() {
      this.getRoleList();
    },
    computed: {

    },
    methods: {
      chunkClick:function(isBtn,item){
        if(isBtn == 1){//添加项目
          this.$router.push("/addRole")

        }else{//项目详情
          this.$router.push({
          path: "/roleDetail",
          query: {
            id: item.id
          }
          })
        }
      },
      deleteChunk:function(item){
        this.$dataPostXD("/role/delete", {id: item.id}, (resultMap)=>{
                 this.getRoleList();
            });


      },
      getRoleList:function(){
          this.$dataPostXD("/role/list", {levels: 1}, (resultMap)=>{
                this.list = resultMap.roleList;
            });

      },
      roleToMenu:function(item){
        this.$router.push({
          path: "/roleToMenu",
          query: {
            id: item.id
          }
          })
      },
    },
    components:{
      roleChunk,
      chunk
      
    },
  }
</script>

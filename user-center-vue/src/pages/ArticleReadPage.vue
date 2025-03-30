<template>
  <div class="articleReadPage">
    <el-row>
      <el-col :span="6" id="article-intro">
        <el-card id="el-card">
          <template #header>
            <div class="card-header">
              <span style="font-size: 20px">{{ article.title }}</span>
            </div>
          </template>

          <el-avatar shape="square" :size="150"
                     style="border-radius: 20%; border: #282828 double 2px;
                     box-shadow: 8px 8px 16px rgba(0, 0, 0, 0.6);"
                     :src="article.authorAvatar || defaultAvatar"
                     alt="头像"></el-avatar>
          <div style="display: flex; justify-content: center;">
            <div id="info">
              <span class="span" id="name">
                <el-icon><User/></el-icon> &nbsp;作者信息：{{ article.authorName || "作者飞走了" }}
              </span>
              <span class="span" id="date">
                <el-icon><Watch/></el-icon> &nbsp;发布时间：{{ article.createTime?.split(" ")[0] }}
              </span>
              <span class="span" id="view" style="display: flex; align-items: center">
                <el-icon><View/></el-icon> &nbsp;浏览次数：{{ article.viewCount }}次
              </span>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" id="article-show">
        <h1 class="article-title">{{ article.title }}</h1>
        <br>
        <v-md-preview :text=article.content style="text-align: left;"></v-md-preview>
      </el-col>
    </el-row>
  </div>
</template>

<!------------------------------------------------------------------------------>

<script setup lang="ts">

import {onMounted, ref} from "vue";
import {type Article, getArticleById} from "@/request_api/article.ts";
import {useRoute} from "vue-router"
import {ElMessage} from "element-plus";

const route = useRoute();
const article = ref<Article>({
  authorName: "",
  authorAvatar: "",
  createTime: "",
  title: "",
  content: "",
})
const defaultAvatar = 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png';


//路由参数传递的ID
const id = ref<number>(Number(route.params.id) || 0)
onMounted(async () => {
  if (!id.value) {
    ElMessage.error("无效的文章ID");
    return;
  }
  const response = await getArticleById(id.value);
  if (response.data.code === 200 && response.data.data) {
    article.value = response.data.data;
  }
})

</script>

<!------------------------------------------------------------------------------>

<style scoped>

#article-intro #el-card {
  position: fixed; /* 新增：固定定位 */
  height: 100vh; /* 新增：全屏高度 */
  width: 25%;
  overflow-y: auto; /* 新增：允许自身滚动 */
}

#article-intro #el-card #info {
  display: flex;
  flex-direction: column;
  align-items: start;
  padding-top: 40px;
}

#info .span {
  margin-bottom: 30px;
  display: flex;
  align-items: center;
  font-size: 16px;
}

.articleReadPage #article-show {
  padding: 20px 60px;
}

#article-show .article-title {
  font-size: 35px;
  margin-bottom: 10px;
}

</style>

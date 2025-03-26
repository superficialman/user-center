<template>
  <div id="ArticleListPage">
    <div v-for="article in articleList" :key="article.id">
      <el-card style="max-width: 800px">
        <template #header>
          <div class="card-header">
            <span>{{ article.author }}</span>
          </div>
        </template>
        <span>{{ article.title }}</span>
        <el-text truncated>
          {{ article.content }}
        </el-text>
        <template #footer>
          ..........
        </template>
      </el-card>
    </div>
  </div>
</template>

<script setup>

import {ref} from "vue";

const articleList = ref([
  {
    id:1,
    author: "liming",
    title: "生活不止眼前的苟且",
    createTime: "2025-02-01",
    content: "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且"
  },
  {
    id:1,
    author: "liming",
    title: "生活不止眼前的苟且",
    createTime: "2025-02-01",
    content: "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且"
  },
  {
    id:1,
    author: "liming",
    title: "生活不止眼前的苟且",
    createTime: "2025-02-01",
    content: "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且"
  },
  {
    id:1,
    author: "liming",
    title: "生活不止眼前的苟且",
    createTime: "2025-02-01",
    content: "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且"
  },
  {
    id:1,
    author: "liming",
    title: "生活不止眼前的苟且",
    createTime: "2025-02-01",
    content: "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且" +
      "生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且生活不止眼前的苟且"
  },
])

const truncateContent = (content) => {
  return content.length > 50 ? content.substring(0, 50) + '...' : content
}

</script>

<style scoped>

</style>

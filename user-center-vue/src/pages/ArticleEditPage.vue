<template>
  <div id="ArticleEditPage">

    <el-container class="el-container">
      <el-main class="el-main">
        <el-input v-model="article.title" class="title-input" size="large"
                  placeholder="请输入文章标题"
                  clearable/>
      </el-main>
      <el-aside class="el-aside" width="240px">
        <el-button type="danger" size="large" style="margin-left: 25px" @click="saveDraft()">保存草稿
        </el-button>
        <el-button type="success" size="large" style="margin-left: 22px" @click="doPublish()">发布文章
        </el-button>
      </el-aside>
    </el-container>
    <v-md-editor v-model="article.content"
                 :include-level="[1,2,3,4,5,6]"
                 :disabled-menus="[]"
                 :default-show-toc="true"
                 height="72vh">
    </v-md-editor>
  </div>

</template>

<!------------------------------------------------------------------------------>

<script setup lang="ts">
import {ref} from "vue";
import {useLoginUserStore} from "@/stores/useLoginUserStore.ts";
import {storeToRefs} from "pinia";
import {type Article, createArticle, Status} from "@/request_api/article.ts";
import {ElMessage, ElMessageBox, ElNotification} from "element-plus";

const loginUserStore = useLoginUserStore();
const {loginUser} = storeToRefs(loginUserStore);

const article = ref<Article>({
  title: "",
  content: "",
  status: 0,
  authorId: loginUser.value.id
})

const markdown = "# haha"

//内容验证
const validateArticle = () => {
  if (!article.value.title.trim()) {
    ElMessage.error('请输入文章标题');
    return false;
  }
  if (!article.value.content.trim()) {
    ElMessage.error('您的文章内容为空');
    return false;
  }
  return true;
};

//保存草稿或发布提交逻辑
const submitArticle = async (status: number, successMessage: string) => {
  if (!validateArticle()) return;
  try {
    await ElMessageBox.confirm(
      status === Status.DRAFT ? '确定将此文章保存为草稿吗?' : '确定要发布此文章吗?',
      '提示',
      {confirmButtonText: '我确定', cancelButtonText: '再想想', type: 'warning'}
    );
    article.value.status = status;
    const response = await createArticle(article.value);
    if (response.data.code === 200) {
      ElNotification({title: '通知', message: successMessage, type: 'success'});
    } else {
      ElMessage.error("请求失败");
    }
  } catch {
    ElMessage.info(`操作已取消`);
  }
};

/**
 * 保存草稿和发布
 */
const saveDraft = () => submitArticle(Status.DRAFT, '草稿已保存成功！🎉🎉🎉');
const doPublish = () => submitArticle(Status.REVIEW, '文章已发布，正在审核中！🎉🎉🎉');
</script>

<!------------------------------------------------------------------------------>

<style scoped>
#ArticleEditPage {
  text-align: left;
  margin: auto 20px;
}

.el-container {
  margin: 10px auto;
}

.el-container .el-main {
  padding: 0
}

.el-main .title-input {
  transition: all 0.3s ease;
}

.el-main .title-input:focus {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.el-container .el-aside {
  display: flex;
  align-items: center;
}


</style>

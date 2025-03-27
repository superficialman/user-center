<template>
  <div id="GlobalHeader">
    <el-container>
      <!-- 顶部栏左侧Logo -->
      <el-aside width="200px" class="el-aside">
        <img src="../assets/logo.ico" alt="MyLogo" style="height: 36px;"/>
      </el-aside>

      <!-- 顶部栏中间导航菜单 -->
      <el-main class="el-main">
        <el-menu router :default-active="route.path" class="el-menu" mode="horizontal">
          <el-menu-item index="/">
            <el-icon><HomeFilled/></el-icon>首页
          </el-menu-item>
          <el-menu-item index="/article-list">
            <el-icon><List /></el-icon>文章
          </el-menu-item>
          <!--仅管理员可见：管理中心-->
          <el-sub-menu v-if="loginUser.userRole === 1" index="manage">
            <template #title><el-icon><Tools/></el-icon>管理中心</template>
            <el-menu-item index="/user-manage">
              <el-icon><UserFilled/></el-icon>用户管理
            </el-menu-item>
            <el-menu-item index="/article-manage">
              <el-icon><Menu/></el-icon>文章管理
            </el-menu-item>
          </el-sub-menu>
          <div v-else></div>

        </el-menu>
      </el-main>

      <!-- 顶部栏右侧登录用户头像和昵称,未登录展示"登录"和"注册"按钮-->
      <el-aside width="300px" class="el-aside">
        <div v-if="loginUser.id" class="currentUser">
          <LoginUserInfo />
        </div>
        <div v-else class="currentUser">
          <el-button type="primary" @click="toLogin">登&nbsp;&nbsp;录</el-button>
          <el-button type="primary" @click="toRegister">注&nbsp;&nbsp;册</el-button>
        </div>
      </el-aside>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from 'vue-router';
import {useLoginUserStore} from '@/stores/useLoginUserStore.ts';
import {storeToRefs} from "pinia";
import LoginUserInfo from "@/components/LoginUserInfo.vue";

const route = useRoute();
const router = useRouter();
const loginUserStore = useLoginUserStore();
const {loginUser} = storeToRefs(loginUserStore); // 保持响应性

// 跳转到登陆页面
function toLogin() {
  router.push({path: '/login'});
}

// 跳转到注册页面
function toRegister() {
  router.push({path: '/register'});
}

</script>

<style scoped>
#GlobalHeader .el-aside {
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: center;
}

#GlobalHeader .el-main {
  padding: 0 20px;
}

#GlobalHeader .el-main .el-menu {
  background-color: snow;
}
</style>

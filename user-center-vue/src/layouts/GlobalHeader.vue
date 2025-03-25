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
            <el-icon>
              <HomeFilled/>
            </el-icon>
            首页
          </el-menu-item>
          <el-menu-item index="/manage">
            <el-icon>
              <Tools/>
            </el-icon>
            用户管理
          </el-menu-item>
        </el-menu>
      </el-main>

      <!-- 顶部栏右侧登录用户头像和昵称 -->
      <el-aside width="300px" class="el-aside">
<!--        <div v-if="loading" class="skeleton">-->
<!--          &lt;!&ndash; 骨架屏内容 &ndash;&gt;-->
<!--          <el-skeleton :rows="1" animated/>-->
<!--        </div>-->
<!--        <div v-else>-->
          <div v-if="loginUser.id" class="currentUser">
            <el-avatar shape="circle" id="el-avatar" :src="loginUser.avatar || defaultAvatar"
                       style="height: 40px; width: 40px; border-radius: 50%;"
                       alt="我的头像"></el-avatar>
            <h1 style="margin-left: 20px; margin-right: 5px;">{{
                loginUser.username || "无名小可爱"
              }}</h1>

            <el-popover :width="300" trigger="click"
                        popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px; background-color: #ebf2ff">
              <template #reference>
                <el-button link>
                  <el-icon>
                    <ArrowDownBold/>
                  </el-icon>
                </el-button>
              </template>
              <template #default>
                <div style="display: flex; flex-direction: column; align-items: center;">

                  <el-image :src="loginUser.avatar || defaultAvatar" preview-teleported
                            :preview-src-list="[loginUser.avatar || defaultAvatar]" fit="cover"
                            style="height: 80px; width: 80px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); border-radius: 5px; cursor: pointer;">
                  </el-image>


                  <h2 style="font-size: 18px; font-weight: bold; margin-top: 15px; margin-bottom: 80px;
                  text-shadow:2px 2px 0 #ccc; ">{{ loginUser.username || "无名小可爱" }}</h2>
                  <el-button link type="warning" @click="toLogout"
                             style="font-size:medium; margin-bottom: 20px;">
                    <el-icon>
                      <SwitchButton/>
                    </el-icon>&nbsp;退出登录
                  </el-button>
                </div>
              </template>
            </el-popover>

          </div>
          <div v-else class="currentUser">
            <el-button type="primary" @click="toLogin">登&nbsp;&nbsp;录</el-button>
            <el-button type="primary" @click="toRegister">注&nbsp;&nbsp;册</el-button>
          </div>
<!--        </div>-->
      </el-aside>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from 'vue-router';
import {useLoginUserStore} from '@/stores/useLoginUserStore';
import {userLogout} from '@/api/user';
import {ElMessage} from 'element-plus';
import {ArrowDownBold, HomeFilled, SwitchButton, Tools} from "@element-plus/icons-vue";
import {storeToRefs} from "pinia";

const route = useRoute();
const router = useRouter();
const loginUserStore = useLoginUserStore();
const {loginUser} = storeToRefs(loginUserStore); // 保持响应性
const defaultAvatar = 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png';

function toLogin() {
  // 跳转到登陆页面
  router.push({path: '/login'});
}

function toRegister() {
  // 跳转到注册页面
  router.push({path: '/register'});
}

// const loading = ref(true); // 加载状态
//
// onMounted(async () => {
//   await loginUserStore.fetchLoginUser(); // 获取用户数据
//   loading.value = false; // 数据加载完成
// });

const toLogout = async () => {
  // 退出登录
  const response = await userLogout();
  if (response.data.code === 200) {
    loginUserStore.clearLoginUser(); // 删除用户数据
    await router.push({path: '/'});  // 跳转到首页
    ElMessage.success("用户已退出!");
  }
}

</script>

<style scoped>
#GlobalHeader .el-aside {
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: center;
}

#GlobalHeader .el-aside .currentUser {
  display: flex;
  justify-content: center;
  align-items: center;
}

#GlobalHeader .el-main {
  padding: 0 20px;
}

#GlobalHeader .el-main .el-menu {
  background-color: snow;
}
</style>

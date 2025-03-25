<template>
  <div id="UserManagePage">

    <!-- 骨架屏：数据加载中显示 -->
    <el-skeleton v-if="loading" :rows="10" animated/>
    <div v-else>
      <div
        style="min-width: 100vw; display: flex; justify-content: left; padding-left: 10px; margin: 10px auto; background-color:white;">
        <el-input v-model="searchInput" class="animated-input" size="large"
                  style=" width: 300px; border-radius: 20px" placeholder="请输入关键词"
                  :prefix-icon="Search" clearable
                  @keyup.enter="handleSearch"/>
        <el-button type="primary" size="large" style=" margin-left: 5px;" @click="handleSearch">搜&nbsp;索
        </el-button>
      </div>

      <el-table :data="tableData" style="font-size:14px; font-family: 'Verdana',system-ui" stripe>
        <!-- 用户名 -->
        <el-table-column fixed prop="username" width="150">
          <template #header>
            <el-icon>
              <User/>
            </el-icon>
            <span>用户名</span>
          </template>
        </el-table-column>

        <!-- 账号 -->
        <el-table-column prop="account" width="160">
          <template #header>
            <el-icon>
              <Key/>
            </el-icon>
            <span>账号</span>
          </template>
        </el-table-column>

        <!-- 头像 -->
        <el-table-column prop="avatar" width="120">
          <template #header>
            <el-icon>
              <Picture/>
            </el-icon>
            <span>头像</span>
          </template>
          <template #default="scope">
            <el-image :src="scope.row.avatar || defaultAvatar" preview-teleported
                      :preview-src-list="[scope.row.avatar || defaultAvatar]" fit="cover"
                      style="display: flex; height: 60px; width: 60px; border-radius: 5px; cursor: pointer;">
            </el-image>
          </template>
        </el-table-column>

        <!-- 性别 -->
        <el-table-column prop="gender" width="120">
          <template #header>
            <el-icon>
              <Star/>
            </el-icon>
            <span>性别</span>
          </template>
          <template #default="scope">
            <el-tag v-if="scope.row.gender === 1" type="primary" effect="dark">&nbsp;男&nbsp;
            </el-tag>
            <el-tag v-else-if="scope.row.gender === 0" type="danger" effect="dark">&nbsp;女&nbsp;
            </el-tag>
          </template>
        </el-table-column>

        <!-- 电话 -->
        <el-table-column prop="phone" width="150">
          <template #header>
            <el-icon>
              <Phone/>
            </el-icon>
            <span>电话</span>
          </template>
        </el-table-column>

        <!-- 邮箱 -->
        <el-table-column prop="email" width="180">
          <template #header>
            <el-icon>
              <Message/>
            </el-icon>
            <span>邮箱</span>
          </template>
        </el-table-column>

        <!-- 用户角色 -->
        <el-table-column prop="userRole" width="150">
          <template #header>
            <el-icon>
              <Medal/>
            </el-icon>
            <span>用户角色</span>
          </template>
          <template #default="scope">
            <el-tag v-if="scope.row.userRole === 1" type="danger">&nbsp;管理员&nbsp;</el-tag>
            <el-tag v-else type="success">普通用户</el-tag>
          </template>
        </el-table-column>

        <!-- 加入时间 -->
        <el-table-column prop="createTime" width="220">
          <template #header>
            <el-icon>
              <Calendar/>
            </el-icon>
            <span>加入时间</span>
          </template>
        </el-table-column>

        <!-- 账户状态 -->
        <el-table-column prop="status" width="150">
          <template #header>
            <el-icon>
              <Warning/>
            </el-icon>
            <span>账户状态</span>
          </template>
          <template #default="scope">
            <el-tag v-if="scope.row.status === 0" type="primary" effect="dark">&nbsp;正常&nbsp;
            </el-tag>
            <el-tag v-else type="danger" effect="dark">&nbsp;异常&nbsp;</el-tag>
          </template>
        </el-table-column>

        <!-- 操作 -->
        <el-table-column fixed="right" label="操作" min-width="120">
          <template #header>
            <el-icon>
              <Setting/>
            </el-icon>
            <span>操作</span>
          </template>
          <template #default="scope">
            <el-button type="primary" circle>
              <el-icon>
                <Edit/>
              </el-icon>
            </el-button>
            <el-button type="danger" circle @click="doDelete(scope.row.id)">
              <el-icon>
                <Delete/>
              </el-icon>
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>
  </div>
</template>

<script setup lang="ts">

import {getAllUser, userDelete, userSelect} from '@/api/user';
import {ref, onMounted} from "vue"
import {ElMessage, ElMessageBox} from 'element-plus'
import router from '@/router';
import {useLoginUserStore} from '@/stores/useLoginUserStore';
import {Search} from '@element-plus/icons-vue'
import {storeToRefs} from "pinia";

const tableData = ref([]);
const defaultAvatar = 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png';
const searchInput = ref("");

const loading = ref(true); // 骨架屏 loading状态

const loginUserStore = useLoginUserStore();
// 获取用户数据
const fetchData = async () => {
  const {loginUser} = storeToRefs(loginUserStore) ;
  if (!loginUser.value || loginUser.value.userRole !== 1) { //未登录或不是管理员
    await router.push("/");
    loading.value = false;
    ElMessage.error("对不起，您没有权限查看！");
    return; //直接返回，不执行后续逻辑
  }
  loading.value = true; // 开始数据加载
  const response = await getAllUser();
  if (response.data.code === 200 && response.data.data) {
    tableData.value = response.data.data;
  } else {
    await router.push("/");
    ElMessage.error("获取数据失败");
  }
  loading.value = false; // 数据加载完成
};

onMounted(async () => {
  // await loginUserStore.fetchLoginUser(); // 重新获取用户信息
  await fetchData(); // 组件挂载时获取数据
});

const handleSearch = async () => {
  loading.value = true; // 开始数据加载
  const response = await userSelect(searchInput.value);
  if (response.data.code === 200 && response.data.data) {
    tableData.value = response.data.data;
  } else {
    ElMessage.error("获取数据失败");
  }
  loading.value = false; // 数据加载完成
};


// 删除数据
const doDelete = async (id: any) => {
  try {
    await ElMessageBox.confirm(
      '该用户所有信息将被彻底删除，确定要继续吗?',
      '提示',
      {
        confirmButtonText: '我确定',
        cancelButtonText: '再想想',
        type: 'warning',
      }
    );
    const response = await userDelete(id);
    if (response.data.code === 200 && response.data.data) {
      ElMessage.success("您已成功删除该用户");
      await fetchData(); // 删除成功后重新获取数据
    } else {
      ElMessage.error(response.data.description || "删除失败");
    }
  } catch {
    ElMessage.info("已取消删除！");
  }
}


</script>

<style scoped>
.el-table .el-table__header .el-icon {
  margin-right: 8px;
  /* 调整图标和文本之间的间距 */
}

.animated-input {
  transition: all 0.3s ease;
}

.animated-input:focus {
  width: 300px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}
</style>

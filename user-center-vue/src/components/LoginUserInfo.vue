<template xmlns="http://www.w3.org/1999/html">
  <div id="LoginUserInfo">

    <!--头像和用户名展示-->
    <el-avatar id="el-avatar" shape="circle" :src="loginUser.avatar || defaultAvatar"
               alt="头像"></el-avatar>
    <span style="margin:0 20px 0 5px">{{ loginUser.username || "无名小可爱" }}</span>

    <!--弹出框-->
    <el-popover :width="300" trigger="click"
                popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px; background-color: #ebf2ff">
      <template #reference>
        <el-button link><el-icon><ArrowDownBold/></el-icon></el-button>
      </template>
      <template #default>
        <div id="el-popover-content">
          <el-image id="avatar" :src="loginUser.avatar || defaultAvatar" fit="cover"></el-image>
          <span id="name">{{ loginUser.username || "无名小可爱" }}</span>
          <el-button class="link-button" link type="success">
            <el-icon><Setting/></el-icon>&nbsp;我的资料
          </el-button>
          <el-button class="link-button" id="last-link-button" link @click="doLogout">
            <el-icon><SwitchButton/></el-icon>&nbsp;退出登录
          </el-button>

        </div>
      </template>
    </el-popover>
  </div>

</template>

<!------------------------------------------------------------------------------>

<script setup lang="ts">
import {useRouter} from 'vue-router';
import {useLoginUserStore} from '@/stores/useLoginUserStore';
import {userDelete, userLogout} from '@/api/user';
import {ElMessage, ElMessageBox} from 'element-plus';
import {storeToRefs} from "pinia";
import {Setting} from "@element-plus/icons-vue";

const router = useRouter();
const loginUserStore = useLoginUserStore();
const {loginUser} = storeToRefs(loginUserStore); // 保持响应性
const defaultAvatar = 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png';


const doLogout = async () => {


  try {
    await ElMessageBox.confirm(
      '您确定要退出吗😉😉😉?',
      '提示',
      {
        confirmButtonText: '我确定',
        cancelButtonText: '再想想',
        type: 'warning',
      }
    );
    // 退出登录
    const response = await userLogout();
    if (response.data.code === 200) {
      await loginUserStore.fetchLoginUser();
      ElMessage.success("用户已退出!");
      router.replace("/"); // 阻止回退到已退出页面
    } else {
      ElMessage.error("请求失败");
    }
  } catch {
    ElMessage.info("已取消退出！");
  }




}

</script>

<style scoped>

#LoginUserInfo {
  display: flex;
  justify-content: center;
  align-items: center;
}

#LoginUserInfo #el-avatar {
  height: 40px;
  width: 40px;
  border-radius: 50%;
}

#el-popover-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#el-popover-content #avatar {
  height: 80px;
  width: 80px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  cursor: pointer;
}

#el-popover-content #name {
  font-size: 18px;
  font-weight: bold;
  margin-top: 15px;
  margin-bottom: 80px;
  text-shadow: 2px 2px 0 #ccc;
}

#el-popover-content .link-button {
  font-size: medium;
  margin-bottom: 20px;
}

#el-popover-content #last-link-button {
  margin-left: 0;
}


</style>

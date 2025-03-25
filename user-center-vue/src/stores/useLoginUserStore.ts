import {ref} from 'vue'
import {defineStore} from 'pinia'
import {getCurrentUser} from '@/api/user';

/**
 * Vue 全局状态管理库 =>当前登录用户
 */


export const useLoginUserStore
  = defineStore('loginUser', () => {
  const loginUser = ref<any>({});

  //更新登录态，调用/current接口
  const fetchLoginUser = async () => {
    const response = await getCurrentUser()
    if (response.data.data) {
      loginUser.value = response.data.data;
    }
  }

  //手动更新，传入一个对象
  const setLoginUser = (newLoginUser: any) => {
    loginUser.value = newLoginUser;
  }

  //退出后，清空数据
  const clearLoginUser = () => {
    loginUser.value = {};
  }

  return {loginUser, fetchLoginUser, setLoginUser, clearLoginUser}

}, {
  persist: true, // 启用持久化
})

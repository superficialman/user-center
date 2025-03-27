import {ref} from 'vue'
import {defineStore} from 'pinia'
import {getCurrentUser} from '@/api/user';

/**
 * Vue 全局状态管理库 =>当前登录用户
 */

interface User {
  id: number;
  username: string;
  account: string;
  userRole: number;
  avatar: string;
  gender: number;
  phone: string;
  email: string;
  createTime: string;
  status: number;
}

const defaultUser: User = {
  id: 0,
  username: "",
  account: "",
  avatar: "",
  createTime: "",
  email: "",
  gender: 0,
  phone: "",
  status: 0,
  userRole: 0
}


export const useLoginUserStore
  = defineStore('loginUser', () => {
    const loginUser = ref<User>({...defaultUser});

    //更新登录态，调用/current接口
    const fetchLoginUser = async () => {
      const response = await getCurrentUser()
      loginUser.value = response.data.data ? {...response.data.data} : {...defaultUser};
    }

    // //手动更新，传入一个对象
    // const setLoginUser = (newLoginUser: User) => {
    //   loginUser.value = {...newLoginUser};
    //   localStorage.setItem("loginUser", JSON.stringify(loginUser.value));
    // }


    return {loginUser, fetchLoginUser}

  }, {
    persist: true
  }
)

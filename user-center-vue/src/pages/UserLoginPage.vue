<template>
  <div id="UserLoginPage">
    <el-card class="el-card">
      <template #header>
        <img src="../assets/logo.ico" alt="MyLogo" style="height: 40px;"/>
        <span style="display: block; margin-top: 10px">用&nbsp;户&nbsp;登&nbsp;录</span>
      </template>

      <el-form ref="ruleFormRef" style="max-width: 600px" label-width="auto" :model="formData"
               :rules="rules"
               status-icon hide-required-asterisk
               @keyup.enter="submitForm(ruleFormRef)" class="demo-ruleForm">
        <el-form-item label="账&nbsp;号&nbsp;&nbsp;" prop="account">
          <el-input v-model="formData.account" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="密&nbsp;码&nbsp;&nbsp;" prop="password">
          <el-input v-model="formData.password" type="password" placeholder="请输入密码"
                    show-password/>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button type="primary" style="margin-bottom: 10px" @click="submitForm(ruleFormRef)">登&nbsp;录</el-button>
        <RouterLink to="/register" style="display: block; font-size: small">没有账号，去注册！
        </RouterLink>
      </template>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import {RouterLink} from 'vue-router';
import {ref} from 'vue'
import type {FormRules, FormInstance} from 'element-plus'
import {userLogin} from '@/api/user.ts';
import {ElMessage} from 'element-plus';
import {useRouter} from 'vue-router';
import {useLoginUserStore} from '@/stores/useLoginUserStore.ts';
import {storeToRefs} from "pinia";

const loginUserStore = useLoginUserStore();
const {loginUser} = storeToRefs(loginUserStore);
const router = useRouter();
const ruleFormRef = ref<FormInstance>()
const formData = ref({
  account: "",
  password: ""
})

const rules = ref<FormRules>({
  account: [
    {required: true, message: '请输入账号', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log("登录验证通过")
      login();
    } else {
      console.log("登录验证失败")
    }
  })
}

const loading = ref(true);

const login = async () => {
  const response = await userLogin(formData.value);
  if (response.data.code === 200 && response.data.data) {
    await loginUserStore.fetchLoginUser();
    ElMessage.success("欢迎您，" + (response.data.data.username || "小可爱") + "！😁😁😁");
    router.replace("/")
  } else if (response.data.code === 40000) {
    ElMessage.error("账号或密码输入有误！😕😕😕");
  } else {
    ElMessage.error("登录失败！😔😔😔");
  }
  loading.value = false;
};

</script>


<style>
#UserLoginPage {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 80px;
}

#UserLoginPage .el-card {
  max-width: 600px;
  padding: 0 64px;
  background-color: azure;
}
</style>

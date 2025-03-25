<template>
  <div id="UserRegisterPage">
    <el-card style="max-width: 600px; padding: 0 64px; background-color:azure;">
      <template #header>
        <div style="margin-bottom: 5px;">
          <img src="../assets/logo.ico" alt="MyLogo" style="height: 40px;" />
        </div>
        <div class="card-header">
          <span>用&nbsp;户&nbsp;注&nbsp;册</span>
        </div>
      </template>

      <el-form ref="ruleFormRef" style="max-width: 600px" :model="formData" status-icon label-width="auto"
        :rules="rules" class="demo-ruleForm">
        <el-form-item label="账&nbsp;号&nbsp;&nbsp;" prop="account">
          <el-input v-model="formData.account" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="密&nbsp;码&nbsp;&nbsp;" prop="password">
          <el-input v-model="formData.password" type="password" autocomplete="off" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item label="校&nbsp;验&nbsp;&nbsp;" prop="checkPassword">
          <el-input v-model="formData.checkPassword" type="password" autocomplete="off" placeholder="请再次输入密码" show-password />
        </el-form-item>
      </el-form>

      <template #footer>
        <div style="margin-bottom: 10px;">
          <el-button type="primary" @click="submitForm(ruleFormRef)">注&nbsp;册</el-button>
        </div>
        <div>
          <RouterLink to="/login" style="font-size: small">已有账号，去登录！</RouterLink>
        </div>
      </template>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router';
import { ref } from 'vue'
import type { FormRules, FormInstance } from 'element-plus'
import { userRegister } from '@/api/user';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter();

const ruleFormRef = ref<FormInstance>()

const formData = ref({
  account: '',
  password: '',
  checkPassword: ''
});

const validateConfirmPassword = (rule: any, value: string, callback: any) => {
  if (value !== formData.value.password) {
    callback(new Error('两次输入的密码不一致'));
  } else {
    callback();
  }
}

const rules = ref<FormRules>({
  account: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 4, max: 20, message: '账号长度为4-20个字符', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9_]+$/, message: '账号只能由字母、数字、下划线组成', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 8, max: 20, message: '密码长度为8-20个字符', trigger: 'blur' },
  ],
  checkPassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { min: 8, max: 20, message: '密码长度为8-20个字符', trigger: 'blur' },
    { validator: validateConfirmPassword, trigger: 'blur' },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      register();
    } else {
      console.log('error submit!', fields)
    }
  })
}

const register = async () => {
  const response = await userRegister(formData.value);
  if (response.data.code === 200 && response.data.data) {
    await router.replace({path: '/login'});
    ElMessage.success("新用户注册成功！😁😁😁");
  } else if (response.data.code === 40000 && response.data.description === "账号已存在") {
    ElMessage.error("该账号已存在，换一个吧！😓😓😓");
  } else {
    ElMessage.error("注册失败！😔😔😔");
  }
};

</script>

<style scoped>
#UserRegisterPage {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 80px;
}
</style>

import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/components/HomePage.vue'
import UserManagePage from '@/components/UserManagePage.vue'
import UserLoginPage from '@/components/UserLoginPage.vue'
import UserRegisterPage from '@/components/UserRegisterPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '', component: HomePage },
    { path: '/manage', component: UserManagePage },
    { path: '/login', component: UserLoginPage },
    { path: '/register', component: UserRegisterPage },
  ],
})


export default router

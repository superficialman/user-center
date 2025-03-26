import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/pages/HomePage.vue'
import UserManagePage from '@/pages/UserManagePage.vue'
import UserLoginPage from '@/pages/UserLoginPage.vue'
import UserRegisterPage from '@/pages/UserRegisterPage.vue'
import ArticleListPage from "@/pages/ArticleListPage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '', component: HomePage },
    { path: '/user-manage', component: UserManagePage },
    { path: '/login', component: UserLoginPage },
    { path: '/register', component: UserRegisterPage },
    { path: '/setting', component: HomePage },
    { path: '/article-list', component: ArticleListPage },
    // { path: 'article-manage', component: ArticleManagePage },
  ],
})


export default router

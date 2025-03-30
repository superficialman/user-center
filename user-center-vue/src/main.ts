import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
const pinia = createPinia()
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
pinia.use(piniaPluginPersistedstate)

import App from './App.vue'
import router from './router'

const app = createApp(App)

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

import { VMdEditor, VMdPreview } from './markdown'; // 引入 markdown 编辑器和预览器

app.use(pinia).use(ElementPlus).use(router).use(VMdEditor).use(VMdPreview).mount('#app')

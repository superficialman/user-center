<template>
  <div class="app">
    <div v-if="loading">
      <template>
        <el-skeleton :rows="5" animated />
      </template>
    </div>
    <AppLayout v-else />
  </div>
</template>

<script setup lang="ts">
import AppLayout from './layouts/AppLayout.vue';
import {useLoginUserStore} from "@/stores/useLoginUserStore.ts";
import {onMounted, ref} from "vue";

const loginUserStore = useLoginUserStore();
const loading = ref(true);
onMounted(async () => {
    await loginUserStore.fetchLoginUser();
    loading.value = false;
});

</script>

<style scoped></style>

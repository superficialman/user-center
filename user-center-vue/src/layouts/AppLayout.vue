<template>
  <div id="AppLayout">
    <el-container>
      <el-header class="header">
        <GlobalHeader></GlobalHeader>
      </el-header>

      <el-main class="main">
        <RouterView />
      </el-main>

      <el-footer class="footer">
        <a href="https://bytebreeze.xyz" target="_blank">© 2025 ByteBreeze. 保留所有权利.</a>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import GlobalHeader from './GlobalHeader.vue';

</script>

<style scoped>
#AppLayout .header {
  background-color:snow;
  position: fixed;
  height: auto;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

#AppLayout .main {
  text-align: center;
  background: white;
  padding: 64px 0;
  min-height: 100vh;
}

#AppLayout .footer {
  background-color: whitesmoke;
  text-align: center;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
  z-index: 1000;
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.1);
}
</style>

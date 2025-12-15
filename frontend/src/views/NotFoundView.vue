<script setup>
import { RouterLink } from 'vue-router'
import { Home } from 'lucide-vue-next'
</script>

<template>
  <div class="min-h-[60vh] flex flex-col items-center justify-center text-center p-4">
    <div class="text-9xl font-bold text-gray-200 mb-4">404</div>
    <h1 class="text-2xl font-bold text-gray-800 mb-2">页面未找到</h1>
    <p class="text-gray-500 mb-8">抱歉，你访问的页面不存在或已被移除。</p>
    <RouterLink to="/" class="flex items-center px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">
      <Home class="w-5 h-5 mr-2" />
      返回首页
    </RouterLink>
  </div>
</template>

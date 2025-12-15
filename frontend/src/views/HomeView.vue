<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { ArrowRight, Calendar, Clock } from 'lucide-vue-next'

// 响应式数据
const latestPosts = ref([])
const photos = ref([])

// 模拟数据作为兜底
const mockPosts = [
  {
    id: 1,
    title: 'Spring Boot 3.0 新特性解析',
    date: '2023-12-10',
    readTime: '5 min',
    cover: 'https://picsum.photos/seed/blog1/800/400'
  },
  {
    id: 2,
    title: 'Vue 3 组合式 API 最佳实践',
    date: '2023-12-08',
    readTime: '8 min',
    cover: 'https://picsum.photos/seed/blog2/800/400'
  }
]

const mockPhotos = Array.from({ length: 4 }).map((_, i) => ({
  id: i,
  url: `https://picsum.photos/seed/${i + 20}/400/400`
}))

// 获取数据
onMounted(async () => {
  try {
    // 获取文章
    const postsRes = await fetch('/api/posts')
    if (postsRes.ok) {
      const data = await postsRes.json()
      // 取前2篇
      latestPosts.value = data.slice(0, 2).map(post => ({
        ...post,
        date: new Date(post.createdAt || Date.now()).toLocaleDateString(),
        cover: post.coverUrl || 'https://picsum.photos/seed/blog/800/400'
      }))
    } else {
      latestPosts.value = mockPosts
    }

    // 获取照片
    const photosRes = await fetch('/api/photos')
    if (photosRes.ok) {
      const data = await photosRes.json()
      photos.value = data.slice(0, 4)
    } else {
      photos.value = mockPhotos
    }
  } catch (e) {
    console.error('Failed to fetch data:', e)
    latestPosts.value = mockPosts
    photos.value = mockPhotos
  }
})
</script>

<template>
  <div class="space-y-6">
    <!-- 顶部欢迎区域 -->
    <div class="bg-gradient-to-r from-blue-600 to-indigo-600 rounded-2xl p-8 md:p-10 text-white shadow-lg relative overflow-hidden group">
      <div class="relative z-10">
        <h1 class="text-3xl md:text-4xl font-bold mb-4">欢迎来到ZXH的空间 👋</h1>
        <p class="text-blue-100 max-w-xl text-lg leading-relaxed mb-8">
          这里记录了ZXH的学习笔记、摄影作品和一些实用的小工具。
          <br>希望你能在这里找到有趣的内容，共同交流进步。
        </p>
        <div class="flex flex-wrap gap-4">
          <RouterLink to="/blog" class="bg-white text-blue-600 px-6 py-2.5 rounded-lg font-medium hover:bg-blue-50 transition shadow-sm">
            浏览博客
          </RouterLink>
          <RouterLink to="/about" class="bg-blue-700/50 text-white px-6 py-2.5 rounded-lg font-medium hover:bg-blue-700/70 transition backdrop-blur-sm">
            了解更多
          </RouterLink>
        </div>
      </div>
      
      <!-- 装饰背景 -->
      <div class="absolute top-0 right-0 -mt-10 -mr-10 w-64 h-64 bg-white opacity-10 rounded-full blur-3xl group-hover:scale-110 transition duration-1000"></div>
      <div class="absolute bottom-0 left-0 -mb-10 -ml-10 w-40 h-40 bg-white opacity-10 rounded-full blur-2xl group-hover:scale-110 transition duration-1000 delay-100"></div>
      
      <!-- 几何图形装饰 -->
      <div class="absolute top-1/4 right-1/4 w-4 h-4 bg-yellow-300 rounded-full opacity-60 animate-pulse"></div>
      <div class="absolute bottom-1/3 right-10 w-6 h-6 border-2 border-white/30 rounded-lg rotate-12"></div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- 风景照片展示区 -->
      <div class="bg-white rounded-2xl shadow-sm p-6 flex flex-col">
        <div class="flex items-center justify-between mb-6">
          <h2 class="text-xl font-bold text-gray-800 flex items-center">
            <span class="mr-2">📷</span> 最新摄影
          </h2>
          <RouterLink to="/gallery" class="text-blue-600 text-sm hover:underline flex items-center">
            查看更多 <ArrowRight class="w-4 h-4 ml-1" />
          </RouterLink>
        </div>
        
        <div class="grid grid-cols-2 gap-4 flex-1">
          <div v-for="photo in photos" :key="photo.id" class="group relative aspect-square rounded-xl overflow-hidden bg-gray-100 cursor-pointer">
            <img :src="photo.url" :alt="photo.title" class="w-full h-full object-cover transition duration-500 group-hover:scale-110" />
            <div class="absolute inset-0 bg-black/0 group-hover:bg-black/10 transition duration-300"></div>
          </div>
        </div>
      </div>

      <!-- 最新博客文章 -->
      <div class="bg-white rounded-2xl shadow-sm p-6 flex flex-col">
        <div class="flex items-center justify-between mb-6">
          <h2 class="text-xl font-bold text-gray-800 flex items-center">
            <span class="mr-2">📝</span> 最新文章
          </h2>
          <RouterLink to="/blog" class="text-blue-600 text-sm hover:underline flex items-center">
            全部文章 <ArrowRight class="w-4 h-4 ml-1" />
          </RouterLink>
        </div>

        <div class="space-y-4 flex-1">
          <div v-for="post in latestPosts" :key="post.id" class="group flex gap-4 items-start p-3 rounded-xl hover:bg-gray-50 transition cursor-pointer">
            <div class="w-24 h-16 shrink-0 rounded-lg overflow-hidden bg-gray-200">
              <img :src="post.cover" class="w-full h-full object-cover group-hover:scale-105 transition duration-500" />
            </div>
            <div class="flex-1 min-w-0">
              <h3 class="font-medium text-gray-800 group-hover:text-blue-600 transition truncate">{{ post.title }}</h3>
              <div class="flex items-center gap-3 text-xs text-gray-500 mt-2">
                <span class="flex items-center"><Calendar class="w-3 h-3 mr-1" /> {{ post.date }}</span>
                <span class="flex items-center"><Clock class="w-3 h-3 mr-1" /> {{ post.readTime }} min</span>
              </div>
            </div>
          </div>
          
          <!-- 更多占位 -->
          <div class="p-4 rounded-xl bg-gray-50 border border-dashed border-gray-200 text-center text-gray-500 text-sm">
            更多精彩内容，请移步博客专栏
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

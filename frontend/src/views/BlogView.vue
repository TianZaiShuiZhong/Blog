<script setup>
import { ref, onMounted } from 'vue'
import { Calendar, Clock, Tag, ChevronRight } from 'lucide-vue-next'

const posts = ref([])

// 模拟博客数据
const mockPosts = [
  {
    id: 1,
    title: 'Spring Boot 3.0 新特性解析',
    summary: 'Spring Boot 3.0 基于 Spring Framework 6.0 构建，需要 Java 17 或更高版本。本文将详细介绍 AOT 编译、Native Image 支持等核心特性。',
    date: '2023-12-10',
    readTime: '5 min',
    tags: ['Java', 'Spring Boot', 'Backend'],
    cover: 'https://picsum.photos/seed/blog1/800/400'
  },
  {
    id: 2,
    title: 'Vue 3 组合式 API 最佳实践',
    summary: 'Composition API 改变了我们组织 Vue 组件逻辑的方式。如何更好地复用逻辑？如何处理复杂的组件状态？这里有一些实用的建议。',
    date: '2023-12-08',
    readTime: '8 min',
    tags: ['Vue 3', 'Frontend', 'JavaScript'],
    cover: 'https://picsum.photos/seed/blog2/800/400'
  },
  {
    id: 3,
    title: '使用 Tailwind CSS 构建现代 UI',
    summary: 'Tailwind CSS 是一个功能类优先的 CSS 框架。它能帮助我们快速构建自定义的用户界面，而无需离开 HTML。',
    date: '2023-12-05',
    readTime: '6 min',
    tags: ['CSS', 'Tailwind', 'Design'],
    cover: 'https://picsum.photos/seed/blog3/800/400'
  },
  {
    id: 4,
    title: 'Docker 容器化部署指南',
    summary: '从 Dockerfile 的编写到 Docker Compose 的编排，带你一步步掌握容器化部署的核心技能。',
    date: '2023-11-28',
    readTime: '10 min',
    tags: ['DevOps', 'Docker', 'Deployment'],
    cover: 'https://picsum.photos/seed/blog4/800/400'
  }
]

onMounted(async () => {
  try {
    const res = await fetch('/api/posts')
    if (res.ok) {
      const data = await res.json()
      posts.value = data.map(post => ({
        ...post,
        date: new Date(post.createdAt || Date.now()).toLocaleDateString(),
        readTime: '5 min', // Mock read time for now
        tags: ['Java', 'Spring Boot'], // Mock tags for now
        cover: post.coverUrl || 'https://picsum.photos/seed/blog/800/400'
      }))
    } else {
      posts.value = mockPosts
    }
  } catch (e) {
    console.error(e)
    posts.value = mockPosts
  }
})
</script>

<template>
  <div class="space-y-6">
    <div class="bg-white rounded-2xl shadow-sm p-6 flex items-center justify-between">
      <h2 class="text-xl font-bold text-gray-800 flex items-center">
        <span class="mr-2">📝</span> 博客文章
      </h2>
      <div class="flex gap-2">
        <span class="px-3 py-1 bg-blue-50 text-blue-600 rounded-full text-sm font-medium cursor-pointer hover:bg-blue-100 transition">全部</span>
        <span class="px-3 py-1 text-gray-500 rounded-full text-sm font-medium cursor-pointer hover:bg-gray-100 transition">技术</span>
        <span class="px-3 py-1 text-gray-500 rounded-full text-sm font-medium cursor-pointer hover:bg-gray-100 transition">生活</span>
      </div>
    </div>

    <div class="grid gap-6">
      <article v-for="post in posts" :key="post.id" class="bg-white rounded-2xl shadow-sm overflow-hidden hover:shadow-md transition group">
        <div class="flex flex-col md:flex-row h-full">
          <div class="md:w-1/3 h-48 md:h-auto relative overflow-hidden">
            <img :src="post.cover" :alt="post.title" class="w-full h-full object-cover group-hover:scale-105 transition duration-500" />
          </div>
          <div class="p-6 md:w-2/3 flex flex-col justify-between">
            <div>
              <div class="flex items-center gap-2 mb-3">
                <span v-for="tag in post.tags" :key="tag" class="px-2 py-0.5 bg-gray-100 text-gray-600 text-xs rounded-md">
                  {{ tag }}
                </span>
              </div>
              <h3 class="text-xl font-bold text-gray-800 mb-2 group-hover:text-blue-600 transition">{{ post.title }}</h3>
              <p class="text-gray-600 text-sm line-clamp-2 mb-4">{{ post.summary }}</p>
            </div>
            
            <div class="flex items-center justify-between pt-4 border-t border-gray-100">
              <div class="flex items-center gap-4 text-xs text-gray-500">
                <span class="flex items-center"><Calendar class="w-3 h-3 mr-1" /> {{ post.date }}</span>
                <span class="flex items-center"><Clock class="w-3 h-3 mr-1" /> {{ post.readTime }}</span>
              </div>
              <button class="text-blue-600 text-sm font-medium flex items-center hover:underline">
                阅读全文 <ChevronRight class="w-4 h-4 ml-1" />
              </button>
            </div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

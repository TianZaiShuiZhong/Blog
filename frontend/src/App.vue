<script setup>
import { ref } from 'vue'
import { RouterLink, RouterView, useRoute } from 'vue-router'
import { Home, Wrench, Camera, FileText, User, Search, Moon, Menu, X, Info } from 'lucide-vue-next'

const isMobileMenuOpen = ref(false)
const route = useRoute()

const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value
}

// 点击菜单项后关闭移动端菜单
const closeMobileMenu = () => {
  isMobileMenuOpen.value = false
}
</script>

<template>
  <div class="min-h-screen flex flex-col bg-[#f8f9fa]">
    <!-- 顶部导航栏 -->
    <header class="fixed top-0 w-full bg-white/80 backdrop-blur-md z-50 border-b border-gray-200">
      <div class="container mx-auto px-4 h-16 flex items-center justify-between">
        <div class="flex items-center space-x-4">
          <!-- 移动端菜单按钮 -->
          <button @click="toggleMobileMenu" class="md:hidden p-2 -ml-2 text-gray-600 hover:bg-gray-100 rounded-lg">
            <Menu v-if="!isMobileMenuOpen" class="w-6 h-6" />
            <X v-else class="w-6 h-6" />
          </button>
          
          <div class="text-xl font-bold text-gray-800 truncate">ZXH的个人空间</div>
          
          <!-- 桌面端导航 -->
          <nav class="hidden md:flex space-x-6 text-gray-600 ml-6">
            <RouterLink to="/" class="hover:text-blue-600 transition font-medium" :class="{ 'text-blue-600': route.path === '/' }">首页</RouterLink>
            <RouterLink to="/toolbox" class="hover:text-blue-600 transition font-medium" :class="{ 'text-blue-600': route.path === '/toolbox' }">工具箱</RouterLink>
            <RouterLink to="/gallery" class="hover:text-blue-600 transition font-medium" :class="{ 'text-blue-600': route.path === '/gallery' }">摄影集</RouterLink>
            <RouterLink to="/blog" class="hover:text-blue-600 transition font-medium" :class="{ 'text-blue-600': route.path.startsWith('/blog') }">博客</RouterLink>
            <RouterLink to="/about" class="hover:text-blue-600 transition font-medium" :class="{ 'text-blue-600': route.path === '/about' }">关于</RouterLink>
          </nav>
        </div>

        <div class="flex items-center space-x-2 sm:space-x-4">
          <div class="relative hidden sm:block">
            <input type="text" placeholder="搜索..." class="pl-8 pr-4 py-1.5 rounded-full bg-gray-100 focus:outline-none focus:ring-2 focus:ring-blue-500 text-sm w-40 focus:w-60 transition-all">
            <Search class="w-4 h-4 absolute left-2.5 top-2 text-gray-400" />
          </div>
          <button class="p-2 hover:bg-gray-100 rounded-full transition">
            <Moon class="w-5 h-5 text-gray-600" />
          </button>
          <button class="p-2 hover:bg-gray-100 rounded-full transition">
            <User class="w-5 h-5 text-gray-600" />
          </button>
        </div>
      </div>

      <!-- 移动端下拉菜单 -->
      <Transition
        enter-active-class="transition duration-200 ease-out"
        enter-from-class="transform -translate-y-4 opacity-0"
        enter-to-class="transform translate-y-0 opacity-100"
        leave-active-class="transition duration-150 ease-in"
        leave-from-class="transform translate-y-0 opacity-100"
        leave-to-class="transform -translate-y-4 opacity-0"
      >
        <div v-if="isMobileMenuOpen" class="md:hidden bg-white border-b border-gray-200 shadow-lg absolute w-full">
          <nav class="flex flex-col p-4 space-y-2">
            <RouterLink to="/" @click="closeMobileMenu" class="flex items-center px-4 py-3 rounded-lg hover:bg-gray-50" :class="{ 'bg-blue-50 text-blue-600': route.path === '/' }">
              <Home class="w-5 h-5 mr-3" /> 首页
            </RouterLink>
            <RouterLink to="/toolbox" @click="closeMobileMenu" class="flex items-center px-4 py-3 rounded-lg hover:bg-gray-50" :class="{ 'bg-blue-50 text-blue-600': route.path === '/toolbox' }">
              <Wrench class="w-5 h-5 mr-3" /> 工具箱
            </RouterLink>
            <RouterLink to="/gallery" @click="closeMobileMenu" class="flex items-center px-4 py-3 rounded-lg hover:bg-gray-50" :class="{ 'bg-blue-50 text-blue-600': route.path === '/gallery' }">
              <Camera class="w-5 h-5 mr-3" /> 摄影集
            </RouterLink>
            <RouterLink to="/blog" @click="closeMobileMenu" class="flex items-center px-4 py-3 rounded-lg hover:bg-gray-50" :class="{ 'bg-blue-50 text-blue-600': route.path.startsWith('/blog') }">
              <FileText class="w-5 h-5 mr-3" /> 博客
            </RouterLink>
            <RouterLink to="/about" @click="closeMobileMenu" class="flex items-center px-4 py-3 rounded-lg hover:bg-gray-50" :class="{ 'bg-blue-50 text-blue-600': route.path === '/about' }">
              <Info class="w-5 h-5 mr-3" /> 关于
            </RouterLink>
          </nav>
        </div>
      </Transition>
    </header>

    <div class="flex flex-1 pt-16 container mx-auto px-4 gap-6 mt-6 mb-10">
      <!-- 左侧个人信息栏 (桌面端显示) -->
      <aside class="hidden lg:block w-72 shrink-0">
        <div class="bg-white rounded-2xl shadow-sm p-6 sticky top-24">
          <div class="flex flex-col items-center text-center">
            <div class="w-24 h-24 rounded-full bg-gray-200 mb-4 overflow-hidden hover:scale-105 transition duration-300 ring-4 ring-gray-50">
              <img src="https://pic4.zhimg.com/v2-54041d58a44eca7584e74cda34887d8f_1440w.jpg" alt="Avatar" class="w-full h-full object-cover" />
            </div>
            <h2 class="text-xl font-bold text-gray-800">ZXH</h2>
            <p class="text-sm text-gray-500 mt-1">前端开发 / 摄影爱好者</p>
            
            <div class="mt-6 w-full space-y-3 text-left px-2">
              <div class="flex items-center text-sm text-gray-600">
                <span class="mr-3 text-lg">📍</span> 广东省广州
              </div>
              <div class="flex items-center text-sm text-gray-600">
                <span class="mr-3 text-lg">📧</span> 24273289@qq.com
              </div>
              <div class="flex items-center text-sm text-gray-600">
                <span class="mr-3 text-lg">🔗</span> github.com/TianZaiShuiZhong
              </div>
            </div>

            <div class="mt-6 w-full border-t border-gray-100 pt-6">
              <nav class="space-y-1">
                <RouterLink to="/" class="flex items-center px-4 py-2.5 rounded-lg text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition font-medium" :class="{ 'bg-blue-50 text-blue-600': route.path === '/' }">
                  <Home class="w-4 h-4 mr-3" /> 首页
                </RouterLink>
                <RouterLink to="/toolbox" class="flex items-center px-4 py-2.5 rounded-lg text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition font-medium" :class="{ 'bg-blue-50 text-blue-600': route.path === '/toolbox' }">
                  <Wrench class="w-4 h-4 mr-3" /> 工具箱
                </RouterLink>
                <RouterLink to="/gallery" class="flex items-center px-4 py-2.5 rounded-lg text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition font-medium" :class="{ 'bg-blue-50 text-blue-600': route.path === '/gallery' }">
                  <Camera class="w-4 h-4 mr-3" /> 摄影集
                </RouterLink>
                <RouterLink to="/blog" class="flex items-center px-4 py-2.5 rounded-lg text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition font-medium" :class="{ 'bg-blue-50 text-blue-600': route.path.startsWith('/blog') }">
                  <FileText class="w-4 h-4 mr-3" /> 博客
                </RouterLink>
                <RouterLink to="/about" class="flex items-center px-4 py-2.5 rounded-lg text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition font-medium" :class="{ 'bg-blue-50 text-blue-600': route.path === '/about' }">
                  <Info class="w-4 h-4 mr-3" /> 关于
                </RouterLink>
              </nav>
            </div>
          </div>
        </div>
      </aside>

      <!-- 右侧主内容区 -->
      <main class="flex-1 min-w-0">
        <RouterView v-slot="{ Component }">
          <Transition 
            enter-active-class="transition duration-300 ease-out"
            enter-from-class="transform opacity-0 translate-y-4"
            enter-to-class="transform opacity-100 translate-y-0"
            leave-active-class="transition duration-200 ease-in"
            leave-from-class="transform opacity-100 translate-y-0"
            leave-to-class="transform opacity-0 -translate-y-4"
            mode="out-in"
          >
            <component :is="Component" />
          </Transition>
        </RouterView>
      </main>
    </div>
    
    <!-- 移动端底部导航 (可选，这里仅作为补充，主要使用顶部菜单) -->
    <div class="md:hidden fixed bottom-0 w-full bg-white border-t border-gray-200 pb-safe z-40">
      <div class="flex justify-around items-center h-16">
        <RouterLink to="/" class="flex flex-col items-center justify-center w-full h-full text-gray-500 hover:text-blue-600" :class="{ 'text-blue-600': route.path === '/' }">
          <Home class="w-6 h-6" />
          <span class="text-[10px] mt-1">首页</span>
        </RouterLink>
        <RouterLink to="/toolbox" class="flex flex-col items-center justify-center w-full h-full text-gray-500 hover:text-blue-600" :class="{ 'text-blue-600': route.path === '/toolbox' }">
          <Wrench class="w-6 h-6" />
          <span class="text-[10px] mt-1">工具箱</span>
        </RouterLink>
        <RouterLink to="/gallery" class="flex flex-col items-center justify-center w-full h-full text-gray-500 hover:text-blue-600" :class="{ 'text-blue-600': route.path === '/gallery' }">
          <Camera class="w-6 h-6" />
          <span class="text-[10px] mt-1">摄影</span>
        </RouterLink>
        <RouterLink to="/blog" class="flex flex-col items-center justify-center w-full h-full text-gray-500 hover:text-blue-600" :class="{ 'text-blue-600': route.path.startsWith('/blog') }">
          <FileText class="w-6 h-6" />
          <span class="text-[10px] mt-1">博客</span>
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<style>
/* 适配 iPhone X 等底部安全区域 */
.pb-safe {
  padding-bottom: env(safe-area-inset-bottom);
}
</style>

<script setup>
import { ref } from 'vue'
import { FileText, Share2, Download, UploadCloud } from 'lucide-vue-next'

const activeTab = ref('convert')
const isLoggedIn = ref(true) // 模拟登录状态

const tabs = [
  { id: 'convert', name: '文档转换', icon: FileText },
  { id: 'share', name: '文件分享', icon: Share2 },
  { id: 'download', name: '下载文件', icon: Download },
]
</script>

<template>
  <div class="bg-white rounded-2xl shadow-sm min-h-[600px] flex flex-col">
    <div class="p-6 border-b border-gray-100">
      <h2 class="text-xl font-bold text-gray-800 flex items-center">
        <span class="mr-2">🧰</span> 我的工具箱
      </h2>
    </div>

    <!-- 未登录状态 -->
    <div v-if="!isLoggedIn" class="flex-1 flex flex-col items-center justify-center p-8 text-center">
      <div class="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mb-4">
        <span class="text-2xl">🔒</span>
      </div>
      <h3 class="text-lg font-bold text-gray-800 mb-2">工具箱功能需登录使用</h3>
      <p class="text-gray-500 mb-6 max-w-md">登录后即可使用文档格式转换、文件临时分享等实用功能。</p>
      <button class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">
        立即登录 / 注册
      </button>
    </div>

    <!-- 已登录状态 -->
    <div v-else class="flex flex-col md:flex-row flex-1">
      <!-- 侧边选项卡 -->
      <div class="w-full md:w-64 border-r border-gray-100 p-4 bg-gray-50/50">
        <div class="space-y-1">
          <button 
            v-for="tab in tabs" 
            :key="tab.id"
            @click="activeTab = tab.id"
            class="w-full flex items-center px-4 py-3 rounded-lg text-sm font-medium transition"
            :class="activeTab === tab.id ? 'bg-white text-blue-600 shadow-sm' : 'text-gray-600 hover:bg-gray-100'"
          >
            <component :is="tab.icon" class="w-4 h-4 mr-3" />
            {{ tab.name }}
          </button>
        </div>
      </div>

      <!-- 功能区域 -->
      <div class="flex-1 p-6 md:p-8">
        <!-- 文档转换 -->
        <div v-if="activeTab === 'convert'" class="max-w-2xl mx-auto">
          <h3 class="text-lg font-bold mb-6">文档格式转换</h3>
          
          <div class="border-2 border-dashed border-gray-300 rounded-xl p-8 text-center hover:border-blue-500 hover:bg-blue-50 transition cursor-pointer mb-6">
            <UploadCloud class="w-12 h-12 text-gray-400 mx-auto mb-4" />
            <p class="text-gray-600 font-medium">点击或拖拽文件到此处上传</p>
            <p class="text-gray-400 text-sm mt-1">支持 PDF, Word, 图片等格式</p>
          </div>

          <div class="space-y-4">
            <p class="font-medium text-gray-700">转换选项：</p>
            <div class="grid grid-cols-1 sm:grid-cols-3 gap-3">
              <label class="flex items-center p-3 border rounded-lg cursor-pointer hover:border-blue-500 hover:bg-blue-50">
                <input type="radio" name="convertType" class="text-blue-600 focus:ring-blue-500" checked>
                <span class="ml-2 text-sm">PDF 转 Word</span>
              </label>
              <label class="flex items-center p-3 border rounded-lg cursor-pointer hover:border-blue-500 hover:bg-blue-50">
                <input type="radio" name="convertType" class="text-blue-600 focus:ring-blue-500">
                <span class="ml-2 text-sm">Word 转 PDF</span>
              </label>
              <label class="flex items-center p-3 border rounded-lg cursor-pointer hover:border-blue-500 hover:bg-blue-50">
                <input type="radio" name="convertType" class="text-blue-600 focus:ring-blue-500">
                <span class="ml-2 text-sm">图片 转 PDF</span>
              </label>
            </div>
          </div>

          <button class="mt-8 w-full bg-blue-600 text-white py-3 rounded-lg font-medium hover:bg-blue-700 transition shadow-lg shadow-blue-600/20">
            开始转换
          </button>
        </div>

        <!-- 文件分享 -->
        <div v-if="activeTab === 'share'" class="max-w-2xl mx-auto">
          <h3 class="text-lg font-bold mb-6">文件临时分享</h3>
          
          <div class="space-y-6">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">选择文件</label>
              <div class="flex">
                <input type="file" class="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-lg file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"/>
              </div>
            </div>

            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">有效期</label>
                <select class="w-full border-gray-300 rounded-lg shadow-sm focus:border-blue-500 focus:ring-blue-500">
                  <option>24 小时</option>
                  <option>7 天</option>
                  <option>30 天</option>
                </select>
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">提取次数</label>
                <select class="w-full border-gray-300 rounded-lg shadow-sm focus:border-blue-500 focus:ring-blue-500">
                  <option>不限</option>
                  <option>1 次</option>
                  <option>5 次</option>
                </select>
              </div>
            </div>

            <button class="w-full bg-blue-600 text-white py-3 rounded-lg font-medium hover:bg-blue-700 transition">
              生成分享码
            </button>

            <div class="bg-gray-50 rounded-xl p-4 border border-gray-200 mt-6">
              <h4 class="text-sm font-medium text-gray-500 mb-3">生成结果示例</h4>
              <div class="flex items-center justify-between bg-white p-3 rounded-lg border border-gray-200 mb-2">
                <span class="text-sm text-gray-600">提取码</span>
                <span class="font-mono font-bold text-lg text-blue-600">8A3B9C</span>
              </div>
              <div class="flex items-center justify-between bg-white p-3 rounded-lg border border-gray-200">
                <span class="text-sm text-gray-600 truncate mr-4">https://mysite.com/d/8A3B9C</span>
                <button class="text-blue-600 text-sm font-medium hover:underline">复制</button>
              </div>
            </div>
          </div>
        </div>

        <!-- 下载文件 -->
        <div v-if="activeTab === 'download'" class="max-w-md mx-auto text-center pt-10">
          <div class="w-20 h-20 bg-blue-50 rounded-full flex items-center justify-center mx-auto mb-6">
            <Download class="w-10 h-10 text-blue-600" />
          </div>
          <h3 class="text-xl font-bold mb-2">文件提取</h3>
          <p class="text-gray-500 mb-8">请输入6位提取码获取文件</p>
          
          <div class="flex justify-center space-x-2 mb-8">
            <input type="text" maxlength="6" placeholder="8 A 3 B 9 C" class="w-full text-center text-2xl font-mono tracking-widest py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none uppercase" />
          </div>

          <button class="w-full bg-blue-600 text-white py-3 rounded-lg font-medium hover:bg-blue-700 transition shadow-lg shadow-blue-600/20">
            立即下载
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

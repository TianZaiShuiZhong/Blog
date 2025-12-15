<script setup>
import { ref, onMounted } from 'vue'
import { Trash2, Edit, Plus, Save, X } from 'lucide-vue-next'

const activeTab = ref('posts')
const posts = ref([])
const photos = ref([])
const isEditing = ref(false)
const currentItem = ref({})

// Fetch Data
const fetchData = async () => {
  try {
    const [postsRes, photosRes] = await Promise.all([
      fetch('/api/posts'),
      fetch('/api/photos')
    ])
    if (postsRes.ok) posts.value = await postsRes.json()
    if (photosRes.ok) photos.value = await photosRes.json()
  } catch (e) {
    console.error('Failed to fetch data', e)
  }
}

onMounted(fetchData)

// Post Operations
const editPost = (post) => {
  currentItem.value = { ...post }
  isEditing.value = true
  activeTab.value = 'posts'
}

const createPost = () => {
  currentItem.value = { title: '', summary: '', content: '', coverUrl: '', readTime: 5 }
  isEditing.value = true
  activeTab.value = 'posts'
}

const savePost = async () => {
  const method = currentItem.value.id ? 'PUT' : 'POST'
  const url = currentItem.value.id ? `/api/posts/${currentItem.value.id}` : '/api/posts'
  
  try {
    const res = await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(currentItem.value)
    })
    if (res.ok) {
      isEditing.value = false
      fetchData()
      alert('保存成功！')
    } else {
      const errorText = await res.text()
      alert(`保存失败: ${res.status} ${res.statusText}\n${errorText}`)
    }
  } catch (e) {
    console.error('Failed to save post', e)
    alert('保存失败，网络错误: ' + e.message)
  }
}

const deletePost = async (id) => {
  if (!confirm('确定要删除这篇文章吗？')) return
  try {
    await fetch(`/api/posts/${id}`, { method: 'DELETE' })
    fetchData()
  } catch (e) {
    console.error('Failed to delete post', e)
  }
}

// Photo Operations
const editPhoto = (photo) => {
  currentItem.value = { ...photo }
  isEditing.value = true
  activeTab.value = 'photos'
}

const createPhoto = () => {
  currentItem.value = { title: '', url: '', location: '' }
  isEditing.value = true
  activeTab.value = 'photos'
}

const savePhoto = async () => {
  const method = currentItem.value.id ? 'PUT' : 'POST'
  const url = currentItem.value.id ? `/api/photos/${currentItem.value.id}` : '/api/photos'
  
  try {
    const res = await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(currentItem.value)
    })
    if (res.ok) {
      isEditing.value = false
      fetchData()
      alert('保存成功！')
    } else {
      const errorText = await res.text()
      alert(`保存失败: ${res.status} ${res.statusText}\n${errorText}`)
    }
  } catch (e) {
    console.error('Failed to save photo', e)
    alert('保存失败，网络错误: ' + e.message)
  }
}

const deletePhoto = async (id) => {
  if (!confirm('确定要删除这张照片吗？')) return
  try {
    await fetch(`/api/photos/${id}`, { method: 'DELETE' })
    fetchData()
  } catch (e) {
    console.error('Failed to delete photo', e)
  }
}

const cancelEdit = () => {
  isEditing.value = false
  currentItem.value = {}
}
</script>

<template>
  <div class="max-w-4xl mx-auto p-6">
    <div class="flex justify-between items-center mb-8">
      <h1 class="text-2xl font-bold text-gray-800">内容管理后台</h1>
      <div class="flex gap-2">
        <button 
          @click="activeTab = 'posts'; isEditing = false"
          :class="['px-4 py-2 rounded-lg transition', activeTab === 'posts' ? 'bg-blue-600 text-white' : 'bg-white text-gray-600 hover:bg-gray-50']"
        >
          文章管理
        </button>
        <button 
          @click="activeTab = 'photos'; isEditing = false"
          :class="['px-4 py-2 rounded-lg transition', activeTab === 'photos' ? 'bg-blue-600 text-white' : 'bg-white text-gray-600 hover:bg-gray-50']"
        >
          照片管理
        </button>
      </div>
    </div>

    <!-- 编辑表单 -->
    <div v-if="isEditing" class="bg-white rounded-2xl shadow-sm p-6 mb-6">
      <div class="flex justify-between items-center mb-6">
        <h2 class="text-lg font-bold">{{ currentItem.id ? '编辑' : '新建' }} {{ activeTab === 'posts' ? '文章' : '照片' }}</h2>
        <button @click="cancelEdit" class="text-gray-400 hover:text-gray-600"><X class="w-5 h-5" /></button>
      </div>
      
      <form @submit.prevent="activeTab === 'posts' ? savePost() : savePhoto()" class="space-y-4">
        <div v-if="activeTab === 'posts'">
          <div class="grid gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">标题</label>
              <input v-model="currentItem.title" type="text" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none" required>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">摘要</label>
              <textarea v-model="currentItem.summary" rows="2" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"></textarea>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">封面图片 URL</label>
              <input v-model="currentItem.coverUrl" type="text" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none">
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">阅读时间 (分钟)</label>
              <input v-model="currentItem.readTime" type="number" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none">
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">内容</label>
              <textarea v-model="currentItem.content" rows="5" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"></textarea>
            </div>
          </div>
        </div>

        <div v-else>
          <div class="grid gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">标题</label>
              <input v-model="currentItem.title" type="text" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none" required>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">图片 URL</label>
              <input v-model="currentItem.url" type="text" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none" required>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">拍摄地点</label>
              <input v-model="currentItem.location" type="text" class="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none">
            </div>
          </div>
        </div>

        <div class="flex justify-end gap-3 mt-6">
          <button type="button" @click="cancelEdit" class="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded-lg transition">取消</button>
          <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition flex items-center">
            <Save class="w-4 h-4 mr-2" /> 保存
          </button>
        </div>
      </form>
    </div>

    <!-- 列表展示 -->
    <div v-else class="bg-white rounded-2xl shadow-sm overflow-hidden">
      <!-- 文章列表 -->
      <div v-if="activeTab === 'posts'">
        <div class="p-4 border-b border-gray-100 flex justify-between items-center">
          <h3 class="font-bold text-gray-700">文章列表 ({{ posts.length }})</h3>
          <button @click="createPost" class="text-sm bg-blue-50 text-blue-600 px-3 py-1.5 rounded-lg hover:bg-blue-100 transition flex items-center">
            <Plus class="w-4 h-4 mr-1" /> 新建文章
          </button>
        </div>
        <div class="divide-y divide-gray-100">
          <div v-for="post in posts" :key="post.id" class="p-4 hover:bg-gray-50 flex items-center justify-between group">
            <div class="flex items-center gap-4">
              <img :src="post.coverUrl" class="w-12 h-12 rounded object-cover bg-gray-200" />
              <div>
                <div class="font-medium text-gray-800">{{ post.title }}</div>
                <div class="text-xs text-gray-500">{{ new Date(post.createdAt).toLocaleDateString() }}</div>
              </div>
            </div>
            <div class="flex gap-2 opacity-0 group-hover:opacity-100 transition">
              <button @click="editPost(post)" class="p-2 text-blue-600 hover:bg-blue-50 rounded-lg"><Edit class="w-4 h-4" /></button>
              <button @click="deletePost(post.id)" class="p-2 text-red-600 hover:bg-red-50 rounded-lg"><Trash2 class="w-4 h-4" /></button>
            </div>
          </div>
        </div>
      </div>

      <!-- 照片列表 -->
      <div v-else>
        <div class="p-4 border-b border-gray-100 flex justify-between items-center">
          <h3 class="font-bold text-gray-700">照片列表 ({{ photos.length }})</h3>
          <button @click="createPhoto" class="text-sm bg-blue-50 text-blue-600 px-3 py-1.5 rounded-lg hover:bg-blue-100 transition flex items-center">
            <Plus class="w-4 h-4 mr-1" /> 上传照片
          </button>
        </div>
        <div class="grid grid-cols-2 sm:grid-cols-4 gap-4 p-4">
          <div v-for="photo in photos" :key="photo.id" class="group relative aspect-square rounded-lg overflow-hidden bg-gray-100">
            <img :src="photo.url" class="w-full h-full object-cover" />
            <div class="absolute inset-0 bg-black/50 opacity-0 group-hover:opacity-100 transition flex items-center justify-center gap-2">
              <button @click="editPhoto(photo)" class="p-2 text-white hover:bg-white/20 rounded-full"><Edit class="w-4 h-4" /></button>
              <button @click="deletePhoto(photo.id)" class="p-2 text-red-400 hover:bg-white/20 rounded-full"><Trash2 class="w-4 h-4" /></button>
            </div>
            <div class="absolute bottom-0 left-0 right-0 p-2 bg-gradient-to-t from-black/60 to-transparent text-white text-xs truncate">
              {{ photo.title }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { X, ZoomIn } from 'lucide-vue-next'

const photos = ref([])
const selectedPhoto = ref(null)

// 模拟图片数据
const mockPhotos = Array.from({ length: 12 }).map((_, i) => ({
  id: i,
  url: `https://picsum.photos/seed/${i + 100}/800/600`,
  title: `摄影作品 ${i + 1}`,
  date: '2023-12-15',
  location: 'Beijing, China'
}))

onMounted(async () => {
  try {
    const res = await fetch('/api/photos')
    if (res.ok) {
      const data = await res.json()
      photos.value = data.map(photo => ({
        ...photo,
        title: photo.title || 'Untitled',
        date: new Date(photo.createdAt || Date.now()).toLocaleDateString(),
        location: photo.location || 'Unknown'
      }))
    } else {
      photos.value = mockPhotos
    }
  } catch (e) {
    console.error(e)
    photos.value = mockPhotos
  }
})

const openLightbox = (photo) => {
  selectedPhoto.value = photo
  document.body.style.overflow = 'hidden'
}

const closeLightbox = () => {
  selectedPhoto.value = null
  document.body.style.overflow = 'auto'
}
</script>

<template>
  <div>
    <div class="bg-white rounded-2xl shadow-sm p-6 mb-6">
      <div class="flex items-center justify-between mb-2">
        <h2 class="text-xl font-bold text-gray-800 flex items-center">
          <span class="mr-2">📷</span> 摄影集
        </h2>
        <span class="text-sm text-gray-500">{{ photos.length }} 张照片</span>
      </div>
      <p class="text-gray-500 text-sm">记录生活中的美好瞬间</p>
    </div>

    <!-- 照片网格 -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
      <div 
        v-for="photo in photos" 
        :key="photo.id"
        class="group relative bg-white rounded-xl overflow-hidden shadow-sm hover:shadow-md transition-all duration-300 hover:-translate-y-1 cursor-pointer"
        @click="openLightbox(photo)"
      >
        <div class="aspect-[4/3] overflow-hidden">
          <img 
            :src="photo.url" 
            :alt="photo.title"
            class="w-full h-full object-cover transition duration-500 group-hover:scale-110"
          />
          <div class="absolute inset-0 bg-black/0 group-hover:bg-black/20 transition duration-300 flex items-center justify-center opacity-0 group-hover:opacity-100">
            <ZoomIn class="text-white w-8 h-8 drop-shadow-md" />
          </div>
        </div>
        <div class="p-4">
          <h3 class="font-medium text-gray-800 mb-1 truncate">{{ photo.title }}</h3>
          <div class="flex justify-between text-xs text-gray-500">
            <span>{{ photo.date }}</span>
            <span>{{ photo.location }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 灯箱 -->
    <div 
      v-if="selectedPhoto" 
      class="fixed inset-0 z-50 bg-black/90 flex items-center justify-center p-4 backdrop-blur-sm"
      @click="closeLightbox"
    >
      <button 
        class="absolute top-4 right-4 text-white/70 hover:text-white transition p-2 rounded-full hover:bg-white/10"
        @click="closeLightbox"
      >
        <X class="w-8 h-8" />
      </button>
      
      <div class="max-w-5xl max-h-[90vh] relative" @click.stop>
        <img 
          :src="selectedPhoto.url" 
          :alt="selectedPhoto.title"
          class="max-w-full max-h-[85vh] rounded-lg shadow-2xl"
        />
        <div class="mt-4 text-center text-white">
          <h3 class="text-xl font-medium mb-1">{{ selectedPhoto.title }}</h3>
          <p class="text-white/60 text-sm">{{ selectedPhoto.date }} · {{ selectedPhoto.location }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

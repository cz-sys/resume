<template>
  <div>
    <div class="h-4/5">
      <!-- 导航栏 -->
      <nav class="bg-transparent absolute w-full z-10">
        <div class="container mx-auto px-4 py-4 flex justify-between items-center">
          <div class="text-white text-xl font-bold">简历互助</div>
          <div class="hidden md:flex space-x-8">
            <a href="#" class="text-white hover:text-gray-300">首页</a>
            <a href="#" class="text-white hover:text-gray-300">关于</a>
            <a href="#" class="text-white hover:text-gray-300">服务</a>
            <a href="#" class="text-white hover:text-gray-300">博客</a>
            <a href="#" class="text-white hover:text-gray-300">联系</a>
          </div>
          <button
              class="hidden md:block text-white border-2 border-white rounded-full px-6 py-2 hover:bg-white hover:text-gray-800 transition duration-300">
            立即开始
          </button>
          <button class="md:hidden text-white" @click="toggleMobileMenu">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"/>
            </svg>
          </button>
        </div>
        <!-- 移动端菜单 -->
        <div v-if="mobileMenuOpen" class="md:hidden bg-gray-900 bg-opacity-95 absolute w-full">
          <div class="container mx-auto px-4 py-4 flex flex-col space-y-4">
            <a href="#" class="text-white hover:text-gray-300">首页</a>
            <a href="#" class="text-white hover:text-gray-300">关于</a>
            <a href="#" class="text-white hover:text-gray-300">服务</a>
            <a href="#" class="text-white hover:text-gray-300">博客</a>
            <a href="#" class="text-white hover:text-gray-300">联系</a>
            <button
                class="text-white border-2 border-white rounded-full px-6 py-2 w-full hover:bg-white hover:text-gray-800 transition duration-300">
              立即开始
            </button>
          </div>
        </div>
      </nav>

      <!-- 英雄区域 -->
      <div class="relative h-screen">
        <!-- 背景图片 -->
        <div class="absolute inset-0 bg-cover bg-center"
             style="background-image: url('https://img.picui.cn/free/2025/04/24/680a4af352168.jpeg');">
          <div class="absolute inset-0 bg-gray-900 bg-opacity-70"></div>
        </div>

        <!-- 内容 -->
        <div class="relative h-full flex flex-col justify-center items-center text-center px-4">
          <div class="text-white mb-4">助你求职成功</div>
          <h1 class="text-4xl md:text-6xl font-bold text-white mb-6">全面简历支持社区</h1>
          <p class="text-white max-w-3xl mb-10 text-sm md:text-base">
            在简历互助平台，我们帮助求职者提供各种简历优化服务，让你在求职中脱颖而出，与业内人士交流经验，轻松找到理想职位。
          </p>
          <button
              class="bg-blue-500 hover:bg-blue-600 text-white font-medium rounded-full px-8 py-3 transition duration-300">
            立即开始
          </button>
        </div>
      </div>

    </div>

    <!-- 返回顶部按钮 -->
    <button
        @click="scrollToTop"
        class="fixed bottom-6 right-6 bg-blue-500 hover:bg-blue-600 text-white rounded-full p-3 shadow-lg transition duration-300 z-50"
        :class="{ 'opacity-0 pointer-events-none': !showBackToTop, 'opacity-100': showBackToTop }"
    >
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 10l7-7m0 0l7 7m-7-7v18" />
      </svg>
    </button>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

// 移动端菜单状态
const mobileMenuOpen = ref(false);

// 切换移动端菜单
const toggleMobileMenu = () => {
  mobileMenuOpen.value = !mobileMenuOpen.value;
};

// 返回顶部按钮状态
const showBackToTop = ref(false);

// 监听滚动事件，控制返回顶部按钮的显示和隐藏
const handleScroll = () => {
  showBackToTop.value = window.scrollY > 300;
};

// 返回顶部功能
const scrollToTop = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
};

// 组件挂载时添加滚动监听
onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

// 组件卸载时移除滚动监听
onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>

<style scoped>
/* 平滑滚动 */
html {
  scroll-behavior: smooth;
}

/* 返回顶部按钮过渡效果 */
button {
  transition: opacity 0.3s, background-color 0.3s;
}
</style>

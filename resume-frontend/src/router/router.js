import {createMemoryHistory, createRouter} from 'vue-router'

const routes = [
    {path: '/', component: () => import('@/views/FrontIndex.vue')}
]

export const router = createRouter({
    history: createMemoryHistory(),
    routes,
})

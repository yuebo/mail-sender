import Vue from 'vue'
import Router from 'vue-router'
import MailTemplate from '@/components/MailTemplate'
import MailSend from '@/components/MailSend'
import SystemSetting from '@/components/SystemSetting'
import Batch from '@/components/Batch'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/template',
      name: '/template',
      component: MailTemplate
    },
    {
      path: '/send',
      name: '/send',
      component: MailSend
    },
    {
      path: '/conf',
      name: '/conf',
      component: SystemSetting
    },
    {
      path: '/',
      name: '/',
      component: SystemSetting
    },
    {
      path: '/batch',
      name: '/batch',
      component: Batch
    }
  ]
})

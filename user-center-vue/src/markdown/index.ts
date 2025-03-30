/*在 vue3 + typescript 中使用报错？
错误示例：Could not find a declaration file for module '@kangc/v-md-editor/lib/theme/vuepress.js'.
解决方法：src/shims.d.ts*/

// 引入 markdown 编辑器
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
// 引入 markdown 预览器
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css'
// 引入 github 主题
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
//引入 表情😀 插件
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
//引入 高亮代码行 插件
import createHighlightLinesPlugin from '@kangc/v-md-editor/lib/plugins/highlight-lines/index';
import '@kangc/v-md-editor/lib/plugins/highlight-lines/highlight-lines.css';
//引入 复制代码 插件
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
// 引入 行号 插件（代码行）
import createLineNumberPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
//引入 语言包（All）：例如（Java，python）
import hljs from 'highlight.js';

/**
 * 编辑器配置
 */
//使用github主题，配置语言包，配置目录
VMdEditor.use(githubTheme, {
  Hljs: hljs,
  config: {
    toc: {
      includeLevel: [1, 2, 3, 4, 5, 6],     // [TOC]目录显示的标题级别
    },
  },
});
// 使用行号插件
VMdEditor.use(createLineNumberPlugin());
// 使用复制代码插件
VMdEditor.use(createCopyCodePlugin());
//使用表情插件
VMdEditor.use(createEmojiPlugin());

/**
 * 预览器配置
 */
//使用github主题，配置语言包
VMdPreview.use(githubTheme, {
  Hljs: hljs,
});
// 使用行号插件
VMdPreview.use(createLineNumberPlugin());
// 使用复制代码插件
VMdPreview.use(createCopyCodePlugin());
//使用高亮代码行插件
VMdPreview.use(createHighlightLinesPlugin());

// 导出 markdown 编辑器和预览器
export {VMdEditor, VMdPreview};

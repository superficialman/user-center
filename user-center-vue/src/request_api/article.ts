import myAxios from "@/request";

/**
 * 文章状态枚举
 */
export enum Status {
  DRAFT = 0,
  REVIEW = 1,
  PUBLISH = 2
}

/**
 * Article接口
 */
export interface Article {
  id?: number;
  title: string;
  content: string;
  authorId?: number;
  status?: Status; // PUBLISH_ARTICLE, REVIEW_ARTICLE, etc.
  viewCount?: number;
  createTime?: string;
  authorName?: string;
  authorAvatar?: string;
}


/**
 * 获取所有已发布文章
 */
export const getAllPublishedArticles = async () => {
  try {
    return await myAxios.get('/article/allPublish');
  } catch (error) {
    console.error('获取文章列表失败:', error);
    throw error;
  }
};

/**
 * 通过ID获取单篇文章
 * @param id 文章ID
 */
export const getArticleById = async (id:number) => {
  try {
    return await myAxios.get(`/article/${id}`);
  } catch (error) {
    console.error('获取文章失败:', error);
    throw error;
  }
};

/**
 * 获取作者的所有已发布文章
 * @param authorId 作者ID
 */
export const getArticlesByAuthor = async (authorId: number) => {
  try {
    return await myAxios.get(`/article/author/${authorId}`);
  } catch (error) {
    console.error('获取作者文章失败:', error);
    throw error;
  }
};

/**
 * 模糊搜索文章标题
 * @param title 搜索关键词
 */
export const searchArticlesByTitle = async (title: string) => {
  try {
    return await myAxios.get('/article/search', {
      params: { title }
    });
  } catch (error) {
    console.error('搜索文章失败:', error);
    throw error;
  }
};

/**
 * 创建新文章
 * @param article 文章数据
 */
export const createArticle = async (article: Article) => {
  try {
    return await myAxios.post('/article/add', article);
  } catch (error) {
    console.error('创建文章失败:', error);
    throw error;
  }
};

/**
 * 更新文章
 * @param article 文章数据
 */
export const updateArticle = async (article: Article) => {
  try {
    return await myAxios.post('/article/update', article);
  } catch (error) {
    console.error('更新文章失败:', error);
    throw error;
  }
};

/**
 * 删除文章
 * @param id 文章ID
 */
export const deleteArticle = async (id: number) => {
  try {
    return await myAxios.post(`/article/delete/${id}`);
  } catch (error) {
    console.error('删除文章失败:', error);
    throw error;
  }
};

/**
 * 增加文章阅读量
 * @param id 文章ID
 */
export const incrementViewCount = async (id: number) => {
  try {
    return await myAxios.post(`/article/${id}/view-count`);
  } catch (error) {
    console.error('增加阅读量失败:', error);
    throw error;
  }
};

/**
 * 获取当前用户的已发布文章
 */
export const getMyPublishedArticles = async () => {
  try {
    return await myAxios.get('/article/mine/publish');
  } catch (error) {
    console.error('获取我的文章失败:', error);
    throw error;
  }
};

/**
 * 获取当前用户的待审核文章
 */
export const getMyReviewingArticles = async () => {
  try {
    return await myAxios.get('/article/mine/review');
  } catch (error) {
    console.error('获取审核中文章失败:', error);
    throw error;
  }
};

/**
 * 获取当前用户的草稿文章
 */
export const getMyDraftArticles = async () => {
  try {
    return await myAxios.get('/article/mine/draft');
  } catch (error) {
    console.error('获取草稿失败:', error);
    throw error;
  }
};

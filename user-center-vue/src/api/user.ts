import myAxios from "@/request";

/**
 * 调用后端模糊查询接口
 * @param username
 */
export const userSelect = async (username: any) => {
  try {
    return await myAxios.get('/user/select', {
      params: {
        username: username
      }
    });
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

/**
 * 调用后端查询全部用户接口
 */
export const getAllUser = async () => {
  try {
    return await myAxios.get('/user/allUser');
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

/**
 * 获取当前登录用户
 */
export const getCurrentUser = async () => {
  try {
    return await myAxios.get('/user/current');
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

/**
 * 用户注册
 * @param userData
 */
export const userRegister = async (userData: any) => {
  try {
    return await myAxios.post('/user/register', userData);
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

/**
 * 用户登录
 * @param userData
 */
export const userLogin = async (userData: any) => {
  try {
    return await myAxios.post('/user/login', userData);
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

/**
 * 用户退出/注销
 */
export const userLogout = async () => {
  try {
    return await myAxios.post('/user/logout');
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

/**
 * 删除用户
 * @param id
 */
export const userDelete = async (id: any) => {
  try {
    return await myAxios.post(`/user/delete/${id}`);
  } catch (error) {
    console.error('请求失败:', error);
    throw error;
  }
};

package com.company.project.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.modules.user.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tangmf
 * @since 2020-04-21
 */
public interface IUserService extends IService<User> {
    /**
     * 插入一条记录
     *
     * @param user，对象
     */
    boolean createUser(User user);

    /**
     * 根据id查询用户信息
     */
    User getUserById(int userId);
}

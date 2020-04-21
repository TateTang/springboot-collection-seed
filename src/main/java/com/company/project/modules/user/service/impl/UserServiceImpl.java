package com.company.project.modules.user.service.impl;

import com.company.project.modules.user.entity.User;
import com.company.project.modules.user.mapper.UserMapper;
import com.company.project.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangmf
 * @since 2020-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

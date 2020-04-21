package com.company.project.modules.user.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultUtil;
import com.company.project.modules.user.entity.User;
import com.company.project.modules.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangmf
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关操作")
public class UserController {
	@Autowired
	IUserService userService;

	@PostMapping("createUser")
	@ApiOperation(value = "添加用户")
	public Result createUser(@RequestBody User user) {
		int b = userService.getBaseMapper().insert(user);
		if (b > 0) {
			return ResultUtil.success("添加用户成功！");
		}
		return ResultUtil.fail("添加用户失败！");
	}

	@GetMapping("getAllUser")
	@ApiOperation(value = "查询所有用户")
	public Result getAllUser() {
		try {
			List<User> userList = userService.list();
			return ResultUtil.success(userList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("查询所有用户失败，请重试！");
		}
	}

	@GetMapping("getUserById/{userId}")
	@ApiOperation(value = "根据id查询用户")
	public Result getUserById(@PathVariable("userId") Integer userId) {
		try {
			User user = userService.getUserById(userId);
			return ResultUtil.success(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("根据id查询用户失败，请重试！");
		}
	}
}

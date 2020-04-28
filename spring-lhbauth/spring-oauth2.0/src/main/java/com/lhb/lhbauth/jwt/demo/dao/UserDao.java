package com.lhb.lhbauth.jwt.demo.dao;

import com.lhb.lhbauth.jwt.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
 /**
  * @description:TODO
  * @author: clj
  * @date: 16:07 22/04/2019
  * @version : V1.0
  */
public interface UserDao extends JpaRepository<UserModel, Long> {

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return UserModel
     */
    UserModel findByMobile(String mobile);

    /**
     * 通过账号获取用户
     *
     * @param username 账号
     * @return UserModel
     */
    UserModel findByUsername(String username);
}

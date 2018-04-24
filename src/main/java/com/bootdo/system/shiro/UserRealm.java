package com.bootdo.system.shiro;

import com.bootdo.system.dao.UserDAO;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/4/24 15:38
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDAO userDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserDO user = userDAO.queryByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("用户名/密码错误");
        }

        if (!user.getPassword().equals(password)) {
            throw new UnknownAccountException("用户名/密码错误");
        }

        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, this.getName());
        return info;
    }
}

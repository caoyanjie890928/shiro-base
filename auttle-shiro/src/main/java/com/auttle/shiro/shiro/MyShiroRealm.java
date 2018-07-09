package com.auttle.shiro.shiro;

import com.auttle.shiro.model.Resources;
import com.auttle.shiro.model.User;
import com.auttle.shiro.service.ResourcesService;
import com.auttle.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.*;

public class MyShiroRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;

    @Resource
    private ResourcesService resourcesService;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        Map<String,Object> map = new HashMap<String,Object>();

        List<Resources> resourceList = resourcesService.loadUserResources(map);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        for (Resources resources : resourceList){
            info.addStringPermission(resources.getResurl());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        User user = userService.selectByUsername(username);

        if(user == null) throw new UnknownAccountException();

        if(0 == user.getEnable()){
            throw new LockedAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(username),getName());

        Session session = SecurityUtils.getSubject().getSession();

        session.setAttribute("userSession",user);
        session.setAttribute("userSessionId",user.getId());

        return authenticationInfo;
    }


    public void clearUserAuthByUserId(List<Integer> userIds){

        if(null == userIds || userIds.size() == 0) return;

        Collection<Session> sessions = redisSessionDAO.getActiveSessions();

        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();

        for (Session session : sessions){
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;

                obj = spc.getPrimaryPrincipal();

                if(obj != null && obj instanceof User){
                    User user = (User) obj;
                    System.out.println("user:" + user);
                    if(user != null && userIds.contains(user.getId())){
                        list.add(spc);
                    }
                }
            }
        }

        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();

        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();

        for (SimplePrincipalCollection simplePrincipalCollection : list){
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }

    }
}

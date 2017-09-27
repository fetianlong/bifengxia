package com.chatm.search.shiro;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.Encodes;

import com.chatm.search.model.RegistUser;
import com.chatm.search.service.RegistUserService;
import com.google.common.base.Objects;

public class ShiroDbRealm extends AuthorizingRealm implements Realm, InitializingBean{
	
	@Autowired
	protected RegistUserService registUserService;

//    private RedisCache<String, Object> shiroCache;
	
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		try {
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			RegistUser user = registUserService.selectRegistUserByUserName(token.getUsername());
			if (user != null) {
				byte[] salt = Encodes.decodeHex(user.getSalt());
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
						new ShiroUser(user.getId(), user.getUserName(), user.getPhone(),user.getRealName(), user.getUniqueKey(), user.getRegistType())
						, user.getPassword(), ByteSource.Util.bytes(salt), getName());
				return authenticationInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		RegistUser user = registUserService.selectRegistUserByUserName(shiroUser.username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addRoles(null);
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
//		setCredentialsMatcher(new CustomCredentialsMatcher());
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(RegistUserService.HASH_ALGORITHM);
		matcher.setHashIterations(RegistUserService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
		
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Long id;
		public String username;
		public String phone;
		public String realName;
		public String openId;
		public Integer registType;

		public ShiroUser(Long id, String username, String phone, String realName, String openId, Integer registType) throws UnsupportedEncodingException {
			this.id = id;
			this.username = username;
			this.phone = phone;
			this.openId = openId;
			this.registType = registType;
			this.realName = URLDecoder.decode(realName, "utf-8");
		}

		public String getPhone() {
			return phone;
		}

		public String getRealName() throws UnsupportedEncodingException {
			return realName;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return username;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(username);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (username == null) {
				if (other.username != null) {
					return false;
				}
			} else if (!username.equals(other.username)) {
				return false;
			}
			return true;
		}
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}

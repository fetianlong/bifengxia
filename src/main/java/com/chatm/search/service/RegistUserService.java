package com.chatm.search.service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.chatm.search.dao.RegistUserDao;
import com.chatm.search.model.RegistUser;
import com.chatm.search.util.UserUtils;

@Component
@Transactional
public class RegistUserService {
//	protected static final Log LOG = LogFactory.getLog(RegistUserService.class);

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(RegistUserService.class);

	@Autowired
	RegistUserDao registUserDao;
	
	
	public int deleteByPrimaryKey(Long id) {
		return registUserDao.deleteByPrimaryKey(id);
	}

	public int insert(RegistUser record) {
		if(null != record.getPassword()){
			entryptPassword(record);
		}
		return registUserDao.insert(record);
	};

	public int insertSelective(RegistUser record) {
		if(null != record.getPassword()){
			entryptPassword(record);
		}
		return registUserDao.insertSelective(record);
	}

	public RegistUser selectByPrimaryKey(Long id) {
		return registUserDao.selectByPrimaryKey(id);
	};

	public int updateByPrimaryKeySelective(RegistUser record) {
		if(null != record.getPassword()){
			entryptPassword(record);
		}
		return registUserDao.updateByPrimaryKeySelective(record);
	};

	public int updateByPrimaryKey(RegistUser record) {
		if(null != record.getPassword()){
			entryptPassword(record);
		}
		return registUserDao.updateByPrimaryKey(record);
	}

	/**
	 * 根据用户名查询当前用户
	 * @param username
	 * @return
	 * @description
	 * @version currentVersion
	 * @author pujianhua
	 * @createtime 2016年3月24日 下午5:55:45
	 */
	public RegistUser selectRegistUserByUserName(String username) {
		return registUserDao.selectRegistUserByUserName(username);
	};

	public RegistUser selectRegistUserByMobile(String mobile) {
		return registUserDao.selectRegistUserByMobile(mobile);
	};
	
	public RegistUser selectRegistUserByEmail(String email) {
		return registUserDao.selectRegistUserByEmail(email);
	};

	public boolean isExistByPhone(String mobile) {
		RegistUser registUser = selectRegistUserByMobile(mobile);
		if (registUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isExistByEmail(String email) {
		email = email.toLowerCase();
		RegistUser registUser = selectRegistUserByEmail(email);
		if (registUser != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isExistByUsername(String username) {
		RegistUser registUser = selectRegistUserByUserName(username);
		if (registUser != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 加密 
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * @param user
	 * @description
	 * @version currentVersion
	 * @author pujianhua
	 * @createtime 2016年3月29日 下午4:39:10
	 */
	public void entryptPassword(RegistUser user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
		
		
	}

	/**
	 * 自动注册 0 成功 1 不成功 2 已存在
	 * @param myMobilephone
	 * @param IP
	 * @return
	 */
	public int regRegistUserSelf(String  myMobilephone, String IP) {
		try {
			if (!UserUtils.isMobileNO(myMobilephone)) {	//判断当前手机号是否合法，不合法返回1
				return 1;
			}else if (this.isExistByPhone(myMobilephone)) {	//先查是否已存在该用户，已存在返回2
				return 2;
			}else{	//手机号合法并且没有被注册过，可以注册
				RegistUser registUser = new RegistUser();
				registUser.setPhone(myMobilephone);
				registUser.setUserName(myMobilephone);
				registUser.setPassword(myMobilephone);
				registUser.setPlainPassword(myMobilephone);
				registUser.setRegistTime(new Date(System.currentTimeMillis()));
				int i = this.insert(registUser);
				if (i!=1) {
					//发送通知注册成功短信
//					Ronglian.sendMsg(myMobilephone,EnumSmsType.REGISTEREMIND, new String[]{myMobilephone});
					return 1;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	public boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	
	/**
	 * 通过用户id查email
	 * @return
	 */
	public String EmailById(String user_id){
		String email=registUserDao.EmailById(user_id);
		return  email;
	}

	/**
	 * 根据用户唯一标识查询是否存在此用户
	 * @param useropenId
	 * @return
	 */
	public RegistUser selectByUniqueKey(String uniqueKey) {
		
		return registUserDao.selectByUniqueKey(uniqueKey);
	}
}
package com.chartTmSearch.quickstart;

import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class Md5Test {

	public static void main(String[] args) throws Exception {
		
		String password = "98c40902c61bbc26";
//		System.out.println(encrypt(password));
//		getPassWordBySalt(salt, password);
//		Date a = new Date();
		
//		SimpleDateFormat smd = new SimpleDateFormat("yyyy年MM月dd日");
//		String dat = smd.format(a);
//		System.out.println(dat);
//		getSaltAndPassWord(password);
		
//		String s = "2017071404370200599531";
//		String tno = s.substring(s.length()-8, s.length());
//		System.out.println(tno);
		
//		String s = "170.00";
//		if(s.lastIndexOf(".") != 0) {
//			
//			String tno = s.substring(0, s.lastIndexOf("."));
//			System.out.println(tno);
//			
//		}
		
		
/*		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String dd = df.format(d);
		Date d3 = df.parse(dd);

		String d1 = "2016-12-30 00:00:00";
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		Date d2 = df.parse(d1);
		if (d3.getTime() <= d2.getTime()) {
			System.out.println("当前 的时间d3: " + d3.getTime() + " <= 预计的时间d2： " + d2.getTime());
		}else {
			System.out.println("当前 的时间d3: " + d3.getTime() + " > 预计的时间： " + d2.getTime());

		}*/
		/*int max=20;
		int min=10;
		Random random = new Random();

		int s = random.nextInt(max)%(max-min+1) + min;
		System.out.println(s);*/

		Random rad = new Random();
		String i = rad.nextInt(1000)+"";
		System.out.println(i);
		/*Long sorders = 5L;
		String soIds = "";
		String oid = "20161228221803855";
		String oid0 = "2017091222370679986458";
		Long id = Long.valueOf(oid);
		for (int i = 0; i < sorders.intValue(); i++) {
			long id1 = id + i;
			System.out.println(id1);
			soIds += "s" + id1  + ",";
		}
		System.out.println(soIds);
		String soid = soIds.substring(0,soIds.length()-1);
		System.out.println(soid);*/
	}
	
	/**
	 * 生成随机盐值并根据密码加密
	 * @param password  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:51:37
	 */
	public static void getSaltAndPassWord(String password){
		/*
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
		
		*/
		byte[] salt = Digests.generateSalt(8);
		String s = Encodes.encodeHex(salt);
		System.out.println("新盐值：" + s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}
	
	/**
	 * 根据盐值和密码进行加密
	 * @param password
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:52:15
	 */
	public static void getPassWordBySalt(String s, String password){
		System.out.println("老盐值：" + s);
		byte[] salt = Encodes.decodeHex(s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}

	 //将传进来密码加密方法  
    private static String encrypt(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//加密后的字符串
//        String newstr = Md5.getInstance().encode(str);
		return null;
//		return newstr;
//        Md5Hash sha384Hex = new Md5Hash(data);//这里可以选择自己的密码验证方式 比如 md5或者sha256等  
//        return sha384Hex;  
    }
}

package com.chatm.search.util;

import java.util.Properties;

/**
 * 常理静态化
 * @description   
 * @version currentVersion(1.0)  
 * @author pjh  
 * @createtime 2017年5月16日 下午6:54:24
 */
public class Constants {

    public static String orderUser;
    public static String orderPW;
    public static String ticketUrl;
    public static String ticketAccount;
    public static String ticketPassword;
    public static String imgsPath;
    public static String gwadd;

    /**智游宝接口属性**/
    public static String userName;
    public static String corpCode;
    public static String secretkey;
    public static String zhiyouURL;
    public static String paymethod;

    static {
        Properties props = new Properties();
        try {
            props.load(Constants.class.getResourceAsStream("/config.properties"));
            
            orderUser = props.getProperty("orderUser");
            orderPW = props.getProperty("orderPW");
            ticketUrl = props.getProperty("ticketUrl");
            ticketAccount = props.getProperty("ticketAccount");
            ticketPassword = props.getProperty("ticketPassword");
            imgsPath = props.getProperty("imgsPath");
            gwadd = props.getProperty("gwadd");

            /**智游宝接口属性**/
            userName = props.getProperty("userName");
            corpCode = props.getProperty("corpCode");
            secretkey = props.getProperty("secretkey");
            zhiyouURL = props.getProperty("zhiyouURL");
            zhiyouURL = props.getProperty("zhiyouURL");
            paymethod = props.getProperty("paymethod");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * 判断系统 true:win / false:linux
     * @return
     * @description
     * @version currentVersion
     * @author yt-tangyj
     * @createtime 2017年4月25日 上午11:23:22
     */
    public static boolean isWindows(){
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os.startsWith("win") || os.startsWith("Win")) {
            return true;
        }else {
            return false;
        }
    }
}

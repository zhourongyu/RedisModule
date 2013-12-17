package util;

public class KeyMapper {
	//用户自增长数
	public static String ACCOUNT_USER_COUNT = "account:usercount";
	//用户列表(set)
	public static String ACCOUNT_USER_LIST = "account:userlist";
	//用户email对应ID
	public static String ACCOUNT_EMAIL_ID = "account:email:id";
	
	//用户信息key
	public static String USER_INFO_EMAIL = "user:uid:email";
	public static String USER_INFO_NICKNAME = "user:uid:nickname";
	public static String USER_INFO_PWD = "user:uid:password";
	public static String USER_INFO_SEX = "user:uid:sex";
	public static String USER_INFO_DESC = "user:uid:description";
	public static String USER_INFO_LOGIN_STATUS = "user:uid:login";
	
	//过期时间
	public static int KEY_EXPIRE_TIME = 1800;
	
}

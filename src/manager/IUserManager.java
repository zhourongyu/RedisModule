package manager;

import java.util.HashMap;

import net.sf.json.JSONObject;
import bean.User;

public interface IUserManager {
	
	//用户登陆
	public HashMap<String,String> login(User user) throws Exception;
	
	//登出
	public void logout(String uid) throws Exception;
	
	//用户注册
	public void registUser(User user) throws Exception;
	
	//用户登陆状态
	public String loginStatus(String uid) throws Exception;
	
	//获取用户信息
	public HashMap<String,String> getUserInfo(String uid) throws Exception;
	
	//更新用户信息
	public JSONObject updateUserInfo(User user) throws Exception;
}

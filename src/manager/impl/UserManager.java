package manager.impl;

import java.util.HashMap;

import bean.User;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import util.JedisUtils;
import util.KeyMapper;
import manager.IUserManager;

public class UserManager implements IUserManager{
	private Jedis jedis = null;

	@Override
	public HashMap<String,String> login(User user) throws Exception{
		// TODO Auto-generated method stub
		HashMap<String,String> hm = new HashMap<String,String>();
		try{
			jedis = JedisUtils.getInstance().getJedis();
			String key =  KeyMapper.ACCOUNT_EMAIL_ID.replace("email", user.getEmail());
			String uid = jedis.get(key);
			String flag = "0";
			String message = null;
			if(uid != null){
				String password = KeyMapper.USER_INFO_PWD.replace("uid",uid);
				String email = KeyMapper.USER_INFO_EMAIL.replace("uid",uid);
				String pwd = jedis.get(password);
				String e = jedis.get(email);
				if(e.equals(user.getEmail()) && pwd.equals(user.getPassword())){
					String k = KeyMapper.USER_INFO_LOGIN_STATUS.replace("uid", uid);
					jedis.getSet(k,"1");  
					jedis.expire(k, KeyMapper.KEY_EXPIRE_TIME);
					flag = "1";
					String url = "userInfo?uid="+uid;
					hm.put("url", url);
				}else{
					message = "用户名或密码错误!";
				}
			}else{
				message = "用户不存在!";
			}
			hm.put("message", message);
			hm.put("flag", flag);
		}finally  
        {  
			JedisUtils.getInstance().returnJedis(jedis);
		}  
		return hm;
	}

	@Override
	public HashMap<String, String> getUserInfo(String uid) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,String> hm = new HashMap<String,String>();
		try{
			jedis = JedisUtils.getInstance().getJedis();
			String email = KeyMapper.USER_INFO_EMAIL.replace("uid", uid);
			String nickname = KeyMapper.USER_INFO_NICKNAME.replace("uid", uid);
			String sex = KeyMapper.USER_INFO_SEX.replace("uid", uid);
			String desc = KeyMapper.USER_INFO_DESC.replace("uid", uid);
			
			hm.put("email", jedis.get(email));
			hm.put("nickname", jedis.get(nickname));
			hm.put("sex", jedis.get(sex));
			hm.put("desc", jedis.get(desc));
		}finally  
        {  
			JedisUtils.getInstance().returnJedis(jedis);
		}  
		return hm;
	}

	@Override
	public JSONObject updateUserInfo(User user) throws Exception {
		// TODO Auto-generated method stub
		String uid = user.getUid();
		try{
			jedis = JedisUtils.getInstance().getJedis();
			JSONObject data = new JSONObject();
			String message = null;
			String url = null;
			if("1".equals(loginStatus(uid))){
				String nickname = KeyMapper.USER_INFO_NICKNAME.replace("uid", uid);
				String desc = KeyMapper.USER_INFO_DESC.replace("uid", uid);
				jedis.getSet(nickname, user.getNickname());
				jedis.getSet(desc, user.getDescription());
				message = "更新成功!";
				url = "userInfo?uid="+uid;
			}else{
				message = "登陆失效!";
				url = "signin.html";
			}
			data.put("url", url);
			data.put("message", message);
			return data;
		}finally  
        {  
			JedisUtils.getInstance().returnJedis(jedis);
		}  
		
	}

	@Override
	public String loginStatus(String uid) throws Exception {
		// TODO Auto-generated method stub
		String status = null;
		try{
			jedis = JedisUtils.getInstance().getJedis();
			String key = KeyMapper.USER_INFO_LOGIN_STATUS.replace("uid", uid);
			status = jedis.get(key);
		}finally  
        {  
			JedisUtils.getInstance().returnJedis(jedis);
		} 
		return status;
	}

	@Override
	public void registUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try{
			//生成用户UID
			jedis = JedisUtils.getInstance().getJedis();
			String uid = String.valueOf(jedis.incr(KeyMapper.ACCOUNT_USER_COUNT));
			
			String email = KeyMapper.USER_INFO_EMAIL.replace("uid", uid);
			String password = KeyMapper.USER_INFO_PWD.replace("uid", uid);
			String nickname = KeyMapper.USER_INFO_NICKNAME.replace("uid", uid);
			String sex = KeyMapper.USER_INFO_SEX.replace("uid", uid);
			String desc = KeyMapper.USER_INFO_DESC.replace("uid", uid);
			
			//增加用户UID
			String accountkey = KeyMapper.ACCOUNT_EMAIL_ID.replace("email", user.getEmail());
			jedis.set(accountkey, uid);
			//添加到用户列表
			jedis.sadd(KeyMapper.ACCOUNT_USER_LIST, uid);
			
			//新增用户信息
			jedis.set(email,user.getEmail());
			jedis.set(password, user.getPassword());
			jedis.set(nickname, user.getNickname());
			jedis.set(sex, user.getSex());
			jedis.set(desc, user.getDescription());
			
		}finally  
        {  
			JedisUtils.getInstance().returnJedis(jedis);
		} 
	}

	@Override
	public void logout(String uid) throws Exception {
		// TODO Auto-generated method stub
		String key = KeyMapper.USER_INFO_LOGIN_STATUS.replace("uid", uid);
		try{
			//生成用户UID
			jedis = JedisUtils.getInstance().getJedis();
			jedis.getSet(key, "0");
		}finally  
        {  
			JedisUtils.getInstance().returnJedis(jedis);
		} 	
	}


	
}

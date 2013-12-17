package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisUtils {

	private static JedisPool jedisPool = null;
	
	private static final JedisUtils jedisUtil = new JedisUtils();
	
	private JedisUtils() {}

	static {
		if (jedisPool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(10);
			config.setMaxIdle(2);
			config.setMaxWait(10001);
			// config.setTestOnBorrow(JRedisPoolConfig.TEST_ON_BORROW);
			// config.setTestOnReturn(JRedisPoolConfig.TEST_ON_RETURN);
			jedisPool = new JedisPool(config, "localhost", 6379);
		}
	}

	public JedisPool getPool() {
		return jedisPool;
	}

	/**
	 * 从jedis连接池中获取获取jedis对象
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		try {  
			return jedisPool.getResource(); 
        } catch (Exception e) {  
            throw new JedisConnectionException(  
                    "Could not get a resource from the pool", e);  
        }  
	}

	/**
	 * 获取JedisUtil实例
	 * 
	 * @return
	 */
	public synchronized static JedisUtils getInstance() {
		return jedisUtil;
	}

	/**
	 * 回收jedis
	 * 
	 * @param jedis
	 */
	public void returnJedis(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

}

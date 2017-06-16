package redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		RedisTest redis = new RedisTest();
		redis.RedisConnectionTest(jedis);
		redis.RedisKeyTest(jedis);
		redis.RedisListTest(jedis);
		redis.RedisStringTest(jedis);
		redis.RedisSetTest(jedis);
	}

	// 查看服务是否运行
	public void RedisConnectionTest(Jedis jedis) {
		System.out.println("Server is running: " + jedis.ping());
	}

	public void RedisStringTest(Jedis jedis) {
		jedis.set("runoobkey", "Redis tutorial");
		// 获取存储的数据并输出
		System.out.println("Stored string in redis:: " + jedis.get("runoobkey"));
	}
	public void RedisSetTest(Jedis jedis){
		Set<String> sets = jedis.smembers("setsass");
		Iterator<String> it =sets.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	public void RedisListTest(Jedis jedis) {
		jedis.lpush("tutorial-list", "Redis");
		jedis.lpush("tutorial-list", "Mongodb");
		jedis.lpush("tutorial-list", "Mysql");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("tutorial-list", 0, 5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Stored string in redis:: " + list.get(i));
		}
	}

	public void RedisKeyTest(Jedis jedis) {
		Set<String> keys = jedis.keys("setsass");
		System.out.println("keys.size=" + keys.size());
		Iterator<String> it = keys.iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println("key=" + key);
		}
	}

}
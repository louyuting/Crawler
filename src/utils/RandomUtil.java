package utils;

import java.util.Random;

import org.junit.Test;

public class RandomUtil {

	private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 获取9位数字的随机数
	 * @return
	 */
	public static long getRandomNumber() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 9; i++) {
			sb.append(chars[new Random().nextInt(10)]);
			System.out.println(sb.toString());
		}
		return Integer.valueOf(sb.toString());
	}
	
	//测试
	@Test
	public void test() {
		long result = getRandomNumber();
		System.out.println(result);
	}
	
	
}

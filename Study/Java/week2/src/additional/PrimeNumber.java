/*
 *  11.编写一个方法来计算10 000以内的素数之和并输出。
 * 
 */
package additional;

public class PrimeNumber {

	public static void main(String[] args) {

		final long NUM_GIVEN_ONE = 1000;
		final long NUM_GIVEN_TWO = 10000;

		getSumOfPrimeNumberWithin(NUM_GIVEN_ONE);
		getSumOfPrimeNumberWithin(NUM_GIVEN_TWO);

	}

	static void getSumOfPrimeNumberWithin(long givenN) {
		boolean flag = false;// 设置标志，若是素数则为0，反之为1
		int sum = 0;
		System.out.println("10000以内的素数有： ");

		for (int num = 2; num <= givenN; num++, flag = false) {
			for (int i = 2; i <= Math.sqrt(num); i++)// 注意Math中m大写
			{
				if (num % i == 0) {
					flag = true;// 不是素数
					break;
				}
			}
			if (flag == false) // 是素数
			{
				System.out.print(num + ",");
				sum += num;
			}
		}
		System.out.println("输出完了!");
		System.out.println(givenN + "以内的素数之和为： " + sum);
	}
}

/*
 *  20.创建一个简单的成绩单程序，帮助老师评估学生的表现。
 *  该程序用double数组存放成绩来计算平均成绩和标准方差。
 *  成绩通过键盘输入字符串，然后转换成double类型的数据。
 * 
 */
package additional;

import java.util.Arrays;

public class Assessment {

	public static void main(String[] args) {
		double[][] scores = { { 90, 95, 100 }, { 90, 95, 100 }, { 90, 95, 100 }, { 90, 95, 100 }, { 88, 83, 93 },
				{ 88, 83, 99 }, { 88, 83, 98 }, { 88, 73, 97 }, { 98, 83, 97 }, { 88, 83, 99 } };
//		double[][] scores = new double[3][3];
//		System.out.println(scores.length);
//		System.out.println(scores[1].length);
//
//		Scanner scanner = new Scanner(System.in);

		double math;
		double english;
		double java;

		// 输入数据并装入数组
//		for (int i = 0; i < scores.length; i++) {
//			System.out.println("请依次输入第 " + (i + 1) + " 个学生的三科成绩：");
//			math = scanner.nextDouble();
//			english = scanner.nextDouble();
//			java = scanner.nextDouble();
//			scores[i] = new double[] { math, english, java };
//		}

		System.out.println("学生成绩列表如下：");
		// Array, java 平台提供的处理数组的工具类
		System.out.println(Arrays.deepToString(scores));

		System.out.println("学生成绩分析如下：\n");
		System.out.println("Math：");
		System.out.print("平均：" + averageSingle(scores, 0));
		System.out.println(";  方差：" + varianceSingle(scores, 0) + "\n");

		System.out.println("Englsih：");
		System.out.print("平均：" + averageSingle(scores, 1));
		System.out.println(";  方差：" + varianceSingle(scores, 1) + "\n");

		System.out.println("Java：");
		System.out.print("平均：" + averageSingle(scores, 2));
		System.out.println(";  方差：" + varianceSingle(scores, 2) + "\n");

		System.out.println("总成绩：");
		System.out.print("平均：" + averageAll(scores));
		System.out.println(";  方差：" + varianceAll(scores) + "\n");
	}

	// 单科总分
	static double sumSingle(double[][] scores, int subject) {
		double sum = 0.0;
		for (int i = 0; i < scores.length; i++) {
			sum = sum + scores[i][subject];
		}
		return sum;
	}

	// 总分
	static double sumAll(double[][] scores) {
		double sum = 0.0;
		for (int i = 0; i < scores.length; i++)
			for (int j = 0; j < scores[i].length; j++)
				sum += scores[i][j];
		return sum;
	}

	// 单科平均分
	static double averageSingle(double[][] scores, int subject) {
		double avg = 0.0;
		avg = sumSingle(scores, subject) / scores.length;
		return avg;
	}

	// 平均总分
	static double averageAll(double[][] scores) {
		double avg = 0.0;
		avg = sumAll(scores) / scores.length;
		return avg;
	}

	// 单科方差
	static double varianceSingle(double[][] scores, int subject) {
		double avg = averageSingle(scores, subject);
		double sum = 0.0;
		// 求各单科成绩减去均值后的平方和
		for (int i = 0; i < scores.length; i++) {
			sum = sum + Math.pow((scores[i][subject] - avg), 2);
		}
		// 除以n
		return sum / scores.length;

	}

	// 总分方差
	static double varianceAll(double[][] scores) {
		double avg = averageAll(scores);
		double sum = 0.0;
		double indivSum;
		// 求各单科成绩减去均值后的平方和
		for (int i = 0; i < scores.length; i++) {
			// 计算每个学生的总分
			indivSum = 0.0;
			for (int j = 0; j < scores[i].length; j++)
				indivSum += scores[i][j];
			// 累加均差平方
			sum += Math.pow((indivSum - avg), 2);
		}
		// 除以n，得方差
		return sum / scores.length;
	}

}

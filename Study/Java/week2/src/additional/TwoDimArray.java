package additional;

public class TwoDimArray {

	static double[][] array = { { 1.0, 2.0, 3.0, 4.0 }, { 5.0, 6.0, 7.0, 8.0 }, { 12.0, 11.0, 10.0, 9.0 },
			{ 16.0, 15.0, 14.0, 13.0 } };

	static int row = 2, column = 2;
	double max;

	// 显示数组
	public static void outputArray(double[][] arrayname) {
		// 注意： 无返回值也要写返回类型
		System.out.println("该数组的元素如下:");
		for (int i = 0; i < arrayname.length; i++) {
			for (int j = 0; j < arrayname[i].length; j++)
				System.out.print(arrayname[i][j] + " ");
			System.out.println();
		}

	}

	// 计算给定行之和
	public static double sumOfRow(double[][] arrayname, int row) {
		double sum = 0.0;
		for (int j = 0; j < arrayname[row].length; j++)
			sum += arrayname[row][j];
		return sum;
	}

	// 计算给定列之和
	public static double sumOfColumn(double[][] arrayname, int column) {
		double sum = 0.0;
		for (int i = 0; i < arrayname.length; i++)
			sum += arrayname[i][column];
		return sum;
	}

	// 计算主对角线之和
	public static double sumOfDiagnoalPos(double[][] arrayname) {
		double sum = 0.0;
		for (int i = 0; i < arrayname.length; i++)
			sum += arrayname[i][i];
		return sum;
	}

	// 计算副对角线之和
	public static double sumOfDiagnoalNeg(double[][] arrayname) {
		double sum = 0.0;
		for (int j = 0; j < arrayname[row].length; j++)
			sum += arrayname[j][arrayname.length - j - 1];
		return sum;
	}

	// 求整个数组的最大值
	public static double getMax(double[][] arrayname) {
		double max = 0.0;
		for (int i = 0; i < arrayname.length; i++)
			for (int j = 0; j < arrayname[i].length; j++)
				if (max < arrayname[i][j])
					max = arrayname[i][j];
		return max;

	}

	public static void main(String args[]) {
		outputArray(array);
		System.out.println("第" + (row + 1) + "行元素之和为:" + sumOfRow(array, row));
		System.out.println("第" + (column + 1) + "列元素之和为:" + sumOfColumn(array, column));
		System.out.println("主对角线元素之和为： " + sumOfDiagnoalPos(array));
		System.out.println("副对角线元素之和为： " + sumOfDiagnoalNeg(array));
		System.out.println("该数组中的最大值是" + getMax(array));
	}
}

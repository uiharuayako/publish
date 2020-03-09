/* 
 * 10. 编写一个方法来计算正方形的面积和周长。
 * 
 */

package additional;

public class Area {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double sideLength = 10.0;

		System.out.print("边长为 " + sideLength + " 的正方形面积是：");
		System.out.println(getSquareArea(sideLength));

	}

	// 求正方形面积的方法
	static double getSquareArea(double side) {
		return side * side;
	}
}

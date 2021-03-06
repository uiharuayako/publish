class InnerClassDemo {
    public static void main(String[] args) {

        RectangleR rect1 = new RectangleR();
        RectangleR rect2 = new RectangleR();
        rect1.setRectangle(10, 20);
        rect2.setRectangle(10, 30);
        System.out.println("矩形1的面积为：" + rect1.area());
        System.out.println("矩形2的面积为：" + rect2.area());
    }

    private static class RectangleR {
        double width, height;

        double area() {
            return width * height;
        }

        void setRectangle(double w, double l) {
            width = w;
            height = l;
        }
    }
}
	


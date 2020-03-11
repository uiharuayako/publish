class X {
	static int x;

	X(int x) {
		this.x = x;
		System.out.println("the member x is " + x);
	}

	static int x() {
		return x;
	}

	;

	public static void main(String[] args) {

		{
			int x = 10;   //±ÿ–Î≥ı ºªØ
			System.out.println("the inner x= " + x);
		}
		;
		System.out.println("the member x= " + x);
		try {
			x = x / x();
		} catch (Exception x) {
			System.out.println("the Exception x is " + x.toString());//
		}
		X x = new X(1);
		System.out.println("the x is " + x);
	}
}

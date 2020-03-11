class Alpha {
	private int privateVar;

	Alpha(int _privateVar) {           //构造方法
		privateVar = _privateVar;
	}

	boolean isEqualTo(Alpha anotherAlpha) {
		if (this.privateVar == anotherAlpha.privateVar)
			return true;
		else
			return false;
	}
}

public class Beta {
	public static void main(String args[]) {
		Alpha a = new Alpha(1);
		Alpha b = new Alpha(2);
		System.out.println(a.isEqualTo(b));
	}
}

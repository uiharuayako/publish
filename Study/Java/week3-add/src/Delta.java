package Latin;

import Greek.*;

class Delta extends Alpha {
	public static void main(String args[]) {
		Delta d = new Delta();
		d.accessMethod();
	}

	void accessMethod() {
		protectedVar = 66; // 非法
//.protectedVar = 10; // 合法
		protectedMethod(); // 非法
//d.protectedMethod(); // 合法
	}
}

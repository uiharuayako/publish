package Latin;

import Greek.*;

class Delta extends Alpha {
	public static void main(String args[]) {
		Delta d = new Delta();
		d.accessMethod();
	}

	void accessMethod() {
		protectedVar = 66; // �Ƿ�
//.protectedVar = 10; // �Ϸ�
		protectedMethod(); // �Ƿ�
//d.protectedMethod(); // �Ϸ�
	}
}

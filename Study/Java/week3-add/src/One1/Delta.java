package Latin;

import Greek.*;

public class Delta extends Greek.Alpha {
    void accessMethod(Alpha a, Delta d) {
//	void accessMethod() {
//Alpha a = new Alpha();
//a.iamprotected = 10; // �Ϸ�
//a.protectedMethod(); // �Ϸ�

//a.iamprotected = 10; // �Ƿ�
        d.iamprotected = 10; // �Ϸ�
//a.protectedMethod(); // �Ƿ�
        d.protectedMethod(); // �Ϸ�
    }
}

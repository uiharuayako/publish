package Latin;

import Greek.*;

public class Delta extends Greek.Alpha {
    void accessMethod(Alpha a, Delta d) {
//	void accessMethod() {
//Alpha a = new Alpha();
//a.iamprotected = 10; // 合法
//a.protectedMethod(); // 合法

//a.iamprotected = 10; // 非法
        d.iamprotected = 10; // 合法
//a.protectedMethod(); // 非法
        d.protectedMethod(); // 合法
    }
}

package com;
/*作业要求：Java有哪些基本数据类型？描述其分类情况，编写一个简单的程序，各声明一个变量，初始化并输出其值。*/
/*
答案：Java语言提供了八种基本类型。六种数字类型（四个整数型，两个浮点型），一种字符类型，还有一种布尔型。

byte：
    byte 数据类型是8位、有符号的，以二进制补码表示的整数；
    最小值是 -128（-2^7）；
    最大值是 127（2^7-1）；
    默认值是 0；
    byte 类型用在大型数组中节约空间，主要代替整数，因为 byte 变量占用的空间只有 int 类型的四分之一；
    例子：byte a = 100，byte b = -50。
*/
/*
short：

    short 数据类型是 16 位、有符号的以二进制补码表示的整数
    最小值是 -32768（-2^15）；
    最大值是 32767（2^15 - 1）；
    Short 数据类型也可以像 byte 那样节省空间。一个short变量是int型变量所占空间的二分之一；
    默认值是 0；
    例子：short s = 1000，short r = -20000。
*/
/*
int：

    int 数据类型是32位、有符号的以二进制补码表示的整数；
    最小值是 -2,147,483,648（-2^31）；
    最大值是 2,147,483,647（2^31 - 1）；
    一般地整型变量默认为 int 类型；
    默认值是 0 ；
    例子：int a = 100000, int b = -200000。
*/
/*
long：

    long 数据类型是 64 位、有符号的以二进制补码表示的整数；
    最小值是 -9,223,372,036,854,775,808（-2^63）；
    最大值是 9,223,372,036,854,775,807（2^63 -1）；
    这种类型主要使用在需要比较大整数的系统上；
    默认值是 0L；
    例子： long a = 100000L，Long b = -200000L。
    "L"理论上不分大小写，但是若写成"l"容易与数字"1"混淆，不容易分辩。所以最好大写。
*/
/*
float：

    float 数据类型是单精度、32位、符合IEEE 754标准的浮点数；
    float 在储存大型浮点数组的时候可节省内存空间；
    默认值是 0.0f；
    浮点数不能用来表示精确的值，如货币；
    例子：float f1 = 234.5f。
*/
/*
double：

    double 数据类型是双精度、64 位、符合IEEE 754标准的浮点数；
    浮点数的默认类型为double类型；
    double类型同样不能表示精确的值，如货币；
    默认值是 0.0d；
    例子：double d1 = 123.4。
*/
/*
boolean：

    boolean数据类型表示一位的信息；
    只有两个取值：true 和 false；
    这种类型只作为一种标志来记录 true/false 情况；
    默认值是 false；
    例子：boolean one = true。
*/
/*
char：

    char类型是一个单一的 16 位 Unicode 字符；
    最小值是 \u0000（即为0）；
    最大值是 \uffff（即为65,535）；
    char 数据类型可以储存任何字符；
    例子：char letter = 'A';。

*/
public class homework1 {
    public static void main(final String[] args) {
        byte b=127;
        System.out.println("byte b是"+b);
        short s=32767;
        System.out.println("short s是"+s);
        int i=-12345;
        System.out.println("int i是"+i);
        long l=546456456;
        System.out.println("long l是"+l);
        float f=123.456f;
        System.out.println("float f是"+f);
        double d=13.531;
        System.out.println("double d是"+d);
        boolean bool=true;
        System.out.println("boolean bool是"+bool);
        char letter = 'A';
        System.out.println("char letter是"+letter);
    }
}

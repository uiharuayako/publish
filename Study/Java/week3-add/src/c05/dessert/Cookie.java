//: c05:dessert:Cookie.java

package c05.dessert;

//
public class Cookie {      //此处的public不能少
    public Cookie() {
        System.out.println("Cookie constructor");
    }

    //public bite如果声明为protected则可被子类使用
    protected void bite() {
        System.out.println("bite");
    }
}  // protected 
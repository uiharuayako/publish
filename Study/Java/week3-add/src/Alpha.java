//package Greek;
public class Alpha {
    //private
    int privateVar;

    Alpha(int x) {
        privateVar = x;
    }

    public static void main(String args[]) {
        Alpha a1 = new Alpha(5);
        Alpha a2 = new Alpha(5);
        if (a1.isEqualTo(a2))
            System.out.println("yes");
        else
            System.out.println("no");

//a.privateVar = 66; // 非法
//a.privateMethod(); // 非法
    }

    //private
    void privateMethod() {
        System.out.println("privateMethod print privateVarialbe: " + privateVar);
    }

    boolean isEqualTo(Alpha anotherAlpha) {
        if (this.privateVar == anotherAlpha.privateVar)
            return true;
        else
            return false;
    }

}

/*
class Beta {
void accessMethod() {
Alpha a = new Alpha();
a.privateVar = 66; // 非法
a.privateMethod(); // 非法
}
}

*/

class People {
    String head, foot, hand;

    void speak(String s) {
        System.out.println(s);
    }
}

public class One {
    public static void main(String args[]) {
        People LiuBei = new People();
        LiuBei.head = "big";
        LiuBei.hand = "long";
        LiuBei.foot = "short"; //用算符"."使用该对象的成员变量
        LiuBei.speak("I am a " + LiuBei.head + " head, a " + LiuBei.hand + " hand and a " + LiuBei.foot + " foot person");
    }
    //用算符"."使用该对象的方法
}

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
        LiuBei.foot = "short"; //�����"."ʹ�øö���ĳ�Ա����
        LiuBei.speak("I am a " + LiuBei.head + " head, a " + LiuBei.hand + " hand and a " + LiuBei.foot + " foot person");
    }
    //�����"."ʹ�øö���ķ���
}
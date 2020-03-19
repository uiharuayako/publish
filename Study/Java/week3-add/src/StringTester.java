public class StringTester {
    public static void changeStrings(String s, StringBuffer sb) {
        s += " by Definition";
        sb.append(" by Definition");
        sb = null;      //�����ܸı����������
        System.out.println("String in method call: " + s);
        System.out.println("StringBuffer after method call: " + sb);
    }

    public static void main(String args[]) {
        String string = new String("Java");
        StringBuffer buffer = new StringBuffer("Java");
        changeStrings(string, buffer);
        System.out.println("String after method call: " + string);
        System.out.println("StringBuffer after method call: " + buffer);
    }
}
class ThisClass {
    public static void main() {
        ThisClass bank = new ThisClass();
        bank.someMethod(this);
        //bank.someMethod(bank);
    }

    ;

    void someMethod(ThisClass s) {
    }
}

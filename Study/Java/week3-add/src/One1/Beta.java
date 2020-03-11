package Greek;

public class Alpha {
    protected int iamprotected;
    private int iamprivate;

    Alpha(int _iamprivate) {
        iamprivate = _iamprivate;
    }

    protected void protectedMethod() {
        System.out.println("protectedMethod");
    }

    boolean isEqualTo(Alpha anotherAlpha) {
        if (this.iamprivate == anotherAlpha.iamprivate)
            return true;
        else
            return false;
    }
}



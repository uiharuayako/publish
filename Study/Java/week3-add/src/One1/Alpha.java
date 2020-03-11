package Greek;

public class Alpha {
    protected int iamprotected;
    private int iamprivate;

    protected void protectedMethod() {
        System.out.println("protectedMethod");
    }
//Alpha(int _iamprivate){
//	iamprivate=_iamprivate;
//}

    boolean isEqualTo(Alpha anotherAlpha) {
        if (this.iamprivate == anotherAlpha.iamprivate)
            return true;
        else
            return false;
    }
}



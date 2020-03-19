package College;

public abstract class College {
    String collegeName;
    String collegeLevel;
    public College(String collegeName,String collegeLevel){
        this.collegeName=collegeName;
        this.collegeLevel=collegeLevel;
    }
    public abstract void introduce();
}

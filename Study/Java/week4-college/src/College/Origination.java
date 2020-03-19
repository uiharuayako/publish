package College;

public abstract class Origination extends College{
    String originationName;//组织名称
    String leader;//领导人
    public Origination(String collegeName, String collegeLevel,String originationName,String leader) {
        super(collegeName, collegeLevel);
        this.originationName=originationName;
        this.leader=leader;
    }
    abstract void work();

    @Override
    public void introduce() {
        System.out.println(originationName+"的领导人为"+leader);
        work();
        System.out.println("\n");
    }
}

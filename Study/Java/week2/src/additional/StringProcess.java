//计算一个给定句子中单词个数和单词平均数
package additional;
public class StringProcess {
    public static void main(String[] args){
        StringBuilder Sentence=new StringBuilder("Java is an object oriented programming language");
        System.out.println(Sentence);
        int num=1;
        int len=Sentence.length();
        for(;Sentence.indexOf(" ")!=-1;num++) {
            Sentence.replace(0,Sentence.indexOf(" ")+1,"");
            System.out.println(Sentence);//这步没必要，但是写出来挺好看！
        }
        System.out.println("一共有"+num+"个词，平均长度为："+(double)(len-num+1)/num);
    }
}

class FinallyWithBreakDemo{
  public static void main(String args[]) {
    for(;;){
   
	try{
       System.out.println("������break�жϣ�Ҫ�˳�ѭ����!");
      return;// break
    }finally{
       System.out.println("����finally����Ҫ��ִ�е���");
    }

	}
  }
}

class FinallyWithBreakDemo{
  public static void main(String args[]) {
    for(;;){
   
	try{
       System.out.println("即将被break中断，要退出循环了!");
      return;// break
    }finally{
       System.out.println("但是finally块总要被执行到！");
    }

	}
  }
}

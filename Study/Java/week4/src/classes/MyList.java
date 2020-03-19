package classes;


public class MyList <T>{
    public class Node <T>{
        private T t;//结点储存的数据
        private Node next;
        public Node(T t,Node next){
            this.t = t;
            this.next = next;
        }//插入一个结点
        public Node(T t){
            this(t,null);
        }

        public T getT() {
            return t;
        }
    }//结点类
    private Node head;//头结点
    private int size;//链表长度
    //初始化链表类
    public MyList(){
        head=null;
        size=0;
    }
    //获取第index个节点，头结点为第一个节点
    public Node get(int index){
        Node result=this.head;//结果结点先初始化为这个节点
        for(int i=0;i<index-1;i++) {
            result=result.next;//找到第n个结点，要递归n-1次
        }
        return result;
    }

    //以下方法实现在链表中插入一个结点，插入位置为index，插入之后，新的结点为第index个节点
    public void add(T t,int index){
        //当在头部插入
        Node node = new Node(t);//初始化一个需要插入的节点
        if (index == 1){
            node.next = this.head;//新头结点的下一个是当前头结点
            this.head = node;//更新头结点
        }
        else {
            //当在中间插入
            Node preNode = get(index-1);
            //要插入的结点的下一个结点指向preNode结点的下一个结点
            node.next = preNode.next;
            //preNode的下一个结点指向要插入的结点node
            preNode.next = node;
        }
        if(index>=0){this.size++;}//长度+1
    }

    public void add(T t){
        Node tmp=new Node(t);
        if (size==0) {head=tmp;}
        else {get(size).next=tmp;}
        size++;
    }//假如不指定位置，默认插入到链表尾。第一个结点必须以此方式插入

    //实现删除链表中的指定结点，不使用索引
    public void remove(T t){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        //当头结点不为空且要删除的为头结点时，删除头结点
        //这里调用了Object类的equals方法
        while(head != null && head.t.equals(t)){
            head = head.next;
            this.size--;
        }
        //现已确保头结点不为指定元素
        Node cur = this.head;//生成一个临时结点cur
        while(cur.next != null/*如果cur不为尾结点*/){
            if(cur.next.t.equals(t)/*判断cur的下一个结点是否为指定元素*/){
                this.size--;
                cur.next = cur.next.next;//临时结点的下一个结点为指定元素，执行删除操作：将临时结点的下一个结点指向下下个结点
            }
            else cur = cur.next;//临时结点的下一个结点不为指定元素，此时进入下一个结点
        }
    }
    //删除链表中的结点，使用索引
    public void removeIndex(int index){
        Node temp=get(index-1);//获取指定结点的上一个结点
        temp.next=temp.next.next;//同理
        size--;
    }
    //调试用：打印整个链表
    public void printList(){
        for(int i=1;i<=size;i++){
            System.out.println(get(i).getT());
        }
        System.out.println();
    }
}

#include "Queue.h"

Queue::Queue(int s)
{

	linear = false;//不是链表！
	totSize = s;
	Cur = Head;
	size = 0;
	//队列的长度是s，除了头结点还有s-1个节点，我想在每个节点里都储存数字
	for (int i = 0; i < s-1; i++) {
		Node* tmp = new Node();
		Cur->next = tmp;
		Cur = Cur->next;
	}
	Cur->next = Head;//首尾呼应，形成循环队列
	Rear = Head;//末尾的下一个指向头结点
}

Queue::~Queue()
{
	//析构...析...构...
	Head = nullptr;
	Rear = nullptr;
	Cur = nullptr;
	cout << endl << "析构工作完成！线性表已经销毁，内存空间被释放" << endl;
}

void Queue::add(int num)
{
	if (isFull())//如果满了
	{
		cout << "插入失败，队列已满！请先释放元素！"<<endl;
	}
	else//没满
	{
		Rear->data = num;//入队
		Rear = Rear->next;//改位置
		size++;
	}

}

void Queue::remove()
{
	if (size==0)//如果全空
	{
		cout << "队列全空！请先入队元素！"<<endl;
	}
	else
	{
		size--;
		Head = Head->next;//跳过头结点，虽然不能delete，但是不访问他
	}
}

void Queue::showOff()
{
	cout << "现在生成一个长度为5的队列"<< endl << endl;
	Queue ls(5);//不用类工厂，实在是没必要，就一个子类...
	cout << "分步插入五个元素" << endl;
	for (int i = 0; i < 5; i++)
	{
		ls.add(random(1, 100));
		ls.printList();
	}
	cout << endl << "试图插入第六个元素" << endl;
	ls.add(random(1, 100));
	ls.printList();
	cout << endl << "分步删除四个元素" << endl;
	for (int i = 0; i < 4; i++)
	{
		ls.remove();
		ls.printList();
	}

	cout << endl << "我们再来插入三个元素" << endl;
	ls.add(random(1, 100));
	ls.printList();
	ls.add(random(1, 100));
	ls.printList();
	ls.add(random(1, 100));
	ls.printList();
	cout << endl << "试图删除五个元素" << endl;
	for (int i = 0; i < 5; i++)
	{
		ls.remove();
		ls.printList();
	}
}

#include "Linear.h"

void Linear::go(int index)
{
	Cur = Head;//开始就是第一个节点，头结点位置是1
	for (int i = 0; i < index-1; i++) {
		Cur = Cur->next;
	}
}

Linear::Linear(){
	Head=new NODE();
	size = 0;
}

Linear::~Linear()
{
	if (linear) {
		while (Head->next != nullptr) {
			Cur = Head->next;
			delete Head;
			Head = Cur;
		}
		delete Head;
		cout << endl << "析构工作完成！线性表已经销毁，内存空间被释放" << endl;
	}
	else {
		cout << "已经跳过父类的析构方法"<<endl;
	}
}

void Linear::add(int index, int num)
{
	if (index == 1) {
		Head->data = num;
	}
	else {
		go(index);
		NODE* newNode = new NODE(num);
		newNode->next = Cur->next;
		Cur->next = newNode;
	}
	size++;

}

void Linear::add(int num)
{
	add(size, num);
}

void Linear::remove(int index)
{
	if(index==1){
		//对头结点的操作
		Cur = Head;
		Head = Head->next;
		delete Cur;
	}
	else {
		go(index - 1);
		NODE* tmp = Cur->next;
		Cur->next = Cur->next->next;//跳过下一个节点
		delete tmp;
	}
	size--;
}

void Linear::remove()
{
	remove(size);
}

void Linear::printList()
{
	Cur = Head;
	cout << "现在的链表：";
	if (size == 0) { cout << "为空"<<endl; }
	else {
		for (int i = 0; i < size - 1; i++)
		{
			cout << Cur->data << " ";
			Cur = Cur->next;
		}
		cout << Cur->data << endl << "其长度：" << size << endl;
	}
}

int Linear::getIndexData(int index)
{
	go(index);
	return Cur->data;
}

int Linear::random(int a, int b)
{
	return rand() % (b - a + 1) + a;
}

void Linear::showOff()
{
	int size;
	cout << "希望生成的链表长度：(请输入大于等于5的数字。不然演示会出问题。数据随机填充1到100闭区间的数字）";
	cin >> size;
	cout << endl;
	Linear ls;
	for (int i = 0; i < size; i++) {
		ls.add(Linear::random(1, 100));
	}
	ls.printList();
	cout << "演示插入操作：在第三个结点后插入新结点，其数据为101" << endl;
	ls.add(3, 101);
	ls.printList();
	cout << "演示插入操作：在最后一个结点后插入新结点，其数据为201" << endl;
	ls.add(201);
	ls.printList();
	cout << "演示删除操作：删除第五个元素" << endl;
	ls.remove(5);
	ls.printList();
	cout << "演示删除操作：删除最后一个元素" << endl;
	ls.remove();
	ls.printList();
}




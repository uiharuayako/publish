#include "Queue.h"

Queue::Queue(int s)
{

	linear = false;//��������
	totSize = s;
	Cur = Head;
	size = 0;
	//���еĳ�����s������ͷ��㻹��s-1���ڵ㣬������ÿ���ڵ��ﶼ��������
	for (int i = 0; i < s-1; i++) {
		Node* tmp = new Node();
		Cur->next = tmp;
		Cur = Cur->next;
	}
	Cur->next = Head;//��β��Ӧ���γ�ѭ������
	Rear = Head;//ĩβ����һ��ָ��ͷ���
}

Queue::~Queue()
{
	//����...��...��...
	Head = nullptr;
	Rear = nullptr;
	Cur = nullptr;
	cout << endl << "����������ɣ����Ա��Ѿ����٣��ڴ�ռ䱻�ͷ�" << endl;
}

void Queue::add(int num)
{
	if (isFull())//�������
	{
		cout << "����ʧ�ܣ����������������ͷ�Ԫ�أ�"<<endl;
	}
	else//û��
	{
		Rear->data = num;//���
		Rear = Rear->next;//��λ��
		size++;
	}

}

void Queue::remove()
{
	if (size==0)//���ȫ��
	{
		cout << "����ȫ�գ��������Ԫ�أ�"<<endl;
	}
	else
	{
		size--;
		Head = Head->next;//����ͷ��㣬��Ȼ����delete�����ǲ�������
	}
}

void Queue::showOff()
{
	cout << "��������һ������Ϊ5�Ķ���"<< endl << endl;
	Queue ls(5);//�����๤����ʵ����û��Ҫ����һ������...
	cout << "�ֲ��������Ԫ��" << endl;
	for (int i = 0; i < 5; i++)
	{
		ls.add(random(1, 100));
		ls.printList();
	}
	cout << endl << "��ͼ���������Ԫ��" << endl;
	ls.add(random(1, 100));
	ls.printList();
	cout << endl << "�ֲ�ɾ���ĸ�Ԫ��" << endl;
	for (int i = 0; i < 4; i++)
	{
		ls.remove();
		ls.printList();
	}

	cout << endl << "����������������Ԫ��" << endl;
	ls.add(random(1, 100));
	ls.printList();
	ls.add(random(1, 100));
	ls.printList();
	ls.add(random(1, 100));
	ls.printList();
	cout << endl << "��ͼɾ�����Ԫ��" << endl;
	for (int i = 0; i < 5; i++)
	{
		ls.remove();
		ls.printList();
	}
}

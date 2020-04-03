#include "Linear.h"

void Linear::go(int index)
{
	Cur = Head;//��ʼ���ǵ�һ���ڵ㣬ͷ���λ����1
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
		cout << endl << "����������ɣ����Ա��Ѿ����٣��ڴ�ռ䱻�ͷ�" << endl;
	}
	else {
		cout << "�Ѿ������������������"<<endl;
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
		//��ͷ���Ĳ���
		Cur = Head;
		Head = Head->next;
		delete Cur;
	}
	else {
		go(index - 1);
		NODE* tmp = Cur->next;
		Cur->next = Cur->next->next;//������һ���ڵ�
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
	cout << "���ڵ�����";
	if (size == 0) { cout << "Ϊ��"<<endl; }
	else {
		for (int i = 0; i < size - 1; i++)
		{
			cout << Cur->data << " ";
			Cur = Cur->next;
		}
		cout << Cur->data << endl << "�䳤�ȣ�" << size << endl;
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
	cout << "ϣ�����ɵ������ȣ�(��������ڵ���5�����֡���Ȼ��ʾ������⡣����������1��100����������֣�";
	cin >> size;
	cout << endl;
	Linear ls;
	for (int i = 0; i < size; i++) {
		ls.add(Linear::random(1, 100));
	}
	ls.printList();
	cout << "��ʾ����������ڵ�������������½�㣬������Ϊ101" << endl;
	ls.add(3, 101);
	ls.printList();
	cout << "��ʾ��������������һ����������½�㣬������Ϊ201" << endl;
	ls.add(201);
	ls.printList();
	cout << "��ʾɾ��������ɾ�������Ԫ��" << endl;
	ls.remove(5);
	ls.printList();
	cout << "��ʾɾ��������ɾ�����һ��Ԫ��" << endl;
	ls.remove();
	ls.printList();
}




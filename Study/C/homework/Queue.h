#pragma once
#include "Linear.h"
class Queue :
	public Linear
{
protected:
	NODE* Rear;//��β
	int totSize;//�����ܳ���
	bool isFull() {return totSize==size;}//�ж϶����Ƿ�����
public:
	Queue(int s);//��ʼ������Ϊs�Ķ��С���ͷ�������Ϊnum��ע�⣬��ʼ��֮�󳤶Ȳ��ɸ���
	~Queue();//����...
	void add(int num);//��д���ຯ��
	void remove();//��д���ຯ��+1
	static void showOff();//��д�����չʾ����
};


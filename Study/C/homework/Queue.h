#pragma once
#include "Linear.h"
class Queue :
	public Linear
{
protected:
	NODE* Rear;//队尾
	int totSize;//队列总长度
	bool isFull() {return totSize==size;}//判断队列是否已满
public:
	Queue(int s);//初始化长度为s的队列。且头结点数字为num。注意，初始化之后长度不可更改
	~Queue();//析构...
	void add(int num);//重写父类函数
	void remove();//重写父类函数+1
	static void showOff();//重写父类的展示方法
};


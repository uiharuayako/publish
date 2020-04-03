#pragma once
#define PAUSE system("pause");
#define CLS system("cls");
#include <iostream>
#include <ctime>
using namespace std;

class Linear {
protected:
	struct NODE {
		NODE* next;
		int data;
		NODE() {
			next = nullptr;
			data = 0;
		}//初始化一个新结点
		NODE(int d) {
			next = nullptr;
			data = d;
		}
	};//结点结构
	NODE* Head;//头结点
	NODE* Cur;//当前操作的结点，这样应该会让程序更简洁，而且避免了冗杂的传参
	int size;//链表长度
	void go(int index);//使操作结点变为index位置
	bool linear = true;
public:
	Linear();//构造
	~Linear();//析构，就是destroy。有子类，没用父类指针，不需要定义为虚析构函数
	void add(int index,int num);//在index后插入一个结点，我们知道，这时新结点索引为index+1，当index为0，将插入的结点变为头结点
	void add(int num);//在链表末尾插入一个结点
	void remove(int index);//移除index位置的结点，并且释放内存
	void remove();//移除最后一个结点
	void printList();//在控制台中打印链表
	int getIndexData(int index);//访问第index结点的数据
	int getSize() { return size; }//获取链表长度
	void headCur() { Cur = Head; }//重置标识符位置
	void nextNode() { Cur = Cur->next; }//标识符向后移一位
	int getCurData() { return Cur->data; }//获取当前结点数据
	bool isNull() { return Cur->next == nullptr ? true : false; }//下一个结点是否为空？
	static int random(int a, int b);//返回a，b闭区间的随机数
	static void showOff();//展示
};


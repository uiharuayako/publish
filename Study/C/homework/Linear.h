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
		}//��ʼ��һ���½��
		NODE(int d) {
			next = nullptr;
			data = d;
		}
	};//���ṹ
	NODE* Head;//ͷ���
	NODE* Cur;//��ǰ�����Ľ�㣬����Ӧ�û��ó������࣬���ұ��������ӵĴ���
	int size;//������
	void go(int index);//ʹ��������Ϊindexλ��
	bool linear = true;
public:
	Linear();//����
	~Linear();//����������destroy�������࣬û�ø���ָ�룬����Ҫ����Ϊ����������
	void add(int index,int num);//��index�����һ����㣬����֪������ʱ�½������Ϊindex+1����indexΪ0��������Ľ���Ϊͷ���
	void add(int num);//������ĩβ����һ�����
	void remove(int index);//�Ƴ�indexλ�õĽ�㣬�����ͷ��ڴ�
	void remove();//�Ƴ����һ�����
	void printList();//�ڿ���̨�д�ӡ����
	int getIndexData(int index);//���ʵ�index��������
	int getSize() { return size; }//��ȡ������
	void headCur() { Cur = Head; }//���ñ�ʶ��λ��
	void nextNode() { Cur = Cur->next; }//��ʶ�������һλ
	int getCurData() { return Cur->data; }//��ȡ��ǰ�������
	bool isNull() { return Cur->next == nullptr ? true : false; }//��һ������Ƿ�Ϊ�գ�
	static int random(int a, int b);//����a��b������������
	static void showOff();//չʾ
};


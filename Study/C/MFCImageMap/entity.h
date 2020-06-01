#pragma once
#include <list>
#include <string>
#include <iostream>
#include "pch.h"
using namespace std;
class entity
{
private:
	list<CPoint> entityPoints;// ���ڴ��������Լ������ϵĸ�����
	bool isSelected = false;// �жϸ�ʸ��ͼ���Ƿ�ѡ��
	string strLabel = "";// �������ֱ�ǩ
public:
	virtual double area() = 0;// ����ͼ�����
	virtual double length() = 0;// ���㳤��
	virtual CPoint center() = 0;// ����ͼ������λ��
	virtual void Draw(CDC*) = 0;// ʵ�ֻ�ͼ����
	virtual void DrawXOR(CDC*) = 0;// ��򻭷���ʵ����Ƥ

	virtual list<CPoint>::iterator hit_test(CRect&) = 0;// ����һ��ѡ��ѡ�еľ��Σ��ҵ���ѡ��Χ�ڵĵ�
	virtual void insert_after(list<CPoint>::iterator&) = 0;// �ڸ����ĵ������
	virtual void remove(list<CPoint>::iterator&) = 0;// �Ƴ������ĵ�

};


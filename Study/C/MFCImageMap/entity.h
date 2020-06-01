#pragma once
#include <list>
#include <string>
#include <iostream>
#include "pch.h"
using namespace std;
class entity
{
private:
	list<CPoint> entityPoints;// 用于储存多边形以及折线上的各个点
	bool isSelected = false;// 判断该矢量图形是否被选中
	string strLabel = "";// 定义文字标签
public:
	virtual double area() = 0;// 计算图形面积
	virtual double length() = 0;// 计算长度
	virtual CPoint center() = 0;// 计算图形质心位置
	virtual void Draw(CDC*) = 0;// 实现绘图功能
	virtual void DrawXOR(CDC*) = 0;// 异或画法，实现橡皮

	virtual list<CPoint>::iterator hit_test(CRect&) = 0;// 给定一个选框选中的矩形，找到框选范围内的点
	virtual void insert_after(list<CPoint>::iterator&) = 0;// 在给定的点后插入点
	virtual void remove(list<CPoint>::iterator&) = 0;// 移除给定的点

};


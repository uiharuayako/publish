#pragma once
#include <cmath>
#include <string>
#include <iostream>
#define Cls system("cls")
#define Pause system("pause")
using namespace std;
const double PI = 3.1415926535897932;
class Date//储存用于计算的数据
{
	double B, L, H;
	double a;
	double b;
public:
	double X, Y, Z;
	int last_conv;//1：BLH-XYZ 2.XYZ-BLH
	void set_ab(double ar,double br);//设定地球椭球体参数，默认使用CGCS2000
	void set_ab();
	double Get_N();//返回N的值
	double Get_H();//返回H
	double Get_L();//返回L
	double Get_B();//返回B
	double Get_e2();//返回e^2
	void set_B(double Br);
	int set_BLH(double Br, double Lr, double Hr);//设定大地坐标系
	void print_po();//打印相关信息
};


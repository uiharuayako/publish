#pragma once
#include <cmath>
#include <string>
#include <iostream>
#define Cls system("cls")
#define Pause system("pause")
using namespace std;
const double PI = 3.1415926535897932;
class Date//�������ڼ��������
{
	double B, L, H;
	double a;
	double b;
public:
	double X, Y, Z;
	int last_conv;//1��BLH-XYZ 2.XYZ-BLH
	void set_ab(double ar,double br);//�趨���������������Ĭ��ʹ��CGCS2000
	void set_ab();
	double Get_N();//����N��ֵ
	double Get_H();//����H
	double Get_L();//����L
	double Get_B();//����B
	double Get_e2();//����e^2
	void set_B(double Br);
	int set_BLH(double Br, double Lr, double Hr);//�趨�������ϵ
	void print_po();//��ӡ�����Ϣ
};


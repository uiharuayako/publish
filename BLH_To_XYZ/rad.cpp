#include "rad.h"


void Date::set_ab(double ar, double br)
{
	a = ar;
	b = br;
}

void Date::set_ab()
{
	a = 6378137;
	b = 6356752.3141;
}

double Date::Get_N()
{
	return a / sqrt(1 - Get_e2()* sin(B) * sin(B));
}

double Date::Get_H()
{
	return H;
}

double Date::Get_L()
{
	return L;
}

double Date::Get_B()
{
	return B;
}

double Date::Get_e2()
{
	return  (a * a - b * b) / (a * a);
}

void Date::set_B(double Br)
{
	B = Br;
}

int Date::set_BLH(double Br, double Lr, double Hr)
{
	B = Br;
	L = Lr;
	H = Hr;
	int errcode = 0;
	if (Br > PI / 2 || Br < -PI / 2) errcode += 1;
	if (Lr > PI || Lr < -PI) errcode += 10;
	return errcode;
}

void Date::print_po()
{
		cout.setf(ios_base::fixed);
		cout << "这个点的大地经度：" << L * 180 / PI;
		cout << "\n这个点的大地纬度：" << B * 180 / PI;
		cout << "\n这个点的大地高：" << H ;
		cout << "\n直角坐标：X=" << X << "     Y=" << Y << "     Z=" << Z <<endl;
		cout << endl << endl;
		Pause;
}


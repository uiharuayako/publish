//����ת������ں���
#include "rad.h"
#include <iostream>
using namespace std;
void BLH_To_XYZ(Date& po);
void XYZ_To_BLH(Date& po);
int main() 
{
	int cho = 0;
	Date poi;
	Date& po = poi;
	po.last_conv = 0;
	poi.set_ab();
	while (true)
	{
		if (cho != 3) {
			if (po.last_conv == 0)
			{
				cout << "1.�������תֱ������ 2.ֱ������ת������� 3.debug:";
			}
			else
				cout << "1.�������תֱ������ 2.ֱ������ת������� 3.ѭ��ʹ�������ֱ������ת�������:";
			cin >> cho;
			Cls;
		}
		if (po.last_conv == 0 && cho == 3) {
			po.X = -2280473; po.Y = 4981179; po.Z = 3255307;
		}
		switch (cho)
		{
		case 1:
			po.last_conv = 1;
			cout << "�������ؾ���:";
			double L;
			cin >> L;
			cout << "��������γ��:";
			double B;
			cin >> B;
			cout << "�������ظ�:";
			double H;
			cin >> H;
			switch (po.set_BLH(B*PI/180, L*PI/180, H))
			{
			case 1:
				cout << "γ�ȳ���\n" << endl;
				break;
			case 10:
				cout << "���ȳ���\n";
			case 11:
				cout << "��γ�Ⱦ�����\n";
			}
			BLH_To_XYZ(po);
			break;
		case 2:
			po.last_conv = 2;
			cout << "������X:";
			cin >> po.X;
			cout << "������Y:";
			cin >> po.Y;
			cout << "������Z:";
			cin >> po.Z;
			XYZ_To_BLH(po);
			break;
		case 3:
			XYZ_To_BLH(po);
			break;
		}
		po.print_po();
	}
}

void BLH_To_XYZ(Date& po)
{
	po.X = (po.Get_N() + po.Get_H()) * cos(po.Get_B()) * cos(po.Get_L());
	po.Y = (po.Get_N() + po.Get_H()) * cos(po.Get_B()) * sin(po.Get_L());
	po.Z = (po.Get_N() * (1 - po.Get_e2()) + po.Get_H()) * sin(po.Get_B());
}

void XYZ_To_BLH(Date& po)
{
	double X=po.X; double Y=po.Y; double Z=po.Z;
	po.set_B(atan2(Z , sqrt(X * X + Y * Y)));
	double L = atan2(Y,X);
	double LB;//��һ��B��ֵ
	do{
		LB = po.Get_B();
		po.set_B(atan2((Z + po.Get_N() * po.Get_e2() * sin(po.Get_B())),sqrt(X * X + Y * Y)));
	}while(abs(LB-po.Get_B())>0.001);
	double H = (sqrt(X * X + Y * Y) / cos(po.Get_B())) - po.Get_N();
	//double H = Z / sin(po.Get_B()) - po.Get_N() * (1 - po.Get_e2());
	switch (po.set_BLH(po.Get_B(), L, H))
	{
	case 1:
		cout << "γ�ȳ���\n" << endl;
		break;
	case 10:
		cout << "���ȳ���\n";
	case 11:
		cout << "��γ�Ⱦ�����\n";
	}
	
}



#include "cities.h"
using namespace std;
int main() {
	Cities a;
	int cho1 = 1;
	cout << "\n1.��ȱ����ǵݹ� 2.���������м�����·�� :" << endl;
	cin >> cho1;
	switch (cho1)
	{
	case 1:
	{
		a.connect_all_in_order();
		string name;
		cout << "\n����������������У�Ӣ��������";
		cin >> name;
		a.DFS(name);
		break;
	}
	case 2:
	{
		string start, end;
		cout << "\n������:";
		cin >> start;
		cout << "\n�յ����:";
		cin >> end;
		int cho2 = 1;
		cout << "\n1.ʱ����� 2.�������� �������֣����󹤷�Ѱ���ۺϻ�����͵�·����������ּ�����󹤷ѣ���λ����Ԫ/Сʱ��:";
		cin >> cho2;
		switch (cho2)
		{
		case 1:
		{
			a.connect_all_in_time();
			a.print_route(a.find_min_time_spRoute(a.search_index(start), a.search_index(end)));
			break;
		}
		case 2:
		{
			a.connect_all_in_price();
			a.print_route(a.find_min_price_spRoute(a.search_index(start), a.search_index(end)));
			break;
		}
		default:
		{
			a.connect_all_in_time_price(cho2);
			a.print_route(a.find_min_price_spRoute(a.search_index(start), a.search_index(end)));
			break;
		}
		}
		break;
	}
	}
	system("pause");
	return 0;
}
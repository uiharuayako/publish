#include "cities.h"
using namespace std;
int main() {
	Cities a;
	int cho1 = 1;
	cout << "\n1.深度遍历非递归 2.查找两城市间的最短路径 :" << endl;
	cin >> cho1;
	switch (cho1)
	{
	case 1:
	{
		a.connect_all_in_order();
		string name;
		cout << "\n请输入遍历的起点城市（英文名）：";
		cin >> name;
		a.DFS(name);
		break;
	}
	case 2:
	{
		string start, end;
		cout << "\n起点城市:";
		cin >> start;
		cout << "\n终点城市:";
		cin >> end;
		int cho2 = 1;
		cout << "\n1.时间最短 2.花费最少 其他数字：按误工费寻找综合花费最低的路，输入的数字即你的误工费（单位：美元/小时）:";
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
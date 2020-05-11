#pragma once
//TODO cities包含一个长度为199的数组，数组的类型是City，City有country，name（城市的名称作为标志，顺序不是标志），以及经纬度
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include "stdio.h"
#include <vector>
#include <stack>
#include <math.h>
using namespace std;
struct City
{
	string country;
	string name;
	double latitude;//纬度，北纬为正，南纬为负
	double longitude;//经度，东经为正，西经为负
	bool isVisited;//作为一个被访问的标识符
	bool isIsolated;//作为是否孤立的标志
	bool neverReturn;//只能去，不能回，不是任何线路的目的地
	bool neverStart;//不能去任何地方
	int id;//当前城市的数组下标，可能没有下标
	City() {
		country = "country";
		name = "city";
		latitude = 0;
		longitude = 0;
		isVisited = false;
		isIsolated = true;//开始时所有独立
		neverReturn = true;
		neverStart = true;
		id = -1;
	}
};
struct Route
{
	string start_city;//出发点，其实是无向图，无所谓的
	string destination_city;//目的地，同上
	string way_to_travel;//旅行方式
	double hours;//旅途时间
	double price;//价格
	double length;//线路长度,直线长度
	string info;//相关信息
};//这是直接读取文件内容得到的数据结构
struct Route_h_l
{
	bool isConnected;
	double hours;
	double price;
	double length;
	string way;
	Route_h_l() {
		isConnected = false;
		hours = 999999;
		price = 9999999;
		length = 9999999;
		way = "error way";
	}
};//这是录入邻接矩阵使用的数据结构
struct spRoute {
	vector<int> way;//记录路上的结点城市
	int start_city;//开始的城市编号
	int end_city;//结束的程序编号
	double weight;//记录该路径的总权重
	spRoute(int start,int end) {
		start_city = start;
		end_city = end;
		weight = 0;
		way.push_back(start_city);
	}
	spRoute(int start, int end,bool cannot) {
		//当无路可走，我尽量防止这种情况的产生，理论上这种情况也不会产生
		start_city = start;
		end_city = end;
		way.push_back(start_city);
		way.push_back(end_city);
		weight = 999999999;
	}
	void addCity(int n,double wei) {
		way.push_back(n);
		weight += wei;
	}
};//这是每一条，特殊化的路径
class Cities
{
	double PI = 3.1415926;//PI
	double EARTH_R = 6378.137; //地球近似半径
	vector<City> city_data;//链表方式，便于今后添加更多城市信息，不用每次都改，也不用查看行号，便于实时更新
	vector<Route> routes;//同上，这个真的需要用链表
	double radian(double d);//角度转弧度
	double get_distance(double lat1, double lng1, double lat2, double lng2);//依照经纬度计算两点间距离
	void clearVisit();//将所有城市置于未访问状态
	void visit(int num);//简单访问一下
public:
	Cities();//初始化
	City search(string this_name);//返回名为name的city
	int search_index(string this_name);//返回名为city的编号
	Route_h_l** route_info;//邻接矩阵，是一个二维动态数组，可以随着数据的增加而自动增加
	bool is_connected_name(string city1, string city2);
	void connect_all_in_order();//按顺序连接全部城市，不计重复
	void connect_all_in_time();//选择时间最小的连接路线
	void connect_all_in_price();//选择时间最小的连接路线
	void connect_all_in_time_price(double money);//按照你的一个小时值多少钱来针对性算出最适合你的路径
	void DFS(string city_n);//深度遍历所有城市，非递归
	spRoute find_min_time_spRoute(int startCity, int endCity);//返回两城市之间的最短时间路径
	spRoute find_min_length_spRoute(int startCity, int endCity);//返回两城市之间的最短路程路径
	spRoute find_min_price_spRoute(int startCity, int endCity);//返回两城市之间的最低价格路径
	bool verif_consistency();//测试用函数
	void print_cities();//调试用，打印城市信息
	void print_unvisited();//调试用，打印未被访问的城市的信息
	int countIsolated();
	void print_route(spRoute spR);
	void print_routes();//同上
	void print_connect();//同上
};


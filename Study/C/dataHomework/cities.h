#pragma once
//TODO cities包含一个长度为199的数组，数组的类型是City，City有country，name（城市的名称作为标志，顺序不是标志），以及经纬度
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include "stdio.h"
#include <vector>
using namespace std;
struct City
{
	string country;
	string name;
	double latitude;//纬度，北纬为正，南纬为负
	double longitude;//经度，东经为正，西经为负
	bool isVisited;//作为一个被访问的标识符
	City() {
		country = "country";
		name = "city";
		latitude = 0;
		longitude = 0;
	}
};
struct Route
{
	string start_city;//出发点，其实是无向图，无所谓的
	string destination_city;//目的地，同上
	string way_to_travel;//旅行方式
	double hours;//旅途时间
	double length;//线路长度
	string info;//相关信息
};
struct Route_h_l
{
	bool isConnected;
	double hours;
	double length;
	string way;
	Route_h_l() {
		isConnected = false;
		hours = 999999;
		length = 9999999;
		way = "error way";
	}
};
class Cities
{
	vector<City> city_data;//链表方式，便于今后添加更多城市信息，不用每次都改，也不用查看行号，便于实时更新
	vector<Route> routes;//同上，这个真的需要用链表
	
public:
	Cities();//初始化
	City search(string this_name);//返回名为name的city
	int search_index(string this_name);//返回名为city的编号
	Route_h_l** route_info;//邻接矩阵，是一个二维动态数组，可以随着数据的增加而自动增加
	bool is_connected_name(string city1, string city2);
	void connect_all();//连接全部城市
	double get_route_length();
	double get_route_price();
	void print_cities();//调试用，打印城市信息
	void print_routes();//同上
	void print_connect();//同上
};


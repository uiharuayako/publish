#pragma once
//TODO cities����һ������Ϊ199�����飬�����������City��City��country��name�����е�������Ϊ��־��˳���Ǳ�־�����Լ���γ��
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
	double latitude;//γ�ȣ���γΪ������γΪ��
	double longitude;//���ȣ�����Ϊ��������Ϊ��
	bool isVisited;//��Ϊһ�������ʵı�ʶ��
	City() {
		country = "country";
		name = "city";
		latitude = 0;
		longitude = 0;
	}
};
struct Route
{
	string start_city;//�����㣬��ʵ������ͼ������ν��
	string destination_city;//Ŀ�ĵأ�ͬ��
	string way_to_travel;//���з�ʽ
	double hours;//��;ʱ��
	double length;//��·����
	string info;//�����Ϣ
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
	vector<City> city_data;//����ʽ�����ڽ����Ӹ��������Ϣ������ÿ�ζ��ģ�Ҳ���ò鿴�кţ�����ʵʱ����
	vector<Route> routes;//ͬ�ϣ���������Ҫ������
	
public:
	Cities();//��ʼ��
	City search(string this_name);//������Ϊname��city
	int search_index(string this_name);//������Ϊcity�ı��
	Route_h_l** route_info;//�ڽӾ�����һ����ά��̬���飬�����������ݵ����Ӷ��Զ�����
	bool is_connected_name(string city1, string city2);
	void connect_all();//����ȫ������
	double get_route_length();
	double get_route_price();
	void print_cities();//�����ã���ӡ������Ϣ
	void print_routes();//ͬ��
	void print_connect();//ͬ��
};


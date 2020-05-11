#pragma once
//TODO cities����һ������Ϊ199�����飬�����������City��City��country��name�����е�������Ϊ��־��˳���Ǳ�־�����Լ���γ��
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
	double latitude;//γ�ȣ���γΪ������γΪ��
	double longitude;//���ȣ�����Ϊ��������Ϊ��
	bool isVisited;//��Ϊһ�������ʵı�ʶ��
	bool isIsolated;//��Ϊ�Ƿ�����ı�־
	bool neverReturn;//ֻ��ȥ�����ܻأ������κ���·��Ŀ�ĵ�
	bool neverStart;//����ȥ�κεط�
	int id;//��ǰ���е������±꣬����û���±�
	City() {
		country = "country";
		name = "city";
		latitude = 0;
		longitude = 0;
		isVisited = false;
		isIsolated = true;//��ʼʱ���ж���
		neverReturn = true;
		neverStart = true;
		id = -1;
	}
};
struct Route
{
	string start_city;//�����㣬��ʵ������ͼ������ν��
	string destination_city;//Ŀ�ĵأ�ͬ��
	string way_to_travel;//���з�ʽ
	double hours;//��;ʱ��
	double price;//�۸�
	double length;//��·����,ֱ�߳���
	string info;//�����Ϣ
};//����ֱ�Ӷ�ȡ�ļ����ݵõ������ݽṹ
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
};//����¼���ڽӾ���ʹ�õ����ݽṹ
struct spRoute {
	vector<int> way;//��¼·�ϵĽ�����
	int start_city;//��ʼ�ĳ��б��
	int end_city;//�����ĳ�����
	double weight;//��¼��·������Ȩ��
	spRoute(int start,int end) {
		start_city = start;
		end_city = end;
		weight = 0;
		way.push_back(start_city);
	}
	spRoute(int start, int end,bool cannot) {
		//����·���ߣ��Ҿ�����ֹ��������Ĳ������������������Ҳ�������
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
};//����ÿһ�������⻯��·��
class Cities
{
	double PI = 3.1415926;//PI
	double EARTH_R = 6378.137; //������ư뾶
	vector<City> city_data;//����ʽ�����ڽ����Ӹ��������Ϣ������ÿ�ζ��ģ�Ҳ���ò鿴�кţ�����ʵʱ����
	vector<Route> routes;//ͬ�ϣ���������Ҫ������
	double radian(double d);//�Ƕ�ת����
	double get_distance(double lat1, double lng1, double lat2, double lng2);//���վ�γ�ȼ�����������
	void clearVisit();//�����г�������δ����״̬
	void visit(int num);//�򵥷���һ��
public:
	Cities();//��ʼ��
	City search(string this_name);//������Ϊname��city
	int search_index(string this_name);//������Ϊcity�ı��
	Route_h_l** route_info;//�ڽӾ�����һ����ά��̬���飬�����������ݵ����Ӷ��Զ�����
	bool is_connected_name(string city1, string city2);
	void connect_all_in_order();//��˳������ȫ�����У������ظ�
	void connect_all_in_time();//ѡ��ʱ����С������·��
	void connect_all_in_price();//ѡ��ʱ����С������·��
	void connect_all_in_time_price(double money);//�������һ��Сʱֵ����Ǯ�������������ʺ����·��
	void DFS(string city_n);//��ȱ������г��У��ǵݹ�
	spRoute find_min_time_spRoute(int startCity, int endCity);//����������֮������ʱ��·��
	spRoute find_min_length_spRoute(int startCity, int endCity);//����������֮������·��·��
	spRoute find_min_price_spRoute(int startCity, int endCity);//����������֮�����ͼ۸�·��
	bool verif_consistency();//�����ú���
	void print_cities();//�����ã���ӡ������Ϣ
	void print_unvisited();//�����ã���ӡδ�����ʵĳ��е���Ϣ
	int countIsolated();
	void print_route(spRoute spR);
	void print_routes();//ͬ��
	void print_connect();//ͬ��
};


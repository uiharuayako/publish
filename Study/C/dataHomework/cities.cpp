#include "cities.h"
//辅助用函数
void string2num(string str, double& num)
{
	stringstream ss;
	ss << str;
	ss >> num;
}
Cities::Cities()
{
	//读取csv信息，写入内存
	//城市信息
	ifstream this_file("cities.csv");
	string this_line;
	for (int i = 0; getline(this_file, this_line);i++) {
		istringstream sin(this_line);
		City tmpCity;
		//国家
		getline(sin, tmpCity.country,',');
		//城市
		getline(sin, tmpCity.name, ',');
		string tmp1, tmp2;
		getline(sin,tmp1, ',');
		getline(sin, tmp2);
		string2num(tmp1, tmpCity.latitude);
		string2num(tmp2, tmpCity.longitude);
		city_data.push_back(tmpCity);
	}
	this_file.close();
	int numCity = city_data.size();
	//初始化邻接矩阵
	route_info = new Route_h_l*[numCity];//动态二维数组
	for (int i = 0; i < numCity; i++) {
		route_info[i] = new Route_h_l[numCity];
	}
	
	ifstream route_file("routes.csv");
	string route_line;
	//获得所有信息
	for (int i = 0; getline(route_file,route_line); i++) {
		istringstream sin(route_line);
		Route tmpRoute;
		getline(sin, tmpRoute.start_city, ',');
		getline(sin, tmpRoute.destination_city, ',');
		getline(sin, tmpRoute.way_to_travel, ',');
		string tmp1, tmp2;
		getline(sin, tmp1, ',');
		getline(sin, tmp2,',');
		string2num(tmp1, tmpRoute.hours);
		string2num(tmp2, tmpRoute.length);
		getline(sin, tmpRoute.info);
		routes.push_back(tmpRoute);
	}
	this_file.close();
}

City Cities::search(string this_name)
{
	for (int i = 0; i < city_data.size(); i++) {
		if (this_name.compare(city_data[i].name)) {
			return city_data[i];
		}
	}
	return City();
}

int Cities::search_index(string this_name)
{
	for (int i = 0; i < city_data.size(); i++) {
		if (this_name.compare(city_data[i].name)==0) {
			return i;
		}
	}
	return -1;
}

bool Cities::is_connected_name(string city1, string city2)
{
	return route_info[search_index(city1)][search_index(city2)].isConnected;
}

void Cities::connect_all()
{
	//有多少条路，遍历多少次
	//这是一个将routes中的信息装载到二维数组中的过程
	for (int i = 0; i < routes.size(); i++) {
		int start_city = search_index(routes[i].start_city);//获取开始的城市号
		int des_city = search_index(routes[i].destination_city);//获取结束的城市号
		if (start_city == -1 || des_city == -1) {
			cout << "程序中断，检索到错误的城市！\n错误位置：routes.csv的第" << i << "行" << endl;
			system("pause");
		}
		route_info[start_city][des_city].isConnected = true;//建立连接
		route_info[start_city][des_city].hours = routes[i].hours;//读取时间
		route_info[start_city][des_city].length = routes[i].length;//读取距离
		route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
	}
}

void Cities::print_cities()
{
	for (int i = 0; i < city_data.size(); i++) {
		cout << i << endl;
		cout << city_data[i].country << "," << city_data[i].name << "," << city_data[i].latitude << "," << city_data[i].longitude << endl;
	}
}

void Cities::print_routes()
{
	for (int i = 0; i < routes.size();i++) {
		for (int j = i+1; j < routes.size(); j++) {
			if (routes[i].start_city.compare(routes[j].start_city)==0 && routes[i].destination_city.compare(routes[j].destination_city)==0) {
				cout << routes[i].start_city << "-" << routes[i].destination_city << endl;
			}
		}
	}
}

void Cities::print_connect()
{
	int n = 0;
	for (int i = 0; i < city_data.size(); i++) {
		for (int j = 0; j < city_data.size(); j++) {
			if (route_info[i][j].isConnected) {
				if (route_info[j][i].isConnected) {
					++n;
					cout << n<<endl;
				}
			}
		}
	}
}

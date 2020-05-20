#include "cities.h"
//辅助用函数
void string2num(string str, double& num)
{
	stringstream ss;
	ss << str;
	ss >> num;
}
double Cities::radian(double d)
{
	return d * PI / 180.0;
}
double Cities::get_distance(double lat1, double lng1, double lat2, double lng2)
{
	double radLat1 = radian(lat1);
	double radLat2 = radian(lat2);
	double a = radLat1 - radLat2;
	double b = radian(lng1) - radian(lng2);
	double dst = 2 * EARTH_R * asin(sqrt(pow(sin(a / 2), 2) + cos(radLat1) * cos(radLat2) * pow(sin(b / 2), 2)));
	return dst;
}
void Cities::clearVisit()
{
	for (int i = 0; i < city_data.size(); i++) city_data[i].isVisited = false;
}
void Cities::visit(int num)
{
	city_data[num].isVisited = true;
	cout << city_data[num].name << "-》";
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
		tmpCity.id = i;//给id赋值
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
		city_data[search_index(tmpRoute.start_city)].neverStart = false;//如果这样，就不独立
		getline(sin, tmpRoute.destination_city, ',');
		city_data[search_index(tmpRoute.destination_city)].neverReturn = false;//如果这样，就不独立
		getline(sin, tmpRoute.way_to_travel, ',');
		string tmp1, tmp2;
		getline(sin, tmp1, ',');
		getline(sin, tmp2,',');
		string2num(tmp1, tmpRoute.hours);
		string2num(tmp2, tmpRoute.price);
		getline(sin, tmpRoute.info);
		double temLength = get_distance(
			city_data[search_index(tmpRoute.start_city)].latitude,
			city_data[search_index(tmpRoute.start_city)].longitude,
			city_data[search_index(tmpRoute.destination_city)].latitude,
			city_data[search_index(tmpRoute.destination_city)].longitude
			);
		tmpRoute.length = temLength;
		routes.push_back(tmpRoute);
	}
	for (int i = 0; i < city_data.size(); i++) {
		city_data[i].isIsolated = city_data[i].neverReturn && city_data[i].neverStart;//如果一个城市既不是任何起点也不是任何终点
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

void Cities::connect_all_in_order()
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
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//建立连接
			route_info[start_city][des_city].hours = routes[i].hours;//读取时间
			route_info[start_city][des_city].price = routes[i].price;//读取价格
			route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
			route_info[start_city][des_city].length = routes[i].length;//读取距离
			route_info[start_city][des_city].info = routes[i].info;//读取信息
		}
	}
}

void Cities::connect_all_in_time()
{
	int start_city, des_city;
	//有多少条路，遍历多少次
	//这是一个将routes中的信息装载到二维数组中的过程
	for (int i = 0; i < routes.size(); i++) {
		start_city = search_index(routes[i].start_city);//获取开始的城市号
		des_city = search_index(routes[i].destination_city);//获取结束的城市号
		if (start_city == -1 || des_city == -1) {
			cout << "程序中断，检索到错误的城市！\n错误位置：routes.csv的第" << i << "行" << endl << "请检查数据后重新运行，按下回车忽视错误继续运行" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//建立连接
			route_info[start_city][des_city].hours = routes[i].hours;//读取时间
			route_info[start_city][des_city].price = routes[i].price;//读取价格
			route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
			route_info[start_city][des_city].length = routes[i].length;//读取距离
			route_info[start_city][des_city].info = routes[i].info;//读取信息
		}
		else {
			if (routes[i].hours < route_info[start_city][des_city].hours) {
				//判断，如果文件中路径时间比内存中记录的更短，则进行替换
				route_info[start_city][des_city].hours = routes[i].hours;//读取时间
				route_info[start_city][des_city].price = routes[i].price;//读取价格
				route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
				route_info[start_city][des_city].length = routes[i].length;//读取距离
				route_info[start_city][des_city].info = routes[i].info;//读取信息
			}
		}
	}
}

void Cities::connect_all_in_price()
{
	int start_city, des_city;
	//有多少条路，遍历多少次
	//这是一个将routes中的信息装载到二维数组中的过程
	for (int i = 0; i < routes.size(); i++) {
		start_city = search_index(routes[i].start_city);//获取开始的城市号
		des_city = search_index(routes[i].destination_city);//获取结束的城市号
		if (start_city == -1 || des_city == -1) {
			cout << "程序中断，检索到错误的城市！\n错误位置：routes.csv的第" << i << "行" << endl << "请检查数据后重新运行，按下回车忽视错误继续运行" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//建立连接
			route_info[start_city][des_city].hours = routes[i].hours;//读取时间
			route_info[start_city][des_city].price = routes[i].price;//读取价格
			route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
			route_info[start_city][des_city].length = routes[i].length;//读取距离
			route_info[start_city][des_city].info = routes[i].info;//读取信息
		}
		else {
			if (routes[i].price < route_info[start_city][des_city].price) {
				//判断，如果文件中价格比内存中记录的更便宜，则进行替换
				route_info[start_city][des_city].hours = routes[i].hours;//读取时间
				route_info[start_city][des_city].price = routes[i].price;//读取价格
				route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
				route_info[start_city][des_city].length = routes[i].length;//读取距离
				route_info[start_city][des_city].info = routes[i].info;//读取信息
			}
		}
	}
}

void Cities::connect_all_in_time_price(double money)
{
	int start_city, des_city;
	//有多少条路，遍历多少次
	//这是一个将routes中的信息装载到二维数组中的过程
	for (int i = 0; i < routes.size(); i++) {
		start_city = search_index(routes[i].start_city);//获取开始的城市号
		des_city = search_index(routes[i].destination_city);//获取结束的城市号
		if (start_city == -1 || des_city == -1) {
			cout << "程序中断，检索到错误的城市！\n错误位置：routes.csv的第" << i << "行" << endl << "请检查数据后重新运行，按下回车忽视错误继续运行" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//建立连接
			route_info[start_city][des_city].hours = routes[i].hours;//读取时间
			route_info[start_city][des_city].price = routes[i].price;//读取价格
			route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
			route_info[start_city][des_city].length = routes[i].length;//读取距离
			route_info[start_city][des_city].info = routes[i].info;//读取信息
		}
		else {
			if (routes[i].price + routes[i].hours*money < route_info[start_city][des_city].price+ route_info[start_city][des_city].hours+money) {
				//判断，如果文件中价格比内存中记录的结合权重更便宜，则替换。相当于算了个路上的误工费
				route_info[start_city][des_city].hours = routes[i].hours;//读取时间
				route_info[start_city][des_city].price = routes[i].price;//读取价格
				route_info[start_city][des_city].way = routes[i].way_to_travel;//读取交通方式
				route_info[start_city][des_city].length = routes[i].length;//读取距离
				route_info[start_city][des_city].info = routes[i].info;//读取信息
			}
		}
	}
}

void Cities::DFS(string city_n)
{
	int ves = search_index(city_n);
	clearVisit();//清空访问信息
	stack<City> s;
	visit(ves);
	s.push(city_data[ves]);
	City tmpCity;
	while (!s.empty()) {
		tmpCity = s.top();//取临时变量为栈顶
		int i;
		for (i = 0; i < city_data.size(); i++) {
			if (route_info[tmpCity.id][i].isConnected && !city_data[i].isVisited) {
				//如果，两城市相连，且城市i未被访问过
				visit(i);
				s.push(city_data[i]);//i入栈
				break;//跳出这个for循环，因为是深度遍历，现在要深度遍历i
			}
		}
		if (i == city_data.size()) {
			s.pop();//如果到了城市数据的末尾，则弹出末尾的数据，栈是LIFO结构，后进先出
		}
	}
}

spRoute Cities::find_min_time_spRoute(int startCity, int endCity)
{
	vector<spRoute> allRoute;
	//初始化所有路径
	for (int i = 0; i < city_data.size(); i++) {
		spRoute tmpRoute(startCity, i);
		tmpRoute.addCity(i, route_info[startCity][i].hours);
		allRoute.push_back(tmpRoute);
	}
	//将所有城市置于非访问状态
	clearVisit();
	city_data[startCity].isVisited = true;//访问第一个城市
	double min_cost;//最低权重
	int min_city;//最短城市的下标
	//主循环
	for (int i = 1; i < city_data.size(); i++) {
		min_cost = 99999;
		for (int j = 0; j < city_data.size(); j++) {
			//确保该城市未被访问，且当起始城市到该城市的距离比当前的min_cost更小时（这个循环旨在找到已知的最短路径
			if (city_data[j].isVisited == false && allRoute[j].weight < min_cost) {
				min_city = j;//新的临时最短城市
				min_cost = allRoute[j].weight;
			}
		}
		//找到了当前最短城市，访问
		city_data[min_city].isVisited = true;
		for (int j = 0; j < city_data.size(); j++) {
			//如果未被访问，有连接，且经过min_city访问这个城市路径更短
			if (city_data[j].isVisited == false 
				&& route_info[min_city][j].isConnected 
				&& route_info[min_city][j].hours + min_cost < allRoute[j].weight) {
				allRoute[j].weight = route_info[min_city][j].hours + min_cost;//新的权重
				allRoute[j] = allRoute[min_city];//把当前最短城市给要求的最短路径
				allRoute[j].addCity(j, route_info[min_city][j].hours);//连接上最后一段路
			}
		}
	}
	return allRoute[endCity];
}

spRoute Cities::find_min_length_spRoute(int startCity, int endCity)
{
	vector<spRoute> allRoute;
	//初始化所有路径
	for (int i = 0; i < city_data.size(); i++) {
		spRoute tmpRoute(startCity, i);
		tmpRoute.addCity(i, route_info[startCity][i].length);
		allRoute.push_back(tmpRoute);
	}
	//将所有城市置于非访问状态
	clearVisit();
	city_data[startCity].isVisited = true;//访问第一个城市
	double min_cost;//最低权重
	int min_city;//最短城市的下标
	//主循环
	for (int i = 1; i < city_data.size(); i++) {
		min_cost = 99999;
		for (int j = 0; j < city_data.size(); j++) {
			//确保该城市未被访问，且当起始城市到该城市的距离比当前的min_cost更小时（这个循环旨在找到已知的最短路径
			if (city_data[j].isVisited == false && allRoute[j].weight < min_cost) {
				min_city = j;//新的临时最短城市
				min_cost = allRoute[j].weight;
			}
		}
		//找到了当前最短城市，访问
		city_data[min_city].isVisited = true;
		for (int j = 0; j < city_data.size(); j++) {
			//如果未被访问，有连接，且经过min_city访问这个城市路径更短
			if (city_data[j].isVisited == false
				&& route_info[min_city][j].isConnected
				&& route_info[min_city][j].length + min_cost < allRoute[j].weight) {
				allRoute[j].weight = route_info[min_city][j].length + min_cost;//新的权重
				allRoute[j] = allRoute[min_city];//把当前最短城市给要求的最短路径
				allRoute[j].addCity(j, route_info[min_city][j].length);//连接上最后一段路
			}
		}
	}
	return allRoute[endCity];
}

spRoute Cities::find_min_price_spRoute(int startCity, int endCity)
{
	vector<spRoute> allRoute;
	//初始化所有路径
	for (int i = 0; i < city_data.size(); i++) {
		spRoute tmpRoute(startCity, i);
		tmpRoute.addCity(i, route_info[startCity][i].price);
		allRoute.push_back(tmpRoute);
	}
	//将所有城市置于非访问状态
	clearVisit();
	city_data[startCity].isVisited = true;//访问第一个城市
	double min_cost;//最低权重
	int min_city;//最短城市的下标
	//主循环
	for (int i = 1; i < city_data.size(); i++) {
		min_cost = 99999;
		for (int j = 0; j < city_data.size(); j++) {
			//确保该城市未被访问，且当起始城市到该城市的距离比当前的min_cost更小时（这个循环旨在找到已知的最短路径
			if (city_data[j].isVisited == false && allRoute[j].weight < min_cost) {
				min_city = j;//新的临时最短城市
				min_cost = allRoute[j].weight;
			}
		}
		//找到了当前最短城市，访问
		city_data[min_city].isVisited = true;
		for (int j = 0; j < city_data.size(); j++) {
			//如果未被访问，有连接，且经过min_city访问这个城市路径更短
			if (city_data[j].isVisited == false
				&& route_info[min_city][j].isConnected
				&& route_info[min_city][j].price + min_cost < allRoute[j].weight) {
				allRoute[j].weight = route_info[min_city][j].price + min_cost;//新的权重
				allRoute[j] = allRoute[min_city];//把当前最短城市给要求的最短路径
				allRoute[j].addCity(j, route_info[min_city][j].price);//连接上最后一段路
			}
		}
	}
	return allRoute[endCity];
}

void Cities::draw_route_in_map(spRoute route)
{
	ofstream myGraph;
	myGraph.open("graph.htm", ios_base::out);
	myGraph << "<!DOCTYPE html><html><head><style type='text/css'>body, html{width: 100%;height: 100%;margin:0;font-family:'微软雅黑';}#allmap{height:100%;width:100%;}#r-result{width:100%;}</style><script type='text/javascript' src='http://api.map.baidu.com/api?v=2.0&ak=nSxiPohfziUaCuONe4ViUP2N'></script><title>Shortest path</title></head><body><div id='allmap'></div></div></body></html><script type='text/javascript'>" << endl;
	myGraph << "var map = new BMap.Map('allmap');" << endl;
	myGraph << "var point = new BMap.Point(0, 0);map.centerAndZoom(point, 2);map.enableScrollWheelZoom(true);" << endl;
	myGraph << "var marker0 = new BMap.Marker(new BMap.Point(" + to_string(city_data[route.way[0]].longitude) + ", " + to_string(city_data[route.way[0]].latitude) + "));map.addOverlay(marker0);" << endl;
	myGraph << "var infoWindow0 = new BMap.InfoWindow(\"<p style = 'font-size:14px;'>country: " + city_data[route.way[0]].country + "<br/>city: " + city_data[route.way[0]].name + "</p>\");marker0.addEventListener(\"click\", function(){this.openInfoWindow(infoWindow0);}); " << endl << endl;
	for (int i = 1; i < route.way.size(); i++) {
		myGraph << "var marker" + to_string(i) + " = new BMap.Marker(new BMap.Point(" + to_string(city_data[route.way[i]].longitude) + ", " + to_string(city_data[route.way[i]].latitude) + "));map.addOverlay(marker" + to_string(i) + ");" << endl;
		myGraph << "var infoWindow" + to_string(i) + " = new BMap.InfoWindow(\"<p style = 'font-size:14px;'>country: " + city_data[route.way[i]].country + "<br/>city: " + city_data[route.way[i]].name + "</p>\");marker" + to_string(i) + ".addEventListener(\"click\", function(){this.openInfoWindow(infoWindow" + to_string(i) + ");}); " << endl;
		myGraph << "var contentString0" + to_string(i) + " = '"+way_in_one_sentense(route.way[i-1], route.way[i])+"';" << endl;
		myGraph << "var path" + to_string(i) + " = new BMap.Polyline([new BMap.Point(" + to_string(city_data[route.way[i-1]].longitude) + ", " + to_string(city_data[route.way[i-1]].latitude) + "),new BMap.Point(" + to_string(city_data[route.way[i]].longitude) + ", " + to_string(city_data[route.way[i]].latitude) + ")], {strokeColor:'#18a45b', strokeWeight:8, strokeOpacity:0.8});map.addOverlay(path" + to_string(i) + ");path" + to_string(i) + ".addEventListener(\"click\", function(){alert(contentString0" + to_string(i) + ");});" << endl;
		myGraph << endl;
	}
	myGraph << "</script>";

}

string Cities::way_in_one_sentense(int start, int end)
{
	Route_h_l thisRoute = route_info[start][end];
	return "乘坐" + thisRoute.way + "，从" + city_data[start].country + "的" + city_data[start].name + "，到" + city_data[end].country + "的" + city_data[end].name + "共用时" + to_string(thisRoute.hours) + "，花费了" + to_string(thisRoute.price) + "美元。" + "更多信息：" + thisRoute.info;
}

bool Cities::verif_consistency()
{
	cout << route_info[search_index("Abu Dhabi")][search_index("Lima")].length;
	return route_info[0][2].isConnected&&!route_info[2][0].isConnected;
}

void Cities::print_cities()
{
	for (int i = 0; i < city_data.size(); i++) {
		cout << i << endl;
		cout << city_data[i].country << "," << city_data[i].name << "," << city_data[i].latitude << "," << city_data[i].longitude << endl;
	}
}

void Cities::print_unvisited()
{
	for (int i = 0; i < city_data.size(); i++) {
		if (!city_data[i].isVisited) {
			cout << i << endl;
			cout << city_data[i].country << "," << city_data[i].name << "," << city_data[i].latitude << "," << city_data[i].longitude << endl;
		}
	}
}

int Cities::countIsolated()
{
	int n = 0;
	for (int i = 0; i < city_data.size(); i++) {
		if (city_data[i].isIsolated) {
			n++;
		}
	}
	return n;
}

void Cities::print_route(spRoute spR)
{
	for (int i = 0; i < spR.way.size()-1; i++) {
		cout << city_data[spR.way[i]].name << "-";
	}
	cout << city_data[spR.way[spR.way.size() - 1]].name << endl << "共花费" << spR.weight << endl << "地图路径已保存在graph.htm中";
	draw_route_in_map(spR);
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

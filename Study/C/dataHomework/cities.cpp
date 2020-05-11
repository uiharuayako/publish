#include "cities.h"
//�����ú���
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
	cout << city_data[num].name << " in the " << city_data[num].country << endl;
}
Cities::Cities()
{
	//��ȡcsv��Ϣ��д���ڴ�
	//������Ϣ
	ifstream this_file("cities.csv");
	string this_line;
	for (int i = 0; getline(this_file, this_line);i++) {
		istringstream sin(this_line);
		City tmpCity;
		tmpCity.id = i;//��id��ֵ
		//����
		getline(sin, tmpCity.country,',');
		//����
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
	//��ʼ���ڽӾ���
	route_info = new Route_h_l*[numCity];//��̬��ά����
	for (int i = 0; i < numCity; i++) {
		route_info[i] = new Route_h_l[numCity];
	}
	
	ifstream route_file("routes.csv");
	string route_line;
	//���������Ϣ
	for (int i = 0; getline(route_file,route_line); i++) {
		istringstream sin(route_line);
		Route tmpRoute;
		getline(sin, tmpRoute.start_city, ',');
		city_data[search_index(tmpRoute.start_city)].neverStart = false;//����������Ͳ�����
		getline(sin, tmpRoute.destination_city, ',');
		city_data[search_index(tmpRoute.destination_city)].neverReturn = false;//����������Ͳ�����
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
		city_data[i].isIsolated = city_data[i].neverReturn && city_data[i].neverStart;//���һ�����мȲ����κ����Ҳ�����κ��յ�
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
	//�ж�����·���������ٴ�
	//����һ����routes�е���Ϣװ�ص���ά�����еĹ���
	for (int i = 0; i < routes.size(); i++) {
		int start_city = search_index(routes[i].start_city);//��ȡ��ʼ�ĳ��к�
		int des_city = search_index(routes[i].destination_city);//��ȡ�����ĳ��к�
		if (start_city == -1 || des_city == -1) {
			cout << "�����жϣ�����������ĳ��У�\n����λ�ã�routes.csv�ĵ�" << i << "��" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//��������
			route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
			route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
			route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
			route_info[start_city][des_city].length = routes[i].length;//��ȡ����
		}
	}
}

void Cities::connect_all_in_time()
{
	int start_city, des_city;
	//�ж�����·���������ٴ�
	//����һ����routes�е���Ϣװ�ص���ά�����еĹ���
	for (int i = 0; i < routes.size(); i++) {
		start_city = search_index(routes[i].start_city);//��ȡ��ʼ�ĳ��к�
		des_city = search_index(routes[i].destination_city);//��ȡ�����ĳ��к�
		if (start_city == -1 || des_city == -1) {
			cout << "�����жϣ�����������ĳ��У�\n����λ�ã�routes.csv�ĵ�" << i << "��" << endl << "�������ݺ��������У����»س����Ӵ����������" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//��������
			route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
			route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
			route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
			route_info[start_city][des_city].length = routes[i].length;//��ȡ����
		}
		else {
			if (routes[i].hours < route_info[start_city][des_city].hours) {
				//�жϣ�����ļ���·��ʱ����ڴ��м�¼�ĸ��̣�������滻
				route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
				route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
				route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
				route_info[start_city][des_city].length = routes[i].length;//��ȡ����
			}
		}
	}
}

void Cities::connect_all_in_price()
{
	int start_city, des_city;
	//�ж�����·���������ٴ�
	//����һ����routes�е���Ϣװ�ص���ά�����еĹ���
	for (int i = 0; i < routes.size(); i++) {
		start_city = search_index(routes[i].start_city);//��ȡ��ʼ�ĳ��к�
		des_city = search_index(routes[i].destination_city);//��ȡ�����ĳ��к�
		if (start_city == -1 || des_city == -1) {
			cout << "�����жϣ�����������ĳ��У�\n����λ�ã�routes.csv�ĵ�" << i << "��" << endl << "�������ݺ��������У����»س����Ӵ����������" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//��������
			route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
			route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
			route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
			route_info[start_city][des_city].length = routes[i].length;//��ȡ����
		}
		else {
			if (routes[i].price < route_info[start_city][des_city].price) {
				//�жϣ�����ļ��м۸���ڴ��м�¼�ĸ����ˣ�������滻
				route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
				route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
				route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
				route_info[start_city][des_city].length = routes[i].length;//��ȡ����
			}
		}
	}
}

void Cities::connect_all_in_time_price(double money)
{
	int start_city, des_city;
	//�ж�����·���������ٴ�
	//����һ����routes�е���Ϣװ�ص���ά�����еĹ���
	for (int i = 0; i < routes.size(); i++) {
		start_city = search_index(routes[i].start_city);//��ȡ��ʼ�ĳ��к�
		des_city = search_index(routes[i].destination_city);//��ȡ�����ĳ��к�
		if (start_city == -1 || des_city == -1) {
			cout << "�����жϣ�����������ĳ��У�\n����λ�ã�routes.csv�ĵ�" << i << "��" << endl << "�������ݺ��������У����»س����Ӵ����������" << endl;
			system("pause");
		}
		if (!route_info[start_city][des_city].isConnected) {
			route_info[start_city][des_city].isConnected = true;//��������
			route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
			route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
			route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
			route_info[start_city][des_city].length = routes[i].length;//��ȡ����
		}
		else {
			if (routes[i].price + routes[i].hours*money < route_info[start_city][des_city].price+ route_info[start_city][des_city].hours+money) {
				//�жϣ�����ļ��м۸���ڴ��м�¼�Ľ��Ȩ�ظ����ˣ����滻���൱�����˸�·�ϵ��󹤷�
				route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
				route_info[start_city][des_city].price = routes[i].price;//��ȡ�۸�
				route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
				route_info[start_city][des_city].length = routes[i].length;//��ȡ����
			}
		}
	}
}

void Cities::DFS(string city_n)
{
	int ves = search_index(city_n);
	clearVisit();//��շ�����Ϣ
	stack<City> s;
	visit(ves);
	s.push(city_data[ves]);
	City tmpCity;
	while (!s.empty()) {
		tmpCity = s.top();//ȡ��ʱ����Ϊջ��
		int i;
		for (i = 0; i < city_data.size(); i++) {
			if (route_info[tmpCity.id][i].isConnected && !city_data[i].isVisited) {
				//������������������ҳ���iδ�����ʹ�
				visit(i);
				s.push(city_data[i]);//i��ջ
				break;//�������forѭ������Ϊ����ȱ���������Ҫ��ȱ���i
			}
		}
		if (i == city_data.size()) {
			s.pop();//������˳������ݵ�ĩβ���򵯳�ĩβ�����ݣ�ջ��LIFO�ṹ������ȳ�
		}
	}
}

spRoute Cities::find_min_time_spRoute(int startCity, int endCity)
{
	spRoute result(startCity, endCity);
	double min_time = route_info[startCity][0].hours;
	int min_city = 0;
	int tmp_start = startCity;
	for (int j = 0; j < city_data.size(); j++) {
		for (int i = 1; i < city_data.size(); i++) {
			if (route_info[tmp_start][i].hours < min_time) {
				min_time = route_info[tmp_start][i].hours;//�����һ����С�����滻
				min_city = i;
			}
		}
		if (min_city == endCity) {
			result.addCity(min_city, route_info[tmp_start][min_city].hours);//��̳�����ջ
			return result;//�������
		}
		//�ҵ���·���е�һ���㣬���ⲻ����������·��
		result.addCity(min_city, route_info[tmp_start][min_city].hours);//��ʱ��̳�����ջ
		//׼����ʼ��һ���ж�
		tmp_start = min_city;//��һ�Σ���֮ǰ�ҵ�����̳��п�ʼ��
		min_time = route_info[min_city][0].hours;//�µĳ�ʼ���ʱ��
		min_city = 0;//��ʼ����̳���
	}
	//һ����˵��ִ�в������У���Ϊ��ǰ���ж���������ܲ��ܱ����ʡ�
	return spRoute(startCity,endCity,true);
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
	for (int i = 0; i < spR.way.size(); i++) {
		cout << city_data[spR.way[i]].name << "-";
	}
	cout << endl << spR.weight;
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

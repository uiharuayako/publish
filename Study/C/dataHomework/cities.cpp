#include "cities.h"
//�����ú���
void string2num(string str, double& num)
{
	stringstream ss;
	ss << str;
	ss >> num;
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
	//�ж�����·���������ٴ�
	//����һ����routes�е���Ϣװ�ص���ά�����еĹ���
	for (int i = 0; i < routes.size(); i++) {
		int start_city = search_index(routes[i].start_city);//��ȡ��ʼ�ĳ��к�
		int des_city = search_index(routes[i].destination_city);//��ȡ�����ĳ��к�
		if (start_city == -1 || des_city == -1) {
			cout << "�����жϣ�����������ĳ��У�\n����λ�ã�routes.csv�ĵ�" << i << "��" << endl;
			system("pause");
		}
		route_info[start_city][des_city].isConnected = true;//��������
		route_info[start_city][des_city].hours = routes[i].hours;//��ȡʱ��
		route_info[start_city][des_city].length = routes[i].length;//��ȡ����
		route_info[start_city][des_city].way = routes[i].way_to_travel;//��ȡ��ͨ��ʽ
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

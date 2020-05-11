#include "cities.h"
int main() {
	Cities a;
	a.connect_all_in_order();
	a.print_route(a.find_min_time_spRoute(a.search_index("Beijing"), a.search_index("Tokyo")));
	a.DFS("Tarawa");
	cout << a.countIsolated();
	a.print_unvisited();
	if (a.verif_consistency()) {
		cout << "1223";
	}
	//a.print_cities();
	//a.print_routes();
	////a.print_connect();
	//cout << a.is_connected_name("Abu Dhabi", "Pretoria(Use Johannesburg)");
}
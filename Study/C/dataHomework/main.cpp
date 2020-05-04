#include "cities.h"
int main() {
	Cities a;
	a.connect_all_in_order();
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
#pragma once
#include "Linear.h"
#include <string>
//程序的目标是构造一个无向图，用邻接表储存，并含有一个深度优先遍历的方法
//由于使用链表，但是又完全不更改链表类的方法，于是添加头文件。后文边date就是该节点的位置

class Graph
{
	typedef struct VNode {
		string data;//顶点信息
		Linear arc;//这里没有完全套用PPT的方法，但是实现的是同样的功能，arcs.Head.data就是第一条边的数据，其地址就是第一条边的指针
		bool isVisited = false;//这里和PPT看起来略微不同，实际上还是相同的，PPT的方法就是把这个isVisited弄成一个另外的数组，我把他集成在顶点信息里也一样
	}AdjList;//顶点结构
	//边就是用单链表储存的，复用了实验一的代码
	int vexNum;//顶点个数
	int arcNum;//边的条数
	int search(string data);//获取信息对应的顶点编号
	AdjList* vertexs;//顶点列表
public:
	Graph(int vn, int an);//初始化，析构就自动完成，没问题
	Graph();//使用PPT中例子初始化
	void printGraph();//打印
	void link(string d1, string d2);//连接两个顶点
	string visit(int index);//访问顶点，返回其信息
	void DFSTraverse();//仿照PPT方法，从v开始递归深度遍历。我这写的无参的，因为都在一个类里。和PPT那个原理完全相同。
	void DFS(int index);//同上
};


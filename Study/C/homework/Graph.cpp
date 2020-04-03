#include "Graph.h"

int Graph::search(string data)
{
	for (int i = 0; i < vexNum; i++)
	{
		if (vertexs[i].data == data)
			return i;
	}
	return -1;
}

Graph::Graph(int vn, int an)
{
	vexNum = vn;
	arcNum = an;
	vertexs = new AdjList[vn];
	for (int i = 0; i < an; i++) {
		cout << "请输入第" << i + 1 << "个顶点的信息:";
		cin >> vertexs[i].data; cout << endl;
	}
	cout << endl << "顶点信息输入完毕！" << endl << "下面输入边的信息。边的输入顺序任意，但每一条边都要输入" << endl;
	for (int i = 0; i < an; i++)
	{
		cout << "请输入第" << i + 1 << "条边的两端顶点信息：“信息1 信息2”，两个信息用空格隔开：";
		string d1, d2;
		cin >> d1 >> d2;
		link(d1, d2);
		cout << endl;
	}
}

Graph::Graph()
{
	vexNum = 8;
	arcNum = 9;
	vertexs = new AdjList[8];
	for (int i = 0; i < 8; i++) {
		vertexs[i].data = "v" + to_string(i+1);
	}
	link("v1", "v2");
	link("v1", "v3");
	link("v2", "v4");
	link("v2", "v5");
	link("v8", "v4");
	link("v8", "v5");
	link("v3", "v6");
	link("v7", "v6");
	link("v3", "v7");
}

void Graph::printGraph()
{
	cout << "所建立的无向图用邻接表表示如下：" << endl << endl;
	for (int i = 0; i < vexNum; i++)
	{
		cout <<"这是角标为"<<i<<"，信息为"<< vertexs[i].data<<"的顶点的边表。用角标代表顶点，表示如下:"<<endl;//先输出顶点信息
		cout << vertexs[i].data << " --> " << vertexs[i].arc.getIndexData(1);
		for (int j = 2; j <= vertexs[i].arc.getSize(); j++)
		{
			cout << " --> " << vertexs[i].arc.getIndexData(j);
		}
		cout << endl << endl;
		cout<<"用信息代表顶点，表示如下:" << endl;//先输出顶点信息
		cout << vertexs[i].data << " --> " <<vertexs[vertexs[i].arc.getIndexData(1)].data;
		for (int j = 2; j <= vertexs[i].arc.getSize(); j++)
		{
			cout << " --> " << vertexs[vertexs[i].arc.getIndexData(j)].data;
		}
		cout << endl << endl;
	}
}

void Graph::link(string d1, string d2)
{
	int v1, v2;
	v1 = search(d1);
	v2 = search(d2);//找位置
	vertexs[v1].arc.add(v2);
	vertexs[v2].arc.add(v1);//添加一条边，无向图是双向的
}

string Graph::visit(int index)
{
	vertexs[index].isVisited = true;
	return vertexs[index].data + " ";
}

void Graph::DFSTraverse()
{
	cout << "深度优先遍历访问结果：";
	//遍历前，把所有顶点置于未访问状态
	for (int i = 0; i < vexNum; i++)
	{
		vertexs[i].isVisited = false;
	}
	for (int i = 0; i < vexNum; i++)
	{
		if (!vertexs[i].isVisited) DFS(i);//如果i未被访问，则访问i
	}
}

void Graph::DFS(int index)
{
	cout << visit(index);
	//查找与当前顶点相连的下一个顶点，为了访问每一个，需要进行指针操作。现在我们注重的是第index个顶点
	vertexs[index].arc.headCur();//重置标识符位置
	do {
		if(!vertexs[vertexs[index].arc.getCurData()].isVisited) DFS(vertexs[index].arc.getCurData());//如果没有访问过，则访问访问当前Cur对应的顶点
		vertexs[index].arc.nextNode();//标识符后移一位
	} while (!vertexs[index].arc.isNull());//标识符非空再次循环
}

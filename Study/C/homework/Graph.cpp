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
		cout << "�������" << i + 1 << "���������Ϣ:";
		cin >> vertexs[i].data; cout << endl;
	}
	cout << endl << "������Ϣ������ϣ�" << endl << "��������ߵ���Ϣ���ߵ�����˳�����⣬��ÿһ���߶�Ҫ����" << endl;
	for (int i = 0; i < an; i++)
	{
		cout << "�������" << i + 1 << "���ߵ����˶�����Ϣ������Ϣ1 ��Ϣ2����������Ϣ�ÿո������";
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
	cout << "������������ͼ���ڽӱ��ʾ���£�" << endl << endl;
	for (int i = 0; i < vexNum; i++)
	{
		cout <<"���ǽǱ�Ϊ"<<i<<"����ϢΪ"<< vertexs[i].data<<"�Ķ���ı߱��ýǱ�����㣬��ʾ����:"<<endl;//�����������Ϣ
		cout << vertexs[i].data << " --> " << vertexs[i].arc.getIndexData(1);
		for (int j = 2; j <= vertexs[i].arc.getSize(); j++)
		{
			cout << " --> " << vertexs[i].arc.getIndexData(j);
		}
		cout << endl << endl;
		cout<<"����Ϣ�����㣬��ʾ����:" << endl;//�����������Ϣ
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
	v2 = search(d2);//��λ��
	vertexs[v1].arc.add(v2);
	vertexs[v2].arc.add(v1);//���һ���ߣ�����ͼ��˫���
}

string Graph::visit(int index)
{
	vertexs[index].isVisited = true;
	return vertexs[index].data + " ";
}

void Graph::DFSTraverse()
{
	cout << "������ȱ������ʽ����";
	//����ǰ�������ж�������δ����״̬
	for (int i = 0; i < vexNum; i++)
	{
		vertexs[i].isVisited = false;
	}
	for (int i = 0; i < vexNum; i++)
	{
		if (!vertexs[i].isVisited) DFS(i);//���iδ�����ʣ������i
	}
}

void Graph::DFS(int index)
{
	cout << visit(index);
	//�����뵱ǰ������������һ�����㣬Ϊ�˷���ÿһ������Ҫ����ָ���������������ע�ص��ǵ�index������
	vertexs[index].arc.headCur();//���ñ�ʶ��λ��
	do {
		if(!vertexs[vertexs[index].arc.getCurData()].isVisited) DFS(vertexs[index].arc.getCurData());//���û�з��ʹ�������ʷ��ʵ�ǰCur��Ӧ�Ķ���
		vertexs[index].arc.nextNode();//��ʶ������һλ
	} while (!vertexs[index].arc.isNull());//��ʶ���ǿ��ٴ�ѭ��
}

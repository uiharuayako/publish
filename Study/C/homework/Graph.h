#pragma once
#include "Linear.h"
#include <string>
//�����Ŀ���ǹ���һ������ͼ�����ڽӱ��棬������һ��������ȱ����ķ���
//����ʹ��������������ȫ������������ķ������������ͷ�ļ������ı�date���Ǹýڵ��λ��

class Graph
{
	typedef struct VNode {
		string data;//������Ϣ
		Linear arc;//����û����ȫ����PPT�ķ���������ʵ�ֵ���ͬ���Ĺ��ܣ�arcs.Head.data���ǵ�һ���ߵ����ݣ����ַ���ǵ�һ���ߵ�ָ��
		bool isVisited = false;//�����PPT��������΢��ͬ��ʵ���ϻ�����ͬ�ģ�PPT�ķ������ǰ����isVisitedŪ��һ����������飬�Ұ��������ڶ�����Ϣ��Ҳһ��
	}AdjList;//����ṹ
	//�߾����õ�������ģ�������ʵ��һ�Ĵ���
	int vexNum;//�������
	int arcNum;//�ߵ�����
	int search(string data);//��ȡ��Ϣ��Ӧ�Ķ�����
	AdjList* vertexs;//�����б�
public:
	Graph(int vn, int an);//��ʼ�����������Զ���ɣ�û����
	Graph();//ʹ��PPT�����ӳ�ʼ��
	void printGraph();//��ӡ
	void link(string d1, string d2);//������������
	string visit(int index);//���ʶ��㣬��������Ϣ
	void DFSTraverse();//����PPT��������v��ʼ�ݹ���ȱ���������д���޲εģ���Ϊ����һ�������PPT�Ǹ�ԭ����ȫ��ͬ��
	void DFS(int index);//ͬ��
};


#include <stdlib.h>
#include <stdio.h>
#include <iostream>
#include <queue>
#include <string>

using namespace std;

#define MAX 100e6

class Route
{
public:
    string m_begin;
    string m_end;
    string m_tran;
    string m_other;

    float m_cost;
    float m_time;
};

class Graph 
{
public:
    Route *r;
    float m_cost;
    float m_time;
    Graph() { m_cost = m_time = MAX; r = NULL; }
    ~Graph() {};
};

class City
{
public:
    string m_country;
    string m_city;

    float m_latitude;
    float m_longitude;
};

int isTrav[199] = { 0 };
Graph MatrixGraph[199][199];
City city[199];
Route route[1975];

void ReadCitise(const char *filename, City *ct) //读取两个csv文件
{
    FILE *fp = fopen(filename, "r");
    if(!fp) { printf("无法打开"); }
    else {
        char ch;
        int i = 0;
        while (!feof(fp) && i < 199)
        {
            ch = fgetc(fp);
            for (; ch != ','; ch = fgetc(fp))
            {
                ct[i].m_country += ch;
            }
            ch = fgetc(fp);
            for (; ch != ','; ch = fgetc(fp)) 
            {
                ct[i].m_city += ch;
            }
            fscanf(fp, "%f,",  &ct[i].m_latitude);
            fscanf(fp, "%f\n", &ct[i].m_longitude);
            i++;
        }
    }
    fclose(fp);
}

void ReadRoutes(const char* filename, Route *routes) 
{
    FILE* fp = fopen(filename, "r");
    if (!fp) { cout << "无法打开" << endl; }
    else
    {
        char ch;
        int i = 0;
        while (!feof(fp) && i < 1975)
        {
            ch = fgetc(fp);
            while (ch != ',')
            {
                routes[i].m_begin += ch;
                ch = fgetc(fp);
            }

            ch = fgetc(fp);
            while (ch != ',')
            {
                routes[i].m_end += ch;
                ch = fgetc(fp);
            }

            ch = fgetc(fp);
            while (ch != ',')
            {
                routes[i].m_tran += ch;
                ch = fgetc(fp);
            }

            fscanf(fp, "%f,", &routes[i].m_time);
            fscanf(fp, "%f\n", &routes[i].m_cost);

            ch = fgetc(fp);
            while (ch != '\n')
            {
                routes[i].m_other += ch;
                ch = fgetc(fp);                
            }
            i++;
        }
    }
    fclose(fp);
}



int Locate(string s, City* ct)
{
    for (int i = 0; i < 199; i++) {
        if (ct[i].m_city == s) {
            return i;
        }
    }
    return -1;
}

void CreateMatrixGraph() {
    for (int i = 0; i < 199; i++) {
        for (int j = 0; j < 199; j++) {
            if (i == j) {
                MatrixGraph[i][j].m_cost = MatrixGraph[i][j].m_time = 0;
            }
            else {
                MatrixGraph[i][j].m_cost = MAX;
                MatrixGraph[i][j].m_time = MAX;
            }
        }
    }
    for (int i = 0; i < 1975; i++) {
        int begin, end;
        begin = Locate(route[i].m_begin, city);
        end = Locate(route[i].m_end, city);
        MatrixGraph[begin][end].m_cost = route[i].m_cost;
        MatrixGraph[begin][end].m_time = route[i].m_time;
        MatrixGraph[begin][end].r = &route[i];
    }
}

void BFSfunction(int i)
{
    int j;
    queue<int>Q;
    isTrav[i] = 1;
    cout << "->" << city[i].m_city;
    Q.push(i);
    while (!Q.empty())
    {
        Q.pop();
        for (j = 0; j < 199; j++)
        {
            if (MatrixGraph[i][j].m_cost != 10000 && !isTrav[j])
            {
                cout << "->" << city[j].m_city;
                isTrav[j] = 1;
                Q.push(j);
            }
        }
    }
}

void BFSTraverse(int pos)
{
    int i;
    for (i = 0; i < 199; i++)
        isTrav[i] = 0;
    for (i = 0; i < 199; i++)
    {
        if (!isTrav[(i + pos) % 199])
            BFSfunction((i + pos) % 199);
    }
}

void ShortestPath(int pre[], int begin, float time[])//最短路径
{
    int i, j, k;
    float min;//时间最小值
    float tem;//暂存最小值
    int get[199];
    //所有值
    for (i = 0; i < 199; i++)
    {
        get[i] = 0;
        pre[i] = -1;
        time[i] = MatrixGraph[begin][i].m_time;
    }
    get[begin] = 1;
    time[begin] = 0;
    for (i = 0; i < 199; i++)
    {
        if (time[i] != 0 && time[i] != 10000)
            pre[i] = begin;
    }
    for (i = 0; i < 198; i++)
    {
        min = MAX;
        for (j = 0; j < 199; j++)
        {
            if (get[j] == 0 && time[j] < min)
            {
                min = time[j];
                k = j;
            }
        }
        get[k] = 1;
        for (j = 0; j < 199; j++)
        {
            if (j != k && MatrixGraph[k][j].m_time < 10000)
            {
                tem = (time[k] + MatrixGraph[k][j].m_time);
                if (get[j] == 0 && (tem < time[j]))
                {
                    time[j] = tem;
                    pre[j] = k;
                }
            }
        }
    }
}


int main()
{
    ReadCitise("cities.csv", city);   //读取城市
    ReadRoutes("routes.csv", route);  //读取道路

    CreateMatrixGraph();              //创建矩阵

    //获得起始和结束
    string city_from, city_to;
    cout << "Please input city of departure:" << endl;
    cin >> city_from;

    cout << "Please input destination:" << endl;
    cin >> city_to;

    //city_from = "Beijing";
    //city_to = "London";

    //计算最短路径
    int pre[199] = { 0 };
    float time[199] = { 0 };
    int begin = Locate(city_from, city);
    int end = Locate(city_to, city);
    ShortestPath(pre,begin,time);

    cout << "The Fastest way is" << endl;
    cout << city_from << endl;
    cout << city_to << endl;
    cout << "It takes " << time[end] << " hours in total" << endl;

    cout << "Breadth first:" << endl;
    BFSTraverse(begin);
}
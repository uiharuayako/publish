#include <iostream>
using namespace std;
typedef struct HTNode {
    int weight;//���Ȩ��
    int parent, left, right;//����㡢���ӡ��Һ����������е�λ���±�
}*HuffmanTree;//�����������ṹ

//HFT�����д��һ��������������һ�����������㣬end��ʾHFT�����д�Ž��ĸ�����s1��s2���ݵ���HFT������Ȩ��ֵ��С����������������е�λ��
void Select(HuffmanTree HFT, int end, int* s1, int* s2)
{
    int min1, min2;//min1����������н�С��Ȩ�أ�min2����������нϴ��Ȩ�أ�s1��s2��֮��Ӧ
    //���������ʼ�±�Ϊ 1
    int i = 1;
    //�ҵ���û�������Ľ�㣬���и���㣬���������ظ��˹����ҵ�û�и����Ľ��
    while (HFT[i].parent != 0 && i <= end) {
        i++;
    }
    //��һ�����Ȱѵ�һ��û�й������Ľ���Ȩ��min1��ͬʱ�����λ�ø�s1
    min1 = HFT[i].weight;
    *s1 = i;
    i++;
    //�ڶ������ҵ��ڶ���û�й������Ľ��
    while (HFT[i].parent != 0 && i <= end) {
        i++;
    }
    //���ҵ�����������Ȩ�ȴ�С��min2Ϊ��ģ�min1ΪС��
    if (HFT[i].weight < min1) {
        min2 = min1;//����ڶ�������Ȩ�رȵ�һ����С���Ͱ�min1��λ���ó������Լ���min2
        *s2 = *s1;//ͬ��
        min1 = HFT[i].weight;//�������ҵ��ĵڶ�������Ȩ��ֵ��min1
        *s1 = i;
    }
    else {
        min2 = HFT[i].weight;//������ڵ��ڣ������·���
        *s2 = i;
    }
    //�������ͺ���������δ���������Ľ�����Ƚ�
    for (int j = i + 1; j <= end; j++)
    {
        //����и���㣬ֱ��������������һ��
        if (HFT[j].parent != 0) {
            continue;
        }
        //�������С�Ļ�С����min2=min1��min1��ֵ�µĽ����±�
        if (HFT[j].weight < min1) {
            min2 = min1;
            min1 = HFT[j].weight;
            *s2 = *s1;
            *s1 = j;
        }
        //�����������֮�䣬min2��ֵΪ�µĽ���λ���±�
        else if (HFT[j].weight >= min1 && HFT[j].weight < min2) {
            min2 = HFT[j].weight;
            *s2 = j;
        }
    }
}
//ģ��ppt������������������������û�����á�HTΪһ������wΪȨ�����飬nΪ������
void CreateHuffmanTree(HuffmanTree* HT, int* w, int n)
{
    if (n <= 1) return; // ���ֻ��һ��������൱��0
    int m = 2 * n - 1; // ���������ܽڵ�����n����Ҷ�ӽ��
    *HT = new HTNode[n + 1]; // 0��λ�ò��ã���1��n
    HuffmanTree p = *HT;//pΪ�����ý��
    // ��ʼ�����������е����н�㣬����n����Ȩ��Ϊw������Ӧλ���е�ֵ
    for (int i = 1; i <= n; i++)
    {
        (p + i)->weight = w[i - 1];
        (p + i)->parent = 0;
        (p + i)->left = 0;
        (p + i)->right = 0;
    }
    //�������±� n+1 ��ʼ��ʼ�����������г�Ҷ�ӽ����Ľ��
    for (int i = n + 1; i <= m; i++)
    {
        (p + i)->weight = 0;
        (p + i)->parent = 0;
        (p + i)->left = 0;
        (p + i)->right = 0;
    }
    //����������������n+1��ʼ����Ϊ��һ��Ҷ�ӽ������Ľ��
    //ѭ��������ÿһ���½���Ȩ��Ϊ������С��Ҷ�ӽ��֮��
    for (int i = n + 1; i <= m; i++)
    {
        int s1, s2;
        Select(*HT, i - 1, &s1, &s2);//��ʱ��n+1��ȨΪ0����������������Χ
        (*HT)[s1].parent = (*HT)[s2].parent = i;//�趨s1,s2�ĸ����
        (*HT)[i].left = s1;//�趨���������ܱ���С
        (*HT)[i].right = s2;
        (*HT)[i].weight = (*HT)[s1].weight + (*HT)[s2].weight;//�趨Ȩֵ
    }
}
//HTΪ���ɵĹ���������HCΪ�洢������������Ķ�ά��̬���飬nΪ���ĸ���
void HuffmanCoding(HuffmanTree HT, char*** HC, int n) {
    *HC = new char* [n + 1];//��ʼ��n+1���ַ�������Ȼ�����Ϊ0�Ĳ��ã�����ʵ�����õľ�n��
    char* cd = new char[n]; //ģ��PPT��������ʼ����ŵ�����������������ַ������飬���ò�˵���Ҿ���...�е��˷ѿռ䣿
    cd[n - 1] = '\0';//�ַ���������

    for (int i = 1; i <= n; i++) {
        //��Ҷ�ӽ��������õ��Ĺ���������������ģ���Ҫ���ַ���������������
        int start = n - 1; //start��ʼ���ǽ�����
        //cΪ��ǰ��������е�λ��
        //fΪ��ǰ���ĸ���������е�λ�ã�׷�ݵ�Դͷʱ����Ϊ���ڵ�0����ʵ�͹���˵���Ǹ��ڵ㣬ֻ������ڵ��parentĬ��Ϊ0����û�б����¸�ֵ
        int c = i; int f = HT[c].parent;
        //ÿ�ΰѸ���㵱�����ڵĽڵ㣬������������
        //Ѱ�ҵ������ʱֹͣ��Ȼ�����µĸ����
        for (; f != 0 ; c = f, f = HT[f].parent ) {
            //���ò�˵����γ�����Ƶ�̫�����ˡ���startһ���������ű��룬ÿ��-1��֮���ٴ�start��ʼ���ƣ��ͱ��������
            //�����ļ����ȷʵ�����˱��֮����
            if (HT[f].left == c) cd[--start] = '0';//����ý���Ǹ������������Ӧ·������Ϊ0
            else cd[--start] = '1';//����Ϊ1          
        }
        //����ѭ����cd�����д��±� start ��ʼ����ŵľ��Ǹý��Ĺ���������
        (*HC)[i] = new char[n - start+1];
        strcpy_s((*HC)[i], n - start + 1, &cd[start]);
    }
    delete[] cd;
    cd = nullptr;
}
void printHT(char** HC, int* w, int n) {
    for (int i = 0; i < n; i++)
    {
        cout << w[i] << "�ı���Ϊ��" << HC[i + 1] << endl;
    }
}
void main()
{
    int w[5] = { 2, 8, 7, 6, 5 };
    int n = 5;
    HuffmanTree htree;
    char** htable;
    CreateHuffmanTree(&htree, w, n);
    HuffmanCoding(htree, &htable, n);
    printHT(htable, w, n);
    delete[] htree;
    delete[] htable;
    htable = nullptr;
    htree = nullptr;
    //return 0;
}
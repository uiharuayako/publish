#include <iostream>
using namespace std;
typedef struct HTNode {
    int weight;//结点权重
    int parent, left, right;//父结点、左孩子、右孩子在数组中的位置下标
}*HuffmanTree;//哈夫曼树结点结构

//HFT数组中存放一个哈夫曼树，即一组哈夫曼树结点，end表示HFT数组中存放结点的个数，s1和s2传递的是HFT数组中权重值最小的两个结点在数组中的位置
void Select(HuffmanTree HFT, int end, int* s1, int* s2)
{
    int min1, min2;//min1：两个结点中较小的权重，min2：两个结点中较大的权重，s1，s2与之对应
    //遍历数组初始下标为 1
    int i = 1;
    //找到还没构建树的结点，如有父结点，则跳过，重复此过程找到没有父结点的结点
    while (HFT[i].parent != 0 && i <= end) {
        i++;
    }
    //第一步，先把第一个没有构建树的结点的权给min1，同时把这个位置给s1
    min1 = HFT[i].weight;
    *s1 = i;
    i++;
    //第二步，找到第二个没有构建树的结点
    while (HFT[i].parent != 0 && i <= end) {
        i++;
    }
    //对找到的两个结点的权比大小，min2为大的，min1为小的
    if (HFT[i].weight < min1) {
        min2 = min1;//如果第二个结点的权重比第一个还小，就把min1的位置让出来，自己当min2
        *s2 = *s1;//同上
        min1 = HFT[i].weight;//把我们找到的第二个结点的权赋值给min1
        *s1 = i;
    }
    else {
        min2 = HFT[i].weight;//如果大于等于，则无事发生
        *s2 = i;
    }
    //两个结点和后续的所有未构建成树的结点做比较
    for (int j = i + 1; j <= end; j++)
    {
        //如果有父结点，直接跳过，进行下一个
        if (HFT[j].parent != 0) {
            continue;
        }
        //如果比最小的还小，将min2=min1，min1赋值新的结点的下标
        if (HFT[j].weight < min1) {
            min2 = min1;
            min1 = HFT[j].weight;
            *s2 = *s1;
            *s1 = j;
        }
        //如果介于两者之间，min2赋值为新的结点的位置下标
        else if (HFT[j].weight >= min1 && HFT[j].weight < min2) {
            min2 = HFT[j].weight;
            *s2 = j;
        }
    }
}
//模仿ppt方法，创建哈夫曼树，但是没用引用。HT为一棵树，w为权重数组，n为结点个数
void CreateHuffmanTree(HuffmanTree* HT, int* w, int n)
{
    if (n <= 1) return; // 如果只有一个编码就相当于0
    int m = 2 * n - 1; // 哈夫曼树总节点数，n就是叶子结点
    *HT = new HTNode[n + 1]; // 0号位置不用，从1到n
    HuffmanTree p = *HT;//p为操作用结点
    // 初始化哈夫曼树中的所有结点，共有n个，权重为w数组相应位置中的值
    for (int i = 1; i <= n; i++)
    {
        (p + i)->weight = w[i - 1];
        (p + i)->parent = 0;
        (p + i)->left = 0;
        (p + i)->right = 0;
    }
    //从树的下标 n+1 开始初始化哈夫曼树中除叶子结点外的结点
    for (int i = n + 1; i <= m; i++)
    {
        (p + i)->weight = 0;
        (p + i)->parent = 0;
        (p + i)->left = 0;
        (p + i)->right = 0;
    }
    //构建哈夫曼树，从n+1开始，其为第一个叶子结点以外的结点
    //循环构建，每一个新结点的权均为两个最小的叶子结点之和
    for (int i = n + 1; i <= m; i++)
    {
        int s1, s2;
        Select(*HT, i - 1, &s1, &s2);//这时候n+1的权为0，不被纳入搜索范围
        (*HT)[s1].parent = (*HT)[s2].parent = i;//设定s1,s2的父结点
        (*HT)[i].left = s1;//设定左子树，总比右小
        (*HT)[i].right = s2;
        (*HT)[i].weight = (*HT)[s1].weight + (*HT)[s2].weight;//设定权值
    }
}
//HT为生成的哈夫曼树，HC为存储结点哈夫曼编码的二维动态数组，n为结点的个数
void HuffmanCoding(HuffmanTree HT, char*** HC, int n) {
    *HC = new char* [n + 1];//初始化n+1个字符串，当然，编号为0的不用，所以实际上用的就n个
    char* cd = new char[n]; //模仿PPT方法，初始化存放单个结点哈夫曼编码的字符串数组，不得不说，我觉得...有点浪费空间？
    cd[n - 1] = '\0';//字符串结束符

    for (int i = 1; i <= n; i++) {
        //从叶子结点出发，得到的哈夫曼编码是逆序的，需要在字符串数组中逆序存放
        int start = n - 1; //start开始就是结束符
        //c为当前结点在树中的位置
        //f为当前结点的父结点在树中的位置，追溯到源头时，必为根节点0，其实客观上说不是根节点，只是这个节点的parent默认为0，且没有被重新赋值
        int c = i; int f = HT[c].parent;
        //每次把父结点当成现在的节点，向着树根遍历
        //寻找到根结点时停止，然后找新的父结点
        for (; f != 0 ; c = f, f = HT[f].parent ) {
            //不得不说，这段程序设计的太精妙了。从start一个个倒序存放编码，每次-1，之后再从start开始复制，就变成了正序！
            //其代码的简洁性确实体现了编程之美！
            if (HT[f].left == c) cd[--start] = '0';//如果该结点是父结点的左孩子则对应路径编码为0
            else cd[--start] = '1';//否则，为1          
        }
        //跳出循环后，cd数组中从下标 start 开始，存放的就是该结点的哈夫曼编码
        (*HC)[i] = new char[n - start+1];
        strcpy_s((*HC)[i], n - start + 1, &cd[start]);
    }
    delete[] cd;
    cd = nullptr;
}
void printHT(char** HC, int* w, int n) {
    for (int i = 0; i < n; i++)
    {
        cout << w[i] << "的编码为：" << HC[i + 1] << endl;
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
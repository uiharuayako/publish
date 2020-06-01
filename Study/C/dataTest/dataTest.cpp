// dataTest.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
using namespace std;
struct Node {
	Node* next;
	int data;
	Node() {
		next = nullptr;
		data = 0;
	}//初始化一个新结点
	Node(int d) {
		next = nullptr;
		data = d;
	}
};//结点结构

typedef struct HTNode {
    int weight;//结点权重
	HTNode* lChild;
	HTNode* rChild;
}*HuffmanTree;//哈夫曼树链式结构
void HuffmanTreeConstructing(HuffmanTree& T, int* s, int n)
{
	HuffmanTree* hfForest = new HuffmanTree[n];
	HuffmanTree tempTree = nullptr;
	// 根据指定的权值数列，构造n棵仅有一个根节点的二叉树。这n棵而车数构成集合F。每棵树根节点的权值均与权值数列中的元素对应
	for (int i = 0; i < n; i++) {
		hfForest[i] = new HTNode;
		hfForest[i]->weight = s[i];
		hfForest[i]->lChild = nullptr;
		hfForest[i]->rChild = nullptr;
	}
	// 选取F中根结点的权值最小的两棵二叉树，分别作为左、右子树构造一棵新的二叉树，将这棵新的二叉树根结点的权值为其左、右子树根结点的权值之和。
	int min1, min2, cur;// 分别是最小的，第二小的，cur用于临时储存对比用的权重
	for (int i = 0; i < n-1;i++) {
		cur = 999;
		// 找权重最小的
		for (int i = 0; i < n; i++) {
			// 第一个判断条件是在验证这个树是否存在
			if (hfForest[i] && cur > hfForest[i]->weight) {
				cur = hfForest[i]->weight;
				min1 = i;
			}
		}
		cur = 999;
		// 找权重倒数第二小的
		for (int i = 0; i < n; i++) {
			if (hfForest[i] && cur > hfForest[i]->weight && i != min1) {
				cur = hfForest[i]->weight;
				min2 = i;
			}
		}
		tempTree = new HTNode;
		tempTree->weight = hfForest[min1]->weight + hfForest[min2]->weight;
		tempTree->lChild = hfForest[min1];
		tempTree->rChild = hfForest[min2];
		// 将新子树加入
		hfForest[min1] = tempTree;
		// 将旧子树置空
		hfForest[min2] = nullptr;
	}
	T = tempTree;
}
void printTree(HuffmanTree ht) {
	int i;
	if (ht) {
		cout << ht->weight << endl;
		printTree(ht->lChild);
		printTree(ht->rChild);
	}
}
void print(int n, Node* node) {
	for (int i = 0; i < n; i++) {
		cout << node->data << endl;
		node = node->next;
	}
}
int ComputeBiTreeDepth(HuffmanTree T)
{
	if (!T) return 0;// 当T为空指针，这个树就不存在，深度0
	// 获取左子树深度
	int left = ComputeBiTreeDepth(T->lChild);
	// 获取右子树深度
	int right = ComputeBiTreeDepth(T->rChild);
	// 树是其最深子树的深度+1
	return (left >= right ? left : right) + 1;
}
Node* init(int* nums, int n) {
	Node* headNode = new Node(nums[0]);
	Node* curNode = new Node(nums[1]);
	headNode->next = curNode;
	for (int i = 0; i < n - 2; i++) {
		Node* tmpNode = new Node(nums[2 + i]);
		curNode->next = tmpNode;
		curNode = curNode->next;
	}
	return headNode;
}
void RemoveDuplicates(struct Node* head, int s[], int n)
{
	if (head == NULL)
		return;
	Node* pre = NULL, * cur = head;
	for (int i = 0; i < n; i++)
		s[i] = false;
	while (cur != NULL) {
		if (!s[(cur->data) - '0']) {
			s[(cur->data) - '0'] = true;
			pre = cur;
			cur = cur->next;
		}
		else {
			pre->next = cur->next;
			delete cur;
			cur = pre->next;
		}
	}
}
void RemoveDuplicates1(Node* head, int s[], int n)
{
	// 由该题性质，结合边权值特性，链表中可能出现的数字不会太大，且均为整数。
	// 该方法的思路为，创建一个足够大的布尔数组，true表示该下标对应的数字出现过，false表示未出现过，来实现判断
	bool isAppear[100] = { false };// 开辟一个bool数组
	s[0] = head->data;// 将第一个元素放入s
	int k = 1;// k为s的长度，也为其下标
	isAppear[head->data] = true;
	Node* pre = head;// 定义上一个指针
	Node* cur = head->next;// 定义一个可移动指针
	while(cur!=nullptr) {
		if (isAppear[cur->data]) {
			// 当，当前节点的下一个节点的数据出现过
			pre->next = cur->next;// 删除该节点
			cur = cur->next;
		}
		else {
			// 没出现过，加以记录
			s[k] = cur->data;
			k++;
			isAppear[cur->data] = true;
			pre = pre->next;
			cur = cur->next;
		}
	}
}
void InsertSort(int s[], int n)
{
	// 首先，传进来的数组s，其s[0]是保存了有意义的信息的，因此我们需要将s整体后移一位
	// 才能空出s[0]当哨兵
	for (int i = n; i>0; i--) {
		s[i] = s[i - 1];
	}
	int i, j;
	for (i = 2; i <= n; i++) {
		s[0] = s[i];//将待插入元素存放到监视哨中
		for (j = i - 1; s[j] > s[0]; j--)
			s[j + 1] = s[j];//将大于待插入元素的全部向后移一个
		s[j + 1] = s[0];//插入待插入元素
	}
	// 最后，把数组还原
	for (int i = 0; i < n; i++) {
		s[i] = s[i + 1];
	}
}
int main()
{
	int a[8] = { 5, 3, 4, 2, 5, 4, 1, 3 };
	Node* n = init(a, 8);
	print(8, n);
	int* s = new int[100];// 为顺序数组开辟足够大的空间;

	RemoveDuplicates1(n, s, 8);
	cout << endl;
	print(5, n);


	InsertSort(s, 5);
	for (int i = 0; i < 5; i++) {
		cout << s[i] << endl;
	}
	cout << "=======" << endl;
	HuffmanTree ht;
	HuffmanTreeConstructing(ht, s, 5);
	printTree(ht);
	cout << "深度" << ComputeBiTreeDepth(ht);
}

// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门使用技巧: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件


// ScoreSheetView.cpp: CScoreSheetView 类的实现
//

#include "pch.h"
#include "framework.h"
// SHARED_HANDLERS 可以在实现预览、缩略图和搜索筛选器句柄的
// ATL 项目中进行定义，并允许与该项目共享文档代码。
#ifndef SHARED_HANDLERS
#include "ScoreSheet.h"
#endif

#include "ScoreSheetDoc.h"
#include "ScoreSheetView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CScoreSheetView

IMPLEMENT_DYNCREATE(CScoreSheetView, CView)

BEGIN_MESSAGE_MAP(CScoreSheetView, CView)
	// 标准打印命令
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CView::OnFilePrintPreview)
END_MESSAGE_MAP()

// CScoreSheetView 构造/析构

CScoreSheetView::CScoreSheetView() noexcept
{
	// TODO: 在此处添加构造代码

}

CScoreSheetView::~CScoreSheetView()
{
}

BOOL CScoreSheetView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改
	//  CREATESTRUCT cs 来修改窗口类或样式

	return CView::PreCreateWindow(cs);
}

// CScoreSheetView 绘图

void CScoreSheetView::OnDraw(CDC* /*pDC*/)
{
	CScoreSheetDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: 在此处为本机数据添加绘制代码
}


// CScoreSheetView 打印

BOOL CScoreSheetView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 默认准备
	return DoPreparePrinting(pInfo);
}

void CScoreSheetView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加额外的打印前进行的初始化过程
}

void CScoreSheetView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加打印后进行的清理过程
}


// CScoreSheetView 诊断

#ifdef _DEBUG
void CScoreSheetView::AssertValid() const
{
	CView::AssertValid();
}

void CScoreSheetView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CScoreSheetDoc* CScoreSheetView::GetDocument() const // 非调试版本是内联的
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CScoreSheetDoc)));
	return (CScoreSheetDoc*)m_pDocument;
}
#endif //_DEBUG


// CScoreSheetView 消息处理程序

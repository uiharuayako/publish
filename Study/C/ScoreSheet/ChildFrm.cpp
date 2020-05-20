
// ChildFrm.cpp: CChildFrame 类的实现
//

#include "pch.h"
#include "framework.h"
#include "ScoreSheet.h"
#include "ScoreSheetView.h"
#include "CScoreListView.h"
#include "ChildFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

// CChildFrame

IMPLEMENT_DYNCREATE(CChildFrame, CMDIChildWnd)

BEGIN_MESSAGE_MAP(CChildFrame, CMDIChildWnd)
	ON_WM_SIZE()
END_MESSAGE_MAP()

// CChildFrame 构造/析构

CChildFrame::CChildFrame() noexcept
{
	// TODO: 在此添加成员初始化代码
}

CChildFrame::~CChildFrame()
{
}


BOOL CChildFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改 CREATESTRUCT cs 来修改窗口类或样式
	if( !CMDIChildWnd::PreCreateWindow(cs) )
		return FALSE;

	cs.style = WS_CHILD | WS_VISIBLE | WS_OVERLAPPED | WS_CAPTION | WS_SYSMENU
		| FWS_ADDTOTITLE | WS_THICKFRAME | WS_MINIMIZEBOX | WS_MAXIMIZEBOX | WS_MAXIMIZE;

	return TRUE;
}

// CChildFrame 诊断

#ifdef _DEBUG
void CChildFrame::AssertValid() const
{
	CMDIChildWnd::AssertValid();
}

void CChildFrame::Dump(CDumpContext& dc) const
{
	CMDIChildWnd::Dump(dc);
}
#endif //_DEBUG

// CChildFrame 消息处理程序


BOOL CChildFrame::OnCreateClient(LPCREATESTRUCT lpcs, CCreateContext* pContext)
{
	// TODO: 在此添加专用代码和/或调用基类
	CRect rect;
	GetClientRect(&rect);

	if (!m_wndSplitter.CreateStatic(this, 1, 2)) return FALSE;

	if (!m_wndSplitter.CreateView(0, 0, RUNTIME_CLASS(CScoreSheetView), CSize(rect.Width() / 2, rect.Height()/2), pContext))
		return FALSE;
	if (!m_wndSplitter.CreateView(0, 1, RUNTIME_CLASS(CScoreListView), CSize(rect.Width() / 2, rect.Height() / 2), pContext))
		return FALSE;
	m_bSplitterCreated = TRUE;
	return TRUE;
}


void CChildFrame::OnSize(UINT nType, int cx, int cy)
{
	CMDIChildWnd::OnSize(nType, cx, cy);
	CRect rect;
	GetClientRect(&rect);
	// TODO: 在此处添加消息处理程序代码
	if (m_bSplitterCreated) {
		m_wndSplitter.SetColumnInfo(0, rect.Width() / 2, 1);
		m_wndSplitter.SetColumnInfo(1, rect.Width() / 2, 1);
		m_wndSplitter.RecalcLayout();
	}
}

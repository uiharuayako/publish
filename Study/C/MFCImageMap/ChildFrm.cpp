
// ChildFrm.cpp: CChildFrame 类的实现
//

#include "pch.h"
#include "framework.h"
#include "MFCImageMap.h"

#include "ChildFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

// CChildFrame

IMPLEMENT_DYNCREATE(CChildFrame, CMDIChildWndEx)

BEGIN_MESSAGE_MAP(CChildFrame, CMDIChildWndEx)
	ON_UPDATE_COMMAND_UI(ID_INDICATOR_COORD, &CChildFrame::OnUpdateIndicatorCoord)
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
	if( !CMDIChildWndEx::PreCreateWindow(cs) )
		return FALSE;

	return TRUE;
}

// CChildFrame 诊断

#ifdef _DEBUG
void CChildFrame::AssertValid() const
{
	CMDIChildWndEx::AssertValid();
}

void CChildFrame::Dump(CDumpContext& dc) const
{
	CMDIChildWndEx::Dump(dc);
}
#endif //_DEBUG

// CChildFrame 消息处理程序


void CChildFrame::OnUpdateIndicatorCoord(CCmdUI* pCmdUI)
{
	// TODO: 在此添加命令更新用户界面处理程序代码
	//CPoint point;
	//GetCursorPos(&point);
	//ScreenToClient(&point);
	//CString strPoint;
	//strPoint.Format(L"坐标（X: %d, Y: %d）", point.x, point.y);
	//pCmdUI->SetText(strPoint);
}

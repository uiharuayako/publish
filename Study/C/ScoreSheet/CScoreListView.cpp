// CScoreListView.cpp: 实现文件
//

#include "pch.h"
#include "ScoreSheet.h"
#include "CScoreListView.h"


// CScoreListView

IMPLEMENT_DYNCREATE(CScoreListView, CListView)

CScoreListView::CScoreListView()
{

}

CScoreListView::~CScoreListView()
{
}

BEGIN_MESSAGE_MAP(CScoreListView, CListView)
END_MESSAGE_MAP()


// CScoreListView 诊断

#ifdef _DEBUG
void CScoreListView::AssertValid() const
{
	CListView::AssertValid();
}

#ifndef _WIN32_WCE
void CScoreListView::Dump(CDumpContext& dc) const
{
	CListView::Dump(dc);
}
#endif
#endif //_DEBUG


// CScoreListView 消息处理程序

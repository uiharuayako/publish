#pragma once

// CScoreListView 视图

class CScoreListView : public CListView
{
	DECLARE_DYNCREATE(CScoreListView)

protected:
	CScoreListView();           // 动态创建所使用的受保护的构造函数
	virtual ~CScoreListView();

public:
#ifdef _DEBUG
	virtual void AssertValid() const;
#ifndef _WIN32_WCE
	virtual void Dump(CDumpContext& dc) const;
#endif
#endif

protected:
	DECLARE_MESSAGE_MAP()
};



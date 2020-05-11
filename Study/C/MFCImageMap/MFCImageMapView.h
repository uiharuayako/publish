
// MFCImageMapView.h: CMFCImageMapView 类的接口
//

#pragma once


class CMFCImageMapView : public CScrollView
{
protected: // 仅从序列化创建
	CMFCImageMapView() noexcept;
	DECLARE_DYNCREATE(CMFCImageMapView)

	BITMAPINFO* m_lpBitmap;
// 特性
public:
	CMFCImageMapDoc* GetDocument() const;

// 操作
public:

// 重写
public:
	virtual void OnDraw(CDC* pDC);  // 重写以绘制该视图
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual void OnInitialUpdate(); // 构造后第一次调用
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// 实现
public:
	virtual ~CMFCImageMapView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 生成的消息映射函数
protected:
	afx_msg void OnFilePrintPreview();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnContextMenu(CWnd* pWnd, CPoint point);
	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnUpdateIndicatorCoord(CCmdUI* pCmdUI);
};

#ifndef _DEBUG  // MFCImageMapView.cpp 中的调试版本
inline CMFCImageMapDoc* CMFCImageMapView::GetDocument() const
   { return reinterpret_cast<CMFCImageMapDoc*>(m_pDocument); }
#endif

BOOL CreateLPBitmapInfo(BITMAPINFO** lpBitmapInfo, int nCols, int nRows, int nColor);

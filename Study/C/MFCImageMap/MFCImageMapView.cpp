﻿
// MFCImageMapView.cpp: CMFCImageMapView 类的实现
//

#include "pch.h"
#include "framework.h"
// SHARED_HANDLERS 可以在实现预览、缩略图和搜索筛选器句柄的
// ATL 项目中进行定义，并允许与该项目共享文档代码。
#ifndef SHARED_HANDLERS
#include "MFCImageMap.h"
#endif

#include "MFCImageMapDoc.h"
#include "MFCImageMapView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

BOOL CreateLPBitmapInfo(BITMAPINFO** lpBitmapInfo, int nCols, int nRows, int nColor) {
	BITMAPINFO** lpbi = lpBitmapInfo;
	if ((*lpbi)) delete[](*lpbi);
	(*lpbi) = nullptr;

	if (nColor == 1) (*lpbi) = (BITMAPINFO*)new BYTE[sizeof(BITMAPINFOHEADER) + 256 * sizeof(RGBQUAD)];
	else (*lpbi) = (BITMAPINFO*)new BYTE[sizeof(BITMAPINFOHEADER)];

	if (nColor == 1) {
		for (int k = 0; k <= 255; k++) {
			(*lpbi)->bmiColors[k].rgbRed = k;
			(*lpbi)->bmiColors[k].rgbGreen = k;
			(*lpbi)->bmiColors[k].rgbBlue = k;
		}
	}
	(*lpbi)->bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
	(*lpbi)->bmiHeader.biWidth = nCols;
	(*lpbi)->bmiHeader.biHeight = nRows;
	(*lpbi)->bmiHeader.biPlanes = 1;
	(*lpbi)->bmiHeader.biBitCount = 8 * nColor;
	(*lpbi)->bmiHeader.biCompression = BI_RGB;
	(*lpbi)->bmiHeader.biSizeImage = nCols * nRows * 8 * nColor;
	(*lpbi)->bmiHeader.biXPelsPerMeter = 0;
	(*lpbi)->bmiHeader.biYPelsPerMeter = 0;
	(*lpbi)->bmiHeader.biClrUsed = 0;
	(*lpbi)->bmiHeader.biClrImportant = 0;
	return TRUE;
}

// CMFCImageMapView

IMPLEMENT_DYNCREATE(CMFCImageMapView, CScrollView)

BEGIN_MESSAGE_MAP(CMFCImageMapView, CScrollView)
	// 标准打印命令
	ON_COMMAND(ID_FILE_PRINT, &CScrollView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CScrollView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CMFCImageMapView::OnFilePrintPreview)
	ON_WM_CONTEXTMENU()
	ON_WM_RBUTTONUP()
	ON_UPDATE_COMMAND_UI(ID_INDICATOR_COORD, &CMFCImageMapView::OnUpdateIndicatorCoord)
END_MESSAGE_MAP()

// CMFCImageMapView 构造/析构

CMFCImageMapView::CMFCImageMapView() noexcept
{
	// TODO: 在此处添加构造代码
	m_lpBitmap = nullptr;// 初始化图像查看
}

CMFCImageMapView::~CMFCImageMapView()
{
	if (m_lpBitmap) {
		delete m_lpBitmap;
		m_lpBitmap = nullptr;
	}
}

BOOL CMFCImageMapView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改
	//  CREATESTRUCT cs 来修改窗口类或样式

	return CScrollView::PreCreateWindow(cs);
}

// CMFCImageMapView 绘图

void CMFCImageMapView::OnDraw(CDC* pDC)
{
	CMFCImageMapDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: 在此处为本机数据添加绘制代码
	int nCols = pDoc->GetImgCols();
	int nRows = pDoc->GetImgRows();
	int nBytes = pDoc->GetImgPixelBytes();
	const BYTE* pData = pDoc->GetImgData();
	StretchDIBits(pDC->m_hDC,
		0, 0, nCols, nRows,
		0, 0, nCols, nRows,
		pData, m_lpBitmap, DIB_RGB_COLORS, SRCCOPY);
}

void CMFCImageMapView::OnInitialUpdate()
{
	CScrollView::OnInitialUpdate();
	CMFCImageMapDoc* pDoc = GetDocument();
	if (!pDoc) return;
	int nCols = pDoc->GetImgCols();
	int nRows = pDoc->GetImgRows();
	int nBytes = pDoc->GetImgPixelBytes();

	CSize sizeTotal;
	// TODO: 计算此视图的合计大小
	sizeTotal.cx = nCols;
	sizeTotal.cy = nRows;
	SetScrollSizes(MM_TEXT, sizeTotal);
	CreateLPBitmapInfo(&m_lpBitmap, nCols, nRows, nBytes);
	
}


// CMFCImageMapView 打印


void CMFCImageMapView::OnFilePrintPreview()
{
#ifndef SHARED_HANDLERS
	AFXPrintPreview(this);
#endif
}

BOOL CMFCImageMapView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 默认准备
	return DoPreparePrinting(pInfo);
}

void CMFCImageMapView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加额外的打印前进行的初始化过程
}

void CMFCImageMapView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加打印后进行的清理过程
}

void CMFCImageMapView::OnRButtonUp(UINT /* nFlags */, CPoint point)
{
	ClientToScreen(&point);
	OnContextMenu(this, point);
}

void CMFCImageMapView::OnContextMenu(CWnd* /* pWnd */, CPoint point)
{
#ifndef SHARED_HANDLERS
	theApp.GetContextMenuManager()->ShowPopupMenu(IDR_POPUP_EDIT, point.x, point.y, this, TRUE);
#endif
}


// CMFCImageMapView 诊断

#ifdef _DEBUG
void CMFCImageMapView::AssertValid() const
{
	CScrollView::AssertValid();
}

void CMFCImageMapView::Dump(CDumpContext& dc) const
{
	CScrollView::Dump(dc);
}

CMFCImageMapDoc* CMFCImageMapView::GetDocument() const // 非调试版本是内联的
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMFCImageMapDoc)));
	return (CMFCImageMapDoc*)m_pDocument;
}
#endif //_DEBUG


// CMFCImageMapView 消息处理程序


void CMFCImageMapView::OnUpdateIndicatorCoord(CCmdUI* pCmdUI)
{
	// TODO: 在此添加命令更新用户界面处理程序代码
	CPoint point;
	GetCursorPos(&point);
	ScreenToClient(&point);

	//CClientDC dc(this);
	//OnPrepareDC(&dc);
	//dc.DPtoLP(&point);
	point += GetScrollPosition();

	double geo_x = point.x, geo_y = point.y;
	CMFCImageMapDoc* pDoc = GetDocument();
	if (pDoc) pDoc->ImageCoord2World(point.x, point.y, &geo_x, &geo_y);
	
	CString strPoint;
	strPoint.Format(L"该点坐标(X: %.2lf , Y: %.2lf )", geo_x, geo_y);
	pCmdUI->SetText(strPoint);
}

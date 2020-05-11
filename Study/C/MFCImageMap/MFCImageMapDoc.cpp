
// MFCImageMapDoc.cpp: CMFCImageMapDoc 类的实现
//

#include "pch.h"
#include "framework.h"
// SHARED_HANDLERS 可以在实现预览、缩略图和搜索筛选器句柄的
// ATL 项目中进行定义，并允许与该项目共享文档代码。
#ifndef SHARED_HANDLERS
#include "MFCImageMap.h"
#endif

#include "MFCImageMapDoc.h"

#include <propkey.h>
#include <atlconv.h>

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

// CMFCImageMapDoc

IMPLEMENT_DYNCREATE(CMFCImageMapDoc, CDocument)

BEGIN_MESSAGE_MAP(CMFCImageMapDoc, CDocument)
END_MESSAGE_MAP()


// CMFCImageMapDoc 构造/析构

CMFCImageMapDoc::CMFCImageMapDoc() noexcept
{
	// TODO: 在此添加一次性构造代码
	// 这里是按要求添加的构造函数
	m_pData = nullptr;
	m_nCols = m_nRows = m_nBytes = 0;
}

BOOL CMFCImageMapDoc::ReadImage(const char* lpszPathName)
{
	// 使用自带的函数打开图像
	if (!m_ImgObject.Open(lpszPathName)) return FALSE;
	m_nCols = m_ImgObject.GetCols();
	m_nRows = m_ImgObject.GetRows();
	m_nBytes = m_ImgObject.GetBands();
	// 虽然不知道为什么，但是显示图像时列数要为4的倍数
	// 针对windows系统...这会给移植造成困难
	if (m_nCols % 4 != 0) {
		m_nCols = ((int)(m_nCols / 4) + 1) * 4;// 取整数部分，会损失数据吧
	}

	if (m_pData) delete[] m_pData;
	m_pData = new BYTE[m_nBytes * m_nCols * m_nRows];
	memset(m_pData, 0, m_nBytes * m_nCols * m_nRows * sizeof(BYTE));
	// 读取图像数据
	m_ImgObject.Read(m_pData, m_nBytes, 0, 0, m_nRows, m_nCols);
	if (m_nBytes==3) {
		// 是把彩色图像的像素的R和B字节交换，为什么要这样做呢...
		BYTE* p = m_pData;
		for (int i = 0; i < m_nRows * m_nCols; i++) {
			BYTE t = *(p + 2);
			*(p + 2) = *p;
			*p = t;
			p += 3;
		}
	}
	return TRUE;
}

CMFCImageMapDoc::~CMFCImageMapDoc()
{
	// 这里是析构函数代码
	if (m_pData) {
		delete[] m_pData;
		m_pData = nullptr;
	}
	m_ImgObject.Close();
}

BOOL CMFCImageMapDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: 在此添加重新初始化代码
	// (SDI 文档将重用该文档)

	return TRUE;
}




// CMFCImageMapDoc 序列化

void CMFCImageMapDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO:  在此添加存储代码
	}
	else
	{
		// TODO:  在此添加加载代码
	}
}

#ifdef SHARED_HANDLERS

// 缩略图的支持
void CMFCImageMapDoc::OnDrawThumbnail(CDC& dc, LPRECT lprcBounds)
{
	// 修改此代码以绘制文档数据
	dc.FillSolidRect(lprcBounds, RGB(255, 255, 255));

	CString strText = _T("TODO: implement thumbnail drawing here");
	LOGFONT lf;

	CFont* pDefaultGUIFont = CFont::FromHandle((HFONT) GetStockObject(DEFAULT_GUI_FONT));
	pDefaultGUIFont->GetLogFont(&lf);
	lf.lfHeight = 36;

	CFont fontDraw;
	fontDraw.CreateFontIndirect(&lf);

	CFont* pOldFont = dc.SelectObject(&fontDraw);
	dc.DrawText(strText, lprcBounds, DT_CENTER | DT_WORDBREAK);
	dc.SelectObject(pOldFont);
}

// 搜索处理程序的支持
void CMFCImageMapDoc::InitializeSearchContent()
{
	CString strSearchContent;
	// 从文档数据设置搜索内容。
	// 内容部分应由“;”分隔

	// 例如:     strSearchContent = _T("point;rectangle;circle;ole object;")；
	SetSearchContent(strSearchContent);
}

void CMFCImageMapDoc::SetSearchContent(const CString& value)
{
	if (value.IsEmpty())
	{
		RemoveChunk(PKEY_Search_Contents.fmtid, PKEY_Search_Contents.pid);
	}
	else
	{
		CMFCFilterChunkValueImpl *pChunk = nullptr;
		ATLTRY(pChunk = new CMFCFilterChunkValueImpl);
		if (pChunk != nullptr)
		{
			pChunk->SetTextValue(PKEY_Search_Contents, value, CHUNK_TEXT);
			SetChunkValue(pChunk);
		}
	}
}

#endif // SHARED_HANDLERS

// CMFCImageMapDoc 诊断

#ifdef _DEBUG
void CMFCImageMapDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CMFCImageMapDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG


// CMFCImageMapDoc 命令


BOOL CMFCImageMapDoc::OnOpenDocument(LPCTSTR lpszPathName)
{
	if (!CDocument::OnOpenDocument(lpszPathName))
		return FALSE;

	// TODO:  在此添加您专用的创建代码
	USES_CONVERSION;
	if (!ReadImage(W2A(lpszPathName))) return FALSE;
	return TRUE;
}

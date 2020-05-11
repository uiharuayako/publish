
// MFCImageMapDoc.h: CMFCImageMapDoc 类的接口
//


#pragma once
#include "IdpmImage.h"

class CMFCImageMapDoc : public CDocument
{
protected: // 仅从序列化创建
	CMFCImageMapDoc() noexcept;
	DECLARE_DYNCREATE(CMFCImageMapDoc)
	//按要求添加的，与图片相关的代码
	CIdpmImage m_ImgObject;
	BYTE* m_pData;
	int m_nCols, m_nRows, m_nBytes;
	BOOL ReadImage(const char* lpszPathName);// 读取图片文件的函数
// 特性
public:

// 操作
public:
	UINT GetImgCols() { return m_nCols; }// 获取列数
	UINT GetImgRows() { return m_nRows; }// 获取行数
	UINT GetImgPixelBytes() { return m_nBytes; }// 获取像素总数?
	const BYTE* GetImgData() { return m_pData; }// 这里加个const，是为了防止在函数体内指针被改变
	void ImageCoord2World(double x, double y, double* gx, double* gy) {
		m_ImgObject.ImageToGeoProj(x, y, gx, gy);
	}
// 重写
public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
#ifdef SHARED_HANDLERS
	virtual void InitializeSearchContent();
	virtual void OnDrawThumbnail(CDC& dc, LPRECT lprcBounds);
#endif // SHARED_HANDLERS

// 实现
public:
	virtual ~CMFCImageMapDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 生成的消息映射函数
protected:
	DECLARE_MESSAGE_MAP()

#ifdef SHARED_HANDLERS
	// 用于为搜索处理程序设置搜索内容的 Helper 函数
	void SetSearchContent(const CString& value);
#endif // SHARED_HANDLERS
public:
	virtual BOOL OnOpenDocument(LPCTSTR lpszPathName);
};

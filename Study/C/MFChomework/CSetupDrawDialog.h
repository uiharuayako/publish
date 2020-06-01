#pragma once


// CSetupDrawDialog 对话框

class CSetupDrawDialog : public CDialogEx
{
	DECLARE_DYNAMIC(CSetupDrawDialog)

public:
	CSetupDrawDialog(CWnd* pParent = nullptr);   // 标准构造函数
	virtual ~CSetupDrawDialog();

// 对话框数据
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_SETUP_DRAW_DIALOG };
#endif

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	COLORREF m_SelectedColor;
	CMFCColorButton m_ColorPicker;
//	CComboBox m_nLineIndex;
//	CString m_nLineIndex;
	int m_nLineIndex;
	UINT m_nLineWidth;
	virtual BOOL OnInitDialog();
	afx_msg void OnClickedPenColorButton();
	afx_msg void OnBnClickedOk();
};

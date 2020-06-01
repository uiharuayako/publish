// CSetupDrawDialog.cpp: 实现文件
//

#include "pch.h"
#include "MFCImageMap.h"
#include "CSetupDrawDialog.h"
#include "afxdialogex.h"


// CSetupDrawDialog 对话框

IMPLEMENT_DYNAMIC(CSetupDrawDialog, CDialogEx)

CSetupDrawDialog::CSetupDrawDialog(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_SETUP_DRAW_DIALOG, pParent)
	, m_nLineIndex(0)
	, m_nLineWidth(1)
{
	m_SelectedColor = RGB(0, 0, 0);
}

CSetupDrawDialog::~CSetupDrawDialog()
{
}

void CSetupDrawDialog::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_PEN_COLOR_BUTTON, m_ColorPicker);
	//  DDX_Control(pDX, IDC_VECTOR_LINE_TYPE, m_nLineIndex);
	//  DDX_CBString(pDX, IDC_VECTOR_LINE_TYPE, m_nLineIndex);
	DDX_CBIndex(pDX, IDC_VECTOR_LINE_TYPE, m_nLineIndex);
	DDX_Text(pDX, IDC_VECTOR_LINE_WIDTH, m_nLineWidth);
	DDV_MinMaxUInt(pDX, m_nLineWidth, 1, 10);
}


BEGIN_MESSAGE_MAP(CSetupDrawDialog, CDialogEx)
	ON_BN_CLICKED(IDC_PEN_COLOR_BUTTON, &CSetupDrawDialog::OnClickedPenColorButton)
	ON_BN_CLICKED(IDOK, &CSetupDrawDialog::OnBnClickedOk)
END_MESSAGE_MAP()


// CSetupDrawDialog 消息处理程序


BOOL CSetupDrawDialog::OnInitDialog()
{
	CDialogEx::OnInitDialog();
	// TODO:  在此添加额外的初始化

	m_ColorPicker.EnableAutomaticButton(_T("默认值"), RGB(0, 0, 0));
	m_ColorPicker.EnableOtherButton(_T("其他颜色"));
	m_ColorPicker.SetColumnsNumber(10);
	m_ColorPicker.SetColor(m_SelectedColor);

	CComboBox* pLineTypeBox = (CComboBox*)GetDlgItem(IDC_VECTOR_LINE_TYPE);
	pLineTypeBox->AddString(_T("实线"));
	pLineTypeBox->AddString(_T("虚线"));
	pLineTypeBox->AddString(_T("点线"));
	pLineTypeBox->AddString(_T("点和虚线交替"));
	pLineTypeBox->AddString(_T("双点线"));

	UpdateData(FALSE);

	return TRUE;  // return TRUE unless you set the focus to a control
				  // 异常: OCX 属性页应返回 FALSE
}


void CSetupDrawDialog::OnClickedPenColorButton()
{
	// TODO: 在此添加控件通知处理程序代码
	m_SelectedColor = m_ColorPicker.GetColor();
	if (m_SelectedColor == -1) {
		m_SelectedColor = m_ColorPicker.GetAutomaticColor();
	}
}


void CSetupDrawDialog::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE);
	CDialogEx::OnOK();
}

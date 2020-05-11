
/*++

Copyright (c) 2014  Wuhan University.

Module Name:

    IdpmImage.

Abstract:

    This module contains the C++ class for reading/writing raster files.

Author:

    Tao PengJie    16-Feb-2014
	pjtao@whu.edu.cn


Revision History:


--*/

#ifndef IDPMIMAGE_H_PJTAO_2014_02_16_21_24_13719
#define IDPMIMAGE_H_PJTAO_2014_02_16_21_24_13719

#ifndef IDPMIMAGE_LIB
#define IDPMIMAGE_LIB  __declspec(dllimport)
	#ifdef _DEBUG_IDPMIMAGE
	#pragma comment(lib,"IdpmImageD.lib") 
	#pragma message("Automatically linking with IdpmImageD.lib") 
	#else
	#pragma comment(lib,"IdpmImage.lib") 
	#pragma message("Automatically linking with IdpmImage.lib") 
	#endif
#endif

//! ������Ϣ�Ļص���������
//  dfCompletedΪ����ֵ��ȡֵ0.0-1.0��0.0��ʾ��ʼ��1.0��ʾ����
//  bCancelΪȡ���źţ�����ΪTRUEʱ�����Զ�ȡ������
//  lpstrMsgΪ��Ϣ�ַ���ָ��
typedef void (/*__stdcall */*ProgUpdateFunc)(double dfCompleted, BOOL& bCancel);
typedef void (/*__stdcall */*ProgMsgFunc)(const char* lpstrMsg);
typedef ProgUpdateFunc	LPPROGUPDATEFUNC;
typedef ProgMsgFunc		LPPROGMSGFUNC;

#ifndef _PROJECTION_TYPE
#define _PROJECTION_TYPE
//! ����ͶӰ���Ͷ���
enum PROJ_TYPE
{
	PRJ_UNKNOWN = 0,
	PRJ_GEOGRAPHIC = 1, // ����γ��
	PRJ_GEOCENTRIC = 2, // ��������ϵ
	PRJ_MERCATOR = 3,   // ī����
	PRJ_TRANSVERSE_MERCATOR = 4, // ����ī����
	PRJ_GAUSS_KRUGER = 5, // ��˹������
	PRJ_UTM = 6, // UTM
	PRJ_LAMBERT_CONFORMAL_CONIC = 7, // ������
	PRJ_STEREOGRAPHIC = 8,
	PRJ_ORTHOGRAPHIC = 9,
};
#endif

#ifndef _PROJECTION_PARAMETERS
#define _PROJECTION_PARAMETERS
//!	ͶӰ��Ϣ�ṹ�嶨��
typedef struct tagProParameters
{
	char	strSphereName[128]; // ��������
	char	strDatumName[128];	// ��������ϵ��׼����
	double	a; // ���򳤰���
	double	b; // ����̰���
	int		nProjType; // ͶӰ����
	int		nVerticalDatum; // �̻߳�׼
	double	lfCentralLongtitude; // ���뾭��
	double	lfOriginalLatitude;  // ��ʼγ��
	double	lfFalseEasting;  // ��ƫ��
	double	lfFalseNorthing; // ��ƫ��
	double	lf7Par[7]; // �߲���
	char	cReversed[256]; // ������
}ProParameters;
#endif

//!	դ�������ļ���д�ӿڡ�����������Ҫ�ص㣺
//  (1) �����դ�����ݵ���ʼ��Ϊդ���ļ������½����ص����ģ����õ�����Ϊ(0, 0)��
//  (2) �ɶ�ȡenum RASTERDATATYPE���������͵�դ�����ݣ�֧��Ӱ�������ļ���DEM�����ļ��Ķ�д��
//  (3) ���ڵ�����Ϣ���ݣ��ɶ�/д������Ϣ������ϵ������Ϣ��
//  (4) ֧�ֵ�դ�����ݸ�ʽ�����/д����������
//      a. TIFF/BigTIFF/GeoTIFF(*.tif;*.tiff)��ʽ����/д��
//      b. BMP��ʽ(*.bmp)����/д��
//      c. JPEG��ʽ(*.jpeg;*.jpg;*.jpe)����/д��
//      d. JPEG2000��ʽ(*.jp2;*.j2k;*.jpt;*.jpc;*.j2c)������
//      e. BIL��ʽ(*.bil;*.bip;*.bsq)������
//      f. ERDAS IMAGINE(*.img)��ʽ����/д��
//      g. PNG(*.png)��ʽ������
//      h. NITF(*.ntf)��ʽ������
//      i. ADS��ʽ(*.ads, Level 0 & Level 1)������
//      j. Digital Global��˾WorldView�߼���Ӱ���TIL��ʽ(*.til)������
//      k. RAW��ʽ(*.raw)����/д������ȡ��ͷ��Ϣ�ļ�����ΪVirtuoZo��SPT�ļ���XML�ļ���
//      l. ���չ�˾����Ӱ���ʽ(*.vz;*.orl;*.orr;*.lei;*.rei)����/д��
//      m. ECW��ʽ(*.ecw)����������ҪECW JPEG 2000 SDK����
//      n. Portable Pixmap(*.pgm;*.ppm;*.pfm)�������������ڶ����Ƹ�ʽ��
//      o. ���չ�˾DEM��ʽ(*.dem;*.bem)�͹���DEM��ʽ(*.dem)������
//      p. SRTM HGT��ʽDEM����(*.hgt)������
//      q. ArcInfo ASCII grid����DEM����(*.grd)������
//
class IDPMIMAGE_LIB CIdpmImage
{
public:
	CIdpmImage();
	virtual ~CIdpmImage();

public:
	//! դ���������Ͷ���
	enum RASTERDATATYPE
	{
		RDT_NONE	= 0,
		RDT_BYTE	= 1,
		RDT_UINT16	= 2,
		RDT_INT16	= 3,
		RDT_UINT32	= 4,
		RDT_INT32	= 5,
		RDT_FLOAT32	= 6,
		RDT_FLOAT64 = 7,
	};

	//! �ļ�����ģʽ����
	enum OPENFLAGS
	{ 
		modeRead	= 0x0000,
		modeCreate	= 0x1000,
	};

	//! ԭʼӰ���ļ���ʼ�㶨��
	enum IMAGEORIENTATION
	{ 
		ORI_TOPLEFT	= 1,
		ORI_TOPRIGHT= 2,
		ORI_BOTLEFT = 3,
		ORI_BOTRIGHT= 4,
	};

	//! ����16λӰ����8λӰ����ұ�������
	enum LOOKUPTABLE
	{
		LUT_NONE	= 0,
		LUT_ALV		= 1, // �Զ�ɫ��
		LUT_SQRT	= 2, // ƽ����
		LUT_LINEAR	= 3, // ���Ա任
	};

	//! ����ѹ�����Ͷ���
	enum COMPRESSIONTECHNIQUE
	{
		CT_NONE		= 0,
		CT_LZW		= 1,
		CT_JPEG		= 2,
		CT_ZIP		= 3,
		CT_LOG		= 4,
		CT_OTHERS	= 5,
	};

	//! �Զ���������Ͷ���
	enum CUSTOMOPERATION
	{
		COP_NONE	= 0,
		COP_WLSTRANS= 1,
		COP_SETAUX  = 2,
		COP_ACTIVWLS= 3,
		COP_WLSSTATE= 4,
		COP_SETGRAYBANDS= 5,
		
		COP_SETPAGENUM=10,
		COP_GETPAGENUM=11,
		COP_ACTIVEPAGE=12,
		COP_PAGESREADY=13,
	};

public:
	/*	��դ�������ļ�
	 *
	 *	@param[I]	lpstrPathName	�ļ�ȫ·��
	 *	@param[I]	flag			�ļ�����ģʽ����enum OPENFLAGS
	 *	@param[I]	nMaxBufSize		��ʹ���ڴ������
	 *	@param[I]	pImgInstance	�ⲿʵ��������
	 *	@param[I]	papszOptions	���Ӳ����ַ���
	 *				LOOKUPTABLE=[NONE/AUTOLEVEL/SQRT/LINEARTRANS]: 16λת8λ���ұ����ɵķ���
	 *				READPALETTECOLOR=[YES/NO]: ��ȡ��ɫ��Ӱ�����ݣ�Ĭ��ΪNO
	 *				AUXDATADIR="": �������ݺͽ�����Ӱ������Ŀ¼
	 *				READWALLIS=[YES/NO]: ��ȡWallis�任���Ӱ�����ݣ�Ĭ��ΪNO
	 *				SOFTWARE="": TIFF�ļ���TIFFTAG_SOFTWARE��ǩֵ
	 *				COPYRIGHT"": TIFF�ļ���TIFFTAG_COPYRIGHT��ǩֵ
	 *				DOCUMENTNAME"": TIFF�ļ���TIFFTAG_DOCUMENTNAME��ǩֵ
	 *				IMAGEDESCRIPTION"": TIFF�ļ���TIFFTAG_IMAGEDESCRIPTION��ǩֵ
	 *				DATETIME"": TIFF�ļ���TIFFTAG_DATETIME��ǩֵ
	 *				ARTIST"": TIFF�ļ���TIFFTAG_ARTIST��ǩֵ
	 *				HOSTCOMPUTER"": TIFF�ļ���TIFFTAG_HOSTCOMPUTER��ǩֵ
	 */
	virtual BOOL Open(const char* lpstrPathName, int flag = modeRead,
		int nMaxBufSize = -1, CIdpmImage* pImgInstance = NULL,
		char** papszOptions = NULL);

	//! �ر�դ�������ļ�
	virtual void Close();

	/*	�������ȡ����
	 *
	 *	@param[I/O]	pBuffer			���ڽ��ն�ȡ��դ�����ݵ��ڴ�飬���ⲿ����
	 *	@param[I]	nBands			��ȡդ�����ݵĲ�������һ�����դ�������ļ���ʵ�ʲ�������
	 *								��դ������ʵ��Ϊ�ನ�Σ���ֵ����Ϊ1ʱ��ǿ�ƶ�ȡΪ�Ҷ�����
	 *	@param[I]	sRow			��ȡ�������ʼ��
	 *	@param[I]	sCol			��ȡ�������ʼ��
	 *	@param[I]	nRows			��ȡ���������
	 *	@param[I]	nCols			��ȡ���������
	 *	@param[I]	eType			��ȡ����������
	 *				����ֵ������ʵ��դ����������ʱ���Զ�����ǿ��ת�����������ºϷ������
	 *				(1) ʵ��դ����������ΪRDT_UINT16����16λӰ�����ݣ�eType=RDT_BYTEʱ���Զ���16λת��Ϊ8λ��
	 *				(2) ʵ��դ����������>=RDT_INT16�����߳����ݣ�eType=RDT_BYTEʱ���Զ�ת��Ϊ�������ݣ�
	 *				(3) ��eType>=ʵ��դ����������ʱ��ǿ��ת��
	 *	@param[I]	dfZoomRate		Ӱ�����ű��ʣ�Ŀǰ��֧����С����dfZoomRate<=1.0��
	 */
	virtual BOOL Read(void* pBuffer, int nBands, int sRow, int sCol,
		int nRows, int nCols, RASTERDATATYPE eType = RDT_BYTE,
		double dfZoomRate = 1.0);

	//! ����д������
	virtual BOOL Write(void* pBuffer, int rowIdx);

	/*	����д������
	 *
	 *	@param[I]	pBuffer			��д���һ�����ݵ��ڴ棬���������ң���������˳��洢
	 *	@param[I]	nTileX, nTileY	���ݿ�Ŀ����꣬���������ʼ����Ӱ��洢������أ���enum IMAGEORIENTATION
	 */
	virtual BOOL Write(void* pBuffer, int nTileX, int nTileY);

	/*	����������Ӱ��
	 *
	 *	@param[I]	nZoomRatio		����ϵ����һ��ȡֵΪ2��3
	 *	@param[I]	bForceToByte	ǿ��ָ��������դ����������ΪBYTE
	 *	@param[I]	papszOptions	���Ӳ����ַ���
	 *              CompTechique=[NONE/LZW/JPEG/ZIP/LOG]: ������դ�����ݵ�ѹ������
	 *				CompQuality		ѹ��������Ĭ��Ϊ75
	 *              OVERWRITE=[YES/NO]: ���������դ�������ļ����ڣ��Ƿ񸲸ǡ�Ĭ��ΪNO
	 *
	 *   �����Ľ�����Ӱ��Ϊ[ԭӰ����+".ovr"]����ԭӰ��Ϊimage.tif���������Ӱ��Ϊimage.tif.ovr
	 */
	virtual BOOL BuildPyramids(int nZoomRatio = 2, BOOL bForceToByte = FALSE,
		char** papszOptions = NULL);

	/*	�Զ������
	 *
	 *	@param[I]	wParam, lParam	����
	 *              ��wParam=COP_SETGRAYBANDS�����öನ��Ӱ��ת�Ҷ�Ӱ�����Ч���Ρ�
	 *                              ��ʱlParam��ÿһ����λ��ʾ��Ӧ�����Ƿ���Ч����Ч��Ϊ1
	 *                              ��Ӱ��1��2��4������Ч��lParam�����Ʊ�ʾΪ0000 0000 0000 1011����ȡֵΪ11
	 *	@param[I]	papszOptions	���Ӳ����ַ���
	 *				WallisParas		Wallis�˲���������ʽΪ"mean,sigma,c,b,gridWndSize,filterWndSize"
	 */
	virtual int  CustomOperation(int wParam, int lParam, char** papszOptions = NULL);

public:
	//! ��ȡӰ���ļ�·��
	const char*  GetPathName() const;
	//! ��ȡӰ�������
	int	         GetRows() const;
	//! ��ȡӰ�������
	int	         GetCols() const;
	//! ��ȡӰ��Ĳ�����
	int	         GetBands() const;
	//! ��ȡӰ�����������
	int          GetDataType() const;
	//! ��ȡӰ��ķֿ���Ϣ
	void         GetTiledInfo(BOOL* pbTiled, int* pnTileCols = NULL, int* pnTileRows = NULL,
		int* pnTileSumX = NULL, int* pnTileSumY = NULL) const;
	//! ��ȡӰ��洢����
	int	         GetDataOrientation() const;
	//! ��ȡ����ѹ������������
	virtual WORD GetCompression(WORD* pnCompQuality = NULL);

	static int   GetDateTypeBytes(int nType);

	//! ����դ��Ӱ���ļ����������
	void         SetRows(int nRows);
	void         SetCols(int nCols);
	void         SetBands(int nBands);

	virtual BOOL SetDataType(RASTERDATATYPE nType = RDT_BYTE);
	virtual BOOL SetTiledInfo(BOOL bTiled = TRUE, int nTileCols = 256, int nTileRows = 256);

	//! ��������ѹ������������
	virtual BOOL SetCompression(WORD nCompTechnique, WORD nCompQuality = 75);
	
	//! ���ý���������ص�����
	virtual void SetProgressFunc(LPPROGUPDATEFUNC lpProgUpdateFunc = NULL,
		LPPROGMSGFUNC lpProgMsgFunc = NULL
		);

	//! ����16λӰ��ת��Ϊ8λ�Ĳ��ұ�
	virtual BOOL CreateLookUpTable(int nLutType = LUT_ALV, LPARAM lParam = 8);
	//! ����16λӰ��ת��Ϊ8λ�Ĳ��ұ�
	virtual BOOL SetLookUpTable(const BYTE* pLut, int bandIdx);
	//! ��ȡ16λӰ��ת��Ϊ8λ�Ĳ��ұ�
	virtual const BYTE* GetLookUpTable(int bandIdx);

	//! ���õ�����Ϣ�任����
	virtual BOOL SetGeoTransform(const double* pGeoMatrix6, ProParameters* proParameters = NULL);
	//! ��ȡ������Ϣ�任����
	virtual double*	GetGeoTransform(ProParameters* proParameters = NULL) const;
	//! Ӱ������任����������
	void	ImageToGeoProj(double ix, double iy, double* gx, double* gy) const;
	//! ��������任��Ӱ������
	void	GeoProjToImage(double gx, double gy, double* ix, double* iy) const;

	//! ��ȡ������Ϣ
	const char*	 GetErrorMsg();

	//! ��ȡ������Ӱ�����ż����ͱ�����Ϣ����һ��Ϊԭʼ1:1Ӱ���
	const WORD*	 GetPyramidLevels(int& nLevels);

protected:
	//! ����Ӱ������
	virtual void UpdateImageProp();

	//! �ڲ���ȡӰ��ӿ�
	virtual BOOL IRead(void* pBuffer, int rowIdx, BOOL* bBIPFormat);
	virtual BOOL IRead(void* pBuffer, int nTileX, int nTileY, BOOL* bBIPFormat);
	virtual BOOL IWrite(void* pBuffer, int rowIdx);
	virtual BOOL IWrite(void* pBuffer, int nTileX, int nTileY);

private:
	//! ������ʱ�ڴ�
	void	AllocateTmpCache();

protected:
	//! Ӱ���С�Ͳ�����
	int		m_nRows, m_nCols, m_nBands;
	//! դ�����ݵ��������ͣ�ȡֵΪRASTERDATATYPEö��ֵ
	int		m_nDataType;
	//! �Ƿ񰴷ֿ鷽ʽ�洢���ļ�
	BOOL	m_bTiled;
	//! դ��Ӱ��ķֿ��С
	int		m_nTileCols, m_nTileRows;
	//! �ļ�ȫ·��
	char	m_strPathName[512];

	//! ԭʼӰ���ļ�����ʼ�㣬��ֵ����ʾԭʼӰ�����ʼ��
	//! ���ඨ�������������ʼ��Ϊ���½����ص����ģ������±���ȡֵ�޹�
	int		m_nOrientation;

	//! �ļ��򿪷�ʽ����ȡ/д�룩��ȡֵΪOPENFLAGSö��ֵ
	int		m_nOpenMode;
	//! �ⲿָ���Ľӿ�ʵ��
	CIdpmImage*	m_pInstance;

	//! ������Ϣ�任����
	double*	m_pGeoMatrix6;

	//! 16λת8λ��ɫ��
	BYTE*	m_pLookupTable;

	//! ����ʹ�����Ϣ
	char	m_strErrorMsg[512];

	//! ����ѹ�����ͺ�ѹ������
	WORD	m_nCompTechnique, m_nCompQuality;

	//! ������Ӱ�����ű����б�
	WORD*	m_pPyramidLevel;
	int		m_nPyramidLevel;

	LPPROGUPDATEFUNC m_lpProgUpdateFunc;
	LPPROGMSGFUNC	 m_lpProgMsgFunc;
	
	//! ���Ӳ����͸�������
	void*	m_pAuxCache;

private:
	//! �ڲ��ӿ�ʵ��
	CIdpmImage*	m_pInstance0;

	//!	��ʱ�����ڴ�
	BYTE*	m_pTmpCache;
	int		m_nTmpCacheSize;
};


//! ���ò���������ƺ�ֵ
char** CFISetNameValue(
	char **papszStringList,
	const char *pszName,
	const char *pszValue
	);

//! �ͷ��ַ����б�
void CFIStringDestroy(
	char **papszStringList
	);


#endif
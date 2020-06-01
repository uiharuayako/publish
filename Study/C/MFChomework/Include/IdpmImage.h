
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

//! 进度消息的回调函数定义
//  dfCompleted为进度值，取值0.0-1.0，0.0表示开始，1.0表示结束
//  bCancel为取消信号，设置为TRUE时程序自动取消处理
//  lpstrMsg为消息字符串指针
typedef void (/*__stdcall */*ProgUpdateFunc)(double dfCompleted, BOOL& bCancel);
typedef void (/*__stdcall */*ProgMsgFunc)(const char* lpstrMsg);
typedef ProgUpdateFunc	LPPROGUPDATEFUNC;
typedef ProgMsgFunc		LPPROGMSGFUNC;

#ifndef _PROJECTION_TYPE
#define _PROJECTION_TYPE
//! 坐标投影类型定义
enum PROJ_TYPE
{
	PRJ_UNKNOWN = 0,
	PRJ_GEOGRAPHIC = 1, // 地理经纬度
	PRJ_GEOCENTRIC = 2, // 地心坐标系
	PRJ_MERCATOR = 3,   // 墨卡托
	PRJ_TRANSVERSE_MERCATOR = 4, // 横轴墨卡托
	PRJ_GAUSS_KRUGER = 5, // 高斯克吕格
	PRJ_UTM = 6, // UTM
	PRJ_LAMBERT_CONFORMAL_CONIC = 7, // 兰伯特
	PRJ_STEREOGRAPHIC = 8,
	PRJ_ORTHOGRAPHIC = 9,
};
#endif

#ifndef _PROJECTION_PARAMETERS
#define _PROJECTION_PARAMETERS
//!	投影信息结构体定义
typedef struct tagProParameters
{
	char	strSphereName[128]; // 椭球名称
	char	strDatumName[128];	// 地理坐标系基准名称
	double	a; // 椭球长半轴
	double	b; // 椭球短半轴
	int		nProjType; // 投影类型
	int		nVerticalDatum; // 高程基准
	double	lfCentralLongtitude; // 中央经线
	double	lfOriginalLatitude;  // 起始纬度
	double	lfFalseEasting;  // 东偏移
	double	lfFalseNorthing; // 北偏移
	double	lf7Par[7]; // 七参数
	char	cReversed[256]; // 保留字
}ProParameters;
#endif

//!	栅格数据文件读写接口。具有以下重要特点：
//  (1) 定义的栅格数据的起始点为栅格文件的左下角像素的中心，即该点坐标为(0, 0)；
//  (2) 可读取enum RASTERDATATYPE所定义类型的栅格数据，支持影像数据文件和DEM数据文件的读写；
//  (3) 对于地理信息数据，可读/写地理信息和坐标系参数信息；
//  (4) 支持的栅格数据格式及其读/写操作包括：
//      a. TIFF/BigTIFF/GeoTIFF(*.tif;*.tiff)格式，读/写；
//      b. BMP格式(*.bmp)，读/写；
//      c. JPEG格式(*.jpeg;*.jpg;*.jpe)，读/写；
//      d. JPEG2000格式(*.jp2;*.j2k;*.jpt;*.jpc;*.j2c)，读；
//      e. BIL格式(*.bil;*.bip;*.bsq)，读；
//      f. ERDAS IMAGINE(*.img)格式，读/写；
//      g. PNG(*.png)格式，读；
//      h. NITF(*.ntf)格式，读；
//      i. ADS格式(*.ads, Level 0 & Level 1)，读；
//      j. Digital Global公司WorldView逻辑景影像的TIL格式(*.til)，读；
//      k. RAW格式(*.raw)，读/写；（读取需头信息文件，可为VirtuoZo的SPT文件或XML文件）
//      l. 适普公司各种影像格式(*.vz;*.orl;*.orr;*.lei;*.rei)，读/写；
//      m. ECW格式(*.ecw)，读；（需要ECW JPEG 2000 SDK）；
//      n. Portable Pixmap(*.pgm;*.ppm;*.pfm)，读；（仅限于二进制格式）
//      o. 适普公司DEM格式(*.dem;*.bem)和国标DEM格式(*.dem)，读；
//      p. SRTM HGT格式DEM数据(*.hgt)，读；
//      q. ArcInfo ASCII grid个数DEM数据(*.grd)，读；
//
class IDPMIMAGE_LIB CIdpmImage
{
public:
	CIdpmImage();
	virtual ~CIdpmImage();

public:
	//! 栅格数据类型定义
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

	//! 文件访问模式定义
	enum OPENFLAGS
	{ 
		modeRead	= 0x0000,
		modeCreate	= 0x1000,
	};

	//! 原始影像文件起始点定义
	enum IMAGEORIENTATION
	{ 
		ORI_TOPLEFT	= 1,
		ORI_TOPRIGHT= 2,
		ORI_BOTLEFT = 3,
		ORI_BOTRIGHT= 4,
	};

	//! 创建16位影像至8位影像查找表方法定义
	enum LOOKUPTABLE
	{
		LUT_NONE	= 0,
		LUT_ALV		= 1, // 自动色阶
		LUT_SQRT	= 2, // 平方根
		LUT_LINEAR	= 3, // 线性变换
	};

	//! 数据压缩类型定义
	enum COMPRESSIONTECHNIQUE
	{
		CT_NONE		= 0,
		CT_LZW		= 1,
		CT_JPEG		= 2,
		CT_ZIP		= 3,
		CT_LOG		= 4,
		CT_OTHERS	= 5,
	};

	//! 自定义操作类型定义
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
	/*	打开栅格数据文件
	 *
	 *	@param[I]	lpstrPathName	文件全路径
	 *	@param[I]	flag			文件访问模式，见enum OPENFLAGS
	 *	@param[I]	nMaxBufSize		可使用内存的上限
	 *	@param[I]	pImgInstance	外部实例化对象
	 *	@param[I]	papszOptions	附加参数字符串
	 *				LOOKUPTABLE=[NONE/AUTOLEVEL/SQRT/LINEARTRANS]: 16位转8位查找表生成的方法
	 *				READPALETTECOLOR=[YES/NO]: 读取调色板影像数据，默认为NO
	 *				AUXDATADIR="": 附加数据和金字塔影像数据目录
	 *				READWALLIS=[YES/NO]: 读取Wallis变换后的影像数据，默认为NO
	 *				SOFTWARE="": TIFF文件的TIFFTAG_SOFTWARE标签值
	 *				COPYRIGHT"": TIFF文件的TIFFTAG_COPYRIGHT标签值
	 *				DOCUMENTNAME"": TIFF文件的TIFFTAG_DOCUMENTNAME标签值
	 *				IMAGEDESCRIPTION"": TIFF文件的TIFFTAG_IMAGEDESCRIPTION标签值
	 *				DATETIME"": TIFF文件的TIFFTAG_DATETIME标签值
	 *				ARTIST"": TIFF文件的TIFFTAG_ARTIST标签值
	 *				HOSTCOMPUTER"": TIFF文件的TIFFTAG_HOSTCOMPUTER标签值
	 */
	virtual BOOL Open(const char* lpstrPathName, int flag = modeRead,
		int nMaxBufSize = -1, CIdpmImage* pImgInstance = NULL,
		char** papszOptions = NULL);

	//! 关闭栅格数据文件
	virtual void Close();

	/*	按区域读取数据
	 *
	 *	@param[I/O]	pBuffer			用于接收读取的栅格数据的内存块，需外部分配
	 *	@param[I]	nBands			读取栅格数据的波段数，一般等于栅格数据文件的实际波段数。
	 *								当栅格数据实际为多波段，该值设置为1时，强制读取为灰度数据
	 *	@param[I]	sRow			读取区域的起始行
	 *	@param[I]	sCol			读取区域的起始列
	 *	@param[I]	nRows			读取区域的行数
	 *	@param[I]	nCols			读取区域的列数
	 *	@param[I]	eType			读取的数据类型
	 *				当该值不等于实际栅格数据类型时，自动进行强制转换。存在如下合法情况：
	 *				(1) 实际栅格数据类型为RDT_UINT16，即16位影像数据，eType=RDT_BYTE时，自动将16位转换为8位；
	 *				(2) 实际栅格数据类型>=RDT_INT16，即高程数据，eType=RDT_BYTE时，自动转换为晕渲数据；
	 *				(3) 当eType>=实际栅格数据类型时，强制转换
	 *	@param[I]	dfZoomRate		影像缩放倍率（目前仅支持缩小，故dfZoomRate<=1.0）
	 */
	virtual BOOL Read(void* pBuffer, int nBands, int sRow, int sCol,
		int nRows, int nCols, RASTERDATATYPE eType = RDT_BYTE,
		double dfZoomRate = 1.0);

	//! 按行写入数据
	virtual BOOL Write(void* pBuffer, int rowIdx);

	/*	按块写入数据
	 *
	 *	@param[I]	pBuffer			待写入的一块数据的内存，按从左至右，从下至上顺序存储
	 *	@param[I]	nTileX, nTileY	数据块的块坐标，块坐标的起始点与影像存储方向相关，见enum IMAGEORIENTATION
	 */
	virtual BOOL Write(void* pBuffer, int nTileX, int nTileY);

	/*	创建金字塔影像
	 *
	 *	@param[I]	nZoomRatio		缩放系数，一般取值为2或3
	 *	@param[I]	bForceToByte	强制指定金字塔栅格数据类型为BYTE
	 *	@param[I]	papszOptions	附加参数字符串
	 *              CompTechique=[NONE/LZW/JPEG/ZIP/LOG]: 金字塔栅格数据的压缩方法
	 *				CompQuality		压缩质量，默认为75
	 *              OVERWRITE=[YES/NO]: 如果金字塔栅格数据文件存在，是否覆盖。默认为NO
	 *
	 *   创建的金字塔影像为[原影像名+".ovr"]，如原影像为image.tif，则金字塔影像为image.tif.ovr
	 */
	virtual BOOL BuildPyramids(int nZoomRatio = 2, BOOL bForceToByte = FALSE,
		char** papszOptions = NULL);

	/*	自定义操作
	 *
	 *	@param[I]	wParam, lParam	参数
	 *              当wParam=COP_SETGRAYBANDS，设置多波段影像转灰度影像的有效波段。
	 *                              此时lParam的每一比特位表示对应波段是否有效，有效则为1
	 *                              如影像1，2，4波段有效，lParam二进制表示为0000 0000 0000 1011，即取值为11
	 *	@param[I]	papszOptions	附加参数字符串
	 *				WallisParas		Wallis滤波参数，格式为"mean,sigma,c,b,gridWndSize,filterWndSize"
	 */
	virtual int  CustomOperation(int wParam, int lParam, char** papszOptions = NULL);

public:
	//! 获取影像文件路径
	const char*  GetPathName() const;
	//! 获取影像的行数
	int	         GetRows() const;
	//! 获取影像的列数
	int	         GetCols() const;
	//! 获取影像的波段数
	int	         GetBands() const;
	//! 获取影像的数据类型
	int          GetDataType() const;
	//! 获取影像的分块信息
	void         GetTiledInfo(BOOL* pbTiled, int* pnTileCols = NULL, int* pnTileRows = NULL,
		int* pnTileSumX = NULL, int* pnTileSumY = NULL) const;
	//! 获取影像存储方向
	int	         GetDataOrientation() const;
	//! 获取数据压缩技术和质量
	virtual WORD GetCompression(WORD* pnCompQuality = NULL);

	static int   GetDateTypeBytes(int nType);

	//! 设置栅格影像文件的相关属性
	void         SetRows(int nRows);
	void         SetCols(int nCols);
	void         SetBands(int nBands);

	virtual BOOL SetDataType(RASTERDATATYPE nType = RDT_BYTE);
	virtual BOOL SetTiledInfo(BOOL bTiled = TRUE, int nTileCols = 256, int nTileRows = 256);

	//! 设置数据压缩技术和质量
	virtual BOOL SetCompression(WORD nCompTechnique, WORD nCompQuality = 75);
	
	//! 设置进度条处理回调函数
	virtual void SetProgressFunc(LPPROGUPDATEFUNC lpProgUpdateFunc = NULL,
		LPPROGMSGFUNC lpProgMsgFunc = NULL
		);

	//! 创建16位影像转换为8位的查找表
	virtual BOOL CreateLookUpTable(int nLutType = LUT_ALV, LPARAM lParam = 8);
	//! 设置16位影像转换为8位的查找表
	virtual BOOL SetLookUpTable(const BYTE* pLut, int bandIdx);
	//! 获取16位影像转换为8位的查找表
	virtual const BYTE* GetLookUpTable(int bandIdx);

	//! 设置地理信息变换矩阵
	virtual BOOL SetGeoTransform(const double* pGeoMatrix6, ProParameters* proParameters = NULL);
	//! 获取地理信息变换矩阵
	virtual double*	GetGeoTransform(ProParameters* proParameters = NULL) const;
	//! 影像坐标变换至地理坐标
	void	ImageToGeoProj(double ix, double iy, double* gx, double* gy) const;
	//! 地理坐标变换至影像坐标
	void	GeoProjToImage(double gx, double gy, double* ix, double* iy) const;

	//! 获取错误信息
	const char*	 GetErrorMsg();

	//! 获取金字塔影像缩放级数和倍率信息，第一层为原始1:1影像层
	const WORD*	 GetPyramidLevels(int& nLevels);

protected:
	//! 更新影像属性
	virtual void UpdateImageProp();

	//! 内部读取影像接口
	virtual BOOL IRead(void* pBuffer, int rowIdx, BOOL* bBIPFormat);
	virtual BOOL IRead(void* pBuffer, int nTileX, int nTileY, BOOL* bBIPFormat);
	virtual BOOL IWrite(void* pBuffer, int rowIdx);
	virtual BOOL IWrite(void* pBuffer, int nTileX, int nTileY);

private:
	//! 分配临时内存
	void	AllocateTmpCache();

protected:
	//! 影像大小和波段数
	int		m_nRows, m_nCols, m_nBands;
	//! 栅格数据的数据类型，取值为RASTERDATATYPE枚举值
	int		m_nDataType;
	//! 是否按分块方式存储的文件
	BOOL	m_bTiled;
	//! 栅格影像的分块大小
	int		m_nTileCols, m_nTileRows;
	//! 文件全路径
	char	m_strPathName[512];

	//! 原始影像文件的起始点，该值仅表示原始影像的起始点
	//! 本类定义的像点坐标的起始点为左下角像素的中心，与以下变量取值无关
	int		m_nOrientation;

	//! 文件打开方式（读取/写入），取值为OPENFLAGS枚举值
	int		m_nOpenMode;
	//! 外部指定的接口实例
	CIdpmImage*	m_pInstance;

	//! 地理信息变换矩阵
	double*	m_pGeoMatrix6;

	//! 16位转8位颜色表
	BYTE*	m_pLookupTable;

	//! 警告和错误信息
	char	m_strErrorMsg[512];

	//! 数据压缩类型和压缩质量
	WORD	m_nCompTechnique, m_nCompQuality;

	//! 金字塔影像缩放倍率列表
	WORD*	m_pPyramidLevel;
	int		m_nPyramidLevel;

	LPPROGUPDATEFUNC m_lpProgUpdateFunc;
	LPPROGMSGFUNC	 m_lpProgMsgFunc;
	
	//! 附加参数和辅助数据
	void*	m_pAuxCache;

private:
	//! 内部接口实例
	CIdpmImage*	m_pInstance0;

	//!	临时数据内存
	BYTE*	m_pTmpCache;
	int		m_nTmpCacheSize;
};


//! 设置参数项的名称和值
char** CFISetNameValue(
	char **papszStringList,
	const char *pszName,
	const char *pszValue
	);

//! 释放字符串列表
void CFIStringDestroy(
	char **papszStringList
	);


#endif
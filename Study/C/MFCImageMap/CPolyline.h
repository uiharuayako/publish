#pragma once
#include "CShape.h"
class CPolyline :
	public CShape
{
public:
	CPolyline();
	virtual ~CPolyline();

	virtual void Draw(CDC* pDC);
	virtual void DrawXOR(CDC* pDC, CPoint& point);
};

class CSingleRoad :public CPolyline {
public:
	CSingleRoad();
	virtual ~CSingleRoad();
};

class CDoubleRoad :public CPolyline {
public:
	CDoubleRoad();
	virtual ~CDoubleRoad();

	virtual void Draw(CDC* pDC);
};



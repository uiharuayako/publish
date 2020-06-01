#include "pch.h"
#include "CPolyline.h"

CPolyline::CPolyline()
{
	SetType("polyline");
}

CPolyline::~CPolyline()
{
}

void CPolyline::Draw(CDC* pDC)
{
	if (pDC) {
		CPoint* lpPoints = Points();
		int nPoints = NumPoints();
		pDC->Polyline(lpPoints, nPoints);
	}
}

void CPolyline::DrawXOR(CDC* pDC, CPoint& point)
{
	if (pDC) {
		CPoint* lpPoints = Points();
		int nPoints = NumPoints();

		CPen pen(PS_SOLID, 1, RGB(255, 255, 255));
		CPen* pOldPen = pDC->SelectObject(&pen);
		int op2 = pDC->SetROP2(R2_XORPEN);

		pDC->MoveTo(lpPoints[nPoints - 1]);
		pDC->LineTo(point);

		pDC->SetROP2(op2);
		pDC->SelectObject(pOldPen);
		pen.DeleteObject();
	}
}

CSingleRoad::CSingleRoad()
{
	SetType("single-road");
}

CSingleRoad::~CSingleRoad()
{
}

CDoubleRoad::CDoubleRoad()
{

	SetType("double-road");
}

CDoubleRoad::~CDoubleRoad()
{
}

void CDoubleRoad::Draw(CDC* pDC)
{
	if (pDC) {
		CPoint* lpPoints = Points();
		int nPoints = NumPoints();
		if (nPoints < 3) return;

		pDC->Polyline(lpPoints, nPoints - 1);

		CPoint offset = lpPoints[nPoints - 1] - lpPoints[nPoints - 2];

		CPoint* lpRoadPt = new CPoint[nPoints - 1];
		for (size_t i = 0; i < nPoints - 1; i++) {
			lpRoadPt[i] = lpPoints[i] + offset;
		}

		pDC->Polyline(lpRoadPt, nPoints - 1);
		delete[] lpRoadPt;
	}
}

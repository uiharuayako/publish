#pragma once

#include <string>
#include <vector>
#include <atltypes.h>
#include <afxwin.h>
using namespace std;
class CShape
{
public:
	CShape();
	virtual ~CShape();
	
	string& GetType() { return m_strType; };
	void SetType(const char* strType) { m_strType = strType; }

	virtual void Draw(CDC* pDC) = 0;
	virtual void DrawXOR(CDC* pDC, CPoint& point) = 0;

	void AddPoint(CPoint& point) { m_Points.push_back(point); }
	CPoint* Points() { return m_Points.data(); }
	int NumPoints() { return static_cast<int>(m_Points.size()); }

private:
	string m_strType;
	vector<CPoint> m_Points;
};

class CShapeFactory {
public:
	CShapeFactory(){}
	virtual ~CShapeFactory(){}

	static CShape* CreateShape(string& strType);
	static void ReleaseShape(CShape** pShape);
};
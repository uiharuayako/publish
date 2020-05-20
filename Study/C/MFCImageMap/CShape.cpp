#include "pch.h"
#include "CShape.h"
#include "CPolyline.h"

CShape::CShape()
{
	m_strType = "unknown";
}

CShape::~CShape()
{
}

CShape* CShapeFactory::CreateShape(string& strType)
{
	CShape* pShape = nullptr;
	if (strType == "single-road") {
		pShape = new CSingleRoad;
	}
	else if (strType == "double-road") {
		pShape = new CDoubleRoad;
	}
	return pShape;
}

void CShapeFactory::ReleaseShape(CShape** pShape)
{
	if (*pShape) {
		delete (*pShape);
		(*pShape) = nullptr;
	}
}

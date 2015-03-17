package org.wso2.carbon.analytics.dashboard.admin.data;

public class WidgetConfig {

	String chartType;
	int xAxis;
	int yAxis;

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public int getXAxis() {
		return xAxis;
	}

	public void setXAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	public int getYAxis() {
		return yAxis;
	}

	public void setYAxis(int yAxis) {
		this.yAxis = yAxis;
	}
}

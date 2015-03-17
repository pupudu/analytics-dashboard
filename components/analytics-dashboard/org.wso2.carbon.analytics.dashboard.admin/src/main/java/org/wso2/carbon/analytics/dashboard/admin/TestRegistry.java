package org.wso2.carbon.analytics.dashboard.admin;

import com.google.gson.Gson;
import org.wso2.carbon.analytics.dashboard.admin.data.*;

import java.util.ArrayList;

/**
 * Created by dodan on 3/13/15.
 */
public class TestRegistry {
	public static void main(String[] args) {
		DataView dv = new DataView();
		ArrayList<Column> e = new ArrayList<>();
		Column column = new Column();
		column.setName("column1");
		column.setType("type1");
		e.add(column);
		e.add(column);
		dv.setColumns(e);
		dv.setDataSource("sorce1");
		dv.setFilter("filter1");
		dv.setName("my Name");
		dv.setType("type1");

		Widget w = new Widget();
		w.setId("widgetID");

		WidgetConfig wc = new WidgetConfig();
		wc.setXAxis(1);
		wc.setYAxis(2);
		wc.setChartType("wchartType");

		WidgetDimensions wd = new WidgetDimensions();
		wd.setColumn(1);
		wd.setHeight(2);
		wd.setRow(3);
		wd.setWidth(4);

		w.setConfig(wc);
		w.setTitle("Widget Title1");

		Widget w2 = new Widget();
		w2.setId("widget2");
		w2.setConfig(wc);
		w2.setTitle("widget2 title");

		ArrayList<Widget> wa = new ArrayList<>();
		wa.add(w);
		wa.add(w2);

		dv.setWidgets(wa);

		Gson gson = new Gson();
		String s = gson.toJson(dv);
		//        ob.remove("chartType");

		DataView d = gson.fromJson(gson.toJson(dv), DataView.class);
		System.out.println(d.getName());
	}

}

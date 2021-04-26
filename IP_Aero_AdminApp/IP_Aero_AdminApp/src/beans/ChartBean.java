package beans;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import dao.CounterDAO;
import dto.Counter;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ChartBean {
    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        lineModel = new LineChartModel();
        LineChartSeries s = new LineChartSeries();
        s.setLabel("Posjetioci");
        List<Counter> list = CounterDAO.selectAll();
        int max = 1;
        int counter = 0;
        int n = 1;
        for (int i =list.size()-1; i >=0; i--) {
        	counter = list.get(i).getNumberOfVisitor();
        	s.set(n, counter);
        	n++;
        	
        	if ( counter > max) {
        		max = counter;
        	}
        }
//        s.set(1, list.get(0).getNumberOfVisitor());
//        s.set(2, 19.63);
//        s.set(3, 59.01);
//        s.set(4, 139.76);
//        s.set(5, 300.4);
//        s.set(6, 630);

        lineModel.addSeries(s);
        lineModel.setLegendPosition("e");
        Axis y = lineModel.getAxis(AxisType.Y);
        y.setMin(10);
        y.setMax(max + (max * 0.1));
        y.setLabel("Broj posjeta");

        Axis x = lineModel.getAxis(AxisType.X);
        x.setMin(1);
        x.setMax(30);
        x.setTickInterval("1");
        x.setLabel("30 dana");

    }

    public LineChartModel getLineModel() {
        return lineModel;
    }
}
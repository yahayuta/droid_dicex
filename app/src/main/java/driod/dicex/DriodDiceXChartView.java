package driod.dicex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.graphics.geom.RectShape;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * DriodDiceXChartView
 * @author yasupong
 */
public class DriodDiceXChartView extends View {
	
	/** ログリスト */
	private List<DriodDiceXScoreEntity> logList = null;
	
	private String chart_name = null;
	private String chart_x_label = null;
	private String chart_y_label = null;
	private String chart_plot = null;
	
	/**
	 * コンストラクター
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DriodDiceXChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * コンストラクター
	 * @param context
	 * @param attrs
	 */
	public DriodDiceXChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * コンストラクター
	 * @param context
	 */
	public DriodDiceXChartView(Context context) {
		super(context);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
        RectShape chartArea = new RectShape(0,0,canvas.getWidth(),400);
        
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
    	
    	if (logList != null && logList.size() > 0) {
			
			List<String> wkList = new ArrayList<String>();
			List<String> wkDateList = new ArrayList<String>();
			
			for (Iterator<DriodDiceXScoreEntity> iterator = logList.iterator(); iterator.hasNext();) {
				DriodDiceXScoreEntity data = (DriodDiceXScoreEntity) iterator.next();
				wkList.add(data.getScore());
				wkDateList.add(data.getScoredate().substring(5, 16));
			}
			
			for (int i = 0; i < wkList.size(); i++) {
				dataSet.addValue(Double.parseDouble(wkList.get(i)), chart_plot, wkDateList.get(i));
			}
    	}
    	
        AFreeChart chart = ChartFactory.createBarChart(chart_name,chart_x_label,chart_y_label,dataSet,PlotOrientation.VERTICAL,true,false,false);
        
        // 整数だけにする
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        chart.draw(canvas, chartArea);
	}

	/**
	 * ログリスト取得
	 * @param logList
	 */
	public void setLogList(List<DriodDiceXScoreEntity> logList) {
		this.logList = logList;
	}

	/**
	 * @param chart_name the chart_name to set
	 */
	public void setChart_name(String chart_name) {
		this.chart_name = chart_name;
	}

	/**
	 * @param chart_x_label the chart_x_label to set
	 */
	public void setChart_x_label(String chart_x_label) {
		this.chart_x_label = chart_x_label;
	}

	/**
	 * @param chart_y_label the chart_y_label to set
	 */
	public void setChart_y_label(String chart_y_label) {
		this.chart_y_label = chart_y_label;
	}

	/**
	 * @param chart_plot the chart_plot to set
	 */
	public void setChart_plot(String chart_plot) {
		this.chart_plot = chart_plot;
	}
}

package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetDepositPage;
import com.moredian.entrance.guard.entity.GetExpensePage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.StringUtils;
import com.moredian.entrance.guard.view.designview.MPChartMarkerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChartActivity extends BaseActivity {

    private static final String TAG = "ChartActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.barchart)
    BarChart barchart;
    private List<GetExpensePage.ContentBean.RowsBean> e = new ArrayList<>();
    private List<GetDepositPage.ContentBean.RowsBean> d = new ArrayList<>();
    private List<Float> first = new ArrayList<>();
    private List<Float> second = new ArrayList<>();
    private List<Float> third = new ArrayList<>();
    private List<Float> fourth = new ArrayList<>();
    private List<Float> fifth = new ArrayList<>();
    private List<Float> sexth = new ArrayList<>();
    private List<Float> seventh = new ArrayList<>();

    private List<Float> first2 = new ArrayList<>();
    private List<Float> second2 = new ArrayList<>();
    private List<Float> third2 = new ArrayList<>();
    private List<Float> fourth2 = new ArrayList<>();
    private List<Float> fifth2 = new ArrayList<>();
    private List<Float> sexth2 = new ArrayList<>();
    private List<Float> seventh2 = new ArrayList<>();
    private List<Integer> x = new ArrayList<>();
    private List<Float> y = new ArrayList<>();
    private List<Float> y2 = new ArrayList<>();

    /**
     * descirption: 获取当前activity 的Intent对象
     */
    public static Intent getChartActivityIntent(Context context) {
        Intent intent = new Intent(context, ChartActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_chart;
    }

    @Override
    public void initView() {
        pageName.setText("七天报表");
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                barchart.setNoDataText("小图正在全力加载数据！");
                barchart.setNoDataTextColor(getResources().getColor(R.color.colorPrimary));
                barchart.invalidate();

            }
        },100);

        initList();
    }

    private void initList() {
        x.clear();
        y.clear();
        y2.clear();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);
        x.add(5);
        x.add(6);
        x.add(7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        api.getExpensePage(token, 1, 5000, dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 6), dateFormat.format(new Date()));
        api.getDepositPage(token, 1, 5000, dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 6), dateFormat.format(new Date()));
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetExpensePage) {
                    e.clear();
                    e.addAll(((GetExpensePage) o).getContent().getRows());
                    Log.d(TAG, "onRespnse: " + e.size());
                    for (int i = 0; i < e.size(); i++) {
                        if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 6))) {
                            Log.d(TAG, "onRespnse: first" );
                            first.add((float) e.get(i).getAmount());
                        } else if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 5))) {
                            Log.d(TAG, "onRespnse: second" );
                            second.add((float) e.get(i).getAmount());
                        } else if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 4))) {
                            Log.d(TAG, "onRespnse: third" );
                            third.add((float) e.get(i).getAmount());
                        } else if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 3))) {
                            Log.d(TAG, "onRespnse: fourth" );
                            fourth.add((float) e.get(i).getAmount());
                        } else if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 2))) {
                            Log.d(TAG, "onRespnse: fif" );
                            fifth.add((float) e.get(i).getAmount());
                        } else if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000))) {
                            Log.d(TAG, "onRespnse: sex" );
                            sexth.add((float) e.get(i).getAmount());
                        } else if (e.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date()))) {
                            Log.d(TAG, "onRespnse: seven" );
                            seventh.add((float) e.get(i).getAmount());
                        }
                    }
                    setData(first, 1);
                    setData(second, 1);
                    setData(third, 1);
                    setData(fourth, 1);
                    setData(fifth, 1);
                    setData(sexth, 1);
                    setData(seventh, 1);
                }
                if (o instanceof GetDepositPage) {
                    d.clear();
                    d.addAll(((GetDepositPage) o).getContent().getRows());
                    for (int i = 0; i < d.size(); i++) {
                        if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 6))) {
                            first2.add((float) d.get(i).getAmount());
                        } else if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 5))) {
                            second2.add((float) d.get(i).getAmount());
                        } else if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 4))) {
                            third2.add((float) d.get(i).getAmount());
                        } else if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 3))) {
                            fourth2.add((float) d.get(i).getAmount());
                        } else if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000 * 2))) {
                            fifth2.add((float) d.get(i).getAmount());
                        } else if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date().getTime() - 60 * 60 * 24 * 1000))) {
                            sexth2.add((float) d.get(i).getAmount());
                        } else if (d.get(i).getTradeDateTime().substring(0, 10).equals(dateFormat.format(new Date()))) {
                            seventh2.add((float) d.get(i).getAmount());
                        }
                    }
                    setData(first2, 2);
                    setData(second2, 2);
                    setData(third2, 2);
                    setData(fourth2, 2);
                    setData(fifth2, 2);
                    setData(sexth2, 2);
                    setData(seventh2, 2);
                }
                if (x.size() > 0 && y.size() > 0 && y2.size() > 0) {
                    setTwoBarChart(barchart, x, y, y2, "消費", "充值");
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    private float floatSum(List<Float> list) {
        float sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    private void setData(List<Float> list, int k) {
        if (k == 1) {
            if (list.size() > 0) {
                y.add(floatSum(list));
            } else {
                y.add(0f);
            }
        } else {
            if (list.size() > 0) {
                y2.add(floatSum(list));
            } else {
                y2.add(0f);
            }
        }
    }

    /**
     * 设置双柱状图样式
     *
     * @param barChart
     * @param xAxisValue
     * @param yAxisValue1
     * @param yAxisValue2
     * @param bartilte1
     * @param bartitle2
     */
    public static void setTwoBarChart(BarChart barChart, List<Integer> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2, String bartilte1, String bartitle2) {
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.setExtraBottomOffset(10);
        barChart.setExtraTopOffset(30);

        MPChartMarkerView markerView = new MPChartMarkerView(barChart.getContext(), R.layout.custom_marker_view);
        barChart.setMarker(markerView);

        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return String.valueOf((int) v);
            }
        });

        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        //设置坐标轴最大最小值
        Float yMin1 = Collections.min(yAxisValue1);
        Float yMin2 = Collections.min(yAxisValue2);
        Float yMax1 = Collections.max(yAxisValue1);
        Float yMax2 = Collections.max(yAxisValue2);
        Float yMin = Double.valueOf((yMin1 < yMin2 ? yMin1 : yMin2) * 0.1).floatValue();
        Float yMax = Double.valueOf((yMax1 > yMax2 ? yMax1 : yMax2) * 1.1).floatValue();
        leftAxis.setAxisMaximum(yMax);
        leftAxis.setAxisMinimum(yMin);

        barChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(26f);

        //设置柱状图数据
        setTwoBarChartData(barChart, xAxisValue, yAxisValue1, yAxisValue2, bartilte1, bartitle2);

        barChart.animateX(1500);//数据显示动画，从左往右依次显示
        barChart.invalidate();
    }

    /**
     * 设置柱状图数据源
     */
    private static void setTwoBarChartData(BarChart barChart, List<Integer> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2, String bartilte1, String bartitle2) {
        float groupSpace = 0.04f;
        float barSpace = 0.03f;
        float barWidth = 0.45f;
        // (0.45 + 0.03) * 2 + 0.04 = 1，即一个间隔为一组，包含两个柱图 -> interval per "group"

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0, n = yAxisValue1.size(); i < n; ++i) {
            entries1.add(new BarEntry(xAxisValue.get(i), yAxisValue1.get(i)));
            entries2.add(new BarEntry(xAxisValue.get(i), yAxisValue2.get(i)));
        }

        BarDataSet dataset1, dataset2;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            dataset1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            dataset2 = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            dataset1.setValues(entries1);
            dataset2.setValues(entries2);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            dataset1 = new BarDataSet(entries1, bartilte1);
            dataset2 = new BarDataSet(entries2, bartitle2);

            dataset1.setColor(Color.rgb(23, 192, 200));
            dataset2.setColor(Color.rgb(249, 96, 50));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataset1);
            dataSets.add(dataset2);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(16f);
            data.setBarWidth(0.5f);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    return StringUtils.double2String(value, 2);
                }
            });

            barChart.setData(data);
        }

        barChart.getBarData().setBarWidth(barWidth);
        barChart.getXAxis().setAxisMinimum(xAxisValue.get(0));
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size() + xAxisValue.get(0));
        barChart.groupBars(xAxisValue.get(0), groupSpace, barSpace);
    }

    @OnClick({R.id.Manualconsumption_back, R.id.page_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.page_name:
                break;
        }
    }

}

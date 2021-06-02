package com.hunny.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class detailactivity extends AppCompatActivity {
    private  int position;
    TextView tvcountry,tvcases,tvtodayCases,tvdeaths,tvtodayDeaths,tvrecovred,tvtodayRecovered,tvactive,tvcritical;
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);
        pieChart=findViewById(R.id.piechartdetail);
        Intent intent =getIntent();
        position=intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Detail of "+ trackcountries.countrymodelList.get(position).getCountries());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       tvcountry=findViewById(R.id.detailcountry);
       tvcases=findViewById(R.id.detailcases);
       tvtodayCases=findViewById(R.id.detailtodaycases);
       tvdeaths=findViewById(R.id.detaildeath);
       tvtodayDeaths=findViewById(R.id.detailtodaydeath);
       tvrecovred=findViewById(R.id.detailrecovered);
       tvtodayRecovered=findViewById(R.id.detailtodayrecovred);
       tvactive=findViewById(R.id.detailActiveCases);
       tvcritical=findViewById(R.id.detailcritical);


        tvtodayCases.setText(trackcountries.countrymodelList.get(position).getTodayCases());
        tvcountry.setText(trackcountries.countrymodelList.get(position).getCountries());
        tvcases.setText(trackcountries.countrymodelList.get(position).getCases());
        tvtodayDeaths.setText(trackcountries.countrymodelList.get(position).getDeaths());
        tvdeaths.setText(trackcountries.countrymodelList.get(position).getTodayDeaths());
        tvrecovred.setText(trackcountries.countrymodelList.get(position).getRecovered());
        tvtodayRecovered.setText(trackcountries.countrymodelList.get(position).getTodayRecovered());
        tvactive.setText(trackcountries.countrymodelList.get(position).getActive());
        tvcritical.setText(trackcountries.countrymodelList.get(position).getCritical());

        pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#F8D15E")));
        pieChart.addPieSlice(new PieModel("recovered",Integer.parseInt(tvrecovred.getText().toString()), Color.parseColor("#70F444")));
        pieChart.addPieSlice(new PieModel("deaths",Integer.parseInt(tvdeaths.getText().toString()), Color.parseColor("#eb3437")));
        pieChart.addPieSlice(new PieModel("active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#1E6EF1")));
        pieChart.startAnimation();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

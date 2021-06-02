package com.hunny.covidtracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvcases,tvrecovered,tvcritical,tvactive,tvdeaths,tvtotalRecovered,tveffectcountries,tvtodaycases;
    Button btn;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcases=findViewById(R.id.tvcases);
        tvrecovered=findViewById(R.id.tvrecovered);
        tvcritical=findViewById(R.id.tvcritical);
        tvactive=findViewById(R.id.tvactive);
        tvdeaths=findViewById(R.id.tvdeaths);
        btn=findViewById(R.id.btn_track);
        tvtotalRecovered=findViewById(R.id.tvtotalRecovered);
        tvtodaycases=findViewById(R.id.tvtodaycases);
        tveffectcountries=findViewById(R.id.tveffectcountries);
        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollstats);
        pieChart=findViewById(R.id.piechart);





        fetchdata();

    }

    private void fetchdata() {
        String url ="https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvcases.setText(jsonObject.getString("cases"));
                    tvrecovered.setText(jsonObject.getString("todayRecovered"));
                    tvcritical.setText(jsonObject.getString("critical"));
                    tvactive.setText(jsonObject.getString("active"));
                    tvdeaths.setText(jsonObject.getString("deaths"));
                    tvtotalRecovered.setText(jsonObject.getString("recovered"));
                    tvtodaycases.setText(jsonObject.getString("todayCases"));
                    tveffectcountries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#F8D15E")));
                    pieChart.addPieSlice(new PieModel("recovered",Integer.parseInt(tvtotalRecovered.getText().toString()), Color.parseColor("#70F444")));
                    pieChart.addPieSlice(new PieModel("deaths",Integer.parseInt(tvdeaths.getText().toString()), Color.parseColor("#eb3437")));
                    pieChart.addPieSlice(new PieModel("active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#1E6EF1")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();




            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    public void gotrack(View view) {
        startActivity(new Intent(MainActivity.this,trackcountries.class));
    }
}

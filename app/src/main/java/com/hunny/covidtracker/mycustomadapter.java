package com.hunny.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class mycustomadapter<ArrayAdapter, Context> extends ArrayAdapter<countrymodel> {
    private Context context;
    private List<countrymodel> countrymodelList;
    private List<countrymodel> countrymodelListfiltered;


    public mycustomadapter( Context context, List<countrymodel>countrymodelList) {
        super(context,R.layout.list_custom,countrymodelList);
        this.context=context;
        this.countrymodelList=countrymodelList;
        this.countrymodelListfiltered=countrymodelList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom,null,true);
        TextView countryname=view.findViewById(R.id.countryname);
        ImageView countryflag=view.findViewById(R.id.imageflag);

        countryname.setText(countrymodelListfiltered.get(position).getCountries());
        Glide.with(context).load(countrymodelListfiltered.get(position).getFlag()).into(countryflag);
        return view;
    }

    @Override
    public int getCount() {
        return countrymodelListfiltered.size();

    }

    @Nullable
    @Override
    public countrymodel getItem(int position) {
        return countrymodelListfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence==null || charSequence.length()==0)
                {
                    filterResults.count=countrymodelList.size();
                    filterResults.values=countrymodelList;
                }
                else
                {
                    List<countrymodel> resultmodel=new ArrayList<>();
                    String searchStr=charSequence.toString().toLowerCase();
                    for(countrymodel itemmodel:countrymodelList){
                        if(itemmodel.getCountries().toLowerCase().contains(searchStr))
                        {
                            resultmodel.add(itemmodel);
                        }
                        filterResults.count=resultmodel.size();
                        filterResults.values=resultmodel;
                    }
                }
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countrymodelListfiltered = (List<countrymodel>) filterResults.values;
                trackcountries.countrymodelList=(List<countrymodel>) filterResults.values;
                notifyDataSetChanged();
            }

            };
        return filter;
        }
}

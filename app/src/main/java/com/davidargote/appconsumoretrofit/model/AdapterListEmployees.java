package com.davidargote.appconsumoretrofit.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.davidargote.appconsumoretrofit.R;

import java.util.ArrayList;

public class AdapterListEmployees extends BaseAdapter {

    Context context;
    ArrayList<Employees> list;

    public AdapterListEmployees(Context context, ArrayList<Employees> list) {
        this.context = context;
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView tvName, tvSalary;

        view = LayoutInflater.from(context).inflate(R.layout.item_list_employees, viewGroup, false);

        /*init views*/

        tvName = view.findViewById(R.id.tvNameItem);
        tvSalary = view.findViewById(R.id.tvSalaryItem);

        /* Fill views */

        tvName.setText(list.get(i).getEmployee_name());
        tvSalary.setText(String.valueOf(list.get(i).getEmployee_salary()));

        return view;
    }
}

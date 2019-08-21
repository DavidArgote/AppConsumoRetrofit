package com.davidargote.appconsumoretrofit.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.davidargote.appconsumoretrofit.Interfaces.RestApiEmployees;
import com.davidargote.appconsumoretrofit.R;
import com.davidargote.appconsumoretrofit.model.AdapterListEmployees;
import com.davidargote.appconsumoretrofit.model.Employees;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    private ListView lvLitas;
    private AdapterListEmployees adapterListEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().hide();

        getRequest();
        referent();


    }


    private void referent() {

        lvLitas = findViewById(R.id.lvLista);

    }

    private void getRequest(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApiEmployees restApiEmployees = retrofit.create(RestApiEmployees.class);

        Call<List<Employees>> call = restApiEmployees.getEmployees();

        call.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                if (!response.isSuccessful()){
                    Log.e("Error", "No hay datos");
                    return;
                }

                //ArrayAdapter<Employees> adapter = new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_1, response.body());
                //lvLitas.setAdapter(adapter);

                adapterListEmployees = new AdapterListEmployees(ListActivity.this, (ArrayList<Employees>) response.body());
                lvLitas.setAdapter(adapterListEmployees);
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });




    }

}

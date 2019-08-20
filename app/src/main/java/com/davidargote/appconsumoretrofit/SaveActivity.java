package com.davidargote.appconsumoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.davidargote.appconsumoretrofit.Interfaces.RestApiEmployees;
import com.davidargote.appconsumoretrofit.model.PostEmployee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaveActivity extends AppCompatActivity {

    private EditText editName, editSalary, editAge;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);


        initViews();

    }

    private void initViews() {

        editName = findViewById(R.id.editNombre);
        editSalary = findViewById(R.id.editSalario);
        editAge = findViewById(R.id.editEdad);

        btnSave = findViewById(R.id.btnGuardar);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requesPost();
            }
        });
    }

    private void requesPost() {

        PostEmployee postEmployee = new PostEmployee();

        postEmployee.setName(editName.getText().toString());
        postEmployee.setSalary(Double.parseDouble(editSalary.getText().toString()));
        postEmployee.setAge(Integer.parseInt(editAge.getText().toString()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApiEmployees restApiEmployees = retrofit.create(RestApiEmployees.class);

        Call<PostEmployee> call = restApiEmployees.createUser(postEmployee);

        call.enqueue(new Callback<PostEmployee>() {
            @Override
            public void onResponse(Call<PostEmployee> call, Response<PostEmployee> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(SaveActivity.this, "No se insertaron los datos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SaveActivity.this, "Se inserto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostEmployee> call, Throwable t) {
                Log.e("ErrorApi", t.getMessage());
            }
        });

    }
}

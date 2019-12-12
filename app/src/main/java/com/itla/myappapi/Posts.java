package com.itla.myappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.myappapi.adaptadores.PostAdaptador;
import com.itla.myappapi.entity.Post;
import com.itla.myappapi.entity.User;
import com.itla.myappapi.service.BlogApiServices;
import com.itla.myappapi.service.PostServices;
import com.itla.myappapi.service.SecurityService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Posts extends AppCompatActivity {

    ArrayList<Post> postArrayList;
    PostAdaptador postAdaptador;
    RecyclerView recyclerView;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.rcpost);
        recyclerView.setLayoutManager(linearLayoutManager);

        SecurityService securityService = BlogApiServices.getInstance().getSecurityService();

        PostServices postServices = BlogApiServices.getInstance().getPostService();
        SharedPreferences editor = getApplicationContext().getSharedPreferences("mypreferences", MODE_PRIVATE);
        token = editor.getString("token", null);
        Call<ArrayList<Post>> cpost = postServices.post(token);
        cpost.enqueue(new Callback<ArrayList<Post>>() {

            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {

                if (response.code() == 200) {
                    postArrayList = response.body();
                    Log.i("body", response.body().toString());
                    postAdaptador = new PostAdaptador(postArrayList);
                    recyclerView.setAdapter(postAdaptador);
//                    postAdaptador.notifyDataSetChanged();
                } else {
                    Log.i("body", "Error en traer los posts ");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnlogout:
                SecurityService securityService = BlogApiServices.getInstance().getSecurityService();
                Call<Void> cpost = securityService.logout(token);
                cpost.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            SharedPreferences.Editor editor = getSharedPreferences("mypreferences", MODE_PRIVATE).edit();
                            editor.remove("token");
                            editor.apply();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
        }
        return true;
    }
}

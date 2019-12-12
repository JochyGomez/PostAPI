package com.itla.myappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.itla.myappapi.entity.Post;
import com.itla.myappapi.service.BlogApiServices;
import com.itla.myappapi.service.PostServices;
import com.itla.myappapi.service.SecurityService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class crear_post extends AppCompatActivity {

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText title=findViewById(R.id.tvtitle);
        final EditText body=findViewById(R.id.tvbody);
        final EditText tag=findViewById(R.id.tvtag);
        Button postear=findViewById(R.id.btnpostear);

        SecurityService securityService = BlogApiServices.getInstance().getSecurityService();

        PostServices postServices = BlogApiServices.getInstance().getPostService();
        SharedPreferences editor = getApplicationContext().getSharedPreferences("mypreferences", MODE_PRIVATE);
        token = editor.getString("token", null);

        postear.setOnClickListener(

                SecurityService securityService = BlogApiServices.getInstance().getSecurityService();

        Post post = new Post();
        post.setTitle(title.getText().toString());
        post.setBody(body.getText().toString());
        post.setTag(tag.getText().toString());

        Call<User> cuser = securityService.login(login);

        cuser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code()== 201) {
                    User user = response.body();
                    SharedPreferences.Editor editor = getSharedPreferences("mypreferences", MODE_PRIVATE).edit();
                    editor.putString("token", "Bearer " + user.getToken());

                    editor.apply();
                    Log.i("LOGIN", user.toString());
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER,0 ,0 );
                    toast1.show();
                    Intent post = new Intent(getApplicationContext(), Posts.class);
                    startActivity(post);
                }else {
                    Log.i("LOGIN","Error en el login");
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Error en el login", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER,0 ,0 );

                    toast1.show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.i("LOGIN", "Error en el login:"+t.getMessage());
                Toast toast1 = Toast.makeText(getApplicationContext(), "Error en el login", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER,0 ,0 );

                toast1.show();
            }
        });
    }
});

final TextView crear = findViewById(R.id.tvcrear);

        crear.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent regis = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(regis);
        );

    }
}

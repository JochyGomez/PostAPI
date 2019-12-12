package com.itla.myappapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itla.myappapi.entity.Login;
import com.itla.myappapi.entity.Post;
import com.itla.myappapi.entity.User;
import com.itla.myappapi.service.BlogApiServices;
import com.itla.myappapi.service.SecurityService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences editor = getApplicationContext().getSharedPreferences("mypreferences", MODE_PRIVATE);
        String token= editor.getString("token", null);
        if (token!= null){
            Intent post = new Intent(getApplicationContext(), Posts.class);
            startActivity(post);
        }

        final EditText correo=findViewById(R.id.tvemail);
        final EditText contrasena=findViewById(R.id.tvpass);
        Button iniciar=findViewById(R.id.btnlogin);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecurityService securityService = BlogApiServices.getInstance().getSecurityService();

                Login login = new Login();
                login.setEmail(correo.getText().toString());
                login.setPassword(contrasena.getText().toString());

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
             }
        });
    }
}

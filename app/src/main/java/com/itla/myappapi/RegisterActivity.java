package com.itla.myappapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.myappapi.entity.Register;
import com.itla.myappapi.entity.User;
import com.itla.myappapi.service.BlogApiServices;
import com.itla.myappapi.service.SecurityService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name=findViewById(R.id.tvname);
        final EditText correo=findViewById(R.id.tvemail);
        final EditText contrasena=findViewById(R.id.tvpassword);
        Button iniciar=findViewById(R.id.btnregister);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecurityService securityService = BlogApiServices.getInstance().getSecurityService();

                Register register = new Register();
                register.setName(name.getText().toString());
                register.setEmail(correo.getText().toString());
                register.setPassword(contrasena.getText().toString());

                Call<User> cuser = securityService.register(register);

                cuser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.code()== 201) {
                            User user = response.body();
                            Log.i("REGISTER", user.toString());
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Registrado Sastifactoriamente", Toast.LENGTH_SHORT);
                            toast1.setGravity(Gravity.CENTER,0 ,0 );

                            toast1.show();
                        }else {
                            Log.i("REGISTER","Error en el registro");
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Error en el registro", Toast.LENGTH_SHORT);
                            toast1.setGravity(Gravity.CENTER,0 ,0 );

                            toast1.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Log.i("REGISTER", "Error en el registro:"+t.getMessage());
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Error en el registro", Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.CENTER,0 ,0 );

                        toast1.show();
                    }
                });
            }
        });
    }
}

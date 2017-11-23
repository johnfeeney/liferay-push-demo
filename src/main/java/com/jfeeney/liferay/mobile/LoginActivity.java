package com.jfeeney.liferay.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;

public class LoginActivity extends AppCompatActivity implements LoginListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginScreenlet loginScreenlet=(LoginScreenlet)findViewById(R.id.login_screenlet);
        loginScreenlet.setListener(this);
    }

    @Override
    public void onLoginSuccess(final User user) {
        String message = String.format("Login successful", user.getEmail());
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onLoginFailure(final Exception e) {
        String message="Incorrect username/password";
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}

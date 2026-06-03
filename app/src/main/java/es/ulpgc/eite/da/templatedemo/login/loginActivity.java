package es.ulpgc.eite.da.templatedemo.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class loginActivity extends AppCompatActivity implements loginContract.View {

    private loginContract.Presenter presenter;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLoginContinue;
    private Button btnLoginGuest;
    private Button btnLoginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLoginContinue = findViewById(R.id.btnLoginContinue);
        btnLoginGuest = findViewById(R.id.btnLoginGuest);
        btnLoginRegister = findViewById(R.id.btnLoginRegister);

        loginScreen.configure(this);

        btnLoginContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                presenter.onLoginButtonClicked(email, password);
            }
        });

        btnLoginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGuestButtonClicked();
            }
        });

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(loginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(loginViewModel viewModel) {
    }

    @Override
    public void navigateToHome() {
        AppMediator.getInstance().goToHome(this);
        finish();
    }

    @Override
    public void navigateToRegister() {
        AppMediator.getInstance().goToRegisterUser(this);
    }
}
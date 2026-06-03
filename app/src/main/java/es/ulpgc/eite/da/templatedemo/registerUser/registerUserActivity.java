package es.ulpgc.eite.da.templatedemo.registerUser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;

public class registerUserActivity extends AppCompatActivity implements registerUserContract.View {

    private registerUserContract.Presenter presenter;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegisterContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegisterContinue = findViewById(R.id.btnRegisterContinue);

        registerUserScreen.configure(this);

        btnRegisterContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                presenter.onRegisterButtonClicked(email, password);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(registerUserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(registerUserViewModel viewModel) {
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void showErrorMessage() {
        TextView tvError = findViewById(R.id.tvRegisterError);
        tvError.setVisibility(View.VISIBLE); // Mostramos el texto rojo
    }
}
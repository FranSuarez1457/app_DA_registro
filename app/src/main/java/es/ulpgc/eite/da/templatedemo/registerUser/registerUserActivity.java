package es.ulpgc.eite.da.templatedemo.registerUser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;

public class registerUserActivity extends AppCompatActivity implements registerUserContract.View {

    private registerUserContract.Presenter presenter;

    // Variables (Tendremos que ajustar los IDs cuando me pases tu XML)
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apuntamos a tu archivo XML
        setContentView(R.layout.register_user);

        // Enlazamos vistas (saldrán en rojo hasta que ajustemos los IDs)
        /*
        etName = findViewById(R.id.etRegName);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        btnRegister = findViewById(R.id.btnRegSubmit);

        registerUserScreen.configure(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterButtonClicked(
                    etEmail.getText().toString(),
                    etPassword.getText().toString(),
                    etName.getText().toString()
                );
            }
        });
        */

        // Lo configuramos aunque los botones estén comentados para que no dé error
        registerUserScreen.configure(this);
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
        // Pintar errores si los hubiera
    }

    @Override
    public void finishView() {
        finish(); // Cierra esta pantalla y te devuelve automáticamente al Login
    }
}

package es.ulpgc.eite.da.templatedemo.registerUser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;

public class registerUserActivity extends AppCompatActivity implements registerUserContract.View {

    private registerUserContract.Presenter presenter;

    // Variables con los nombres exactos de tu XML
    private EditText etEmail;
    private EditText etPassword;
    private EditText etCompanyCode;
    private Button btnRegisterContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apuntamos a tu archivo register_user.xml
        setContentView(R.layout.register_user);

        // 1. Enlazamos las vistas
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCompanyCode = findViewById(R.id.etCompanyCode);
        btnRegisterContinue = findViewById(R.id.btnRegisterContinue);

        // 2. Configuramos el módulo (ensamblador MVP)
        registerUserScreen.configure(this);

        // 3. Programar el clic del botón
        btnRegisterContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String companyCode = etCompanyCode.getText().toString();

                // Le pasamos los datos al Presenter
                presenter.onRegisterButtonClicked(email, password, companyCode);
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
        // Aquí pintaríamos errores en pantalla si los hubiera
    }

    @Override
    public void finishView() {
        finish(); // Cierra esta pantalla y te devuelve automáticamente al Login
    }
}

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

    // Variables para tus vistas XML
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLoginContinue;
    private Button btnLoginGuest;
    private Button btnLoginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apunta a tu archivo login.xml
        setContentView(R.layout.login);

        // 1. Enlazar las vistas con los IDs EXACTOS de tu XML
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLoginContinue = findViewById(R.id.btnLoginContinue);
        btnLoginGuest = findViewById(R.id.btnLoginGuest);
        btnLoginRegister = findViewById(R.id.btnLoginRegister);

        // 2. Configurar el módulo (ensamblador MVP)
        loginScreen.configure(this);

        // 3. Programar el clic de los botones

        // Botón Continuar (Login normal)
        btnLoginContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Le pasamos los datos al Presenter para que valide (admin@empresa.com / 123)
                presenter.onLoginButtonClicked(email, password);
            }
        });

        // Botón Invitado (De momento lo mandamos directo al Home para que puedas probar)
        btnLoginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMediator.getInstance().goToHome(loginActivity.this);
                finish();
            }
        });

        // Botón Crear Usuario (De momento vacío hasta que hagamos esa pantalla)
        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí llamaremos al mediador para ir a la pantalla de registro:
                // AppMediator.getInstance().goToRegisterUser(loginActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(); // Avisamos al presenter que la pantalla está visible
    }

    @Override
    public void injectPresenter(loginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(loginViewModel viewModel) {
        // Como no pusiste un TextView para errores en el XML, dejamos esto vacío por ahora.
        // Si más adelante quieres mostrar el error de "Contraseña incorrecta", lo añadiremos.
    }

    @Override
    public void navigateToHome() {
        // Usamos el Mediador para saltar de pantalla al hacer login con éxito
        AppMediator.getInstance().goToHome(this);
        finish(); // Cerramos el login para que el usuario no pueda volver atrás
    }
}
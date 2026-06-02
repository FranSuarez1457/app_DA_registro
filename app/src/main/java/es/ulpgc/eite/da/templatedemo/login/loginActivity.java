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

                // Le pasamos los datos al Presenter para que valide en la base de datos
                presenter.onLoginButtonClicked(email, password);
            }
        });

        // Botón Invitado
        btnLoginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // El Presenter limpia la sesión y nos manda al Home
                presenter.onGuestButtonClicked();
            }
        });

        // Botón Crear Usuario
        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le decimos al Presenter que queremos ir al registro (Cumpliendo MVP estricto)
                presenter.onRegisterButtonClicked();
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
        // Vacío por ahora
    }

    // --- MÉTODOS DEL CONTRATO QUE EL PRESENTER USARÁ PARA NAVEGAR ---

    @Override
    public void navigateToHome() {
        AppMediator.getInstance().goToHome(this);
        finish(); // Cerramos el login para que el usuario no pueda volver atrás
    }

    @Override
    public void navigateToRegister() {
        AppMediator.getInstance().goToRegisterUser(this);
    }
}
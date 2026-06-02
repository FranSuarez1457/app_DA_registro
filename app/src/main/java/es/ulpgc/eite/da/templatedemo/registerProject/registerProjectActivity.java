package es.ulpgc.eite.da.templatedemo.registerProject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class registerProjectActivity extends AppCompatActivity implements registerProjectContract.View {

    private registerProjectContract.Presenter presenter;

    // Vistas del XML
    private TextView tvCompanyBarRegProject;
    private EditText etProjectName;
    private EditText etProjectDescription;
    private EditText etProjectDate;
    private Button btnSubmitProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apuntamos a tu archivo register_project.xml
        setContentView(R.layout.register_project);

        // 1. Enlazamos los IDs
        tvCompanyBarRegProject = findViewById(R.id.tvCompanyBarRegProject);
        etProjectName = findViewById(R.id.etProjectName);
        etProjectDescription = findViewById(R.id.etProjectDescription);
        etProjectDate = findViewById(R.id.etProjectDate);
        btnSubmitProject = findViewById(R.id.btnSubmitProject);

        // 2. Configuramos el ensamblador
        registerProjectScreen.configure(this);

        // 3. Programamos el clic del botón ajustado al contrato
        btnSubmitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recogemos lo que el usuario ha escrito
                String name = etProjectName.getText().toString();
                String description = etProjectDescription.getText().toString();

                // Se lo pasamos al Presenter (Usando el método exacto del contrato)
                presenter.onSaveButtonClicked(name, description);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(registerProjectContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(registerProjectViewModel viewModel) {
        // En el futuro, aquí pondremos el nombre de la compañía que saque del Mediador
    }

    // --- MÉTODO EXIGIDO POR EL CONTRATO ---
    @Override
    public void finishView() {
        // Cuando el proyecto se guarde con éxito en la base de datos,
        // volvemos directamente a la lista de proyectos para que el usuario vea que se ha añadido.
        AppMediator.getInstance().goToProjectList(this);
        finish(); // Destruimos la pantalla de registro
    }
}

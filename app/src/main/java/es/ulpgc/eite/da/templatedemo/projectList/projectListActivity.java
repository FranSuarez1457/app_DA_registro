package es.ulpgc.eite.da.templatedemo.projectList;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class projectListActivity extends AppCompatActivity implements projectListContract.View {

    private projectListContract.Presenter presenter;

    // Variables con los IDs de tu XML
    private TextView tvCompanyBarList;
    private TextView tvListTitle;
    private ConstraintLayout cardProject1;
    private TextView tvProjectNameItem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apuntamos a tu archivo project_list.xml
        setContentView(R.layout.project_list);

        // 1. Enlazamos las vistas
        tvCompanyBarList = findViewById(R.id.tvCompanyBarList);
        tvListTitle = findViewById(R.id.tvListTitle);
        cardProject1 = findViewById(R.id.cardProject1);
        tvProjectNameItem1 = findViewById(R.id.tvProjectNameItem1);

        // 2. Configuramos el ensamblador MVP
        projectListScreen.configure(this);

        // 3. Programamos el clic de la tarjeta del proyecto
        cardProject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sacamos el texto que tenga la tarjeta en ese momento
                String projectName = tvProjectNameItem1.getText().toString();

                // Le avisamos al Presenter de qué proyecto se ha tocado
                presenter.onProjectClicked(projectName);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(projectListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(projectListViewModel viewModel) {
        // Como tenemos una lista simulada en el Model, vamos a poner
        // el primer elemento de esa lista en tu tarjeta para ver que funciona.
        if (viewModel.projectList != null && !viewModel.projectList.isEmpty()) {
            tvProjectNameItem1.setText(viewModel.projectList.get(0));
        }
    }

    @Override
    public void navigateToProjectDetail() {
        // ¡Viajamos a la pantalla de detalle!
        AppMediator.getInstance().goToProjectDetail(this);
    }
}
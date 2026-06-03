package es.ulpgc.eite.da.templatedemo.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class homeActivity extends AppCompatActivity implements homeContract.View {

    private homeContract.Presenter presenter;

    private TextView tvCompanyBarHome;
    private Button btnNavRegisterProject;
    private Button btnNavRegisterTask;
    private Button btnNavProjectList;
    private Button btnNavFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        tvCompanyBarHome = findViewById(R.id.tvCompanyBarHome);
        btnNavRegisterProject = findViewById(R.id.btnNavRegisterProject);
        btnNavRegisterTask = findViewById(R.id.btnNavRegisterTask);
        btnNavProjectList = findViewById(R.id.btnNavProjectList);
        btnNavFavorites = findViewById(R.id.btnNavFavorites);

        homeScreen.configure(this);

        btnNavRegisterProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterProjectBtnClicked();
            }
        });

        btnNavRegisterTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterTaskBtnClicked();
            }
        });

        btnNavProjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onProjectListBtnClicked();
            }
        });

        btnNavFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFavoritesBtnClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(homeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(homeViewModel viewModel) {
    }

    @Override
    public void navigateToRegisterProject() {
        AppMediator.getInstance().goToRegisterProject(this);
    }

    @Override
    public void navigateToRegisterTask() {
        AppMediator.getInstance().goToRegisterTask(this);
    }

    @Override
    public void navigateToProjectList() {
        AppMediator.getInstance().goToProjectList(this);
    }

    @Override
    public void showFavoritesMessage() {
        Toast.makeText(this, "Pantalla de Favoritos en construcción", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGuestMessage() {
        android.widget.Toast.makeText(this,
                "Modo Invitado: Permisos insuficientes para crear registros.",
                android.widget.Toast.LENGTH_LONG).show();
    }
}
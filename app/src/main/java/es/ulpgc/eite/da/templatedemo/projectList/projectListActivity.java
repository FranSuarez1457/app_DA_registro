package es.ulpgc.eite.da.templatedemo.projectList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class projectListActivity extends AppCompatActivity implements projectListContract.View {

    private projectListContract.Presenter presenter;

    private TextView tvCompanyBarList;
    private TextView tvListTitle;
    private ConstraintLayout cardProject1;
    private TextView tvProjectNameItem1;
    private Button btnNavRegisterProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.project_list);

        tvCompanyBarList = findViewById(R.id.tvCompanyBarList);
        tvListTitle = findViewById(R.id.tvListTitle);
        cardProject1 = findViewById(R.id.cardProject1);
        tvProjectNameItem1 = findViewById(R.id.tvProjectNameItem1);
        btnNavRegisterProject = findViewById(R.id.btnNavRegisterProject);

        projectListScreen.configure(this);

        cardProject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectName = tvProjectNameItem1.getText().toString();
                presenter.onProjectClicked(projectName);
            }
        });

        if (btnNavRegisterProject != null) {
            btnNavRegisterProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppMediator.getInstance().goToRegisterProject(projectListActivity.this);
                }
            });
        }
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
    public void displayData(projectListState state) {
        if (state.projectList != null && !state.projectList.isEmpty()) {
            tvProjectNameItem1.setText(state.projectList.get(0));
        }
    }

    @Override
    public void navigateToProjectDetail() {
        AppMediator.getInstance().goToProjectDetail(this);
    }
}
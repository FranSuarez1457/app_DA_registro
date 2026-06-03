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

    private TextView tvCompanyBarRegProject;
    private EditText etProjectName;
    private EditText etProjectDescription;
    private EditText etProjectDate;
    private Button btnSubmitProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_project);

        tvCompanyBarRegProject = findViewById(R.id.tvCompanyBarRegProject);
        etProjectName = findViewById(R.id.etProjectName);
        etProjectDescription = findViewById(R.id.etProjectDescription);
        etProjectDate = findViewById(R.id.etProjectDate);
        btnSubmitProject = findViewById(R.id.btnSubmitProject);

        registerProjectScreen.configure(this);

        btnSubmitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etProjectName.getText().toString();
                String description = etProjectDescription.getText().toString();

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
    }

    @Override
    public void finishView() {
        AppMediator.getInstance().goToProjectList(this);
        finish();
    }
}
package es.ulpgc.eite.da.templatedemo.registerTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class registerTaskActivity extends AppCompatActivity implements registerTaskContract.View {

    private registerTaskContract.Presenter presenter;

    private TextView tvCompanyBarRegTask;
    private EditText etTaskProject;
    private EditText etTaskName;
    private EditText etTaskResponsible;
    private Button btnSubmitTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_task);

        tvCompanyBarRegTask = findViewById(R.id.tvCompanyBarRegTask);
        etTaskProject = findViewById(R.id.etTaskProject);
        etTaskName = findViewById(R.id.etTaskName);
        etTaskResponsible = findViewById(R.id.etTaskResponsible);
        btnSubmitTask = findViewById(R.id.btnSubmitTask);

        registerTaskScreen.configure(this);

        btnSubmitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String project = etTaskProject.getText().toString();
                String taskName = etTaskName.getText().toString();
                String responsible = etTaskResponsible.getText().toString();

                presenter.onRegisterBtnClicked(project, taskName, responsible);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(registerTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(registerTaskViewModel viewModel) {
    }

    @Override
    public void navigateToStatus() {
        AppMediator.getInstance().goToStatus(this);
        finish();
    }
}
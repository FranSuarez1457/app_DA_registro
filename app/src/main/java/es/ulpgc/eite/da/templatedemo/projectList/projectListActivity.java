package es.ulpgc.eite.da.templatedemo.projectList;

import android.os.Bundle;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class projectListActivity extends AppCompatActivity implements projectListContract.View {

    private projectListContract.Presenter presenter;

    private TextView tvCompanyBarList;
    private TextView tvListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_list);

        tvCompanyBarList = findViewById(R.id.tvCompanyBarList);
        tvListTitle = findViewById(R.id.tvListTitle);

        projectListScreen.configure(this);
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
        android.widget.LinearLayout container = findViewById(R.id.llProjectContainer);
        if (container != null) {
            container.removeAllViews();
        }

        if (state.projectList != null && !state.projectList.isEmpty()) {
            android.view.LayoutInflater inflater = getLayoutInflater();

            for (String projectName : state.projectList) {

                android.view.View itemView = inflater.inflate(R.layout.item_project, container, false);

                android.widget.TextView tvName = itemView.findViewById(R.id.tvProjectNameItem1);
                android.view.View cardProject = itemView.findViewById(R.id.cardProject1);

                tvName.setText(projectName);

                cardProject.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        presenter.onProjectClicked(projectName);
                    }
                });

                if (container != null) {
                    container.addView(itemView);
                }
            }
        }
    }

    @Override
    public void navigateToProjectDetail() {
        AppMediator.getInstance().goToProjectDetail(this);
    }
}
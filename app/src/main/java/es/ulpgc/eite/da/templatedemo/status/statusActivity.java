package es.ulpgc.eite.da.templatedemo.status;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class statusActivity extends AppCompatActivity implements statusContract.View {

    private statusContract.Presenter presenter;

    private TextView tvCompanyNameStatus;
    private TextView tvStatusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.status);

        tvCompanyNameStatus = findViewById(R.id.tvCompanyNameStatus);
        tvStatusMessage = findViewById(R.id.tvStatusMessage);

        statusScreen.configure(this);

        tvStatusMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAcceptButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(statusContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(statusViewModel viewModel) {
        tvStatusMessage.setText(viewModel.message);

        if (viewModel.isSuccess) {
            tvStatusMessage.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            tvStatusMessage.setTextColor(Color.parseColor("#F44336"));
        }
    }

    @Override
    public void navigateToHome() {
        AppMediator.getInstance().goToHome(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onAcceptButtonClicked();
    }
}
package es.ulpgc.eite.da.templatedemo.projectDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;

public class projectDetailActivity extends AppCompatActivity implements projectDetailContract.View {

    private projectDetailContract.Presenter presenter;

    private TextView tvDetailTitle;
    private ImageButton btnFavorite;
    private TextView tvProjectDate;
    private TextView tvProjectDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        btnFavorite = findViewById(R.id.btnFavorite);
        tvProjectDate = findViewById(R.id.tvProjectDate);
        tvProjectDescription = findViewById(R.id.tvProjectDescription);

        projectDetailScreen.configure(this);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFavoriteButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(projectDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(projectDetailViewModel viewModel) {
        tvDetailTitle.setText(viewModel.projectName);
        tvProjectDate.setText(viewModel.projectDate);
        tvProjectDescription.setText(viewModel.projectDescription);

        updateFavoriteIcon(viewModel.isFavorite);
    }

    public void updateFavoriteIcon(boolean isFavorite) {
        if (isFavorite) {
            btnFavorite.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btnFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

    @Override
    public void showFavoriteAddedMessage() {
        Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
        updateFavoriteIcon(true);
    }
}
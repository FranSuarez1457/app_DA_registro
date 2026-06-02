package es.ulpgc.eite.da.templatedemo.projectDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.templatedemo.R;

public class projectDetailActivity extends AppCompatActivity implements projectDetailContract.View {

    private projectDetailContract.Presenter presenter;

    // Tus vistas del XML
    private TextView tvDetailTitle;
    private ImageButton btnFavorite;
    private TextView tvProjectDate;
    private TextView tvProjectDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail); // Apunta a tu XML

        // 1. Enlazamos los IDs
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        btnFavorite = findViewById(R.id.btnFavorite);
        tvProjectDate = findViewById(R.id.tvProjectDate);
        tvProjectDescription = findViewById(R.id.tvProjectDescription);

        // 2. Ensamblador
        projectDetailScreen.configure(this);

        // 3. Programar el clic en la estrella
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFavoriteBtnClicked();
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
        // Rellenamos los textos con la información del Presenter
        tvDetailTitle.setText(viewModel.projectName);
        tvProjectDate.setText(viewModel.projectDate);
        tvProjectDescription.setText(viewModel.projectDescription);

        // Colocamos el icono correcto de inicio
        updateFavoriteIcon(viewModel.isFavorite);
    }

    @Override
    public void updateFavoriteIcon(boolean isFavorite) {
        // Dependiendo de si es favorito o no, ponemos la estrella encendida o apagada
        if (isFavorite) {
            btnFavorite.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btnFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }
}

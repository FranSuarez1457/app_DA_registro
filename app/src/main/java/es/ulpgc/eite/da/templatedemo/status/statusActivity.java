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

    // Variables con los IDs exactos de tu status.xml
    private TextView tvCompanyNameStatus;
    private TextView tvStatusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apuntamos a tu archivo status.xml
        setContentView(R.layout.status);

        // 1. Enlazamos las vistas
        tvCompanyNameStatus = findViewById(R.id.tvCompanyNameStatus);
        tvStatusMessage = findViewById(R.id.tvStatusMessage);

        // 2. Configuramos el ensamblador
        statusScreen.configure(this);

        // 3. Como no hay botón, hacemos que tocar el texto sirva para salir
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
        // Pintamos el mensaje que nos ha mandado el Mediador
        tvStatusMessage.setText(viewModel.message);

        // EXTRA: Cambiamos el color dependiendo de si fue bien o mal
        if (viewModel.isSuccess) {
            tvStatusMessage.setTextColor(Color.parseColor("#4CAF50")); // Verde para éxito
        } else {
            tvStatusMessage.setTextColor(Color.parseColor("#F44336")); // Rojo para error
        }
    }

    @Override
    public void navigateToHome() {
        AppMediator.getInstance().goToHome(this);
        finish(); // Cerramos esta pantalla
    }

    // EXTRA: Si el usuario le da al botón "Atrás" del móvil, también hace el circuito correcto
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onAcceptButtonClicked();
    }
}

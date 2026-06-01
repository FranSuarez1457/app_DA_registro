package es.ulpgc.eite.da.templatedemo.status;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class statusPresenter implements statusContract.Presenter {

    private WeakReference<statusContract.View> view;
    private statusState state;
    private statusContract.Model model;

    public statusPresenter(statusState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<statusContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(statusContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        // Le pasamos a la pantalla el mensaje y si fue un éxito o un error
        view.get().displayData(state);
    }

    @Override
    public void onAcceptButtonClicked() {
        // Cuando el usuario lea el mensaje y pulse el botón, lo mandamos de vuelta al Home
        view.get().navigateToHome();
    }
}

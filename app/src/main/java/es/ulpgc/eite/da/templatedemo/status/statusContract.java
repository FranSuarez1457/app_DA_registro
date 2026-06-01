package es.ulpgc.eite.da.templatedemo.status;

import java.lang.ref.WeakReference;

public interface statusContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(statusViewModel viewModel);
        void navigateToHome(); // Para volver al menú cuando el usuario pulse "Aceptar"
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onAcceptButtonClicked();
    }

    interface Model {
        // En este caso, el modelo casi no tiene trabajo porque los datos vienen del Mediador
    }
}
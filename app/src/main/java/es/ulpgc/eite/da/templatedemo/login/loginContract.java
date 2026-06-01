package es.ulpgc.eite.da.templatedemo.login;

import java.lang.ref.WeakReference; // ¡Esta es la línea clave que faltaba!

public interface loginContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(loginViewModel viewModel);
        void navigateToHome();
    }

    interface Presenter {
        // AQUÍ ESTABA EL ERROR: Ahora sí coinciden todos usando WeakReference
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onLoginButtonClicked(String email, String password);
    }

    interface Model {
        boolean validateCredentials(String email, String password);
    }
}

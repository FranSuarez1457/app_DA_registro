package es.ulpgc.eite.da.templatedemo.login;

import java.lang.ref.WeakReference;

public interface loginContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(loginViewModel viewModel);
        // Métodos de navegación que ya deberías tener en tu Activity
        void navigateToHome();
        void navigateToRegister();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();

        void onLoginButtonClicked(String email, String password);
        void onRegisterButtonClicked();
        void onGuestButtonClicked();
    }

    interface Model {

        boolean loginUser(String email, String password);
        void loginGuest();
    }
}

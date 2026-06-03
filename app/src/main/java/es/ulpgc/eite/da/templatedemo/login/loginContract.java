package es.ulpgc.eite.da.templatedemo.login;

import java.lang.ref.WeakReference;

public interface loginContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(loginViewModel viewModel);
        void navigateToHome();
        void navigateToRegister();
        void showErrorMessage(String message);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();

        void onLoginButtonClicked(String email, String password);
        void onRegisterButtonClicked();
        void onGuestButtonClicked();
    }

    // En loginContract.java
    interface Model {
        boolean loginUser(String email, String password);
        void loginGuest();
        boolean userExists(String email);
    }
}
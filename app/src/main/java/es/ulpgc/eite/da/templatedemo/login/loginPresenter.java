package es.ulpgc.eite.da.templatedemo.login;

import java.lang.ref.WeakReference;

public class loginPresenter implements loginContract.Presenter {

    private WeakReference<loginContract.View> view;
    private loginState state;
    private loginContract.Model model;

    public loginPresenter(loginState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<loginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(loginContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        view.get().displayData(state);
    }

    @Override
    public void onLoginButtonClicked(String email, String password) {
        // Primero, validación básica de campos
        if (email.isEmpty() || password.isEmpty()) {
            view.get().showErrorMessage("Error: Los campos no pueden estar vacíos");
            return;
        }

        boolean isSuccess = model.loginUser(email, password);

        if (isSuccess) {
            es.ulpgc.eite.da.templatedemo.database.UserEntity user = model.getUserByEmail(email);
            es.ulpgc.eite.da.templatedemo.app.AppMediator.getInstance().setLoggedUser(user);

            view.get().navigateToHome();
        } else {
            view.get().showErrorMessage("Error: Credenciales incorrectas");
        }
    }

    @Override
    public void onRegisterButtonClicked() {
        view.get().navigateToRegister();
    }

    @Override
    public void onGuestButtonClicked() {
        model.loginGuest();
        view.get().navigateToHome();
    }
}
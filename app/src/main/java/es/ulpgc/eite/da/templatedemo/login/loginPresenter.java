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
        // Llamamos a la base de datos a través del modelo
        boolean isSuccess = model.loginUser(email, password);

        if (isSuccess) {
            // Si las credenciales son correctas, entramos a la app
            view.get().navigateToHome();
        } else {
            // Si fallan, de momento no hace nada (se queda en la pantalla).
            // En el futuro aquí le diríamos a la View que muestre un Toast de error.
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

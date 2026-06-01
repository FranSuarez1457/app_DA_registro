package es.ulpgc.eite.da.templatedemo.login;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class loginPresenter implements loginContract.Presenter {

    private WeakReference<loginContract.View> view;
    private loginState state;
    private loginContract.Model model;
    private AppMediator mediator;

    public loginPresenter(loginState state) {
        this.state = state;
        this.mediator = AppMediator.getInstance();
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
        // Cuando la pantalla se muestra, le mandamos el estado a la vista para que pinte
        view.get().displayData(state);
    }

    @Override
    public void onLoginButtonClicked(String email, String password) {
        // 1. Preguntamos al modelo si los datos coinciden
        boolean isValid = model.validateCredentials(email, password);

        if (isValid) {
            // 2a. Si es correcto, borramos los errores y navegamos a Home
            state.errorMessage = "";
            view.get().navigateToHome();
        } else {
            // 2b. Si es incorrecto, guardamos un error y actualizamos la vista
            state.errorMessage = "Email o contraseña incorrectos";
            view.get().displayData(state);
        }
    }
}

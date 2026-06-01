package es.ulpgc.eite.da.templatedemo.login;

public interface loginContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(loginViewModel viewModel);
        void navigateToHome(); // Orden de saltar de pantalla
    }

    interface Presenter {
        void injectView(View view);
        void injectModel(Model model);
        void onResume();
        void onLoginButtonClicked(String email, String password); // Acción del usuario
    }

    interface Model {
        // Devuelve true si el usuario es correcto, false si no lo es
        boolean validateCredentials(String email, String password);
    }
}

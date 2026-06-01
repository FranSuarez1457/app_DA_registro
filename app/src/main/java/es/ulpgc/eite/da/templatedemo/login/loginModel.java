package es.ulpgc.eite.da.templatedemo.login;

public class loginModel implements loginContract.Model {

    @Override
    public boolean validateCredentials(String email, String password) {
        // SIMULACRO: Solo deja entrar al administrador por ahora
        if (email.equals("admin@empresa.com") && password.equals("123")) {
            return true; // Éxito
        } else {
            return false; // Fallo
        }
    }
}

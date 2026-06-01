package es.ulpgc.eite.da.templatedemo.registerUser;

public class registerUserModel implements registerUserContract.Model {

    @Override
    public boolean registerNewUser(String email, String password, String name) {
        // SIMULACRO: De momento siempre devuelve true simulando que se guardó en Room
        return true;
    }
}

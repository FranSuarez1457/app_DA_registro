package es.ulpgc.eite.da.templatedemo.home;

public class homeModel implements homeContract.Model {

    @Override
    public String getUserName() {
        // SIMULACRO: Devolvemos un nombre temporal para ver que funciona
        return "Administrador";
    }
}

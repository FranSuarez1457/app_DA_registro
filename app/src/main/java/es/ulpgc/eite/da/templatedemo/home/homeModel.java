package es.ulpgc.eite.da.templatedemo.home;

import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class homeModel implements homeContract.Model {

    @Override
    public boolean isGuestUser() {
        // Si es null, devuelve 'true' (es un invitado)
        return AppMediator.getInstance().getLoggedUser() == null;
    }
}

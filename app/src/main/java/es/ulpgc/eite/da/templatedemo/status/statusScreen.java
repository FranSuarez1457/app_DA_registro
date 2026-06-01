package es.ulpgc.eite.da.templatedemo.status;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class statusScreen {

    public static void configure(statusContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();

        // ¡Magia! Cogemos el estado que nos haya dejado la pantalla anterior
        statusState state = mediator.getStatusState();

        // Por si acaso entramos por error y no hay estado, creamos uno vacío
        if (state == null) {
            state = new statusState();
            state.message = "Estado desconocido";
        }

        statusContract.Presenter presenter = new statusPresenter(state);
        statusContract.Model model = new statusModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
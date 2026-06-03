package es.ulpgc.eite.da.templatedemo.status;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class statusScreen {

    public static void configure(statusContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();

        statusState state = mediator.getStatusState();

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
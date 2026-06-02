package es.ulpgc.eite.da.templatedemo.registerProject;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class registerProjectScreen {

    public static void configure(registerProjectContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        registerProjectState state = mediator.getRegisterProjectState();

        if (state == null) {
            state = new registerProjectState();
        }

        registerProjectContract.Presenter presenter = new registerProjectPresenter(state);

        // ¡Pasamos el contexto al modelo!
        registerProjectContract.Model model = new registerProjectModel(context.get());

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}

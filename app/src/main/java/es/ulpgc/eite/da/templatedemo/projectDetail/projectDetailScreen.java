package es.ulpgc.eite.da.templatedemo.projectDetail;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class projectDetailScreen {

    public static void configure(projectDetailContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        projectDetailState state = mediator.getProjectDetailState();

        if (state == null) {
            state = new projectDetailState();
        }

        projectDetailContract.Presenter presenter = new projectDetailPresenter(state);

        // ¡CAMBIO AQUÍ! Pasamos el contexto al modelo
        projectDetailContract.Model model = new projectDetailModel(context.get());

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
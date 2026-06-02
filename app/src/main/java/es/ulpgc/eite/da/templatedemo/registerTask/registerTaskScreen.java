package es.ulpgc.eite.da.templatedemo.registerTask;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class registerTaskScreen {

    public static void configure(registerTaskContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        registerTaskState state = mediator.getRegisterTaskState();

        if (state == null) {
            state = new registerTaskState();
        }

        registerTaskContract.Presenter presenter = new registerTaskPresenter(state);
        registerTaskContract.Model model = new registerTaskModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
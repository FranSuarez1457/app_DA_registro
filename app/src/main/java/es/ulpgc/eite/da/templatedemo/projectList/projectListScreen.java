package es.ulpgc.eite.da.templatedemo.projectList;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class projectListScreen {

    public static void configure(projectListContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        projectListState state = mediator.getProjectListState();

        if (state == null) {
            state = new projectListState();
        }

        projectListContract.Presenter presenter = new projectListPresenter(state);

        projectListContract.Model model = new projectListModel(context.get());

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}

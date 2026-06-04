package es.ulpgc.eite.da.templatedemo.projectDetail;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class projectDetailScreen {

    public static void configure(projectDetailContract.View view) {
        android.content.Context context = ((android.app.Activity) view).getApplicationContext();

        projectDetailContract.Model model = new projectDetailModel(context);

        projectDetailState state = new projectDetailState();

        projectDetailContract.Presenter presenter = new projectDetailPresenter(model);

        presenter.injectModel(model);
        presenter.injectView(new java.lang.ref.WeakReference<>(view));
        view.injectPresenter(presenter);
    }
}
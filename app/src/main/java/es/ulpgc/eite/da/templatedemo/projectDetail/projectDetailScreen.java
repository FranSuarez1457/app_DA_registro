package es.ulpgc.eite.da.templatedemo.projectDetail;




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
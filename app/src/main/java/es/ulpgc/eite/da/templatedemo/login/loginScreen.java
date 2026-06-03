package es.ulpgc.eite.da.templatedemo.login;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class loginScreen {

    public static void configure(loginContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        loginState state = mediator.getLoginState();

        if (state == null) {
            state = new loginState();
        }

        loginContract.Presenter presenter = new loginPresenter(state);

        loginContract.Model model = new loginModel(context.get());

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
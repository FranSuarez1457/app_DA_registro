package es.ulpgc.eite.da.templatedemo.registerUser;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;

public class registerUserScreen {

    public static void configure(registerUserContract.View view) {
        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        // Instanciamos un estado local para esta pantalla
        registerUserState state = new registerUserState();

        registerUserContract.Presenter presenter = new registerUserPresenter(state);
        registerUserContract.Model model = new registerUserModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
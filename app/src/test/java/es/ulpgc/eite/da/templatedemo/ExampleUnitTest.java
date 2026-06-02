package es.ulpgc.eite.da.templatedemo;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

// Importamos todas tus Activities y el Mediador
import es.ulpgc.eite.da.templatedemo.login.loginActivity;
import es.ulpgc.eite.da.templatedemo.home.homeActivity;
import es.ulpgc.eite.da.templatedemo.registerUser.registerUserActivity;
import es.ulpgc.eite.da.templatedemo.registerProject.registerProjectActivity;
import es.ulpgc.eite.da.templatedemo.registerTask.registerTaskActivity;
import es.ulpgc.eite.da.templatedemo.projectList.projectListActivity;
import es.ulpgc.eite.da.templatedemo.projectDetail.projectDetailActivity;
import es.ulpgc.eite.da.templatedemo.status.statusActivity;
import es.ulpgc.eite.da.templatedemo.status.statusState;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 32)
public class ExampleUnitTest {

    // ==========================================
    // 1. TESTS DE LOGIN
    // ==========================================

    @Test
    public void login_uiElementsExist() {
        loginActivity activity = Robolectric.buildActivity(loginActivity.class).create().resume().get();
        assertNotNull(activity.findViewById(R.id.etLoginEmail));
        assertNotNull(activity.findViewById(R.id.etLoginPassword));
        assertNotNull(activity.findViewById(R.id.btnLoginContinue));
    }

    @Test
    public void login_guestButton_navigatesToHome() {
        loginActivity activity = Robolectric.buildActivity(loginActivity.class).create().resume().get();
        activity.findViewById(R.id.btnLoginGuest).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals(homeActivity.class.getName(), actualIntent.getComponent().getClassName());

        // CORRECCIÓN: Le preguntamos a la 'activity' real, no a la sombra
        assertTrue("La activity de login debería cerrarse al entrar como invitado", activity.isFinishing());
    }

    @Test
    public void login_registerButton_navigatesToRegisterUser() {
        loginActivity activity = Robolectric.buildActivity(loginActivity.class).create().resume().get();
        activity.findViewById(R.id.btnLoginRegister).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals(registerUserActivity.class.getName(), actualIntent.getComponent().getClassName());
    }

    @Test
    public void login_continueButton_navigatesToHome() {
        loginActivity activity = Robolectric.buildActivity(loginActivity.class).create().resume().get();

        ((EditText) activity.findViewById(R.id.etLoginEmail)).setText("admin@empresa.com");
        ((EditText) activity.findViewById(R.id.etLoginPassword)).setText("123");
        activity.findViewById(R.id.btnLoginContinue).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals(homeActivity.class.getName(), actualIntent.getComponent().getClassName());
    }

    // ==========================================
    // 2. TESTS DE REGISTRO DE USUARIO
    // ==========================================

    @Test
    public void registerUser_continueButton_finishesActivity() {
        registerUserActivity activity = Robolectric.buildActivity(registerUserActivity.class).create().resume().get();

        activity.findViewById(R.id.btnRegisterContinue).performClick();

        // CORRECCIÓN
        assertTrue("La pantalla de registro debe cerrarse al terminar", activity.isFinishing());
    }

    // ==========================================
    // 3. TESTS DE HOME (MENÚ PRINCIPAL)
    // ==========================================

    @Test
    public void home_allNavigationButtons_workCorrectly() {
        homeActivity activity = Robolectric.buildActivity(homeActivity.class).create().resume().get();
        ShadowActivity shadowActivity = shadowOf(activity);

        activity.findViewById(R.id.btnNavRegisterProject).performClick();
        assertEquals(registerProjectActivity.class.getName(), shadowActivity.getNextStartedActivity().getComponent().getClassName());

        activity.findViewById(R.id.btnNavRegisterTask).performClick();
        assertEquals(registerTaskActivity.class.getName(), shadowActivity.getNextStartedActivity().getComponent().getClassName());

        activity.findViewById(R.id.btnNavProjectList).performClick();
        assertEquals(projectListActivity.class.getName(), shadowActivity.getNextStartedActivity().getComponent().getClassName());
    }

    @Test
    public void home_favoritesButton_showsToast() {
        homeActivity activity = Robolectric.buildActivity(homeActivity.class).create().resume().get();
        activity.findViewById(R.id.btnNavFavorites).performClick();
        assertEquals("Pantalla de Favoritos en construcción", ShadowToast.getTextOfLatestToast());
    }

    // ==========================================
    // 4. TESTS DE REGISTRO DE PROYECTOS
    // ==========================================

    @Test
    public void registerProject_emptyName_leavesErrorInMediatorAndNavigatesToStatus() {
        registerProjectActivity activity = Robolectric.buildActivity(registerProjectActivity.class).create().resume().get();

        ((EditText) activity.findViewById(R.id.etProjectName)).setText("");
        activity.findViewById(R.id.btnSubmitProject).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        assertEquals(statusActivity.class.getName(), shadowActivity.getNextStartedActivity().getComponent().getClassName());

        // CORRECCIÓN
        assertTrue("La activity debe cerrarse tras registrar", activity.isFinishing());

        statusState state = AppMediator.getInstance().getStatusState();
        assertFalse("El estado debe ser ERROR porque no hay nombre", state.isSuccess);
    }

    @Test
    public void registerProject_validData_leavesSuccessInMediator() {
        registerProjectActivity activity = Robolectric.buildActivity(registerProjectActivity.class).create().resume().get();

        ((EditText) activity.findViewById(R.id.etProjectName)).setText("Proyecto de Prueba");
        activity.findViewById(R.id.btnSubmitProject).performClick();

        statusState state = AppMediator.getInstance().getStatusState();
        assertTrue("El estado debe ser ÉXITO", state.isSuccess);
        assertTrue("El mensaje debe contener el nombre del proyecto", state.message.contains("Proyecto de Prueba"));
    }

    // ==========================================
    // 5. TESTS DE REGISTRO DE TAREAS
    // ==========================================

    @Test
    public void registerTask_emptyName_leavesErrorInMediator() {
        registerTaskActivity activity = Robolectric.buildActivity(registerTaskActivity.class).create().resume().get();

        ((EditText) activity.findViewById(R.id.etTaskName)).setText("");
        activity.findViewById(R.id.btnSubmitTask).performClick();

        statusState state = AppMediator.getInstance().getStatusState();
        assertFalse("El estado debe ser ERROR porque la tarea no tiene nombre", state.isSuccess);
    }

    @Test
    public void registerTask_validData_leavesSuccessInMediator() {
        registerTaskActivity activity = Robolectric.buildActivity(registerTaskActivity.class).create().resume().get();

        ((EditText) activity.findViewById(R.id.etTaskName)).setText("Diseñar Base de Datos");
        ((EditText) activity.findViewById(R.id.etTaskResponsible)).setText("Carlos");
        activity.findViewById(R.id.btnSubmitTask).performClick();

        statusState state = AppMediator.getInstance().getStatusState();
        assertTrue("El estado debe ser ÉXITO", state.isSuccess);
        assertTrue(state.message.contains("Diseñar Base de Datos"));
    }

    // ==========================================
    // 6. TESTS DE LISTADO DE PROYECTOS
    // ==========================================

    @Test
    public void projectList_clickOnProjectCard_navigatesToProjectDetail() {
        projectListActivity activity = Robolectric.buildActivity(projectListActivity.class).create().resume().get();
        activity.findViewById(R.id.cardProject1).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals("Al tocar un proyecto debe abrirse el detalle",
                projectDetailActivity.class.getName(), actualIntent.getComponent().getClassName());
    }

    // ==========================================
    // 7. TESTS DE DETALLE DE PROYECTO
    // ==========================================

    @Test
    public void projectDetail_favoriteButton_canBeClicked() {
        projectDetailActivity activity = Robolectric.buildActivity(projectDetailActivity.class).create().resume().get();
        ImageButton btnFavorite = activity.findViewById(R.id.btnFavorite);

        assertNotNull(btnFavorite);
        btnFavorite.performClick();
        btnFavorite.performClick();
    }

    // ==========================================
    // 8. TESTS DE ESTADO (STATUS)
    // ==========================================

    @Test
    public void status_clickingMessage_navigatesToHome() {
        statusActivity activity = Robolectric.buildActivity(statusActivity.class).create().resume().get();
        activity.findViewById(R.id.tvStatusMessage).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertEquals("Al tocar el mensaje debe volver al Home",
                homeActivity.class.getName(), actualIntent.getComponent().getClassName());

        // CORRECCIÓN
        assertTrue(activity.isFinishing());
    }

    @Test
    public void status_onBackPressed_navigatesToHome() {
        statusActivity activity = Robolectric.buildActivity(statusActivity.class).create().resume().get();
        activity.onBackPressed();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertEquals("Al pulsar atrás debe redirigir al Home",
                homeActivity.class.getName(), actualIntent.getComponent().getClassName());

        // CORRECCIÓN
        assertTrue(activity.isFinishing());
    }
}
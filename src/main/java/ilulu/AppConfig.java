package ilulu;

import com.jfinal.config.*;
import ilulu.creater.CreaterController;

/**
 * Created by ilulu on 14-8-10.
 */
public class AppConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/createApp", CreaterController.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}

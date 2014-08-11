package ilulu.creater;

import com.jfinal.core.Controller;

/**
 * Created by ilulu on 14-8-11.
 */
public class CreaterController extends Controller {
    public void index(){
        String p=getPara(0);
        renderJson("hehe");
    }
}

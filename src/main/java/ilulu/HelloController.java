package ilulu;

import com.jfinal.core.Controller;

/**
 * Created by ilulu on 14-8-10.
 */
public class HelloController extends Controller {
    public void index(){
        renderText(getPara(0));
    }
}

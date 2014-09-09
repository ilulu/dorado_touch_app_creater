package ilulu.creater;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import ilulu.generate.GenerateProject;

import java.io.File;

/**
 * Created by ilulu on 14-8-11.
 */
public class CreaterController extends Controller {
    public void index(){
        String path_temp= PathKit.getWebRootPath()+ File.separator+"upload";
        UploadFile file=getFile("app_icon",path_temp);
        String appName=getPara("app_name");
        String appPackage=getPara("app_package");
        String appUrl=getPara("app_url");


        GenerateProject generateProject=new GenerateProject();
        generateProject.generateAndroidProject(appPackage,appName,appUrl,file.getFile());
        renderFile("/upload/"+appName+".apk");
    }
}

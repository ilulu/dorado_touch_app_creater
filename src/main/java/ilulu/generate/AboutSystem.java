package ilulu.generate;

import org.apache.commons.lang.SystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ilulu on 14-8-21.
 * 检查环境。当前需要服务器安装了npm或者是安装了cordova的common-line-tools
 */
public class AboutSystem {

    /**
     * 检查是否安装了
     * @return
     */
    public static boolean isInstallCordovaCLI(){
        boolean flag=false;
        if(SystemUtils.IS_OS_LINUX){

        }else if(SystemUtils.IS_OS_WINDOWS){

        }else if(SystemUtils.IS_OS_MAC){
            String cmd="cordova -v";
            try {
                Process ps=Runtime.getRuntime().exec(cmd);
                ps.waitFor();
                BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String result = sb.toString();
                if(result.indexOf("not found")<0){
                    flag=true;
                }
                System.out.println(result);
                return flag;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * 执行命令
     * @param cmd 编译或者创建的命令
     * @param showMsg 是否显示直接结果
     */
    public static void execCmd(String cmd,boolean showMsg){
        if(SystemUtils.IS_OS_MAC){
            try {
                Process ps=Runtime.getRuntime().exec(cmd);
                ps.waitFor();
                if(showMsg) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    String result = sb.toString();
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

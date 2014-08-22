package ilulu.generate;

import com.sun.javafx.runtime.SystemProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import java.io.*;

/**
 * Created by ilulu on 14-8-21.
 * 生成项目
 */
public class GenerateProject {

    public void generateAndroidProject(String packageName, String appName, String url) {
        if (SystemUtils.IS_OS_MAC) {
            AboutSystem.execCmd("pwd", true);
            StringBuilder createShell = new StringBuilder("/Users/ilulu/dev/createApp " + appName.toLowerCase() + " " + packageName + " " + appName);
            AboutSystem.execCmd(createShell.toString(), true);
            String projectPath = "/Users/ilulu/dev/" + appName.toLowerCase();
            try {
                changeIndex(projectPath, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String buildShell = "/Users/ilulu/dev/buildApp " + appName.toLowerCase();
            AboutSystem.execCmd(buildShell, true);
        }
    }

    /**
     * 修改主页地址
     */
    private void changeIndex(String projectPath, String url) throws IOException {
        String resPath = projectPath + "/platforms/android/res";
        String configPath = resPath + "/xml/config.xml";
        try {
            File file = new File(configPath);
            BufferedInputStream bis =
                    new BufferedInputStream(new FileInputStream(file));

            byte[] buff = new byte[(int) file.length()];

            bis.read(buff);

            FileOutputStream fos = new FileOutputStream(file);

            String[] lines = (new String(buff)).split("\n");

            for (String line : lines) {
                if(StringUtils.contains(line,"index.html")){
                    line=line.replace("index.html",url);
                }
                fos.write((line + "\n").getBytes());
            }

            fos.flush();

            fos.close();

            bis.close();

        } catch (FileNotFoundException ex) {

            ex.printStackTrace();

        } catch (IOException ioe) {

            ioe.printStackTrace();

        }

    }
}

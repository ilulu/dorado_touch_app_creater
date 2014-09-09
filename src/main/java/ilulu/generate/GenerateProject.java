package ilulu.generate;

import com.jfinal.kit.PathKit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;


/**
 * Created by ilulu on 14-8-21.
 * 生成项目
 */
public class GenerateProject {

    public void generateAndroidProject(String packageName, String appName, String url, File file) {

        String shellRootPath = PathKit.getWebRootPath() + "/shell";
        String devPath = ResourceBundle.getBundle("config").getString("dev_path");
        if (SystemUtils.IS_OS_MAC) {
            AboutSystem.execCmd("pwd", true);
            String projectPath = devPath + File.separator + appName.toLowerCase();
            StringBuilder createShell = new StringBuilder(shellRootPath + "/createApp " + devPath + " " + projectPath + " " + packageName + " " + appName);
            AboutSystem.execCmd(createShell.toString(), true);
            try {
                changeIndex(projectPath, url);
                changeLuncherIcon(projectPath, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String buildShell = shellRootPath + "/buildApp " + devPath + File.separator + appName.toLowerCase();
            AboutSystem.execCmd(buildShell, true);

            String moveShell = shellRootPath + "/moveApp "+devPath + File.separator + appName.toLowerCase() + "/platforms/android/ant-build/" + appName + "-debug.apk " + PathKit.getWebRootPath() + File.separator + "upload/" + appName + ".apk";
            AboutSystem.execCmd(moveShell, true);

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
                if (StringUtils.contains(line, "index.html")) {
                    if (!StringUtils.contains(url, "http")) {
                        url = "http://" + url;
                    }
                    line = line.replace("index.html", url);
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

    /**
     * 修改ICON
     *
     * @param projectPath
     * @param image
     */
    private void changeLuncherIcon(String projectPath, File image) {
        String iconPath=projectPath+ File.separator+"platforms/android/res/drawable";
        try {
            FileInputStream inputStream=new FileInputStream(image);
            FileOutputStream outputStream=new FileOutputStream(new File(iconPath+File.separator+"icon.png"));
            FileOutputStream outputStream5=new FileOutputStream(new File(iconPath+"-xhdpi"+File.separator+"icon.png"));
            FileChannel infileChannel=inputStream.getChannel();
            FileChannel outFileChannel=outputStream.getChannel();
            FileChannel outFileChannel5=outputStream5.getChannel();
            long size=infileChannel.size();
            infileChannel.transferTo(0,size,outFileChannel);
            infileChannel.transferTo(0,size,outFileChannel5);
            infileChannel.close();
            outFileChannel.close();
            outFileChannel5.close();
            outputStream5.close();
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

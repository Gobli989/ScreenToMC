package a;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends TimerTask {

    private final path = "C:/Server/plugins/ScreenToMc/screenshots.png";
    
    static Timer timer = new Timer();
    final Robot robot = new Robot();

    public Main() throws AWTException {
    }

    public static void main(String[] args) throws AWTException {
        timer.schedule(new Main(), 0, 100);
    }

    @Override
    public void run() {
        BufferedImage before = resize(robot.createScreenCapture(new Rectangle(0, 0, 1920, 1080)), 192, 108); // 1080p screen

        File outputfile = new File(path);
        try {
            ImageIO.write(before, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}

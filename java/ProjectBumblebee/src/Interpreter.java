
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author thomasfurst99
 */
public class Interpreter {

    private static final String HOME = "HOME";
    private static final String COLOR = "COLOR";
    private static final String TURNLEFT = "TURNLEFT";
    private static final String TURNRIGHT = "TURNRIGHT";
    private static final String FORWARD = "FORWARD";

    private Head head;

    public Interpreter(Graphics g) {
        head = new Head(g);
    }

    public void runScript(String fn) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fn));

            String line;
            while ((line = br.readLine()) != null) {
                process(line);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void runImage(String fn) {
        final File file = new File(fn);
        final BufferedImage image;
        head.set(0, 0);
        try {
            image = ImageIO.read(file);

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    final int clr = image.getRGB(x, y);
                    final int red = (clr & 0x00ff0000) >> 16;
                    final int green = (clr & 0x0000ff00) >> 8;
                    final int blue = clr & 0x000000ff;

                    head.color((double) red / 255.0, (double) green / 255.0, (double) blue / 255);
                    head.forward(1);
                }
                head.turnRight(90);
                head.forward(1);
                head.turnRight(90);
                head.forward(image.getWidth());
                head.turnRight(180);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void process(String line) {
        String[] split = line.split(" ");

        if (split.length == 0) {
            return;
        }

        switch (split[0]) {
            case HOME:
                head.home();
                break;
            case COLOR:
                processColor(split);
                break;
            case TURNLEFT:
                processTurn(split, false);
                break;
            case TURNRIGHT:
                processTurn(split, true);
                break;
            case FORWARD:
                processForward(split);
                break;
        }
    }

    private void processColor(String... args) {
        if (args.length < 4) {
            return;
        }

        try {
            double[] lvls = new double[3];
            for (int i = 1; i < 4; i++) {
                lvls[i - 1] = Double.parseDouble(args[i]);
            }

            head.color(lvls[0], lvls[1], lvls[2]);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void processTurn(String[] args, boolean right) {
        if (args.length < 2) {
            return;
        }

        try {
            double deg = Double.parseDouble(args[1]);
            if (right) {
                head.turnRight(deg);
            } else {
                head.turnLeft(deg);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void processForward(String... args) {
        if (args.length < 2) {
            return;
        }

        try {
            double len = Double.parseDouble(args[1]);
            head.forward(len);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

}

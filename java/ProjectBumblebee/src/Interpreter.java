
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author thomasfurst99
 * TODO: přidat lineWidth, RANDOM
 * TODO: FOR - rozpoznat, jestli od vyššího k nižšímu či naopak a podle toho přičítat či odčítat
 */
public class Interpreter {
    
    private static final String HOME = "HOME";
    private static final String COLOR = "COLOR";
    private static final String WIDTH = "WIDTH";
    private static final String TURNLEFT = "TURNLEFT";
    private static final String TURNRIGHT = "TURNRIGHT";
    private static final String FORWARD = "FORWARD";
    private static final String PENUP = "PENUP";
    private static final String PENDOWN = "PENDOWN";
    private static final String MOVETO = "MOVETO";
    private static final String REPEAT = "REPEAT";
    private static final String BREAK = "BREAK";
    private static final String RESETROTATE = "RESET";
    private static final String FOR = "FOR";
    private static final String TO = "TO";
    private static final String NEXT = "NEXT";
    private Head head;
    private String[] lines;
    private int currentLine = 1;
    private Map<Integer, String> lineMap = new HashMap<>();
    BufferedReader br;
    public Interpreter(Graphics g,String fn) throws FileNotFoundException
    {
        head = new Head(g);
        this.br = new BufferedReader(new FileReader(fn));
    }
    
    public void runScript() {
        try {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                 lineMap.put(i, line);
                 i++;
            }
            currentLine = 1;
            while (currentLine<lineMap.size()) {
                String lineContents = lineMap.get(currentLine);
                System.out.println(lineContents);
                processLine(lineContents);
                currentLine++;
                }

            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void processLine(String line) throws IOException {
        String[] split = line.split("\\s+");
        //System.out.println("Processing a line");
        if (split.length == 0) return;
        
        switch (split[0]) {
            case HOME:
                head.home();
                break;
            case COLOR:
                processColor(split);
                break;
            case WIDTH:
                processWidth(split);
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
            case PENUP:
                head.penUp();
                break;
            case PENDOWN:
                head.penDown();
                break;
            case MOVETO:
                processMoveToCoords(split);
                break;
            case RESETROTATE:
                    head.resetDir();
            break;
            case FOR:
                processForLoop(split);
            break;
        }
    }
    public void processForLoop(String[] args){
                /*String pattern = "/FOR\\s(\\w*)\\s=\\s(\\d*)\\sTO\\s(\\d*)/g";
                 *Pattern r = Pattern.compile(pattern);
                 *Matcher m = r.matcher(line);
                 *if (m.find( )) {
                 *String ctrName = m.group(0);//jméno proměnné ve Scriptu, která se nahradí tmp
                 *int ctrFrom = parseInt(m.group(1));
                 *int ctrTo = parseInt(m.group(2));*/
                //System.out.println("FOR LOOP BEGIN");
                Debug.printArr(args);
                    if(args.length>5){   
                 String ctrName = args[1];//jméno proměnné ve Scriptu, která se nahradí tmp
                 int ctrFrom = parseInt(args[3]);
                 int ctrTo = parseInt(args[5]);
                 int step = 1;
                 if(args.length>7){
                     //System.out.println("Watch out, we have some long args over here");
                     if(args[6].contains("STEPSOF")){
                          //System.out.println("Watch out, we have some long steps over here");
                         step = parseInt(args[7]);
                         System.out.println("jumping by steps of" + step);
                     }
                 }
                 int continueFrom = currentLine; //odkud to znovu začne při čtení zbytku souboru
                    System.out.println("Varname is " + ctrName +", range from "+ctrFrom+" to " + ctrTo + ", moving by steps of " + step);
                    int lineCounter = currentLine + 1;
                    String subline;
                    subline = lineMap.get(lineCounter);
                    for(int tmp = ctrFrom;tmp<ctrTo;tmp+=step){//tmp jde od nejmenší hodnoty k největší
                        //System.out.println(tmp+" FOR loop iterations:");
                        subline = lineMap.get(lineCounter);
                        String[] subsplit = subline.split("\\s+");
                        innerloop: while(!((lineCounter >=lineMap.size())||((subsplit[0].equals("NEXT") && subsplit[1].equals(ctrName))))){//projed celej blok zadanej FOR .. NEXT  nebo dokud nedojdes na konec                  processLine(subline);

                      Debug.printArr(subsplit);
                     String toExecute = subline.replace(ctrName,Integer.toString(tmp));//nahraď všechny instance specifikovaný proměnný tmpem
                      System.out.println(toExecute);
                            try {
                                processLine(toExecute);
                            } catch (IOException ex) {
                                System.out.println("Incorrect variable name in FOR loop");
                            }
                        lineCounter++;
                        subline = lineMap.get(lineCounter);
                        subsplit = subline.split("\\s+");
                    }
                     //System.out.println("FOR BLOCK ENDED");
                     
                     lineCounter = currentLine+1;}
                    currentLine = continueFrom;//skoč tam, kdes byl
                }else{
                    System.out.println("Invalid for loop syntax");
                }
    }
        public void runImage(String fn) {
        final File file = new File(fn);
        final BufferedImage image;
        head.moveTo(0, 0);
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
    
    public void processWidth(String[] args){
        double newWidth = Double.parseDouble(args[1]);
        head.setWidth(newWidth);
    }
    private void processColor(String... args) {
        if (args.length < 4) return;
        
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
    private void processMoveToCoords(String[] args){
        if(args.length<2) return;
        try {
        double x,y;
        x = Double.parseDouble(args[1]);
        y = Double.parseDouble(args[2]);
             head.moveTo(x,y);   
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    private void processTurn(String[] args, boolean right) {
        if (args.length < 2) return;
        
        try {
            double deg = Double.parseDouble(args[1]);
            if (right) head.turnRight(deg);
            else head.turnLeft(deg);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    private void processForward(String... args) {
        if (args.length < 2) return;
        
        try {
            double len = Double.parseDouble(args[1]);
            head.forward(len);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
}
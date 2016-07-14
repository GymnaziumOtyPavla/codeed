import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author thomasfurst
 */
public class Graphics extends Application {
    
    public static Graphics instance = null;
    
    private Pane pane;
    
    public Graphics() {
        instance = this;
    }
    
    public static void addNode(Node node) {
        instance._addNode(node);
    }
    
    private void _addNode(Node node) {
        pane.getChildren().add(node);
    }
   
    public static double getWidth() {
        return instance._getWidth();
    }
    
    private double _getWidth() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return bounds.getWidth();
    }
    
    public static double getHeight() {
        return instance._getHeight();
    }
    
    private double _getHeight() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return bounds.getHeight();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Turtle graphics");
        pane = new Pane();
        
        stage.setScene(new Scene(pane, 400, 400));
        stage.show();
    }
    
}

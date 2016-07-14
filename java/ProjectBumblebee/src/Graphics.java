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
    
    private Pane pane;
    
    public void addNode(Node node) {
        pane.getChildren().add(node);
    }
    
    public double getWidth() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return bounds.getWidth();
    }
    
    public double getHeight() {
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

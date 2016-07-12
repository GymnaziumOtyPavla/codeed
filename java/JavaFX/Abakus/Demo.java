
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Demo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Abakus");

        int polomer = 20;
        int aktualniX = polomer;
        int aktualniY = polomer;
        
        int pocetRadku = 8;
        int pocetSloupcu = 17;

        Pane pane = new Pane();

        for (int i = 0; i < pocetRadku; i++) {

            for (int j = 0; j < pocetSloupcu; j++) {
                final Circle circle = new Circle();
                circle.setCenterX(aktualniX);
                circle.setCenterY(aktualniY);
                circle.setRadius(polomer);
                
                circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        circle.setCenterX(circle.getCenterX() + 20);
                    }
                });
                
                if (j < 5 ) {
                    circle.setFill(Color.SADDLEBROWN);
                } else if (j > 5  && j < 9) {
                    circle.setFill(Color.BLUE);
                } else { 
                    circle.setFill(Color.YELLOW);
                }
                pane.getChildren().add(circle);

                aktualniX += 2 * polomer;
            }
            
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(aktualniY);
            line.setEndX(10000);
            line.setEndY(aktualniY);
            line.setStroke(Color.BLACK);
            pane.getChildren().add(line);
            
            aktualniX = polomer;
            aktualniY += 2 * polomer;
        }
        
        

        stage.setScene(new Scene(pane, 900, 350));
        stage.show();
    }

}


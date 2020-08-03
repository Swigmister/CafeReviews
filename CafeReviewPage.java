import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
//I worked on the homework assignment alone, using only course materials.
/**
 * A representation of a CafeReviewPage.
 * @author Parth Desai / pdesai85
 * @version 1.0
 */
public class CafeReviewPage extends Application {
    private Button post;
    private Button clear;
    private String nameInp;
    private String feedInp;
    private String colorInp;
    private BorderPane pane = new BorderPane();
    private TextField name;
    private TextField feedback;
    private TextField color;
    private VBox vbox;

    /**
     * A helper method that updates the reviews for the GUI. It puts in the name of the reviewer and their feedback
     * they inputted in the textfields and takes the color inputted in the textfield into account as well.
     */
    public void update() {
        feedInp = feedback.getText();
        Label text = new Label();
        nameInp = name.getText();
        if (nameInp.equals("")) {
            nameInp = "Anonymous";
        }
        String review = nameInp + ": " + feedInp;
        text.setWrapText(true);
        text.setText(review);
        colorInp = color.getText();
        try {
            text.setTextFill(Color.web(colorInp.toUpperCase()));
        } catch (Exception e) {
            text.setTextFill(Color.BLACK);
        }
        text.setStyle("-fx-font-family: 'Apple Chancery'; -fx-font-size: 15px;");
        vbox.getChildren().add(text);
    }

    /**
     * Main method, serves as the driver for our class.
     * @param args String array representing arguments passed by command line.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cafe1331 Reviews");
        HBox title = new HBox();
        Label myTitle = new Label();
        myTitle.setText("Cafe1331 Reviews");
        myTitle.setTextFill(Color.WHITE);
        title.setPrefSize(650, 100);
        title.getChildren().add(myTitle);
        title.setStyle("-fx-font-family: 'Snell Roundhand'; -fx-font-size: 50px; -fx-alignment: 'center';"
                + " -fx-background-color: 'black'");
        pane.setTop(title);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        //name prompt
        name = new TextField();
        name.setPromptText("Name");
        name.setPrefColumnCount(10);
        name.setFocusTraversable(false);
        //feedback prompt
        feedback = new TextField();
        feedback.setPromptText("Feedback");
        feedback.setPrefColumnCount(10);
        feedback.setFocusTraversable(false);
        //color prompt. This takes in any color ranging from name to even hexadecimal values!
        color = new TextField();
        color.setPromptText("Color");
        color.setPrefColumnCount(10);
        color.setFocusTraversable(false);
        //hBox methods
        post = new Button("Post");
        clear = new Button("Clear");
        hBox.getChildren().addAll(name, feedback, color, post, clear);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(hBox);
        PushHandlerClass handler1 = new PushHandlerClass();
        PushHandlerClass2 handler2 = new PushHandlerClass2();
        vbox = new VBox();
        post.setOnAction(handler1);
        clear.setOnAction(handler2);
        //setting the scene.
        Scene scene = new Scene(pane, 650, 700);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    class PushHandlerClass implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            feedInp = feedback.getText();
            if (!(feedInp.equals(""))) {
                update();
                pane.setCenter(vbox);
                name.clear();
                feedback.clear();
                color.clear();
            }
        }
    }
    class PushHandlerClass2 implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent ae) {
            vbox.getChildren().clear();
        }
    }
}

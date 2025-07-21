package cs112.lab08;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {

    //CONSTANTS
    public final String TITLE = "EChALE STEM Loteria";
    public final String BUTTON_LABEL = "Draw Random Card";

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    //GUI ELEMENTS
    public Button drawCardButton;
    public Label titleLabel;
    public Label messageLabel;
    public ImageView cardImageView;
    public int cardCount = 0;
    Random rand = new Random();


    @Override
    public void start(Stage primaryStage) throws IOException {
        drawCardButton = new Button(BUTTON_LABEL);
        drawCardButton.setOnAction(this);
        titleLabel = new Label("Welcome to " + TITLE + "!");
        messageLabel = new Label(LOTERIA_CARDS[cardCount].getCardName());
        cardImageView = new ImageView(LOTERIA_CARDS[cardCount].getImage());

        //ADD COMPONENTS
        VBox layout = new VBox(); //simple layout, components are stacked on top of each other in added order
        layout.getChildren().add(titleLabel);
        layout.getChildren().add(cardImageView);
        layout.getChildren().add(messageLabel);
        layout.getChildren().add(drawCardButton);

        //SIZE AND RELOCATE
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setMargin(cardImageView, new  Insets(10));
        layout.setMargin(messageLabel, new  Insets(0, 0, 10, 0));
        cardImageView.setFitWidth(300);
        cardImageView.setPreserveRatio(true);
        titleLabel.setFont(new Font(20));
        messageLabel.setFont(new Font(14));


        //SETUP SCENE AND SHOW
        Scene scene = new Scene(layout, 350, 500); //layout, dimensions of window
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE); //setting title of main window
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == drawCardButton) {
            int newCardNum = 0;
            do {
                newCardNum = rand.nextInt(LOTERIA_CARDS.length);
            } while (cardCount == newCardNum);
            cardCount = newCardNum;
            messageLabel.setText(LOTERIA_CARDS[cardCount].getCardName());
            cardImageView.setImage(LOTERIA_CARDS[cardCount].getImage());
        }
    }
}
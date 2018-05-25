package sample.controllers;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Home implements Initializable {
    private String[] parallaxs;
    private MediaPlayer player;

    @FXML
    ScrollPane scrollHome;

    @FXML
    VBox boxHome;

    @FXML
    ImageView parallax;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollHome.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
        init();
    }

    private void init(){
        parallaxs = new String[]{
                "/sample/recursos/pub9.png",
                "/sample/recursos/pub8.png",
                "/sample/recursos/pub7.png",
                "/sample/recursos/pub6.png",
                "/sample/recursos/pub5.png",
                "/sample/recursos/pub4.png",
                "/sample/recursos/pub3.png"};
        Random random = new Random();
        //System.out.println(random.nextInt(7));
        parallax.setImage(new Image(parallaxs[random.nextInt(6) + 0]));
        addCategorias();
        addMediaPlayer();
    }

    private void addCategorias(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/categoria.fxml"));
        try {
            loader.load();
        } catch (Exception e) {

        }
        Categoria categoria = loader.getController();
        categoria.setId("Algunas Apps");
        Parent parent = loader.getRoot();
        /*parent.minWidth(1000);
        parent.minHeight(1000);*/
        boxHome.getChildren().add(parent);
        Label label = new Label(" ");
        label.setPadding(new Insets(0,0,40,0));
        boxHome.getChildren().add(label);
    }

    private void addMediaPlayer(){
        Label label = new Label("Recomendaciones");
        label.getStyleClass().add("title");
        boxHome.getChildren().add(label);
        player = new MediaPlayer(new Media(getClass().getResource("../recursos/video.mp4").toExternalForm()));
        //player.play();
        MediaView mediaView = new MediaView(player);
        boxHome.getChildren().add(mediaView);
        addButtons();
    }

    private void addButtons(){
        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER);

        Button button = new Button();
        button.setGraphic(new MaterialDesignIconView(MaterialDesignIcon.PLAY));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.play();
            }
        });
        box.getChildren().add(button);

        button = new Button();
        button.setGraphic(new MaterialDesignIconView(MaterialDesignIcon.PAUSE));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.pause();
            }
        });
        box.getChildren().add(button);

        button = new Button();
        button.setGraphic(new MaterialDesignIconView(MaterialDesignIcon.STOP));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.stop();
            }
        });
        box.getChildren().add(button);

        boxHome.getChildren().add(box);

        Label label = new Label("");
        boxHome.getChildren().add(label);
    }
}


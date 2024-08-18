package com.example.SinLauncher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class JavaFX extends Application {

    private WebView webView;
    private WebEngine webEngine;
    private BorderPane root;
    private Scene scene;
    private ChoiceBox<String> versionChoiceBox;
    private List<String> availableVersions; // Assume you fetch these from a source

    @Override
    public void start(Stage primaryStage) throws Exception {
        webView = new WebView();
        webEngine = webView.getEngine();
        root = new BorderPane();

        availableVersions = getAvailableVersions(); // Fetch the available versions

        versionChoiceBox = new ChoiceBox<>();
        versionChoiceBox.getItems().addAll(availableVersions);
        versionChoiceBox.setStyle(
            "-fx-background-color: #282c34;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 10px;" +
            "-fx-background-radius: 4px;" +
            "-fx-pref-width: 150px;"
        );

        Button btnPlay = createStyledButton("Play");
        Button btnMods = createStyledButton("Mods");
        Button btnSettings = createStyledButton("Settings");
        Button btnRegister = createStyledButton("Register");
        Button btnLogin = createStyledButton("Login");

        btnPlay.setOnAction(e -> loadPage("/Pages/Play.HTML"));
        btnMods.setOnAction(e -> loadPage("/Pages/Mods.HTML"));
        btnSettings.setOnAction(e -> loadPage("/Pages/Settings.HTML"));
        btnRegister.setOnAction(e -> loadPage("/Pages/Register.HTML"));
        btnLogin.setOnAction(e -> loadPage("/Pages/Login.HTML"));

        VBox sidebar = new VBox(15, btnPlay, btnMods, btnSettings, btnRegister, btnLogin, versionChoiceBox);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #20232a; -fx-pref-width: 220px;");

        root.setLeft(sidebar);
        root.setCenter(webView);

        loadPage("/Pages/Play.HTML");

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        scene = new Scene(root, screenWidth * 0.8, screenHeight * 0.8);

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/images/SinLauncher.png")));

        primaryStage.setScene(scene);
        primaryStage.setTitle("SinLauncher");
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
            "-fx-background-color: #61dafb;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12px 24px;" +
            "-fx-background-radius: 6px;"
        );
        return button;
    }

    private void loadPage(String page) {
        webEngine.load(getClass().getResource(page).toExternalForm());
    }

    private List<String> getAvailableVersions() {
        // Mockup of available versions. Replace with actual fetching logic.
        return List.of("1.20.1", "1.19.4", "1.18.2", "1.17.1");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

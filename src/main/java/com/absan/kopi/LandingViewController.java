package com.absan.kopi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LandingViewController {

    public static SpotifyClass spotify = new SpotifyClass(true, "ABSanthosh");

    @FXML Pane connectSpotify;
    @FXML ImageView spotifyInactive;
    @FXML ImageView spotifyActive;
    @FXML Label loginButtonText;

    @FXML
    protected void loginHoverEnterEffect() {
        spotifyInactive.setVisible(false);
        spotifyActive.setOpacity(1.0);
        loginButtonText.setTextFill(Color.color(1,1,1));
    }

    @FXML
    protected void loginHoverExitEffect() {
        spotifyInactive.setVisible(true);
        spotifyActive.setOpacity(0);
        loginButtonText.setTextFill(Color.rgb(30, 215, 96));
    }
    @FXML
    protected void loginClickEffect() {
        System.out.println(spotify.authorizer.getPermissionURL());

//        if(spotify.authorizer.isDeny()){
//
//        }
    }

}

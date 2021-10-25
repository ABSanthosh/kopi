package com.absan.kopi;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;

import java.util.Arrays;
import java.util.List;

public class SpotifyClass {

    final String state = "someExpectedStateString";
    static Authorization authorizer;
    public String userID;
    private static final String clientId = "c60c0201cab240bbba8f3e3340f45a40";
    private static final String clientSecret = "e48a3c42e56c4c6aa6d335a65869af6a";
    private static final String redirectUri = "http://www.movemypaper.com/images/sucess.png";

    boolean debug;
    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectUri)
            .build();

    final List<String> scopes = Arrays.asList(
            "playlist-read-private",
            "playlist-read-collaborative",
            "playlist-modify-public",
            "playlist-modify-private",
            "user-follow-modify",
            "user-library-read",
            "user-follow-modify",
            "user-read-email",
            "app-remote-control",
            "streaming"
    );

    public SpotifyClass(boolean mode, String userID) {
        debug = false;
        this.userID = userID;
        authorizer = new Authorization();
    }

    public void setUserID() {
        try {
            userID = api.getMe().build().get().getId();
        } catch (Exception e) {
        }
    }

    class Authorization {
        public String permissionURL;
        public String code = "";

        public Authorization() {
            permissionURL = userPermission();
            if (debug) {
                System.out.println("Permission " + permissionURL);
            }
            System.out.println();
        }

        public String getPermissionURL() {
            return permissionURL;
        }

        public boolean isRedirectAddress(String url) {
            return !url.contains("code&");
        }

        public boolean isDeny(String targetAddress) {
            return targetAddress.contains("error=access_denied");
        }

        public String userPermission() {
            String authorizeURL = api.createAuthorizeURL(scopes, state);
            if (debug) {
                System.out.println("Authorize " + authorizeURL);
            }
            return authorizeURL;
        }

        public void retrieveCode(String url) {
            int start = url.indexOf("=") + 1;
            while (url.charAt(start) != '&') {
                code += url.charAt(start++);
            }
            if (debug) {
                System.out.println("Code in RetrieveCode " + code);
            }
        }

        public void obtainCredentials(String targetAddress) {
            retrieveCode(targetAddress);
            AuthorizationCodeCredentials authorizationCodeCredentials = null;
            try {
                authorizationCodeCredentials = api.authorizationCodeGrant(code).build().get();
            } catch (Exception e) {
                if (debug) {
                    System.out.println("Code " + code);
                    System.out.println("Something went wrong while Authorizing  " + e.getMessage());
                    //System.out.println();
                }

            }
            assert authorizationCodeCredentials != null;
            api.setAccessToken(authorizationCodeCredentials.getAccessToken());
            api.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            setUserID();
        }
    }

}

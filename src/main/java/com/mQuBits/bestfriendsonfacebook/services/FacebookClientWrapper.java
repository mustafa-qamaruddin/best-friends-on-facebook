package com.mQuBits.bestfriendsonfacebook.services;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

public class FacebookClientWrapper {
  FacebookClient facebookClient;

  public FacebookClientWrapper(String apiKey, String apiSecret) {
    FacebookClient.AccessToken accessToken = new DefaultFacebookClient(Version.LATEST).obtainAppAccessToken(apiKey, apiSecret);
    facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.LATEST);
  }

  public String getAuthRedirectUrl() {
    ScopeBuilder scopeBuilder = new ScopeBuilder();
    scopeBuilder.addPermission(FacebookPermissions.EMAIL);
    scopeBuilder.addPermission(FacebookPermissions.USER_ABOUT_ME);
    With the ScopeBuilder you can now easily build the login dialog url, like this:

    FacebookClient client = new DefaultFacebookClient(Version.LATEST);
    String loginDialogUrlString = client.getLoginDialogUrl(appId, redirectUrl, scopeBuilder);
  }

  public void fetchNumLikesPerFriend()
}

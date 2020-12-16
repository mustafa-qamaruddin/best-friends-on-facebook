package com.mQuBits.bestfriendsonfacebook.services;

import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;

public class FacebookClientWrapper {
  private final String apiKey;
  private final String apiSecret;
  private final String authRedirectUrl;
  FacebookClient facebookClient;

  public FacebookClientWrapper(String apiKey, String apiSecret, String redirectUrl) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    FacebookClient.AccessToken accessToken = new DefaultFacebookClient(Version.LATEST).obtainAppAccessToken(apiKey, apiSecret);
    facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.LATEST);
    authRedirectUrl = getAuthRedirectUrl(redirectUrl);
  }

  public String getAuthRedirectUrl(String redirectUrl) {
    ScopeBuilder scopeBuilder = new ScopeBuilder();
    scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
    scopeBuilder.addPermission(FacebookPermissions.PAGES_READ_ENGAGEMENT);
    FacebookClient client = new DefaultFacebookClient(Version.LATEST);
    return client.getLoginDialogUrl(apiKey, redirectUrl, scopeBuilder);
  }

  public void fetchNumLikesPerFriend() {
    fetchObjectAsJsonObject("/insights");
  }

  void fetchObjectAsJsonObject(String graphQuery) {
    // Make the API call
    JsonObject results = facebookClient.fetchObject(graphQuery, JsonObject.class);

    System.out.println(results.toString());

    // Pull out JSON data by key and map each type by hand.
    JsonMapper jsonMapper = new DefaultJsonMapper();
    User user = jsonMapper.toJavaObject(results.getString("4","{}"), User.class);

  }
}

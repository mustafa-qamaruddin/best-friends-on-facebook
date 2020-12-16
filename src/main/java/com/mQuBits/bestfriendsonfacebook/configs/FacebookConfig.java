package com.mQuBits.bestfriendsonfacebook.configs;

import com.mQuBits.bestfriendsonfacebook.services.FacebookClientWrapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "facebook")
public class FacebookConfig {
  private String apiKey;
  private String apiSecret;

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  private String redirectUrl;

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiSecret() {
    return apiSecret;
  }

  public void setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
  }

  @Bean
  public FacebookClientWrapper facebookClientWrapper() {
    return new FacebookClientWrapper(apiKey, apiSecret, redirectUrl);
  }
}

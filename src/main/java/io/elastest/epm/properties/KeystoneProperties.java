package io.elastest.epm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "keystone")
public class KeystoneProperties {

  private String endpoint;
  private String username;
  private String password;
  private String domain;

  private String port;
  private String version;
  private boolean enabled;

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getDomain() {
    return domain;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {

    return "\n Enabled: "
        + enabled
        + "\n Endpoint: "
        + endpoint
        + "\n Username: "
        + username
        + "\n Password: "
        + password
        + "\n Domain: "
        + domain
        + "\n Port: "
        + port
        + "\n Version: "
        + version;
  }
}

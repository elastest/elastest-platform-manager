package io.elastest.epm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "elastest")
public class ElastestProperties {

  private EMP emp;

  @Override
  public String toString() {
    return "DockerProperties{" + "EMP=" + emp + '}';
  }

  public EMP getEmp() {
    return emp;
  }

  public void setEmp(EMP emp) {
    this.emp = emp;
  }

  public static class EMP {
    private boolean enabled;

    private String endpoint;

    private String port;

    private String topic = "";

    private String seriesName = "";

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    @Override
    public String toString() {
      return "EMP{" + "enabled=" + enabled + ", address='" + endpoint + ":" + port + ", topic=" + topic + ", seriesName="
              + seriesName + '\'' + '}';
    }

    public String getEndPoint() {
      return endpoint;
    }

    public String getPort() {
      return port;
    }

    public void setEndpoint(String endpoint) {
      this.endpoint = endpoint;
    }

    public void setPort(String port) {
      this.port = port;
    }

      public String getTopic() {
          return topic;
      }

      public void setTopic(String topic) {
          this.topic = topic;
      }

      public String getSeriesName() {
          return seriesName;
      }

      public void setSeriesName(String seriesName) {
          this.seriesName = seriesName;
      }
  }
}

package io.elastest.epm.pop.adapter.exception;

public class AdapterException extends Exception {

  private String computeId;

  public AdapterException(String msg) {
    super(msg);
  }

  public AdapterException(String msg, String computeId) {
    super(msg);
    this.computeId = computeId;
  }

  public String getComputeId() {
    return computeId;
  }

  public void setComputeId(String computeId) {
    this.computeId = computeId;
  }

  @Override
  public String toString() {
    return "AdapterException{" + "computeId='" + computeId + '\'' + '}';
  }
}

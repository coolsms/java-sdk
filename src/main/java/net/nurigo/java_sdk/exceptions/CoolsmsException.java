package net.nurigo.java_sdk.exceptions;

/**
 * @class CoolsmsException
 * @brief Thrown when an SDK call returns an comprehensive exception.
 */
public class CoolsmsException extends Exception {
  private int code;

  public CoolsmsException(String message, int code) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }

  public String toString() {
    return "Message : " + this.getMessage() + " Code : " + this.code;
  }
}
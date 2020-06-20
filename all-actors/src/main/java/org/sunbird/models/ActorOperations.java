package org.sunbird.models;

/**
 * This enum will contains different operation for user groups
 *
 */
public enum ActorOperations {
  CREATE_GROUP("createGroup"),
  UPDATE_GROUP("updateGroup");

  private String value;

  /**
   * constructor
   *
   * @param value String
   */
  ActorOperations(String value) {
    this.value = value;
  }

  /**
   * returns the enum value
   *
   * @return String
   */
  public String getValue() {
    return this.value;
  }
}

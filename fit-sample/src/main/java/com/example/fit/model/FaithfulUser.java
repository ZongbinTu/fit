package com.example.fit.model;

import fit.SharedPreferenceAble;

/**
 * @auther Tu enum@foxmail.com
 * @date 9/5/16
 */
@SharedPreferenceAble public class FaithfulUser extends User {
  public FaithfulUser() {
  }

  public long joinTime;

  protected String a;

  public long tlong;

  private String n;
  private boolean smaller;
  private Integer age;
  private int _role;

  String b;

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public long getJoinTime() {
    return joinTime;
  }

  public void setJoinTime(long joinTime) {
    this.joinTime = joinTime;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public boolean isSmaller() {
    return smaller;
  }

  public void setSmaller(boolean smaller) {
    this.smaller = smaller;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public int get_role() {
    return _role;
  }

  public void set_role(int _role) {
    this._role = _role;
  }

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }

  class Inner {
    public char cInner;
  }
}

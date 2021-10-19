package com.studywithus.domain;

import java.sql.Date;

public class Community extends Content {
  private Date registeredDate; // 게시글 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요
  private int category; // 정보, 질문, 스몰톡

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getLike() {
    return like;
  }

  public void setLike(int like) {
    this.like = like;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  // public List<Member> getMembers() {
  // return members;
  // }

  // public void setMembers(List<Member> members) {
  // this.members = members;
  // }
}

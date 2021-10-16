package com.studywithus.dao;

import com.studywithus.domain.Comment;
import com.studywithus.domain.Study;

public interface CommentDao {

  void insert(Comment comment) throws Exception;
  //  List<Study> findAll() throws Exception;
  //  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int no) throws Exception;
  //  void update(Study chargeStudy) throws Exception;
  void delete(int no) throws Exception;
}

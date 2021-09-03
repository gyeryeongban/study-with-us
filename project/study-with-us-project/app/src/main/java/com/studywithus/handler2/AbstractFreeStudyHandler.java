package com.studywithus.handler2;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public abstract class AbstractFreeStudyHandler implements Command {

  protected List<FreeStudy> freeStudyList;

  public AbstractFreeStudyHandler(List<FreeStudy> freeStudyList) {
    this.freeStudyList = freeStudyList;
  }

  // 무료 스터디 번호 조회
  protected FreeStudy findByNo(int no) {
    for (FreeStudy freeStudy : freeStudyList) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }
}

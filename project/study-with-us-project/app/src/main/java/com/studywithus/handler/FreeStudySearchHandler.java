package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudySearchHandler extends AbstractFreeStudyHandler {

  public FreeStudySearchHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 검색]");

    String input = Prompt.inputString("검색어? ");

    for (FreeStudy freeStudy : freeStudyList) {
      if (!freeStudy.getTitle().contains(input) &&
          !freeStudy.getExplanation().contains(input) &&
          !freeStudy.getWriter().contains(input)) {
        continue;
      }

      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          freeStudy.getNo(), 
          freeStudy.getTitle(), 
          freeStudy.getWriter(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), 
          freeStudy.getLike());
    }
  }
}
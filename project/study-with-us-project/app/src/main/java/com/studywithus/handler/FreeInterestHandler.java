package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeInterestHandler extends AbstractFreeInterestHandler {

  public FreeInterestHandler(List<FreeStudy> freeInterestList) {
    super(freeInterestList);
  }

  public void execute() {
    System.out.println("[무료 스터디 / 관심 목록]");

    System.out.println("1. 조회");
    System.out.println("2. 삭제");
    System.out.println("0. 이전 메뉴");

    int input = Prompt.inputInt("선택 > ");

    switch (input) {
      case 1: list(); break;
      case 2: delete(); break;
      default: return;
    }
  }

  // 무료 스터디 관심목록 조회
  public void list() {
    System.out.println("[무료 스터디 / 관심 목록 / 조회]");

    for (FreeStudy freeStudy : freeInterestList) {
      if (freeStudy.getOnOffLine() == 2) {
        System.out.println();
        System.out.printf("%d, %s, %s, %d, %s\n",
            freeStudy.getNo(),
            freeStudy.getTitle(),
            freeStudy.getWriter(),
            freeStudy.getOnOffLine(),
            freeStudy.getArea());

      } else {
        System.out.println();
        System.out.printf("%d, %s, %s, %d \n",
            freeStudy.getNo(),
            freeStudy.getTitle(),
            freeStudy.getWriter(),
            freeStudy.getOnOffLine());
      }
    }
  }

  // 무료 스터디 관심목록 삭제
  public void delete() {
    System.out.println("[무료 스터디 / 관심 목록 / 삭제]\n");
    int no = Prompt.inputInt("번호? ");

    FreeStudy freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    freeInterestList.remove(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler extends AbstractStudyHandler {

  Study chargeStudy;

  // 유료 스터디 관심목록 리스트 (회원 관점)
  List<Study> chargeInterestList;

  // 유료 스터디 결제내역 리스트 (회원 관점)
  List<Payment> chargePaymentList;

  // 유료 스터디 결제자 내역 (멘토 관점)
  List<Member> chargeApplicantList;

  // 각 회원의 참여 유료 스터디 리스트
  HashMap<String, List<Study>> participateChargeStudyMap;
  List<Study> participateChargeStudyList;

  public ChargeStudyDetailHandler(List<Study> chargeStudyList, List<Study> chargeInterestList, List<Payment> chargePaymentList, List<Member> chargeApplicantList, HashMap<String, List<Study>> participateChargeStudyMap) {
    super(chargeStudyList);
    this.chargeInterestList = chargeInterestList;
    this.chargePaymentList = chargePaymentList;
    this.chargeApplicantList = chargeApplicantList;
    this.participateChargeStudyMap = participateChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    chargeStudy = findByNo(no);

    if (chargeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());

    System.out.println();
    System.out.println("1. 결제하기");

    //  해당 스터디의 관심목록 존재 유/무에 따라 관심목록 삭제/추가로 나뉨
    for (Study chargeInterest : chargeInterestList) {
      if (chargeStudy.equals(chargeInterest)) {
        System.out.println("2. 관심목록 삭제하기");
        break;
      }

      else {
        System.out.println("2. 관심목록 추가하기");
        break;
      }
    }

    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        payHandler();

      } else if (input == 2) {
        if (no == 0) {
          interestDelete(chargeStudy);
          return;
        }
        interestAddHandler(chargeStudy);

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      return;
    }
  }
}

package com.studywithus.handler3;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class ExamCalenderDeleteHandler extends AbstractExamCalenderHandler {

  public ExamCalenderAddHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 생성
  public void add() {
    System.out.println("이달의 시험일정 등록]");

    Calender calender = new Calender();

    calender.setNo(Prompt.inputInt("번호 "));
    calender.setTitle(Prompt.inputString("제목 "));
    calender.setContent(Prompt.inputString("내용 "));
    calender.setStartDate(Prompt.inputDate("시험일 "));
    calender.setEndDate(Prompt.inputDate("메모 "));

    ExamCalenderHandler[] calenders = new ExamCalenderHandler[MAX_LENGTH];
  }

  // 이달의 시험일정 상세목록
  public void detail() {
    System.out.println("[이달의 시험일정 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", calender.getTitle());
    System.out.printf("내용: %s\n", calender.getContent());
    System.out.printf("시험일: %s\n", calender.getStartDate());
    System.out.printf("메모: %s\n", calender.getMemo());
  }

  // 이달의 시험일정 변경
  public void update() {
    System.out.println("[이달의 시험일정 변경]");

    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", calender.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", calender.getContent()));
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("시험일정 변경을 취소하였습니다.");
      return;
    }

    calender.setTitle(title);
    calender.setContent(content);
    System.out.println("시험일정을 변경하였습니다.");
  }

  // 이달의 시험일정 삭제
  public void delete() {
    System.out.println("[이달의 시험일정 삭제]");

    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("시험일정 삭제를 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.calenders[i - 1] = this.calenders[i];
    }

    this.calenders[--this.size] = null;
    System.out.println("이달의 시험일정을 삭제하였습니다.");
  }

  // 이달의 시험일정 번호 조회
  private Calender findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.calenders[i].getNo() == no) {
        return this.calenders[i];
      }
    }
    return null;
  }

  // 이달의 시험일정 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.calenders[i].getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
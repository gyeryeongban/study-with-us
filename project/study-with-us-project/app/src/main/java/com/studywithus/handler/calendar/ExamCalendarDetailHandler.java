package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamCalendarDetailHandler extends AbstractCalendarHandler {

  List<Calendar> examCalendar;

  public ExamCalendarDetailHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");

    Calendar examCalendar = findByNo(no);

    if (examCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", examCalendar.getTitle());
    System.out.printf("내용: %s\n", examCalendar.getContent());
    System.out.printf("시험일: %s\n", examCalendar.getExamDate());

    System.out.println();
  }
}
package com.studywithus.handler.chargestudy;

import java.sql.Date;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class MentorApplicationAddHandler implements Command {

  MentorApplicationDao mentorApplicationDao;


  public MentorApplicationAddHandler(MentorApplicationDao mentorApplicationDao) {
    this.mentorApplicationDao = mentorApplicationDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 멘토 신청]\n");

    if (AuthLogInHandler.loginUser.isMentor()) {
      System.out.println("이미 멘토입니다.");
      return;
    }

    MentorApplicationForm mentorApplicantEmail = mentorApplicationDao.findByEmail(AuthLogInHandler.loginUser.getEmail());

    // 신청서가 이미 있으면서 아직 승인/거절 결정이 안났다면 (visible이 true라면)
    if (mentorApplicantEmail != null && mentorApplicantEmail.isVisible()) {
      System.out.println("이미 멘토 신청이 완료되었습니다.");
      return;
    }

    while (true) {
      String selfIntro = Prompt.inputString("자기 소개를 입력하세요. > ");
      String subject = Prompt.inputString("개설할 스터디 주제를 입력하세요. > ");
      String explanation = Prompt.inputString("스터디 설명을 입력하세요. > ");
      System.out.println();

      if (selfIntro.equals("") || subject.equals("") || explanation.equals("")) {
        System.out.println("모두 필수입력 항목입니다.\n");
        continue;

      } else {
        MentorApplicationForm mentorApplication = new MentorApplicationForm();

        mentorApplication.setVisible(true);
        mentorApplication.setName(AuthLogInHandler.loginUser.getName());
        mentorApplication.setMentorApplicantEmail(AuthLogInHandler.loginUser.getEmail());
        mentorApplication.setSelfIntroduction(selfIntro);
        mentorApplication.setChargeStudySubject(subject);
        mentorApplication.setChargeStudyExplanation(explanation);
        mentorApplication.setRegisteredDate(new Date(System.currentTimeMillis()));

        mentorApplicationDao.insert(mentorApplication);

        System.out.println();
        System.out.println("멘토 신청이 완료되었습니다.");
      }
      break;
    }
  }
}

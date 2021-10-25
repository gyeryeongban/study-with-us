package com.studywithus.handler.freestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyParticipationCancelHandler implements Command {

  StudyDao freeStudyDao;
  MemberDao memberDao;
  SqlSession sqlSession;


  public FreeStudyParticipationCancelHandler(StudyDao freeStudyDao, MemberDao memberDao, SqlSession sqlSession) {
    this.freeStudyDao = freeStudyDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 참여 취소]\n");
    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = freeStudyDao.findByNoParticipateStudy
        (AuthLogInHandler.getLoginUser().getNo(), no, 1);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("무료 스터디 참여를 취소하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("무료 스터디 참여 취소가 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        freeStudyDao.deleteStudyMember(AuthLogInHandler.getLoginUser().getNo(), no);

        int temp = AuthLogInHandler.getLoginUser().getUserAccessLevel();
        AuthLogInHandler.getLoginUser().setUserAccessLevel((~temp) & Menu.ACCESS_MEMBER);
        System.out.println();
        System.out.println("무료 스터디 참여를 취소하였습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}

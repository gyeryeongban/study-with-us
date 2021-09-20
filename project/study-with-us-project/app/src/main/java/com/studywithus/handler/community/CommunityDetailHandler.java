package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;
//import com.studywithus.domain.Member;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
//import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityDetailHandler extends AbstractCommunityHandler{

  String updateKey;
  String deleteKey;

  public CommunityDetailHandler(List<Community> communityList, String updateKey, String deleteKey) {
    super(communityList);
    this.updateKey = updateKey;
    this.updateKey = updateKey;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 상세보기] \n");

    int no = Prompt.inputInt("번호? ");
    Community community = findByNo(no);

    if (community == null) {
      System.out.println();
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", community.getTitle());
    System.out.printf("내용: %s\n", community.getContent());
    System.out.printf("작성자: %s\n", community.getWriter().getName());
    System.out.printf("등록일: %s\n", community.getRegisteredDate());

    community.setViewCount(community.getViewCount() + 1);
    System.out.printf("조회수: %d\n", community.getViewCount());

    System.out.println();

    Member loginUser = AuthLogInHandler.getLoginUser(); 
    if (loginUser == null || community.getWriter().getId() != loginUser.getId()) {
      return;
    }

    // CommunityUpdateHandler나 CommunityStudyDeleteHandler를 실행할 때 
    // 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
    request.setAttribute("no", no);

    while (true) {

      System.out.println("1. 수정");
      System.out.println("2. 삭제");
      System.out.println("0. 이전");
      System.out.println();
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

      switch (input) {
        case 1:
          request.getRequestDispatcher(updateKey).forward(request);
          return;
        case 2:
          request.getRequestDispatcher(deleteKey).forward(request);
          return;
        case 0:
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}

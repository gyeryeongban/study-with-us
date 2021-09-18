package com.studywithus.handler.user;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class SnsLogInHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public SnsLogInHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[SNS 로그인]");

    String snsId = Prompt.inputString("아이디: ");
    String password = Prompt.inputString("비밀번호: ");

    Member member = findBySnsIdPassword(snsId, password);

    if (member == null) {
      System.out.println("연동된 SNS 계정이 존재하지 않습니다.");

    } else {
      System.out.printf("%s님 환영합니다.\n", member.getName());

      loginUser = member;
      userAccessLevel = member.getUserAccessLevel();
    }
  }

  private Member findBySnsIdPassword(String id, String password) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) && member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }
}

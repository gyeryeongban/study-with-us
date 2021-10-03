package com.studywithus.table;

import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 회원 데이터를 처리하는 일을 한다.
// 
public class MentorApplicationTable extends JsonDataTable<MentorApplicationForm> implements DataProcessor {

  public MentorApplicationTable() {
    super("mentorApplication.json", MentorApplicationForm.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "mentorApplication.insert": insert(request, response); break;
      case "mentorApplication.selectList": selectList(request, response); break;
      //      case "member.selectOne": selectOne(request, response); break;
      case "mentorApplication.selectOneByName": selectOneByName(request, response); break;
      case "mentorApplication.selectOneByEmail": selectOneByEmail(request, response); break;
      //      case "member.update": update(request, response); break;
      //      case "member.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    MentorApplicationForm mentorApplication = request.getObject(MentorApplicationForm.class);

    list.add(mentorApplication);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOneByName(Request request, Response response) throws Exception {
    String name = request.getParameter("name");
    MentorApplicationForm mentorApplication = null;
    for (MentorApplicationForm Application : list) {
      if (Application.getName().equals(name)) {
        mentorApplication = Application;
        break;
      }
    }
    if (mentorApplication != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(mentorApplication);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 회원을 찾을 수 없습니다.");
    }
  }

  private void selectOneByEmail(Request request, Response response) throws Exception {
    String email = request.getObject(String.class);

    MentorApplicationForm mentorApplication = null;

    for (MentorApplicationForm Application : list) {
      if (Application.getMentorApplicantEmail().equals(email)) {
        mentorApplication = Application;
        break;
      }
    }
    if (mentorApplication != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(mentorApplication);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 회원을 찾을 수 없습니다.");
    }
  }

  //
  //  private void selectOne(Request request, Response response) throws Exception {
  //    int no = Integer.parseInt(request.getParameter("no"));
  //    Study chargeStudy = findByNo(no);
  //
  //    if (chargeStudy != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(chargeStudy);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
  //    }
  //  }
  //
  //  private void update(Request request, Response response) throws Exception {
  //    Study chargeStudy = request.getObject(Study.class);
  //
  //    int index = indexOf(chargeStudy.getNo());
  //    if (index == -1) {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
  //      return;
  //    }
  //
  //    list.set(index, chargeStudy);
  //    response.setStatus(Response.SUCCESS);
  //  }
  //
  //  private void delete(Request request, Response response) throws Exception {
  //    int no = Integer.parseInt(request.getParameter("no"));
  //    int index = indexOf(no);
  //
  //    if (index == -1) {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
  //      return;
  //    }
  //
  //    list.remove(index);
  //    response.setStatus(Response.SUCCESS);
  //  }
  //
  //  private Study findByNo(int no) {
  //    for (Study chargeStudy : list) {
  //      if (chargeStudy.getNo() == no) {
  //        return chargeStudy;
  //      }
  //    }
  //    return null;
  //  }
  //
  //  private int indexOf(int memberNo) {
  //    for (int i = 0; i < list.size(); i++) {
  //      if (list.get(i).getNo() == memberNo) {
  //        return i;
  //      }
  //    }
  //    return -1;
  //  }

}












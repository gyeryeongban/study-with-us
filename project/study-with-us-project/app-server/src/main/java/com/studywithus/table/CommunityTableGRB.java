package com.studywithus.table;

import java.util.ArrayList;
import com.studywithus.domain.Community;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 커뮤니티 데이터를 처리하는 일을 한다.
//
public class CommunityTableGRB extends JsonDataTable<Community> implements DataProcessor {

  String commandPrefix;

  public CommunityTableGRB(String filename, String commandPrefix) {
    super(filename, Community.class);
    this.commandPrefix = commandPrefix;
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    String action = request.getCommand().substring(commandPrefix.length());
    switch (action) {
      case "insert":
        insert(request, response);
        break;
      case "selectList":
        selectList(request, response);
        break;
      case "selectListByKeyword":
        selectListByKeyword(request, response);
        break;
      case "selectOne":
        selectOne(request, response);
        break;
      case "update":
        update(request, response);
        break;
      case "delete":
        delete(request, response);
        break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Community community = request.getObject(Community.class);

    if (list.isEmpty()) {
      community.setNo(1);
    } else {
      Community lastIndex = list.get(list.size() - 1);
      community.setNo(lastIndex.getNo() + 1);
    }

    list.add(community);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Community> searchResult = new ArrayList<>();
    for (Community community : list) {
      if (!community.getTitle().contains(keyword) && !community.getContent().contains(keyword)
          && !community.getWriter().getName().contains(keyword)) {
        continue;
      }
      searchResult.add(community);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Community community = findByNo(no);

    if (community != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(community);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 커뮤니티를 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Community community = request.getObject(Community.class);

    int index = indexOf(community.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 커뮤니티를 찾을 수 없습니다.");
      return;
    }

    list.set(index, community);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 커뮤니티를 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Community findByNo(int no) {
    for (Community community : list) {
      if (community.getNo() == no) {
        return community;
      }
    }
    return null;
  }

  private int indexOf(int communityNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == communityNo) {
        return i;
      }
    }
    return -1;
  }
}


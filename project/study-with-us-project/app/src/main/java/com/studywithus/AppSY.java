package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import static com.studywithus.menu.Menu.ACCESS_MENTEE;
import static com.studywithus.menu.Menu.ACCESS_MENTOR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studywithus.domain.Calendar;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.calendar.ExamCalendarAddHandler;
import com.studywithus.handler.calendar.ExamCalendarDeleteHandler;
import com.studywithus.handler.calendar.ExamCalendarDetailHandler;
import com.studywithus.handler.calendar.ExamCalendarListHandler;
import com.studywithus.handler.calendar.ExamCalendarUpdateHandler;
import com.studywithus.handler.calendar.JobsCalendarAddHandler;
import com.studywithus.handler.calendar.JobsCalendarDeleteHandler;
import com.studywithus.handler.calendar.JobsCalendarDetailHandler;
import com.studywithus.handler.calendar.JobsCalendarListHandler;
import com.studywithus.handler.calendar.JobsCalendarUpdateHandler;
import com.studywithus.handler.community.CommunityAddHandler;
import com.studywithus.handler.community.CommunityDeleteHandler;
import com.studywithus.handler.community.CommunityDetailHandler;
import com.studywithus.handler.community.CommunityListHandler;
import com.studywithus.handler.community.CommunitySearchHandler;
import com.studywithus.handler.community.CommunityUpdateHandler;
import com.studywithus.handler.community.MyPostListHandler;
import com.studywithus.handler.study.ChargeStudyAddHandler;
import com.studywithus.handler.study.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.study.ChargeStudyDeletedDetailHandler;
import com.studywithus.handler.study.ChargeStudyDeletedListHandler;
import com.studywithus.handler.study.ChargeStudyDetailHandler;
import com.studywithus.handler.study.ChargeStudyInterestAddHandler;
import com.studywithus.handler.study.ChargeStudyInterestDeleteHandler;
import com.studywithus.handler.study.ChargeStudyInterestListHandler;
import com.studywithus.handler.study.ChargeStudyListHandler;
import com.studywithus.handler.study.ChargeStudyPaymentHandler;
import com.studywithus.handler.study.ChargeStudySearchHandler;
import com.studywithus.handler.study.ChargeStudyUpdateHandler;
import com.studywithus.handler.study.FreeStudyAddHandler;
import com.studywithus.handler.study.FreeStudyDeleteHandler;
import com.studywithus.handler.study.FreeStudyDetailHandler;
import com.studywithus.handler.study.FreeStudyInterestAddHandler;
import com.studywithus.handler.study.FreeStudyInterestDeleteHandler;
import com.studywithus.handler.study.FreeStudyInterestListHandler;
import com.studywithus.handler.study.FreeStudyListHandler;
import com.studywithus.handler.study.FreeStudySearchHandler;
import com.studywithus.handler.study.FreeStudyUpdateHandler;
import com.studywithus.handler.study.MentorApplicationAddHandler;
import com.studywithus.handler.study.MentorApplicationDetailHandler;
import com.studywithus.handler.study.ParticipateChargeStudyListHandler;
import com.studywithus.handler.study.RegisterChargeStudyListHandler;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.handler.user.AuthLogOutHandler;
import com.studywithus.handler.user.FindIdHandler;
import com.studywithus.handler.user.MembershipWithdrawalHandler;
import com.studywithus.handler.user.ResetPasswordHandler;
import com.studywithus.handler.user.SignUpHandler;
import com.studywithus.handler.user.SnsLogInHandler;
import com.studywithus.handler.user.SnsSignUpHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class AppSY {
	List<Member> memberList = new LinkedList<>();
	List<Member> freeApplicantList = new ArrayList<>();
	List<Member> mentorApplicantList = new ArrayList<>();
	List<Member> chargeApplicantList = new ArrayList<>();
	List<String> mentorList = new ArrayList<>();

	List<Study> registerFreeStudyList = new ArrayList<>();
	List<Study> participateFreeStudyList = new ArrayList<>();
	List<Study> registerChargeStudyList = new ArrayList<>();
	List<Study> participateChargeStudyList = new ArrayList<>();
	List<Study> freeInterestList = new ArrayList<>();
	List<Study> chargeInterestList = new ArrayList<>();
	List<Study> freeStudyList = new ArrayList<>();
	List<Study> freeApplicationList = new ArrayList<>();
	List<Study> chargeStudyList = new ArrayList<>();
	List<Study> chargeDeleteRequestList = new ArrayList<>();

	List<MentorApplicationForm> mentorApplicationFormList = new ArrayList<>();

	List<Payment> chargePaymentList = new ArrayList<>();

	List<Community> communityInfoList = new ArrayList<>();
	List<Community> communityQaList = new ArrayList<>();
	List<Community> communityTalkList = new ArrayList<>();

	List<Calendar> jobsCalendarList = new ArrayList<>();
	List<Calendar> examCalendarList = new ArrayList<>();

	HashMap<String, Command> commandMap = new HashMap<>();
	HashMap<String, List<Study>> participateFreeStudyMap = new HashMap<>();
	HashMap<String, List<Study>> participateChargeStudyMap = new HashMap<>();
	HashMap<String, List<Study>> registerFreeStudyMap = new HashMap<>();
	HashMap<String, List<Study>> registerChargeStudyMap = new HashMap<>();
	HashMap<String, List<Study>> applyFreeStudyMap = new HashMap<>(); // 주창 추가


	class MenuItem extends Menu {
		String menuId;

		public MenuItem(String title, String menuId) {
			super(title);
			this.menuId = menuId;
		}

		public MenuItem(String title, int accessScope, String menuId) {
			super(title, accessScope);
			this.menuId = menuId;
		}

		@Override
		public void execute() {
			Command command = commandMap.get(menuId);
			try {
				command.execute(new CommandRequest(commandMap));
			} catch (Exception e) {
				System.out.printf("%s 명령을 실행하는 중 오류 발생!\n", menuId);
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		AppSY appSY = new AppSY(); 
		appSY.service();
	}

	public AppSY() {

		commandMap.put("/auth/logIn", new AuthLogInHandler(memberList));
		commandMap.put("/google/logIn", new SnsLogInHandler(memberList));
		commandMap.put("/facebook/logIn", new SnsLogInHandler(memberList));
		commandMap.put("/kakao/logIn", new SnsLogInHandler(memberList));
		commandMap.put("/naver/logIn", new SnsLogInHandler(memberList));

		commandMap.put("/auth/logOut", new AuthLogOutHandler(memberList));

		commandMap.put("/auth/signUp", new SignUpHandler(memberList));
		commandMap.put("/google/signUp", new SnsSignUpHandler(memberList));
		commandMap.put("/facebook/signUp", new SnsSignUpHandler(memberList));
		commandMap.put("/kakao/signUp", new SnsSignUpHandler(memberList));
		commandMap.put("/naver/signUp", new SnsSignUpHandler(memberList));

		commandMap.put("/auth/membershipWithdrawal", new MembershipWithdrawalHandler(memberList));

		commandMap.put("/find/id", new FindIdHandler(memberList));
		commandMap.put("/reset/password", new ResetPasswordHandler(memberList));

		//		commandMap.put("/myinfo/list", new MyInfoHandler(memberList)); 

		commandMap.put("/freeInterest/list", new FreeStudyInterestListHandler(freeInterestList));
		commandMap.put("/freeInterest/delete", new FreeStudyInterestDeleteHandler(freeStudyList));
		commandMap.put("/chargeInterest/list", new ChargeStudyInterestListHandler(chargeInterestList));
		// 추가
		commandMap.put("/chargeInterest/delete", new ChargeStudyInterestDeleteHandler(chargeStudyList));

		commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(mentorApplicationFormList, chargeApplicantList));
		commandMap.put("/mentorApplicant/list", new MentorApplicationDetailHandler(mentorApplicationFormList, mentorList));

		commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
		commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList, registerFreeStudyMap));
		commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
		commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList));
		commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
		commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));

		commandMap.put("/chargeStudy/search", new ChargeStudySearchHandler(chargeStudyList));
		commandMap.put("/chargeStudy/add", new ChargeStudyAddHandler(chargeStudyList, registerChargeStudyMap));
		commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(chargeStudyList));
		commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(chargeStudyList));
		commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(chargeStudyList));
		commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(chargeStudyList, chargeDeleteRequestList));
		commandMap.put("/chargeStudy/deleteList", new ChargeStudyDeletedListHandler(chargeDeleteRequestList));
		commandMap.put("/chargeStudy/deleteDetail", new ChargeStudyDeletedDetailHandler(chargeStudyList, chargeDeleteRequestList));
		commandMap.put("/chargeStudy/payment", new ChargeStudyPaymentHandler(chargeStudyList, chargePaymentList, chargeApplicantList, participateChargeStudyMap));
		commandMap.put("/chargeStudy/interestAdd", new ChargeStudyInterestAddHandler(chargeStudyList));  // 
		commandMap.put("/chargeStudy/interestDelete", new ChargeStudyInterestDeleteHandler(chargeStudyList)); //

		commandMap.put("/chargeStudy/registerChargeStudy", new RegisterChargeStudyListHandler(registerChargeStudyMap)); // 내가 참여한 유료 스터디***
		commandMap.put("/chargeStudy/participateChargeStudt", new ParticipateChargeStudyListHandler(participateChargeStudyMap)); // 내가 생성한 유료 스터디***

		commandMap.put("/freeStudy/addInterest", new FreeStudyInterestAddHandler(freeStudyList));
		commandMap.put("/freeStudy/deleteInterest", new FreeStudyInterestDeleteHandler(freeStudyList));


		commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
		commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
		commandMap.put("/communityQa/detail", new CommunityDetailHandler(communityQaList, "/communityQa/update", "/communityQa/delete"));
		commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
		commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
		commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));

		commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
		commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
		commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList, "/communityInfo/update", "/communityInfo/delete"));
		commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
		commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
		commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));

		commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
		commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
		commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList, "/communityTalk/update", "/communityTalk/delete"));
		commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
		commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
		commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));

		commandMap.put("/myPost/list", new MyPostListHandler(communityQaList, communityInfoList, communityTalkList));

		commandMap.put("/jobsCalendar/add", new JobsCalendarAddHandler(jobsCalendarList));
		commandMap.put("/jobsCalendar/list", new JobsCalendarListHandler(jobsCalendarList));
		commandMap.put("/jobsCalendar/detail", new JobsCalendarDetailHandler(jobsCalendarList));
		commandMap.put("/jobsCalendar/update", new JobsCalendarUpdateHandler(jobsCalendarList));
		commandMap.put("/jobsCalendar/delete", new JobsCalendarDeleteHandler(jobsCalendarList));

		commandMap.put("/examCalendar/add", new ExamCalendarAddHandler(examCalendarList));
		commandMap.put("/examCalendar/list", new ExamCalendarListHandler(examCalendarList));
		commandMap.put("/examCalendar/detail", new ExamCalendarDetailHandler(examCalendarList));
		commandMap.put("/examCalendar/update", new ExamCalendarUpdateHandler(examCalendarList));
		commandMap.put("/examCalendar/delete", new ExamCalendarDeleteHandler(examCalendarList));
	}

	void service() {
		loadObjects("member_jc.json", memberList, Member.class);
		loadObjects("chargeInterest_jc.json", chargeInterestList, Study.class);
		loadObjects("freeStudy_jc.json", freeStudyList, Study.class);
		loadObjects("chargeStudy_jc.json", chargeStudyList, Study.class);
		loadObjects("communityQa_jc.json", communityQaList, Community.class);
		loadObjects("communityInfo_jc.json", communityInfoList, Community.class);
		loadObjects("communityTalk_jc.json", communityTalkList, Community.class);
		loadObjects("jobsCalendar_jc.json", jobsCalendarList, Calendar.class);
		loadObjects("examCalendar_jc.json", examCalendarList, Calendar.class);

		createMainMenu().execute();
		Prompt.close();

		saveObjects("member.json", memberList);
		saveObjects("freeInterest.json", freeInterestList);
		saveObjects("chargeInterest.json", chargeInterestList);
		saveObjects("freeStudy.json", freeStudyList);
		saveObjects("chargeStudy.json", chargeStudyList);
		saveObjects("communityQa.json", communityQaList);
		saveObjects("communityInfo.json", communityInfoList);
		saveObjects("communityTalk.json", communityTalkList);
		saveObjects("jobsCalendar.json", jobsCalendarList);
		saveObjects("examCalendar.json", examCalendarList);
	}

	// JSON 형식으로 저장된 데이터를 읽어서 객체로 만든다.
	private <E> void loadObjects (
			String filepath, // 데이터를 읽어 올 파일 경로
			List<E> list, // 로딩한 데이터를 객체로 만든 후 저장할 목록
			Class<E> domainType // 생성할 객체의 타입 정보
			) {

		try (BufferedReader in = new BufferedReader(
				new FileReader(filepath, Charset.forName("UTF-8")))) {

			StringBuilder strBuilder = new StringBuilder();
			String str;

			while ((str = in.readLine()) != null) { // 파일 전체를 읽는다.
				strBuilder.append(str);
			}

			// StringBuilder로 읽어 온 JSON 문자열을 객체로 바꾼다.
			Type type = TypeToken.getParameterized(Collection.class, domainType).getType(); 
			Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

			// JSON 데이터로 읽어온 목록을 파라미터로 받은 List 에 저장한다.
			list.addAll(collection);

			System.out.printf("%s 데이터 로딩 완료!\n", filepath);

		} catch (Exception e) {
			System.out.printf("%s 데이터 로딩 오류!\n", filepath);
		}
	}

	// 객체를 JSON 형식으로 저장한다.
	private void saveObjects(String filepath, List<?> list) {
		try (PrintWriter out = new PrintWriter(
				new BufferedWriter(
						new FileWriter(filepath, Charset.forName("UTF-8"))))) {

			out.print(new Gson().toJson(list));

			System.out.printf("%s 데이터 출력 완료!\n", filepath);

		} catch (Exception e) {
			System.out.printf("%s 데이터 출력 오류!\n", filepath);
			e.printStackTrace();
		}
	}

	//------------------------------ STUDY WITH US -----------------------------------------

	// 메인 메뉴
	Menu createMainMenu() {
		MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
		mainMenuGroup.setPrevMenuTitle("종료");

		mainMenuGroup.add(createSignUpMenu());
		mainMenuGroup.add(createLogInMenu());
		mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logOut"));
		mainMenuGroup.add(createMyPageMenu());
		mainMenuGroup.add(createAdminPageMenu());
		mainMenuGroup.add(createFreeStudyMenu());
		mainMenuGroup.add(createChargeStudyMenu());
		mainMenuGroup.add(createCommunityMenu());
		mainMenuGroup.add(createCalendarMenu());

		return mainMenuGroup;
	}

	// 메인 메뉴 / 회원가입
	private Menu createSignUpMenu() {
		MenuGroup signUpMenu = new MenuGroup("회원가입", ACCESS_LOGOUT);

		signUpMenu.add(new MenuItem("이메일로 가입하기", "/auth/signUp"));
		signUpMenu.add(createSnsSignUpMenu()); 

		return signUpMenu;
	}

	// 메인 메뉴 / 회원가입 / SNS로 시작하기
	private Menu createSnsSignUpMenu() {
		MenuGroup snsSignUpMenu = new MenuGroup("SNS로 시작하기", ACCESS_LOGOUT);

		snsSignUpMenu.add(new MenuItem("구글로 시작하기","/google/signUp"));
		snsSignUpMenu.add(new MenuItem("페이스북으로 시작하기","/facebook/signUp"));
		snsSignUpMenu.add(new MenuItem("카카오로 시작하기","/kakao/signUp"));
		snsSignUpMenu.add(new MenuItem("네이버로 시작하기","/naver/signUp"));

		return snsSignUpMenu;
	}

	// 메인 메뉴 / 로그인
	private Menu createLogInMenu() {
		MenuGroup logInMenu = new MenuGroup("로그인", ACCESS_LOGOUT);

		logInMenu.add(new MenuItem("이메일 로그인", "/auth/logIn"));
		logInMenu.add(createSnsLogInMenu()); 
		logInMenu.add(new MenuItem("아이디 찾기", "/find/id"));
		logInMenu.add(new MenuItem("비밀번호 변경", "/reset/password"));

		return logInMenu;
	}

	// 메인 메뉴 / 로그인 / SNS로 시작하기
	private Menu createSnsLogInMenu() {
		MenuGroup createSnsLogInMenu = new MenuGroup("SNS 로그인", ACCESS_LOGOUT);

		createSnsLogInMenu.add(new MenuItem("구글로 로그인","/google/logIn"));
		createSnsLogInMenu.add(new MenuItem("페이스북으로 로그인","/facebook/logIn"));
		createSnsLogInMenu.add(new MenuItem("카카오로 로그인","/kakao/logIn"));
		createSnsLogInMenu.add(new MenuItem("네이버로 로그인","/naver/logIn"));

		return createSnsLogInMenu;
	}

	//------------------------------ 무료 스터디 -----------------------------------------

	//무료 스터디 메인 메뉴
	private Menu createFreeStudyMenu() {
		MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

		freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
		freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
		freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
		freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
		// freeStudyMenu.add(new MenuItem("수정", ACCESS_LEADER, "/freeStudy/update"));
		// freeStudyMenu.add(new MenuItem("삭제", ACCESS_LEADER | ACCESS_ADMIN, "/freeStudy/delete"));

		return freeStudyMenu;
	}

	// ------------------------------ 유료 스터디 -----------------------------------------

	// 유료 스터디 메인 메뉴
	private Menu createChargeStudyMenu() {
		MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

		chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
		chargeStudyMenu.add(new MenuItem("생성", ACCESS_MENTOR, "/chargeStudy/add"));
		chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
		chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
		//		chargeStudyMenu.add(new MenuItem("수정", ACCESS_MENTOR, "/chargeStudy/update")); // 스터디 메인 메뉴 통일***
		//		chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_MENTOR, "/chargeStudy/deleteRequest")); // 스터디 메인 메뉴 통일***
		chargeStudyMenu.add(new MenuItem("멘토 신청", ACCESS_GENERAL, "/mentorApplicant/add"));

		return chargeStudyMenu;
	}

	// ------------------------------ 커뮤니티 -----------------------------------------

	// 커뮤니티 메인 메뉴
	private Menu createCommunityMenu() {
		MenuGroup communityMenu = new MenuGroup("커뮤니티");

		communityMenu.add(createCommunityInfoMenu());
		communityMenu.add(createCommunityQaMenu());
		communityMenu.add(createCommunityTalkMenu());

		return communityMenu;
	}

	// 커뮤니티 / 질문
	private Menu createCommunityQaMenu() {
		MenuGroup communityQaMenu = new MenuGroup("질문");

		communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
		communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
		communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
		communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
		communityQaMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityQa/update"));
		communityQaMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityQa/delete"));

		return communityQaMenu;
	}

	// 커뮤니티 / 정보
	private Menu createCommunityInfoMenu() {
		MenuGroup communityInfoMenu = new MenuGroup("정보");

		communityInfoMenu.add(new MenuItem("검색", "/communityInfo/search"));
		communityInfoMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityInfo/add"));
		communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
		communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
		communityInfoMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityInfo/update"));
		communityInfoMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityInfo/delete"));

		return communityInfoMenu;
	}

	// 커뮤니티 / 스몰톡
	private Menu createCommunityTalkMenu() {
		MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

		communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
		communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
		communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
		communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
		communityTalkMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityTalk/update"));
		communityTalkMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityTalk/delete"));

		return communityTalkMenu;
	}

	// ------------------------------ 캘린더 -----------------------------------------

	private Menu createCalendarMenu() {
		MenuGroup calendarMenu = new MenuGroup("캘린더");

		calendarMenu.add(createJobsCalendarMenu());
		calendarMenu.add(createExamCalendarMenu());

		return calendarMenu;
	}

	private Menu createJobsCalendarMenu() {
		MenuGroup jobsCalendarMenu = new MenuGroup("이달의 채용공고");

		jobsCalendarMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsCalendar/add"));
		jobsCalendarMenu.add(new MenuItem("조회", "/jobsCalendar/list"));
		jobsCalendarMenu.add(new MenuItem("상세보기", "/jobsCalendar/detail"));
		//    jobsCalendarMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/jobsCalendar/update"));
		//    jobsCalendarMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsCalendar/delete"));

		return jobsCalendarMenu;
	}

	private Menu createExamCalendarMenu() {
		MenuGroup examCalendarMenu = new MenuGroup("이달의 시험일정");

		examCalendarMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examCalendar/add"));
		examCalendarMenu.add(new MenuItem("조회", "/examCalendar/list"));
		examCalendarMenu.add(new MenuItem("상세보기", "/examCalendar/detail"));
		//    examCalendarMenu.add(new MenuItem("수정", ACCESS_ADMIN, "/examCalendar/update"));
		//    examCalendarMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examCalendar/delete"));

		return examCalendarMenu;
	}

	// ------------------------------ 마이 페이지 -----------------------------------------

	// 마이 페이지 메인
	private Menu createMyPageMenu() {
		MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

		myPageMenu.add(createActivityDetailMenu());
		myPageMenu.add(createInterestMenu());
		// 결제내역 돌아가는지 확인 후 ACCESS_MENTEE 권한 추가 예정
		myPageMenu.add(new MenuItem("나의 결제내역", "/chargeStudy/payment")); 
		myPageMenu.add(new MenuItem("회원 탈퇴", ACCESS_GENERAL, "/auth/membershipWithdrawal"));
		myPageMenu.add(new MenuItem("나의 정보", "auth/userInfo"));

		return myPageMenu;
	}

	// 마이 페이지 / 나의 활동
	private Menu createActivityDetailMenu() {

		MenuGroup activityDetailMenu = new MenuGroup("나의 활동");
		activityDetailMenu.add(createMyStudyMenu());
		activityDetailMenu.add(createMyPostMenu());

		return activityDetailMenu;
	}

	// 마이 페이지 / 나의 활동 / 나의 스터디
	private Menu createMyStudyMenu() {

		MenuGroup myStudyMenu = new MenuGroup("나의 스터디");
		myStudyMenu.add(createFreeStudyApplyMenu());
		myStudyMenu.add(createRegisterFreeStudyMenu()); // 주창 추가
		myStudyMenu.add(createParticipateFreeStudyMenu()); // 주창 추가

		myStudyMenu.add(createRegisterChargeStudyMenu()); 
		myStudyMenu.add(createParticipateChargeStudyMenu()); 

		return myStudyMenu;
	}

	// 마이 페이지 / 나의 활동 / 나의 스터디 / 무료 스터디 신청 내역
	private Menu createFreeStudyApplyMenu() {

		MenuGroup freeStudyApplyMenu = new MenuGroup("무료 스터디 신청 내역", ACCESS_GENERAL);
		freeStudyApplyMenu.add(new MenuItem("조회", "/freeStudyApply/list"));
		freeStudyApplyMenu.add(new MenuItem("상세보기", "/freeStudyApply/detail"));
		freeStudyApplyMenu.add(new MenuItem("수정", "/freeStudyApply/update"));
		freeStudyApplyMenu.add(new MenuItem("삭제", "/freeStudyApply/delete"));

		return freeStudyApplyMenu;
	}

	// 주창 추가
	// 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디(팀장 관점)
	// - "신청자 명단" -> 상세보기(승인/삭제) 추가해야 함
	private Menu createRegisterFreeStudyMenu() {

		MenuGroup registerFreeStudyMenu = new MenuGroup("내가 생성한 무료 스터디", ACCESS_LEADER);
		//		registerFreeStudyMenu.add(new MenuItem("조회 [상세보기랑 합침]", "/freeStudy/registerStudyList"));
		registerFreeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/registerStudyList"));
		//		registerFreeStudyMenu.add(new MenuItem("삭제[삭제를 여기서?]", "/registerFreeStudy/delete"));

		return registerFreeStudyMenu;
	}

	// 주창 추가
	// 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 참여한 무료 스터디(팀원 관점)
	private Menu createParticipateFreeStudyMenu() {

		MenuGroup participateFreeStudyMenu = new MenuGroup("내가 참여한 무료 스터디", Menu.ACCESS_MEMBER);
		participateFreeStudyMenu.add(new MenuItem("조회", "/freeStudy/participateStudyList"));
		//    participateFreeStudyMenu.add(new MenuItem("상세보기[안하기로함]", "/participateFreeStudy/detail"));

		return participateFreeStudyMenu;
	}

	//마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 유료 스터디(멘토 관점)
	// - "신청자 명단" -> 상세보기(승인/삭제) 추가해야 함
	// 선영 추가
	private Menu createRegisterChargeStudyMenu() {

		MenuGroup registerChargeStudyMenu = new MenuGroup("내가 생성한 유료 스터디", ACCESS_GENERAL);
		registerChargeStudyMenu.add(new MenuItem("조회", "/registerChargeStudy/list"));
		registerChargeStudyMenu.add(new MenuItem("상세보기", "/registerChargeStudy/detail"));
		registerChargeStudyMenu.add(new MenuItem("삭제", "/registerChargeStudy/delete"));

		return registerChargeStudyMenu;
	}

	// 마이 페이지 / 나의 활동 / 내 스터디 / 내가 참여한 유료 스터디(팀원~)***
	private Menu createParticipateChargeStudyMenu() {

		MenuGroup participateChargeStudyMenu = new MenuGroup("내가 참여한 유료 스터디", ACCESS_GENERAL);
		participateChargeStudyMenu.add(new MenuItem("조회", "/participateChargeStudy/list"));
		participateChargeStudyMenu.add(new MenuItem("상세보기", "/participateChargeStudy/detail"));
		// 상세보기 / 후기 작성 추가하기 // 보류

		return participateChargeStudyMenu;
	}

	// 마이 페이지 / 나의 활동 / 내 게시글
	private Menu createMyPostMenu() {

		MenuGroup myPostMenu = new MenuGroup("내 게시글");
		myPostMenu.add(new MenuItem("조회", "/myPost/list"));
		myPostMenu.add(new MenuItem("상세보기", "/myPost/detail"));
		myPostMenu.add(new MenuItem("수정", "/myPost/update"));
		myPostMenu.add(new MenuItem("삭제", "/myPost/delete"));

		return myPostMenu;
	}

	// 마이 페이지 / 내 관심목록
	private Menu createInterestMenu() {

		MenuGroup interestMenu = new MenuGroup("내 관심목록");
		interestMenu.add(createFreeInterestMenu());
		interestMenu.add(createChargeInterestMenu()); 

		return interestMenu;
	}

	// 마이 페이지 / 내 관심목록 / 무료 스터디 관심목록
	private Menu createFreeInterestMenu() {

		MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");
		freeInterestMenu.add(new MenuItem("조회", "/freeInterest/list"));
		freeInterestMenu.add(new MenuItem("삭제", "/freeInterest/delete"));

		return freeInterestMenu;
	}

	// 마이 페이지 / 내 관심목록 / 유료 스터디 관심목록
	private Menu createChargeInterestMenu() {

		MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");
		chargeInterestMenu.add(new MenuItem("조회", "/chargeInterest/list"));
		chargeInterestMenu.add(new MenuItem("삭제", "/chargeInterest/delete"));

		return chargeInterestMenu;
	}

	// 마이 페이지 / 나의 결제 내역
	private Menu createPaymentListMenu() {

		MenuGroup paymentListMenu = new MenuGroup("나의 결제 내역");
		paymentListMenu.add(new MenuItem("조회", ACCESS_MENTEE, "myPayment/list"));
		paymentListMenu.add(new MenuItem("상세보기", ACCESS_MENTEE, "myPayment/detail"));

		return paymentListMenu;
	}

	// ------------------------------ 관리자 페이지 -----------------------------------------

	// 관리자 페이지 메인
	private Menu createAdminPageMenu() {

		MenuGroup adminPageMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);
		adminPageMenu.add(createMemberManagementMenu());
		adminPageMenu.add(createStudyManagementMenu());
		adminPageMenu.add(createCalendarManagementMenu());

		return adminPageMenu;
	}

	// 관리자 페이지 / 회원 관리
	private Menu createMemberManagementMenu() {

		MenuGroup memberManagementMenu = new MenuGroup("회원 관리");
		memberManagementMenu.add(createMentorApplicantMenu());
		memberManagementMenu.add(createBlackListMenu());

		return memberManagementMenu;
	}

	// 관리자 페이지 / 회원 관리 / 멘토 신청 내역 관리
	private Menu createMentorApplicantMenu() {
		MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");

		mentorApplicantMenu.add(new MenuItem("조회", "/mentorApplicant/list"));
		mentorApplicantMenu.add(new MenuItem("상세보기", "/mentorApplicant/detail"));

		return mentorApplicantMenu;

	}

	// 관리자 페이지 / 회원 관리 / 블랙리스트 관리
	private Menu createBlackListMenu() {

		MenuGroup mentorManagementMenu = new MenuGroup("블랙리스트 관리");
		mentorManagementMenu.add(new MenuItem("", "/"));

		return mentorManagementMenu;
	}

	// 관리자 페이지 / 스터디 관리
	private Menu createStudyManagementMenu() {

		MenuGroup studyManagementMenu = new MenuGroup("스터디 관리");
		studyManagementMenu.add(createDeleteRequestStudyMenu());

		return studyManagementMenu;
	}

	// 관리자 페이지 / 스터디 관리 / 삭제 요청 스터디 관리
	private Menu createDeleteRequestStudyMenu() {

		MenuGroup deletedRequestMenu = new MenuGroup("삭제 요청 스터디 관리");
		deletedRequestMenu.add(new MenuItem("", "/"));

		return deletedRequestMenu;
	}

	// 관리자 페이지 / 캘린더 관리
	private Menu createCalendarManagementMenu() {

		MenuGroup calendarMenu = new MenuGroup("캘린더 관리", ACCESS_ADMIN);
		calendarMenu.add(createJobsCalendarManagementMenu());
		calendarMenu.add(createExamCalendarManagementMenu());

		return calendarMenu;
	}

	// 관리자 페이지 / 캘린더 관리 / 이달의 채용공고 관리
	private Menu createJobsCalendarManagementMenu() {
		MenuGroup jobsCalendarManagementMenu = new MenuGroup("이달의 채용공고");

		jobsCalendarManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsCalendar/add"));
		jobsCalendarManagementMenu.add(new MenuItem("상세보기", "/jobsCalendar/detail"));
		jobsCalendarManagementMenu.add(new MenuItem("변경", ACCESS_ADMIN, "/jobsCalendar/update"));
		jobsCalendarManagementMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/jobsCalendar/delete"));

		return jobsCalendarManagementMenu;
	}

	// 관리자 페이지 / 캘린더 관리 / 이달의 시험일정 관리
	private Menu createExamCalendarManagementMenu() {

		MenuGroup examcalendarManagementMenu = new MenuGroup("이달의 시험일정");
		examcalendarManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examCalendar/add"));
		examcalendarManagementMenu.add(new MenuItem("상세보기", "/examCalendar/detail"));
		examcalendarManagementMenu.add(new MenuItem("변경", ACCESS_ADMIN, "/examCalendar/update"));
		examcalendarManagementMenu.add(new MenuItem("삭제", ACCESS_ADMIN, "/examCalendar/delete"));

		return examcalendarManagementMenu;
	}

	/* 기존 App(09.18 ver) 메뉴

	  private Menu createMyPageMenu() {
	    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

	    myPageMenu.add(new MenuItem("내정보", ACCESS_GENERAL, "/auth/userinfo"));

	    myPageMenu.add(createInterestMenu());
	    myPageMenu.add(createFreeStudyApplyMenu());

	    return myPageMenu;
	  }

	  private Menu createAdminMenu() {
	    MenuGroup adminMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);

	    adminMenu.add(createMemberMenu());
	    adminMenu.add(createMentorApplicantMenu());
	    adminMenu.add(createDeleteRequestStudyMenu());

	    return adminMenu;
	  }

	  private Menu createMemberMenu() {
	    MenuGroup memberMenu = new MenuGroup("회원 관리");

	    memberMenu.add(createMentorApplicantMenu());

	    return memberMenu;
	  }

	  private Menu createMentorApplicantMenu() {
	    MenuGroup mentorApplicantMenu = new MenuGroup("멘토 승인 관리");

	    mentorApplicantMenu.add(new MenuItem("조회", "/mentorApplicant/list"));
	    mentorApplicantMenu.add(new MenuItem("상세보기", "/mentorApplicant/detail"));

	    return mentorApplicantMenu;
	  }

	  private Menu createDeleteRequestStudyMenu() {
	    MenuGroup deletedRequestMenu = new MenuGroup("삭제 요청 스터디 관리");

	    deletedRequestMenu.add(new MenuItem("조회", "/chargeStudy/deleteList"));

	    return deletedRequestMenu;
	  }

	  private Menu createInterestMenu() {
	    MenuGroup interestMenu = new MenuGroup("관심목록", ACCESS_GENERAL);

	    interestMenu.add(createFreeInterestMenu());
	    interestMenu.add(createChargeInterestMenu());

	    return interestMenu;
	  }

	  private Menu createFreeInterestMenu() {
	    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");

	    freeInterestMenu.add(new MenuItem("조회", "/freeInterest/list"));
	    freeInterestMenu.add(new MenuItem("삭제", "/freeInterest/delete"));

	    return freeInterestMenu;
	  }

	  private Menu createChargeInterestMenu() {
	    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");

	    chargeInterestMenu.add(new MenuItem("조회", "/chargeInterest/list"));
	    chargeInterestMenu.add(new MenuItem("삭제", "/chargeInterest/delete"));

	    return chargeInterestMenu;
	  }

	  private Menu createFreeStudyApplyMenu() {
	    MenuGroup freeStudyApplyMenu = new MenuGroup("무료 스터디 신청 내역", ACCESS_GENERAL);

	    freeStudyApplyMenu.add(new MenuItem("조회", "/freeStudyApply/list"));
	    freeStudyApplyMenu.add(new MenuItem("상세보기", "/freeStudyApply/detail"));
	    freeStudyApplyMenu.add(new MenuItem("수정", "/freeStudyApply/update"));
	    freeStudyApplyMenu.add(new MenuItem("삭제", "/freeStudyApply/delete"));

	    return freeStudyApplyMenu;
	  }

	  private Menu createFreeStudyMenu() {
	    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

	    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
	    freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
	    freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
	    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));
	    freeStudyMenu.add(new MenuItem("수정", ACCESS_LEADER, "/freeStudy/update"));
	    freeStudyMenu.add(new MenuItem("삭제", ACCESS_LEADER | ACCESS_ADMIN, "/freeStudy/delete"));

	    return freeStudyMenu;
	  }

	  private Menu createChargeStudyMenu() {
	    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

	    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
	    chargeStudyMenu.add(new MenuItem("멘토 신청", ACCESS_GENERAL, "/mentorApplicant/add"));
	    chargeStudyMenu.add(new MenuItem("생성", ACCESS_MENTOR, "/chargeStudy/add"));
	    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
	    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
	    chargeStudyMenu.add(new MenuItem("수정", ACCESS_MENTOR, "/chargeStudy/update"));
	    chargeStudyMenu.add(new MenuItem("삭제 요청", ACCESS_MENTOR, "/chargeStudy/deleteRequest"));

	    return chargeStudyMenu;
	  }

	  private Menu createCommunityMenu() {
	    MenuGroup communityMenu = new MenuGroup("커뮤니티");

	    communityMenu.add(createCommunityInfoMenu());
	    communityMenu.add(createCommunityQaMenu());
	    communityMenu.add(createCommunityTalkMenu());

	    return communityMenu;
	  }

	  private Menu createCommunityInfoMenu() {
	    MenuGroup communityInfoMenu = new MenuGroup("정보");

	    communityInfoMenu.add(new MenuItem("검색", "/communityInfo/search"));
	    communityInfoMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityInfo/add"));
	    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
	    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));
	    communityInfoMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityInfo/update"));
	    communityInfoMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityInfo/delete"));

	    return communityInfoMenu;
	  }

	  private Menu createCommunityQaMenu() {
	    MenuGroup communityQaMenu = new MenuGroup("질문");

	    communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
	    communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
	    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
	    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));
	    communityQaMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityQa/update"));
	    communityQaMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityQa/delete"));

	    return communityQaMenu;
	  }

	  private Menu createCommunityTalkMenu() {
	    MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

	    communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
	    communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
	    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
	    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));
	    communityTalkMenu.add(new MenuItem("수정", ACCESS_GENERAL, "/communityTalk/update"));
	    communityTalkMenu.add(new MenuItem("삭제", ACCESS_GENERAL | ACCESS_ADMIN, "/communityTalk/delete"));

	    return communityTalkMenu;
	  }
	 */

}

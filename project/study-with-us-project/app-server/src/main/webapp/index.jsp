<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="copyright" content="MACode ID, https://macodeid.com/">

  <title>스터디위더스: 메인</title>

  <base target="_self"/>
 <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
   -->

 <!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet"> -->

  <link rel="stylesheet" href="css/maicons.css">

  <link rel="stylesheet" href="css/bootstrap.css">

  <link rel="stylesheet" href="css/animate.css">

  <link rel="stylesheet" href="css/theme.css">

  <link rel="stylesheet" href="css/footer.css">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">

</head>

<style>

.md-form.mb-5{
  margin:10px;
}

.modal-header{
  display: fixed;
  justify-content: space-evenly;
  align-items: center;
  z-index: 2000 !important;
}

.validate{
  padding: 0px;
  margin:0 auto;
  width:300px;
  height:35px;
}

.join_btn_4_space{
  display: flex;
  text-align:center;
  justify-content: space-evenly;
  margin: 10px;
}

.arrow{
padding:0px;
margin-left:-2.6px;
margin-right:10px;
}

.img_main{
  height:360px;
  width:390px;
  }
  
  .modal1 {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
   z-index: 100 !important;
}

.modal1_overlay {
  position:relative;
  width: 100%;
  height: 100%;
}

.modal1_content {
  position: fixed;
  text-align: center;
  top: 30%;
  left: 30%;
  width: 600px;
  height: 550px;
  background-color: rgb(246, 238, 253);
  padding: 30px 30px;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px
    rgba(0, 0, 0, 0.23);
  z-index: 10 !important;
}

.hidden {
  display: none;
}

/*
.form_box {
  margin : 35px;
}

.hidden {
  display: none;
}

.button_box {
display : flex;
justify-content : flex-end;
margin-right : 5px;
text-align: center;
}

.form_buttons {
  display : flex;
  justify-content: flex-end;
  width : 360px;
}
  */
    .request_button, #close {
      margin-right : 15px;
    }

    input {
    all : unset;
    }

  .btn-primary1{
    background: #756daa;
    color:snow;
    border-radius: 70px;
  }

    .st-sns{
      font-size: 14px;
    }

    .row{
      margin-left:5px;
      margin-right:5px;
      position: relative;
    }
    
    .sns_container{
    display: flex;
    text-align:center;
    justify-content:space-evenly;
    margin-top:15px;
    }

  .title_img{
    margin:30px 10px;
    text-align:center;
    justify-content: center;
  }

  .sign-buttons{
    margin-top: 50%;
  }
</style>

<body>

  <!-- Back to top button -->
  <div class="back-to-top"></div>

  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
      <div class="container">
        <!--<a href="#" class="navbar-brand">스터디<span class="text-primary">위더스</span></a>-->
        <a href="/swu/"><img src="img/swu_text.png" alt="LOGO"></a>

        <div class="navbar-collapse collapse" id="navbarContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <c:if test="${loginUser eq null}">
              <a id="open1" class="nav-link" href="#">Sign Up / In</a>
              </c:if>
              <c:if test="${loginUser ne null}">
              <a id="open1" class="nav-link" href="user/logout">Logout</a>
              </c:if>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="freestudy/list">Study</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="chargestudy/list">Mentoring</a>
              </li>
              
              <c:if test="${loginUser ne null}">
              <c:if test="${loginUser.userAccessLevel eq 32}">
                <a class="nav-link" href="/swu/adminpage">Admin Page</a>
              </c:if>
              <c:if test="${loginUser.userAccessLevel ne 32}">
                <a class="nav-link" href="/swu/mypage">My Page</a>
              </c:if>
              <li class="nav-item">
              </li>
              </c:if>
            </ul>
           </div>

             <!-- 여기 햄버거 & 드롭다운 추가해야 함 // 보류?
                <li class="nav-item">
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation"></button>
                <span class="navbar-toggler-icon"></span>-->
              <!-- ver.bootstrap
             <div class="dropdown">
              <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
                  </button>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <li><button class="dropdown-item" type="button">Account</button></li>
                    <li><button class="dropdown-item" type="button">Account</button></li>
                    <li><button class="dropdown-item" type="button>Account</button></li>
                  </ul>
                </div>
                -->
      </div>
    </nav>

<!-- 대표 메뉴 세 개 나오는 파트-->
    <div class="container">
      <div class="page-banner home-banner">
        <div class="row align-items-center flex-wrap-reverse h-100">
          <div class="col-md-6 py-5 wow fadeInLeft">
            <h1 class="mb-4">Let's Study with Us!</h1>
            <p class="text-lg text-grey mb-5">내가 원하는 분야의 스터디를 한 눈에 살펴보고 자유롭게 참여할 수 있는 스터디위더스 웹 사이트입니다. </p> 
            <p> 그리고 이제 여기 밑에 어쩌고를 기반으로 어쩌고저쩌고 그랬습니다 하면서 우리 프로젝트 소개 이만큼만 적으면 되겠지 아니면 위에 문장이랑 아예 합쳐서 적을까?</p>
            <!-- <a href="#" class="btn btn-primary btn-split">Watch Video <div class="fab"><span class="mai-play"></span></div></a> -->
          </div>
          <div class="col-md-6 py-5 wow zoomIn">
            <div class="img-fluid text-center">
             <img class="img_main" src="img/urban-858.png"></img>
            </div>
          </div>
        </div>
        <!--<a href="#about" class="btn-scroll" data-role="smoothscroll"><span class="icon-arrow-down"></span></a>-->
        <a href="#about" class="btn-scroll" data-role="smoothscroll"><img class="arrow" src="img/down-arrow .png"></img></a>
      </div>
    </div>
  </header>

  <div class="page-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-4">
          <div class="card-service wow fadeInUp">
            <div class="header">
              <!--img src에서 아이콘으로 대체함 (메인 중앙 세 개 아이콘) -->
              <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-building" viewBox="0 0 16 16">
              <path d="M2 11h1v1H2v-1zm2 0h1v1H4v-1zm-2 2h1v1H2v-1zm2 0h1v1H4v-1zm4-4h1v1H8V9zm2 0h1v1h-1V9zm-2 2h1v1H8v-1zm2 0h1v1h-1v-1zm2-2h1v1h-1V9zm0 2h1v1h-1v-1zM8 7h1v1H8V7zm2 0h1v1h-1V7zm2 0h1v1h-1V7zM8 5h1v1H8V5zm2 0h1v1h-1V5zm2 0h1v1h-1V5zm0-2h1v1h-1V3z"/>
              <path fill-rule="evenodd" d="M14.763.075A.5.5 0 0 1 15 .5v15a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5V14h-1v1.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V10a.5.5 0 0 1 .342-.474L6 7.64V4.5a.5.5 0 0 1 .276-.447l8-4a.5.5 0 0 1 .487.022zM6 8.694 1 10.36V15h5V8.694zM7 15h2v-1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5V15h2V1.309l-7 3.5V15z"/>
            </svg>
            </div>
            <div class="body">
              <h5 class="text-secondary">일반기업</h5>
              <p>We help you define your SEO objective & develop a realistic strategy with you</p>
              <a href="service.html" class="btn btn-primary">Read more</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card-service wow fadeInUp">
            <div class="header">
              <svg xmlns="http://www.w3.org/2000/svg" width="50" height="60" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
              </svg>
            </div>
            <div class="body">
              <h5 class="text-secondary">공기업/공무원</h5>
              <p>We help you define your SEO objective & develop a realistic strategy with you</p>
              <a href="service.html" class="btn btn-primary">Read more</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card-service wow fadeInUp">
            <div class="header">
              <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentcolor" class="bi bi-pc-display" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M8 1a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H9a1 1 0 0 1-1-1V1Zm1 13.5a.5.5 0 1 1 1 0 .5.5 0 0 1-1 0Zm2 0a.5.5 0 1 1 1 0 .5.5 0 0 1-1 0ZM9.5 1a.5.5 0 0 0 0 1h5a.5.5 0 0 0 0-1h-5ZM9 3.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5ZM1.5 2A1.5 1.5 0 0 0 0 3.5v7A1.5 1.5 0 0 0 1.5 12H6v2h-.5a.5.5 0 0 0 0 1H7v-4H1.5a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .5-.5H7V2H1.5Z"/>
              </svg></div>
            <div class="body">
              <h5 class="text-secondary">프로그래밍</h5>
              <p>We help you define your SEO objective & develop a realistic strategy with you</p>
              <a href="service.html" class="btn btn-primary">Read more</a>
            </div>
          </div>
        </div>
      </div>
    </div> <!-- .container -->
  </div> <!-- .page-section -->

 
  <div class="page-section" id="about">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 py-3 wow fadeInUp">
          <span class="subhead">About us</span>
          <h2 class="title-section">Team3</h2>
          <div class="divider"></div>

          <p>여기에 이제 프로젝트 설명이 들어갈 예정입니당. 사이트를 왜 만들게 되었는지, 어떤 기능들을 구현했는지 간단하게 적어두면 좋을 섹션인 것 같아</p>
          <p>조원 이름이랑 깃헙 주소를 넣을까 말까 고민이다 안 넣으면 그냥 이렇게 간단한 소개만 넣을까?</p>
          <a href="about.html" class="btn btn-primary mt-3">Read more</a>
        </div>
        <div class="col-lg-6 py-3 wow fadeInRight">
          <div class="img-fluid py-3 text-center">
            <img class="img_sub" src="img/gummy-macbook.png" alt="">
          </div>
        </div>
      </div>
    </div> <!-- .container -->
  </div> <!-- .page-section -->

  <!-- Blog -->
  <div class="page-section">
    <div class="container">
      <div class="text-center wow fadeInUp">
        <h2 class="title-section">스터디위더스 둘러보기</h2>
        <br>
        <div class="subhead">스터디위더스 회원이시라면, 아래 아이콘을 통해 주요기능을 이용해보세요</div>
        <br>
        <div class="divider mx-auto"></div>
      </div>

      <div class="row mt-5">

        <div class="col-lg-4 py-3 wow fadeInUp">
          <div class="card-blog">
            <div class="header">
              <div class="post-thumb">
                <a href="freestudy/list"><img src="img/st-cr.png"></img></a>
              </div>
            </div>
            <div class="body">
              <h5 class="post-title"><a href="freestudy/form">스터디 등록하기</a></h5>
              <div class="post-date">나의 관심 분야로 새로운 스터디 모임을 만들어보세요</div>
            </div>
          </div>
        </div>
        
        
        <div class="col-lg-4 py-3 wow fadeInUp">
          <div class="card-blog">
            <div class="header">
              <div class="post-thumb">
                <a href="community/list?categoryNo=0"><img src="img/cmnt.png"></img></a>
              </div>
            </div>
            <div class="body">
              <h5 class="post-title"><a href="community/list?categoryNo=0">커뮤니티</a></h5>
              <div class="post-date">스터디위더스 회원들과 소통하고 정보를 나누어보세요</div>
            </div>
          </div>
        </div>

        
        <div class="col-lg-4 py-3 wow fadeInUp">
          <div class="card-blog">
            <div class="header">
              <div class="post-thumb">
                <a href="mentorapplication/form"><img src="img/mentor.png"></img></a>
              </div>
            </div>
            <div class="body">
              <h5 class="post-title"><a id= "open" href="#">멘토 신청하기</a></h5>
              <div class="post-date">멘토가 되어 자신 있는 분야의 스터디를 이끌어보세요 </div>
            </div>
          </div>
        </div>
        
        <!-- 
    <div class="modal hidden">
      <div class="modal_overlay">
        <div class="modal_content">
          <div class="form_box">
            <form action='mentorapplication/add' method='post'>
              <input class="form_input_title" type='text' name='selfIntro' size=36
                maxlength=30 placeholder="멘토링 소개에 들어갈 자기소개를 입력하세요.">
              <textarea class="input_content" name="content" id="textarea"
                cols="40" rows="5"></textarea>
              <input class="form_input_title" type='text' name='subject' size=36
                maxlength=30 placeholder="개설할 멘토링 주제를 입력하세요.">
              <textarea class="input_content" name="content" id="textarea"
                cols="40" rows="5"></textarea>
              <div class="form_buttons">
                <input type="submit" onclick="offClick()" value="등록"> 
                <input id="close" type="button" value="취소">
              </div>
            </form>
          </div>
        </div> 
      </div> 
    </div> 
         -->
        <!--
        <div class="col-lg-4 py-3 wow fadeInUp">
          <div class="card-blog">
            <div class="header">
              <div class="post-thumb">
                <img src="img/cal.png">
              </div>
            </div>
            <div class="body">
              <h5 class="post-title"><a href="#">이달의 주요일정</a></h5>
              <div class="post-date">채용공고와 시험일정을 캘린더로 한 눈에 확인해보세요</div>
            </div>
          </div>
        </div>
        -->
        
        <div class="col-12 mt-4 text-center wow fadeInUp">
          <div class="post-date"><br><br><br><br> 더 많은 기능을 이용해볼까요?</div><br>
          <a id="open2" href="#" class="btn btn-primary">Start with Us</a><br><br>
        </div>
      </div>
      <!--풋터-->
      <div class="footer" text-align="center">
        <div>LOGO</div>
        <div>
          스터디위더스 <br>
          Addr. 서울특별시 강남구 역삼동 12-7번지. <br>
          02 - 123 - 4567 <br>
          COPYRIGHT 2021. TEAM3. ALL RIGHT RESERVED.<br>
          
          Illustration by 
          <a href="https://icons8.com/illustrations/author/602b6fa7123f993a3afdba7b"> Victoria Chepkasova</a> from <a href="https://icons8.com/illustrations">Ouch!</a>
        </div>
      </div>

      <!-- ***** 로그인/회원가입 시 모달창 *****-->
      <div class="modal1 hidden">
      <!--모달 활성화 시 흐린 배경 표현-->
      <div class="modal1_overlay">
        <!--모달 화면-->
        <div class="modal1_content">

        <!--  <button type="button" class="close" data-dismiss="modal" aria-lable="close">&times;</button> -->

          <div class="sign-buttons">
  <div class="d-flex justify-content-center">

    <!--회원가입 버튼 -->
    <div class="text-center">
      <a href="" class="btn btn-primary1" data-toggle="modal" data-target="#signupPage">Sign up<i class="fas fa-user-plus ml-3"></i></a>
    </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <!--로그인 버튼-->
    <div class="text-center">
      <a href="" class="btn btn-primary1" data-toggle="modal" data-target="#signinPage">&nbsp;&nbsp;Sign in&nbsp;<i class="fas fa-sign-in-alt ml-3"></i></a>
    </div>
    
  </div>
</div>

        </div> <!-- modal_content -->
      </div> <!-- modal_overlay -->
    </div> <!-- modal_hidden -->
    </div>
  </div>


<div class="modal fade" id="signupPage">
    <div class="modal-dialog">
      <div class="modal-content">
        
        <div class="modal-header text-center">
          <h5 class="modal-title w-100 dark-grey-text font-weight-bold">Sign Up</h5>
          <button type="button" class="close" data-dismiss="modal" aria-lable="close">&times;</button>
        </div>

        <div class="title_img"><img src="img/스터디위더스.png"></img></div>

        <div class="modal-body mx-3">

          <form action="user/join" method="post"><!-- 추가-->
            
            <!--<div class="md-form mb-5">일단-->
            <label data-error="wrong" data-success="right">이름</label>
            <input type="text" name="name" class="form-control validate" placeholder="이름을 입력하세요" required >
          </div>

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">이메일</label>
            <input type="email" name="email" class="form-control validate" placeholder="이메일을 입력하세요" required>
          </div>

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">닉네임</label>
            <input type="text" name="nickname" class="form-control validate" placeholder="닉네임을 입력하세요" required>
          </div>

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">비밀번호</label>
            <input type="password" name="password" class="form-control validate" placeholder="비밀번호를 입력하세요" required>
          </div>

        <!--
          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">비밀번호 확인</label>
            <input type="password" name="password_confirm" class="form-control validate">
          </div>
        -->  

          <div class="md-form mb-5">
            <label data-error="wrong" data-success="right">휴대폰 번호</label>
            <input type="text" name="phoneNumber" class="form-control validate" placeholder="010-1234-5678" required>
          </div>
        <!--</div>주석-->
          
        <div class="modal-footer d-flex justify-content-center">
          <button class="btn btn-primary1">스터디위더스 시작하기</button><br><hr>
        </div>
      </form>

      </div>
    </div>
  </div>
  
<!--LOG IN 누르면 나오는 모달 창 내용들--> 

<div class="modal fade" id="signinPage">
  <div class="modal-dialog">
    <div class="modal-content">
      
      <div class="modal-header text-center">
        <h5 class="modal-title w-100 dark-grey-text font-weight-bold">Sign In</h5>
        <button type="button" class="close" data-dismiss="modal" aria-lable="close">&times;</button>
      </div>

      <div class="title_img"><img src="img/스터디위더스.png"></img></div>
      <!--추가-->
      <div class="modal-body mx-4">

        <form action='user/login' method="post">
        <div class="md-form">
          <label data-error="wrong" data-success="right">이메일</label>
          <input type="email" name='email' class="form-control validate">
        </div>

        <div class="md-form">
          <label data-error="wrong" data-success="right">비밀번호</label>
          <input type="password" name='password' class="form-control validate">
        </div><br>
        
        <div class="text-center mb-3">
           <button class="btn btn-primary1">스터디위더스 시작하기</button><br>
         <!--   <button type="button" class="btn btn-primary1 btn-block z-depth-1a">스터디위더스 시작하기</button><br><hr> -->
          <!--<button type="button" class="btn btn-primary btn-block z-depth-1a">스터디위더스 시작하기</button>-->
        </div>
     </form>
     
         <br><p class="font-small blue-text d-flex justify-content-end"><a href="#" class="blue-text sl-1">아이디</a>
           &nbsp;/&nbsp; <a href="#" class="blue-text sl-1">비밀번호 찾기</a></p><br><hr>

        <div class="st-sns">
        <p class="font-large dark-grey-text d-flex justify-content-center">or</p>
        <p class="font-large dark-grey-text d-flex justify-content-center">SNS로 시작하기</p>
      </div>

        <div class="row my-3 justify-content-center">
        
          <div class="sns_container">
            <div class="sns_icon"> <a><img src="img/facebook.png"></img></a></div>
            <div class="sns_icon"><a><img src="img/twitter.png"></img></a></div>
            <div class="sns_icon"><a><img src="img/google.png"></img></a></div>
            <div class="sns_icon"><a><img src="img/instagram.png"></img></a></div>
        </div><!--container-->
        
        </div><!--row my-3 justify-content-center-->
      </div>
    </div>
    </div> 
  </div>

<script>
const openBtn = document.getElementById('open1');
const openBtn2 = document.getElementById('open2');
//onModal button

const closeBtn = document.getElementById('close1');
//offModal button

const modal = document.querySelector('.modal1');
//HTML에서의 모달 최상위 요소

const overlay = document.querySelector('.modal1_overlay');
//모달창이 활성화되면 흐린 배경을 표현하는 요소

const openModal = () => {
modal.classList.remove('hidden');
}

const closeModal = () => {
modal.classList.add('hidden');
}

openBtn2.addEventListener('click', openModal);
openBtn.addEventListener('click', openModal);
//onModal

closeBtn.addEventListener('click', closeModal);

overlay.addEventListener('click', closeModal);
//모달창 영역 밖
</script>

<script src="js/jquery-3.5.1.min.js"></script>

<script src="js/bootstrap.bundle.min.js"></script>

<script src="js/google-maps.js"></script>

<script src="js/wow.min.js"></script>

<script src="js/theme.js"></script>

  <!-- jQuery -->
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="js/mdb.min.js"></script>
  
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        margin: 0 auto;
        
    }

    .container {
            padding: 10px;
            border: 1px solid;
            width: 300px;
        }

    .header {
        height: 40px;
    }

    .row {
        height: 30px;
    }

    .title {
        width: 70px;
        float: left;
        font-weight: bold;
    }

    .input {
        float: left;
    }

    input[type='submit'] {
        font-weight: bold;
        width: 120px;
        background-color: lightgrey;
    }
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
   
   let isIdCheck = false; 
   // 아래의 ajax요청 후 DB에서 selectMember하여 success:function(res){} 안에서 이 변수에 true를 담아줄것
   // isIdCheck가 true가 되는 조건은 중복체크수행&&중복이아닐때
   // isIdCheck가 false가 되는 조건은 중복체크수행안함or중복체크수행했는데중복일때
   
   
   $(function() {   
      
      // 1. 아이디 중복체크 버튼 비동기-제이쿼리 이용
      $("#idcheck").click(function(){
         $.ajax({
            url:"idcheck",
            type:"post",
            data:{id:$("#id").val()},
            success:function(res) {
               console.log(res);
               console.log(typeof res);
               // res는 서블릿JoinIdCheck.java가 이 ajax요청에 대해 response.getWriter().print(memberService.idCheck(id));로 응답한 값이다
               
               if(res==="notexist") { // js에서는 자바와 달리 문자열 비교에 ==또는===를 사용한다
                  isIdCheck = true;
                  alert("아이디 사용 가능");
               } else {
                  alert("아이디가 중복됩니다.");
               }
               
            },
            error:function(err) {
               console.log(err);
               alert("아이디 중복체크 오류");
            }
         });
      });
      
      // 2. 사용자가 중복체크 수행(하여 통과했을경우 isIdCheck는 true가 되었을것) 이후에 다시 아이디 입력값을 바꾸게될수도 있기 때문에 그때는 false로 재설정해줘야한다
      $("#id").change(function() {
         isIdCheck = false;
      });
      
      
      
      // 3. 사용자가 중복체크 수행하지 않았다면 form의 기본submit동작을 방지한다 
      $("#form").submit(function(e) {
         if(isIdCheck==false) {
            alert("아이디 중복체크를 수행해야하지 않았으므로 form제출 불가합니다.");
            e.preventDefault();
         }
      });
      
   });
</script>
</head>
<body>
<center>
        <div class="header">
            <h3>회원가입</h3>
        </div>
        <div class="container" id='query'>
            <form action="join" method="post" id='form'>
                <div class="row">
                    <div class="title">아이디</div>
                    <div class="input"><input type="text" name="id" id="id" required="required"></div>
                    <%-- 꼭 넣어야하는 값을 required="required"하지 않으면 서블릿에서 에러 발생하게된다 --%>
                    
                    <%-- 아이디 중복체크버튼 비동기 --%>
                    <div class="input"><input type="button" id="idcheck" value="중복"></div>
                </div>
                <div class="row">
                    <div class="title">이름</div>
                    <div class="input"><input type="text" name="name" required="required"></div>
                </div>
                <div class="row">
                    <div class="title">비밀번호</div>
                    <div class="input"><input type="password" name="password" required="required"></div>
                </div>
                <div class="row">
                    <div class="title">이메일</div>
                    <div class="input"><input type="text" name="email"></div>
                </div>
                <div class="row">
                    <div class="title">주소</div>
                    <div class="input"><input type="text" name="address"></div>
                </div>
                <div class="button">
                    <input type="submit" value="회원가입">
                </div>
            </form>
        </div>   
</center>
</body>
</html>
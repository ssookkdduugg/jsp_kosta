<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세</title>
<style type="text/css">
h2 {
   text-align: center;
}
table {
   margin: auto;
   width: 450px;
}
.td_left {
   width: 150px;
   background: orange;
}
.td_right {
   width: 300px;
   background: skyblue;
}
#commandCell {
   text-align: center;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
// 좋아요 버튼 클릭시 작동하는 함수
$(function() {
   $('#heart').click(function() {
      // 비동기ajax통신, 좋아요 아이콘 색변경 
      $.ajax({
         url:'like', // 서블릿url매핑명
         type:'post',
         dataType: 'json', //받는 데이터 
         data:{'num': '<c:out value="${board.num}"/>'},
         success: function(res) { // db에서 가져온 데이터를 res에 담아서 여기서 반영시키는것(뷰 없이 db와 통신하는 것이 비동기방식 통신이다)
         /*   console.log(res.select);
           console.log(res.likecount); */
           if(res.select){
        	   $("#heart").attr("src","image?file=like2.png")
           }else{
        	   $("#heart").attr("src","image?file=like1.png")
           }
           	   $("#likecount").text(res.likecount);
         },
  		error:function(err){
  			console.log(err);
  		}       
      })
   })
})
</script>
</head>
<body>
   <section id="./writeForm">
      <h2>게시판 글 상세</h2>
         <table>
            <tr>
               <td class="td_left"><label for="writer">글쓴이</label></td>
               <td class="td_right"><input type="text" name="writer" id="writer" readonly="readonly" value="${board.writer}"/></td>
            </tr>
            <tr>
               <td class="td_left"><label for="subject">제목</label></td>
               <td class="td_right"><input name="subject" type="text" id="subject" readonly="readonly" value="${board.subject}"/></td>
            </tr>
            <tr>
               <td class="td_left"><label for="content">내용</label></td>
               <td><textarea id="content" name="content" readonly="readonly" cols="40" rows="15">${board.content}</textarea></td>
            </tr>

            
            <tr>
               <td class="td_left"><label for="content">이미지</label></td>
               <%-- 파일이 null이 아닐때만 보여지게 하기 위해서 td태그를 c:if태그로 감싼다 --%>
               <c:if test="${board.fileurl ne null}">
               <td><img src="image?file=${board.fileurl}" width="100px" height="100px"/></td>
               </c:if>
            </tr>
         
         </table>
         <section id="commandCell">
            <%-- 작성자 본인일때만 수정 버튼 노출되도록 c:if태그로 a태그를 감싼다 --%>
            <c:if test="${user.id eq board.writer }">
            <a href="boardmodify?num=${board.num}">수정</a>&nbsp;&nbsp;
            </c:if>
            <a href="boardlist">목록</a>
            좋아요(<span id="likecount">${board.likecount}</span>)&nbsp;&nbsp;
            <c:if test="${user ne Empty }">
            	<c:choose>
            		<c:when  test="${select == true }">
            	 <img id="heart" src="image?file=like2.png" width="20px" height="20px" id="heart"/>
            	 	</c:when>
            	 	<c:otherwise>
            	 	<img id="heart" src="image?file=like1.png" width="20px" height="20px" id="heart"/>
            	 	</c:otherwise>
            	</c:choose>
           
            </c:if>
         </section>
         
         
            <%-- 좋아요 버튼 이미지
            1. cf. main.jsp의 로고이미지 서블릿 get요청 
            img src="image?file=logo.png" width="100px" height="50px"
            2. 로그인 상태에서만 노출되도록 c:if태그로 감싼다
            3. id값을 주고 클릭시 동작하는 js함수 생성
            --%>
         
         

   </section>
</body>
</html>
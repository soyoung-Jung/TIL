# Spring MVC

> MVC 패턴의 목적

스파게티코드처럼 모두 섞지 말고 분리를 시키는 것. = *Seperation of Concerns*

- Model(애플리케이션의 정보)

  : 데이터접근 (DAO, VO, (@Repository) + Busines Logic(@Service) - 자바 클래스로 작성

- View(화면)

  : Presentation Logic - html이나 jsp로 작성(REST 방식 시 필요 x)

- Controller(Model과 View의 중재역할)

  : servelet이 컨트롤러 역할을 한다. (모델2 아키텍처)



> MVC패턴의 흐름

![](.\spring_assets\MVCPatternDetail.PNG)

1. 클라이언트의 요청을 컨트롤러(DispatcherServlet)가 해석해 요청에 맞는 모델의 메소드를 호출한다.
2. 모델이 db에 접근하여 VO객체를 갖고와 결과를 다시 컨트롤러에게 넘겨준다. (ModelAndView를 넘겨준다.)
3. ModelAndView를 바탕으로 컨트롤러는 결과가 뿌려질 뷰에게 결과를 넘겨준다.
4. 뷰는 결과를 화면에 뿌려준다.



>Spring MVC의 주요 구성 요소

### DispatcherServlet

- 설정

  어떤 url요청에 대해 반응할 것인지 설정한다.

  ```xml
  <!-- Map all requests to the DispatcherServlet for handling -->
  	<servlet-mapping>
  		<servlet-name>springDispatcherServlet</servlet-name>
  		<url-pattern>*.do</url-pattern>
  	</servlet-mapping>
  ```

  *ex. do로 끝나는 모든 요청을 처리한다.*

  ![./DispatcherServlet](.\spring_assets\DispatcherServlet.PNG)

- 역할
  1. 스프링에서 제공하는 클래스로, 프론트컨트롤러(제일 먼저 요청을 받아서 처리하는 역할)로서 존재한다. DispatcherServlet은 처리할 요청을 개발자가 작성한 컨트롤러 클래스에게 위임한다. 
  2. 컨트롤러가 요청에 맞는 결과(모델, 즉 VO객체)를 다시 DispatcherServlet에게 주면 그것을 View에게 준다.

### HandlerMapping

```java
@RequestMapping("/getUserList.do")
public String getUsers(Model model) {
		List<UserVO> userList = service.getUserList();
		model.addAttribute("users", userList);
		return "userList";}
```

위와 같이 Controller의 `@RequestMapping("/getUserList.do")`에 따라서 해당 요청과 매핑하는 메소드의 정보를 HandlerMapping이 갖고 있다. DispatcherServlet은 HandlerMapping을 통해 요청에 따른 적절한 메소드를 실행하게 할 수 있다.

### Controller

위와 같이 메소드를 작성하여 요청에 맞게 지정된 뷰와 model에서 가져온 model(userVO객체)를  return하여 다시 DispatcherServlet에게 알려준다.

### ModelAndView

Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체

컨트롤러에 작성된 메소드가 ModelAndView를 리턴한다. ModelAndView를 생성할 때는 아규먼트로 `뷰이름, userVO객체를 접근하는 키값, userVO객체`를 넘겨준다.

```java
@RequestMapping("/getUser.do") 
public ModelAndView getUser(@RequestParam("userid") String id) {
		UserVO user = service.getUser(id);
		return new ModelAndView("userDetail", "uservo", user);
	}
```

- @RequestParam

  요청에 포함된 값을 추출할 때 쓰는 annotation. (=`request.getParameter()`)

  변수명이 요청의 name값과 같다면 괄호안에 아무것도 쓰지 않아도 되지만, 변수명을 다르게 줄 경우 괄호 안에 name값을 주어야 한다.

### ViewResolver

Controller가 리턴한 뷰 이름을 기반으로 Controller 처리 결과를 생성할 뷰를 결정한다.

### View

Controller의 처리 결과 화면에 대한 정보를 보유한 객체. jsp로 작성된다.

```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자관리 메인화면</h2>
	<form method="get" action="getUser.do">
		<input type="text" name="userid"><br/>
		<input type="submit" value="조회">
	</form>
	<hr>
	<a href="getUserList.do">사용자 리스트</a><br/>
	<a href="insertUserForm.do">사용자 등록</a>
</body>
</html>
```



***

> JSTL

```jsp
<body>
	<h2>사용자 리스트</h2>
	<table>
		<tr>
		<th>USERID</th><th>NAME</th>
		</tr>
		<c:forEach var="user" items="${users}"><!--유저스라는키값을갖는애를 치환해줌.. var는 리스트의 값, 즉 객체를 받아오는 변수 -->
			<tr>
				<td><a href="getUser.do?userid=${user.userId}">${user.userId}</a></td><!-- 물음표왼쪽에 있는게 컨트롤러에 파람으로받는 값이랑 같아야함..와이??<a 태그에는 name과 같은 속성이 없어서 이렇게 하는것..? -->
				<td>${user.name}</td>
			</tr>
		</c:forEach>

	</table>
</body>
```

***

session

@ModelAttribute

"redirect:/getUserList.do"
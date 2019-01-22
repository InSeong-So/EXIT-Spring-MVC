# Spring-MVC-basic

## 웹 어플리케이션 구조
> 사용자 요청(브라우저)
>> 프론트 컨트롤러(DispatcherServlet) → 뷰(jsp 파일 등)
>>> 컨트롤러
>>>> 서비스
>>>>> DAO(Data Access Object)
>>>>>> Database

## 서비스 객체 구현

### 방법 1 : new 연산자를 이용한 service 객체 생성 및 참조
```
MemberService service = new MemberService();
```

### 방법 2 : 스프링 설정파일을 이용한 서비스 객체 생성 및 의존 객체 자동 주입
```
<beans:bean id="service" class="com.spring.basic.member.service.MemberService" />
							↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
@Autowired
MemberService service;
```

### 방법3 : 어노테이션을 이용한 서비스 객체 생성 및 의존 객체 자동 주입
```
@Repository("memService")
public class MemberService implements IMemberService{
							↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
@Resource(name="memService")
MemberService service;
```

## DAO 객체 구현

### 어노테이션을 이용한 DAO 객체 생성 및 의존 객체 자동 주입
```
@Repository
public class MemberDao implements IMemberDao{
							↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
@Autowired
MemberDao dao;
```

## 부가 내용

### 한글 처리(web.xml 추가)
```
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>
			org.springframework.web.filter.CharacterEncodingFilter     
		</filter-class>
		<init-param>
			<param-name>encoding</param-name>   
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>  
			<param-value>true</param-value>
		</init-param>
	</filter>    

	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>                 
	</filter-mapping>
```
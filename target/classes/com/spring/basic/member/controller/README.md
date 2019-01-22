## 컨트롤러의 기능

### @RequestMapping을 이용한 URL 매핑

#### 메소드에 @RquestMapping 적용
```
# 개념
	컨트롤러 내부의 세부 기능으로 구분한다.

# 실행 프로세스
	http://locatlhost:8080/basic/memJoin → memJoin() 실행
	
# 사용 방법 1
	@RequestMapping("/memJoin")
	public String memJoin(){
		...
	}
	
# 사용 방법 2
	* 전송방식이 GET 방식일 경우(속성 생략 가능)	
		@RequestMapping(value="memJoin")
		
	* 전송방식이 POST 방식일 경우(속성 반드시 기입)
		@RequestMapping(value="memJoin", method=RequestMethod.POST)
```

#### 클래스에 @RequestMapping 적용
```
# 개념
	기능에 따른 컨트롤러 클래스를 제작하는데 사용한다.

# 예시
	@Controller
	@RequestMapping("/member")
	public class MemberController{
		@RequestMapping(value="/memJoin", method=RequestMethod.POST)
			...
			
		@RequestMapping(value="/memLogin", method=RequestMethod.POST)
			...
	}
	
# mapping URL
	http://localhost:8080/basic/member/memJoin
	http://localhost:8080/basic/member/memLogin
```

<hr>

### 요청 파라미터

#### HttpServletRequest 객체를 이용한 HTTP 전송 정보 얻기
```
# 사용 방법
	public String memJoin(Model model, HttpServletRequest request){
		String memid = request.getParamter("memId");
	}
```

#### @RequestParam 어노테이션을 이용한 HTTP 전송 정보 얻기
```
# 사용 방법
	public String memJoin(Model model, @RequestParam("memId") String memId,
			@RequestParam("memPw") String memPw) {
		String memid = request.getParamter("memId");
	}
```
* @RequestParam의 속성
	* value : 생략해도 된다. (value="")
	* required : 생략해도 된다. (required=true||false), 입력 여부를 정하는 속성이다.
	* default : 생략해 된다. 입력을 하지 않아도 기본적으로 들어가는 값을 지정하는 속성이다.

#### 받아온 전송정보 사용하기
```
# 각 파라미터를 설정하여 사용하는 방법
## Controller 코드
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(Model model, HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		String memMail = request.getParameter("memMail");
		String memPhone1 = request.getParameter("memPhone1");
		String memPhone2 = request.getParameter("memPhone2");
		String memPhone3 = request.getParameter("memPhone3");
		
		service.memberRegister(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
		
		model.addAttribute("memId", memId);
		model.addAttribute("memPw", memPw);
		model.addAttribute("memMail", memMail);
		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
		return "memJoinOk";
	}

## View 코드	
	<h1> memJoinOk </h1>
	ID : ${memId}<br />
	PW : ${memPw}<br />
	Mail : ${memMail} <br />
	Phone : ${memPhone} <br />
	
# 커맨드 객체를 생성하여 사용하는 방법(getter, setter 반드시 필요)
## Controller 코드
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(Member member) {
		
		service.memberRegister(member.getMemId(), member.getMemPw(),
				member.getMemMail(), member.getMemPhone1(), member.getMemPhone2(),
				member.getMemPhone3());
		
		return "memJoinOk";
	}

## View 코드	
	<h1> memJoinOk </h1>
	ID : ${member.memId}<br />
	PW : ${member.memPw}<br />
	Mail : ${member.memMail} <br />
	Phone : ${member.memPhone1}, ${member.memPhone2}, ${member.memPhone3} <br />
```

> 최근엔 개발자의 스트레스를 줄이기 위해 커맨드 객체를 사용하는 방법을 애용한다.

<hr>

### @ModelAttribute

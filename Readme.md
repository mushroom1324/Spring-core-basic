# SPRING 대충 팁
- 생성자 주입 해라.. final 박아서 아예 수정 못하게 막아버리는게 좋음

### 생성자 주입, @Autowired?
- 상황에 따라 맞게 사용하되, 가능한 생성자 주입을 쓰자


#### 초기화, 소멸 인터페이스 단점
- 스프링 전용 인터페이스에 의존하게됨
- 이름 못바꿈 
- 내가 고칠 수 없는 외부 라이브러리에 적용할 수 없음

#### initMethod, destroyMethod
- @Bean(initMethod = "init", destroyMethod = "close")
- 'destroyMethod'는 기본값 (inferred)인데 이는 'close'나 'shutdown' 자동으로 찾아서 소멸자로 해줌

### @PostConstruct, @PreDestroy
- 이게 국룰이다.
- **외부 라이브러리**의 초기화, 소멸은 initMethod, destroyMethod를 사용하자



## Bean Scope

### 프로토타입 빈
- 스프링 DI 컨테이너가 생성 후 의존관계 주입해서 클라이언트한테 던져주고 버림
- @PreDestroy같은 종료 메서드가 호출될리가 없죠

### Provider를 통해 DL(Dependency Lookup)을 할 수 있는데, 그래서 언제 씀?
- 순환 참조 오류 시 .. (A <--> B 서로 의존)
- 프로토타입 빈 .. 거의 사용할 일 없긴 합니다..
- @Lookup 애노테이션 있긴 합니다

## Provider

- request scope bean의 경우 생명주기에 주의
- 만약 서비스/컨트롤러에서 request scope의 bean을 사용할 경우 proxy를 설정하자!!
- @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
- 애너테이션으로 설정할 수 있기 때문에 다형성에 유리하다
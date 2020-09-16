# MyBatis

> 정의

***자바 오브젝트와 SQL문 사이의 자동 Mapping 기능을 지원하는 ORM 프레임워크***

> 장점

- 쉬운 접근성과 코드의 간결함

- SQL문과 프로그래밍 코드의 분리: SQL을 별도의 파일로 분리해서 관리하게 해주어 개발자가 아닌 DBA가 sql문을 수정/유지보수 할 수 있다.
- 다양한 프로그래밍 언어로 구현가능



> MyBatis3과 MyBatis-Spring의 주요 요소

**Oracle JDBC Driver 설정**

**DataSource(javax.sql) 인터페이스**

**MyBatis**

- SqlSessionFactory(SqlSession 생성하는 Factory)

  데이터 베이스의 접속 주소 정보, Mapping파일의 경로 등 환경정보를 설정한다.

- SqlSession

- SqlMapConfig.xml (MyBatis 설정파일)

- Mapping파일

**MyBatis-Spring**

- SqlSessionFactoryBean
- SqlSessionTemplate
- bean.xml

마이바티스를 스프링과 함께 쓰기 위해서 마이바티스의 주요 컴포넌트를 bean.xml에 bean으로 등록하여 객체를 생성한다.



> MyBatis Mapper


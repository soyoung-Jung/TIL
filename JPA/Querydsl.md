# Querydsl

> ### JPQL

- 개념

  SQL과 비슷한 문법의 **객체지향 쿼리** -> 테이블이 아닌 엔터티 객체 대상으로 쿼리

- 탄생 배경

  JPA에서 기본으로 제공하는 메소드 호출만으로는 디테일한 쿼리 작성이 어려웠기 때문에. 복잡한 쿼리문을 만들 때 사용된다.

- 특징

  1. sql을 추상화한 것으로, 특정 벤더에 종속적이지 않다.
  2. JPA는 JPQL을 분석하여 sql생성 후 db에서 조회한다.

- 문법

  *example*

  ```java
  String jpql = "select c from Category c"
  ```

  1. 대소문자 구분

     `Category != category`

  2. 엔터티 이름

     클래스의 이름이 아닌 `@Entity(name = "Category")`로 설정한 Entity 이름.

  3. 별칭

     Entity의 별칭(`c`)은 필수이다.

- 문제점

  1. 문자(string)이기 때문에, Type-safe하지 않음.
  2. Runtime시에 에러가 남.

> ### Querydsl

- 개념

  JPQL의 빌더 역할을 하는 오픈소스.

- 특징

  - 문자가 아닌 코드로 작성 --> 컴파일 시 오류 발견 가능.

- 사용방법

  1. QueryDslPredicateExecutor Interface

     일반적인 CRUD 가능. Like문, and 조건, or조건 등 여러 condition 생성 가능

     ```java
     public interface ItemRepository extends JpaRepository<Item, Long>, QueryDslPredicateExecutor<Item>{
         QItem item = QItem.item;
         Iterable<Item> result = itemRepository.findAll(
         item.name.contains("장난감").and(item.price.between(10000. 20000))
         );
     }
     ```

  2. QueryDslRepositorySupport Interface

     ```java
     package com.github.homework.program.repository;
     
     
     import com.github.homework.program.domain.Program;
     import com.github.homework.program.model.ProgramViewDto;
     import com.querydsl.core.types.Projections;
     import com.querydsl.jpa.JPQLQuery;
     import org.springframework.data.domain.Page;
     import org.springframework.data.domain.Pageable;
     import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
     import org.springframework.data.support.PageableExecutionUtils;
     
     import java.util.Objects;
     
     import static com.github.homework.program.domain.QProgram.program;
     import static com.github.homework.theme.domain.QTheme.theme;
     
     public class ProgramCustomRepositoryImpl extends QuerydslRepositorySupport implements ProgramCustomRepository {
     
         //QuerydslRepositorySupport클래스에는 기본 생성자가 없음.
         public ProgramCustomRepositoryImpl() {
             super(Program.class);
         }
     
         @Override
         public Page<ProgramViewDto> findBy(Pageable pageable) {
             JPQLQuery<ProgramViewDto> query = Objects.requireNonNull(getQuerydsl())
                     .applyPagination(pageable, from(program)
                             .innerJoin(program.theme, theme)
                     ).select(Projections.constructor(ProgramViewDto.class,
                             program.id,
                             program.name,
                             program.introduction,
                             program.introductionDetail,
                             program.region,
                             program.theme.name
                     ));
     
             return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
         }
     }
     ```

- Projection

  entity전체를 가져오는 것이 아닌, 조회 대상을 지정하여 조회할 때 사용.

  특정 DTO를 쿼리의 결과로 가져올 때.

  방법 1. 프로퍼티 이용한 접근 방법

  방법 2. 필드 직접 접근 방법

  **방법 3. 생성자 이용**: `Projections.constructor()`

```java
List<UserContactDto> userContacts = query.from(user)
  .select(Projections.constructor(UserContactDto.class,
    user.name,
    user.mobile,
    user.address))
.fetch();
```

`Projections.constructor()`로 projection할 클래스와, 컬럼을 지정한다. 생성자를 통해 DTO 객체를 생성한다.


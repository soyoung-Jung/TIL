# Persistence Context & Entity Manager

> ### Persistence Context(영속성 컨텍스트)

Entity를 영구히 저장하는 환경. Entity Manager로 Entity를

- 저장- persist()
- 조회 - find()

하면 Entity Manager는 해당 Entity를 영속성 컨텍스트에 보관하고 관리한다.



> ### Entity 생명주기

1. 비영속(new)

   persistence context와 관련 없음. db와 관련없는 순수한 객체.

   ```java
   Member member = new Member();
   ```

2. 영속(managed)

   Entity Manager가 관리하는 persistence context에 Entity가 저장된 상태.

   ```java
   em.persist(member);
   em.find(member.class, 1)
   ```

3. 준영속(detached)

   persistence context에 저장됐다가 분리된 상태. = Entity Manager가 관리하지 않는 상태.

   - 영속 상태 -> 준영속 상태

   ```java
   //방법 1. 특정 Entity만 분리
   em.detach(userA);
   //방법 2. persistence context 초기화
   em.clear();
   //방법 3. persistence context 종료
   em.close();
   ```

4. 삭제(removed)

   persistence context와 DB에서 Entity(객체)삭제.

   ```java
   em.remove(member);
   ```

   
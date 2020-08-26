# 협업도구로써의 Git

## 1) 끝말잇기 - push & pull (다른 사람과)

> 가장 쉬운 협업 방법

> 취약점 

- 동기적으로 처리됨 -> 다른 사람이 끝나야만 내가 쓸 수 있음
- 생각보다 협업 시 말을 많이 해야 함.
- 이 모델로 오픈소스로 할 수 없음. why? 노예가 마스터에 접근시 퍼미션이 있어야 함. 
- 프로젝트에 참여하기 위해서는 master가 manage access로 초대해줘야 함.



## 2) 백일장 - Fork & PR(Pull Request)

> github이 오픈소스의 성지가 될 수 있게 만든 모델

공개된 git을 클론할 수는 있지만 푸쉬하려면 허락을 받아야 함. 이때 포크를 사용!



포크: 원격저장소를 내 저장소에 복제하는 것. 클론과 다른 점. 

원격 저장소에 타겟저장소를 갖고 오는 것. 

포크로 떠온 repository를 내 로컬에 클론을 받음. 수정 한뒤 push하면 내 포크된것에 올라가고, 그 포크된 것을 pull request해서 원 주인에게 가져가라고 요청할수 있음.

## 3) Shared Repo(PR) - Git Flow, Github Flow

실습 - colabo repository 

마스터로 이동해서 pull해야함.. ?!





### `git branch`

현재 저장소에 있는 모든 브랜치 출력. *가 있는 것이 현재 브랜치

### `git branch [브랜치명]`

새로운 branch를 생성

### `git checkout [브랜치명]`

설정한 브랜치로 이동

### `HEAD`

git이 어떤 세계에 있는지 알려주는 것.

### `git merge [브랜치명]`

목적 branch를 병합.

- **(주의)** 병합의 주체가 되는 브랜치로 이동 후 merge한다.

  ex. 마스터가 테스트 브랜치를 머지한다.

   --> master로 이동 후 merge한다!!

???헤드와 포인터 개념 좀 더 익히..

### `git checkout -b [브랜치명]`

t새로운 브랜치를 생성하며 이동

### `git branch -d [브랜치명]`

브랜치를 삭제



### merge 시나리오

#### 1. Fast Forward

![](C:\Users\정소영\Desktop\github_repository\TIL\Git\02_Github_TIL.assets\fast forward.JPG)

??? 브랜치 개념 포인터와 함께 더 ㅏㅈㄹ알기..

포인터가 빨리 감는 것처럼 이동하는 것. 하나의 브랜치에서만 커밋이 쌓인 경우에. 

#### 2. Merge commit (w/o Conflict)

![](C:\Users\정소영\Desktop\github_repository\TIL\Git\02_Github_TIL.assets\merge commit.JPG)

#### 3. Merge Conflict

![](C:\Users\정소영\Desktop\github_repository\TIL\Git\02_Github_TIL.assets\merge conflict.JPG)

conflict가 나지 않게 하는 방법: 같은 파일을 건드리면 안된다.

accept currnet change: 마스터에 있는 걸로 할때

accept incoming change: 테스트2에 있는걸로 할 때

accept both changing: 

- branch는 일회용이다. branch는 재사용하지 않는다.

## 4) 추가적으로 배운 git 명령어

>  지정 파일의 달라진 내용 보기

```bash
git diff README.md
```



> 로컬과 연결한 원격저장소 삭제하기

실수로 주소를 잘못 설정한 경우 원격 저장소를 삭제한다.

```bash
git remote remove origin
```



> git clone시 폴더명 지정

```bash
git clone 원격저장소주소 변경할 폴더명
```

git clone시 원격저장소의 repository이름으로 디렉토리가 만들어진다. 로컬에 이미 해당 디렉토리가 있거나, 자신만의 디렉토리명을 설정하고 싶을 때 clone의 인자값으로 폴더명을 추가한다.



> -u 옵션 설정

```
git push -u origin master 
```

위 코드 사용시 git push만 쳐도 됨. origin master가 기본값이 됨. ?? 한번 더 보기







왜 내 컴퓨터끼리 푸시 풀 되고 

다른 사람일 경우 푸시 풀이 안되나? 주소가 다르기 때문? 생각해보기.. 뭐가 다른지 코드로 주욱 생각해보기

처음부터 끝까지 흐름생각해보기

git log --oneline

git checkout 커밋아이디? & 다른 세계 이름

과거로 이동시, 다른 세계로 이동
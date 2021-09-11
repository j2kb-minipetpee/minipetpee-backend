# 백엔드 협업 규칙

3. back/front repository 분리

## Branch

### Git Flow

1. main: 2주 단위로 develop 브랜치를 머지
2. develop: feature 브랜치 중 리뷰 완료 된 브랜치만 머지
3. feature: 기능 브랜치
4. 그외 hotfix, release 등 

### Naming

예) feature 관련 브랜치일 때: feature/{issueNumber}-feature-name

## Commit

### Commit Message

```
<#[IssueNo]> [Type]: [Subject]
```

ex) <#61> feat: 앨범 게시글 단건 조회

### Type

1. feat: 기능 추가
2. refactor: 기능 변경 없이 리팩토링
3. chore: 사소한 변경 사항
4. fix: 버그 수정
5. test: 테스트 코드
6. docs: 문서 작업  



## Github Project

### Card

이슈 등록 시 프로젝트 태깅하여 프로젝트 카드 추가하기 

### Column

1. To do: 시작 전
2. In Progress: 진행 중
3. In Review: 개발 완료는 했지만 리뷰는 기다리는 중
4. Done: 리뷰 완료 후, develop 브랜치에 머지된 상태
5. Canceled: 중간에 취소된 작업

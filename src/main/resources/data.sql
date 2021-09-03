INSERT INTO member(id, email, password, name, gender, birthday, species, personality, profile_image_url, role, created_at, updated_at, deleted)
VALUES (1, 'test1@naver.com', '$2a$10$Zjpcj391rBzU06CNj083HuDjZFQFqg8QpK/6064nN8eqcJNtsVjB6', '죠르디', 'MALE', '2021-09-15 15:55:58', '공룡', '착하고 순함', 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg', 0, now(), null, false);
INSERT INTO member(id, email, password, name, gender, birthday, species, personality, profile_image_url, role, created_at, updated_at, deleted)
VALUES (2, 'test2@naver.com', '$2a$10$Zjpcj391rBzU06CNj083HuDjZFQFqg8QpK/6064nN8eqcJNtsVjB6', '앙몬드', 'MALE', '2021-09-15 15:55:58', '물개', '게으르고 초콜릿을 좋아함', 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg', 0, now(), null, false);
INSERT INTO member(id, email, password, name, gender, birthday, species, personality, profile_image_url, role, created_at, updated_at, deleted)
VALUES (3, 'test3@naver.com', '$2a$10$Zjpcj391rBzU06CNj083HuDjZFQFqg8QpK/6064nN8eqcJNtsVjB6', '스카피', 'MALE', '2021-09-15 15:55:58', '토끼', '비밀이 많음', 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg', 0, now(), null, false);

INSERT INTO homepee(id, member_id, gate_image_url, title, visit_count) VALUES (1, 1, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg', '죠르디의 취준일기', 350);
INSERT INTO homepee(id, member_id, gate_image_url, title, visit_count) VALUES (2, 2, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg', '앙몬드의 촤컬릿 리뷰', 120);
INSERT INTO homepee(id, member_id, gate_image_url, title, visit_count) VALUES (3, 3, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg', '스카피의 요리비책', 500);

INSERT INTO tab(id, homepee_id, type, visible) VALUES (1, 1, 'BOARD', 1);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (2, 1, 'ALBUM', 1);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (3, 1, 'GUEST', 1);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (4, 2, 'BOARD', 1);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (5, 2, 'ALBUM', 1);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (6, 2, 'GUEST', 0);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (7, 3, 'BOARD', 0);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (8, 3, 'ALBUM', 0);
INSERT INTO tab(id, homepee_id, type, visible) VALUES (9, 3, 'GUEST', 1);

INSERT INTO post(id, tab_id, dtype, title, content, view_count, created_at, updated_at) VALUES (1, 1, 'BOARD', '취준 1일째', '오늘은 알바 월급날. 저는 오늘 사치를 부릴 예정입니다...후후...카드 슬래쉬!!잔액이 부족합니다.', 10, now(), null);
INSERT INTO post(id, tab_id, dtype, title, content, view_count, created_at, updated_at) VALUES (2, 1, 'BOARD', '취준 2일째', '앙몬드가 사탕을 줬어요. 저도 하나 주었죠. 하지만 거부 당했어요...홍삼 사탕 맛을 모르네~', 25, now(), null);
INSERT INTO post(id, tab_id, dtype, title, content, view_count, created_at, updated_at) VALUES (3, 1, 'BOARD', '취준 3일째', '오늘은 영화를 보러 갔어요. 앞자리에 콥이 앉아 있었죠. 그래서 하나도 안 보였어요...ㅠㅠ', 13, now(), null);
INSERT INTO post(id, tab_id, dtype, title, content, view_count, created_at, updated_at) VALUES (4, 2, 'ALBUM', '취준 4일째', null, null, now(), null);
INSERT INTO post(id, tab_id, dtype, title, content, view_count, created_at, updated_at) VALUES (5, 2, 'ALBUM', '취준 5일째', null, null, now(), null);
INSERT INTO post(id, tab_id, dtype, title, content, view_count, created_at, updated_at) VALUES (6, 2, 'ALBUM', '취준 6일째', null, null, now(), null);

INSERT INTO image(id, post_id, url) VALUES (1, 1, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (2, 2, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (3, 4, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (4, 4, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (5, 4, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (6, 5, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (7, 5, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (8, 6, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');
INSERT INTO image(id, post_id, url) VALUES (9, 6, 'http://image.dongascience.com/Photo/2017/03/14900752352661.jpg');

INSERT INTO comment(id, member_id, post_id, content, created_at, updated_at) VALUES (1, 2, 1, '누가 월급 가져갔엉', now(), null);
INSERT INTO comment(id, member_id, post_id, content, created_at, updated_at) VALUES (2, 3, 1, '...ㅠ', now(), null);
INSERT INTO comment(id, member_id, post_id, content, created_at, updated_at) VALUES (3, 2, 4, '저 쵸코 맛있겠당...', now(), null);

INSERT INTO guest_note(id, member_id, tab_id, content, visible, created_at, updated_at) VALUES (1, 2, 3, '잘 봤엉', 1, now(), null);
INSERT INTO guest_note(id, member_id, tab_id, content, visible, created_at, updated_at) VALUES (2, 3, 3, '...bb', 0, now(), null);

INSERT INTO fan_comment(id, member_id, homepee_id, content, created_at, updated_at) VALUES (1, 2, 1, '다시 생각해도 홍삼 사탕은 맛없엉', now(), null);
INSERT INTO fan_comment(id, member_id, homepee_id, content, created_at, updated_at) VALUES (2, 3, 1, '내일 알바 날이다.', now(), null);

INSERT INTO star(id, star_member_id, fan_member_id, created_at) VALUES (1, 1, 2, now());
INSERT INTO star(id, star_member_id, fan_member_id, created_at) VALUES (2, 1, 3, now());
INSERT INTO star(id, star_member_id, fan_member_id, created_at) VALUES (3, 2, 1, now());
INSERT INTO star(id, star_member_id, fan_member_id, created_at) VALUES (4, 3, 1, now());
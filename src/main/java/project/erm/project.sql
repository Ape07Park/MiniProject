SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS BoardAuction;
DROP TABLE IF EXISTS BoardBuy;
DROP TABLE IF EXISTS Community;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE BoardAuction
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	applTime datetime NOT NULL,
	nickName varchar(16),
	processTitle varchar(64),
	processContent varchar(256),
	avgPrice int,
	numOfCompany int,
	process int,
	isDeleted int,
	PRIMARY KEY (bid)
);


CREATE TABLE BoardBuy
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	applTime datetime NOT NULL,
	nickName varchar(16),
	processTitle varchar(64),
	processContent varchar(256),
	avgPrice int,
	numOfCompany int,
	process int,
	isDeleted int,
	PRIMARY KEY (bid)
);



CREATE TABLE BoardAdvice

(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	title varchar(256) NOT NULL,
	content varchar(4000),
	modTime datetime DEFAULT CURRENT_TIMESTAMP,
	isDeleted int DEFAULT 0,
	viewCount int DEFAULT 0,
	replyCount int DEFAULT 0,
	PRIMARY KEY (bid)
);


CREATE TABLE equipment
(
	inum int NOT NULL AUTO_INCREMENT,
	ename varchar(64) NOT NULL,
	eContent varchar(256) NOT NULL,
	
	PRIMARY KEY (inum)
);


CREATE TABLE users
(
	uid varchar(12) NOT NULL,
	pwd char(60) NOT NULL,
	uname varchar(16) NOT NULL,
	email varchar(32),
	nickName varchar(16),
	regDate date DEFAULT (CURRENT_DATE),
	isDeleted int DEFAULT 0,
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE BoardAuction
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE BoardBuy
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



ALTER TABLE BoardAdvice

	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


-- user 데이터

INSERT INTO users (uid, pwd, uname, nickName, email) VALUES
('james', '1234', '제임스', '아름', 'asd@gmail.com'),

('maria', '1234', '마리아', '성민', 'maria@naver.com'),

('lily ', '1234', '릴리', '용현', ‘lily@gmail.com’),
('eunice', '1234', '유니스', '윤주', 'eunice@gmail.com'),
('lion', '1234', '라이언', '시표', 'lion@naver.com'),
('aron', '1234', '아론', '해인', 'aron@kakao.com'),
('soon', '1234', '순', '순현', 'soon@korea.kr'),
('yong', '1234', '용', '강성', 'yong@naver.com'),
('hanna', '1234', '한나', '햇님', 'hanna@hanmail.com'),
('nick', '1234', '닉', '성한', 'nick@gmail.com'),
('crystal', '1234', '크리스탈', '시연', 'crystal@kakao.com'),
('amy', '1234', '에이미', '기준', 'amy@nate.com'),
('hershey', '1234', '허쉬', '동호', 'hershey@kakao.com'),
('kate', '1234', '케이트', '주영', 'kate@gamil.com'),
('lotso', '1234', '랏소', '병학', 'lotso@naver.com'),
('buz', '1234', '버즈', '영준', 'buz@kakao.com'),
('woody', '1234', '우디', '도현', 'woody@gmail.com'),
('elizabeth', '1234', '엘리자베스', '기주', 'elizabeth@gamil.com'),
('rihanna', '1234', '리한나', '희재', 'rihanna@kakao.com'),
('rebecka', '1234', '레베카', '철기', 'rebecka@instagram.com');

-- boardauction
INSERT boardauction(uid, processTitle, processContent) VALUES
('maria', '이 제품 사이즈 있을까요?', '살로몬 스키 바이저 헬멧 사이즈 문의 드려요'),
('lily', '스미스 고글 최저가', '스미스 고글 2223 제일 저렴하게 사고 싶어요'),
('eunice', '스키복 커플로 사려는데 추천좀', '뭐가 좋을까요 추천 받을게요'),
('lion', '피셔 스키폴 재고 확인', '찾으려는 스키폴 재고가 있나요? '),
('aron', '5살 여아가 입으려는데', '괜찮은 스키복 알려주세요~'),
('soon', '컴퍼델 보호대 말인데요', '승마조끼로도 사용 가능한가요?'),
('yong', '2122 아토믹 스키부츠 ', '추가 할인은 없는건가요?'),
('hanna', '2021 루디스 스키장갑 색상', '하얀색상은 없을까요?'),
('nick', '살로몬 보드 부츠', '230은 품절이에요?'),
('crystal', '컴퍼델 문의', '바스켓 판매 문의요'),
('amy', '초3 남아 스키부츠', '초3남아 스키부츠 추천해 주세요^^'),
('hershey', '블리자드 스키', 'a/s 어디서 할 수 있습니까?'),
('kate', '끝내주는 보드 추천좀요', '제목 그대로요'),
('lotso', '우벡스 주니어 아동 바라클라바', '기모 있는건가요'),
('buz', '지로 아동용 고글 ', '초4인데 사이즈 맞을까요?'),
('woody', '아토믹 ATOMIC X16', '호환 문의 드려요'),
('elizabeth', '온요네 스키복 2324', 's사이즈 있는지 알고 싶어요'),
('rihanna', '로시놀 스키복', '170/80인데 사이즈 뭘로 사야 할까요?'),
('rebecka', '피닉스 주니어 아동 스키복 세트', '최저가로 사고 싶은데요');


-- equipment 데이터

INSERT equipment(category, ename, eContent) VALUES
(’스키’, '스키 가방', '스키를 탈 때 필요한 여러 가지 용품을 담아 가지고 다니기 편리하게 만든 가방'),
('보드', '보드 데크', '스노우 보드의 판'),
('보드', '보드 바인딩', '보드판에 신을 고정하기 위한 죔쇠'),
('보드', '보드 부츠', '스노우보드에 사용되는 부드러운 플렉스(강도)의 신발'),
('보드', '보드세트', '스노우 보드의 도구나 옷 따위의 한 벌'),
('보드', '보드 자켓', '스노우 보드 활동시에 입는 겉옷'),
('보드', '보드 팬츠', '스노우 보드 활동시에 입는 바지'),
('기타 장비', '비니', '모자,체온 유지를 위해 스키장에서는 비니를 많이 쓴다'),
('스키', '스키 고글', '먼지나 강한 빛 따위로부터 눈을 보호하는 데 쓰는 안경'),
('스키', '스키 미들러+이너', '겉옷 이외에 안에 입는 것 '),
('스키', '스키 바인딩', '스키화의 바닥에 스키를 부착시키는 도구'),
('스키', '스키 부츠', '스키 전용의 부츠'),
('스키', '스키 자켓+팬츠', '스키장에서 입는 재킷과 바지'),
('스키', '스키 장갑', '스키를 탈 때 끼는 장갑.'),
('스키', '스키 판초', '커다란 천 가운데 머리를 내놓는 구멍만 있는 일종의 외투'),
('스키', '스키 헬멧', '머리를 충격으로부터 보호하기 위하여 쓰는 모자'),
('스키', '스키폴', '손잡이가 있는 긴 막대, 균형을 유지하기 위해 사용한다'),
('스키', '스키 후드', '머리 부분을 덮는 쓰개. 방한복이나 비옷 따위에 달려 있다'),
('기타 장비', '장비관리용품', '스키 관련 용품, 안전하게 보호해 주는 용품'),
('스키', '스키복 세트', '스키복 상의,하의의 한 벌');



-- boardadvice
INSERT boardadvice(uid, title, content) VALUES
('james', '내일 휘닉스파크 사람 많을까요?', '성수기는 아니니까 기대중이긴 한데,,'),
('maria', '당일치기로 갈 건데요', '어디 스키장이 좋을까요?'),
('lily', '용평 일일락카', '구할 수 있을까요? 짐 맡기고 싶은데 ㅜㅜ'),
('lion', '카빙 잘 못 타는 카빙인이 되었습니다', '더 연습이 필요하네요'),
('aron', '이틀 렌탈비...', '차라리 장비 구입하세요......'),
('soon', '아이 장갑을 잃어버렸어요', '분실물 센터에 일단 가봐야 하겠죠?'),
('yong', '힐링이 필요할때~~~', '보드 타면서 힐링합시다~'),
('hanna', '원없이 탔다 생각했는데… ', '또 타고 싶어요 중독인가요 ㅎㅎ'),
('nick', '점메추', '뭐 먹을까요 아무거나 추천좀요'),
('crystal', '스키장 가는 꿈을 꿨어요', '참나,,,'),
('amy', '가족끼리 보드 타러 가요', '신난다~~~! 너무 좋네요'),
('hershey', '초보 보더입니다', '스키장 추천 부탁 드려요'),
('kate', '저 오늘 생일이에요 ㅎㅎ', '축하해 주세요!'),
('lotso', '아이와 탈 때는 아이 실력에 맞는 슬로프를 이용합시다!', '사고 위험이 크잖아요'),
('buz', '스키vs보드', '뭐가 더 재밌나요?'),
('woody', '스키복이요 브랜드', '추천 부탁 드립니다'),
('elizabeth', '혹시 같이 스키 타실 분?', '있나요?'),
('rihanna', '체력이 바닥인가봐요ㅜㅜ', '1시간도 안 탔는데 힘드네요'),
('rebecka', '지산.. 지금 갈까요 야간에 갈까요ㅜ', '야간에 타는 맛도 있는데..'),

('eunice', '주차장이 아주 엉망이네요', '매너 주차 합시다...');


-- boardbuy 데이터

INSERT boardbuy(uid, nickName, processTitle, processContent) VALUES
('james', '아름', '카스크 헬멧의 바이저를 별도로..','별도 구매가 되나요? 기스가 많이 생겨서요'),
('maria', '성민', '아이디원 모글 스키','현금가 할인 있나요?'),
('lily', '용현', '렌탈 1박2일은 없나요?','1박 2일로 하고 싶은데'),
('eunice', '유니스', '아토믹 스키 헬멧','헬멧 사이즈 큰 건 없나요?'),
('lion', '라이언', '사이즈 문의','M사이즈면 95인가요?'),
('aron', '아론', '발송 문의','주문한 것들 내일 발송 되죠? '),
('soon', '순현', '스미스 스키 고글 1819','안경 착용 상태에서 써도 되나요?'),
('yong', '강성', '로시놀 스키 휠백 가방','재입고 언제 되나요?'),
('hanna', '햇님', '해머 바이저 헬멧 구입시','사각의 케이스 함께 오는 거죠?'),
('nick', '성한', '바이저에 달린 고글 렌즈',' 따로 구매 못하나요?'),
('crystal', '시연', '아토믹 뵐클 월드컵모델 ㅠㅠ ','구매 가능한 건가요?'),
('amy', '기준', '로시놀 스키복 2021 ','빨간색 구매하고 싶습니다'),
('hershey', '동호', '헬멧 내피 구매 가능 여부 ','알고 싶습니다'),
('kate', '주영', '데상트 17/18 여성 스키 바지 ','상세 사이즈 알고 싶습니다^^'),
('lotso', '병학', '몬타나 스포츠 스키 가방','재고 있을까요?'),
('buz', '영준', '피셔 여성 스키 2021','올라운드 스키 인가요?'),
('woody', '도현', '피오씨 스키 보드헬멧 OBEX PURE','화이트 있나요?'),
('elizabeth', '기주', '피오씨 스키 보드고글 2223 ','미러 없는 블랙렌즈인가요?'),
('rihanna', '희재', '아토믹 바이저 헬멧','L사이즈가 있는겁니까?'),
('rebecka', '철기', '우벡스 바라클라바','성인용은 없는지요?');


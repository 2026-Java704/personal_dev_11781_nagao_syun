

-- users テーブルにデータを挿入するクエリ
INSERT INTO users ( email, name, password)
VALUES
( 'tanaka@aaa.com', '田中太郎', 'test123'),
( 'suzuki@aaa.com', '鈴木一郎', 'test456'),
( 'satou@aaa.com', '佐藤二郎', 'test789');




--categories テーブルにデータを挿入するクエリ
INSERT INTO categories(name) VALUES('運動');
INSERT INTO categories(name) VALUES('勉強');
INSERT INTO categories(name) VALUES('その他');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (  user_id, category_id,title, closing_date, progress, memo)
VALUES
( 1, 1, '散歩', '2026/5/18', 0, '最低20分以上散歩する'),
( 2, 2, '数学', '2026/5/19', 0, '最低20分以上勉強する'),
( 3, 3, '掃除','2026/5/20', 0, '家に帰ってすぐ掃除する');



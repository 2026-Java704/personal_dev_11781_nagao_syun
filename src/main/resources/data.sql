

-- users テーブルにデータを挿入するクエリ
INSERT INTO users ( email, name, password)
VALUES
( 'satou@aaa.com', '佐藤太郎', 'test123'),
( 'suzuki@aaa.com', '鈴木一郎', 'test234');





--categories テーブルにデータを挿入するクエリ
INSERT INTO categories(name) VALUES('運動');
INSERT INTO categories(name) VALUES('勉強');
INSERT INTO categories(name) VALUES('その他');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (  user_id, category_id,title, closing_date, progress, memo)
VALUES
( 1, 1, '散歩', '2026/10/1', 0, '最低5分以上は散歩する'),
( 1, 2, '数学', '2026/5/25', 0, '最低20分以上勉強する'),
( 1, 3, '掃除','2026/5/27', 0, '家に帰ってすぐ掃除する'),
( 2, 1, '散歩', '2026/6/1', 0, '最低5分以上は散歩する'),
( 2, 2, '数学', '2026/5/19', 0, '最低20分以上勉強する'),
( 2, 3, '掃除','2026/5/20', 0, '家に帰ってすぐ掃除する');





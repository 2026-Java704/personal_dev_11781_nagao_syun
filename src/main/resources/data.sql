

-- users テーブルにデータを挿入するクエリ
INSERT INTO users ( email, name, password)
VALUES
( 'tanaka@aaa.com', '田中太郎', 'test1'),
( 'suzuki@aaa.com', '鈴木一郎', 'test2'),
( 'satou@aaa.com', '佐藤二郎', 'test3'),
( 'aoyama@aaa.com', '青山三郎', 'test4');




--categories テーブルにデータを挿入するクエリ
INSERT INTO categories(name) VALUES('運動');
INSERT INTO categories(name) VALUES('勉強');
INSERT INTO categories(name) VALUES('その他');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (  user_id, category_id,title, closing_date, progress, memo)
VALUES
( 1, 1, '散歩', '2026/6/1', 0, '最低5分以上は散歩する'),
( 2, 2, '数学', '2026/5/19', 0, '最低20分以上勉強する'),
( 3, 3, '掃除','2026/5/20', 0, '家に帰ってすぐ掃除する'),
( 4, 1, '散歩', '2026/5/30', 0, '最低20分以上は散歩する');




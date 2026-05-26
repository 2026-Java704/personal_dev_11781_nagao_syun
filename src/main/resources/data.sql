

-- users テーブルにデータを挿入するクエリ
INSERT INTO users (email, name, password) VALUES
('satou@aaa.com', '佐藤太郎', 'test123'),
('suzuki@aaa.com', '鈴木一郎', 'test456');

INSERT INTO categories (user_id, name) VALUES
(0, '運動'),
(0, '勉強'),
(0, 'その他');






-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (category_id,user_id, title, closing_date, progress, memo)
VALUES
( 1, 1, '散歩', '2026/10/1', 0, '最低5分以上は散歩する'),
( 2, 1, '数学', '2026/5/25', 0, '最低20分以上勉強する'),
( 3, 1, '掃除','2026/5/27', 0, '家に帰ってすぐ掃除する'),
( 1, 2, '散歩', '2026/6/1', 0, '最低5分以上は散歩する'),
( 2, 2, '数学', '2026/5/19', 0, '最低20分以上勉強する'),
( 3, 2, '掃除','2026/5/20', 0, '家に帰ってすぐ掃除する');





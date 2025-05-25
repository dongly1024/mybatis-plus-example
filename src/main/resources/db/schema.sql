DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记：0-未删除，1-已删除',
    PRIMARY KEY (id)
);
drop table material_relation;
create table material_relation
(
    id         bigint       not null,
    code       varchar(32)  not null,
    relation   varchar(512) not null,
    is_deleted tinyint      not null default 0,
    primary key pk_id (id),
    index idx_code (code),
    index idx_relation (relation)
);
INSERT INTO tiger.material_relation (id, code, relation, is_deleted) VALUES (1, 'A', 'A,B', 0);
INSERT INTO tiger.material_relation (id, code, relation, is_deleted) VALUES (2, 'B', 'A,B', 0);
INSERT INTO tiger.material_relation (id, code, relation, is_deleted) VALUES (3, 'C', 'C,D,E,F', 0);
INSERT INTO tiger.material_relation (id, code, relation, is_deleted) VALUES (4, 'D', 'C,D,E,F', 0);
INSERT INTO tiger.material_relation (id, code, relation, is_deleted) VALUES (5, 'E', 'C,D,E,F', 0);
INSERT INTO tiger.material_relation (id, code, relation, is_deleted) VALUES (6, 'F', 'C,D,E,F', 0);
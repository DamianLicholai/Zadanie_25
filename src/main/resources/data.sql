Create table TASK
(
    ID          Long PRIMARY KEY Auto_increment,
    title       varchar,
    description varchar,
    status      varchar,
    category    varchar
);

INSERT INTO TASK (title, description, status, category)
VALUES ('Zrobić zakupy', 'Lista zakupów jest na lodówce', 'DONE', 'HOME'),
       ('Zrobić zadanie na Bootcamp', 'Materiał z baz relacyjnych, pamiętaj o DTO', 'DONE', 'WORK'),
       ('Zrobić Obiad', 'najlepiej schabowe', 'TODO', 'HOME');

INSERT ALL
   INTO GROUPS (NAME) VALUES ('КМ-71')
   INTO GROUPS (NAME) VALUES ('КМ-11')
   INTO GROUPS (NAME) VALUES ('КМ-21')
   INTO GROUPS (NAME) VALUES ('КМ-31')
SELECT 1 FROM DUAL;

--Crшterias
INSERT ALL
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Постановка задачі', '10')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Вибір методу', '10')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Матмодель', '13')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Експерементальна перевірка', '13')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Програмна реалізація', '13')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Пояснювальна записка', '8')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Презентація', '14')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Доповідь','10')
   INTO CRITERIAS (TITLE, MAX_POINT) VALUES ('Дискусія', '9')
SELECT 1 FROM DUAL;


-- students
INSERT ALL
  INTO USERS(LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, SUR_NAME, TYPE, FACULTY_GROUP, PHONE, EMAIL) VALUES ('student1','student1','Надія','Кузенко','Юріївна','студент', 'КМ-71', '(093)-12-345-67', 'test1@test.test')
  INTO USERS(LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, SUR_NAME, TYPE, FACULTY_GROUP, PHONE, EMAIL) VALUES ('student2','student2','Аня','Сахно','Андріївна','студент', 'КМ-11', '(093)-11-223-36', 'test2@test.test' )
SELECT 1 FROM DUAL;

-- teachers

INSERT ALL
  INTO USERS(LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, SUR_NAME, TYPE, PHONE, EMAIL) VALUES ('teacher1','teacher1','Ігор','Терещенко','Олександрович','викладач', '(093)-55-667-79', 'test3@test.test')
  INTO USERS(LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, SUR_NAME, TYPE, PHONE, EMAIL) VALUES ('teacher2','teacher2','Людмила','Ковальчук-Хімюк','Олександрівна','викладач', '(093)-44-555-66', 'test4@test.test')	
SELECT 1 FROM DUAL;

-- adminss
INSERT ALL
  INTO USERS(LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, SUR_NAME, TYPE, PHONE, EMAIL) VALUES ('admin1','admin1','Ігор','Терещенко','Олександрович','адмін', '(067)-77-888-66', 'test5@test.test')
SELECT 1 FROM DUAL;
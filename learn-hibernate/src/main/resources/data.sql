insert into course(id,name,last_Updated_Date, creation_Date) 
values(101,'Learn Hibernate',current_timestamp(),current_timestamp());
insert into course(id,name,last_Updated_Date, creation_Date) 
values(102,'Learn SQL',current_timestamp(),current_timestamp());
insert into course(id,name,last_Updated_Date, creation_Date) 
values(103,'Learn JUnit',current_timestamp(),current_timestamp());
insert into course(id,name,last_Updated_Date, creation_Date) 
values(104,'Learn Mockto',current_timestamp(),current_timestamp());
insert into course(id,name,last_Updated_Date, creation_Date) 
values(105,'Learn Java',current_timestamp(),current_timestamp());

insert into passport(passport_id,number)
values(301,'E12345');
insert into passport(passport_id,number)
values(302,'E15345');
insert into passport(passport_id,number)
values(303,'E12365');

insert into student(student_id,name,age,passport_passport_id)
values(201,'Divyanshu',25,301);
insert into student(student_id,name,age,passport_passport_id)
values(202,'Vikas',25,302);
insert into student(student_id,name,age,passport_passport_id)
values(203,'Anuj',50,303);

insert into review(review_id,rating,description,course_id)
values(401,'5 star','Awesome',101);
insert into review(review_id,rating,description,course_id)
values(402,'4 star','Good',101);
insert into review(review_id,rating,description,course_id)
values(403,'3 star','Average',102);

insert into student_course(student_id,course_id)
values(201,101);
insert into student_course(student_id,course_id)
values(201,102);
insert into student_course(student_id,course_id)
values(202,103);
insert into student_course(student_id,course_id)
values(203,103);
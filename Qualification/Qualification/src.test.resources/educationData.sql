drop table if exists education CASCADE 
drop sequence if exists qualificaiton_seq
create sequence qualificaiton_seq start with 1 increment by 1
create table education (eid integer not null, qualificaiton varchar(255), school_college varchar(255), specialization varchar(255), user_unique_id varchar(255) not null, year_of_pass date, primary key (eid))

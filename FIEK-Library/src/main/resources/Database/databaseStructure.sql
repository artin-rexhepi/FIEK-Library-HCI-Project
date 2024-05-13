CREATE DATABASE LibraryManagementSystem;

USE LibraryManagementSystem;




CREATE TABLE users (
id int primary key auto_increment,
username VARCHAR(20) NOT NULL,
salt VARCHAR(100) NOT NULL,
passwordHash VARCHAR(256) NOT NULL
);




CREATE TABLE  Book(
isbn varchar(200) not null,
title varchar(200) not null,
author varchar(200) not null,
publisher varchar(200) not null,
genre varchar(200) not null,
quantity int not null,
isAvailable boolean default true,
primary key(isbn));

CREATE TABLE  Member(
memberid varchar(200) not null,
name varchar(200) not null,
email varchar(200) not null ,
phone varchar(200)not null ,
gender enum('female','male') not null,
primary key(Memberid));

CREATE TABLE  issuedBooks(
isbn varchar(200) not null,
memberID varchar(200) not null,
issueTime timestamp default CURRENT_TIMESTAMP,
renew_count integer default 0,
primary key(isbn,memberID),
foreign key(isbn) references Book(isbn),
foreign key(Memberid) references Member(Memberid));
create database LibraryManagementSystem;

use LibraryManagementSystem;

CREATE TABLE Author (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(255),
    description TEXT
);

CREATE TABLE Book 	(
    ISBN VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255),
    subject VARCHAR(100),
    publisher VARCHAR(100),
    publicationDate DATE,
    language VARCHAR(50),
    numberOfPages INT,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES Author(id)
);
CREATE TABLE Rack (
    id INT PRIMARY KEY auto_increment,
    locationIdentifier VARCHAR(255)
);

CREATE TABLE BookItem (
    barcode VARCHAR(20) PRIMARY KEY,
    ISBN VARCHAR(20),
    isReferenceOnly BOOLEAN,
    borrowed DATE,
    dueDate DATE,
    price DOUBLE,
    format VARCHAR(50),
    status VARCHAR(50),
    dateOfPurchase DATE,
    rack_id INT,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
    FOREIGN KEY (rack_id) REFERENCES Rack(id)
);



CREATE TABLE Account (
    id INT PRIMARY KEY auto_increment,
    password VARCHAR(255) not null,
    status VARCHAR(50),
    person_name VARCHAR(255) not null,
    email VARCHAR(255) UNIQUE not null,
    phone VARCHAR(50) not null
);

CREATE TABLE Member (
    id INT PRIMARY KEY auto_increment,
    account_id INT UNIQUE,
    dateOfMembership DATE,
    totalBooksCheckedOut INT,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Librarian (
    id INT PRIMARY KEY auto_increment,
    account_id INT UNIQUE,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE LibraryCard (
    cardNumber VARCHAR(20) PRIMARY KEY,
    issuedAt DATE,
    isActive BOOLEAN,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE BookReservation (
    id INT PRIMARY KEY auto_increment,
    creationDate DATE,
    status VARCHAR(50),
    account_id INT,
    book_item_barcode VARCHAR(20),
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (book_item_barcode) REFERENCES BookItem(barcode)
);

CREATE TABLE BookLending (
    id INT PRIMARY KEY auto_increment,
    creationDate DATE,
    dueDate DATE,
    returnDate DATE,
    book_item_barcode VARCHAR(20),
    account_id INT,
    FOREIGN KEY (book_item_barcode) REFERENCES BookItem(barcode),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Fine (
    id INT PRIMARY KEY auto_increment,
    amount DOUBLE
);
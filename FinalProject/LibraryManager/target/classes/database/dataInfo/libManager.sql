CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    [password] NVARCHAR(255) NOT NULL,
    UserType INT NOT NULL CHECK (UserType IN (0, 1, 2)) -- 0: reader, 1: librarian, 2: admin
);


CREATE TABLE Books (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title NVARCHAR(100) NOT NULL,
    Author NVARCHAR(100) NOT NULL,
    Genre NVARCHAR(50),
    ISBN NVARCHAR(13) UNIQUE NOT NULL,
    Available INT NOT NULL, -- = 0: not available, > 0: available
    BookType BIT NOT NULL, -- 0: printed book, 1: ebook
    NumberOfPages INT, -- only when booktype = 0
    FileFormat NVARCHAR(20) --only when booktype = 1
);


CREATE TABLE BorrowedBooks (
    BorrowID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    BookID INT NOT NULL,
    BorrowDate DATE NOT NULL,         
    ReturnDate DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

drop table BorrowedBooks;
drop table Books;
drop table Users;
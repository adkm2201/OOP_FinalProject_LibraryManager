CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    PasswordHash NVARCHAR(255) NOT NULL,
    FullName NVARCHAR(100),
    UserType INT NOT NULL CHECK (UserType IN (0, 1, 2))
);


CREATE TABLE Books (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Author NVARCHAR(255) NOT NULL,
    Genre NVARCHAR(100),
    ISBN NVARCHAR(13) UNIQUE NOT NULL,
    AvailabilityStatus BIT NOT NULL,
    BookType BIT NOT NULL,
    NumberOfPages INT,
    FileFormat NVARCHAR(50)
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
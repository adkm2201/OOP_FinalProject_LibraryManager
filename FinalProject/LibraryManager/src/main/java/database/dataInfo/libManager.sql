CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    [password] NVARCHAR(50) NOT NULL,
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
    ReturnDate DATE CHECK (ReturnDate >= BorrowDate),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);


INSERT INTO Books (Title, Author, Genre, ISBN, Available, BookType, NumberOfPages, FileFormat)
VALUES
-- Printed books
('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', '9780743273565', 5, 0, 180, NULL),
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', '9780061120084', 3, 0, 281, NULL),
('1984', 'George Orwell', 'Dystopian', '9780451524935', 7, 0, 328, NULL),
('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', '9780316769488', 2, 0, 277, NULL),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', '9780547928227', 4, 0, 310, NULL),

-- Ebooks
('The Road', 'Cormac McCarthy', 'Dystopian', '9780307387899', 10, 1, NULL, 'PDF'),
('Dune', 'Frank Herbert', 'Science Fiction', '9780441013593', 6, 1, NULL, 'EPUB'),
('Ender''s Game', 'Orson Scott Card', 'Science Fiction', '9780812550702', 8, 1, NULL, 'MOBI'),
('Brave New World', 'Aldous Huxley', 'Dystopian', '9780060850524', 10, 1, NULL, 'PDF'),
('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Fantasy', '9780439554930', 12, 1, NULL, 'EPUB'),

-- Mixed
('The Name of the Wind', 'Patrick Rothfuss', 'Fantasy', '9780756404741', 5, 0, 662, NULL),
('Foundation', 'Isaac Asimov', 'Science Fiction', '9780553293357', 7, 1, NULL, 'MOBI'),
('Frankenstein', 'Mary Shelley', 'Horror', '9780486282114', 6, 0, 280, NULL),
('Dracula', 'Bram Stoker', 'Horror', '9780486411095', 4, 0, 418, NULL),
('The Shining', 'Stephen King', 'Horror', '9780307743657', 3, 1, NULL, 'PDF');

INSERT INTO Books (Title, Author, Genre, ISBN, Available, BookType, NumberOfPages, FileFormat)
VALUES
-- Printed book
('Introduction to Algorithms', 'Thomas H. Cormen', 'Tech', '9780262033848', 4, 0, 1312, NULL),
-- Ebook
('The Art of Computer Programming', 'Donald E. Knuth', 'Tech', '9780201896831', 6, 1, NULL, 'PDF'),
-- Mixed
('Computer Networking: A Top-Down Approach', 'James F. Kurose', 'Tech', '9780133594140', 5, 0, 864, NULL);

-- Verify the inserted data
SELECT * FROM Books;

insert into Users (Username, password, UserType)
values 
--admin
('admin', 'admin', 2),
--librarian
('lib', 'lib', 1),
-- reader
('readera','reader',0);
CREATE TABLE IF NOT EXISTS shelves (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_shelf_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS books (
    isbn VARCHAR(13) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    edition VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS shelf_books (
    shelf_id BIGINT NOT NULL,
    book_isbn VARCHAR(13) NOT NULL,
    PRIMARY KEY (shelf_id, book_isbn),
    FOREIGN KEY (shelf_id) REFERENCES shelves(id) ON DELETE CASCADE,
    FOREIGN KEY (book_isbn) REFERENCES books(isbn) ON DELETE CASCADE
);

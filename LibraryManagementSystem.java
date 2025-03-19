import java.util.ArrayList;

class Book {
    String title;
    String author;
    String ISBN;
    boolean isAvailable;

    // Default Constructor
    public Book() {
        this.isAvailable = true; // Books are available by default
    }

    // Parameterized Constructor
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true; 
    }

    // Getters and Setters
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + 
                ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.title);
    }

    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; 
    }

    public Book searchByISBN(String ISBN) {
        for (Book book : books) {
            if (book.ISBN.equals(ISBN)) {
                return book;
            }
        }
        return null; 
    }

    public boolean borrowBook(String ISBN) {
        Book book = searchByISBN(ISBN);
        if (book != null && book.isAvailable) {
            book.setAvailable(false);
            System.out.println("Book borrowed: " + book.title);
            return true;
        }
        System.out.println("Book is not available or does not exist.");
        return false;
    }

    // Return a book (mark as available)
    public boolean returnBook(String ISBN) {
        Book book = searchByISBN(ISBN);
        if (book != null && !book.isAvailable) {
            book.setAvailable(true);
            System.out.println("Book returned: " + book.title);
            return true;
        }
        System.out.println("Invalid return attempt.");
        return false;
    }

    // Display all books
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook(new Book("Java Programming", "John Doe", "123456"));
        library.addBook(new Book("Data Structures", "Jane Smith", "654321"));

        // Display all books
        System.out.println("\nAvailable Books:");
        library.displayBooks();

        // Borrow a book
        library.borrowBook("123456");

        // Try to borrow the same book again
        library.borrowBook("123456");

        // Return a book
        library.returnBook("123456");

        // Display all books after transactions
        System.out.println("\nUpdated Book List:");
        library.displayBooks();
    }
}
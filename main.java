// Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addBook(new Book(3, "To Kill a Mockingbird", "Harper Lee"));

        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Display all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Search a book by title");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    library.borrowBook(borrowId);
                    break;
                case 3:
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 4:
                    System.out.print("Enter book title to search: ");
                    String title = scanner.nextLine();
                    library.searchBook(title);
                    break;
                case 5:
                    System.out.println("Thank you for using the library!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 5);

        scanner.close();
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }

    public boolean isBookBorrowed() {
        return isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ": " + title + " by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("\n--- List of Books ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }

    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }

    public void searchBook(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with title containing: " + title);
        }
    }
}

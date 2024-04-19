import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Book{
    private String title;
    private String Author;
    private String ISBN;
    boolean bookAvailable;

    public Book(String title,String author,String ISBN,boolean bookAvailable){
        this.title =title;
        this.Author= author;
        this.ISBN =ISBN;
        this.bookAvailable = bookAvailable;
    }
    /// Remeber bro that you have to return all the private top fields

    public String fetchTitle(){
        return title;
    }
    public String fetchAuthor(){
        return Author;
    }
    public String fetchISBN(){
        return ISBN;
    }

    public boolean isBookAvailable() {
        return bookAvailable;
    }
    public void setBookAvailable(boolean bookAvailable ){
        this.bookAvailable = bookAvailable;
    }
}

class Member {
    private String naam;
    private String email;
    private List<Book> borrewedBooks;
    private List<Book> addedBooks;
    private List<Date> borredDatas;
    private List<Date> addDates;
    /// next brother make a method No STATIC VOID .. this emthode has the pric=vate parameter in it

    public Member(String naam, String email) throws IllegalArgumentException {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException(" clearly thats an invalod email");
        }
        this.naam = naam;
        this.email = email;
        this.borrewedBooks = new ArrayList<>();
        this.addDates = new ArrayList<>();
        this.borredDatas = new ArrayList<>();
        this.addedBooks = new ArrayList<>();
    }

    // lets make sure the validity wokrs and is able to get the util
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /// this is the methods for boorrwin the books or returnint
    public void borrewingThatBook(Book book) {
        borrewedBooks.add(book);
        borredDatas.add(new Date());
        book.setBookAvailable(false);
    }

    public void returnBook(Book book) {
        borrewedBooks.remove(book);
        book.setBookAvailable(true);
    }

    // lets create a method to help add a book
    public void addingBook(Book book) {
        addedBooks.add(book);
        addDates.add(new Date());
    }

    /// THOSE GOOD METHODS FOR THE PRIVATE WEAIRD CLASSES
    public String fetchNaam() {
        return naam;
    }

    public String fetchEmail() {
        return email;
    }

    public List<Book> fetchBorrowedBooks() {
        return borrewedBooks;
    }

    public List<Book> fetchAddedBooks() {
        return addedBooks;
    }

    public List<Date> fetchBorrowDates() {
        return borredDatas;
    }

    public List<Date> fetchAddDates() {
        return addDates;
    }
}
public class LibraryManagingSystem{
    private List<Member> members;
    private List<Book> books;


    public LibraryManagingSystem(){
        books = new ArrayList<>();
        members = new ArrayList<>();

    }
// TO ADD A BOK
    public void addingBook(Book book){
        books.add(book);
    }


    public List<Book> haveBooksByTitle(String title){
        List<Book> result = new ArrayList<>();
        for (Book book : books){
            if(book.fetchTitle().equalsIgnoreCase(title)){
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> haveBooksByAuthor(String author){
        List<Book> result = new ArrayList<>();
        for(Book book : books){
            if (book.fetchAuthor().equalsIgnoreCase(author)){
                result.add(book);
            }
        }
        return result;
    }
// METHOD TO ADD A MEMBER

    public void addMember(Member member){
        members.add(member);

    }// METHOD USED FOR BORROWING a book nd tells u if the book is not thewe
    public void borrowbook(Member member, Book book) throws Exception{
        if (!book.isBookAvailable()) {
            throw new Exception(" that book is  not available");
        }
        member.borrewingThatBook(book);
    }

    public void returningBook(Member member,Book book){
        member.returnBook(book);
    }
    public void addingBookByMember(Member member ,Book book){
        member.addingBook(book);
        addingBook(book);
    }

    public void seeBooks(){
        System.out.println("----------------------------------------");
        System.out.println(" the Magazines in the library");
        System.out.println("----------------------------------------");
        for (Book book : books){
            System.out.println("Title: " + book.fetchTitle() + ", Author: " + book.fetchAuthor() + ", ISBN: " + book.fetchISBN() + ", Available: " + book.isBookAvailable());

        }
    }
    // This method here helps to see member andd the books borrowed
    public void seeMembers(){

        System.out.println("----------------------------------------");
        System.out.println(" The corrent Members in the Library: ");
        System.out.println("----------------------------------------");
        for (Member member : members){
            System.out.println("Name: " + member.fetchNaam() + " , EMAIL: " + member.fetchEmail());
            System.out.println("The Boooks borrowed:");
            for(int jr = 0 ; jr < member.fetchBorrowedBooks().size(); jr++){
                Book book = member.fetchBorrowedBooks().get(jr);
                Date borrowDate = member.fetchBorrowDates().get(jr);
                System.out.println("##- "+ book.fetchTitle() + " by: "+ book.fetchAuthor() + " , borrowed date: " + borrowDate);

            }
             System.out.println("----------------------------------------");
             System.out.println(" books added: ");
            for (int jr = 0; jr < member.fetchAddedBooks().size(); jr++){
                Book book = member.fetchAddedBooks().get(jr);
                Date addDate = member.fetchAddDates().get(jr);
                System.out.println("##- "+ book.fetchTitle() + " by " + book.fetchAuthor() + "Added on this date "+ addDate);

            }

            System.out.println();
        }


    }
    // Main method to run the library management system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagingSystem librarySys = new LibraryManagingSystem();

        System.out.println(" welcome to the Calebrity Magazine Library");
        // Adding initial books to the library
        Book book1 = new Book("The Maze Runner", "James Dashner", "1234578", true);
        Book book2 = new Book("George Of The Jungle", "Bill Scott", "12345678", true);
        Book book3 = new Book("Willy Wonka", "Roald Dahl", "32323", true);
        Book book4 = new Book("Baboon", "Andzi", "545", true);
        Book book5 = new Book("THE CAT IN THE HAT", "Asher", "0000000", true);
        Book book6 = new Book("The sick 12 year old", "Yandi", "0000012", true);
        Book book7 = new Book("Tarzan ", "HIM", "00000123", true);
        Book book8 = new Book("Alcaholic", "Katlego ", " 212129",true);

        // Adding initial books to the library system
        librarySys.addingBook(book1);
        librarySys.addingBook(book2);
        librarySys.addingBook(book3);
        librarySys.addingBook(book4);
        librarySys.addingBook(book5);
        librarySys.addingBook(book6);
        librarySys.addingBook(book7);
        librarySys.addingBook(book8);
        // Ehile loop to show the menu
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a member");
            System.out.println("2. Show all books in the library");
            System.out.println("3. Search for a book by title");
            System.out.println("4. Search for a book by author");
            System.out.println("5. Add a book by a member");
            System.out.println("6. Show all members and their activities");
            System.out.println("7. Check out a book");
            System.out.println("8. Return a book");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            // Error handling
            int decisionMaking = 0;
            try {
                decisionMaking = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println(" Error , please  enter a valid answer");
                scanner.nextLine();
                continue;
            }

            switch (decisionMaking){
                // Switch case for menu options
                case 1:
                    System.out.println(" enter in the new members name: ");
                    String newMemName = scanner.nextLine();
                    System.out.println(" enter the new member's email: ");
                    String newMemEmail = scanner.nextLine();
                    try{
                        Member newMem = new Member(newMemName,newMemEmail);
                        librarySys.addMember(newMem);
                        System.out.println(" New member has been added with great success!!");
                    }catch (IllegalArgumentException e ){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                // // Show all books in the library
                case 2:
                    librarySys.seeBooks();
                    break;
                // Search for a book by title
                case 3:
                    System.out.println(" put in the title to search for :");
                    String looking4title = scanner.nextLine();
                    List<Book> booksByTitle = librarySys.haveBooksByTitle(looking4title);
                    if( booksByTitle.isEmpty()){
                        System.out.println("There no magazine, book or manga in here");

                    }else {
                        System.out.println(" WE have found your book , now smile");
                        for (Book book : booksByTitle){
                            System.out.println("##- " + book.fetchTitle() + " by :  " + book.fetchAuthor());
                        }
                     break;
                    }
                    // Search for a book by author
                case 4:
                    System.out.print("give me the author to search for: ");
                    String look4Author = scanner.nextLine();
                    List<Book> booksByAuthor = librarySys.haveBooksByAuthor(look4Author);
                    if (booksByAuthor.isEmpty()) {
                        System.out.println("There no magazine, book or manga in here of that author");
                    } else {
                        System.out.println("WE have found your book , now smile:");
                        for (Book book : booksByAuthor) {
                            System.out.println("##-- " + book.fetchTitle() + " by: " + book.fetchAuthor());
                        }

                    }
                    break;
                // Add a book by a member
                case 5:
                    System.out.println(" please enter the member's name : ");
                    String addingBMemName = scanner.nextLine();
                    Member addBookMem = null;
                    for (Member Jr : librarySys.members){
                        if (Jr.fetchNaam().equalsIgnoreCase(addingBMemName)){
                            addBookMem = Jr;
                            break;
                        }
                    }
                    if(addBookMem == null){
                        System.out.println(" That member cannot not be found ");
                        break;
                    }
                    System.out.println(" enter the title of the book you are about to add");
                    String BToAddTitle = scanner.nextLine();
                    System.out.println("give me the author of the book to add ");
                    String BtoAddAuthor = scanner.nextLine();
                    System.out.println(" Enter the ISBN of the book  you are about to add: ");
                    String BtoAddISBN = scanner.nextLine();
                    Book newBook = new Book(BToAddTitle,BtoAddAuthor,BtoAddISBN,true);
                    librarySys.addingBook(newBook);
                    addBookMem.addingBook(newBook);


                    System.out.println(" the book has been added successfully");
                    break;
                // Show all members and their activities
                case 6:
                    librarySys.seeMembers();
                    break;
                // Check/Borrow out a book
                case 7:
                    System.out.println(" enters the present memeber name");
                    String memName =scanner.nextLine();
                    Member member = null;
                    for(Member jj : librarySys.members){
                        if (jj.fetchNaam().equalsIgnoreCase(memName)) {
                            member =jj;
                            break;
                        }
                    }
                    if (member == null ){
                        System.out.println(" that member is not here here");
                        break;
                    }
                    System.out.println("Give the title of the book you are to borrow");
                    String bookTitle = scanner.nextLine();
                    List<Book> TbookstoBorrow = librarySys.haveBooksByTitle(bookTitle);
                    if (TbookstoBorrow.isEmpty()){
                        System.out.println( " the book you are looking for is not found ");
                    }else{
                        Book bookToBorrow = TbookstoBorrow.get(0);
                        try{
                            librarySys.borrowbook(member,bookToBorrow);
                            System.out.println("Book :" + bookToBorrow.fetchTitle() + ",you borrowing this book!! please bring that back ");
                        }catch (Exception e){
                            System.out.println("Error !!" + e.getMessage());
                        }
                    }
                    break;
                // Return a book
                case 8:
                    System.out.println(" Enter the members name ");
                    String returningMemName = scanner.nextLine();
                    Member returningMem = null;
                    for (Member JUJU : librarySys.members){
                        if(JUJU.fetchNaam().equalsIgnoreCase(returningMemName)){
                            returningMem = JUJU;
                            break;
                        }
                    }
                    if (returningMem == null){
                        System.out.println(" That member my dear pall has not been found!!");
                        break;
                    }
                    System.out.println(" enter the title of the book you are about to return");
                    String BToReturnTitle = scanner.nextLine();
                    List <Book> BToReturn = librarySys.haveBooksByTitle(BToReturnTitle);
                    if(BToReturn.isEmpty()){
                        System.out.println(" That book is not found");
                    }else {
                        Book bookToReturn = BToReturn.get(0);
                        librarySys.returningBook(returningMem ,bookToReturn);
                        System.out.println(" Book " + bookToReturn.fetchTitle() + " has been returned successfully");
                    }
                    break;

                // Exit the program
                case 9:
                    System.out.println(" EXITING ... GOODBYE");
                    System.exit(0);
                default:
                    System.out.println(" Invalid answer . I advice if you can try again");



            }
        }

    }
}
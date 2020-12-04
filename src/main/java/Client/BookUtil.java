package Client;

import Client.Controller.LogIn;
import Client.Controller.Modal;
import Client.Controller.Visitor.VisitorHome;
import Model.Book;
import Model.Category;
import Model.History;
import Model.Language;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiko Kuno
 * Date: 2020-12-01
 * Time: 13:36
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class BookUtil {
    private static ObservableList<Book> bookData = FXCollections.observableArrayList();
    private static ObservableList<History> historyData = FXCollections.observableArrayList();
    public static Book selectedBook;

    /**
     * Ta bort white space
     *
     * @param text t.ex Harry Potter
     * @return harrypotter
     */
    public static String removeWhiteSpace(String text) {
        String[] split = text.split(" ");
        text = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() != 0)
                text = text.concat(split[i]).toLowerCase();
        }
        return text;
    }

    //Boksök funktion
    public static List<Book> searchBook(String searchWord) {

        /*---------------- TEST --------------------*/

        Book b1 = new Book().setTitle("Harry Potter").setIsbn("123434").setCategory(Category.FANTASY).setLanguage(Language.Svenska).setAuthor("JK.Rolling");
        Book b2 = new Book().setTitle("Load of the rings").setIsbn("22343").setCategory(Category.FANTASY).setLanguage(Language.Engelska).setAuthor("J. R. R. Tolkien");
        Book b3 = new Book().setTitle("Gozilla").setIsbn("32343").setCategory(Category.SCIENCE_FICTION).setLanguage(Language.Engelska).setAuthor("Tomoyuki Tanaka");

        List<Book> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        /*------------------------------------------*/

        List<Book> hitSearchBookList = new ArrayList<>();
        searchWord = removeWhiteSpace(searchWord);

        for (Book book : bookList) {
            String title = removeWhiteSpace(book.getTitle());
            String isbn = book.getIsbn();
            String category = book.getCategory().toString().toLowerCase();
            String author = removeWhiteSpace(book.getAuthor());
            if (title.contains(searchWord) || isbn.contains(searchWord) ||
                    category.contains(searchWord) || author.contains(searchWord)) {
                hitSearchBookList.add(book);
            }
        }
        return hitSearchBookList;
    }

    //Skriv ut sökresultat
    public static void printOutSearchResult(String searchWord, TableView searchView,
                                            TableColumn<Book, String> title, TableColumn<Book, String> author,
                                            TableColumn<Book, String> language, TableColumn<Book, String> category,
                                            Text message, Class<?> currentClass) {

        searchView.getItems().clear();
        searchView.setVisible(false);
        List<Book> books = BookUtil.searchBook(searchWord);
        if (books.size() == 0) {
            message.setText("Din sökning gav inga träffar. Försök igen.");
        } else {
            message.setText("");
            searchView.setVisible(true);

            //Skriv ut sökresultat
            for (Book book : books) {
                title.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTitle()));
                author.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getAuthor()));
                language.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getLanguage().toString()));
                category.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getCategory().getCategory()));
                bookData.add(book);
            }
            searchView.setItems(bookData);
        }

        //Open modal window
        searchView.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) ->
        {
            selectedBook = getBook(newVal.toString());
            Modal.displayBook(currentClass);
        });

    }

    //Skriv ut utlåningshistorik
    public static void printOutLendingHistory(TableView historyView, TableColumn<History, String> title, TableColumn<History, String> isbn,
                                              TableColumn<History, String> returnDate, TableColumn<History, String> lendOutDate) {


        List<History> histories = UserUtil.getLoggedInUserHistory(LogIn.currentLoggedInUser);
        if (histories != null) {
            for (History history : histories) {
                title.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getBook().getTitle()));
                isbn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getBook().getIsbn()));
                lendOutDate.setCellValueFactory(cellData -> new SimpleObjectProperty(String.valueOf(cellData.getValue().getLendOutDate())));
                returnDate.setCellValueFactory(cellData -> new SimpleObjectProperty(String.valueOf(cellData.getValue().getReturnDate())));
                historyData.add(history);
            }
            historyView.getItems().clear();
            historyView.setItems(historyData);

        }

    }

    public static Book getBook(String isbn) {
        /*---------------- TEST --------------------*/
        Book b1 = new Book().setTitle("Harry Potter").setIsbn("123434").setCategory(Category.FANTASY).setLanguage(Language.Svenska).setAuthor("JK.Rolling");
        Book b2 = new Book().setTitle("Load of the rings").setIsbn("22343").setCategory(Category.FANTASY).setLanguage(Language.Engelska).setAuthor("J. R. R. Tolkien");
        Book b3 = new Book().setTitle("Gozilla").setIsbn("32343").setCategory(Category.SCIENCE_FICTION).setLanguage(Language.Engelska).setAuthor("Tomoyuki Tanaka");

        List<Book> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        /*------------------------------------------*/

        Book tempBook = new Book();
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn))
                tempBook = book;
        }
        return tempBook;
    }
}

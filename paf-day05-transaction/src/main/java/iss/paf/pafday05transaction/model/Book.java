package iss.paf.pafday05transaction.model;

public class Book {

    private Integer id;

    private String title;

    private Integer quantity;

    public Book() {
    }

    public Book(Integer id, String title, Integer quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

package iss.paf.pafday01.model;

public class Room {

    private Integer id;
    private String roomType;
    private Float price;

    public Room() {
    }

    public Room(Integer id, String roomType, Float price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", roomType='" + getRoomType() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

    
}

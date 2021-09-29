package mdole;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + getRoomNumber() + '\'' +
                ", price=" + getRoomPrice() +
                ", enumeration=" + getRoomType() +
                '}';
    }
}

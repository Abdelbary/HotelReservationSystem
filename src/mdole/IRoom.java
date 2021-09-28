package mdole;

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public void updateRoomState(boolean state);
    public boolean isFree();
}

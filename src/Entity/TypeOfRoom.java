package Entity;

import DAO.TypeOfRoomDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Entity: Loại phòng
 * Người thiết kế: Hà Thị Phương Linh
 */
public class TypeOfRoom {
    private String maLoaiPhong;
    private String tenLoaiPhong;
    private int sucChua;

    public TypeOfRoom(ResultSet rs) throws SQLException {
        this(rs.getString(1), rs.getString(2), rs.getInt(3));
    }

    public TypeOfRoom(String maLoaiPhong) {
        TypeOfRoomDAO typeOfRoomDAO = new TypeOfRoomDAO();
        TypeOfRoom t = typeOfRoomDAO.getRoomTypeByID(maLoaiPhong);
        this.maLoaiPhong = t.getMaLoaiPhong();
        this.tenLoaiPhong= t.getTenLoaiPhong();
        this.sucChua = t.getSucChua();
    }

    public TypeOfRoom() {
        this.maLoaiPhong = "";
        this.tenLoaiPhong = "";
        this.sucChua = 0;
    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeOfRoom that)) return false;
        return Objects.equals(getMaLoaiPhong(), that.getMaLoaiPhong());
    }

    @Override
    public String toString() {
        return "TypeOfRoom{" +
                "maLoaiPhong='" + maLoaiPhong + '\'' +
                ", tenLoaiPhong='" + tenLoaiPhong + '\'' +
                ", sucChua=" + sucChua +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaLoaiPhong());
    }

    public TypeOfRoom(String maLoaiPhong, String tenLoaiPhong, int sucChua) {
        setMaLoaiPhong(maLoaiPhong);
        setTenLoaiPhong(tenLoaiPhong);
        setSucChua(sucChua);

    }



}

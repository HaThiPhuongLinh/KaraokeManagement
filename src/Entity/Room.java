package Entity;
import DAOs.RoomDAO;
import DAOs.TypeOfRoomDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Room {
    private String maPhong;
    private TypeOfRoom loaiPhong;
    private String tinhTrang;
    private String viTri;
    private int giaPhong;

    public Room(ResultSet rs) throws SQLException {
        this(rs.getString("maPhong"), new TypeOfRoom(rs), rs.getString("tinhTrang"), rs.getString("viTri"), rs.getInt("giaPhong"));
    }
    public Room() {
        this.maPhong = "";
        this.loaiPhong = new TypeOfRoom();
        this.tinhTrang = "";
        this.viTri = "";
    }

    public Room(String string) {
        this.maPhong = maPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        if(!(maPhong.trim().equals(""))) {
            this.maPhong = maPhong;
        }else {
            this.maPhong = "Un-known";
        }
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        if(!(viTri.trim().equals(""))) {
            this.viTri = viTri;
        }else {
            this.viTri = "Un-known";
        }
    }

    public TypeOfRoom getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(TypeOfRoom loaiPhong) {
        this.loaiPhong = loaiPhong;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(getMaPhong(), room.getMaPhong());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaPhong());
    }

    @Override
    public String toString() {
        return "Room{" +
                "maPhong='" + maPhong + '\'' +
                ", loaiPhong=" + loaiPhong +
                ", tinhTrang='" + tinhTrang + '\'' +
                ", viTri='" + viTri + '\'' +
                ", giaPhong=" + giaPhong +
                '}';
    }

    public Room(String maPhong, TypeOfRoom loaiPhong, String tinhTrang, String viTri, int giaPhong) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.tinhTrang = tinhTrang;
        this.viTri = viTri;
        this.giaPhong = giaPhong;
    }

    public int getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(int giaPhong) {
        this.giaPhong = giaPhong;
    }
}

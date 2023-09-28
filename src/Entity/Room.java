package Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Room {
    private String maPhong;
    private TypeOfRoom maLoaiPhong;
    private String tinhTrang;
    private String viTri;

    public Room(){

    }

    public Room(String maPhong){
        this.maPhong=maPhong;
    }

    public Room(ResultSet rs) throws SQLException {
        this(rs.getString(1), new TypeOfRoom(rs.getString(2)), rs.getString(3), rs.getString(4));
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

    public TypeOfRoom getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(TypeOfRoom maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
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
                ", tinhTrang=" + tinhTrang +
                ", viTri='" + viTri + '\'' +
                ", maLP=" + maLoaiPhong +
                '}';
    }

    public Room(String maPhong, TypeOfRoom maLoaiPhong, String tinhTrang, String viTri) {
        setMaPhong(maPhong);
        this.tinhTrang = tinhTrang;
        setViTri(viTri);
        this.maLoaiPhong = maLoaiPhong;
    }
}

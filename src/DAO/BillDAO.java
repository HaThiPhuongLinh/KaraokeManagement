package DAO;

import ConnectDB.ConnectDB;
import Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Người tham gia thiết kế: Hà Thị Phương Linh, Nguyễn Quang Duy, Nguyễn Đình Dương
 * <p>
 * Ngày tạo: 23/10/2023
 * <p>
 * Lần cập nhật cuối: 25/11/2023
 * <p>
 * Nội dung cập nhật: cập nhật chức năng hàm getBillsByCustomerName (tìm khách hàng theo tên không dấu)
 */
public class BillDAO {
    private static BillDAO instance = new BillDAO();

    /**
     * Tạo thể hiện hiện tại cho BillDAO
     */
    public static BillDAO getInstance() {
        return instance;
    }

    /**
     * Lấy ra số lượng hóa đơn theo khoảng thời gian
     *
     * @param startDate:Ngày bát đầu
     * @param endDate:Ngày   kết thúc
     * @return {@code int}:Tổng bill
     */

    public static int getTotalLineOfBillList(Date startDate, Date endDate) {
        int totalCount = 0;
        ConnectDB.getInstance();
        PreparedStatement statement = null;
        Connection con = ConnectDB.getConnection();

        try {
            // Chuỗi SQL để lấy số lượng hóa đơn trong khoảng thời gian đã chọn
            String sql = "SELECT COUNT(*) AS total FROM HoaDon WHERE thoiGianVao >= ? AND thoiGianRa <= ?";
            statement = con.prepareStatement(sql);
            // Thiết lập các tham số trong câu lệnh SQL
            statement.setDate(1, (java.sql.Date) startDate);
            statement.setDate(2, (java.sql.Date) endDate);

            // Thực thi câu lệnh SQL
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return totalCount;
    }

    /**
     * Update thuộc tính Khuyến Mãi của hóa đơn
     *
     * @return {@code boolean} :True or false
     */
    public static boolean updateKM(String b) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String sql = "update dbo.HoaDon" + " set khuyenMai = 'KM'"
                    + " where maHoaDon = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, b);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    /**
     * Lấy ra toàn bộ Hóa Đơn
     *
     * @return {@code ArrayList<Bill>}:Danh sách hóa đơn
     */
    public ArrayList<Bill> getAllBill() {
        ArrayList<Bill> dsBill = new ArrayList<Bill>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * from dbo.HoaDon";
            Statement statement = con.createStatement();
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);
            // Duyệt trên kết quả trả về
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Staff maNhanVien = new Staff(rs.getString(2));
                Customer maKhachHang = new Customer(rs.getString(3));
                Room maPhong = new Room(rs.getString(4));
                Timestamp thoiGianVao = rs.getTimestamp(5);
                Timestamp thoiGianRa = rs.getTimestamp(6);
                int tinhTrang = rs.getInt(7);
                String khuyenMai = rs.getString(8);

                Bill bill = new Bill(maHoaDon, maNhanVien, maKhachHang, maPhong, thoiGianVao, thoiGianRa, tinhTrang, khuyenMai);

                dsBill.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBill;
    }

    /**
     * Lấy ra toàn bộ hóa đơn bao gồm cả chi tiết dịch vụ
     *
     * @return {@code ArrayList<Bill>}:Danh sách hóa đơn
     */
    public ArrayList<Bill> getAllBill2() {
        ArrayList<Bill> dsBill = new ArrayList<Bill>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * from dbo.HoaDon";
            Statement statement = con.createStatement();
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);
            // Duyệt trên kết quả trả về
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Staff maNhanVien = new Staff(rs.getString(2));
                Customer maKhachHang = new Customer(rs.getString(3));
                Room maPhong = new Room(rs.getString(4));
                Timestamp thoiGianVao = rs.getTimestamp(5);
                Timestamp thoiGianRa = rs.getTimestamp(6);
                int tinhTrang = rs.getInt(7);
                String khuyenMai = rs.getString(8);
                ArrayList<DetailsOfService> ctDV = new ArrayList<DetailsOfService>(9);
                Bill bill = new Bill(maHoaDon, maNhanVien, maKhachHang, maPhong, thoiGianVao, thoiGianRa, tinhTrang, khuyenMai, ctDV);

                dsBill.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBill;
    }

    /**
     * Lấy ra hóa đơn theo ngày
     *
     * @param tuNgay:Ngày  bắt đầu
     * @param denNgay:Ngày kết thúc
     * @return {@code ArrayList<Bill>}:Danh sách hóa đơn
     */

    public ArrayList<Bill> getListBillByDate(Date tuNgay, Date denNgay) {
        ArrayList<Bill> dsStaff = new ArrayList<Bill>();
        ConnectDB.getInstance();
        PreparedStatement statement = null;
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM dbo.HoaDon WHERE thoiGianRa >= ? AND thoiGianRa <= ?";
//            String sql = "SELECT ct.* FROM ChiTietDichVu ct JOIN HoaDon hd ON ct.maHoaDon = hd.maHoaDon WHERE hd.ngayGioTra >= ? AND hd.ngayGioTra <= ?";
            statement = con.prepareStatement(sql);

            statement.setDate(1, (java.sql.Date) tuNgay);
            statement.setDate(2, (java.sql.Date) denNgay);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                String maHoaDon = rs.getString(1);
                Staff maNhanVien = new Staff(rs.getString(2));
                Customer maKhachHang = new Customer(rs.getString(3));
                Room maPhong = new Room(rs.getString(4));
                Timestamp thoiGianVao = rs.getTimestamp(5);
                Timestamp thoiGianRa = rs.getTimestamp(6);
                int tinhTrang = rs.getInt(7);
                String khuyenMai = rs.getString(8);

                Bill bill = new Bill(maHoaDon, maNhanVien, maKhachHang, maPhong, thoiGianVao, thoiGianRa, tinhTrang, khuyenMai);

                dsStaff.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsStaff;
    }

    /**
     * Thêm hóa đơn
     *
     * @param bill: hóa đơn cần thêm
     * @return {@code boolean} :True or false
     */

    public boolean addBill(Bill bill) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO HoaDon (maHoaDon, maNhanVien, maKhachHang, maPhong, thoiGianVao, thoiGianRa, tinhTrangHD, khuyenMai) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            statement = con.prepareStatement(sql);
            statement.setString(1, bill.getMaHoaDon());
            statement.setString(2, bill.getMaNhanVien().getMaNhanVien());
            statement.setString(3, bill.getMaKH().getMaKhachHang());
            statement.setString(4, bill.getMaPhong().getMaPhong());
            statement.setTimestamp(5, bill.getThoiGianVao());
            statement.setTimestamp(6, bill.getThoiGianRa());
            statement.setInt(7, bill.getTinhTrangHD());
            statement.setString(8, bill.getKhuyenMai());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Lấy Hóa Đơn dựa trên mã Phòng
     *
     * @param roomID: mã phòng được truyền vào
     * @return {@code Bill}:Hóa đơn
     */

    public Bill getBillByRoomID(String roomID) {
        Bill bill = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getInstance().getConnection();
            String query = "SELECT * FROM HoaDon WHERE maPhong = ? AND tinhTrangHD = 0";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,  roomID);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                Staff maNhanVien = new Staff(rs.getString("maNhanVien"));
                Customer maKhachHang = new Customer(rs.getString("maKhachHang"));
                Timestamp thoiGianVao = rs.getTimestamp("thoiGianVao");
                Timestamp thoiGianRa = rs.getTimestamp("thoiGianRa");
                int tinhTrang = rs.getInt("tinhTrangHD");
                String khuyenMai = rs.getString("khuyenMai");

                bill = new Bill(maHoaDon, maNhanVien, maKhachHang, new Room(roomID), thoiGianVao, thoiGianRa, tinhTrang, khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bill;
    }

    public Bill getBillByBillID(String billID) {
        Bill bill = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getInstance().getConnection();
            String query = "SELECT * FROM HoaDon WHERE maHoaDon = ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, billID);

            rs = stmt.executeQuery();

            if (rs.next()) {

                Staff maNhanVien = new Staff(rs.getString("maNhanVien"));
                Customer maKhachHang = new Customer(rs.getString("maKhachHang"));
                Room maPhong = new Room(rs.getString("maPhong"));
                Timestamp thoiGianVao = rs.getTimestamp("thoiGianVao");
                Timestamp thoiGianRa = rs.getTimestamp("thoiGianRa");
                int tinhTrang = rs.getInt("tinhTrangHD");
                String khuyenMai = rs.getString("khuyenMai");

                bill = new Bill(billID, maNhanVien, maKhachHang, maPhong, thoiGianVao, thoiGianRa, tinhTrang, khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bill;
    }

    /**
     * Lấy danh sách hóa đơn dựa trên tên khách hàng
     *
     * @param customerName: Tên khách hàng cần tìm hóa đơn
     * @return {@code ArrayList<Bill>}: Danh sách hóa đơn
     */
    public ArrayList<Bill> getBillsByCustomerName(String customerName) {
        ConnectDB.getInstance();
        PreparedStatement statement = null;
        Connection con = ConnectDB.getConnection();
        ArrayList<Bill> billList = new ArrayList<>();

        try {
            // Tìm mã khách hàng dựa trên tên khách hàng
            String customerQuery = "SELECT maKhachHang FROM dbo.KhachHang WHERE tenKhachHang COLLATE Latin1_General_CI_AI LIKE ?";
            statement = con.prepareStatement(customerQuery);
            statement.setString(1, "%" + customerName + "%");
            ResultSet customerResultSet = statement.executeQuery();

            while (customerResultSet.next()) {
                String customerID = customerResultSet.getString("maKhachHang");
                String sql = "SELECT * FROM dbo.HoaDon "
                        + "JOIN dbo.KhachHang ON dbo.HoaDon.maKhachHang = dbo.KhachHang.maKhachHang "
                        + "WHERE dbo.HoaDon.maKhachHang = ? AND dbo.HoaDon.tinhTrangHD = 0";
                statement = con.prepareStatement(sql);
                statement.setString(1, customerID);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    String maHoaDon = rs.getString(1);
                    Staff maNhanVien = new Staff(rs.getString(2));
                    Customer maKhachHang = new Customer(rs.getString(3));
                    Room maPhong = new Room(rs.getString(4));
                    Timestamp thoiGianVao = rs.getTimestamp(5);
                    Timestamp thoiGianRa = rs.getTimestamp(6);
                    int tinhTrang = rs.getInt(7);
                    String khuyenMai = rs.getString(8);

                    Bill bill = new Bill(maHoaDon, maNhanVien, maKhachHang, maPhong, thoiGianVao, thoiGianRa, tinhTrang, khuyenMai);
                    billList.add(bill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billList;
    }

    /**
     * Tạo mã hóa đơn với dựa trên mã của hóa đơn cuối cùng
     *
     * @return {@code String}:mã hóa đơn
     */
    public String generateNextBillId() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String nextId = null;
        try {
            String sql = "SELECT TOP 1 maHoaDon FROM HoaDon ORDER BY maHoaDon DESC";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            if (rs.next()) {
                String lastServiceId = rs.getString("maHoaDon");
                String numericPart = lastServiceId.substring(4); // Lấy phần số từ mã dịch vụ cuối cùng
                int counter = Integer.parseInt(numericPart) + 1; // Tăng giá trị số lên 1
                nextId = "HD" + String.format("%07d", counter); // Định dạng lại giá trị số thành chuỗi 3 chữ số, sau đó ghép với tiền tố "HD"
            } else {
                nextId = "HD0000001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    /**
     * Hàm thanh toán hóa đơn
     *
     * @param billId:     mã hóa đơn
     * @param thoiGianRa: Ngày giờ thanh toán
     * @return {@code boolean}: True hoặc False
     */
    public boolean paymentBill(String billId, Timestamp thoiGianRa) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            // Update ngày trả và tình trạng hóa đơn
            String sql = "UPDATE dbo.HoaDon SET thoiGianRa = ?, tinhTrangHD = 1 WHERE maHoaDon = ?;";
            statement = con.prepareStatement(sql);
            statement.setTimestamp(1, thoiGianRa);
            statement.setString(2, billId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Update hóa đơn thành công
                // Lấy mã phòng của hóa đơn
                String sql2 = "SELECT maPhong FROM dbo.HoaDon WHERE maHoaDon = ?;";
                statement = con.prepareStatement(sql2);
                statement.setString(1, billId);

                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    String maPhong = rs.getString("maPhong");

                    // Kiểm tra xem có phiếu đặt phòng nào dựa trên mã phòng không
                    String sql3 = "SELECT trangThai FROM dbo.PhieuDatPhong WHERE maPhong = ?;";
                    statement = con.prepareStatement(sql3);
                    statement.setString(1, maPhong);

                    ResultSet rs2 = statement.executeQuery();
                    boolean hasWaitStatusReservation = false;

                    while (rs2.next()) {
                        int trangThaiPhieu = rs2.getInt("trangThai");

                        if (trangThaiPhieu == 2) {
                            hasWaitStatusReservation = true;
                            break;
                        }
                    }

                    // Cập nhật tình trạng phòng
                    if (hasWaitStatusReservation) {
                        updateRoomStatus(con, maPhong, "Cho");
                    } else {
                        updateRoomStatus(con, maPhong, "Trong");
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cập nhật tình trạng phòng dựa trên phiếu đặt
     *
     * @param con:       kết nối driver
     * @param maPhong:   mã phòng cần cập nhật
     * @param tinhTrang: tình trạng cần cập nhật
     */
    private void updateRoomStatus(Connection con, String maPhong, String tinhTrang) throws SQLException {
        String updateRoomSql = "UPDATE dbo.Phong SET tinhTrang = ? WHERE maPhong = ?;";
        try (PreparedStatement statement = con.prepareStatement(updateRoomSql)) {
            statement.setString(1, tinhTrang);
            statement.setString(2, maPhong);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new SQLException("Không thể cập nhật tình trạng phòng");
            }
        }
    }

    /**
     * Lấy ra hóa đơn dựa trên mã khách hàng và khoảng thời gian
     *
     * @param customerID: Mã khách hàng
     * @param fromDate:   Ngày bắt đầu
     * @param toDate:     Ngày kết thúc
     * @return {@code ArrayList<Bill>}: Danh sách hóa đơn
     */
    public ArrayList<Bill> getBillByCustomerIDAndDateRange(String customerID, Date fromDate, Date toDate) {
        ArrayList<Bill> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getInstance().getConnection();
            String query = "SELECT * FROM HoaDon WHERE maKhachHang = ? AND thoiGianVao >= ? AND thoiGianRa <= ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, customerID);
            stmt.setDate(2, (java.sql.Date) fromDate);
            stmt.setDate(3, (java.sql.Date) toDate);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                Staff maNhanVien = new Staff(rs.getString("maNhanVien"));
                Room maPhong = new Room(rs.getString("maPhong"));
                Timestamp thoiGianVao = rs.getTimestamp("thoiGianVao");
                Timestamp thoiGianRa = rs.getTimestamp("thoiGianRa");
                int tinhTrang = rs.getInt("tinhTrangHD");
                String khuyenMai = rs.getString("khuyenMai");

                lst.add(new Bill(maHoaDon, maNhanVien, new Customer(customerID), maPhong, thoiGianVao, thoiGianRa, tinhTrang, khuyenMai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lst;
    }

}
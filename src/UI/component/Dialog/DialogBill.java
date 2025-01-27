package UI.component.Dialog;

import DAO.*;
import Entity.*;
import UI.CustomUI.Custom;
import UI.component.Bill_UI;
import com.itextpdf.text.DocumentException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Thiết kế giao diện hóa đơn tính tiền
 * Người tham gia thiết kế: Nguyễn Đình Dương, Hà Thị Phương Linh
 * Ngày tạo: 29/10/2023
 * Lần cập nhật cuối: 06/11/2023
 * Nội dung cập nhật: cập nhật khi click thanh toán thì hiển thị file pdf
 */
public class DialogBill extends JDialog implements ActionListener {
    private final String WORKING_DIR = System.getProperty("user.dir");
    private JTextField txtKM;
    private JTextField txtBillId, txtStaffName, txtCustomerName, txtRoomId, txtRoomTypeName, txtRoomPrice, txtStartTime,
            txtEndTime, txtUsedTime, txtTotalPriceService, txtTotalPriceRoom, txtVAT, txtTotalPriceBill;
    private JTable tblTableBillInfo;
    private DefaultTableModel modelTblBillInfo;
    private JButton btnPayment, btnBack, btnExportPdf;
    private String formatTime = "HH:mm:ss dd/MM/yyyy";
    private DecimalFormat df = new DecimalFormat("#,###.## VND");
    private DecimalFormat df2 = new DecimalFormat("#,###.##");
    private String path = WORKING_DIR + "/KRManagement/bill/";
    private Bill bill = new Bill();
    private boolean paid = false;
    private BillDAO billDAO = BillDAO.getInstance();
    private StaffDAO staffDAO = StaffDAO.getInstance();
    private CustomerDAO customerDAO = CustomerDAO.getInstance();
    private DetailOfBillDAO detailOfBillDAO;
    private DetailOfServiceDAO serviceDetailDAO = DetailOfServiceDAO.getInstance();
    private Bill_UI main;

    /**
     * Khởi tạo giao diện thanh toán hóa đơn
     *
     * @param bill {@code HoaDon}: hóa đơn cần thanh toán
     */
    public DialogBill(Bill bill) {
        this.bill = bill;
        detailOfBillDAO = new DetailOfBillDAO();
        Staff staff = null;
        Customer customer = null;
        List<DetailsOfService> serviceOrders = new ArrayList<>();
        staff = staffDAO.getStaffByBillId(bill.getMaHoaDon());
        customer = customerDAO.getCustomerByBillId(bill.getMaHoaDon());
        serviceOrders = serviceDetailDAO.getDetailsOfServiceForBill(bill.getMaHoaDon());
        if (staff == null)
            staff = new Staff();
        if (customer == null)
            customer = new Customer();
        bill.setMaNhanVien(staff);
        bill.setMaKH(customer);
        bill.setLstDetails(serviceOrders);
        setSize(800, 720);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.setLayout(null);
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        this.setBackground(new Color(255, 255, 255, 0));

        JPanel pnMain = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gra = new GradientPaint(179, 0, Color.decode("#FF69B4"), 180, getHeight(),
                        Color.decode("#2c7da0"));
                g2.setPaint(gra);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                setFont(new Font("Dialog", Font.BOLD, 16));
            }
        };
        pnMain.setLayout(null);
        pnMain.setOpaque(false);
        pnMain.setBounds(0, 0, 800, 720);
        this.add(pnMain);

        JLabel lblKaraokeName = new JLabel(
                "<HTML><h1 style=\"margin: 10px 0px -20px 0px;\">♥ROSIE KARAOKE♥ </h1> <hr> <p> <i> 12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh </i> </p></HTML>") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
        };
        lblKaraokeName.setVerticalAlignment(SwingConstants.TOP);
        lblKaraokeName.setForeground(Color.WHITE);
        lblKaraokeName.setBorder(BorderFactory.createEmptyBorder(0, 100, 5, 5));
        lblKaraokeName.setHorizontalAlignment(SwingConstants.CENTER);
        lblKaraokeName.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblKaraokeName.setBounds(180, 0, 350, 100);
        pnMain.add(lblKaraokeName);

        JLabel lblBillName = new JLabel("HÓA ĐƠN TÍNH TIỀN");
        lblBillName.setForeground(Color.WHITE);
        lblBillName.setFont(new Font("Dialog", Font.BOLD, 24));
        lblBillName.setHorizontalAlignment(SwingConstants.CENTER);
        lblBillName.setBounds(225, 120, 350, 40);
        pnMain.add(lblBillName);

        JPanel pnInfoService = new JPanel();
        pnInfoService.setLayout(null);
        pnInfoService.setBounds(10, 325, 780, 235);
        pnInfoService.setOpaque(false);
        Border a = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white), "Chi tiết dịch vụ",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 16), Color.WHITE);
        pnInfoService.setBorder(a);

        pnMain.add(pnInfoService);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(null);
        pnTable.setOpaque(false);
        pnTable.setBounds(10, 25, 760, 200);
        pnInfoService.add(pnTable);

        String[] colsBillInfo = {"STT", "Tên dịch vụ ", "Số lượng", "Đơn giá", "Thành tiền"};
        modelTblBillInfo = new DefaultTableModel(colsBillInfo, 0);

        tblTableBillInfo = new JTable(modelTblBillInfo);
        tblTableBillInfo.setBackground(new Color(255, 255, 255, 0));
        tblTableBillInfo.setForeground(new Color(255, 255, 255));
        Custom.getInstance().setCustomTableBill(tblTableBillInfo);
        JScrollPane scrTableBillInfo = new JScrollPane(tblTableBillInfo);
        scrTableBillInfo.setBounds(1, 1, 797, 199);
        scrTableBillInfo.setOpaque(false);
        scrTableBillInfo.getViewport().setOpaque(false);
        scrTableBillInfo.getViewport().setBackground(Color.WHITE);
        pnTable.add(scrTableBillInfo);

        JLabel lblTotalPriceService = new JLabel("Tổng tiền dịch vụ:");
        Custom.getInstance().setCustomLabelBill(lblTotalPriceService);
        lblTotalPriceService.setBounds(40, 560, 140, 25);
        pnMain.add(lblTotalPriceService);

        txtTotalPriceService = new JTextField("");
        txtTotalPriceService.setEditable(false);
        Custom.getInstance().setCustomTextFieldBill2(txtTotalPriceService);
        txtTotalPriceService.setBounds(555, 560, 200, 25);
        txtTotalPriceService.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMain.add(txtTotalPriceService);

        JLabel lblTotalPriceRoom = new JLabel("Tổng tiền giờ:");
        Custom.getInstance().setCustomLabelBill(lblTotalPriceRoom);
        lblTotalPriceRoom.setBounds(40, 585, 140, 25);
        pnMain.add(lblTotalPriceRoom);

        txtTotalPriceRoom = new JTextField("");
        txtTotalPriceRoom.setEditable(false);
        txtTotalPriceRoom.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtTotalPriceRoom.setOpaque(false);
        Custom.getInstance().setCustomTextFieldBill2(txtTotalPriceRoom);
        txtTotalPriceRoom.setBounds(555, 585, 200, 25);
        txtTotalPriceRoom.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMain.add(txtTotalPriceRoom);

        JLabel lblVAT = new JLabel("VAT(8%):");
        Custom.getInstance().setCustomLabelBill(lblVAT);
        lblVAT.setBounds(40, 610, 140, 25);
        pnMain.add(lblVAT);

        txtVAT = new JTextField("");
        Custom.getInstance().setCustomTextFieldBill2(txtVAT);
        txtVAT.setBounds(555, 610, 200, 25);
        txtVAT.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMain.add(txtVAT);

        JLabel lblKM = new JLabel("Khuyến mãi:");
        Custom.getInstance().setCustomLabelBill(lblKM);
        lblKM.setBounds(40, 635, 140, 25);
        pnMain.add(lblKM);

        txtKM = new JTextField("");
        Custom.getInstance().setCustomTextFieldBill2(txtKM);
        txtKM.setBounds(555, 635, 200, 25);
        txtKM.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMain.add(txtKM);

        JLabel lblTotalPriceBill = new JLabel("Tổng cộng:");
        lblTotalPriceBill.setForeground(Color.WHITE);
        lblTotalPriceBill.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTotalPriceBill.setBounds(40, 660, 140, 25);
        pnMain.add(lblTotalPriceBill);

        txtTotalPriceBill = new JTextField("");
        Custom.getInstance().setCustomTextFieldBill2(txtTotalPriceBill);
        txtTotalPriceBill.setFont(new Font("Dialog", Font.BOLD, 16));
        txtTotalPriceBill.setColumns(10);
        txtTotalPriceBill.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTotalPriceBill.setBounds(555, 660, 200, 25);
        pnMain.add(txtTotalPriceBill);
        txtTotalPriceBill.setEditable(false);

        btnBack = new JButton("Quay lại");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
        btnBack.setBounds(240, 670, 130, 35);
        pnMain.add(btnBack);

        btnPayment = new JButton("Thanh toán");
        btnPayment.setFont(new Font("Dialog", Font.BOLD, 15));
        btnPayment.setBounds(420, 670, 130, 35);
        pnMain.add(btnPayment);

//        btnExportPdf = new JButton("Xuất PDF");
//        btnExportPdf.setFont(new Font("Dialog", Font.BOLD, 15));
//        btnExportPdf.setBounds(620, 670, 130, 35);
//
//        pnMain.add(btnExportPdf);
        JLabel txtPhoneNumber = new JLabel("0999.999.999");
        txtPhoneNumber.setBackground(Color.WHITE);
        txtPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
        txtPhoneNumber.setForeground(Color.WHITE);
        txtPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        txtPhoneNumber.setBounds(225, 100, 350, 20);
        pnMain.add(txtPhoneNumber);

        JPanel panel = new JPanel();
        Border b = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white), "Thông tin hóa đơn",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 16), Color.WHITE);
        panel.setBounds(10, 150, 780, 170);
        panel.setBorder(b);
        panel.setOpaque(false);
        pnMain.add(panel);
        panel.setLayout(null);

        JLabel lblBillId = new JLabel("Mã hóa đơn:");
        lblBillId.setBounds(25, 23, 140, 25);
        panel.add(lblBillId);
        Custom.getInstance().setCustomLabelBill(lblBillId);

        JLabel lblStaffName = new JLabel("Thu ngân:");
        lblStaffName.setBounds(25, 48, 140, 25);
        panel.add(lblStaffName);
        Custom.getInstance().setCustomLabelBill(lblStaffName);

        JLabel lblCustomerName = new JLabel("Tên khách hàng:");
        lblCustomerName.setBounds(25, 73, 140, 25);
        panel.add(lblCustomerName);
        Custom.getInstance().setCustomLabelBill(lblCustomerName);

        JLabel lblRoomId = new JLabel("Số phòng:");
        lblRoomId.setBounds(25, 98, 140, 25);
        panel.add(lblRoomId);
        Custom.getInstance().setCustomLabelBill(lblRoomId);

        JLabel lblRoomTypeName = new JLabel("Loại phòng:");
        lblRoomTypeName.setBounds(25, 123, 140, 25);
        panel.add(lblRoomTypeName);
        Custom.getInstance().setCustomLabelBill(lblRoomTypeName);

        JLabel lblRoomPrice = new JLabel("Giá phòng:");
        lblRoomPrice.setBounds(440, 23, 140, 25);
        panel.add(lblRoomPrice);
        Custom.getInstance().setCustomLabelBill(lblRoomPrice);

        JLabel lblStartTime = new JLabel("Giờ bắt đầu:");
        lblStartTime.setBounds(440, 48, 140, 25);
        panel.add(lblStartTime);
        Custom.getInstance().setCustomLabelBill(lblStartTime);

        JLabel lblEndTime = new JLabel("Giờ kết thúc:");
        lblEndTime.setBounds(440, 73, 140, 25);
        panel.add(lblEndTime);
        Custom.getInstance().setCustomLabelBill(lblEndTime);

        JLabel lblUsedTime = new JLabel("Thời gian sử dụng:");
        lblUsedTime.setBounds(440, 98, 140, 25);
        panel.add(lblUsedTime);
        Custom.getInstance().setCustomLabelBill(lblUsedTime);

        txtBillId = new JTextField("");
        txtBillId.setBounds(160, 23, 220, 25);
        Custom.getInstance().setCustomTextFieldBill(txtBillId);
        panel.add(txtBillId);
        txtBillId.setEditable(false);

        txtStaffName = new JTextField("");
        txtStaffName.setBounds(160, 48, 220, 25);
        panel.add(txtStaffName);
        txtStaffName.setEditable(false);
        Custom.getInstance().setCustomTextFieldBill(txtStaffName);

        txtCustomerName = new JTextField("");
        txtCustomerName.setBounds(160, 73, 220, 25);
        panel.add(txtCustomerName);
        txtCustomerName.setEditable(false);
        Custom.getInstance().setCustomTextFieldBill(txtCustomerName);

        txtRoomId = new JTextField("");
        txtRoomId.setBounds(160, 98, 220, 25);
        txtRoomId.setEditable(false);
        panel.add(txtRoomId);
        Custom.getInstance().setCustomTextFieldBill(txtRoomId);

        txtRoomTypeName = new JTextField("");
        txtRoomTypeName.setBounds(160, 123, 220, 25);
        txtRoomTypeName.setEditable(false);
        panel.add(txtRoomTypeName);
        Custom.getInstance().setCustomTextFieldBill(txtRoomTypeName);

        txtRoomPrice = new JTextField("");
        txtRoomPrice.setBounds(588, 23, 150, 25);
        txtRoomPrice.setEditable(false);
        panel.add(txtRoomPrice);
        Custom.getInstance().setCustomTextFieldBill(txtRoomPrice);

        txtStartTime = new JTextField("");
        txtStartTime.setBounds(588, 48, 150, 25);
        panel.add(txtStartTime);
        txtStartTime.setEditable(false);
        Custom.getInstance().setCustomTextFieldBill(txtStartTime);

        txtEndTime = new JTextField("");
        txtEndTime.setBounds(588, 73, 150, 25);
        panel.add(txtEndTime);
        txtEndTime.setEditable(false);
        Custom.getInstance().setCustomTextFieldBill(txtEndTime);

        txtUsedTime = new JTextField("");
        txtUsedTime.setBounds(588, 98, 150, 25);
        panel.add(txtUsedTime);
        txtUsedTime.setEditable(false);
        Custom.getInstance().setCustomTextFieldBill(txtUsedTime);

        //btnExportPdf.addActionListener(this);
        btnPayment.addActionListener(this);
        btnBack.addActionListener(this);

        allLoaded();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
//        if (o.equals(btnExportPdf)) {
//            Boolean result = null;
//            try {
//                result = ExportBill.getInstance().exportBillToPdf(bill, path);
//            } catch (DocumentException ex) {
//                throw new RuntimeException(ex);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//            String message = "";
//            int type = JOptionPane.INFORMATION_MESSAGE;
//            if (result) {
//                message = "Xuất file pdf thành công";
//                type = JOptionPane.INFORMATION_MESSAGE;
//            } else {
//                message = "Xuất file pdf thất bại";
//                type = JOptionPane.ERROR_MESSAGE;
//            }
//            JOptionPane.showMessageDialog(null, message, "Thông báo", type);
//        } else
        if (o.equals(btnPayment)) {
            boolean isPaid = billDAO.paymentBill(bill.getMaHoaDon(), bill.getThoiGianRa());
            if (isPaid) {
                paid = isPaid;
                //btnExportPdf.setEnabled(true);
                btnPayment.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Thanh toán hóa đơn thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                ArrayList<Bill> bills = billDAO.getAllBill();
                main.loadHD2(bills);
                String maHD = bill.getMaHoaDon();
                String maPhong = bill.getMaPhong().getMaPhong();
                String soGio = bill.tinhThoiGianSuDung();
                double giaPhong = bill.getMaPhong().getGiaPhong();
                DetailsOfBill dtbill = new DetailsOfBill(new Bill(maHD), new Room(maPhong), soGio, giaPhong);
                try {
                    detailOfBillDAO.insert(dtbill);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String pdfFilePath = path;
                try {
                    ExportBill.getInstance().exportBillToPdf(bill, pdfFilePath);
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi thanh toán vui lòng thử lại!!!", "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (o.equals(btnBack)) {
            this.dispose();

        }
    }

    public void setBillUI(Bill_UI main) {
        this.main = main;
    }

    /**
     * chạy tất cả các hàm khi bắt đầu chạy form
     */
    public void allLoaded() {
        reSizeColumnTableBillInfo();
        showBillInfo();
        showServiceOrders();
        showTotalBill();
    }

    /**
     * Chuyển số giờ thuê thành chuỗi dạng {@code x giờ y phút}
     *
     * @param hours {@code double}: số giờ thuê
     * @return {@code String}: chuỗi dạng {@code x giờ y phút}
     */
    private String convertRentalTime(double hours) {
        int minutes = (int) (hours % 1 * 60);
        int hoursInt = (int) hours;
        String minutesStr = minutes < 10 ? "0" + minutes : minutes + "";
        String hoursStr = hoursInt < 10 ? "0" + hoursInt : hoursInt + "";
        String time = String.format("%s giờ %s phút", hoursStr, minutesStr);
        return time;
    }

    /**
     * Hiển thị thông tin hóa đơn
     */
    private void showBillInfo() {
        TypeOfRoom roomType = bill.getMaPhong().getLoaiPhong();
        txtBillId.setText(bill.getMaHoaDon());
        txtStaffName.setText(bill.getMaNhanVien().getTenNhanVien());
        txtCustomerName.setText(bill.getMaKH().getTenKhachHang());
        txtRoomId.setText(bill.getMaPhong().getMaPhong());
        txtRoomTypeName.setText(roomType.getTenLoaiPhong());
        txtRoomPrice.setText(df.format(bill.getMaPhong().getGiaPhong()) + "/giờ");
        Timestamp startTime = bill.getThoiGianVao();
        String startTimeStr = ConvertTime.getInstance().convertTimeToString(startTime, formatTime);
        txtStartTime.setText(startTimeStr);
        Timestamp endTime = bill.getThoiGianRa();
        String endTimeStr = ConvertTime.getInstance().convertTimeToString(endTime, formatTime);
        txtEndTime.setText(endTimeStr);
        String usedTime = convertRentalTime(bill.tinhGioThue());
        txtUsedTime.setText(usedTime);
    }

    /**
     * Thêm khoảng trắng vào trước và sao chuỗi được truyền vào
     *
     * @param str {@code String}: chuỗi cần xử lý
     * @return {@code String}: chuỗi đã xử lý
     */
    private String addSpaceToString(String str) {
        return " " + str + " ";
    }

    /**
     * Hiển thị thông tin các dịch vụ đã đặt
     */
    private void showServiceOrders() {
        modelTblBillInfo.getDataVector().removeAllElements();
        modelTblBillInfo.fireTableDataChanged();
        List<DetailsOfService> serviceOrders = bill.getLstDetails();
        int i = 1;
        for (DetailsOfService item : serviceOrders) {
            Service service = item.getMaDichVu();
            String sttStr = df2.format(i++);
            String quantityStr = df2.format(item.getSoLuong());
            String priceStr = df.format(item.getGiaBan());
            String totalPriceStr = df.format(item.tinhTienDichVu());
            modelTblBillInfo.addRow(new Object[]{addSpaceToString(sttStr), addSpaceToString(service.getTenDichVu()),
                    addSpaceToString(quantityStr), addSpaceToString(priceStr), addSpaceToString(totalPriceStr)});
        }
    }

    /**
     * Hiển thị chi tiết tổng tiền của hóa đơn
     */
    private void showTotalBill() {
        double totalPriceService = bill.tinhTongTienDichVu();
        txtTotalPriceService.setText(df.format(totalPriceService));
        double totalPriceRoom = bill.tinhTienPhong();
        txtTotalPriceRoom.setText(df.format(totalPriceRoom));
        double km = 0;
        Date ngayHienTai = new Date();

        // Chuyển ngày hiện tại và ngày sinh của khách hàng thành lớp Calendar
        Calendar calendarHienTai = Calendar.getInstance();
        calendarHienTai.setTime(ngayHienTai);

        Calendar calendarNgaySinhKhachHang = Calendar.getInstance();
        calendarNgaySinhKhachHang.setTime(bill.getMaKH().getNgaySinh());

        // Lấy ngày và tháng của ngày hiện tại
        int ngayHienTaiValue = calendarHienTai.get(Calendar.DAY_OF_MONTH);
        int thangHienTaiValue = calendarHienTai.get(Calendar.MONTH);

        // Lấy ngày và tháng từ ngày sinh của khách hàng
        int ngaySinhKhachHangValue = calendarNgaySinhKhachHang.get(Calendar.DAY_OF_MONTH);
        int thangSinhKhachHangValue = calendarNgaySinhKhachHang.get(Calendar.MONTH);
        double vat = (totalPriceService + totalPriceRoom) * 0.08;
        if (ngayHienTaiValue == ngaySinhKhachHangValue && thangHienTaiValue == thangSinhKhachHangValue) {
            km = (totalPriceService + totalPriceRoom + vat) * 0.15;
            boolean b = billDAO.updateKM(bill.getMaHoaDon());
        } else {
            km = 0.0;
        }
        txtKM.setText(df.format(km));
        txtVAT.setText(df.format(vat));
        double totalPrice = bill.getTongTienHD() + vat - km;
        txtTotalPriceBill.setText(df.format(totalPrice));
    }

    /**
     * Thay đổi kích thước cột của table
     */
    private void reSizeColumnTableBillInfo() {
        tblTableBillInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModelTableBillInfo = tblTableBillInfo.getColumnModel();
        columnModelTableBillInfo.getColumn(0).setPreferredWidth(59);
        columnModelTableBillInfo.getColumn(1).setPreferredWidth(280);
        columnModelTableBillInfo.getColumn(2).setPreferredWidth(90);
        columnModelTableBillInfo.getColumn(3).setPreferredWidth(160);
        columnModelTableBillInfo.getColumn(4).setPreferredWidth(170);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        columnModelTableBillInfo.getColumn(0).setCellRenderer(centerRenderer);
        columnModelTableBillInfo.getColumn(2).setCellRenderer(centerRenderer);
        columnModelTableBillInfo.getColumn(3).setCellRenderer(rightRenderer);
        columnModelTableBillInfo.getColumn(4).setCellRenderer(rightRenderer);
    }

    /**
     * Hiển thị lấy kết quả thanh toán
     *
     * @return {@code boolean}: kết quả thanh toán
     * <ul>
     * <li>{@code true:} thanh toán thành công</li>
     * <li>{@code false:} thanh toán thất bại</li>
     * </ul>
     */
    public boolean getPaid() {
        return paid;
    }
}
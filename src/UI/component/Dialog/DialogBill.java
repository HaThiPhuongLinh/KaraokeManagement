package UI.component.Dialog;

import DAO.BillDAO;
import DAO.CustomerDAO;
import DAO.DetailOfServiceDAO;
import DAO.StaffDAO;
import Entity.*;
import UI.CustomUI.Custom;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.*;
import javax.swing.table.*;

//import Event_Handlers.ConvertTime;
//import Event_Handlers.ExportBill;



public class DialogBill extends JDialog implements ActionListener {
    private JTextField txtBillId, txtStaffName, txtCustomerName, txtRoomId, txtRoomTypeName, txtRoomPrice, txtStartTime,
            txtEndTime, txtUsedTime, txtTotalPriceService, txtTotalPriceRoom, txtVAT, txtTotalPriceBill;
    private JTable tblTableBillInfo;
    private DefaultTableModel modelTableBillInfo;
    private GradientPaint gra = new GradientPaint(0, 0, new Color(255, 255, 255), getWidth(), 0,
            Color.decode("#FAFFD1"));
    private JButton btnPayment, btnBack, btnExportPdf, btnExportExcel;

//    private ImageIcon logoApp = new ImageIcon(DialogBill.class.getResource(CustomUI.LOGO_APP));
//    private ImageIcon backIcon = new ImageIcon(DialogBill.class.getResource(CustomUI.BACK_ICON));
//    private ImageIcon paymentIcon = new ImageIcon(DialogBill.class.getResource(CustomUI.PAYMENT_ICON));
//    private ImageIcon pdfIcon = new ImageIcon(DialogBill.class.getResource(CustomUI.PDF_ICON));
//    private ImageIcon excelIcon = new ImageIcon(DialogBill.class.getResource(CustomUI.EXCEL_ICON));
//    private ImageIcon logoIcon = new ImageIcon(new ImageIcon(DialogBill.class.getResource(CustomUI.LOGO_ICON))
//            .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

    private String formatTime = "HH:mm:ss dd/MM/yyyy";
    private DecimalFormat df = new DecimalFormat("#,###.##");
    private final String WORKING_DIR = System.getProperty("user.dir");
    private String path = WORKING_DIR + "/bill/";
    private Bill bill =new Bill() ;
    private boolean paid = false;
    private BillDAO billDAO = BillDAO.getInstance();
    private StaffDAO staffDAO = StaffDAO.getInstance();
    private CustomerDAO customerDAO = CustomerDAO.getInstance();
    private DetailOfServiceDAO serviceDetailDAO = DetailOfServiceDAO.getInstance();

    /**
     * Khởi tạo giao diện thanh toán hóa đơn
     *
     * @param bill {@code HoaDon}: hóa đơn cần thanh toán
     */
    public DialogBill(Bill bill) {

        this.bill = bill;
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
//        setIconImage(logoApp.getImage());
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                // GradientPaint gra = new GradientPaint(179, 0, Color.decode("#5a189a"), 180,
                // getHeight(),
                // Color.decode("#7b2cbf"));
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
//                Image bgMain = logoIcon.getImage();
//                g2.drawImage(bgMain, 20, 20, 70, 70, null);
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

        JPanel pnTable = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#ffffff"));
                g2.drawRect(0, 0, 759, getHeight() - 1);

                g2.drawLine(0, 24, 759, 24);
                setFont(new Font("Dialog", Font.BOLD, 16));

            }
        };
        pnTable.setLayout(null);
        pnTable.setOpaque(false);
        pnTable.setBounds(10, 25, 760, 200);
        pnInfoService.add(pnTable);

        String[] colsBillInfo = { "STT", "Tên dịch vụ ", "SL", "Đơn giá", "Thành tiền" };
        modelTableBillInfo = new DefaultTableModel(colsBillInfo, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tblTableBillInfo = new JTable(modelTableBillInfo) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setFont(new Font("Dialog", Font.PLAIN, 14));
            }
        };
//        Custom.getInstance().setCustomTable(tblTableBillInfo);
        tblTableBillInfo.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));
        tblTableBillInfo.getTableHeader().setBackground(new Color(255, 255, 255, 0));
        tblTableBillInfo.getTableHeader().setForeground(Color.WHITE);
        tblTableBillInfo.setOpaque(false);
        tblTableBillInfo.setShowGrid(false);
        tblTableBillInfo.setRowHeight(25);
        JScrollPane scrTableBillInfo = new JScrollPane(tblTableBillInfo);
        scrTableBillInfo.setBounds(1, 1, 777, 199);
        pnTable.add(scrTableBillInfo);

        JLabel lblTotalPriceService = new JLabel("Tổng tiền dịch vụ:");
        Custom.getInstance().setCustomLabelBill(lblTotalPriceService);
        lblTotalPriceService.setBounds(40, 560, 140, 25);
        pnMain.add(lblTotalPriceService);

        txtTotalPriceService = new JTextField("");
        txtTotalPriceService.setEditable(false);
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
//       Custom.getInstance().setCustomTextFieldBill(txtTotalPriceRoom);
        txtTotalPriceRoom.setBounds(555, 585, 200, 25);
        txtTotalPriceRoom.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMain.add(txtTotalPriceRoom);

        JLabel lblVAT = new JLabel("VAT(10%):");
      Custom.getInstance().setCustomLabelBill(lblVAT);
        lblVAT.setBounds(40, 610, 140, 25);
        pnMain.add(lblVAT);

        txtVAT = new JTextField("");
//        Custom.getInstance().setCustomTextFieldBill(txtVAT);
        txtVAT.setBounds(555, 610, 200, 25);
        txtVAT.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMain.add(txtVAT);

        JLabel lblTotalPriceBill = new JLabel("Tổng cộng:");
        lblTotalPriceBill.setForeground(Color.WHITE);
        lblTotalPriceBill.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTotalPriceBill.setBounds(40, 635, 140, 25);
        pnMain.add(lblTotalPriceBill);

        txtTotalPriceBill = new JTextField("");
//        Custom.getInstance().setCustomTextFieldBill(txtTotalPriceBill);
        txtTotalPriceBill.setFont(new Font("Dialog", Font.BOLD, 16));
        txtTotalPriceBill.setColumns(10);
        txtTotalPriceBill.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTotalPriceBill.setBounds(555, 635, 200, 25);
        pnMain.add(txtTotalPriceBill);
        txtTotalPriceBill.setEditable(false);

        btnBack = new JButton("Quay lại");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
        btnBack.setBounds(40, 670, 130, 35);
        pnMain.add(btnBack);

        btnPayment = new JButton("Thanh toán");
        btnPayment.setFont(new Font("Dialog", Font.BOLD, 15));
        btnPayment.setBounds(240, 670, 130, 35);
        pnMain.add(btnPayment);

        btnExportPdf = new JButton( "Xuất PDF");
        btnExportPdf.setFont(new Font("Dialog", Font.BOLD, 15));
        btnExportPdf.setBounds(435, 670, 130, 35);
        btnExportPdf.setEnabled(false);
        pnMain.add(btnExportPdf);

        btnExportExcel = new JButton( "Xuất excel");
        btnExportExcel.setFont(new Font("Dialog", Font.BOLD, 15));
        btnExportExcel.setBounds(625, 670, 130, 35);
        btnExportExcel.setEnabled(false);
        pnMain.add(btnExportExcel);

        JLabel txtPhoneNumber = new JLabel("0303.030.303");
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
//       Custom.getInstance().setCustomTextFieldBill(txtBillId);
        panel.add(txtBillId);

        txtStaffName = new JTextField("");
        txtStaffName.setBounds(160, 48, 220, 25);
        panel.add(txtStaffName);
        txtStaffName.setEditable(false);
//      Custom.getInstance().setCustomTextFieldBill(txtStaffName);

        txtCustomerName = new JTextField("");
        txtCustomerName.setBounds(160, 73, 220, 25);
        panel.add(txtCustomerName);
        txtCustomerName.setEditable(false);
//       Custom.getInstance().setCustomTextFieldBill(txtCustomerName);

        txtRoomId = new JTextField("");
        txtRoomId.setBounds(160, 98, 220, 25);
        txtRoomId.setEditable(false);
        panel.add(txtRoomId);
//        Custom.getInstance().setCustomTextFieldBill(txtRoomId);

        txtRoomTypeName = new JTextField("");
        txtRoomTypeName.setBounds(160, 123, 220, 25);
        txtRoomTypeName.setEditable(false);
        panel.add(txtRoomTypeName);
//        Custom.getInstance().setCustomTextFieldBill(txtRoomTypeName);

        txtRoomPrice = new JTextField("");
        txtRoomPrice.setBounds(588, 23, 150, 25);
        txtRoomPrice.setEditable(false);
        panel.add(txtRoomPrice);
//       Custom.getInstance().setCustomTextFieldBill(txtRoomPrice);

        txtStartTime = new JTextField("");
        txtStartTime.setBounds(588, 48, 150, 25);
        panel.add(txtStartTime);
        txtStartTime.setEditable(false);
//        Custom.getInstance().setCustomTextFieldBill(txtStartTime);

        txtEndTime = new JTextField("");
        txtEndTime.setBounds(588, 73, 150, 25);
        panel.add(txtEndTime);
        txtEndTime.setEditable(false);
//        Custom.getInstance().setCustomTextFieldBill(txtEndTime);

        txtUsedTime = new JTextField("");
        txtUsedTime.setBounds(588, 98, 150, 25);
        panel.add(txtUsedTime);
        txtUsedTime.setEditable(false);
//       Custom.getInstance().setCustomTextFieldBill(txtUsedTime);

        btnExportExcel.addActionListener(this);
        btnExportPdf.addActionListener(this);
        btnPayment.addActionListener(this);
        btnBack.addActionListener(this);

        allLoaded();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
//        if (o.equals(btnExportExcel)) {
//            Boolean result = ExportBill.getInstance().exportBillToExcel(bill, path);
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
//        } else if (o.equals(btnExportPdf)) {
//            Boolean result = ExportBill.getInstance().exportBillToPdf(bill, path);
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
//          if (o.equals(btnPayment))
//
//    {
//        boolean isPaid = billDAO.makePayment(bill.getMaHoaDon(), bill.getTongTienHD(), bill.getNgayGioTra());
//        if (isPaid) {
//            paid = isPaid;
//            btnExportExcel.setEnabled(true);
//            btnExportPdf.setEnabled(true);
//            btnPayment.setEnabled(false);
//            JOptionPane.showMessageDialog(null, "Thanh toán hóa đơn thành công", "Thông báo",
//                    JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, "Lỗi khi thanh toán vui lòng thử lại!!!", "Thông báo",
//                    JOptionPane.ERROR_MESSAGE);
//        }

//    }else
   if (o.equals(btnBack)) {
            this.dispose();
        }
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
        txtRoomPrice.setText(df.format(bill.getMaPhong().getGiaPhong()) + " đ/giờ");
        Timestamp startTime = bill.getNgayGioDat();
        String startTimeStr = ConvertTime.getInstance().convertTimeToString(startTime, formatTime);
        txtStartTime.setText(startTimeStr);
        Timestamp endTime = bill.getNgayGioTra();
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
        modelTableBillInfo.getDataVector().removeAllElements();
        modelTableBillInfo.fireTableDataChanged();
        List<DetailsOfService> serviceOrders = bill.getLstDetails();
        int i = 1;
        for (DetailsOfService item : serviceOrders) {
            Service service = item.getMaDichVu();
            String sttStr = df.format(i++);
            String quantityStr = df.format(item.getSoLuong());
            String priceStr = df.format(item.getGiaBan());
            String totalPriceStr = df.format(item.tinhTienDichVu());
            modelTableBillInfo.addRow(new Object[] { addSpaceToString(sttStr), addSpaceToString(service.getTenDichVu()),
                    addSpaceToString(quantityStr), addSpaceToString(priceStr), addSpaceToString(totalPriceStr) });
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
        double vat = (totalPriceService + totalPriceRoom) * 0.1;
        txtVAT.setText(df.format(vat));
        double totalPrice = bill.getTongTienHD();
        txtTotalPriceBill.setText(df.format(totalPrice));
    }

    /**
     * Thay đổi kích thước cột của table
     */
    private void reSizeColumnTableBillInfo() {
        tblTableBillInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModelTableBillInfo = tblTableBillInfo.getColumnModel();
        columnModelTableBillInfo.getColumn(0).setPreferredWidth(59);
        columnModelTableBillInfo.getColumn(1).setPreferredWidth(320);
        columnModelTableBillInfo.getColumn(2).setPreferredWidth(70);
        columnModelTableBillInfo.getColumn(3).setPreferredWidth(150);
        columnModelTableBillInfo.getColumn(4).setPreferredWidth(160);

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
     *         <ul>
     *         <li>{@code true:} thanh toán thành công</li>
     *         <li>{@code false:} thanh toán thất bại</li>
     *         </ul>
     */
    public boolean getPaid() {
        return paid;
    }
}

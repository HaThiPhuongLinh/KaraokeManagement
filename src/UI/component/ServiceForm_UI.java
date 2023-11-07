package UI.component;

import ConnectDB.ConnectDB;
import DAO.*;
import Entity.*;
import UI.CustomUI.Custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServiceForm_UI extends JPanel implements ActionListener, MouseListener, ItemListener {
    public static Staff staffLogin = null;
    private DefaultTableModel serviceModel, modelService;
    private JPanel pnlShowRoom, pnlRoomList, pnlServiceList, pnlShowService, pnlServiceControl, pnlServiceDetail, timeNow, pnlRoomControl, pnlSelect;
    private JLabel backgroundLabel, timeLabel, searchLabel, searchServiceLable, tOSLabel, quantityLabel, nameLable, stockLabel, sumLabel, returnLabel;
    private JTextField txtFind, txtFindService, txtName, txtStock, txtSum;
    private JScrollPane scrShowRoom, scrShowService, scrShowServiceDetail;
    private JSpinner txtQuantity, txtReturn;
    private JButton btnFindRoom, btnFindService, btnUse, btnReturn, btnRefresh1, btnRefresh2;
    private JButton[] btnRoomList;
    private int heightTable = 140;
    private int location = -1;
    private JTable tblService, tblSC;
    private JComboBox<String> cboService;
    private TypeOfRoomDAO typeOfRoomDAO;
    private TypeOfServiceDAO typeOfServiceDAO;
    private ServiceDAO serviceDAO;
    private RoomDAO roomDAO;
    private BillDAO billDAO;
    private boolean isDoubleClick = false;
    private int selectedServiceIndex = -1;
    private int selectedServiceOrderIndex = -1;
    private ArrayList<Service> serviceList = new ArrayList<Service>();
    private ArrayList<Service> serviceOrderList = new ArrayList<Service>();
    private DetailsOfService detailsOfService;
    private DetailOfServiceDAO detailOfServiceDAO;
    private DecimalFormat df = new DecimalFormat("#,###.##");
    private JTable selectedTable;

    public ServiceForm_UI(Staff staff) {
        this.staffLogin = staff;
        setLayout(null);
        setBounds(0, 0, 1475, 770);

        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        typeOfRoomDAO = new TypeOfRoomDAO();
        typeOfServiceDAO = new TypeOfServiceDAO();
        serviceDAO = new ServiceDAO();
        roomDAO = new RoomDAO();
        billDAO = new BillDAO();
        detailOfServiceDAO = new DetailOfServiceDAO();

        timeNow = new JPanel();
        timeNow.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP));
        timeNow.setBounds(12, 10, 300, 50);
        timeNow.setOpaque(false);
        add(timeNow);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 33));
        timeLabel.setForeground(Color.WHITE);
        timeNow.add(timeLabel);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();

        //Room
        pnlRoomList = new JPanel();
        pnlRoomList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Phòng", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        pnlRoomList.setBounds(10, 70, 500, 360);
        pnlRoomList.setOpaque(false);
        add(pnlRoomList);
        pnlRoomList.setLayout(new BorderLayout(0, 0));

        pnlRoomControl = new JPanel();
        pnlRoomControl.setOpaque(false);
        pnlRoomControl.setBackground(Color.WHITE);
        pnlRoomList.add(pnlRoomControl, BorderLayout.NORTH);
        pnlRoomControl.setLayout(null);
        pnlRoomControl.setPreferredSize(new Dimension(330, 55));

        searchLabel = new JLabel("Mã phòng: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchLabel.setBounds(20, 17, 85, 20);
        searchLabel.setForeground(Color.WHITE);
        pnlRoomControl.add(searchLabel);

        txtFind = new JTextField();
        txtFind.setBounds(106, 14, 140, 28);
        pnlRoomControl.add(txtFind);

        btnFindRoom = new JButton("Tìm");
        btnFindRoom.setFont(new Font("Arial", Font.BOLD, 14));
        Custom.setCustomBtn(btnFindRoom);
        btnFindRoom.setBounds(260, 13, 90, 30);
        pnlRoomControl.add(btnFindRoom);

        btnRefresh1 = new JButton("Làm mới");
        btnRefresh1.setFont(new Font("Arial", Font.BOLD, 14));
        Custom.setCustomBtn(btnRefresh1);
        btnRefresh1.setBounds(360, 13, 105, 30);
        pnlRoomControl.add(btnRefresh1);

        pnlShowRoom = new JPanel();
        pnlShowRoom.setOpaque(false);
        pnlShowRoom.setBackground(Color.WHITE);
        FlowLayout flShowRoom = new FlowLayout(FlowLayout.LEFT);
        flShowRoom.setHgap(6);
        flShowRoom.setVgap(6);
        pnlShowRoom.setLayout(flShowRoom);
        pnlShowRoom.setPreferredSize(new Dimension(310, 140));

        scrShowRoom = new JScrollPane(pnlShowRoom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrShowRoom.setOpaque(false);
        scrShowRoom.getViewport().setOpaque(false);
        scrShowRoom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        scrShowRoom.setBackground(Color.WHITE);
        scrShowRoom.getVerticalScrollBar().setUnitIncrement(10);
        pnlRoomList.add(scrShowRoom, BorderLayout.CENTER);

        pnlServiceList = new JPanel();
        pnlServiceList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dịch vụ", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        pnlServiceList.setBounds(520, 10, 730, 420);
        pnlServiceList.setOpaque(false);
        add(pnlServiceList);
        pnlServiceList.setLayout(new BorderLayout(0, 0));

        pnlServiceControl = new JPanel();
        pnlServiceControl.setOpaque(false);
        pnlServiceControl.setBackground(Color.WHITE);
        pnlServiceList.add(pnlServiceControl, BorderLayout.NORTH);
        pnlServiceControl.setLayout(null);
        pnlServiceControl.setPreferredSize(new Dimension(330, 95));

        tOSLabel = new JLabel("Loại dịch vụ: ");
        tOSLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        tOSLabel.setBounds(20, 17, 85, 20);
        tOSLabel.setForeground(Color.WHITE);
        pnlServiceControl.add(tOSLabel);

        cboService = new JComboBox<String>();
        cboService.setBounds(126, 14, 170, 28);
        Custom.setCustomComboBox(cboService);
        pnlServiceControl.add(cboService);

        searchServiceLable = new JLabel("Tên dịch vụ: ");
        searchServiceLable.setFont(new Font("Arial", Font.PLAIN, 14));
        searchServiceLable.setBounds(20, 57, 85, 20);
        searchServiceLable.setForeground(Color.WHITE);
        pnlServiceControl.add(searchServiceLable);

        txtFindService = new JTextField();
        txtFindService.setBounds(126, 54, 170, 28);
        pnlServiceControl.add(txtFindService);

        btnFindService = new JButton("Tìm");
        btnFindService.setFont(new Font("Arial", Font.BOLD, 14));
        Custom.setCustomBtn(btnFindService);
        btnFindService.setBounds(330, 54, 105, 30);
        pnlServiceControl.add(btnFindService);

        btnRefresh2 = new JButton("Làm mới");
        btnRefresh2.setFont(new Font("Arial", Font.BOLD, 14));
        Custom.setCustomBtn(btnRefresh2);
        btnRefresh2.setBounds(460, 54, 105, 30);
        pnlServiceControl.add(btnRefresh2);

        pnlShowService = new JPanel();
        pnlShowService.setOpaque(false);
        pnlShowService.setBackground(Color.WHITE);
        pnlShowService.setPreferredSize(new Dimension(310, 140));

        String[] colsService2 = {"Mã dịch vụ", "Tên dịch vụ", "Đơn vị tính", "Số lượng tồn", "Giá bán"};
        serviceModel = new DefaultTableModel(colsService2, 0);

        tblSC = new JTable(serviceModel);
        Custom.getInstance().setCustomTable(tblSC);

        tblSC.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblSC.getSelectedRow();
                Service service = serviceList.get(selectedRow);
                selectedServiceIndex = selectedRow;
                txtName.setText(service.getTenDichVu());
                txtStock.setText(df.format(service.getSoLuongTon()));
                double price = Double.parseDouble(serviceModel.getValueAt(selectedRow, 4).toString().trim().replace(",", ""));
                txtSum.setText(df.format(1*price));

                selectedTable = tblSC;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        pnlServiceList.add(scrShowService = new JScrollPane(tblSC, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        scrShowService.getViewport().setOpaque(false);

        pnlServiceDetail = new JPanel();
        pnlServiceDetail.setLayout(null);
        pnlServiceDetail.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        pnlServiceDetail.setBounds(13, 440, 710, 295);
        pnlServiceDetail.setOpaque(false);
        add(pnlServiceDetail);

        String[] colsService = {"STT", "Tên dịch vụ", "Đơn vị tính", "Số lượng", "Giá bán", "Thành tiền"};
        modelService = new DefaultTableModel(colsService, 0);

        tblService = new JTable(modelService);
        Custom.getInstance().setCustomTable(tblService);

        tblService.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblService.getSelectedRow();
                selectedServiceOrderIndex = selectedRow;
                txtName.setText(modelService.getValueAt(selectedRow, 1).toString().trim());
                String quantityString = modelService.getValueAt(selectedRow, 3).toString();
                quantityString = quantityString.replaceAll(" ", "").replaceAll(",", "");
                int quantity = Integer.parseInt(quantityString);
                txtQuantity.setValue(quantity);
                int quantityService = serviceOrderList.get(selectedRow).getSoLuongTon();
                txtStock.setText(String.valueOf(quantityService));
                txtSum.setText(modelService.getValueAt(selectedRow, 5).toString().trim());

                selectedTable = tblService;

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });


        pnlServiceDetail.add(scrShowServiceDetail = new JScrollPane(tblService, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        scrShowServiceDetail.setBounds(4, 10, 700, 280);
        scrShowServiceDetail.setOpaque(false);
        scrShowServiceDetail.getViewport().setOpaque(false);
        scrShowServiceDetail.getViewport().setBackground(Color.WHITE);

        pnlSelect = new JPanel();
        pnlSelect.setLayout(null);
        pnlSelect.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        pnlSelect.setBounds(730, 440, 520, 290);
        pnlSelect.setOpaque(false);
        add(pnlSelect);

        nameLable = new JLabel("Dịch vụ: ");
        nameLable.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLable.setBounds(20, 37, 85, 20);
        nameLable.setForeground(Color.WHITE);
        pnlSelect.add(nameLable);

        txtName = new JTextField();
        txtName.setBounds(100, 33, 185, 28);
        txtName.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlSelect.add(txtName);

        quantityLabel = new JLabel("Số lượng: ");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityLabel.setBounds(20, 93, 85, 20);
        quantityLabel.setForeground(Color.WHITE);
        pnlSelect.add(quantityLabel);

        txtQuantity = new JSpinner();
        txtQuantity.setBounds(100, 91, 185, 28);
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) txtQuantity.getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.LEFT);
        txtQuantity.setValue(1);
        pnlSelect.add(txtQuantity);

        stockLabel = new JLabel("SL tồn: ");
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setBounds(20, 147, 85, 20);
        stockLabel.setForeground(Color.WHITE);
        pnlSelect.add(stockLabel);

        txtStock = new JTextField();
        txtStock.setBounds(100, 143, 185, 28);
        txtStock.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlSelect.add(txtStock);

        sumLabel = new JLabel("Tổng tiền: ");
        sumLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sumLabel.setBounds(20, 207, 85, 20);
        sumLabel.setForeground(Color.WHITE);
        pnlSelect.add(sumLabel);

        txtSum = new JTextField();
        txtSum.setBounds(100, 204, 185, 28);
        txtSum.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlSelect.add(txtSum);

        btnUse = new JButton("Thêm");
        btnUse.setFont(new Font("Arial", Font.BOLD, 14));
        Custom.setCustomBtn(btnUse);
        btnUse.setBounds(330, 95, 110, 30);
        pnlSelect.add(btnUse);

        btnReturn = new JButton("Trả");
        btnReturn.setFont(new Font("Arial", Font.BOLD, 14));
        Custom.setCustomBtn(btnReturn);
        btnReturn.setBounds(330, 145, 110, 30);
        pnlSelect.add(btnReturn);


        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/background.png"));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);


        btnFindService.addActionListener(this);
        btnFindRoom.addActionListener(this);
        btnRefresh1.addActionListener(this);
        btnRefresh2.addActionListener(this);
        btnUse.addActionListener(this);
        btnReturn.addActionListener(this);
        cboService.addMouseListener(this);
        cboService.addItemListener(this);

        allLoaded();

        txtFindService.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnFindService.doClick();
                }
            }
        });

        txtQuantity.addChangeListener(e -> {
            if (selectedTable == tblSC) {
                // Xử lý cho tblSC
                int row = tblSC.getSelectedRow();
                if (row != -1) {
                    int quantity = (int) txtQuantity.getValue();
                    int soLuongTon = Integer.parseInt(serviceModel.getValueAt(row, 3).toString().replace(",", ""));
                    if (quantity > soLuongTon) {
                        txtQuantity.setValue(soLuongTon);
                    } else if (quantity < 1) {
                        txtQuantity.setValue(1);
                    } else {
                        double giaBan = Double.parseDouble(serviceModel.getValueAt(row, 4).toString().replace(",", ""));
                        double sum = giaBan * quantity;
                        txtSum.setText(df.format(sum));
                    }
                }
            } else if (selectedTable == tblService) {
                // Xử lý cho tblService
                int row = tblService.getSelectedRow();
                if (row != -1) {
                    int quantity = (int) txtQuantity.getValue();
                    int soLuongTon = Integer.parseInt(txtStock.getText());
                    if (quantity > soLuongTon) {
                        txtQuantity.setValue(soLuongTon);
                    } else if (quantity < 1) {
                        txtQuantity.setValue(1);
                    } else {
                        double giaBan = Double.parseDouble(modelService.getValueAt(row, 4).toString().replace(",", ""));
                        double sum = giaBan * quantity;
                        txtSum.setText(df.format(sum));
                    }
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
        if (o.equals(cboService)) {
            txtFindService.setText("");
            String serviceTypeName = cboService.getSelectedItem().toString().trim();
            serviceList = serviceDAO.getServiceListByServiceTypeName(serviceTypeName);
            loadServiceList(serviceList);
        }
    }

    public void allLoaded() {
        ArrayList<Room> roomsInUse = roomDAO.getRoomsByStatus("Đang sử dụng");
        loadRoomList(roomsInUse);
        reSizeColumnTableService();
        reSizeColumnTableService2();
        loadCboService();
        String serviceName = cboService.getSelectedItem().toString();
        serviceList = serviceDAO.getServiceListByServiceTypeName(serviceName);
        loadServiceList(serviceList);
    }

    private void loadCboService() {
        java.util.List<TypeOfService> dataList = typeOfServiceDAO.getAllLoaiDichVu();
        for (TypeOfService serviceType : dataList) {
            cboService.addItem(serviceType.getTenLoaiDichVu());
        }
    }

    private void loadRoom(String roomID1) {
        Room room = roomDAO.getRoomByRoomId(roomID1);
        if (room == null) room = new Room();
        String statusP = room.getTinhTrang();
        String roomID = room.getMaPhong();
        String btnName = "<html><p style='text-align: center;'> " + roomID + " </p></br><p style='text-align: center;'> " + statusP + " </p></html>";
        int index = 0;
        for (int i = 0; i < btnRoomList.length; i++) {
            if (btnRoomList[i].getText().contains(roomID)) {
                index = i;
            } else if (btnRoomList[i].getText().equals("")) {
                index = i;
                break;
            }
        }
        btnRoomList[index].setText(btnName);
        btnRoomList[index].setForeground(Color.WHITE);
        btnRoomList[index].setFont(new Font("Dialog", Font.BOLD, 12));
        btnRoomList[index].setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRoomList[index].setHorizontalTextPosition(SwingConstants.CENTER);
        btnRoomList[index].setPreferredSize(new Dimension(110, 110));
        btnRoomList[index].setIcon(new ImageIcon(getClass().getResource("/images/room.png")));
        btnRoomList[index].setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRoomList[index].setBackground(Color.decode("#008000"));
        pnlShowRoom.revalidate();
        pnlShowRoom.repaint();
    }

    /**
     * Hiển thị danh sách phòng được truyền vào
     *
     * @param {@code ArrayList<Room>}: danh sách phòng cần hiển thị
     */
    private void loadRoomList(ArrayList<Room> listRoom) {
        pnlShowRoom.removeAll();
        pnlShowRoom.revalidate();
        pnlShowRoom.repaint();
        Border lineRed = new LineBorder(Color.RED, 2);
        Border lineGray = new LineBorder(Color.GRAY, 1);

        int sizeListRoom = listRoom.size();
        btnRoomList = new JButton[sizeListRoom];

        for (int i = 0; i < sizeListRoom; i++) {
            int selection = i;
            String roomID = listRoom.get(i).getMaPhong();

            btnRoomList[selection] = new JButton("");
            loadRoom(listRoom.get(i).getMaPhong());
            btnRoomList[selection].setBorder(lineGray);
            if ((i + 1) % 4 == 0) {
                heightTable += 110;
                pnlShowRoom.setPreferredSize(new Dimension(0, heightTable));
            }
            btnRoomList[selection].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (location != -1) {
                        btnRoomList[location].setBorder(lineGray);
                    }
                    txtFind.setText(roomID);
                    location = selection;
                    btnRoomList[selection].setBorder(lineRed);
                    showBillInfo(roomID);
                }
            });

            btnRoomList[selection].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    btnRoomList[selection].setBackground(Color.decode("#bbdefc"));
                    btnRoomList[selection].setForeground(Color.decode("#1a66e3"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btnRoomList[selection].setBackground(Color.decode("#008000"));
                    btnRoomList[selection].setForeground(Color.WHITE);
                }

            });
            pnlShowRoom.add(btnRoomList[selection]);
        }
    }

    /**
     * Thay đổi kích thước các cột trong bảng dịch vụ
     */
    private void reSizeColumnTableService() {
        TableColumnModel tcm = tblSC.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(120);
        tcm.getColumn(2).setPreferredWidth(30);
        tcm.getColumn(3).setPreferredWidth(40);
        tcm.getColumn(4).setPreferredWidth(40);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        tcm.getColumn(0).setCellRenderer(leftRenderer);
        tcm.getColumn(3).setCellRenderer(rightRenderer);
        tcm.getColumn(4).setCellRenderer(rightRenderer);
    }

    private void reSizeColumnTableService2() {
        TableColumnModel tcm = tblService.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(10);
        tcm.getColumn(1).setPreferredWidth(140);
        tcm.getColumn(2).setPreferredWidth(30);
        tcm.getColumn(3).setPreferredWidth(20);
        tcm.getColumn(4).setPreferredWidth(40);
        tcm.getColumn(5).setPreferredWidth(40);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        tcm.getColumn(0).setCellRenderer(leftRenderer);
        tcm.getColumn(3).setCellRenderer(rightRenderer);
        tcm.getColumn(4).setCellRenderer(rightRenderer);
        tcm.getColumn(5).setCellRenderer(rightRenderer);
    }


    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnFindService) || o.equals(btnRefresh2)) {
            searchService(0);
            if (o.equals(btnRefresh2)) {
                searchService(1);
                txtFindService.setText("");
            }
        }
        if (o.equals(btnFindRoom)) {
            String roomID = txtFind.getText().trim();
            if (roomID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa nhập mã phòng");
            } else {
                ArrayList roomInUse = roomDAO.getRoomsByRoomIdAndStatus(roomID, "Đang sử dụng");
                if (roomInUse.size() == 0) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy");
                } else {
                    loadRoomListByRoomID(roomID);
                }
            }
        }
        if (o.equals(btnRefresh1)) {
            txtFind.setText("");
            ArrayList<Room> roomsInUse = roomDAO.getRoomsByStatus("Đang sử dụng");
            loadRoomList(roomsInUse);
            modelService.setRowCount(0);
            modelService.getDataVector().removeAllElements();
        }
        if (o.equals(btnUse)) {
            if (txtName.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dịch vụ");
            } else if (txtFind.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần phải chọn phòng");
            } else {
                int orderQuantity = (int) txtQuantity.getValue();
                if (isDoubleClick) {
                    orderQuantity = 1;
                    isDoubleClick = false;
                }
                String quantityInStockStr = txtStock.getText().replace(",", "").trim();
                int quantityInStock = Integer.parseInt(quantityInStockStr);
                String message = "";

                if (quantityInStock <= 0) {
                    message = "Dịch vụ đã hết";
                    JOptionPane.showMessageDialog(this, message, "Cảnh bảo", JOptionPane.ERROR_MESSAGE);
                } else {
                    String typeMessage = "Thêm";
                    Service service = new Service();
                    if (selectedServiceIndex != -1) {
                        service = serviceList.get(selectedServiceIndex);
                    } else if (selectedServiceOrderIndex != -1) {
                        service = serviceOrderList.get(selectedServiceOrderIndex);
                    }

                    String roomID = txtFind.getText().trim();
                    Bill bill = billDAO.getBillByRoomID(roomID);
                    boolean result = false;
                    message = typeMessage + " dịch vụ thất bại";
                    boolean isUpdate = false;
                    if (!bill.getMaHoaDon().equalsIgnoreCase("")) {
                        DetailsOfService serviceDetail = detailOfServiceDAO.getDetailsOfServiceByBillIdAndServiceId(bill.getMaHoaDon(),
                                service.getMaDichVu());
                        // nếu ctDichVu không tồn tại thì thêm mới
                        // nếu ctDichVu đã tồn tại thì cập nhật
                        // Thêm mới
                        int newOrderQuantity = 0;
                        if (serviceDetail == null) {
                            isUpdate = false;
                            double servicePrice = service.getGiaBan();
                            serviceDetail = new DetailsOfService(bill, service, orderQuantity, servicePrice);
                            result = detailOfServiceDAO.updateServiceDetail(serviceDetail, orderQuantity,
                                    bill.getMaHoaDon());
                            // cập nhật

                        } else {
                            int newQuantity = quantityInStock - orderQuantity;
                            newOrderQuantity = serviceDetail.getSoLuong() + orderQuantity;
                            if (serviceDetail.getSoLuong() > 0 && newQuantity >= 0) {
                                isUpdate = true;
                                serviceDetail.setSoLuong(newOrderQuantity);
                                result = detailOfServiceDAO.updateServiceDetail(serviceDetail, orderQuantity,
                                        bill.getMaHoaDon());
                            } else {
                                message = "Số lượng đặt phải nhỏ hơn số lượng hiện có. Vui lòng nhập lại";
                            }
                        }
                        // kiểm tra kết quả thêm, cập nhật
                        if (result) {
                            if (isUpdate) {
                                int lastIndex = serviceOrderList.size() - 1;
                                for (int i = 0; i < lastIndex; i++) {
                                    Service serviceOrder = serviceOrderList.get(i);
                                    if (service.getMaDichVu().equals(serviceOrder.getMaDichVu())) {
                                        serviceOrder.setSoLuongTon(newOrderQuantity);
                                        break;
                                    }
                                }
                            } else {
                                serviceOrderList.add(service);
                            }
                            int newQuantity = quantityInStock - orderQuantity;
                            showBillInfo(roomID);
                            searchService(1);
                            txtQuantity.setValue(1);
                            txtStock.setText(String.valueOf(newQuantity));
                        } else {
                            JOptionPane.showMessageDialog(this, message);
                        }
                    } else {
                        message = "Hóa đơn không tồn tại";
                        JOptionPane.showMessageDialog(this, message);
                    }
                }

            }
        }
        if (o.equals(btnReturn)) {
            if (txtName.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dịch vụ");
            } else if (txtFind.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần phải chọn phòng");
            } else {
                int cancelQuantity = (int) txtQuantity.getValue();
                String message = "";
                Service service = serviceOrderList.get(selectedServiceOrderIndex);
                String roomID = txtFind.getText().trim();
                Bill bill = billDAO.getBillByRoomID(roomID);
                boolean result = false;
                if (!bill.getMaHoaDon().equalsIgnoreCase("")) {
                    DetailsOfService serviceDetail = detailOfServiceDAO.getDetailsOfServiceByBillIdAndServiceId(bill.getMaHoaDon(),
                            service.getMaDichVu());
                    // nếu ctDichVu đã tồn tại thì cập nhật
                    // nếu ctDichVu không tồn tại thì thông báo lỗi
                    int newOrderQuantity = 0;
                    int quantityInStock = 0;
                    if (serviceDetail != null) {
                        newOrderQuantity = serviceDetail.getSoLuong() - cancelQuantity;
                        if (newOrderQuantity < 0 || newOrderQuantity > service.getSoLuongTon()) {
                            JOptionPane.showMessageDialog(this, "Số lượng hủy không hợp lệ.");
                            return;
                        }
                        serviceDetail.setSoLuong(newOrderQuantity);
                        quantityInStock = service.getSoLuongTon();
                        service.setSoLuongTon(quantityInStock + cancelQuantity);
                        result = false;
                        result = detailOfServiceDAO.updateServiceDetail(serviceDetail, (-1) * cancelQuantity,
                                bill.getMaHoaDon());
                    } else {
                        message = "Dịch vụ này chưa được đặt";
                    }
                    // kiểm tra kết quả thêm, cập nhật
                    if (result) {
                        if (newOrderQuantity <= 0) {
                            serviceOrderList.remove(service);
                        } else {
                            quantityInStock = quantityInStock + cancelQuantity;
                            serviceOrderList.get(selectedServiceOrderIndex).setSoLuongTon(quantityInStock);
                        }
                        showBillInfo(roomID);
                        searchService(1);
                        txtQuantity.setValue(1);
                        txtStock.setText(String.valueOf(service.getSoLuongTon()));
                    } else {
                        JOptionPane.showMessageDialog(this, message);
                    }
                } else {
                    message = "Hóa đơn không tồn tại, vui lòng thử lại";
                    JOptionPane.showMessageDialog(this, message);
                }
            }
        }
    }

    private void searchService(int isRefresh) {
        String serviceName = txtFindService.getText().trim();
        String serviceTypeName = cboService.getSelectedItem().toString().trim();
        if (serviceName.equalsIgnoreCase("")) {
            serviceList = serviceDAO.getServiceListByServiceTypeName(serviceTypeName);
        } else {
            if (isRefresh == 1) {
                serviceList = serviceDAO.getServiceListByServiceTypeName(serviceTypeName);
            } else
                serviceList = serviceDAO.getServiceListByNameAndServiceTypeName(serviceName,
                        serviceTypeName);
        }
        loadServiceList(serviceList);
    }

    private void loadServiceList(ArrayList<Service> dataList) {
        serviceModel.getDataVector().removeAllElements();
        serviceModel.fireTableDataChanged();
        for (Service dv : dataList) {
            Object[] rowData = {dv.getMaDichVu(), dv.getTenDichVu(), dv.getDonViTinh(), dv.getSoLuongTon(), df.format(dv.getGiaBan())};
            serviceModel.addRow(rowData);
        }
    }

    private void loadRoomListByRoomID(String roomID) {
        ArrayList<Room> dataList = new ArrayList<Room>();
        if (txtFind.getText().equalsIgnoreCase(roomID)) {
            location = -1;
            dataList = roomDAO.getRoomsByRoomIdAndStatus(roomID, "Đang sử dụng");
        }

        loadRoomList(dataList);
    }

    private void showBillInfo(String maPhong) {
        ArrayList<DetailsOfService> dataList = detailOfServiceDAO.getDetailsOfServiceListByRoomId(maPhong);
        int i = 1;
        modelService.getDataVector().removeAllElements();
        modelService.fireTableDataChanged();
        Double totalPrice = 0.0;
        serviceOrderList.clear();
        for (DetailsOfService item : dataList) {
            Service service = item.getMaDichVu();
            serviceOrderList.add(service);
            // hiển thị lại phòng đã chọn lúc đầu
            if (selectedServiceOrderIndex <= -1) {
                if (selectedServiceOrderIndex == i) {
                    tblService.getSelectionModel().addSelectionInterval(i - 1, i - 1);
                }
            }
            String stt = df.format(i++);
            String totalPriceStr = df.format(item.getSoLuong() * item.getGiaBan());
            String priceStr = df.format(item.getGiaBan());
            String quantityStr = df.format(item.getSoLuong());
            modelService.addRow(new Object[]{stt, addSpaceToString(service.getTenDichVu()), addSpaceToString(service.getDonViTinh()),
                    addSpaceToString(quantityStr), addSpaceToString(priceStr), addSpaceToString(totalPriceStr)});
        }
    }

    private String addSpaceToString(String str) {
        return " " + str + " ";
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date());
        timeLabel.setText(time);
    }
}

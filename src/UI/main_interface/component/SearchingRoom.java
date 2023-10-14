package UI.main_interface.component;

import ConnectDB.ConnectDB;
import DAOs.RoomDAO;
import Entity.Room;
import UI.CustomUI.Custom;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchingRoom extends JPanel {
    private  JTable tableP;
    private  DefaultTableModel modelTableP;
    private JLabel backgroundLabel, timeLabel, search1Label, search2Label, search3Label;
    private JTextField txtSearch1, txtSearch2, txtSearch3;
    private JPanel timeNow, pnlCusList, pnlCusControl, pnlCusListRight;
    private DefaultTableModel tableModel;
    private JButton btnTim;
    private RoomDAO RoomDAO;

    public SearchingRoom() {
        setLayout(null);
        setBounds(0, 0, 1175, 770);
        RoomDAO = new RoomDAO();
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //phan viet code
        JLabel headerLabel = new JLabel("TÌM KIẾM PHÒNG");
        headerLabel.setBounds(470, 10, 1175, 40);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        headerLabel.setForeground(Color.WHITE);
        Component add = add(headerLabel);

        timeNow = new JPanel();
        timeNow.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
                TitledBorder.LEADING, TitledBorder.TOP));
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

        pnlCusList = new JPanel();
        pnlCusList.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "PHÒNG",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        pnlCusList.setBounds(10, 70, 1120, 620);
        pnlCusList.setOpaque(false);
        add(pnlCusList);
        pnlCusList.setLayout(new BorderLayout(0, 0));


        pnlCusControl = new JPanel();
        pnlCusControl.setOpaque(false);
        pnlCusControl.setBackground(Color.WHITE);
        pnlCusList.add(pnlCusControl, BorderLayout.NORTH);
        pnlCusControl.setLayout(null);
        pnlCusControl.setPreferredSize(new Dimension(1100, 100));

        search1Label = new JLabel("Tìm Theo Tên Loại Phòng: ");

        search1Label.setFont(new Font("Arial", Font.PLAIN, 14));
        search1Label.setBounds(30, 25, 200, 30);
        search1Label.setForeground(Color.WHITE);
        pnlCusControl.add(search1Label);

        txtSearch1 = new JTextField();
        txtSearch1.setBounds(215, 25, 280, 30);
        pnlCusControl.add(txtSearch1);

        btnTim = new JButton("Tìm");
        btnTim.setBounds(980, 25, 100, 30);
        Custom.setCustomBtn(btnTim);
        btnTim.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlCusControl.add(btnTim);


        search2Label = new JLabel("Tìm Theo Giá: ");
        search2Label.setFont(new Font("Arial", Font.PLAIN, 14));
        search2Label.setBounds(540, 25, 120, 30);
        search2Label.setForeground(Color.WHITE);
        pnlCusControl.add(search2Label);

        txtSearch2 = new JTextField();
        txtSearch2.setBounds(645, 25, 280, 30);
        pnlCusControl.add(txtSearch2);




        JPanel panelDSKH = new JPanel();
        panelDSKH.setLayout(null);
        panelDSKH.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "DANH SÁCH PHÒNG",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        panelDSKH.setBounds(30, 20, 1100, 470);
        panelDSKH.setOpaque(false);

        String[] colsKH = {"STT", "Mã Phòng", "Loại Phòng", "Tình Trạng", "Vị Trí","Giá Tiền"};
         modelTableP = new DefaultTableModel(colsKH, 0);
        JScrollPane scrollPaneP;

         tableP = new JTable(modelTableP);
        tableP.setFont(new Font("Arial", Font.BOLD, 14));
        tableP.setBackground(new Color(255, 255, 255, 0));
        tableP.setForeground(new Color(255, 255, 255));
        tableP.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableP.getTableHeader().setForeground(Color.BLUE);
        Custom.getInstance().setCustomTable(tableP);

        panelDSKH.add(scrollPaneP = new JScrollPane(tableP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
                BorderLayout.CENTER);
        scrollPaneP.setBounds(10, 20, 1090, 470);
        scrollPaneP.setOpaque(false);
        scrollPaneP.getViewport().setOpaque(false);
        scrollPaneP.getViewport().setBackground(Color.WHITE);
        pnlCusList.add(panelDSKH);
        loadP();


        //
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/background.png"));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date());
        timeLabel.setText(time);
    }
    public void loadP(){
        int i=1;

        for (Room room : RoomDAO.getRoomList()) {


//            if(customer.isGioiTinh()==true){
//
//                gt="Nam" ;
//
//            }
//            else{
//                gt="Nữ";
//            }
            Object[] rowData = { i,room.getMaPhong(),room.getLoaiPhong().getTenLoaiPhong(),room.getViTri(),room.getTinhTrang(),room.getGiaPhong()};
            modelTableP.addRow(rowData);
            i++;

        }
    }

}


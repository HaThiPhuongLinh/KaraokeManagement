package UI.CustomUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.border.EmptyBorder;
/**
 * Custom giao diện
 * Người tham gia thiết kế: Hà Thị Phương Linh
 * Ngày tạo: 10/09/2023
 * Lần cập nhật cuối: 11/11/2023
 * Nội dung cập nhật: Thêm tính năng custom Bill
 */
public class Custom {
    private static Custom instance = new Custom();
    private Font fontBold = new Font("Dialog", Font.BOLD, 14);
    private Font fontNormal = new Font("Dialog", Font.PLAIN, 14);
    public static String pathFont = "/fonts/";
    public static String PATH_EXPORT_BILL = System.getProperty("user.dir") +
            "/../bill/";
    public static Custom getInstance() {
        if (instance == null)
            instance = new Custom();
        return instance;
    }

    /**
     * Custom jbutton
     * @param btn: button
     */
    public static void setCustomBtn(JButton btn) {
        btn.setBackground(Color.decode("#d0e1fd"));
        btn.setForeground(Color.decode("#1a66e3"));
        btn.setBorder(new LineBorder(Color.BLUE, 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Custom combobox
     * @param cmb: combobox
     */
    public static void setCustomComboBox(JComboBox<?> cmb) {
        cmb.setFont(new Font("Arial", Font.PLAIN, 15));
    }

    /**
     * Custom table
     * @param tbl: table
     */
    public void setCustomTableBill(JTable tbl) {
        tbl.setFont(fontNormal);
        tbl.setBackground(Color.decode("#7F75A7"));
        tbl.setForeground(new Color(255, 255, 255));
        tbl.setRowHeight(27);
        tbl.setShowGrid(true);
        tbl.getTableHeader().setFont(fontBold);
        tbl.getTableHeader().setForeground(Color.decode("#9B17EB"));
        tbl.getTableHeader().setBackground(new Color(255, 255, 255));
    }

    /**
     * Custom label
     * @param lbl: table
     */
    public void setCustomLabelBill(JLabel lbl) {
        lbl.setBackground(Color.WHITE);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Dialog", Font.PLAIN, 16));
    }

    /**
     * Custom txt
     * @param txt: txt
     */
    public void setCustomTextFieldBill(JTextField txt) {
        txt.setEditable(false);
        txt.setForeground(Color.WHITE);
        txt.setBorder(new EmptyBorder(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setBackground(Color.decode("#BD6FAD"));
        txt.setFont(fontNormal);
    }

    /**
     * Custom txt
     * @param txt: txt
     */
    public void setCustomTextFieldBill2(JTextField txt) {
        txt.setEditable(false);
        txt.setForeground(Color.WHITE);
        txt.setBorder(new EmptyBorder(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setBackground(Color.decode("#5679A3"));
        txt.setFont(fontNormal);
    }

    /**
     * Custom table
     * @param tbl: table
     */
    public static void setCustomTable(JTable tbl) {
        tbl.setFont(new Font("Arial", Font.PLAIN, 14));
        tbl.setForeground(Color.WHITE);
        tbl.setBackground(Color.decode("#5F009D"));
        tbl.setSelectionBackground(Color.decode("#232D31"));
        tbl.setRowHeight(27);
        tbl.setShowGrid(true);
        tbl.setGridColor(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tbl.getTableHeader().setForeground(Color.BLUE);
    }
}

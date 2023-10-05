package menu;

import menu.mode.LightDarkMode;

import com.formdev.flatlaf.util.UIScale;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Menu extends JPanel {
    private JButton buttonLogin;
    private final String menuItems[][] = {
            {"~Khách Hàng~"},
            {"Khách Hàng", "Tìm kiếm khách hàng", "Cập nhật khách hàng"},
            {"~Nhân viên~"},
            {"Nhân viên", "Tìm kiếm nhân viên", "Cập nhật nhân viên"},
            {"~Phòng~"},
            {"Phòng", "Tìm kiếm phòng", "Cập nhật phòng", "Loại phòng", "Thêm phiếu đặt phòng"},
            {"~Dịch Vụ~"},
            {"Dịch Vụ", "Tìm kiếm dịch vụ", "Cập nhật dịch vụ", "Loại dịch vụ", "Thêm phiếu dịch vụ"},
            {"~Hóa Đơn~"},
            {"Hóa Đơn", "Lập hóa đơn"},
            {"~Thống Kê~"},
            { "Thống Kê ","Thống kê doanh thu", "Thống kê khách hàng", "Thống kê dịch vụ"},
    };

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        if (menuFull) {
            header.setText(headerName);
            header.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JLabel.LEFT : JLabel.RIGHT);
        } else {
            header.setText("");
            header.setHorizontalAlignment(JLabel.CENTER);
        }
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFull(menuFull);
            }
        }
        lightDarkMode.setMenuFull(menuFull);
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;
    private final String headerName = "Staff Name";

    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 280;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    public Menu() {
        init();
    }

    private void init() {
        setLayout(new MenuLayout());
        setBorder(BorderFactory.createEmptyBorder(
                UIScale.scale(20),
                UIScale.scale(2),
                UIScale.scale(2),
                UIScale.scale(2)
        ));
        buttonLogin = new JButton("Log out");
        buttonLogin.setBackground(new Color(255, 255, 255));
        buttonLogin.setForeground(new Color(49, 62, 74));
        buttonLogin.setFocusPainted(false);
        buttonLogin.setBorderPainted(false);
        buttonLogin.setFont(new Font("Arial", Font.PLAIN, 14));


        Color backgroundColor = new Color(51, 51, 51);
        setBackground(backgroundColor);

        header = new JLabel(headerName);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setForeground(new Color(240, 240, 240));

        scroll = new JScrollPane();
        panelMenu = new JPanel(new MenuItemLayout(this));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelMenu.setBackground(new Color(51, 51, 51));
        panelMenu.add(buttonLogin);
        scroll.setViewportView(panelMenu);
        scroll.setBorder(null);
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);

        int menuScrollWidth = 5;
        Color menuScrollBarBackground = new Color(255, 255, 255);
        Color menuScrollBarThumb = new Color(200, 200, 200);

        vscroll.setPreferredSize(new Dimension(menuScrollWidth, vscroll.getPreferredSize().height));
        vscroll.setBackground(menuScrollBarBackground);
        vscroll.setBorder(new EmptyBorder(2, 0, 2, 1));
        vscroll.setBorder(new EmptyBorder(2, 0, 2, 1));
        vscroll.setBackground(menuScrollBarBackground);
        vscroll.setBackground(menuScrollBarThumb);
        createMenu();
        lightDarkMode = new LightDarkMode();

        add(header);
        add(scroll);
        add(lightDarkMode);
    }

    private void createMenu() {
        int index = 0;
        for (int i = 0; i < menuItems.length; i++) {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~")) {
                panelMenu.add(createTitle(menuName));
            } else {
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
    }

    private JLabel createTitle(String title) {
        String menuName = title.substring(1, title.length() - 1);
        JLabel lbTitle = new JLabel(menuName);
        lbTitle.setFont(new Font("sansserif", Font.PLAIN, 14));
        lbTitle.setForeground(new Color(240, 240, 240));

        return lbTitle;
    }

    public void setSelectedMenu(int index, int subIndex) {
        runEvent(index, subIndex);
    }

    protected void setSelected(int index, int subIndex) {
        int size = panelMenu.getComponentCount();
        for (int i = 0; i < size; i++) {
            Component com = panelMenu.getComponent(i);
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == index) {
                    item.setSelectedIndex(subIndex);
                } else {
                    item.setSelectedIndex(-1);
                }
            }
        }
    }

    protected void runEvent(int index, int subIndex) {
        MenuAction menuAction = new MenuAction();
        for (MenuEvent event : events) {
            event.menuSelected(index, subIndex, menuAction);
        }
        if (!menuAction.isCancel()) {
            setSelected(index, subIndex);
        }
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public void hideMenuItem() {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).hideMenuItem();
            }
        }
        revalidate();
    }

    public boolean isHideMenuTitleOnMinimum() {
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
        return menuMinWidth;
    }

    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;

    private class MenuLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int gap = UIScale.scale(5);
                int sheaderFullHgap = UIScale.scale(headerFullHgap);
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int iconWidth = width;
                int iconHeight = header.getPreferredSize().height;
                int hgap = menuFull ? sheaderFullHgap : 0;
                int accentColorHeight = 0;


                header.setBounds(x + hgap, y, iconWidth - (hgap * 2), iconHeight);
                int ldgap = UIScale.scale(10);
                int ldWidth = width - ldgap * 2;
                int ldHeight = lightDarkMode.getPreferredSize().height;
                int ldx = x + ldgap;
                int ldy = y + height - ldHeight - ldgap - accentColorHeight;

                int menux = x;
                int menuy = y + iconHeight + gap;
                int menuWidth = width;
                int menuHeight = height - (iconHeight + gap) - (ldHeight + ldgap * 2) - (accentColorHeight);
                scroll.setBounds(menux, menuy, menuWidth, menuHeight);

                lightDarkMode.setBounds(ldx, ldy, ldWidth, ldHeight);

                int loginButtonWidth = 100;
                int loginButtonHeight = 50;
                int loginButtonX = ldx + ldWidth + ldgap;
                int loginButtonY = ldy - loginButtonHeight - gap;

                buttonLogin.setBounds(loginButtonX, loginButtonY, loginButtonWidth, loginButtonHeight);

            }
        }
    }


}

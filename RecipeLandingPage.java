
package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.sql.SQLException;

public class RecipeLandingPage extends JFrame {
    private static final long serialVersionUID = 1L;

    private String currentUserName = null;
    private JPanel contentPane;
    private JPanel cardsPanel;
    private JTextField searchField;
    private JButton loginButton, signUpButton;
    private static final int CARD_WIDTH = 270;
    private static final int CARD_GAP = 15;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecipeLandingPage frame = new RecipeLandingPage();
            frame.setVisible(true);
            frame.adjustCardLayout();
            frame.loadAndDisplayRecipes();
        });
    }

    public RecipeLandingPage() {
        setTitle("Recipe Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((int)(screen.width*0.9), (int)(screen.height*0.85)));
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);
        setResizable(true);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.add(buildNavBar(), BorderLayout.NORTH);
        contentPane.add(buildMainScroll(), BorderLayout.CENTER);
        contentPane.add(buildFooter(), BorderLayout.SOUTH);

        pack();
    }

    private JPanel buildNavBar() {
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        navBar.setBackground(Color.WHITE);
        navBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel logo = new JLabel("RecipeHub");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(new Color(234, 88, 12));
        navBar.add(logo);

        JLabel searchIcon = new JLabel("\uD83D\uDD0D");
        searchIcon.setFont(new Font("Arial", Font.PLAIN, 18));
        navBar.add(searchIcon);

        searchField = new JTextField("Search for recipes...", 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for recipes...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for recipes...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        searchField.addActionListener(e -> performSearch());
        navBar.add(searchField);

        JButton searchBtn = new JButton("Search");
        styleButton(searchBtn, new Color(234, 88, 12), Color.WHITE);
        searchBtn.addActionListener(e -> performSearch());
        navBar.add(searchBtn);

        loginButton = new JButton("Login");
        styleButton(loginButton, Color.WHITE, new Color(234, 88, 12));
        loginButton.addActionListener(e -> openLogin());

        signUpButton = new JButton("Sign Up");
        styleButton(signUpButton, new Color(234, 88, 12), Color.WHITE);
        signUpButton.addActionListener(e -> openLogin());

        if (currentUserName == null) {
            navBar.add(loginButton);
            navBar.add(signUpButton);
        } else {
            JButton userBtn = new JButton(currentUserName + " \u25BE");
            styleButton(userBtn, Color.WHITE, new Color(234, 88, 12));
            JPopupMenu menu = new JPopupMenu();
            JMenuItem logout = new JMenuItem("Log Out");
            logout.addActionListener(ev -> {
                currentUserName = null;
                rebuildNavBar();
            });
            menu.add(logout);
            userBtn.addActionListener(ev -> menu.show(userBtn, 0, userBtn.getHeight()));
            navBar.add(userBtn);
        }

        return navBar;
    }

    private void rebuildNavBar() {
        contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.NORTH));
        contentPane.add(buildNavBar(), BorderLayout.NORTH);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void openLogin() {
        LoginPage lp = new LoginPage(name -> {
            currentUserName = name;
            rebuildNavBar();
        });
        lp.setVisible(true);
    }

    private void performSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty() || keyword.equals("Search for recipes...")) {
            JOptionPane.showMessageDialog(this, "Please enter a keyword to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new SearchResultsPage(keyword, currentUserName).setVisible(true);
    }

    private JScrollPane buildMainScroll() {
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(248, 250, 252));
        scroll.setViewportView(main);

        JPanel hero = new JPanel(new BorderLayout(0, 15));
        hero.setBackground(new Color(255, 237, 213));
        hero.setBorder(new EmptyBorder(30, 20, 30, 20));
        main.add(hero);
        main.add(Box.createVerticalStrut(20));

        JLabel heroTitle = new JLabel("Discover Delicious Recipes", SwingConstants.CENTER);
        heroTitle.setFont(new Font("Arial", Font.BOLD, 36));
        heroTitle.setForeground(new Color(153, 27, 27));
        hero.add(heroTitle, BorderLayout.NORTH);

        JTextArea heroDesc = new JTextArea("Discover, manage, and preserve authentic Nepali recipes!");
        heroDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        heroDesc.setWrapStyleWord(true);
        heroDesc.setLineWrap(true);
        heroDesc.setOpaque(false);
        heroDesc.setEditable(false);
        hero.add(heroDesc, BorderLayout.CENTER);
//
//        JButton browse = new JButton("Browse Recipes");
//        styleButton(browse, new Color(234, 88, 12), Color.WHITE);
//        browse.setFont(new Font("Arial", Font.BOLD, 18));
//        hero.add(browse, BorderLayout.SOUTH);

        JLabel title = new JLabel("Traditional Nepalese Recipes", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(51, 51, 51));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(title);
        main.add(Box.createVerticalStrut(20));

        JPanel cardsWrapper = new JPanel();
        cardsWrapper.setLayout(new BoxLayout(cardsWrapper, BoxLayout.X_AXIS));
        cardsWrapper.setBackground(new Color(248, 250, 252));
        main.add(cardsWrapper);
        cardsWrapper.add(Box.createHorizontalGlue());

        cardsPanel = new JPanel(new GridLayout(0, 3, CARD_GAP, CARD_GAP));
        cardsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardsPanel.setBackground(new Color(248, 250, 252));
        cardsPanel.setMaximumSize(new Dimension(1000, Integer.MAX_VALUE));
        cardsWrapper.add(cardsPanel);
        cardsWrapper.add(Box.createHorizontalGlue());
        main.add(Box.createVerticalStrut(30));

        main.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                adjustCardLayout();
            }
        });

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setBackground(new Color(255, 247, 237));
        addPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        main.add(addPanel);
        main.add(Box.createVerticalStrut(20));

        JLabel addTitle = new JLabel("Didn't find what you were looking for?", SwingConstants.CENTER);
        addTitle.setFont(new Font("Arial", Font.BOLD, 28));
        addTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPanel.add(addTitle);
        addPanel.add(Box.createVerticalStrut(10));

        JTextArea addDesc = new JTextArea("Help us grow our collection by adding your own unique recipes!");
        addDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        addDesc.setWrapStyleWord(true);
        addDesc.setLineWrap(true);
        addDesc.setOpaque(false);
        addDesc.setEditable(false);
        addDesc.setBorder(null);
        addDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        addDesc.setMaximumSize(new Dimension(800, 40));
        addPanel.add(addDesc);
        addPanel.add(Box.createVerticalStrut(20));

        JButton addBtn = new JButton("Add New Recipe");
        styleButton(addBtn, new Color(234, 88, 12), Color.BLACK);
        addBtn.setFont(new Font("Arial", Font.BOLD, 20));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(e -> {
            if (currentUserName != null) {
                new AddRecipePage().setVisible(true);
            } else {
                LoginPage lp = new LoginPage(fullName -> {
                    setCurrentUserName(fullName);
                    SwingUtilities.invokeLater(() -> this.setVisible(true));
                });
                lp.setVisible(true);
                this.setVisible(false);
            }
        });
        addPanel.add(addBtn);

        return scroll;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footer.setBackground(new Color(51, 51, 51));

        JLabel copy = new JLabel("© 2024 RecipeHub. All rights reserved.");
        copy.setForeground(Color.WHITE);
        copy.setFont(new Font("Arial", Font.PLAIN, 12));
        footer.add(copy);

        for (String text : Arrays.asList("Privacy Policy", "Terms of Service", "Contact Us")) {
            JButton link = new JButton(text);
            styleLinkButton(link);
            footer.add(link);
        }
        return footer;
    }

    private void adjustCardLayout() {
        int w = cardsPanel.getWidth();
        if (w <= 0) return;
        int cols = Math.max(1, w / CARD_WIDTH);
        GridLayout g = (GridLayout) cardsPanel.getLayout();
        if (g.getColumns() != cols) {
            g.setColumns(cols);
            cardsPanel.revalidate();
        }
    }

    private void loadAndDisplayRecipes() {
        cardsPanel.removeAll();
        JLabel loading = new JLabel("Loading recipes...", SwingConstants.CENTER);
        loading.setFont(new Font("Arial", Font.ITALIC, 16));
        loading.setForeground(Color.GRAY);
        cardsPanel.add(loading);

        new SwingWorker<List<RecipeData>, Void>() {
            protected List<RecipeData> doInBackground() throws Exception {
                Thread.sleep(500);
                return fetchRecipes();
            }

            protected void done() {
                try {
                    cardsPanel.removeAll();
                    for (RecipeData r : get()) addRecipeCard(r);
                    adjustCardLayout();
                } catch (Exception e) {
                    cardsPanel.removeAll();
                    cardsPanel.add(new JLabel("Failed to load recipes.", SwingConstants.CENTER));
                }
            }
        }.execute();
    }

    private List<RecipeData> fetchRecipes() {
        try {
            return RecipeDAO.getTopRecipes(6);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
//

    private void addRecipeCard(RecipeData r) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(260, 340));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 🖼️ Image Section
        try {
            ImageIcon raw = new ImageIcon(new URL(r.imageUrl));
            Image scaled = raw.getImage().getScaledInstance(240, 180, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(imgLabel, BorderLayout.NORTH);
        } catch (Exception ex) {
            JLabel img = new JLabel("Image Not Available", SwingConstants.CENTER);
            img.setPreferredSize(new Dimension(240, 180));
            img.setForeground(Color.GRAY);
            card.add(img, BorderLayout.NORTH);
        }

        // 📃 Text Info
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);
        info.setBorder(new EmptyBorder(10, 0, 0, 0));

        JLabel title = new JLabel(r.title);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        info.add(title);

        info.add(Box.createVerticalStrut(8));

        // 📄 Short Description (Max 150 chars + ellipsis)
        String desc = r.description != null ? r.description.trim() : "";
        if (desc.length() > 50) desc = desc.substring(0, 50) + "...";

        JTextArea shortDesc = new JTextArea(desc);
        shortDesc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        shortDesc.setWrapStyleWord(true);
        shortDesc.setLineWrap(true);
        shortDesc.setEditable(false);
        shortDesc.setOpaque(false);
        shortDesc.setFocusable(false);
        shortDesc.setMaximumSize(new Dimension(240, 60));
        shortDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        info.add(shortDesc);

        info.add(Box.createVerticalStrut(10));

        // 🔘 Read More Button
        JButton more = new JButton("Read More →");
        more.setFont(new Font("SansSerif", Font.BOLD, 13));
        more.setForeground(new Color(234, 88, 12));
        more.setContentAreaFilled(false);
        more.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        more.setAlignmentX(Component.LEFT_ALIGNMENT);

        more.addActionListener(e -> new RecipeDescriptionPage(
            r.title,
            r.description,
            r.imageUrl,
            r.ingredients != null ? r.ingredients : List.of("N/A"),
            r.cookTime != null ? r.cookTime : "Unknown"
        ).setVisible(true));

        info.add(more);

        card.add(info, BorderLayout.CENTER);
        cardsPanel.add(card);
    }

    private void styleButton(JButton b, Color bg, Color fg) {
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void styleLinkButton(JButton b) {
        b.setBackground(null);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFont(new Font("Arial", Font.PLAIN, 12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setForeground(new Color(251, 191, 36)); }
            public void mouseExited(MouseEvent e) { b.setForeground(Color.WHITE); }
        });
    }

    private void setCurrentUserName(String name) {
        this.currentUserName = name;
        rebuildNavBar();
    }
}

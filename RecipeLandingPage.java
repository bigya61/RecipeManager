//package app.ui;
//import app.ui.RecipeDAO;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.*;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.ArrayList;
//import app.ui.RecipeData;
//import java.util.List;
//import java.util.function.Consumer;
//import java.sql.SQLException;
//
//public class RecipeLandingPage extends JFrame {
//    private static final long serialVersionUID = 1L;
//
//    private String currentUserName = null;
//    private JPanel contentPane;
//    private JPanel cardsPanel;
//    private JTextField searchField;
//    private JButton loginButton, signUpButton;
//
//    private static final int CARD_WIDTH = 270;
//    private static final int CARD_GAP = 15;
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            RecipeLandingPage frame = new RecipeLandingPage();
//            frame.setVisible(true);
//            frame.adjustCardLayout();
//            frame.loadAndDisplayRecipes();
//        });
//    }
//    private void performSearch() {
//        String keyword = searchField.getText().trim();
//        if (keyword.isEmpty() || keyword.equals("Search for recipes...")) {
//            JOptionPane.showMessageDialog(this, "Please enter a keyword to search.", "Warning", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        // Open the search results page and pass the keyword and currentUserName
//        SearchResultsPage resultsPage = new SearchResultsPage(keyword, currentUserName);
//        resultsPage.setVisible(true);
//    }
//
//    public RecipeLandingPage() {
//        setTitle("Recipe Management System");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Responsive sizing
//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        setPreferredSize(new Dimension((int)(screen.width*0.9), (int)(screen.height*0.85)));
//        setMinimumSize(new Dimension(1000, 650));
//        setLocationRelativeTo(null);
//        setResizable(true);
//
//        contentPane = new JPanel(new BorderLayout());
//        contentPane.setBorder(new EmptyBorder(5,5,5,5));
//        setContentPane(contentPane);
//
//        // Build UI sections
//        contentPane.add(buildNavBar(), BorderLayout.NORTH);
//        contentPane.add(buildMainScroll(), BorderLayout.CENTER);
//        contentPane.add(buildFooter(), BorderLayout.SOUTH);
//
//        pack();
//    }
//    private void performSearch(String query) {
//        new SwingWorker<List<RecipeData>, Void>() {
//            protected List<RecipeData> doInBackground() throws Exception {
//                return RecipeDAO.searchRecipes(query);
//            }
//
//            protected void done() {
//                try {
//                    List<RecipeData> results = get();
////                    new SearchResultsPage(results, query ,currentUserName);  // ⬅️ launch new page
//                    SearchResultsPage page = new SearchResultsPage(query, currentUserName);
//                    page.setVisible(true);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    JOptionPane.showMessageDialog(RecipeLandingPage.this,
//                        "An error occurred during search.",
//                        "Search Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        }.execute();
//    }
//    
//
////    private JPanel buildNavBar() {
////        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
////        navBar.setBackground(Color.WHITE);
////        navBar.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.LIGHT_GRAY));
////
////        JLabel logo = new JLabel("RecipeHub");
////        logo.setFont(new Font("Arial",Font.BOLD,24));
////        logo.setForeground(new Color(234,88,12));
////        navBar.add(logo);
////
////        searchField = new JTextField("Search for recipes...",30);
////        searchField.setFont(new Font("Arial",Font.PLAIN,14));
////        searchField.setForeground(Color.GRAY);
////        searchField.addFocusListener(new FocusAdapter() {
////            public void focusGained(FocusEvent e) {
////                if (searchField.getText().equals("Search for recipes...")) {
////                    searchField.setText("");
////                    searchField.setForeground(Color.BLACK);
////                }
////            }
////            public void focusLost(FocusEvent e) {
////                if (searchField.getText().isEmpty()) {
////                    searchField.setText("Search for recipes...");
////                    searchField.setForeground(Color.GRAY);
////                }
////            }
////        });
////        navBar.add(searchField);
//    
////    private JPanel buildNavBar() {
////        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
////        navBar.setBackground(Color.WHITE);
////        navBar.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.LIGHT_GRAY));
////
////        JLabel logo = new JLabel("RecipeHub");
////        logo.setFont(new Font("Arial",Font.BOLD,24));
////        logo.setForeground(new Color(234,88,12));
////        navBar.add(logo);
////
////        searchField = new JTextField("Search for recipes...",30);
////        searchField.setFont(new Font("Arial",Font.PLAIN,14));
////        searchField.setForeground(Color.GRAY);
////        searchField.addFocusListener(new FocusAdapter() {
////            public void focusGained(FocusEvent e) {
////                if (searchField.getText().equals("Search for recipes...")) {
////                    searchField.setText("");
////                    searchField.setForeground(Color.BLACK);
////                }
////            }
////            public void focusLost(FocusEvent e) {
////                if (searchField.getText().isEmpty()) {
////                    searchField.setText("Search for recipes...");
////                    searchField.setForeground(Color.GRAY);
////                }
////            }
////        });
////
////        // Add action listener for pressing Enter key in search field
////        searchField.addActionListener(e -> performSearch());
////
////        navBar.add(searchField);
////
////        JButton searchBtn = new JButton("Search");
////        styleButton(searchBtn, new Color(234,88,12), Color.WHITE);
////        searchBtn.addActionListener(e -> performSearch());
////        navBar.add(searchBtn);
////
////        // ... existing loginButton, signUpButton logic here
////        // (no changes to user login buttons)
////
////        
////
////
////        // Login / User button
////        loginButton = new JButton("Login");
////        styleButton(loginButton, Color.WHITE, new Color(234,88,12));
////        loginButton.addActionListener(e -> openLogin());
////        signUpButton = new JButton("Sign Up");
////        styleButton(signUpButton, new Color(234,88,12), Color.WHITE);
////        signUpButton.addActionListener(e -> openLogin());
////
////        if (currentUserName == null) {
////            navBar.add(loginButton);
////            navBar.add(signUpButton);
////        } else {
////            JButton userBtn = new JButton(currentUserName + " \u25BE");
////            styleButton(userBtn, Color.WHITE, new Color(234,88,12));
////            JPopupMenu menu = new JPopupMenu();
////            JMenuItem logout = new JMenuItem("Log Out");
////            logout.addActionListener(ev -> {
////                currentUserName = null;
////                rebuildNavBar();
////            });
////            menu.add(logout);
////            userBtn.addActionListener(ev -> menu.show(userBtn,0,userBtn.getHeight()));
////            navBar.add(userBtn);
////        }
////
////        return navBar;
////    }
//    private JPanel buildNavBar() {
//        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
//        navBar.setBackground(Color.WHITE);
//        navBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
//
//        // Logo
//        JLabel logo = new JLabel("RecipeHub");
//        logo.setFont(new Font("Arial", Font.BOLD, 24));
//        logo.setForeground(new Color(234, 88, 12));
//        navBar.add(logo);
//
//        // Search icon
//        JLabel searchIcon = new JLabel("\uD83D\uDD0D"); // Unicode for magnifying glass
//        searchIcon.setFont(new Font("Arial", Font.PLAIN, 18));
//        navBar.add(searchIcon);
//
//        // Search text field
//        searchField = new JTextField("Search for recipes...", 30);
//        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
//        searchField.setForeground(Color.GRAY);
//        searchField.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent e) {
//                if (searchField.getText().equals("Search for recipes...")) {
//                    searchField.setText("");
//                    searchField.setForeground(Color.BLACK);
//                }
//            }
//
//            public void focusLost(FocusEvent e) {
//                if (searchField.getText().isEmpty()) {
//                    searchField.setText("Search for recipes...");
//                    searchField.setForeground(Color.GRAY);
//                }
//            }
//        });
//
//        // Handle Enter key
//        searchField.addActionListener(e -> performSearch());
//
//        navBar.add(searchField);
//
//        // Search button
//        JButton searchBtn = new JButton("Search");
//        styleButton(searchBtn, new Color(234, 88, 12), Color.WHITE);
//        searchBtn.addActionListener(e -> performSearch());
//        navBar.add(searchBtn);
//
//        // Login and SignUp buttons
//        loginButton = new JButton("Login");
//        styleButton(loginButton, Color.WHITE, new Color(234, 88, 12));
//        loginButton.addActionListener(e -> openLogin());
//
//        signUpButton = new JButton("Sign Up");
//        styleButton(signUpButton, new Color(234, 88, 12), Color.WHITE);
//        signUpButton.addActionListener(e -> openLogin());
//
//        if (currentUserName == null) {
//            navBar.add(loginButton);
//            navBar.add(signUpButton);
//        } else {
//            JButton userBtn = new JButton(currentUserName + " \u25BE"); // ▼ symbol
//            styleButton(userBtn, Color.WHITE, new Color(234, 88, 12));
//
//            JPopupMenu menu = new JPopupMenu();
//            JMenuItem logout = new JMenuItem("Log Out");
//            logout.addActionListener(ev -> {
//                currentUserName = null;
//                rebuildNavBar(); // Rebuild with login/signup again
//            });
//            menu.add(logout);
//
//            userBtn.addActionListener(ev -> menu.show(userBtn, 0, userBtn.getHeight()));
//            navBar.add(userBtn);
//        }
//
//        return navBar;
//    }
//
//    private void rebuildNavBar() {
//        contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.NORTH));
//        contentPane.add(buildNavBar(), BorderLayout.NORTH);
//        contentPane.revalidate();
//        contentPane.repaint();
//    }
//
//    private void openLogin() {
//        LoginPage lp = new LoginPage(name -> {
//            currentUserName = name;
//            rebuildNavBar();
//        });
//        lp.setVisible(true);
//    }
//    private void setCurrentUserName(String name) {
//        this.currentUserName = name;
//        rebuildNavBar();  // Optional: Update the top bar after login
//    }
//
//    private JScrollPane buildMainScroll() {
//        JScrollPane scroll = new JScrollPane();
//        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scroll.getVerticalScrollBar().setUnitIncrement(16);
//
//        JPanel main = new JPanel();
//        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
//        main.setBackground(new Color(248,250,252));
//        scroll.setViewportView(main);
//
//        // Hero Section
//        JPanel hero = new JPanel(new BorderLayout(0,15));
//        hero.setBackground(new Color(255,237,213));
//        hero.setBorder(new EmptyBorder(30,20,30,20));
//        main.add(hero);
//        main.add(Box.createVerticalStrut(20));
//
//        JLabel heroTitle = new JLabel("Discover Delicious Recipes",SwingConstants.CENTER);
//        heroTitle.setFont(new Font("Arial",Font.BOLD,36));
//        heroTitle.setForeground(new Color(153,27,27));
//        hero.add(heroTitle,BorderLayout.NORTH);
//
//        JTextArea heroDesc = new JTextArea("Discover, manage, and preserve authentic Nepali recipes!");
//        heroDesc.setFont(new Font("Arial",Font.PLAIN,16));
//        heroDesc.setWrapStyleWord(true);
//        heroDesc.setLineWrap(true);
//        heroDesc.setOpaque(false);
//        heroDesc.setEditable(false);
//        hero.add(heroDesc,BorderLayout.CENTER);
//
//        JButton browse = new JButton("Browse Recipes");
//        styleButton(browse,new Color(234,88,12),Color.WHITE);
//        browse.setFont(new Font("Arial",Font.BOLD,18));
//        hero.add(browse,BorderLayout.SOUTH);
//
//        // Section Title
//        JLabel title = new JLabel("Traditional Nepalese Recipes",SwingConstants.CENTER);
//        title.setFont(new Font("Arial",Font.BOLD,28));
//        title.setForeground(new Color(51,51,51));
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        main.add(title);
//        main.add(Box.createVerticalStrut(20));
//
//        // Cards Container
//        JPanel cardsWrapper = new JPanel();
//        cardsWrapper.setLayout(new BoxLayout(cardsWrapper,BoxLayout.X_AXIS));
//        cardsWrapper.setBackground(new Color(248,250,252));
//        main.add(cardsWrapper);
//        cardsWrapper.add(Box.createHorizontalGlue());
//
//        cardsPanel = new JPanel(new GridLayout(0,1,CARD_GAP,CARD_GAP)); 
//        //TAGGED FOR HORIZONTAL LAYOUT 
//       
////        cardsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, CARD_GAP, CARD_GAP));// maintains the webpage size
//        cardsPanel.setBorder(new EmptyBorder(10,10,10,10));
//        cardsPanel.setBackground(new Color(248,250,252));
//        cardsPanel.setMaximumSize(new Dimension(1000,Integer.MAX_VALUE));
//        cardsWrapper.add(cardsPanel);
//
//        cardsWrapper.add(Box.createHorizontalGlue());
//        main.add(Box.createVerticalStrut(30));
//
//        main.addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent e) {
//                adjustCardLayout();
//            }
//        });
//
//        // Add Recipe Section
//        JPanel addPanel = new JPanel();
//        addPanel.setLayout(new BoxLayout(addPanel,BoxLayout.Y_AXIS));
//        addPanel.setBackground(new Color(255,247,237));
//        addPanel.setBorder(new EmptyBorder(30,20,30,20));
//        main.add(addPanel);
//        main.add(Box.createVerticalStrut(20));
//
//        JLabel addTitle = new JLabel("Didn't find what you were looking for?",SwingConstants.CENTER);
//        addTitle.setFont(new Font("Arial",Font.BOLD,28));
//        addTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addPanel.add(addTitle);
//        addPanel.add(Box.createVerticalStrut(10));
//
//        JTextArea addDesc = new JTextArea("Help us grow our collection by adding your own unique recipes!");
//        addDesc.setFont(new Font("Arial",Font.PLAIN,16));
//        addDesc.setWrapStyleWord(true);
//        addDesc.setLineWrap(true);
//        addDesc.setOpaque(false);
//        addDesc.setEditable(false);
//        addDesc.setBorder(null);
//        addDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addDesc.setMaximumSize(new Dimension(800,40));
//        addPanel.add(addDesc);
//        addPanel.add(Box.createVerticalStrut(20));
//
//
//        JButton addBtn = new JButton("Add New Recipe");
//        styleButton(addBtn, new Color(234, 88, 12), Color.BLACK);
//        addBtn.setFont(new Font("Arial", Font.BOLD, 20));
//        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // 👉 Connect to AddRecipePage
//        addBtn.addActionListener(e -> {
//            if (currentUserName != null) {
//                // User is logged in
//                new AddRecipePage().setVisible(true);
//            } else {
//                // User not logged in → redirect to login
//                LoginPage lp = new LoginPage(fullName -> {
//                    setCurrentUserName(fullName);
//                    SwingUtilities.invokeLater(() -> this.setVisible(true));
//                });
//                lp.setVisible(true);
//                this.setVisible(false);
//            }
//        });
//
//        addPanel.add(addBtn);
//
//        return scroll;
//    }
//
//    private JPanel buildFooter() {
//        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
//        footer.setBackground(new Color(51,51,51));
//
//        JLabel copy = new JLabel("© 2024 RecipeHub. All rights reserved.");
//        copy.setForeground(Color.WHITE);
//        copy.setFont(new Font("Arial",Font.PLAIN,12));
//        footer.add(copy);
//
//        for (String text : Arrays.asList("Privacy Policy","Terms of Service","Contact Us")) {
//            JButton link = new JButton(text);
//            styleLinkButton(link);
//            footer.add(link);
//        }
//        return footer;
//    }
//
//    private void adjustCardLayout() {
//        int w = cardsPanel.getWidth(); if (w<=0) return;
//        int cols = Math.max(1, w/CARD_WIDTH);
//        GridLayout g = (GridLayout)cardsPanel.getLayout();
//        if (g.getColumns()!=cols) {
//            g.setColumns(cols);
//            cardsPanel.revalidate();
//        }
//    }
//
////    private List<RecipeData> fetchRecipes() {
////        return Arrays.asList(
//////            new RecipeData("Dal, Bhat, Tarkari","A soul-warming staple of lentils, rice, and veggies.","https://placehold.co/400x400/FDBA74/FFFFFF?text=Dal+Bhat+Tarkari"),
//////            new RecipeData("Sel Roti","Ring-shaped sweet rice bread, deep-fried to perfection.","https://placehold.co/400x400/6EE7B7/FFFFFF?text=Sel+Roti"),
//////            new RecipeData("Momo","Steamed dumplings filled with meat or veggies.","https://placehold.co/400x400/F0ABFC/FFFFFF?text=Momo"),
//////            new RecipeData("Yomari","Rice-flour dumplings with sweet molasses filling.","https://placehold.co/400x400/A5B4FC/FFFFFF?text=Yomari")
////        );
////    }
//    private List<RecipeData> fetchRecipes() {
//        try {
//            return RecipeDAO.getTopRecipes(6);  // fetch max 6 recipes
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<>(); // return empty if error
//        }
//    }
//
//    private void loadAndDisplayRecipes() {
//        cardsPanel.removeAll();
//        JLabel loading = new JLabel("Loading recipes...",SwingConstants.CENTER);
//        loading.setFont(new Font("Arial",Font.ITALIC,16));
//        loading.setForeground(Color.GRAY);
//        cardsPanel.add(loading);
//
//        new SwingWorker<List<RecipeData>,Void>() {
//            protected List<RecipeData> doInBackground() throws Exception {
//                Thread.sleep(500);
//                return fetchRecipes();
//            }
//            protected void done() {
//                try {
//                    cardsPanel.removeAll();
//                    for (RecipeData r : get()) addRecipeCard(r);
//                    adjustCardLayout();
//                } catch (Exception e) {
//                    cardsPanel.removeAll();
//                    cardsPanel.add(new JLabel("Failed to load recipes.",SwingConstants.CENTER));
//                }
//            }
//        }.execute();
//    }
//
//    private void addRecipeCard(RecipeData r) {
//        JPanel card = new JPanel(new BorderLayout());
//        card.setBackground(Color.WHITE);
//        card.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
//            new EmptyBorder(5,5,5,5)
//        ));
//        card.setPreferredSize(new Dimension(250,320));
//
//        try {
//            ImageIcon raw = new ImageIcon(new URL(r.imageUrl));
//            Image scaled = raw.getImage().getScaledInstance(240,240,Image.SCALE_SMOOTH);
//            card.add(new JLabel(new ImageIcon(scaled)),BorderLayout.NORTH);
//        } catch (Exception ex) {
//            JLabel img = new JLabel("Image",SwingConstants.CENTER);
//            img.setPreferredSize(new Dimension(240,240));
//            card.add(img,BorderLayout.NORTH);
//        }
//
//        JPanel info = new JPanel();
//        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
//        info.setBackground(Color.WHITE);
//        info.setBorder(new EmptyBorder(5,5,5,5));
//
//        JLabel t = new JLabel(r.title);
//        t.setFont(new Font("Arial",Font.BOLD,14));
//        info.add(t);
//
//        JTextArea d = new JTextArea(r.description);
//        d.setFont(new Font("Arial",Font.PLAIN,12));
//        d.setWrapStyleWord(true); d.setLineWrap(true);
//        d.setEditable(false); d.setOpaque(false);
//        info.add(d);
//
//        JButton more = new JButton("Read More →");
//        more.setFont(new Font("Arial",Font.PLAIN,12));
//        more.setForeground(new Color(234,88,12));
//        more.setContentAreaFilled(false); more.setBorderPainted(false);
//        more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        more.setAlignmentX(Component.LEFT_ALIGNMENT);
//        more.addActionListener(e -> new RecipeDescriptionPage(
//        	    r.title,
//        	    r.description,
//        	    r.imageUrl,
//        	    r.ingredients,
//        	    r.cookTime
//        	).setVisible(true));
//
//        more.addActionListener(e -> new RecipeDescriptionPage(
//        	    r.title,
//        	    r.description,
//        	    r.imageUrl,
//        	    r.ingredients != null ? r.ingredients : List.of("N/A"),
//        	    r.cookTime != null ? r.cookTime : "Unknown"
//        	).setVisible(true));
//        
//        info.add(Box.createVerticalStrut(5));
//        info.add(more);
//
//        card.add(info,BorderLayout.CENTER);
//        cardsPanel.add(card);
//    }
//
//    private void styleButton(JButton b, Color bg, Color fg) {
//        b.setBackground(bg); b.setForeground(fg);
//        b.setFocusPainted(false);
//        b.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
//        b.setFont(new Font("Arial",Font.BOLD,14));
//        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//    }
//
//    private void styleLinkButton(JButton b) {
//        b.setBackground(null); b.setForeground(Color.WHITE);
//        b.setFocusPainted(false); b.setBorderPainted(false);
//        b.setContentAreaFilled(false); b.setFont(new Font("Arial",Font.PLAIN,12));
//        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        b.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) { b.setForeground(new Color(251,191,36)); }
//            public void mouseExited(MouseEvent e) { b.setForeground(Color.WHITE); }
//        });
//    }
//
//
////    public static class RecipeData {
////        public String title;
////        public String description;
////        public String imageUrl;
////        public List<String> ingredients;
////        public String cookTime;
////    }
//
//    // WrapLayout for responsive wrapping
//    static class WrapLayout extends FlowLayout {
//        public WrapLayout(int align,int hgap,int vgap){super(align,hgap,vgap);}        
//        public Dimension preferredLayoutSize(Container target){return layoutSize(target,true);}        
//        public Dimension minimumLayoutSize(Container target){Dimension m=layoutSize(target,false);m.width-=(getHgap()+1);return m;}        
//        private Dimension layoutSize(Container target,boolean pref){synchronized(target.getTreeLock()){int width=target.getWidth();if(width==0)width=Integer.MAX_VALUE;Insets ins=target.getInsets();int maxW=width-(ins.left+ins.right+getHgap()*2);int x=0,y=ins.top+getVgap(),rowH=0;for(Component m:target.getComponents()){if(!m.isVisible())continue;Dimension d=pref?m.getPreferredSize():m.getMinimumSize();if(x>0&&x+d.width>maxW){x=0;y+=getVgap()+rowH;rowH=0;}x+=d.width+getHgap();rowH=Math.max(rowH,d.height);}y+=rowH+ins.bottom;return new Dimension(width,y);}}    }
//}
//
//
//
//
//
//
//
//
//
//
//
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

        JButton browse = new JButton("Browse Recipes");
        styleButton(browse, new Color(234, 88, 12), Color.WHITE);
        browse.setFont(new Font("Arial", Font.BOLD, 18));
        hero.add(browse, BorderLayout.SOUTH);

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

        cardsPanel = new JPanel(new GridLayout(0, 1, CARD_GAP, CARD_GAP));
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

    private void addRecipeCard(RecipeData r) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(5, 5, 5, 5)
        ));
        card.setPreferredSize(new Dimension(250, 320));

        try {
            ImageIcon raw = new ImageIcon(new URL(r.imageUrl));
            Image scaled = raw.getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
            card.add(new JLabel(new ImageIcon(scaled)), BorderLayout.NORTH);
        } catch (Exception ex) {
            JLabel img = new JLabel("Image", SwingConstants.CENTER);
            img.setPreferredSize(new Dimension(240, 240));
            card.add(img, BorderLayout.NORTH);
        }

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);
        info.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel t = new JLabel(r.title);
        t.setFont(new Font("Arial", Font.BOLD, 14));
        info.add(t);

        JTextArea d = new JTextArea(r.description);
        d.setFont(new Font("Arial", Font.PLAIN, 12));
        d.setWrapStyleWord(true);
        d.setLineWrap(true);
        d.setEditable(false);
        d.setOpaque(false);
        info.add(d);

        JButton more = new JButton("Read More →");
        more.setFont(new Font("Arial", Font.PLAIN, 12));
        more.setForeground(new Color(234, 88, 12));
        more.setContentAreaFilled(false);
        more.setBorderPainted(false);
        more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        more.setAlignmentX(Component.LEFT_ALIGNMENT);
        more.addActionListener(e -> new RecipeDescriptionPage(
            r.title,
            r.description,
            r.imageUrl,
            r.ingredients != null ? r.ingredients : List.of("N/A"),
            r.cookTime != null ? r.cookTime : "Unknown"
        ).setVisible(true));

        info.add(Box.createVerticalStrut(5));
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

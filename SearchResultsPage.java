//package app.ui;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.net.URL;
//import java.util.List;
//
//public class SearchResultsPage extends JFrame {
//    private JPanel contentPane;
//    private JPanel cardsPanel;
//
//    public SearchResultsPage(List<RecipeData> results, String searchQuery, String currentUserName) {
//        setTitle("Search Results for: " + searchQuery);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(900, 600);
//        setLocationRelativeTo(null);
//
//        contentPane = new JPanel(new BorderLayout(10, 10));
//        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//        setContentPane(contentPane);
//
//        JLabel title = new JLabel("Results for: \"" + searchQuery + "\"", SwingConstants.CENTER);
//        title.setFont(new Font("Arial", Font.BOLD, 22));
//        contentPane.add(title, BorderLayout.NORTH);
//
//        cardsPanel = new JPanel(new GridLayout(0, 3, 15, 15));
//        JScrollPane scrollPane = new JScrollPane(cardsPanel);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//
//        if (results.isEmpty()) {
//            showNotFoundUI(searchQuery, currentUserName);
//        } else {
//            for (RecipeData recipe : results) {
//                cardsPanel.add(createRecipeCard(recipe));
//            }
//        }
//
//        setVisible(true);
//    }
//
//    private JPanel createRecipeCard(RecipeData r) {
//        JPanel card = new JPanel(new BorderLayout());
//        card.setBackground(Color.WHITE);
//        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//        card.setPreferredSize(new Dimension(250, 320));
//
//        try {
//            ImageIcon raw = new ImageIcon(new URL(r.imageUrl));
//            Image scaled = raw.getImage().getScaledInstance(240, 180, Image.SCALE_SMOOTH);
//            card.add(new JLabel(new ImageIcon(scaled)), BorderLayout.NORTH);
//        } catch (Exception e) {
//            JLabel img = new JLabel("Image", SwingConstants.CENTER);
//            img.setPreferredSize(new Dimension(240, 180));
//            card.add(img, BorderLayout.NORTH);
//        }
//
//        JPanel info = new JPanel();
//        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
//        info.setBorder(new EmptyBorder(10, 10, 10, 10));
//        info.setBackground(Color.WHITE);
//
//        JLabel title = new JLabel(r.title);
//        title.setFont(new Font("Arial", Font.BOLD, 14));
//        info.add(title);
//
//        JTextArea desc = new JTextArea(r.description);
//        desc.setFont(new Font("Arial", Font.PLAIN, 12));
//        desc.setLineWrap(true);
//        desc.setWrapStyleWord(true);
//        desc.setOpaque(false);
//        desc.setEditable(false);
//        info.add(desc);
//
//        JButton readMore = new JButton("Read More →");
//        readMore.setFont(new Font("Arial", Font.PLAIN, 12));
//        readMore.setForeground(new Color(234, 88, 12));
//        readMore.setContentAreaFilled(false);
//        readMore.setBorderPainted(false);
//        readMore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        readMore.addActionListener(e -> new RecipeDescriptionPage(
//            r.title,
//            r.description,
//            r.imageUrl,
//            r.ingredients != null ? r.ingredients : List.of("N/A"),
//            r.cookTime != null ? r.cookTime : "Unknown"
//        ).setVisible(true));
//
//        info.add(Box.createVerticalStrut(5));
//        info.add(readMore);
//
//        card.add(info, BorderLayout.CENTER);
//        return card;
//    }
//
//    private void showNotFoundUI(String query, String currentUserName) {
//        cardsPanel.setLayout(new BorderLayout());
//
//        JPanel notFoundPanel = new JPanel();
//        notFoundPanel.setLayout(new BoxLayout(notFoundPanel, BoxLayout.Y_AXIS));
//        notFoundPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        notFoundPanel.setBackground(Color.WHITE);
//
//        JLabel msg = new JLabel("No results found for: \"" + query + "\"", SwingConstants.CENTER);
//        msg.setFont(new Font("Arial", Font.BOLD, 18));
//        msg.setForeground(Color.RED);
//        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
//        notFoundPanel.add(msg);
//
//        notFoundPanel.add(Box.createVerticalStrut(15));
//
//        JButton addBtn = new JButton("Add New Recipe");
//        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addBtn.setBackground(new Color(234, 88, 12));
//        addBtn.setForeground(Color.WHITE);
//        addBtn.setFont(new Font("Arial", Font.BOLD, 14));
//        addBtn.setFocusPainted(false);
//
//        addBtn.addActionListener(e -> {
//            if (currentUserName != null) {
//                new AddRecipePage().setVisible(true);
//            } else {
//                LoginPage loginPage = new LoginPage(name -> {
//                    new AddRecipePage().setVisible(true);
//                });
//                loginPage.setVisible(true);
//            }
//        });
//
//        notFoundPanel.add(addBtn);
//
//        cardsPanel.add(notFoundPanel, BorderLayout.CENTER);
//    }
//}









// thikai thiyo mathi ko purano ho hai bigryo bhane yo 



//package app.ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.List;
//import java.sql.SQLException;
//
//public class SearchResultsPage extends JFrame {
//    private String currentUserName;
//    private JPanel resultsPanel;
//
//    public SearchResultsPage(String keyword, String currentUserName) {
//        this.currentUserName = currentUserName;
//
//        setTitle("Search Results for: " + keyword);
//        setSize(800, 600);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//        JPanel content = new JPanel(new BorderLayout());
//        setContentPane(content);
//
//        JLabel heading = new JLabel("Search Results for: \"" + keyword + "\"", SwingConstants.CENTER);
//        heading.setFont(new Font("Arial", Font.BOLD, 22));
//        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
//        content.add(heading, BorderLayout.NORTH);
//
//        resultsPanel = new JPanel();
//        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
//        JScrollPane scroll = new JScrollPane(resultsPanel);
//        content.add(scroll, BorderLayout.CENTER);
//
//        loadResults(keyword);
//    }
//
//    private void loadResults(String keyword) {
//        resultsPanel.removeAll();
//        try {
//            List<RecipeData> results = RecipeDAO.searchRecipes(keyword);
//
//            if (results.isEmpty()) {
//                JLabel noMatch = new JLabel("No recipes found.");
//                noMatch.setFont(new Font("Arial", Font.ITALIC, 16));
//                noMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
//                resultsPanel.add(Box.createVerticalStrut(20));
//                resultsPanel.add(noMatch);
//
//                JButton addRecipeBtn = new JButton("Add Recipe");
//                addRecipeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//                addRecipeBtn.setFont(new Font("Arial", Font.BOLD, 16));
//                addRecipeBtn.setBackground(new Color(234, 88, 12));
//                addRecipeBtn.setForeground(Color.WHITE);
//                addRecipeBtn.setFocusPainted(false);
//                addRecipeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//
//                addRecipeBtn.addActionListener(e -> {
//                    if (currentUserName != null) {
//                        new AddRecipePage().setVisible(true);
//                    } else {
//                        LoginPage loginPage = new LoginPage(name -> {
//                            this.currentUserName = name;
//                            this.setVisible(true);
//                        });
//                        loginPage.setVisible(true);
//                        this.setVisible(false);
//                    }
//                });
//
//                resultsPanel.add(Box.createVerticalStrut(10));
//                resultsPanel.add(addRecipeBtn);
//            } else {
//                for (RecipeData r : results) {
//                    JPanel card = new JPanel();
//                    card.setLayout(new BorderLayout());
//                    card.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
//                    ));
//
//                    JLabel title = new JLabel(r.title);
//                    title.setFont(new Font("Arial", Font.BOLD, 16));
//                    card.add(title, BorderLayout.NORTH);
//
//                    JTextArea desc = new JTextArea(r.description);
//                    desc.setLineWrap(true);
//                    desc.setWrapStyleWord(true);
//                    desc.setEditable(false);
//                    desc.setOpaque(false);
//                    card.add(desc, BorderLayout.CENTER);
//
//                    JButton viewBtn = new JButton("View Details →");
//                    viewBtn.setForeground(new Color(234, 88, 12));
//                    viewBtn.setContentAreaFilled(false);
//                    viewBtn.setBorderPainted(false);
//                    viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//                    viewBtn.addActionListener(e -> {
//                    new RecipeDescriptionPage(
//                    		    r.title,
//                    		    r.description,
//                    		    r.imageUrl,
//                    		    r.ingredients != null ? r.ingredients : List.of("N/A"),
//                    		    r.instructions != null ? r.instructions : List.of("Step-by-step instructions not available."),
//                    		    r.cookTime != null ? r.cookTime : "Unknown"
//                    		).setVisible(true);
//
//                    });
//                    card.add(viewBtn, BorderLayout.SOUTH);
//
//                    resultsPanel.add(card);
//                    resultsPanel.add(Box.createVerticalStrut(10));
//                }
//            }
//        } catch (SQLException e) {
//            JLabel errorLabel = new JLabel("Error loading search results.");
//            errorLabel.setForeground(Color.RED);
//            resultsPanel.add(errorLabel);
//        }
//
//        resultsPanel.revalidate();
//        resultsPanel.repaint();
//    }
//}
//










// bigryo bhane mathi ko 








package app.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.sql.SQLException;

public class SearchResultsPage extends JFrame {
    private String currentUserName;
    private JPanel resultsPanel;

    public SearchResultsPage(String keyword, String currentUserName) {
        this.currentUserName = currentUserName;

        setTitle("Search Results for: " + keyword);
        setSize(960, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Main content panel with light orange background
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(255, 241, 224)); // soft orange
        setContentPane(content);

        JLabel heading = new JLabel("Search Results for: \"" + keyword + "\"", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(new Color(80, 40, 0)); // deep brown-orange
        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        content.add(heading, BorderLayout.NORTH);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(255, 241, 224)); // match background

        JScrollPane scroll = new JScrollPane(resultsPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        scroll.getViewport().setBackground(new Color(255, 241, 224));
        content.add(scroll, BorderLayout.CENTER);

        loadResults(keyword);
    }

    private void loadResults(String keyword) {
        resultsPanel.removeAll();
        try {
            List<RecipeData> results = RecipeDAO.searchRecipes(keyword);

            if (results.isEmpty()) {
                JLabel noMatch = new JLabel("No recipes found.");
                noMatch.setFont(new Font("Segoe UI", Font.ITALIC, 18));
                noMatch.setForeground(Color.BLACK);
                noMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
                resultsPanel.add(Box.createVerticalStrut(20));
                resultsPanel.add(noMatch);

                JButton addRecipeBtn = new JButton("Add Recipe");
                addRecipeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                addRecipeBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                addRecipeBtn.setBackground(new Color(234, 88, 12));
                addRecipeBtn.setForeground(Color.BLACK);
                addRecipeBtn.setFocusPainted(false);
                addRecipeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                addRecipeBtn.addActionListener(e -> {
                    if (currentUserName != null) {
                        new AddRecipePage().setVisible(true);
                    } else {
                        LoginPage loginPage = new LoginPage(name -> {
                            this.currentUserName = name;
                            this.setVisible(true);
                        });
                        loginPage.setVisible(true);
                        this.setVisible(false);
                    }
                });

                resultsPanel.add(Box.createVerticalStrut(15));
                resultsPanel.add(addRecipeBtn);
            } else {
                for (RecipeData r : results) {
                    JPanel card = new JPanel();
                    card.setLayout(new BorderLayout());
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(240, 180, 100), 1),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                    ));
                    card.setBackground(Color.WHITE);
                    card.setMaximumSize(new Dimension(850, 150));

                    JLabel title = new JLabel(r.title);
                    title.setFont(new Font("Segoe UI", Font.BOLD, 18));
                    title.setForeground(new Color(80, 40, 0));
                    card.add(title, BorderLayout.NORTH);

                    JTextArea desc = new JTextArea(r.description);
                    desc.setLineWrap(true);
                    desc.setWrapStyleWord(true);
                    desc.setEditable(false);
                    desc.setOpaque(false);
                    desc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    desc.setForeground(Color.DARK_GRAY);
                    card.add(desc, BorderLayout.CENTER);

                    JButton viewBtn = new JButton("View Details →");
                    viewBtn.setForeground(new Color(234, 88, 12));
                    viewBtn.setBackground(Color.WHITE);
                    viewBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    viewBtn.setBorderPainted(false);
                    viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    viewBtn.setFocusPainted(false);

                    viewBtn.addActionListener(e -> {
                        new RecipeDescriptionPage(
                            r.title,
                            r.description,
                            r.imageUrl,
                            r.ingredients != null ? r.ingredients : List.of("N/A"),
                            r.instructions != null ? r.instructions : List.of("Step-by-step instructions not available."),
                            r.cookTime != null ? r.cookTime : "Unknown"
                        ).setVisible(true);
                    });

                    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    btnPanel.setOpaque(false);
                    btnPanel.add(viewBtn);
                    card.add(btnPanel, BorderLayout.SOUTH);

                    resultsPanel.add(card);
                    resultsPanel.add(Box.createVerticalStrut(15));
                }
            }
        } catch (SQLException e) {
            JLabel errorLabel = new JLabel("⚠ Error loading search results.");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            resultsPanel.add(errorLabel);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}

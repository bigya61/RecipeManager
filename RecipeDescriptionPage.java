//
//

//
//package app.ui;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.util.List;
//
//public class RecipeDescriptionPage extends JFrame {
//    private static final long serialVersionUID = 1L;
//
//    public RecipeDescriptionPage(String title, String description, String imageUrl, List<String> ingredients, String cookTime) {
//        setTitle(title);
//        setSize(600, 700);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//        setContentPane(contentPane);
//
//        // Image banner
//        JLabel imageLabel = new JLabel();
//        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        imageLabel.setPreferredSize(new Dimension(600, 250));
//        imageLabel.setIcon(new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(580, 250, Image.SCALE_SMOOTH)));
//        contentPane.add(imageLabel, BorderLayout.NORTH);
//
//        // Main content area
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
//        centerPanel.setBackground(Color.WHITE);
//        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        JLabel titleLabel = new JLabel(title);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
//        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        centerPanel.add(titleLabel);
//        centerPanel.add(Box.createVerticalStrut(15));
//
//        JTextArea descArea = new JTextArea(description);
//        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
//        descArea.setWrapStyleWord(true);
//        descArea.setLineWrap(true);
//        descArea.setEditable(false);
//        descArea.setBackground(null);
//        descArea.setBorder(null);
//        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
//        centerPanel.add(descArea);
//        centerPanel.add(Box.createVerticalStrut(20));
//
//        JLabel ingTitle = new JLabel("Ingredients:");
//        ingTitle.setFont(new Font("Arial", Font.BOLD, 16));
//        ingTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        centerPanel.add(ingTitle);
//        centerPanel.add(Box.createVerticalStrut(10));
//
//        for (String ingredient : ingredients) {
//            JLabel item = new JLabel("• " + ingredient);
//            item.setFont(new Font("Arial", Font.PLAIN, 14));
//            item.setAlignmentX(Component.LEFT_ALIGNMENT);
//            centerPanel.add(item);
//        }
//
//        centerPanel.add(Box.createVerticalStrut(20));
//        JLabel cookTimeLabel = new JLabel("Cooking Time: " + cookTime);
//        cookTimeLabel.setFont(new Font("Arial", Font.ITALIC, 14));
//        cookTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        centerPanel.add(cookTimeLabel);
//
//        JScrollPane scrollPane = new JScrollPane(centerPanel);
//        scrollPane.setBorder(null);
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//
//        // Close button
//        JButton closeBtn = new JButton("Close");
//        closeBtn.setBackground(new Color(234, 88, 12));
//        closeBtn.setForeground(Color.WHITE);
//        closeBtn.setFocusPainted(false);
//        closeBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//        closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
//        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        closeBtn.addActionListener(e -> dispose());
//
//        JPanel bottom = new JPanel();
//        bottom.setBackground(Color.WHITE);
//        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
//        bottom.add(closeBtn);
//        contentPane.add(bottom, BorderLayout.SOUTH);
//    }
//}







// all good 






//package app.ui;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.net.URL;
//import java.util.List;
//
//public class RecipeDescriptionPage extends JFrame {
//    private static final long serialVersionUID = 1L;
//
//    public RecipeDescriptionPage(String title, String description, String imageUrl, List<String> ingredients, String cookTime) {
//        setTitle("Recipe: " + title);
//        setSize(600, 720);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//        contentPane.setBackground(Color.WHITE);
//        setContentPane(contentPane);
//
//        // 🖼️ Image Banner
//        JLabel imageLabel = new JLabel();
//        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        imageLabel.setPreferredSize(new Dimension(600, 250));
//        try {
//            ImageIcon imgIcon = new ImageIcon(new URL(imageUrl));
//            Image scaled = imgIcon.getImage().getScaledInstance(580, 250, Image.SCALE_SMOOTH);
//            imageLabel.setIcon(new ImageIcon(scaled));
//        } catch (Exception e) {
//            imageLabel.setText("Image not available");
//            imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
//        }
//        contentPane.add(imageLabel, BorderLayout.NORTH);
//
//        // 📄 Recipe Info Panel
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
//        centerPanel.setBackground(Color.WHITE);
//        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        JLabel titleLabel = new JLabel(title);
//        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
//        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        centerPanel.add(titleLabel);
//        centerPanel.add(Box.createVerticalStrut(20));
//
//        JTextArea descArea = new JTextArea(description);
//        descArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
//        descArea.setWrapStyleWord(true);
//        descArea.setLineWrap(true);
//        descArea.setEditable(false);
//        descArea.setOpaque(false);
//        descArea.setBorder(null);
//        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
//        centerPanel.add(descArea);
//        centerPanel.add(Box.createVerticalStrut(20));
//
//        JLabel ingTitle = new JLabel("🧂 Ingredients:");
//        ingTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
//        ingTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
//        centerPanel.add(ingTitle);
//        centerPanel.add(Box.createVerticalStrut(10));
//
//        for (String ingredient : ingredients) {
//            JLabel item = new JLabel("• " + ingredient);
//            item.setFont(new Font("SansSerif", Font.PLAIN, 14));
//            item.setAlignmentX(Component.LEFT_ALIGNMENT);
//            centerPanel.add(item);
//        }
//
//        centerPanel.add(Box.createVerticalStrut(20));
//        JLabel cookTimeLabel = new JLabel("⏱ Cooking Time: " + cookTime + " mins");
//        cookTimeLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
//        cookTimeLabel.setForeground(Color.DARK_GRAY);
//        cookTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        centerPanel.add(cookTimeLabel);
//
//        // 🔽 Scrollable Center
//        JScrollPane scrollPane = new JScrollPane(centerPanel);
//        scrollPane.setBorder(null);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//
//        // ❌ Close Button
//        JButton closeBtn = new JButton("Close");
//        closeBtn.setBackground(new Color(234, 88, 12));
//        closeBtn.setForeground(Color.WHITE);
//        closeBtn.setFocusPainted(false);
//        closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
//        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        closeBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//        closeBtn.addActionListener(e -> dispose());
//
//        JPanel bottom = new JPanel();
//        bottom.setBackground(Color.WHITE);
//        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
//        bottom.add(closeBtn);
//        contentPane.add(bottom, BorderLayout.SOUTH);
//    }
//}








package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class RecipeDescriptionPage extends JFrame {
    private static final long serialVersionUID = 1L;

    //public RecipeDescriptionPage(String title, String description, String imageUrl, List<String> ingredients, String cookTime, List<String> instructions) {
    public RecipeDescriptionPage(String title, String description, String imageUrl, List<String> ingredients, List<String> instructions, String cookTime) {

        setTitle("Recipe: " + title);
        setSize(600, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // 🖼️ Image Banner
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(600, 250));
        try {
            ImageIcon imgIcon = new ImageIcon(new URL(imageUrl));
            Image scaled = imgIcon.getImage().getScaledInstance(580, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            imageLabel.setText("Image not available");
            imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        }
        contentPane.add(imageLabel, BorderLayout.NORTH);

        // 📄 Recipe Info Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));

        // Description
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);
        descArea.setBorder(null);
        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(descArea);
        centerPanel.add(Box.createVerticalStrut(20));

        // Ingredients Section
        JLabel ingTitle = new JLabel("🧂 Ingredients:");
        ingTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        ingTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(ingTitle);
        centerPanel.add(Box.createVerticalStrut(10));

        if (ingredients == null || ingredients.isEmpty()) {
            JLabel none = new JLabel("• N/A");
            none.setFont(new Font("SansSerif", Font.PLAIN, 14));
            none.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(none);
        } else {
            for (String ingredient : ingredients) {
                JLabel item = new JLabel("• " + ingredient);
                item.setFont(new Font("SansSerif", Font.PLAIN, 14));
                item.setAlignmentX(Component.LEFT_ALIGNMENT);
                centerPanel.add(item);
            }
        }

        centerPanel.add(Box.createVerticalStrut(20));

        // Steps Section
        JLabel stepTitle = new JLabel("📋 Steps:");
        stepTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        stepTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(stepTitle);
        centerPanel.add(Box.createVerticalStrut(10));

        if (instructions == null || instructions.isEmpty()) {
            JLabel noSteps = new JLabel("• Step-by-step instructions not available.");
            noSteps.setFont(new Font("SansSerif", Font.PLAIN, 14));
            noSteps.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(noSteps);
        } else {
            for (int i = 0; i <instructions.size(); i++) {
                JLabel step = new JLabel((i + 1) + ". " + instructions.get(i));
                step.setFont(new Font("SansSerif", Font.PLAIN, 14));
                step.setAlignmentX(Component.LEFT_ALIGNMENT);
                step.setBorder(new EmptyBorder(5, 0, 5, 0));
                centerPanel.add(step);
            }
        }

        centerPanel.add(Box.createVerticalStrut(20));

        // Cook time
        JLabel cookTimeLabel = new JLabel("⏱ Cooking Time: " + cookTime + " mins");
        cookTimeLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        cookTimeLabel.setForeground(Color.DARK_GRAY);
        cookTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(cookTimeLabel);

        // Scrollable center panel
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Close Button
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(234, 88, 12));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeBtn.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.add(closeBtn);
        contentPane.add(bottom, BorderLayout.SOUTH);
    }
}

//
//


package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class RecipeDescriptionPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public RecipeDescriptionPage(String title, String description, String imageUrl, List<String> ingredients, String cookTime) {
        setTitle(title);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Image banner
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(600, 250));
        imageLabel.setIcon(new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(580, 250, Image.SCALE_SMOOTH)));
        contentPane.add(imageLabel, BorderLayout.NORTH);

        // Main content area
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(15));

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setBackground(null);
        descArea.setBorder(null);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(descArea);
        centerPanel.add(Box.createVerticalStrut(20));

        JLabel ingTitle = new JLabel("Ingredients:");
        ingTitle.setFont(new Font("Arial", Font.BOLD, 16));
        ingTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(ingTitle);
        centerPanel.add(Box.createVerticalStrut(10));

        for (String ingredient : ingredients) {
            JLabel item = new JLabel("• " + ingredient);
            item.setFont(new Font("Arial", Font.PLAIN, 14));
            item.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(item);
        }

        centerPanel.add(Box.createVerticalStrut(20));
        JLabel cookTimeLabel = new JLabel("Cooking Time: " + cookTime);
        cookTimeLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        cookTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(cookTimeLabel);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBorder(null);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(234, 88, 12));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.add(closeBtn);
        contentPane.add(bottom, BorderLayout.SOUTH);
    }
}

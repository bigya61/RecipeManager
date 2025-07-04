package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
// Removed unused imports based on compilation warnings
// import java.util.ArrayList; 
// import java.util.List;

/**
 * A dedicated page for users to add new recipes to the system.
 * It provides input fields for various recipe details, including title,
 * description, cook time, ingredients, cooking process, tags, and images.
 */
public class AddRecipePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField cookTimeField;
    private JTextArea ingredientsArea; // For a simplified comma-separated list or multi-line text
    private JTextArea processArea;     // For a simplified step-by-step process as multi-line text
    private JTextField tagsField;      // For comma-separated tags
    private JTextField imageUrlField;  // For a single primary image URL

    /**
     * Constructor for AddRecipePage.
     */
    public AddRecipePage() {
        setTitle("Add New Recipe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setSize(700, 800); // Increased height to accommodate more fields
        setMinimumSize(new Dimension(600, 700)); // Minimum size for responsiveness
        setLocationRelativeTo(null); // Center on screen

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10)); // Added gaps
        contentPane.setBackground(new Color(248, 250, 252)); // Light gray background
        setContentPane(contentPane);

        // --- Header Panel ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(234, 88, 12)); // Orange header
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(headerPanel, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Submit Your Recipe");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // --- Form Content Panel (Scrollable) ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // Stack elements vertically
        formPanel.setBackground(new Color(255, 255, 255)); // White background for the form
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding inside the form
        scrollPane.setViewportView(formPanel); // Set this panel as the scroll pane's view

        // --- Recipe Title ---
        formPanel.add(createLabel("Recipe Title:"));
        titleField = createStyledTextField("e.g., Spicy Chicken Curry");
        formPanel.add(titleField);
        formPanel.add(Box.createVerticalStrut(15));

        // --- Description ---
        formPanel.add(createLabel("Description:"));
        descriptionArea = createStyledTextArea("A brief overview of your recipe...", 5); // 5 rows height
        formPanel.add(new JScrollPane(descriptionArea)); // Make it scrollable if text overflows
        formPanel.add(Box.createVerticalStrut(15));

        // --- Cook Time ---
        formPanel.add(createLabel("Cook Time (e.g., 30 minutes, 1.5 hours):"));
        cookTimeField = createStyledTextField("e.g., 45 minutes");
        formPanel.add(cookTimeField);
        formPanel.add(Box.createVerticalStrut(15));

        // --- Ingredients ---
        formPanel.add(createLabel("Ingredients (one per line):"));
        ingredientsArea = createStyledTextArea("e.g.,\n1 cup flour\n2 eggs\n...", 8); // 8 rows height
        formPanel.add(new JScrollPane(ingredientsArea));
        formPanel.add(Box.createVerticalStrut(15));

        // --- Cooking Process ---
        formPanel.add(createLabel("Cooking Process (step-by-step):"));
        processArea = createStyledTextArea("1. First step...\n2. Next step...\n...", 10); // 10 rows height
        formPanel.add(new JScrollPane(processArea));
        formPanel.add(Box.createVerticalStrut(15));

        // --- Tags ---
        formPanel.add(createLabel("Tags (comma-separated, e.g., vegetarian, quick, dinner):"));
        tagsField = createStyledTextField("e.g., Nepali, spicy, comfort food");
        formPanel.add(tagsField);
        formPanel.add(Box.createVerticalStrut(15));

        // --- Image URL ---
        formPanel.add(createLabel("Image URL (primary image):"));
        imageUrlField = createStyledTextField("e.g., https://example.com/my-recipe-image.jpg");
        formPanel.add(imageUrlField);
        formPanel.add(Box.createVerticalStrut(25));

        // --- Buttons ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // Match form background
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered flow layout
        formPanel.add(buttonPanel); // Add to the form panel

        JButton submitButton = new JButton("Submit Recipe");
        styleButton(submitButton, new Color(234, 88, 12), Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.addActionListener(e -> handleSubmit());
        buttonPanel.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, Color.LIGHT_GRAY, Color.BLACK);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.addActionListener(e -> dispose()); // Close the window
        buttonPanel.add(cancelButton);
    }

    /**
     * Helper method to create styled JLabels.
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(51, 51, 51));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    /**
     * Helper method to create styled JTextFields with placeholder functionality.
     */
    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height + 4)); // Add small extra height
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    /**
     * Helper method to create styled JTextAreas with placeholder functionality.
     */
    private JTextArea createStyledTextArea(String placeholder, int rows) {
        JTextArea area = new JTextArea(rows, 20); // Rows, columns for preferred size
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setForeground(Color.GRAY);
        area.setText(placeholder);

        area.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (area.getText().equals(placeholder)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    area.setText(placeholder);
                    area.setForeground(Color.GRAY);
                }
            }
        });
        return area;
    }

    /**
     * Helper method to style buttons.
     */
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Add a subtle border for white buttons for better visibility
        if (bgColor.equals(Color.WHITE)) {
            button.setBorder(BorderFactory.createLineBorder(new Color(234, 88, 12), 1));
        }
    }

    /**
     * Handles the submission of the recipe form.
     * For now, it just validates and shows a confirmation.
     */
    private void handleSubmit() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String cookTime = cookTimeField.getText();
        String ingredients = ingredientsArea.getText(); // Value is collected but not used for persistence
        String process = processArea.getText();         // Value is collected but not used for persistence
        String tags = tagsField.getText();
        String imageUrl = imageUrlField.getText();

        // Basic validation
        if (title.isEmpty() || title.equals("e.g., Spicy Chicken Curry") ||
            description.isEmpty() || description.equals("A brief overview of your recipe...") ||
            cookTime.isEmpty() || cookTime.equals("e.g., 45 minutes")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields (Title, Description, Cook Time).", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Simulate successful submission
        String confirmationMessage = "Recipe Submitted!\n\n" +
                                     "Title: " + title + "\n" +
                                     "Cook Time: " + cookTime + "\n" +
                                     "Tags: " + tags + "\n" +
                                     "Image URL: " + imageUrl + "\n\n" +
                                     "Thank you for your contribution!";
        JOptionPane.showMessageDialog(this, confirmationMessage, "Recipe Submission Success", JOptionPane.INFORMATION_MESSAGE);

        // Close the page after successful submission
        dispose();
    }

    /**
     * Main method for standalone testing of AddRecipePage.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddRecipePage frame = new AddRecipePage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


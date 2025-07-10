////
////package app.ui;
////
////import app.ui.RecipeDAO; // Import your DAO class
////
////import javax.swing.*;
////import javax.swing.border.EmptyBorder;
////import java.awt.*;
////import java.awt.event.FocusAdapter;
////import java.awt.event.FocusEvent;
////import java.sql.SQLException;
////import java.util.Arrays;
////import java.util.List;
////import java.util.regex.Matcher;
////import java.util.regex.Pattern;
////
/////**
//// * A dedicated page for users to add new recipes to the system.
//// * It provides input fields for various recipe details and uses a DAO
//// * to persist the data to a MySQL database.
//// */
////public class AddRecipePage extends JFrame {
////
////    private static final long serialVersionUID = 1L;
////    private JPanel contentPane;
////
////    private JTextField titleField;
////    private JTextArea descriptionArea;
////    private JTextField cookTimeField;
////    private JTextArea ingredientsArea;
////    private JTextArea processArea;
////    private JTextField tagsField;
////    private JTextField imageUrlField;
////
////    // Instance of the Data Access Object
////    private final RecipeDAO recipeDAO;
////
////    /**
////     * Constructor for AddRecipePage.
////     */
////    public AddRecipePage() {
////        // Initialize the DAO
////        this.recipeDAO = new RecipeDAO();
////
////        setTitle("Add New Recipe");
////        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////        setSize(700, 800);
////        setMinimumSize(new Dimension(600, 700));
////        setLocationRelativeTo(null);
////
////        contentPane = new JPanel();
////        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
////        contentPane.setLayout(new BorderLayout(10, 10));
////        contentPane.setBackground(new Color(248, 250, 252));
////        setContentPane(contentPane);
////
////        // --- Header Panel ---
////        JPanel headerPanel = new JPanel();
////        headerPanel.setBackground(new Color(234, 88, 12));
////        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
////        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
////        contentPane.add(headerPanel, BorderLayout.NORTH);
////
////        JLabel headerLabel = new JLabel("Submit Your Recipe");
////        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
////        headerLabel.setForeground(Color.WHITE);
////        headerPanel.add(headerLabel);
////
////        // --- Form Content Panel (Scrollable) ---
////        JScrollPane scrollPane = new JScrollPane();
////        scrollPane.setBorder(BorderFactory.createEmptyBorder());
////        contentPane.add(scrollPane, BorderLayout.CENTER);
////
////        JPanel formPanel = new JPanel();
////        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
////        formPanel.setBackground(Color.WHITE);
////        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
////        scrollPane.setViewportView(formPanel);
////
////        // --- Form Fields ---
////        formPanel.add(createLabel("Recipe Title:"));
////        titleField = createStyledTextField("e.g., Spicy Chicken Curry");
////        formPanel.add(titleField);
////        formPanel.add(Box.createVerticalStrut(15));
////
////        formPanel.add(createLabel("Description:"));
////        descriptionArea = createStyledTextArea("A brief overview of your recipe...", 5);
////        formPanel.add(new JScrollPane(descriptionArea));
////        formPanel.add(Box.createVerticalStrut(15));
////
////        formPanel.add(createLabel("Cook Time (e.g., 30 minutes, 1.5 hours):"));
////        cookTimeField = createStyledTextField("e.g., 45 minutes");
////        formPanel.add(cookTimeField);
////        formPanel.add(Box.createVerticalStrut(15));
////
////        formPanel.add(createLabel("Ingredients (one per line):"));
////        ingredientsArea = createStyledTextArea("e.g.,\n1 cup flour\n2 eggs\n...", 8);
////        formPanel.add(new JScrollPane(ingredientsArea));
////        formPanel.add(Box.createVerticalStrut(15));
////
////        formPanel.add(createLabel("Cooking Process (step-by-step):"));
////        processArea = createStyledTextArea("1. First step...\n2. Next step...\n...", 10);
////        formPanel.add(new JScrollPane(processArea));
////        formPanel.add(Box.createVerticalStrut(15));
////
////        formPanel.add(createLabel("Tags (comma-separated):"));
////        tagsField = createStyledTextField("e.g., Nepali, spicy, comfort food");
////        formPanel.add(tagsField);
////        formPanel.add(Box.createVerticalStrut(15));
////
////        formPanel.add(createLabel("Image URL (primary image):"));
////        imageUrlField = createStyledTextField("e.g., https://example.com/my-recipe-image.jpg");
////        formPanel.add(imageUrlField);
////        formPanel.add(Box.createVerticalStrut(25));
////
////        // --- Buttons ---
////        JPanel buttonPanel = new JPanel();
////        buttonPanel.setBackground(Color.WHITE);
////        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
////        formPanel.add(buttonPanel);
////
////        JButton submitButton = new JButton("Submit Recipe");
////        styleButton(submitButton, new Color(234, 88, 12), Color.BLACK);
////        submitButton.addActionListener(e -> handleSubmit());
////        buttonPanel.add(submitButton);
////
////        JButton cancelButton = new JButton("Cancel");
////        styleButton(cancelButton, Color.LIGHT_GRAY, Color.BLACK);
////        cancelButton.addActionListener(e -> dispose());
////        buttonPanel.add(cancelButton);
////    }
////
////    private void handleSubmit() {
////        // --- 1. GATHER DATA ---
////        String title = getTextFromField(titleField, "e.g., Spicy Chicken Curry");
////        String description = getTextFromArea(descriptionArea, "A brief overview of your recipe...");
////        String cookTimeText = getTextFromField(cookTimeField, "e.g., 45 minutes");
////        String ingredientsText = getTextFromArea(ingredientsArea, "e.g.,\n1 cup flour\n2 eggs\n...");
////        String processText = getTextFromArea(processArea, "1. First step...\n2. Next step...\n...");
////        List<String> instructionList = Arrays.stream(processText.split("\\n"))
////                .map(String::trim)
////                .filter(s -> !s.isEmpty())
////                .toList();
////        String tagsText = getTextFromField(tagsField, "e.g., Nepali, spicy, comfort food");
////        String imageUrl = getTextFromField(imageUrlField, "e.g., https://example.com/my-recipe-image.jpg");
////
////        // --- 2. VALIDATE ---
////        if (title.isEmpty() || description.isEmpty() || cookTimeText.isEmpty() || ingredientsText.isEmpty() || processText.isEmpty()) {
////            JOptionPane.showMessageDialog(this,
////                "Please fill in all required fields:\nTitle, Description, Cook Time, Ingredients, and Process.",
////                "Validation Error",
////                JOptionPane.WARNING_MESSAGE);
////            return;
////        }
////
////        // --- 3. PARSE COOK TIME ---
////        int cookTimeMins;
////        try {
////            Matcher matcher = Pattern.compile("\\d+").matcher(cookTimeText);
////            if (matcher.find()) {
////                cookTimeMins = Integer.parseInt(matcher.group(0));
////            } else {
////                throw new NumberFormatException();
////            }
////        } catch (NumberFormatException ex) {
////            JOptionPane.showMessageDialog(this,
////                "Invalid Cook Time format. Please enter a number (e.g., '45 minutes').",
////                "Validation Error",
////                JOptionPane.WARNING_MESSAGE);
////            return;
////        }
////
////        // --- 4. PREPARE INGREDIENTS ---
////        List<String> ingredientsList = Arrays.asList(ingredientsText.split("\\r?\\n"));
////
////        // --- 5. HARDCODED USER ID (for now) ---
////        int currentUserId = 1;
////
////        // --- 6. SAVE TO DATABASE ---
////        try {
////            recipeDAO.addRecipe(title, description, cookTimeMins, instructionList, ingredientsList, tagsText, imageUrl, currentUserId);
////
////            JOptionPane.showMessageDialog(this,
////                "Recipe '" + title + "' has been successfully submitted!\nThank you for your contribution!",
////                "Recipe Submission Success",
////                JOptionPane.INFORMATION_MESSAGE);
////
////            dispose(); // ✅ Only dispose once after success
////
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////            JOptionPane.showMessageDialog(this,
////                "A database error occurred while submitting the recipe.\n\nError: " + ex.getMessage(),
////                "Database Error",
////                JOptionPane.ERROR_MESSAGE);
////        }
////    }
////
////    // Helper to get text, returning empty if it's just the placeholder
////    private String getTextFromField(JTextField field, String placeholder) {
////        String text = field.getText().trim();
////        return text.equals(placeholder) ? "" : text;
////    }
////
////    // Helper to get text from a JTextArea
////    private String getTextFromArea(JTextArea area, String placeholder) {
////        String text = area.getText().trim();
////        return text.equals(placeholder) ? "" : text;
////    }
////
////    // --- All other helper methods (createLabel, createStyledTextField, etc.) remain unchanged ---
////
////    private JLabel createLabel(String text) {
////        JLabel label = new JLabel(text);
////        label.setFont(new Font("Arial", Font.BOLD, 16));
////        label.setForeground(new Color(51, 51, 51));
////        label.setAlignmentX(Component.LEFT_ALIGNMENT);
////        label.setBorder(new EmptyBorder(0, 0, 5, 0)); // Add some bottom margin
////        return label;
////    }
////
////    private JTextField createStyledTextField(String placeholder) {
////        JTextField field = new JTextField();
////        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
////        field.setBorder(BorderFactory.createCompoundBorder(
////            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
////            new EmptyBorder(8, 10, 8, 10)
////        ));
////        field.setFont(new Font("Arial", Font.PLAIN, 14));
////        field.setForeground(Color.GRAY);
////        field.setText(placeholder);
////
////        field.addFocusListener(new FocusAdapter() {
////            @Override
////            public void focusGained(FocusEvent e) {
////                if (field.getText().equals(placeholder)) {
////                    field.setText("");
////                    field.setForeground(Color.BLACK);
////                }
////            }
////
////            @Override
////            public void focusLost(FocusEvent e) {
////                if (field.getText().isEmpty()) {
////                    field.setText(placeholder);
////                    field.setForeground(Color.GRAY);
////                }
////            }
////        });
////        return field;
////    }
////
////    private JTextArea createStyledTextArea(String placeholder, int rows) {
////        JTextArea area = new JTextArea(rows, 20);
////        area.setLineWrap(true);
////        area.setWrapStyleWord(true);
////        area.setBorder(BorderFactory.createCompoundBorder(
////            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
////            new EmptyBorder(8, 10, 8, 10)
////        ));
////        area.setFont(new Font("Arial", Font.PLAIN, 14));
////        area.setForeground(Color.GRAY);
////        area.setText(placeholder);
////
////        area.addFocusListener(new FocusAdapter() {
////            @Override
////            public void focusGained(FocusEvent e) {
////                if (area.getText().equals(placeholder)) {
////                    area.setText("");
////                    area.setForeground(Color.BLACK);
////                }
////            }
////            @Override
////            public void focusLost(FocusEvent e) {
////                if (area.getText().isEmpty()) {
////                    area.setText(placeholder);
////                    area.setForeground(Color.GRAY);
////                }
////            }
////        });
////        return area;
////    }
////
////    private void styleButton(JButton button, Color bgColor, Color fgColor) {
////        button.setBackground(bgColor);
////        button.setForeground(fgColor);
////        button.setFocusPainted(false);
////        button.setBorder(new EmptyBorder(12, 25, 12, 25));
////        button.setFont(new Font("Arial", Font.BOLD, 16));
////        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
////    }
////
////    /**
////     * Main method for standalone testing of AddRecipePage.
////     */
////    public static void main(String[] args) {
////        EventQueue.invokeLater(() -> {
////            // Set a modern Look and Feel
////            try {
////                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////            } catch (Exception ex) {
////                ex.printStackTrace();
////            }
////
////            try {
////            	
////         
////                AddRecipePage frame = new AddRecipePage();
////                frame.setVisible(true);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        });
////    }
////}
//package app.ui;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class AddRecipePage extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//    private JPanel contentPane;
//
//    private JTextField titleField;
//    private JTextArea descriptionArea;
//    private JTextField cookTimeField;
//    private JTextArea ingredientsArea;
//    private JTextArea processArea;
//    private JTextField tagsField;
//    private JTextField imageUrlField;
//    private File selectedImageFile = null;
//
//    private final RecipeDAO recipeDAO;
//    
//    private String getTextFromField(JTextField field, String placeholder) {
//        String text = field.getText().trim();
//        return text.equals(placeholder) ? "" : text;
//    }
//
//    private String getTextFromArea(JTextArea area, String placeholder) {
//        String text = area.getText().trim();
//        return text.equals(placeholder) ? "" : text;
//    }
//    
//    public AddRecipePage() {
//        this.recipeDAO = new RecipeDAO();
//
//        setTitle("Add New Recipe");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(700, 800);
//        setMinimumSize(new Dimension(600, 700));
//        setLocationRelativeTo(null);
//
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//        contentPane.setLayout(new BorderLayout(10, 10));
//        contentPane.setBackground(new Color(248, 250, 252));
//        setContentPane(contentPane);
//
//        JPanel headerPanel = new JPanel();
//        headerPanel.setBackground(new Color(234, 88, 12));
//        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
//        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        contentPane.add(headerPanel, BorderLayout.NORTH);
//
//        JLabel headerLabel = new JLabel("Submit Your Recipe");
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
//        headerLabel.setForeground(Color.WHITE);
//        headerPanel.add(headerLabel);
//
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBorder(BorderFactory.createEmptyBorder());
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
//        formPanel.setBackground(Color.WHITE);
//        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        scrollPane.setViewportView(formPanel);
//
//        formPanel.add(createLabel("Recipe Title:"));
//        titleField = createStyledTextField("e.g., Spicy Chicken Curry");
//        formPanel.add(titleField);
//        formPanel.add(Box.createVerticalStrut(15));
//
//        formPanel.add(createLabel("Description:"));
//        descriptionArea = createStyledTextArea("A brief overview of your recipe...", 5);
//        formPanel.add(new JScrollPane(descriptionArea));
//        formPanel.add(Box.createVerticalStrut(15));
//
//        formPanel.add(createLabel("Cook Time (e.g., 30 minutes, 1.5 hours):"));
//        cookTimeField = createStyledTextField("e.g., 45 minutes");
//        formPanel.add(cookTimeField);
//        formPanel.add(Box.createVerticalStrut(15));
//
//        formPanel.add(createLabel("Ingredients (one per line):"));
//        ingredientsArea = createStyledTextArea("e.g.,\n1 cup flour\n2 eggs\n...", 8);
//        formPanel.add(new JScrollPane(ingredientsArea));
//        formPanel.add(Box.createVerticalStrut(15));
//
//        formPanel.add(createLabel("Cooking Process (step-by-step):"));
//        processArea = createStyledTextArea("1. First step...\n2. Next step...\n...", 10);
//        formPanel.add(new JScrollPane(processArea));
//        formPanel.add(Box.createVerticalStrut(15));
//
//        formPanel.add(createLabel("Tags (comma-separated):"));
//        tagsField = createStyledTextField("e.g., Nepali, spicy, comfort food");
//        formPanel.add(tagsField);
//        formPanel.add(Box.createVerticalStrut(15));
//
//        formPanel.add(createLabel("Choose Recipe Image:"));
//        JButton chooseImageButton = new JButton("Select Image");
//        styleButton(chooseImageButton, new Color(100, 149, 237), Color.WHITE);
//        formPanel.add(chooseImageButton);
//
//        imageUrlField = createStyledTextField("No file selected");
//        imageUrlField.setEditable(false);
//        formPanel.add(imageUrlField);
//        formPanel.add(Box.createVerticalStrut(25));
//
//        chooseImageButton.addActionListener(e -> chooseImageFile());
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(Color.WHITE);
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        formPanel.add(buttonPanel);
//
//        JButton submitButton = new JButton("Submit Recipe");
//        styleButton(submitButton, new Color(234, 88, 12), Color.BLACK);
//        submitButton.addActionListener(e -> handleSubmit());
//        buttonPanel.add(submitButton);
//
//        JButton cancelButton = new JButton("Cancel");
//        styleButton(cancelButton, Color.LIGHT_GRAY, Color.BLACK);
//        cancelButton.addActionListener(e -> dispose());
//        buttonPanel.add(cancelButton);
//    }
//
//    private void handleSubmit() {
//    	System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
//
//        String title = getTextFromField(titleField, "e.g., Spicy Chicken Curry");
//        String description = getTextFromArea(descriptionArea, "A brief overview of your recipe...");
//        String cookTimeText = getTextFromField(cookTimeField, "e.g., 45 minutes");
//        String ingredientsText = getTextFromArea(ingredientsArea, "e.g.,\n1 cup flour\n2 eggs\n...");
//        String processText = getTextFromArea(processArea, "1. First step...\n2. Next step...\n...");
//        List<String> instructionList = Arrays.stream(processText.split("\\n")).map(String::trim).filter(s -> !s.isEmpty()).toList();
//        String tagsText = getTextFromField(tagsField, "e.g., Nepali, spicy, comfort food");
//
//        if (title.isEmpty() || description.isEmpty() || cookTimeText.isEmpty() || ingredientsText.isEmpty() || processText.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        int cookTimeMins;
//        try {
//            Matcher matcher = Pattern.compile("\\d+").matcher(cookTimeText);
//            if (matcher.find()) {
//                cookTimeMins = Integer.parseInt(matcher.group(0));
//            } else {
//                throw new NumberFormatException();
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Invalid cook time.", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        List<String> ingredientsList = Arrays.asList(ingredientsText.split("\\r?\\n"));
//        int currentUserId = 1;
//       
//
//        String imagePath = null;
//        if (selectedImageFile != null) {
//            try {
//                Path imagesDir = Paths.get("images");
//                if (!Files.exists(imagesDir)) {
//                    Files.createDirectories(imagesDir);
//                }
//                String uniqueFileName = System.currentTimeMillis() + "_" + selectedImageFile.getName();
//                Path destination = imagesDir.resolve(uniqueFileName);
//                Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
//                imagePath = destination.toString();
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, "Failed to save image.", "Image Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        }
//    	File savedImageFile = (imagePath != null) ? new File(imagePath) : null;
//
//        try {
//            recipeDAO.addRecipe(title, description, cookTimeMins, instructionList, ingredientsList, tagsText, savedImageFile, currentUserId);
//            JOptionPane.showMessageDialog(this, "Recipe submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//            dispose();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Error saving recipe to database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    private void chooseImageFile() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Choose an Image");
//        int result = fileChooser.showOpenDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            selectedImageFile = fileChooser.getSelectedFile();
//            imageUrlField.setText(selectedImageFile.getName());
//        }
//    }
//
//    private JLabel createLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Arial", Font.BOLD, 16));
//        label.setForeground(new Color(51, 51, 51));
//        label.setAlignmentX(Component.LEFT_ALIGNMENT);
//        label.setBorder(new EmptyBorder(0, 0, 5, 0));
//        return label;
//    }
//
//    private JTextField createStyledTextField(String placeholder) {
//        JTextField field = new JTextField();
//        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
//        field.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
//            new EmptyBorder(8, 10, 8, 10)
//        ));
//        field.setFont(new Font("Arial", Font.PLAIN, 14));
//        field.setForeground(Color.GRAY);
//        field.setText(placeholder);
//
//        field.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (field.getText().equals(placeholder)) {
//                    field.setText("");
//                    field.setForeground(Color.BLACK);
//                }
//            }
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (field.getText().isEmpty()) {
//                    field.setText(placeholder);
//                    field.setForeground(Color.GRAY);
//                }
//            }
//        });
//        return field;
//    }
//
//    private JTextArea createStyledTextArea(String placeholder, int rows) {
//        JTextArea area = new JTextArea(rows, 20);
//        area.setLineWrap(true);
//        area.setWrapStyleWord(true);
//        area.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
//            new EmptyBorder(8, 10, 8, 10)
//        ));
//        area.setFont(new Font("Arial", Font.PLAIN, 14));
//        area.setForeground(Color.GRAY);
//        area.setText(placeholder);
//
//        area.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (area.getText().equals(placeholder)) {
//                    area.setText("");
//                    area.setForeground(Color.BLACK);
//                }
//            }
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (area.getText().isEmpty()) {
//                    area.setText(placeholder);
//                    area.setForeground(Color.GRAY);
//                }
//            }
//        });
//        return area;
//    }
//
//    private void styleButton(JButton button, Color bgColor, Color fgColor) {
//        button.setBackground(bgColor);
//        button.setForeground(fgColor);
//        button.setFocusPainted(false);
//        button.setBorder(new EmptyBorder(12, 25, 12, 25));
//        button.setFont(new Font("Arial", Font.BOLD, 16));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//    }
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                AddRecipePage frame = new AddRecipePage();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}
package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddRecipePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField cookTimeField;
    private JTextArea ingredientsArea;
    private JTextArea processArea;
    private JTextField imageUrlField;
    private JComboBox<String> tagsDropdown;
    private File selectedImageFile = null;

    private final RecipeDAO recipeDAO;

    private String getTextFromField(JTextField field, String placeholder) {
        String text = field.getText().trim();
        return text.equals(placeholder) ? "" : text;
    }

    private String getTextFromArea(JTextArea area, String placeholder) {
        String text = area.getText().trim();
        return text.equals(placeholder) ? "" : text;
    }

    public AddRecipePage() {
        this.recipeDAO = new RecipeDAO();

        setTitle("Add New Recipe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 800);
        setMinimumSize(new Dimension(600, 700));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBackground(new Color(248, 250, 252));
        setContentPane(contentPane);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(234, 88, 12));
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(headerPanel, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Submit Your Recipe");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setViewportView(formPanel);

        formPanel.add(createLabel("Recipe Title:"));
        titleField = createStyledTextField("e.g., Spicy Chicken Curry");
        formPanel.add(titleField);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Description:"));
        descriptionArea = createStyledTextArea("A brief overview of your recipe...", 5);
        formPanel.add(new JScrollPane(descriptionArea));
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Cook Time (e.g., 30 minutes, 1.5 hours):"));
        cookTimeField = createStyledTextField("e.g., 45 minutes");
        formPanel.add(cookTimeField);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Ingredients (one per line):"));
        ingredientsArea = createStyledTextArea("e.g.,\n1 cup flour\n2 eggs\n...", 8);
        formPanel.add(new JScrollPane(ingredientsArea));
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Cooking Process (step-by-step):"));
        processArea = createStyledTextArea("1. First step...\n2. Next step...\n...", 10);
        formPanel.add(new JScrollPane(processArea));
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Select Tag:"));
        tagsDropdown = new JComboBox<>(new String[] {"Veg", "Non-Veg"});
        tagsDropdown.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        tagsDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        tagsDropdown.setAlignmentX(Component.LEFT_ALIGNMENT);
        tagsDropdown.setEditable(false);
        formPanel.add(tagsDropdown);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Choose Recipe Image:"));
        JButton chooseImageButton = new JButton("Select Image");
        styleButton(chooseImageButton, new Color(100, 149, 237), Color.BLACK);
        formPanel.add(chooseImageButton);

        imageUrlField = createStyledTextField("No file selected");
        imageUrlField.setEditable(false);
        formPanel.add(imageUrlField);
        formPanel.add(Box.createVerticalStrut(25));

        chooseImageButton.addActionListener(e -> chooseImageFile());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        formPanel.add(buttonPanel);

        JButton submitButton = new JButton("Submit Recipe");
        styleButton(submitButton, new Color(234, 88, 12), Color.BLACK);
        submitButton.addActionListener(e -> handleSubmit());
        buttonPanel.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, Color.LIGHT_GRAY, Color.BLACK);
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
    }

    private void handleSubmit() {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        String title = getTextFromField(titleField, "e.g., Spicy Chicken Curry");
        String description = getTextFromArea(descriptionArea, "A brief overview of your recipe...");
        String cookTimeText = getTextFromField(cookTimeField, "e.g., 45 minutes");
        String ingredientsText = getTextFromArea(ingredientsArea, "e.g.,\n1 cup flour\n2 eggs\n...");
        String processText = getTextFromArea(processArea, "1. First step...\n2. Next step...\n...");
        List<String> instructionList = Arrays.stream(processText.split("\\n")).map(String::trim).filter(s -> !s.isEmpty()).toList();
        String tagsText = (String) tagsDropdown.getSelectedItem();

        if (title.isEmpty() || description.isEmpty() || cookTimeText.isEmpty() || ingredientsText.isEmpty() || processText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cookTimeMins;
        try {
            Matcher matcher = Pattern.compile("\\d+").matcher(cookTimeText);
            if (matcher.find()) {
                cookTimeMins = Integer.parseInt(matcher.group(0));
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cook time.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> ingredientsList = Arrays.asList(ingredientsText.split("\\r?\\n"));
        int currentUserId = 1;

        String imagePath = null;
        if (selectedImageFile != null) {
            try {
                Path imagesDir = Paths.get("images");
                if (!Files.exists(imagesDir)) {
                    Files.createDirectories(imagesDir);
                }
                String uniqueFileName = System.currentTimeMillis() + "_" + selectedImageFile.getName();
                Path destination = imagesDir.resolve(uniqueFileName);
                Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                imagePath = destination.toString();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to save image.", "Image Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        File savedImageFile = (imagePath != null) ? new File(imagePath) : null;

        try {
            recipeDAO.addRecipe(title, description, cookTimeMins, instructionList, ingredientsList, tagsText, savedImageFile, currentUserId);
            JOptionPane.showMessageDialog(this, "Recipe submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving recipe to database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an Image");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            imageUrlField.setText(selectedImageFile.getName());
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(51, 51, 51));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(0, 0, 5, 0));
        return label;
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
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

    private JTextArea createStyledTextArea(String placeholder, int rows) {
        JTextArea area = new JTextArea(rows, 20);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
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

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 25, 12, 25));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                AddRecipePage frame = new AddRecipePage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

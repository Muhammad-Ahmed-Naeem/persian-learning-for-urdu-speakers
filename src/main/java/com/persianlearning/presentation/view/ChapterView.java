package com.persianlearning.presentation.view;

import com.persianlearning.model.Chapter;
import com.persianlearning.presentation.controller.ChapterController;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

public class ChapterView {
    private ChapterController controller;

    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> chapterList;
    private JTextField titleField;
    private List<Chapter> currentChapters;
    private String selectedChapterId;

    public ChapterView() {
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Manage Chapters - Persian Learning App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        chapterList = new JList<>(listModel);
        chapterList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        chapterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        chapterList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = chapterList.getSelectedIndex();
                if (index >= 0 && currentChapters != null && index < currentChapters.size()) {
                    Chapter selected = currentChapters.get(index);
                    selectedChapterId = selected.getChapterId();
                    titleField.setText(selected.getTitle());
                }
            }
        });

        titleField = new JTextField();
        titleField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton createButton = new JButton("Create Chapter");
        JButton renameButton = new JButton("Rename");
        JButton deleteButton = new JButton("Delete");
        JButton refreshButton = new JButton("Refresh");

        createButton.addActionListener(e -> {
            if (controller != null) {
                controller.createChapter(titleField.getText());
            }
        });

        renameButton.addActionListener(e -> {
            if (controller != null) {
                if (selectedChapterId != null) {
                    controller.renameChapter(selectedChapterId, titleField.getText());
                } else {
                    displayMessage("Please select a chapter from the list to rename.");
                }
            }
        });

        deleteButton.addActionListener(e -> {
            if (controller != null) {
                if (selectedChapterId != null) {
                    controller.requestDelete(selectedChapterId);
                } else {
                    displayMessage("Please select a chapter to delete.");
                }
            }
        });

        refreshButton.addActionListener(e -> {
            if (controller != null) {
                controller.showChapterList();
            }
        });

        // Top input panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        inputPanel.add(new JLabel("Chapter Title:"), BorderLayout.NORTH);
        inputPanel.add(titleField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(createButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Center list panel
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        centerPanel.add(new JLabel("All Chapters:"), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(chapterList), BorderLayout.CENTER);

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
    }

    public void setController(ChapterController controller) {
        this.controller = controller;
    }

    public void displayChapterList(List<Chapter> chapters) {
        this.currentChapters = chapters;
        listModel.clear();
        selectedChapterId = null;
        if (chapters != null) {
            for (Chapter c : chapters) {
                listModel.addElement(c.getTitle());
            }
        }
    }

    public void displayChapter(Chapter chapter) {
        if (chapter != null) {
            this.selectedChapterId = chapter.getChapterId();
            titleField.setText(chapter.getTitle());
        }
    }

    public void displayDeleteWarning(String message) {
        int choice = JOptionPane.showConfirmDialog(
                frame,
                message,
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (controller != null && selectedChapterId != null) {
            controller.confirmDelete(selectedChapterId, choice == JOptionPane.YES_OPTION);
        }
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void setVisible(boolean visible) {
        if (frame != null) {
            frame.setVisible(visible);
        }
    }
}

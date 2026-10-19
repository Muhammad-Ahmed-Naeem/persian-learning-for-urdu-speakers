package com.persianlearning.presentation.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class AdminView {

    private JFrame frame;
    private ChapterView chapterView;
    private ChapterStructureView structureView;
    private ChapterContentView contentView;
    private GlossaryView glossaryView;

    public AdminView() {
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Persian Learning App - Administrator Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 380);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Persian Learning App (Administration)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnChapters = new JButton("1. Manage Chapters & Basic Details (UC-01)");
        JButton btnStructure = new JButton("2. Configure Chapter Structure (UC-02)");
        JButton btnContent = new JButton("3. Manage Instructional Content (UC-03)");
        JButton btnGlossary = new JButton("4. Manage Chapter Glossary (UC-04)");

        btnChapters.addActionListener(e -> openChapterView());
        btnStructure.addActionListener(e -> openStructureView());
        btnContent.addActionListener(e -> openContentView());
        btnGlossary.addActionListener(e -> openGlossaryView());

        buttonPanel.add(btnChapters);
        buttonPanel.add(btnStructure);
        buttonPanel.add(btnContent);
        buttonPanel.add(btnGlossary);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(mainPanel);
    }

    public void setChapterView(ChapterView chapterView) {
        this.chapterView = chapterView;
    }

    public void setStructureView(ChapterStructureView structureView) {
        this.structureView = structureView;
    }

    public void setContentView(ChapterContentView contentView) {
        this.contentView = contentView;
    }

    public void setGlossaryView(GlossaryView glossaryView) {
        this.glossaryView = glossaryView;
    }

    public void showMainMenu() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    public void openChapterView() {
        if (chapterView != null) {
            chapterView.setVisible(true);
        }
    }

    public void openStructureView() {
        // Member 2 will connect their view
    }

    public void openContentView() {
        // Member 3 will connect their view
    }

    public void openGlossaryView() {
        // Member 4 will connect their view
    }
}

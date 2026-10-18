package com.persianlearning.model;

public class ChapterComponent {
    private String componentId;
    private String chapterId;
    private ComponentType type;
    private int orderIndex;
    private String content;

    public ChapterComponent(String componentId, String chapterId, ComponentType type, int orderIndex, String content) {
        this.componentId = componentId;
        this.chapterId = chapterId;
        this.type = type;
        this.orderIndex = orderIndex;
        this.content = content;
    }

    public String getComponentId() {
        return componentId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public ComponentType getType() {
        return type;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

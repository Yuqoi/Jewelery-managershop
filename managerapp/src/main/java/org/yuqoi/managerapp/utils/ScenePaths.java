package org.yuqoi.managerapp.utils;

public enum ScenePaths {
    LOGINPANEL("/views/login-panel.fxml"),
    MAINPANEL("/views/main-panel.fxml");

    private final String fxmlFileName;

    ScenePaths(String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }

}

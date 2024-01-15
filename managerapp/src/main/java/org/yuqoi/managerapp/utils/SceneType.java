package org.yuqoi.managerapp.utils;

public enum SceneType {
    LOGINSCENE("views/login-panel.fxml"),
    MAINMENUSCENE("views/main-panel.fxml");
    
    private final String fxmlPath;
    SceneType(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath(){
        return fxmlPath;
    }
}

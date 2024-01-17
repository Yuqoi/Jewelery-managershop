package org.yuqoi.managerapp.utils;

public enum ScenePaths {
    // all fxml files
    LOGINPANEL("views/login-panel.fxml"),
    MAINPANEL("views/main-panel.fxml"),
    INVENTORYPANEL("views/inventory-panel.fxml"),
    INVOICEPANEL("views/invoice-panel.fxml"),
    SALESPANEL("views/sales-panel.fxml"),
    SETTINGSPANEL("views/settings-panel.fxml");

    private final String fxmlFileName;

    ScenePaths(String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }

}

package org.yuqoi.managerapp.utils;

public enum ScenePaths {
    // all fxml files
    LOGINPANEL("views/windows/login-panel.fxml"),
    MAINPANEL("views/windows/main-panel.fxml"),
    INVENTORYPANEL("views/panels/inventory-panel.fxml"),
    INVOICEPANEL("views/panels/invoice-panel.fxml"),
    SALESPANEL("views/panels/sales-panel.fxml"),
    SETTINGSPANEL("views/panels/settings-panel.fxml");

    private final String fxmlFileName;

    ScenePaths(String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }

}

package ch07IoStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyNotePad extends Application {
	static TextArea textArea;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// 文件选取器
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Java Sourse Files", "*.java"));

		// 创建MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.setStyle("-fx-background-color:lightblue");

		/************************************
		 * 创建 Menu, 文件菜单条
		 ************************************/
		Menu menuFile = new Menu("文件");
		MenuItem menuNew = new MenuItem("新建");
		menuNew.setAccelerator(KeyCombination.valueOf("Ctrl+N"));
		MenuItem menuOpen = new MenuItem("打开");
		// 设置menuItem的快捷键
		menuOpen.setAccelerator(KeyCombination.valueOf("Ctrl+O"));
		menuOpen.setOnAction((final ActionEvent e) -> {
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				openFile(file);
			}
		});

		MenuItem menuSave = new MenuItem("保存");
		menuSave.setAccelerator(KeyCombination.valueOf("Ctrl+S"));
		MenuItem menuSaveAs = new MenuItem("另存");
		// 创建分割线
		SeparatorMenuItem separator1 = new SeparatorMenuItem();
		SeparatorMenuItem separator2 = new SeparatorMenuItem();

		MenuItem menuExit = new MenuItem("退出");
		menuExit.setOnAction((ActionEvent e) -> {
			// 严格的话，需判断文件保存与否，或确认是否退出
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // 创建一个确认对话框
			alert.setHeaderText("确定要退出MyNotePad吗？"); // 设置对话框的头部文本
			// 设置对话框的内容文本
//			alert.setContentText("确定要退出MyNotePad吗？");
			// 显示对话框，并等待按钮返回
			Optional<ButtonType> buttonType = alert.showAndWait();
			// 判断返回的按钮类型是确定还是取消，再据此分别进一步处理
			if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) { // 单击了确定按钮OK_DONE
				System.exit(0);
			}
		});
		// 将MenuItem放在对应的Menu上e
		menuFile.getItems().addAll(menuNew, menuOpen, separator1, menuSave, menuSaveAs, separator2, menuExit);// 将分割线加进来

		/************************************
		 * 创建 Menu, 编辑菜单条
		 ************************************/
		Menu menuEdit = new Menu("编辑");
		MenuItem menuSelctAll = new MenuItem("全选");
		menuSelctAll.setAccelerator(KeyCombination.valueOf("Ctrl+A"));
		MenuItem menuCopy = new MenuItem("复制");
		menuCopy.setAccelerator(KeyCombination.valueOf("Ctrl+C"));
		MenuItem menuPaste = new MenuItem("粘贴");
		menuPaste.setAccelerator(KeyCombination.valueOf("Ctrl+V"));
		// 创建分割线
		SeparatorMenuItem separator3 = new SeparatorMenuItem();
		// 查找替换菜单项
		MenuItem menuFind = new MenuItem("查找");
		MenuItem menuReplace = new MenuItem("替换");

		menuEdit.getItems().addAll(menuSelctAll, menuCopy, menuPaste, separator3, menuFind, menuReplace);

		// 创建 帮助子菜单 Menu
		Menu menuHelp = new Menu("帮助");
		MenuItem menuGuide = new MenuItem("指南");
		MenuItem menuAbout = new MenuItem("关于");

		menuAbout.setOnAction((ActionEvent e) -> { // 设置按钮的单击事件
			Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
			alert.setHeaderText("关于本软件"); // 设置对话框的头部文本
			// 设置对话框的内容文本
			alert.setContentText("RSGISJAVALAB 版权所有 @2020");
			alert.show(); // 显示对话框
		});

		menuHelp.getItems().addAll(menuGuide, menuAbout);

		// MenuBar，装入各菜单条
		menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);

		// 创建MenuItem类
		// 还可以对MenuItem设置图标

		// 将menuBar加入到布局类mainPane上

		// 文本编辑组件
		textArea = new TextArea();

		// 创建布局类, 放置编辑区域
		BorderPane mainPane = new BorderPane();

		mainPane.setTop(menuBar);
		mainPane.setCenter(textArea);

		// 创建场景图
//		Scene scene = new Scene(anchorPane);
		Scene scene = new Scene(mainPane);

		primaryStage.setScene(scene);
		primaryStage.setHeight(400);
		primaryStage.setWidth(500);
		primaryStage.setTitle("MyNotepad");

		// 用户点击关窗按钮时 ......
		primaryStage.setOnCloseRequest((WindowEvent event) -> {
			// 严格的话，需判断文件保存与否，或确认是否退出
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // 创建一个确认对话框
			alert.setHeaderText("确定要退出MyNotePad吗？"); // 设置对话框的头部文本
			// 设置对话框的内容文本
//				alert.setContentText("确定要退出MyNotePad吗？");
			// 显示对话框，并等待按钮返回
			Optional<ButtonType> buttonType = alert.showAndWait();
			// 判断返回的按钮类型是确定还是取消，再据此分别进一步处理
			if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) { // 单击了确定按钮OK_DONE
				System.exit(0);
			}
		});

		primaryStage.show();

	}

	private void openFile(File file) {
		textArea.setText("");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null)
				textArea.appendText(line + "\n");
			in.close();
			textArea.positionCaret(0);
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}

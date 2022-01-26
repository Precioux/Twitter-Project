package graphics;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class MenuItemAccelerators extends Application {
    @Override
    public void start(Stage stage) {
        //Creating image view files
//        ImageView imgView1 = new ImageView("UIControls/open.png");
//        imgView1.setFitWidth(20);
//        imgView1.setFitHeight(20);
//        ImageView imgView2 = new ImageView("UIControls/Save.png");
//        imgView2.setFitWidth(20);
//        imgView2.setFitHeight(20);
//        ImageView imgView3 = new ImageView("UIControls/Exit.png");
//        imgView3.setFitWidth(20);
//        imgView3.setFitHeight(20);
        //Creating menu
        Menu fileMenu = new Menu("File");
        //Creating menu Items
        MenuItem item1 = new MenuItem("Open File");
        MenuItem item2 = new MenuItem("Save file");
        MenuItem item3 = new MenuItem("Exit");
        //Setting accelerators to the menu items
        item1.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        item2.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        item3.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        //Adding all the menu items to the menu
        fileMenu.getItems().addAll(item1, item2, item3);
        //Creating a menu bar and adding menu to it.
        MenuBar menuBar = new MenuBar(fileMenu);
        menuBar.setTranslateX(200);
        menuBar.setTranslateY(20);
        //Setting the stage
        Group root = new Group(menuBar);
        Scene scene = new Scene(root, 595, 200, Color.BEIGE);
        stage.setTitle("Menu Example");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
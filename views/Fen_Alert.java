package roll_the_ball.views;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import roll_the_ball.models.RollTheBall;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class Fen_Alert
{
	public static void display(Stage stage,String message,String titre) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(titre);
		alert.setHeaderText("Alert !");
		alert.setContentText(message);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			if(titre.equals("Quitter")) {
				RollTheBall r=Fen_Menu.rollTheball3;
			 r.sauver_objets();
			// Fen_Menu.rollTheball3.readToFile();
			stage.close();
			
			}
			else
			alert.close();
		}
	}

}

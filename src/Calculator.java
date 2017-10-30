import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Calculator extends Application {
	int scenewid = 160;
	int scenehei = 210;
	int btnspace = 30;
	int yadjust = 27;
	int initbx = 20;
	int initby = 30;
	
	Label disp;
	Button[] nums;
	Button add, sub, mult, div, eq, cl;
	
	int stage = 0;
	int op1;
	int op2;
	String oper;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Calculate the Heavens");
		disp = new Label("0");
		VBox root = new VBox();
		
		Scene scene = new Scene(root, scenewid, scenehei);
		stage.setScene(scene);
		
		disp.setTranslateX(initbx);
		disp.setTranslateY(20);
		root.getChildren().add(disp);
		
		nums = new Button[10];
		int n = 7;
		int xpos = initbx;
		int ypos = initby;
		for(int i = 0; i < 9; i++) {
			createBtn(i, xpos, ypos, n+"");
			
			xpos += btnspace;
			ypos -= yadjust;
			n++;
			if(xpos >= initbx + 3*btnspace) {
				xpos = initbx;
				ypos += btnspace;
				n -= 6;
			}
			
			root.getChildren().add(nums[i]);
		}
		// create 0 button
		createBtn(9, xpos, ypos, "0");
		root.getChildren().add(nums[9]);
		
		add = new Button("+");
		add.setTranslateX(initbx + 3*btnspace);
		add.setTranslateY(-240);
		add.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleBtn(add);
			}
		});
		root.getChildren().add(add);
		
		sub = new Button("-");
		sub.setTranslateX(initbx + 3*btnspace);
		sub.setTranslateY(-237);
		sub.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleBtn(sub);
			}
		});
		root.getChildren().add(sub);
		
		mult = new Button("*");
		mult.setTranslateX(initbx + 3*btnspace);
		mult.setTranslateY(-234);
		mult.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleBtn(mult);
			}
		});
		root.getChildren().add(mult);
		
		div = new Button("/");
		div.setTranslateX(initbx + 3*btnspace);
		div.setTranslateY(-231);
		div.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleBtn(div);
			}
		});
		root.getChildren().add(div);
		
		eq = new Button("=");
		eq.setTranslateX(initbx + 2*btnspace);
		eq.setTranslateY(-258);
		eq.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleBtn(eq);
			}
		});
		root.getChildren().add(eq);
		
		cl = new Button("c");
		cl.setTranslateX(initbx + 1*btnspace);
		cl.setTranslateY(-285);
		cl.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handleBtn(cl);
			}
		});
		root.getChildren().add(cl);
		
		stage.show();
	}
	
	private void createBtn(int i, int x, int y, String t) {
		nums[i] = new Button(t);
		nums[i].setTranslateX(x);
		nums[i].setTranslateY(y);
		nums[i].setOnAction(new EventHandler<ActionEvent>() {
			// what happens when user clicks button
			public void handle(ActionEvent event) {
				handleBtn(nums[i]);
			}
		});
	}
	
	private void handleBtn(Button b) {
		String tmp;
		System.out.println(b.getText());
		if(b.getText() == "+" || b.getText() == "-" ||
		   b.getText() == "*" || b.getText() == "/") {
			if(stage == 0) {
				oper = b.getText();
				stage = 1;
			}
		} else if(b.getText() == "=") {
			if(stage == 1) {
				tmp = oper;
				if(tmp == "+")
					disp.setText((op1 + op2) + "");
				else if(tmp == "-")
					disp.setText((op1 - op2) + "");
				else if(tmp == "*")
					disp.setText((op1 * op2) + "");
				else if(tmp == "/") {
					if(op2 == 0)
						disp.setText("Excuse me?");
					else
						disp.setText((op1 / op2) + "");
				}
				op1 = op2 = stage = 0;
			}
		} else if(b.getText() == "c") {
			disp.setText("0");
			op1 = op2 = stage = 0;
		} else { // hit a number button
			if(stage == 0) {
				op1 = 10*op1 + Integer.parseInt(b.getText());
				disp.setText(op1+"");
			} else if(stage == 1) {
				op2 = 10*op2 + Integer.parseInt(b.getText());
				disp.setText(op2+"");
			}
		}
		
	}
}

package com.example.sberbankoffline2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.Console;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    //String url = "jdbc:mysql://213.167.217.126:3306/bank";
    String url = "jdbc:mysql://213.167.207.249/bank";
    String user = "isaeva";
    String password = "julia092004";
    private Connection connection;
    String nowUser = "";

    //entranceForm
    @FXML
    private Button button1, button2, button3;
    @FXML
    private Label label3, label4;
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField passwordField1;
    //entranceForm

    //exitForm
    @FXML
    private Button button4, button5, button6;
    @FXML
    private Label label10, label101, label102;
    @FXML
    private TextField textField2;
    @FXML
    private PasswordField passwordField2;
    //exitForm

    //maneForm
    @FXML
    private Button button7, button8, button9, button10, button11;
    @FXML
    public Label label103;
    @FXML
    private Label label9;
    //errorForm
    @FXML
    private Button button18, button19;
    @FXML
    private TextArea bug1;
    //errorForm

    //moneyForm
    @FXML
    private Button button17;
    @FXML
    private TextField textField3;
    @FXML
    private Button X_button;
    //moneyForm

    //transferForm
    @FXML
    private Button button12, button13, button14, button15;
    //transferForm

    //historyForm
    @FXML
    private Button button16;
    @FXML
    private ListView listView1;
    @FXML
    private ScrollBar scrollBar1;
    //historyForm

    @FXML
    private AnchorPane entranceForm, exitForm, maneForm, errorForm, moneyForm, transferForm, historyForm;


    //entranceForm
    @FXML
    private void button1_Click() {
        passwordCheck();
    }

    @FXML
    public void passwordCheck() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sql = "select log from users where log = '"+textField1.getText()+"' and pass = '"+passwordField1.getText()+"';";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            nowUser = textField1.getText();
            sql = "select balance from users where log = '"+nowUser+"';";
            statement = connection.createStatement();
            ResultSet answ = statement.executeQuery(sql);
            answ.next();
            label103.setText(answ.getString(1));
            textField1.setStyle("-fx-background-color: #FFFFFF;");
            passwordField1.setStyle("-fx-background-color: #FFFFFF;");
            label3.setVisible(false);
            label4.setVisible(false);
            label9.setText(nowUser);
            entranceForm.setVisible(false);
            maneForm.setVisible(true);
        } catch (SQLException e) {
            bug1.setText(String.valueOf(e));
            textField1.setStyle("-fx-background-color: #F08080;");
            passwordField1.setStyle("-fx-background-color: #F08080;");
            label3.setVisible(true);
            label4.setVisible(true);
            passwordField1.setText("");
            textField1.setText("");
        }
        catch (ClassNotFoundException e){
            bug1.setText(String.valueOf(e));
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void adminCheck() {
        String getLogin = textField1.getText();
        if (getLogin.equals("Администратор")) {

        } else {
            

        }
    }

    @FXML
    public void button3_Click() {
        System.exit(0);
    }

    @FXML
    public void button2_Click() {
        entranceForm.setVisible(false);
        exitForm.setVisible(true);
    }
    //entranceForm

    //exitForm
    @FXML
    public void button6_Click() {
        System.exit(0);
    }

    @FXML
    public void button5_Click() {
        exitForm.setVisible(false);
        entranceForm.setVisible(true);
    }

    @FXML
    public void button4_Click() {
        regCheck();
    }

    @FXML
    public void regCheck() {
        label102.setVisible(false);
        label101.setVisible(false);
        label10.setVisible(false);
        textField2.setStyle("-fx-background-color: #FFFFFF;");
        passwordField2.setStyle("-fx-background-color: #FFFFFF;");
        String getLogin = textField2.getText();
        String getPassword = passwordField2.getText();

        if (getLogin.equals(null) || getLogin.equals("")) {
            label10.setVisible(true);
            label101.setVisible(true);
            textField2.setStyle("-fx-background-color: #F08080;");
            passwordField2.setStyle("-fx-background-color: #F08080;");
        } else {
            label101.setVisible(false);
            label10.setVisible(false);
            textField2.setStyle("-fx-background-color: #FFFFFF;");
            passwordField2.setStyle("-fx-background-color: #FFFFFF;");
            if (getPassword.equals(null) || getPassword.equals("")) {
                label10.setVisible(true);
                label101.setVisible(true);
                textField2.setStyle("-fx-background-color: #F08080;");
                passwordField2.setStyle("-fx-background-color: #F08080;");
            } else {
                reg();
                nowUser = textField2.getText();
                textField2.setStyle("-fx-background-color: #90EE90;");
                passwordField2.setStyle("-fx-background-color: #90EE90;");
                label102.setVisible(true);
                label101.setVisible(false);
                label10.setVisible(false);
                exitForm.setVisible(false);
                maneForm.setVisible(true);
            }
        }
    }

    public void reg(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select count(*) from users;";
            Statement statement = connection.createStatement();
            ResultSet answ = statement.executeQuery(sql);
            answ.next();
            String a = answ.getString(1);
            sql = "insert into users value ('"+a+"','"+textField2.getText()+"','"+passwordField2.getText()+"');";
            connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
        }
        catch (ClassNotFoundException e){
           // bug1.setText(String.valueOf(e));
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    //exitForm

    //maneForm
    @FXML
    public void button7_Click() {
        maneForm.setVisible(false);
        historyForm.setVisible(true);
    }

    @FXML
    public void button8_Click() {
        maneForm.setVisible(false);
        transferForm.setVisible(true);
    }

    @FXML
    public void button9_Click() {
        maneForm.setVisible(false);
        entranceForm.setVisible(true);
    }

    @FXML
    public void button10_Click() {
        System.exit(1);
    }

    @FXML
    public void button18_Click() {
        System.exit(0);
    }

    @FXML
    public void button19_Click() {
        errorForm.setVisible(false);
        maneForm.setDisable(true);
    }


    @FXML
    private void button11_Click() {
        moneyForm.setVisible(true);
    }

    //moneyForm
    @FXML
    public void button17_Click() {
        double newMoney = Double.parseDouble(textField3.getText());
        if (textField3.getText().equals("")) {
            textField3.setText("Ошибка! Введите сумму");
        } else {
            double oldMoney = Double.parseDouble(label103.getText());
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                Connection connection = DriverManager.getConnection(url, user, password);
                String sql = "update users set balance = "+(newMoney + oldMoney)+" where log = '"+nowUser+"';";
                Statement statement = connection.createStatement();
                statement.execute(sql);
                sql = "select balance from users where log = '"+nowUser+"';";
                statement = connection.createStatement();
                ResultSet answ = statement.executeQuery(sql);
                answ.next();
                label103.setText(answ.getString(1));
            } catch (SQLException e) {
            }
            catch (ClassNotFoundException e){
                //bug1.setText(String.valueOf(e));
            }
            catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        textField3.setText("");
    }

    @FXML
    private void X_button_click() {
        moneyForm.setVisible(false);
    }
    //moneyForm

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "";
            Statement statement = connection.createStatement();
            statement.execute(sql);
           // bug1.setText("Подключение к базе данных успешно установлено!");
        } catch (SQLException e) {
         //   bug1.setText("Ошибка при подключении к базе данных:\n" + e);

        }
        catch (ClassNotFoundException e){
          //  bug1.setText("Ошибка драйвера:\n" + e);
        }
        catch (Exception e){
          //  bug1.setText(e.toString());
        }
    }


    //maneForm

    //historyForm
    @FXML
    public void button16_Click() {
        historyForm.setVisible(false);
        maneForm.setVisible(true);
    }
    //historyForm

    //transferForm
    @FXML
    public void button15_Click() {
        transferForm.setVisible(false);
        maneForm.setVisible(true);
    }
    //transferForm


}
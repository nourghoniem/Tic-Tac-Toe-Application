//
//package TicTacToeClients.view;
//
//import serverside.Players;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.chart.PieChart.Data;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.util.Callback;
//import serverside.DatabaseConnection;
//
//
//public class ViewPlayersController implements Initializable {
//    DatabaseConnection data = new DatabaseConnection();
//    List<Players> players = data.getPlayers();
//    SceneController s = new SceneController();
//    String getssss;
//     @FXML
//     private TableView<Players> table;
//     @FXML
//     private TableColumn<Players, String> usernamecol;
//     @FXML
//     private TableColumn<Players, String> statuscol;
//     @FXML
//     private TableColumn<Players, Void> invitecol;
//     Button invite = new Button("Invite");
//     @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//       s.getSignInInfo();
//       usernamecol.setCellValueFactory(new PropertyValueFactory<Players, String>("username"));
//       statuscol.setCellValueFactory(new PropertyValueFactory<Players, String>("status"));
//       invitecol.setCellValueFactory(new PropertyValueFactory<Players, Void>("invite"));
//       table.setItems(getPlayersList());
//       addButtonToTable();
//    }
//
//    ObservableList<Players> getPlayersList(){
//     ObservableList<Players> playerm = FXCollections.observableArrayList();
//       for(int i = 0; i < players.size(); i++){
//           final Players pl = Players.usernameStatus(players.get(i).getUsername(), players.get(i).getStatus());
//           playerm.add(pl);
//       }
//       return playerm;
//    }
//
//   private void addButtonToTable() {
//       
//        Callback<TableColumn<Players, Void>, TableCell<Players, Void>> cellFactory = new Callback<TableColumn<Players, Void>, TableCell<Players, Void>>() {
//            @Override
//            public TableCell<Players, Void> call(final TableColumn<Players, Void> param) {
//                final TableCell<Players, Void> cell = new TableCell<Players, Void>() {
//
//                    private final Button btn = new Button("Invite");
//
//                    {
//                        btn.setOnAction((ActionEvent event) -> {
//                            Players data = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedData: " + data.getUsername());
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        invitecol.setCellFactory(cellFactory);
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}

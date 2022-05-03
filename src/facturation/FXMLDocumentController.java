package facturation;

import java.awt.print.*;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author NATHAN
 */
public class FXMLDocumentController implements Initializable {

    /**
     * ceci est le Anchorpane de l'identite
     */
    @FXML
    private AnchorPane principal_panr;
    @FXML
    private TextField first_name;
    @FXML
    private TextField second_name;
    @FXML
    private TextField adress;

    /**
     * ceci est le Achorpane de la facture
     */
    
    @FXML
    private ComboBox produit_deroulant;
    @FXML
    private TableView<fact> table_utilisateur;
    @FXML
    private TextField num_article;
    @FXML
    private TextField qtes;
    @FXML
    private TextField p_ts;
    @FXML
    private TextField p_u;
    @FXML
    private DatePicker dat;

    /**
     * ceci est le tableau des operations
     */

    @FXML
    private TableColumn<fact, Integer> col_idf;

    @FXML
    private TableColumn<fact, String> col_nom_produit;

    @FXML
    private TableColumn<fact, Integer> col_qte;
    @FXML
    private TableColumn<fact, Integer> col_prix_unitaire;
    @FXML
    private TableColumn<fact, Integer> col_prix_total;

    @FXML
    private TableColumn<fact, String> col_date;
    
    
        @FXML
    private TableView<personne> table_identite;

    @FXML
    private TableColumn<personne, Integer> col_id;

    @FXML
    private TableColumn<personne, String> col_nom;

    @FXML
    private TableColumn<personne, String> col_prenom;

    @FXML
    private TableColumn<personne, String> col_adresse;

    /**
     * ceci est les operation sur les boutons
     *
     * @param event
     */
    @FXML
    void btn_annuler_ident(ActionEvent event) {
        first_name.setText("");
        second_name.setText("");
        adress.setText("");
    }

    @FXML
    void btn_annuler_fact(ActionEvent event) {
        qtes.setText("");
        p_u.setText("");
        p_ts.setText("");
    }
    @FXML
    private AnchorPane pane_facture;

    @FXML
    private AnchorPane pane_identite;

    @FXML
    void show_facture(ActionEvent event) {
        pane_facture.setVisible(true);
        pane_identite.setVisible(false);
    }

    @FXML
    void show_identity(ActionEvent event) {
        pane_identite.setVisible(true);
        pane_facture.setVisible(false);
    }

    /**
     * la partie de la connexion la base de données
     */
    ObservableList<fact> listM = FXCollections.observableArrayList();
    ObservableList<personne> listM1 = FXCollections.observableArrayList();
    int index = -1;
    Connection conn = null;
    PreparedStatement pst = null;

    @FXML
    void ajout_identite(ActionEvent event) {
        conn = connection.connectbd();
        String sql = "insert into personne(nom,prenom,adresse) values(?,?,?)";
        try {

            pst = conn.prepareStatement(sql);
            pst.setString(1, first_name.getText());
            pst.setString(2, second_name.getText());
            pst.setString(3, adress.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "identite enregistrée");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void calcul() {

        int nb_quantite, nb_prix_unit;
        double resultat;

        nb_quantite = Integer.parseInt(qtes.getText());
        nb_prix_unit = Integer.parseInt(p_u.getText());
        resultat = nb_prix_unit * nb_quantite;
        p_ts.setText(String.valueOf(resultat));
    }

    @FXML
    void ajout_facture(ActionEvent event) {
        calcul();
        conn = connection.connectbd();
        String sql = "insert into facture(nom_produit,quantite,prix_unitaire,prix_total) values(?,?,?,?)";
        try {

            pst = conn.prepareStatement(sql);
            pst.setString(1, produit_deroulant.getValue().toString());
            pst.setString(2, qtes.getText());
            pst.setString(3, p_u.getText());
            pst.setString(4, p_ts.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "facture enregistrée");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        @FXML
    void btn_actualiser_ident(ActionEvent event) {
        col_id.setCellValueFactory(new PropertyValueFactory<personne, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<personne, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<personne, String>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<personne, String>("adresse"));
        listM1 = connection.dataFacture1();
        table_identite.setItems(listM1); 
    }

    @FXML
    void btn_actualiser(ActionEvent event) {

        col_idf.setCellValueFactory(new PropertyValueFactory<fact, Integer>("id"));
        col_nom_produit.setCellValueFactory(new PropertyValueFactory<fact, String>("nom_produit"));
        col_qte.setCellValueFactory(new PropertyValueFactory<fact, Integer>("qte"));
        col_prix_unitaire.setCellValueFactory(new PropertyValueFactory<fact, Integer>("prix_unitaire"));
        col_prix_total.setCellValueFactory(new PropertyValueFactory<fact, Integer>("prix_total"));
        col_date.setCellValueFactory(new PropertyValueFactory<fact, String>("date"));
        listM = connection.dataFacture2();
        table_utilisateur.setItems(listM);
    }

    @FXML
    void btn_print(ActionEvent event) {
        PrinterJob pJ = PrinterJob.getPrinterJob();
            boolean success = pJ.printDialog();// this is the important line 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produit_deroulant.getItems().addAll("chemise", "pantalon", "singlet", "lacost", "polo", "veste", "centure", "chaussure", "smoking");

        col_id.setCellValueFactory(new PropertyValueFactory<personne, Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<personne, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<personne, String>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<personne, String>("adresse"));
        
        col_idf.setCellValueFactory(new PropertyValueFactory<fact, Integer>("id"));
        col_nom_produit.setCellValueFactory(new PropertyValueFactory<fact, String>("nom_produit"));
        col_qte.setCellValueFactory(new PropertyValueFactory<fact, Integer>("qte"));
        col_prix_unitaire.setCellValueFactory(new PropertyValueFactory<fact, Integer>("prix_unitaire"));
        col_prix_total.setCellValueFactory(new PropertyValueFactory<fact, Integer>("prix_total"));
        col_date.setCellValueFactory(new PropertyValueFactory<fact, String>("date"));

        listM1 = connection.dataFacture1();
        table_identite.setItems(listM1);
        listM = connection.dataFacture2();
        table_utilisateur.setItems(listM);

    }
}

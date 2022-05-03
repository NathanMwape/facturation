package facturation;

import java.sql.*;
import javafx.collections.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class connection {

    Connection conn = null;

    public static Connection connectbd() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/facturation", "root", "");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     *
     * @return
     */

    
         public static ObservableList<personne> dataFacture1() {
        Connection cons = connectbd();
        ObservableList<personne> list = FXCollections.observableArrayList();
        try {

            PreparedStatement ps = cons.prepareStatement("select *from personne");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new personne(Integer.parseInt(rs.getString("id")), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try {
                
            } catch (Exception e) {
            }
        }
        return list;
    }
         
        public static ObservableList<fact> dataFacture2() {
        Connection cons = connectbd();
        ObservableList<fact> list = FXCollections.observableArrayList();
        try {

            PreparedStatement ps = cons.prepareStatement("select *from facture");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new fact(Integer.parseInt(rs.getString("id")),rs.getString("nom_produit"), Integer.parseInt(rs.getString("quantite")), Integer.parseInt(rs.getString("prix_unitaire")), Integer.parseInt(rs.getString("prix_total")),rs.getString("date_produit")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try {
                
            } catch (Exception e) {
            }
        }
        return list;
    }

}

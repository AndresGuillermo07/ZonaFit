package zona_fit.data;

import zona_fit.conexion.Conexion;
import zona_fit.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IClientDAO implements ClientDao{

    @Override
    public List<Client> listClients() {

        List<Client> clients = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = Conexion.getConnection();

        var query = "SELECT * FROM client ORDER BY idclient";
        try {

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setIdclient(rs.getInt("idclient"));
                client.setName(rs.getString("name"));
                client.setSurname(rs.getString("surname"));
                client.setMembership(rs.getInt("membership"));
                clients.add(client);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return clients;
    }

    @Override
    public boolean addClient(Client client) {

        Connection con = Conexion.getConnection();
        PreparedStatement ps = null;
        var query = "INSERT INTO client(name,surname,membership) VALUES(?,?,?)";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getMembership());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean editClient(Client client){
            Connection con = Conexion.getConnection();
            PreparedStatement ps = null;
            var query = "UPDATE client SET name=?,surname=?,membership=? WHERE idclient = ?";
        try {

            ps = con.prepareStatement(query);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getMembership());
            ps.setInt(4, client.getIdclient());
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public boolean findById(Client client) {

        Connection con = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        var query = "SELECT * FROM client WHERE idclient = ?";

        try{
            ps = con.prepareStatement(query);
            ps.setInt(1, client.getIdclient());
            rs = ps.executeQuery();

            if(rs.next()) {
                client.setName(rs.getString("name"));
                client.setSurname(rs.getString("surname"));
                client.setMembership(rs.getInt("membership"));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public boolean deleteClient(int id) {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = null;
            var query = "DELETE FROM client WHERE idclient = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

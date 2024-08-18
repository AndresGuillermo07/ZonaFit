package zona_fit.data;

import zona_fit.domain.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {

    List<Client> listClients();

    boolean addClient(Client client);

    boolean editClient(Client client) throws SQLException;

    boolean findById(Client client) throws SQLException;

    boolean deleteClient(int id);

}

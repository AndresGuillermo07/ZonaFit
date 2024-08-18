package zona_fit.domain;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private int idclient;
    private String name;
    private String surname;
    private int membership;

    public Client(int idclient) {
        this.idclient = idclient;
    }

    public Client(String name, String surname, int membership) {
        this.name = name;
        this.surname = surname;
        this.membership = membership;
    }

    @Override
    public String toString() {
        return
                "\nId -> " + idclient +
                "\nName -> " + name +
                "\nSurname -> " + surname +
                "\nMembership -> " + membership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return idclient == client.idclient && membership == client.membership && Objects.equals(name, client.name) && Objects.equals(surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idclient, name, surname, membership);
    }
}

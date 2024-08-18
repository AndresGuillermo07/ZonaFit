import zona_fit.data.*;
import zona_fit.domain.Client;

import java.util.Scanner;

public class AppZonaFit {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    public static void start(){
        System.out.print("""
                \n*** WELCOME TO ZONA FIT ***
                
                1. List Clients.
                2. Add Client.
                3. Modify Client.
                4. Delete Client.
                5. Find Client.
                6. Exit
                Choose an option ->  """);

        var option = sc.next();
        manageOptions(option);

    }

    static void manageOptions(String option){

        var iClientDAO = new IClientDAO();

        switch (option){
            case "1" -> {
                System.out.println("\n*** CLIENTS ***");
                iClientDAO.listClients().forEach(System.out::println);
                start();
            }
            case "2" -> {
                System.out.println("*** Adding a new client ***\n");
                System.out.print("Name: ");
                var name = sc.next();
                System.out.print("Surname: ");
                var surname = sc.next();
                System.out.print("Membership: ");
                var membership = sc.nextInt();
                if(iClientDAO.addClient(new Client(name, surname, membership))){
                    System.out.println("Client added");
                    start();
                }

            }
            case "3" -> {
                System.out.println("*** Modifying a new client ***\n");
                System.out.print("Type the id of the client you would like to update: ");
                var id = sc.nextInt();
                System.out.print("Name: ");
                var name = sc.next();
                System.out.print("Surname: ");
                var surname = sc.next();
                System.out.print("Membership: ");
                var membership = sc.nextInt();
                var client = new Client(id, name, surname, membership);
                if(iClientDAO.editClient(client))
                    System.out.println("\n Client updated");
                start();
            }
            case "4" -> {
                System.out.print("Type the id of the client you would like to delete: ");
                var id = sc.nextInt();
                if(iClientDAO.deleteClient(id))
                    System.out.println("Client deleted.");
                start();
            }
            case "5" -> {
                System.out.print("Type the id of the client you would like to find: ");
                var id = sc.nextInt();
                var client = new Client(id);
                if (iClientDAO.findById(client)){
                    System.out.println("Client found\n" + client);
                    start();
                }else{
                    System.out.println("\nClient not found\n");
                    start();
                }
            }
            case "6" -> {
                System.out.println("See you soon.");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid option.\n");
                start();
                }
        }

    }

}

package backend.project.services;

import backend.project.dtos.ClientDTO;
import backend.project.entities.Client;

import java.util.List;

public interface ClientService {

    Client insertClient(Client client);

    Client insertClientWithAlredyUserAccount(ClientDTO clientdto);

    Client insertClient(String firstName, String lastName, String gender, Integer age, String phone, String dni, String username, String password);
    void deleteClient(Long id);
    List<Client> listAllClients();
    Client findById(Long id);
    List<Client> findByPhone(String phone);

    Client findByIdUser(Long idUser);
}

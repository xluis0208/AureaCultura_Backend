package backend.project.servicesImplemetation;

import backend.project.dtos.ClientDTO;
import backend.project.entities.Client;
import backend.project.entities.User;
import backend.project.exceptions.InvalidDataException;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.AuthorityRepository;
import backend.project.repositories.ClientRepository;
import backend.project.repositories.UserRepository;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Client insertClient(Client client) {
        User user = client.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        user.setAuthority(authorityRepository.findByName("USUARIO"));
        userRepository.save(user);
        client.setUser(user);
        return clientRepository.save(client);
    }

    @Override
    public Client insertClientWithAlredyUserAccount(ClientDTO clientdto) {
        // Validaciones de campos obligatorios
        if (clientdto.getFirstName() == null || clientdto.getFirstName().isBlank()) {
            throw new InvalidDataException("First name cannot be empty");
        }
        if (clientdto.getLastName() == null || clientdto.getLastName().isBlank()) {
            throw new InvalidDataException("Last name cannot be empty");
        }
        if (clientdto.getGender() == null || clientdto.getGender().isBlank()) {
            throw new InvalidDataException("Gender cannot be empty");
        }
        if (clientdto.getAge() == null) {
            throw new InvalidDataException("Age cannot be empty");
        }
        if (clientdto.getPhone() == null || clientdto.getPhone().isBlank()) {
            throw new InvalidDataException("Phone cannot be empty");
        }
        if (clientdto.getDni() == null || clientdto.getDni().isBlank()) {
            throw new InvalidDataException("DNI cannot be empty");
        }
        if (clientdto.getUser_id() == null || clientdto.getUser_id().isBlank()) {
            throw new InvalidDataException("User ID cannot be empty");
        }

        // Buscar entidad relacionada
        User user = userRepository.findById(Long.parseLong(clientdto.getUser_id()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + clientdto.getUser_id()));

        // Validar que el usuario tenga una autoridad de tipo USUARIO y no ADMIN
        if (user.getAuthority() == null || !"USUARIO".equals(user.getAuthority().getName())) {
            throw new InvalidDataException("Client can only be associated with a USUARIO, not an ADMIN");
        }

        // Validar que el usuario no esté ya asociado a otro cliente
        if (clientRepository.existsByUser(user)) {
            throw new InvalidDataException("User with ID: " + clientdto.getUser_id() + " is already associated with another client");
        }

        // Crear el cliente
        Client client = new Client();
        client.setFirstName(clientdto.getFirstName());
        client.setLastName(clientdto.getLastName());
        client.setGender(clientdto.getGender());
        client.setAge(clientdto.getAge());
        client.setPhone(clientdto.getPhone());
        client.setDni(clientdto.getDni());
        client.setUser(user);
        client.setBibliografia(clientdto.getBio());

        // Guardar el cliente
        return clientRepository.save(client);

    }

    @Override
    public Client insertClient(String firstName, String lastName, String gender, Integer age, String phone, String dni, String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setActive(true);
        user.setAuthority(authorityRepository.findByName("USUARIO"));
        userRepository.save(user);

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setGender(gender);
        client.setAge(age);
        client.setPhone(phone);
        client.setDni(dni);
        client.setUser(user);

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {throw new ResourceNotFoundException("Client not found with ID: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
    }

    @Override
    public List<Client> findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    @Override
    public Client findByIdUser(Long idUser) {
        return clientRepository.findByUserId(idUser);
    }
}

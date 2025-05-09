package backend.project.controllers;

import backend.project.dtos.ClientDTO;
import backend.project.entities.Client;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> createClientAssociatedUser(@RequestBody ClientDTO clientdto) {
        Client savedClient = clientService.insertClientWithAlredyUserAccount(clientdto);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PostMapping("/create-param")
    public ResponseEntity<Client> createClientByDetails(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender,
                                                        @RequestParam Integer age, @RequestParam String phone, @RequestParam String dni,
                                                        @RequestParam  String username,
                                                        @RequestParam  String password) {
        Client savedClient = clientService.insertClient(firstName, lastName, gender, age, phone, dni, username,password);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.listAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/phone")
    public ResponseEntity<List<Client>> getClientByPhone(@RequestParam String phone) {
        List<Client> clients = clientService.findByPhone(phone);
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/buscarPorIdUser/{id}")
    public ResponseEntity<Client> buscarPorIdUser(@PathVariable Long id) {
        Client client = clientService.findByIdUser(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}

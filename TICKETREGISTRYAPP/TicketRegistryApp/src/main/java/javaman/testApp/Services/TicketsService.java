package javaman.testApp.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaman.testApp.Model.Tickets;
import javaman.testApp.Repository.TicketsRepository;


@Service
public class TicketsService {


    @Autowired
    private TicketsRepository ticketsRepository;
  
    public List<Tickets> getAllTickets() {
        return ticketsRepository.findAll();
    }

    public Tickets getTicketById(Long id) {
        Optional<Tickets> ticket = ticketsRepository.findById(id);
        return ticket.orElse(null);
    }
   
    public Tickets saveTicket(Tickets ticket) {
        return ticketsRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketsRepository.deleteById(id);
    }
}

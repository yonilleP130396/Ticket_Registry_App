package javaman.testApp.Controller;




import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javaman.testApp.Model.Tickets;
import javaman.testApp.Services.TicketsService;

@Controller
@RequestMapping("/admin")
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;

    // GET all tickets (view page)
    @GetMapping
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketsService.getAllTickets());
        return "tickets/admin"; // Thymeleaf page
    }

    // SHOW create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Tickets());
        return "tickets/add";
    }


    // SAVE ticket
    @PostMapping("/save")
    public String createTicket(@ModelAttribute Tickets ticket) {
        ticket.setCreatedAt(LocalDateTime.now());
        ticketsService.saveTicket(ticket);
        return "redirect:/admin/new?success"; 
    }

    @PostMapping("/update")
    public String updateTicket(@ModelAttribute Tickets ticket) {
        ticket.setCreatedAt(LocalDateTime.now());
        ticketsService.saveTicket(ticket);
        return "redirect:/admin?success"; 
    }


    // SHOW edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", ticketsService.getTicketById(id));
        return "tickets/update";
    }

    // DELETE ticket
    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketsService.deleteTicket(id);
        return "redirect:/admin?deletedsuccessfully";
    }
}

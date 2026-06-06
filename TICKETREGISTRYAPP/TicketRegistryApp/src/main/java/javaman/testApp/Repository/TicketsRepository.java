package javaman.testApp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaman.testApp.Model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
}

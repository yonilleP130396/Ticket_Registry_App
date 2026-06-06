package javaman.testApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javaman.testApp.Model.myUser;


public interface MyUserRepository extends JpaRepository<myUser,Long> {

    Optional<myUser> findByUsername(String username);
}

package net.springboot.practice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import net.springboot.practice.model.Login;
public interface LoginRepository extends JpaRepository<Login, Long>{

}
